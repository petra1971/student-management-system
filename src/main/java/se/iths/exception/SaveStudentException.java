package se.iths.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class SaveStudentException extends WebApplicationException {

    public SaveStudentException(Response response) {
        super(response);
    }

}
