package sk.fillo.todos.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	
	@OneToMany(mappedBy="user")
	private Set<Todo> todos;
	
	@OneToMany(mappedBy="user")
	private Set<Label> labels;
	
	@OneToMany(mappedBy="user")
	private Set<Project> projects;

}
