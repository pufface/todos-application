package sk.fillo.todos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.fillo.todos.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
