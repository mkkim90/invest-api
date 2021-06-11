package invest.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(message, httpStatus.value());
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
