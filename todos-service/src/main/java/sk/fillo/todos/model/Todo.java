package sk.fillo.todos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String content;
	private boolean done = false;
	
	@ManyToOne
	private User user;

	public Todo() {
	}

	public Todo(String content, boolean done, User user) {
		this.content = content;
		this.done = done;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
