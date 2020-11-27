package ru.bellintegrator.practice.aspect;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.view.ErrorView;

/**
 * Класс обработки исключений
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Метод оборачивает исключение в класс ErrorView
     *
     * @return ErrorView
     */
    @ExceptionHandler(Exception.class)
    public ErrorView handleException(Exception e) {
        return new ErrorView(e.getMessage());
    }
}
