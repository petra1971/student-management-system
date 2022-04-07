package se.iths.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.Gson;

public class SaveStudentException extends WebApplicationException {

    public SaveStudentException(String message) {
        super(message);
    }

    public SaveStudentException(String message, int code) {
        super(message, code);
    }
}
