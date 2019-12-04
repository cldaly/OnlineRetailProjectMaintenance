package com.onlineretail.exception;

public class DuplicateSubCategoryException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateSubCategoryException() {
		super();
	}

	public DuplicateSubCategoryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateSubCategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateSubCategoryException(String message) {
		super(message);
	}

	public DuplicateSubCategoryException(Throwable cause) {
		super(cause);
	}
	
}
