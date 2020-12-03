package ru.bellintegrator.practice.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import java.util.stream.Collectors;

/**
 * Исключение при ошибке валидации данных во входящем запросе
 */
public class RequestDataValidationException extends RuntimeException {

    private final Errors errors;

    public RequestDataValidationException(Errors errors) {
        super();
        this.errors = errors;
    }

    /**
     * Возвращает одной строкой все сообщения ошибок, возникшие при валидации полей
     */
    @Override
    public String getMessage() {
        return errors.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
    }
}
