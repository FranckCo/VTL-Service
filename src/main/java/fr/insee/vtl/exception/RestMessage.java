package fr.insee.vtl.exception;

public class RestMessage {
	private int status;
	private int state;
	private String message;
	private String details;

	public RestMessage(int status, int state, String message, String details) {
		this.status = status;
		this.state = state;
		this.message = message;
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
