package sk.fillo.todos;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sk.fillo.todos.repository.TodoRepository;
import sk.fillo.todos.repository.UserRepository;

@WebAppConfiguration
public class BaseTest {
	
	protected MediaType jsonMediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	protected RequestPostProcessor credentialsUser = httpBasic("user", "password");
	protected RequestPostProcessor credentialsJozko = httpBasic("jozko", "mrkvicka");
	@SuppressWarnings("rawtypes")
	protected HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	protected TodoRepository todoRepository;

	@Autowired
	protected UserRepository userRepository;

    @Autowired
    protected WebApplicationContext webApplicationContext;
    
    @Autowired
    protected Filter springSecurityFilterChain;
	
    @Autowired
    protected void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);
        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

	protected MockMvc buildMockMvc() {
		return MockMvcBuilders
			.webAppContextSetup(webApplicationContext)
			.addFilters(springSecurityFilterChain)
			.build();
	}
	
	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
}
