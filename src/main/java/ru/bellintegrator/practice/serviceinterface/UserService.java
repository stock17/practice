package ru.bellintegrator.practice.serviceinterface;

import ru.bellintegrator.practice.view.user.UserListRequestView;
import ru.bellintegrator.practice.view.user.UserListResponseView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;

/**
 * Интерфейс сервиса для работы с классом {@link ru.bellintegrator.practice.model.Organization}
 */
public interface UserService {

    /**
     * Метод сохраняет информацию о новом работнике
     * @param user DTO-объект с информацией о работнике, документе и гражданстве
     */
    void save(UserSaveView user);

    /**
     * Метод возвращает объект {@link ru.bellintegrator.practice.view.user.UserView}
     * соответствующий переданному Id
     *
     * @param id идентификатор работника
     * @return DTO для класса {@link ru.bellintegrator.practice.model.User}
     */
    UserView findById(long id);

    /**
     * Метод возращает список работников в соответствии с указанными параметрами
     *
     * @param requestView DTO запроса {@link ru.bellintegrator.practice.view.user.UserListRequestView} (обязательный параметр)
     * @return список работников в кратком виде
     */
    List<UserListResponseView> findByOfficeId(UserListRequestView requestView);

    /**
     * Метод обновляет данные об уже существующем работнике согласно переданным параметрам
     *
     * @param updateView DTO-объект работника
     */
    void update(UserUpdateView updateView);


}

