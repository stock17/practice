package ru.bellintegrator.practice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.serviceinterface.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationShortView;
import ru.bellintegrator.practice.view.OrganizationView;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
* Класс контроллер для операций с организациями {@link ru.bellintegrator.practice.model.Organization}
*/
@RestController
@RequestMapping(value = "/api/organization/", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService service;

    @Autowired
    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    /**
     * Метод возвращает организацию с указанным Id
     *
     * @param id идентификатор организации
     * @return организация
     */
    @GetMapping("/{id}")
    public OrganizationView getOrganizationById(@NotNull @PathVariable(name = "id") long id) {
        return service.findById(id);
    }

    /**
     * Метод возвращает список организаций в кратком виде, у которых совпадают переданные параметры
     *
     * @param body map, содержащая ключи name, inn и isActive
     * @return список организаций в кратком виде
     */
    @PostMapping("/list")
    public List<OrganizationShortView> getOrganizationsByName(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        Objects.requireNonNull(name);
        String inn = body.get("inn");
        String isActiveString = body.get("isActive");
        Boolean isActive = null;
        if (isActiveString != null) {
            isActive = Boolean.parseBoolean(isActiveString);
        }
        return service.findByName(name, inn, isActive);
    }

    /**
     * Метод сохраняет организацию с переданными параметрами
     *
      * @param organizationView организация
     * @return результат сохранения
     */
    @PostMapping("/save")
    public String save(@RequestBody @Valid OrganizationView organizationView) {
        service.save(organizationView);

       //TODO refactor return success
        return "{\"result\":\"success\"}";
    }

    /**
     * Метод обновляет запись об организации согласно переданным параметрам
     *
     * @param organizationView организация
     * @return результат обновления
     */
    @PostMapping("/update")
    public String update(@RequestBody @Valid OrganizationView organizationView) {
        if (organizationView.getId() == 0) {
            throw new ValidationException("поле Id не может быть пустым");
        }
        service.update(organizationView);

        //TODO refactor return success
        return "{\"result\":\"success\"}";
    }
}
