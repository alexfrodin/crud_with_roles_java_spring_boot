package se.sti.javasti.controller.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorResponse {

    private Map<String, Object> error = new LinkedHashMap<>();

    public ErrorResponse(String message, HttpStatus status) {
        error.put("Timestamp", LocalDateTime.now());
        error.put("Message", message);
        error.put("Status", status);
    }

    public Map<String, Object> getErrorResponse() {
        return error;
    }
}
