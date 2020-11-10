package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * DTO-класс для {@link ru.bellintegrator.practice.model.Organization}
 * для передачи параметров в кратком виде
 */
public class OrganizationShortView {

    /**
     * Id
     */
    private long id;

    /**
     * Краткое наименование организации
     */
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(max = 50)
    private String name;

    /**
     * Действующий статус
     */
    private Boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
