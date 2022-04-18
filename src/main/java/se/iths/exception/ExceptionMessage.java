package se.iths.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StudentException {
    public int code;
    public String status;
    public String message;

    public StudentException(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getJsonException() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(this);
    }
}
