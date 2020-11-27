package ru.bellintegrator.practice.aspect;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.view.DataView;
import ru.bellintegrator.practice.view.StatusView;

/**
 * Класс для оборачивания возвраемых данных
 */
@RestControllerAdvice
public class DataResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Метод исключает из обработки объекта класса StatusView
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getParameterType() != StatusView.class;
    }

    /**
     * Иетод оборачивает данные в блок {data:value}
     */
    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        return new DataView(obj);
    }


}
