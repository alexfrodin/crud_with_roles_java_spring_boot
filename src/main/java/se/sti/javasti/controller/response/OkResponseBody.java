package se.sti.javasti.controller.response;

import java.time.LocalDateTime;

public class OkResponseBody<E> {

    private String date;
    private String message;
    private E data;

    public OkResponseBody(String message, E data) {
        this.date = LocalDateTime.now().toString();
        this.message = message;
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public E getData() {
        return data;
    }
}
