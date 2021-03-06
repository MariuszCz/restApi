package pl.edu.uam.restapi.dokumentacjaibledy.exceptions;


import pl.edu.uam.restapi.dokumentacjaibledy.model.ExceptionMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResourceException extends WebApplicationException {
    public ResourceException(String message, String userMessage, String info) {
        //super(Responses.notFound().type(MediaType.APPLICATION_JSON).entity(message).build());
        super(Response.status(Response.Status.NOT_ACCEPTABLE).
                entity(createNoContentMessage(message, userMessage, info))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }


    private static ExceptionMessage createNoContentMessage(String message, String userMessage, String info) {
        return new ExceptionMessage(message, userMessage, info);
    }
}
