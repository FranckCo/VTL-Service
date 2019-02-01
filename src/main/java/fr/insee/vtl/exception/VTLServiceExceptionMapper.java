package fr.insee.vtl.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class VTLServiceExceptionMapper implements ExceptionMapper<VTLServiceException> {
	public Response toResponse(VTLServiceException ex) {
		RestMessage message = ex.toRestMessage();
		return Response.status(message.getStatus()).entity(message).type(MediaType.APPLICATION_JSON).build();
	}
}
