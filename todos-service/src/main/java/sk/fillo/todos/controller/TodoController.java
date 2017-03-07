package sk.fillo.todos.controller;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sk.fillo.todos.UserBean;
import sk.fillo.todos.model.Todo;
import sk.fillo.todos.repository.TodoRepository;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserBean user;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<Todo> getAllTodos() {
		return todoRepository.findByUser_id(user.getId());
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Todo createTodo(@RequestBody Todo todo) {
		todo.setUser(user.getUser());
		return todoRepository.save(todo);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@Transactional
	public @ResponseBody Todo updateTodo(@RequestBody Todo todo) {
		Todo origin = todoRepository.findByIdAndUser_id(todo.getId(), user.getId())
				.orElseThrow(() -> new TodoNotFoundException(todo));
		origin.setContent(todo.getContent());
		origin.setDone(todo.isDone());
		return todoRepository.save(origin);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable long id) {
		Todo todo = todoRepository.findByIdAndUser_id(id, user.getId())
				.orElseThrow(() -> new TodoNotFoundException(id));
		todoRepository.delete(todo);
	}

}
