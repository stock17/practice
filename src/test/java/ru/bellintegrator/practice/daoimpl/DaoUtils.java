package ru.bellintegrator.practice.daoimpl;

import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.User;

import java.sql.Date;

public class DaoUtils {

    private DaoUtils() {}

    public static Organization createOrganization() {
        final String NAME = "Интерстеллар";
        final String FULL_NAME = "Межзвездное пространство";
        final String INN = "1234567890";
        final String KPP = "987654321";
        final String ADDRESS = "Марс, Пустыня, Кюриосити";
        final String PHONE = "000-000-000";
        final Boolean IS_ACTIVE = true;

        Organization organization = new Organization();
        organization.setName(NAME);
        organization.setFullName(FULL_NAME);
        organization.setInn(INN);
        organization.setKpp(KPP);
        organization.setAddress(ADDRESS);
        organization.setPhone(PHONE);
        organization.setActive(IS_ACTIVE);

        return organization;
    }

    public static Office createOffice() {

        Office office = new Office();
        office.setName("Первый");
        office.setOrganization(createOrganization());
        office.setAddress("Московское шоссе, 111");
        office.setPhone("000-000-000");
        office.setActive(true);

        return office;
    }

    public static User createUser() {
        User user = new User();
        user.setFirstName("Иван");
        user.setMiddleName("Иванович");
        user.setSecondName("Иванов");
        user.setAddress("город Москва");
        user.setPhone("322-112");
        user.setPosition("Джун");
        user.setOffice(createOffice());
        user.setCitizenship(createCitizenship());
        return user;
    }

    public static Country createCitizenship() {
        Country citizenship = new Country();
        citizenship.setName("Ямайка");
        citizenship.setCode(999);
        return citizenship;
    }

    public static Country createCitizenship(int code) {
        Country citizenship = new Country();
        citizenship.setName("Ямайка");
        citizenship.setCode(code);
        return citizenship;
    }

    public static Document createDocument(User user) {
        Document document = new Document();
        document.setUser(user);
        document.setDocumentType(createDocumentType());
        document.setDocNumber("9900123456");
        document.setDocDate(Date.valueOf("2015-11-15"));
        return document;
    }

    public static DocumentType createDocumentType() {
        DocumentType documentType = new DocumentType();
        documentType.setCode(123);
        documentType.setName("Межгалактический паспорт");
        return documentType;
    }

}
