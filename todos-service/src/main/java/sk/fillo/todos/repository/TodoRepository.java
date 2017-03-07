package sk.fillo.todos.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.fillo.todos.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	Collection<Todo> findByUser_id(long id);
	
	Optional<Todo> findByIdAndUser_id(long id, long userId);

	Collection<Todo> findAllByContent(String content);

}
