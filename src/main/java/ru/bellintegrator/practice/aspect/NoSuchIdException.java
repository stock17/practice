package ru.bellintegrator.practice.aspect;

/**
 * Исключение при отсутствии объекта с указанным ID в базе
 */
public class NoSuchIdException extends RuntimeException {
    public NoSuchIdException(String message) {
        super(message);
    }

    public NoSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
