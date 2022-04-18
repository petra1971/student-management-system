package se.iths.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class SaveStudentException extends WebApplicationException {

    public SaveStudentException(Response message) {
        super(message);
    }

    public SaveStudentException(String message, int code) {
        super(message,  code);
    }

    public SaveStudentException(Response.ResponseBuilder status) {
        super(String.valueOf(status));
    }
}
