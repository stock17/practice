package ru.bellintegrator.practice.view.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO-класс для отображения информации о работнике {@link ru.bellintegrator.practice.model.User} в кратком виде в списке
 */
public class UserListView {

    /**
     * Id
     */
    @NotNull(message = "Id не может быть пустым")
    private long id;

    /**
     * Имя
     */
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(max = 50)
    private String firstName;

    /**
     * Отчество
     */
    @Size(max = 50)
    private String middleName;

    /**
     * Фамилия
     */
    @Size(max = 50)
    private String secondName;

    /**
     * Должность
     */
    @NotEmpty(message = "Должность не может быть пустой")
    @Size(max = 50)
    private String position;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
