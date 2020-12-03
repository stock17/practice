package ru.bellintegrator.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.CountryService;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллер для операций со странами
 */
@RestController
@RequestMapping(value = "/api/countries/", produces = APPLICATION_JSON_VALUE + "; charset=utf-8")
public class CountryController {

    private final CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    /**
     * Возвращает список стран, содержащихся в справочнике, в виде списка DTO-объектов
     *
     * @return список {@link ru.bellintegrator.practice.view.CountryView}
     */
    @GetMapping("/")
    public List<CountryView> getCountries() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public CountryView getByCode(@PathVariable Integer code) {
        return service.findByCode(code);
    }
}
