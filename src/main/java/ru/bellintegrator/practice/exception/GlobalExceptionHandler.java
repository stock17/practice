package ru.bellintegrator.practice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.view.ErrorView;

/**
 * Класс обработки исключений
 *
 * Логгирует исключения, а оборачивает сообщение об ошибке в ErrorView
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String SERVER_ERROR_CODE = "500";
    private static final String NOT_FOUND_ERROR_CODE = "404";
    private static final String BAD_REQUEST_ERROR_CODE = "401";
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getSimpleName());

    /**
     * Метод обратывает исключение отсутствующего Id
     *
     * @return ErrorView
     */
    @ExceptionHandler({NoSuchIdException.class, NoSuchEntityException.class})
    public ErrorView handleNoSuchIdException(RuntimeException e) {
        LOGGER.error("user request error", e);
        return new ErrorView(e.getMessage() +": " + NOT_FOUND_ERROR_CODE);
    }

    /**
     * Метод обратывает исключение валидации входящих данных
     *
     * @return ErrorView
     */
    @ExceptionHandler(RequestDataValidationException.class)
    public ErrorView handleRequestDataValidationException(RequestDataValidationException e) {
        LOGGER.error("user request error", e);
        return new ErrorView(e.getMessage() +": " + BAD_REQUEST_ERROR_CODE);
    }
    /**
     * Метод обрататывает все остальные необработанные исключения
     *
     * @return ErrorView
     */
    @ExceptionHandler(Exception.class)
    public ErrorView handleException(Exception e) {
        LOGGER.error("unhandled server error", e);
        return new ErrorView("Ошибка сервера: " + SERVER_ERROR_CODE);
    }
}
