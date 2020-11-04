package ru.bellintegrator.practice.service;


import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

/**
 * Интерфейс сервиса для работы с классом {@link ru.bellintegrator.practice.model.Country}
 */
public interface CountryService {
    /**
     * Метод возвращает объект {@link ru.bellintegrator.practice.view.CountryView}
     * соответствующий переданному коду
     *
     * @param code код страны
     * @return DTO для класса {@link ru.bellintegrator.practice.model.Country}
     */
    CountryView findByCode(Integer code);

    /**
     * Метод сохраняет переданный объект
     * @param countryView DTO-объект
     */
    void addCountry(CountryView countryView);

    /**
     * Метод возращает все найденные страны в виде списка DTO-объектов
     * @return список {@link ru.bellintegrator.practice.model.Country}
     */
    List<CountryView> findAll();



}
