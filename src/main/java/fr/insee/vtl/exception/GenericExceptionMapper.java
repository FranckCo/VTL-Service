package fr.insee.vtl.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private Status STATUS = Status.INTERNAL_SERVER_ERROR;

    public Response toResponse(Throwable error){
    	error.printStackTrace();
    	RestMessage message = new RestMessage(
                STATUS.getStatusCode(),-1,
                "An unexpected error occured", error.getMessage());
        if(error instanceof NotFoundException) {
            STATUS = Status.NOT_FOUND;
            message.setMessage("Not Found");
            message.setDetails("No JAX-RS resource found for this path");
        }
        return Response.status(STATUS)
                .entity(message)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
