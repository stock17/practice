package ru.bellintegrator.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.serviceinterface.UserService;
import ru.bellintegrator.practice.view.user.UserListRequestView;
import ru.bellintegrator.practice.view.user.UserListResponseView;
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
@RequestMapping(value = "/api/user/", produces = APPLICATION_JSON_VALUE)
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
     * @return результат обновления
     */
    @PostMapping("/save")
    public String save(@Valid @RequestBody UserSaveView saveView) {
        userService.save(saveView);
        //TODO refactor return success
        return "{\"result\":\"success\"}";
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
     * @param requestView DTO c параметрами запроса
     * @return список работников
     */
    @PostMapping("/list")
    public List<UserListResponseView> getByOfficeId(@Valid @RequestBody UserListRequestView requestView) {
        return userService.findByOfficeId(requestView);
    }

    /**
     * Метод обновляет информацию о существующем работнике
     *
     * @param updateView обновляемые параметры
     * @return результат обновления
     */
    @PostMapping("/update")
    public String update(@Valid @RequestBody UserUpdateView updateView) {
        userService.update(updateView);
        //TODO refactor return success
        return "{\"result\":\"success\"}";
    }
}
