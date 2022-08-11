package com.example.employeeManager.service;

import java.util.List;

import com.example.employeeManager.exception.TodoCollectionException;
import com.example.employeeManager.model.TodoDTO;

public interface TodoService {

	public void createTodo(TodoDTO todo) throws TodoCollectionException;
	
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO getSingleTodo(String id) throws TodoCollectionException;
	
	public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;
	
	public void deleteTodo(String id) throws TodoCollectionException;
}
