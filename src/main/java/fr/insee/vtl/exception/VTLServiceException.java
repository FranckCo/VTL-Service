package fr.insee.vtl.exception;

import java.io.IOException;

public class VTLServiceException extends IOException {

	private int status;
	private int state;
	private String details;

	/**
	 *
	 * @param status
	 * @param message
	 * @param details
	 */
	public VTLServiceException(int status, int state, String message, String details) {
		super(message);
		this.state = state;
		this.status = status;
		this.details = details;
	}

	public RestMessage toRestMessage() {
		return new RestMessage(this.status, this.state, this.getMessage(), this.details);
	}
}
