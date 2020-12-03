package ru.bellintegrator.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.aspect.RequestDataValidationException;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.StatusView;
import ru.bellintegrator.practice.filter.UserRequestFilter;
import ru.bellintegrator.practice.view.user.UserListView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для обработки запросов по адресу: "/api/user/
 * Проводит операции с работниками {@link ru.bellintegrator.practice.model.User}
 */
@RestController
@RequestMapping(value = "/api/user/", produces = APPLICATION_JSON_VALUE + "; charset=utf-8")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод добавляет нового работника
     *
     * @param saveView DTO работника
     */
    @PostMapping("/save")
    public void save(@Valid @RequestBody UserSaveView saveView, Errors errors) {
        if (errors.hasErrors()) {
            throw new RequestDataValidationException(errors);
        }
        userService.save(saveView);
    }

    /**
     * Метод возвращает информацию о работнике с указанным ID
     *
     * @param id идентификатор работника
     * @return работник
     */
    @GetMapping("/{id}")
    public UserView getById(@PathVariable long id) {
        return userService.findById(id);
    }

    /**
     * Метод возвращает список работников, соответствующих переданным параметром
     *
     * @param filter DTO c параметрами запроса
     * @return список работников
     */
    @PostMapping("/list")
    public List<UserListView> getByOfficeId(@Valid @RequestBody UserRequestFilter filter) {
        return userService.findByFilter(filter);
    }

    /**
     * Метод обновляет информацию о существующем работнике
     *
     * @param updateView обновляемые параметры
     */
    @PostMapping("/update")
    public void update(@Valid @RequestBody UserUpdateView updateView, Errors errors) {
        if (errors.hasErrors()) {
            throw new RequestDataValidationException(errors);
        }
        userService.update(updateView);
    }
}
