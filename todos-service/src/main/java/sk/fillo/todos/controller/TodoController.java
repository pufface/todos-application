package sk.fillo.todos.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sk.fillo.todos.model.Todo;
import sk.fillo.todos.service.TodoService;

@RestController
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
		return todoService.save(todo);
    }
	
	@RequestMapping(path="/todos", method=RequestMethod.PUT)
    public @ResponseBody Todo updateTodo(@RequestBody Todo todo, Principal principal) {
		return todoService.save(todo);
    }
	
	@RequestMapping(path="/todos/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable(value="id") long id, Principal principal) {
		todoService.delete(id);
    }

}
