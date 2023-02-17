package com.yohan.myfirstapp;

import java.util.Date;

public class ExceptionResponse {
private Date dateTime;
private String message;
private String Description;

public ExceptionResponse(Date dateTime, String message, String description) {
	super();
	this.dateTime = dateTime;
	this.message = message;
	Description = description;
}

public Date getDateTime() {
	return dateTime;
}
public String getMessage() {
	return message;
}
public String getDescription() {
	return Description;
}


}
