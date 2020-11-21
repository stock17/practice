package ru.bellintegrator.practice.daointerface;

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
     * @param officeId  идентификатор офиса (обязательный параметр)
     * @param firstName имя
     * @param middleName отчество
     * @param secondName фамилия
     * @param position должность
     * @param docCode тип документа, удостоверяющего личность
     * @param citizenship код гражданства
     * @return список работников
     */
    List<User> findByOfficeId(Integer officeId, String firstName, String middleName, String secondName,
                              String position, Integer docCode, Integer citizenship);
}
