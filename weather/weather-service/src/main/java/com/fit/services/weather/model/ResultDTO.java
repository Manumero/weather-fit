package com.fit.services.weather.model;

public class ResultDTO<T> {
	private String status;
	private String message;
	private T details;

	public ResultDTO() {	
	}

	public ResultDTO(String status, String message, T details) {
		this.status = status;
		this.message = message;
		this.details = details;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getDetails() {
		return details;
	}

	public void setDetails(T details) {
		this.details = details;
	}

}
