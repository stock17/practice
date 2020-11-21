package ru.bellintegrator.practice.service;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.daointerface.CountryDao;
import ru.bellintegrator.practice.daointerface.DocumentDao;
import ru.bellintegrator.practice.daointerface.DocumentTypeDao;
import ru.bellintegrator.practice.daointerface.OfficeDao;
import ru.bellintegrator.practice.daointerface.UserDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Document;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.serviceinterface.UserService;
import ru.bellintegrator.practice.view.user.UserListRequestView;
import ru.bellintegrator.practice.view.user.UserListResponseView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

import java.util.List;
import java.util.Objects;

/**
 * Реализация интерфейса {@link ru.bellintegrator.practice.serviceinterface.OfficeService}
 * с использованием библиотеки {@link ma.glasnost.orika}
 */
@Service
public class UserServiceImpl implements UserService {

    private final MapperFactory mapperFactory;
    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final DocumentTypeDao documentTypeDao;
    private final CountryDao countryDao;
    private final DocumentDao documentDao;

    @Autowired
    public UserServiceImpl(MapperFactory mapperFactory, UserDao userDao, OfficeDao officeDao,
                           DocumentTypeDao documentTypeDao, CountryDao countryDao, DocumentDao documentDao) {
        this.mapperFactory = mapperFactory;
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.documentTypeDao = documentTypeDao;
        this.countryDao = countryDao;
        this.documentDao = documentDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UserSaveView userSaveView) {
        Objects.requireNonNull(userSaveView);
        User user = mapperFactory.getMapperFacade().map(userSaveView, User.class);
        setOffice(userSaveView.getOfficeId(), user);
        setCitizenship(userSaveView.getCitizenshipCode(), user);
        Document document = createDocument(userSaveView, user);
        document.setUser(user);
        userDao.save(user);
        documentDao.save(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserView findById(long id) {
        User user = userDao.findById(id);
        Document document = documentDao.findById(id);
        UserView userView = mapperFactory.getMapperFacade().map(user, UserView.class);
        mapperFactory.getMapperFacade().map(document, userView);
        return userView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserListResponseView> findByOfficeId(UserListRequestView requestView) {
        Objects.requireNonNull(requestView);
        List<User> users = userDao.findByOfficeId(requestView.getOfficeId(),
                requestView.getFirstName(),
                requestView.getMiddleName(),
                requestView.getSecondName(),
                requestView.getPosition(),
                requestView.getDocCode(),
                requestView.getCitizenshipCode());

        return mapperFactory.getMapperFacade().mapAsList(users, UserListResponseView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserUpdateView updateView) {
        Objects.requireNonNull(updateView);

        User user = userDao.findById(updateView.getId());
        mapperFactory.getMapperFacade().map(updateView, user);
        setOffice(updateView.getOfficeId(), user);
        setCitizenship(updateView.getCitizenshipCode(), user);
        userDao.update(user);

        Document document = documentDao.findById(updateView.getId());
        mapperFactory.getMapperFacade().map(updateView, document);
        setDocumentType(updateView.getDocCode(), document);
        documentDao.update(document);
    }

    private void setOffice(Integer officeId, User user) {
        if (Objects.isNull(officeId) || Objects.isNull(user)) {
            return;
        }
        Office office = officeDao.findById(officeId);
        user.setOffice(office);
    }

    private void setCitizenship(Integer citizenshipCode, User user) {
        if (Objects.isNull(citizenshipCode) || Objects.isNull(user)) {
            return;
        }
        Country citizenship = countryDao.findByCode(citizenshipCode);
        user.setCitizenship(citizenship);
    }

    private void setDocumentType (Integer code, Document document) {
        if (Objects.isNull(code) || Objects.isNull(document)) {
            return;
        }
        DocumentType type = documentTypeDao.findByCode(code);
        document.setDocumentType(type);
    }

    private Document createDocument(UserSaveView userSaveView, User user) {
        Document document = mapperFactory.getMapperFacade().map(userSaveView, Document.class);
        Integer docType = userSaveView.getDocCode();
        setDocumentType(docType, document);
        return document;
    }
}
