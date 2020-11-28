package ru.bellintegrator.practice.daointerface;

import ru.bellintegrator.practice.filter.UserRequestFilter;
import ru.bellintegrator.practice.model.User;

import java.util.List;

/**
 * DAO интерфейс для класса {@link ru.bellintegrator.practice.model.User}
 */
public interface UserDao {
    /**
     * Метод возвращает {@link ru.bellintegrator.practice.model.User} по Id
     *
     * @param id идентификатор
     * @return объект {@link ru.bellintegrator.practice.model.User}
     */
    User findById(long id);

    /**
     * Метод обноваляет данные уже существующего работника {@link ru.bellintegrator.practice.model.User}
     * @param user работник
     */
    void update(User user);

    /**
     * Метод добавляет нового работника {@link ru.bellintegrator.practice.model.User}
     * @param user работник
     */
    void save(User user);

    /**
     * Метод возвращает список работников, работающих в офисе с указанным officeId
     * и соотвествующих дополнительным параметрам (при наличии)
     *
     * @param filter DTO c параметрами запроса
     * @return список работников
     */
    List<User> findByFilter(UserRequestFilter filter);
}
