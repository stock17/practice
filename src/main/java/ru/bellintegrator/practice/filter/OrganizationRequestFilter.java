package ru.bellintegrator.practice.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO-класс для фильтрации запроса списка организаций
 */
public class OrganizationRequestFilter {

    /**
     * Краткое наименование организации
     */
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(max = 50)
    private String name;

    /**
     * Идентификационный налоговый номер
     */
    @Size(min = 10, max = 10)
    private String inn;

    /**
     * Действующий статус
     */
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setActive(Boolean active) {
        isActive = active;
    }
}
