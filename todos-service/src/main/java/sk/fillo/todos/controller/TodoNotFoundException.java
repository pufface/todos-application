package sk.fillo.todos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import sk.fillo.todos.model.Todo;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TodoNotFoundException(long id) {
		super(String.format("Could not find todo [id: %d]", id));
	}

	public TodoNotFoundException(Todo todo) {
		this(todo.getId());
	}

}
