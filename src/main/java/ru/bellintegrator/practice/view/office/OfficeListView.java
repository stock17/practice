package ru.bellintegrator.practice.view.office;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для возращения списка офисов {@link ru.bellintegrator.practice.model.Office} в кратком виде по запросу
 */
public class OfficeListView {

    /**
     * ID
     */
    @NotNull(message = "Id не должно быть пустым")
    private Long id;

    /**
     * Наименование
     */
    @Size(max = 50)
    private String name;

    /**
     * Действующий статус
     */
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
