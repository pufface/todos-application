package sk.fillo.todos;

import sk.fillo.todos.model.User;

public class UserBean {

	User user;

	public UserBean(User user) {
		this.user = user;
	}

	public long getId() {
		return user.getId();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public User getUser() {
		return user;
	}

}
