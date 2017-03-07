package sk.fillo.todos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sk.fillo.todos.UserBean;
import sk.fillo.todos.model.User;

@RestController
public class UserController {

	@Autowired
	private UserBean userBean;

	@RequestMapping(path = "/userinfo", method = RequestMethod.GET)
	public @ResponseBody User login() {
		return userBean.getUser();
	}

}
