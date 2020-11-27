package ru.bellintegrator.practice.view;

/**
 * Класс-обертка для передачи ошибки
 */
public class ErrorView {

    /**
     * Текст ошибки
     */
    private final String error;

    public ErrorView(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
