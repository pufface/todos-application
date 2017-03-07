package sk.fillo.todos.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique=true)
	private String username;
	
//	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
//	private Set<Todo> todos;
//	
//	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
//	private Set<Label> labels;
//	
//	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
//	private Set<Project> projects;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

//	public Set<Todo> getTodos() {
//		return todos;
//	}
//
//	public Set<Label> getLabels() {
//		return labels;
//	}
//
//	public Set<Project> getProjects() {
//		return projects;
//	}

}
