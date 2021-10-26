package com.job.demo.exceptions;

public class EmploiCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmploiCollectionException(String message){
		super(message);
	}
	
	public static String NotFoundException(String id){
		return "Job with this "+id+"not found";
	}
	
	public static String EmploiAlreadyExists(){
		return "Job with given name already Exists";
	}
	
}
