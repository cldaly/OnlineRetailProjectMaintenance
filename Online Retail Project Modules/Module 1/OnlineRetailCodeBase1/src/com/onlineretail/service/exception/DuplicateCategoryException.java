package com.onlineretail.service.exception;

public class DuplicateCategoryException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateCategoryException(String message) {
		super(message);
	}
}
