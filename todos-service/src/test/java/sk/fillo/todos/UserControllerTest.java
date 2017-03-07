package sk.fillo.todos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserControllerTest extends BaseTest {
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = buildMockMvc();
	}

	@Test
	public void userInfo() throws Exception {
		mockMvc.perform(get("/userinfo/").with(credentialsJozko))
			.andExpect(status().isOk())
			.andExpect(content().contentType(jsonMediaType))
			.andExpect(jsonPath("$.id").isNumber())
			.andExpect(jsonPath("$.username").isString())
			.andExpect(jsonPath("$.username").value("jozko"));
	}

}
