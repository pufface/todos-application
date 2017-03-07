package sk.fillo.todos;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AuthenticationTest extends BaseTest {

    private MockMvc mockMvc;
    
	@Before
    public void setup() throws Exception {
        this.mockMvc = buildMockMvc();
    }

    @Test
    public void userNoCredentials() throws Exception {
        mockMvc.perform(get("/userinfo/"))
        		.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void userAuth() throws Exception {
        mockMvc.perform(get("/userinfo/").with(httpBasic("user","password")))
        		.andExpect(status().isOk());
    }
    
    @Test
    public void userInvalidAuth() throws Exception {
        mockMvc.perform(get("/userinfo/").with(httpBasic("user","invalidPassword")))
        		.andExpect(status().isUnauthorized());
    }

}
