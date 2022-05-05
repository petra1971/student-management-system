package se.iths.exception;

public class ExceptionMessage {
    public int code;
    public String status;
    public String message;

    public ExceptionMessage(String message) {
        this.message = message;
    }

    public ExceptionMessage(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
