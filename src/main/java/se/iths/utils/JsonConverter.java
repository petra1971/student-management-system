package se.iths.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.iths.exception.ExceptionMessage;

public class JsonConverter {

    public static String convertStringToJson(String string) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(string);
    }

    public static String convertExceptionToJson(ExceptionMessage exceptionMessage) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(exceptionMessage);
    }
}
