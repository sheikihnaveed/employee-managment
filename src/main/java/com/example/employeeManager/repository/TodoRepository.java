package com.example.employeeManager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.employeeManager.model.TodoDTO;
import java.lang.String;
import java.util.List;
import java.util.Optional;


@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {

	@Query("{'todo':?0}")
	Optional<TodoDTO> findByTodo(String todo);
}
