package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * DTO-класс для передачи статуса запроса
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusView {

    SUCCESS("success"),
    FAILURE("failure");

    /**
     * Результат запроса
     */
    private final String result;

    StatusView(String status) {
        this.result = status;
    }

    public String getResult() {
        return result;
    }
}
