package sk.fillo.todos.service;

import java.util.List;

import sk.fillo.todos.model.Todo;

public interface TodoService {
	
	List<Todo> getAll();
	
	Todo save(Todo todo);
	
	void delete(long id);

}
