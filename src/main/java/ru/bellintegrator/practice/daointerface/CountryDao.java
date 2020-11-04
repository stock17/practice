package ru.bellintegrator.practice.daointerface;

import ru.bellintegrator.practice.model.Country;

import java.util.List;

/**
 * DAO интерфейс для класса {@link ru.bellintegrator.practice.model.Country}
 */
public interface CountryDao {

    /**
     * Метод возвращает {@link ru.bellintegrator.practice.model.Country},
     * который соответствует переданному коду
     *
     * @param code код страны
     * @return     объект {@link ru.bellintegrator.practice.model.Country}
     */
    Country findByCode(Integer code);

    /**
     * Метод возвращает все {@link ru.bellintegrator.practice.model.Country},
     * содержащиеся в базе в виде списка
     *
     * @return список {@link ru.bellintegrator.practice.model.Country}
     */
    List<Country> findAll();

}
