package se.iths.entity;

public class StudentException {
    public int code;
    public String status;
    public String message;

    public StudentException(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
