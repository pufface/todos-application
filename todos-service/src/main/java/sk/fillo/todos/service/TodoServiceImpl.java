package sk.fillo.todos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.fillo.todos.model.Todo;
import sk.fillo.todos.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> getAll() {
		return todoRepository.findAll();
	}

	@Override
	public Todo create(String content) {
		Todo todo = new Todo();
		todo.setContent(content);
		return todoRepository.save(todo);
	}

}
