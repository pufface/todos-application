package sk.fillo.todos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.fillo.todos.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
