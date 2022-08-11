package com.example.employeeManager.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeManager.exception.TodoCollectionException;
import com.example.employeeManager.model.TodoDTO;
import com.example.employeeManager.repository.TodoRepository;
import com.example.employeeManager.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired
	private TodoService todoServices;

	@GetMapping("/todos/getAll")
	public ResponseEntity<?> getAllTodos() {
		List<TodoDTO> todos = todoServices.getAllTodos();
		return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK: HttpStatus.NOT_FOUND);
	}

	@PostMapping("/todos/addNew")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
		try {
			todoServices.createTodo(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/todos/getSingle/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id")String id){
		try {
			return new ResponseEntity<>(todoServices.getSingleTodo(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PutMapping("/todos/update/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable("id")String id, @RequestBody TodoDTO todo){
		
		try {
			todoServices.updateTodo(id, todo);
			return new ResponseEntity<>("Update Todo with id "+ id, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (TodoCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/todos/deleteTodo/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") String id){
		try {
			
			todoServices.deleteTodo(id); 
			return new ResponseEntity<>("SuccessFully Delete Todo with id "+id, HttpStatus.OK);	
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}

}







