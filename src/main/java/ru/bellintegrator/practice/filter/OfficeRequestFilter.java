package ru.bellintegrator.practice.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для запроса списка офисов {@link ru.bellintegrator.practice.model.Office},
 * соотвествующих переданным параметрам
 */
public class OfficeRequestFilter {

    /**
     * Id организации, к которой относится офис
     */
    @NotNull(message = "Id организации не должно быть пустым")
    private long orgId;

    /**
     * Наименование
     */
    @Size(max = 50)
    private String name;

    /**
     * Телефон
     */
    @Size(max = 20)
    private String phone;

    /**
     * Действующий статус
     */
    private Boolean isActive;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setActive(Boolean active) {
        isActive = active;
    }
}
