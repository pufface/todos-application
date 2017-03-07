package sk.fillo.todos;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import sk.fillo.todos.model.Todo;
import sk.fillo.todos.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TodosControllerTest extends BaseTest {
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = buildMockMvc();
		todoRepository.deleteAllInBatch();
		userRepository.deleteAllInBatch();
	}

	@Test
	public void createTodo() throws Exception {
		Todo todo = new Todo("something", false, null);
		mockMvc.perform(post("/todos/")
				.with(credentialsUser)
				.contentType(jsonMediaType)
				.content(json(todo)))
			.andExpect(status().isOk())
			.andExpect(content().contentType(jsonMediaType))
			.andExpect(jsonPath("$.id").isNumber())
			.andExpect(jsonPath("$.content").value("something"))
			.andExpect(jsonPath("$.user.username").value("user"));
		Collection<Todo> todos = todoRepository.findAllByContent("something");
		assertFalse(todos.isEmpty());
	}
	
	@Test
	public void createTodoForUser() throws Exception {
		String uniqueContent = "somenthing" + System.currentTimeMillis();
		Todo todo = new Todo(uniqueContent, false, null);

		// create todo for specific user
		mockMvc.perform(post("/todos/")
				.with(credentialsUser)
				.contentType(jsonMediaType)
				.content(json(todo)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.user.username").value("user"));
		Collection<Todo> todos = todoRepository.findAllByContent(uniqueContent);
		assertTrue(todos.size() == 1);
	}

	@Test
	public void readTodoForUser() throws Exception {
		User user = userRepository.save(new User("user"));
		User jozko = userRepository.save(new User("jozko"));
		todoRepository.save(Arrays.asList(
					new Todo("one", false, user),
					new Todo("two", false, user)));
		todoRepository.save(Arrays.asList(
					new Todo("three", false, jozko),
					new Todo("four", false, jozko)));
		
		// read only todos of auth user 
		mockMvc.perform(get("/todos/")
				.with(credentialsUser)
				.contentType(jsonMediaType))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$..content").value(hasItem("one")))
			.andExpect(jsonPath("$..content").value(hasItem("two")));
	}

	@Test
	public void deleteTodoForUser() throws Exception {
		User jozko = userRepository.save(new User("jozko"));
		Todo todoJozko = todoRepository.save(new Todo("xxx", false, jozko));

		// delete other user's todo is forbidden 
		mockMvc.perform(delete("/todos/" + todoJozko.getId())
				.with(credentialsUser))
			.andExpect(status().isNotFound());

		// delete own todo is ok
		mockMvc.perform(delete("/todos/" + todoJozko.getId())
				.with(credentialsJozko))
			.andExpect(status().isOk());		
		Todo todo = todoRepository.findOne(todoJozko.getId());
		assertNull(todo);
	}
	
	@Test
	public void updateTodoForUser() throws Exception {
		User jozko = userRepository.save(new User("jozko"));
		Todo todoJozko = todoRepository.save(new Todo("xxx", false, jozko));
		
		// edit other user's todo is forbidden
		mockMvc.perform(put("/todos/")
				.with(credentialsUser)
				.contentType(jsonMediaType)
				.content(json(todoJozko)))
			.andExpect(status().isNotFound());
			
		// edit own todo is ok
		todoJozko.setDone(true);
		mockMvc.perform(put("/todos/")
				.with(credentialsJozko)
				.contentType(jsonMediaType)
				.content(json(todoJozko)))
			.andExpect(status().isOk());
		Todo todo = todoRepository.findOne(todoJozko.getId());
		assertNotNull(todo);
		assertTrue(todo.isDone());
	}

}
