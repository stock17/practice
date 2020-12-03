package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для {@link ru.bellintegrator.practice.model.Country}
 */
public class CountryView {

    /**
     * Числовой код страны
     */
    @NotNull(message = "Код не может быть пустым")
    private Integer code;

    /**
     * Наименование страны
     */
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(max = 50, message = "Наименование не более 50 символов")
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
