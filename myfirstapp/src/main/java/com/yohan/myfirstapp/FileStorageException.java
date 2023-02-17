package com.yohan.myfirstapp;


public class FileStorageException extends RuntimeException {
	
	public String newsString;
	
    public FileStorageException(String message) {
        super(message);
        newsString=message;
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}