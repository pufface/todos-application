package sk.fillo.todos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import sk.fillo.todos.model.User;
import sk.fillo.todos.repository.UserRepository;

@Configuration
public class UserBeanConfig extends WebMvcConfigurerAdapter {

	@Autowired
	UserRepository userRepository;

	@Bean
	@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public UserBean currentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return new UserBean(getOrCreateUser(username));
	}

	@Transactional
	private User getOrCreateUser(String username) {
		return userRepository.findByUsername(username).orElseGet(() -> userRepository.save(new User(username)));
	}

}
