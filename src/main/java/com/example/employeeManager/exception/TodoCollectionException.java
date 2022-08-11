package com.example.employeeManager.exception;

public class TodoCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TodoCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Todo with "+id+" not Found";
	}
	
	public static String TodoAlreadyExists() {
		return "todo with same name already exists";
	}

}
