package ru.bellintegrator.practice.exception;

/**
 * Исключение при пустом результате поиска по фильтру
 */
public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String message) {
        super(message);
    }
}
