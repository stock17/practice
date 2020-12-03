package ru.bellintegrator.practice.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для {@link ru.bellintegrator.practice.model.DocumentType}
 */
public class DocumentTypeView {
    /**
     * Числовой код документа
     */
    @NotNull(message = "Код не может быть пустым")
    private Integer code;

    /**
     * Наименование документа
     */
    @Size(max = 127, message = "Наименование не более 127 символов")
    @NotEmpty(message = "Наименование не может быть пустым")
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
