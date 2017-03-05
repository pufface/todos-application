package sk.fillo.todos.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sk.fillo.todos.model.Todo;
import sk.fillo.todos.service.TodoService;

@RestController
@Secured("ROLE_USER")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(path="/todos", method=RequestMethod.GET)
    public @ResponseBody List<Todo> getAllTodos(Principal principal) {
		System.out.println(principal.getName());
		return todoService.getAll();
    }
	
	@RequestMapping(path="/todos", method=RequestMethod.POST)
    public @ResponseBody Todo createTodo(@RequestBody Todo todo, Principal principal) {
		return todoService.create(todo.getContent());
    }

}
