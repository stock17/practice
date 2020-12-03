package ru.bellintegrator.practice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.glasnost.orika.MapperFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.bellintegrator.practice.aspect.DataResponseBodyAdvice;
import ru.bellintegrator.practice.aspect.GlobalExceptionHandler;
import ru.bellintegrator.practice.daoimpl.DaoUtils;
import ru.bellintegrator.practice.dao.DocumentDao;
import ru.bellintegrator.practice.dao.UserDao;
import ru.bellintegrator.practice.filter.UserRequestFilter;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    private static final String URL = "/api/user/";

    private final String SUCCESS = "{\"result\":\"success\"}";
    private final String ERROR_TEMPLATE = "\\{\"error\":.*\\}";
    private final String DATA_TEMPLATE  = "\\{\"data\":.*\\}";
    private final String LIST_TEMPLATE  = "\\{\"data\":\\[.*\\]\\}";

    private MockMvc mockMvc;

    @Autowired
    private UserController controller;

    @MockBean
    private UserDao userDao;

    @MockBean
    private DocumentDao documentDao;

    @Autowired
    private MapperFactory mapperFactory;


    @BeforeEach
    void setUp() {
         mockMvc = MockMvcBuilders.standaloneSetup(controller)
                 .setControllerAdvice(new GlobalExceptionHandler(), new DataResponseBodyAdvice())
                 .build();
    }

    @Test
    void whenPostSave_thenReturnSuccess() throws Exception {
        doNothing().when(userDao).save(any());
        doNothing().when(documentDao).save(any());

        UserSaveView view = createSaveView();
        view.setOfficeId(1);

        String body = convertViewToString(view);

        mockMvc.perform(post(URL + "save")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(body))
                .andExpect(content().string(SUCCESS));
    }

    @Test
    void givenDataNotValid_whenPostSave_thenReturnError() throws Exception {
        String badRequest = "This is a bad request, which doesn't contain any object's data";
        mockMvc.perform(post(URL + "save")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(badRequest))
                .andExpect(content().string(Matchers.matchesPattern(ERROR_TEMPLATE)));
    }

    @Test
    void whenGetId_whenReturnData() throws Exception {
        User user = DaoUtils.createUser();
        when(userDao.findById(user.getId())).thenReturn(user);
        when(documentDao.findById(user.getId())).thenReturn(DaoUtils.createDocument(user));

        mockMvc.perform(get(URL + user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.matchesPattern(DATA_TEMPLATE)))
                .andExpect(content().string(containsString(user.getFirstName())));
    }

    @Test
    void givenIdIsNotValid_whenGetId_whenReturnError() throws Exception {
        long badId = 999;
        when(userDao.findById(badId)).thenThrow(new PersistenceException("No such entity"));

        mockMvc.perform(get(URL + badId))
                .andExpect(content().string(Matchers.matchesPattern(ERROR_TEMPLATE)));
    }

    @Test
    void whenPostList_whenReturnData() throws Exception {
        User user= DaoUtils.createUser();
        UserRequestFilter filter = new UserRequestFilter();
        filter.setOfficeId(user.getOffice().getId());

        when(userDao.findByFilter(any())).thenReturn(List.of(user));

        mockMvc.perform(post(URL + "list")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(filter)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.matchesPattern(LIST_TEMPLATE)))
                .andExpect(content().string(containsString(user.getFirstName())));
    }

    @Test
    void whenPostUpdate_thenReturnSuccess() throws Exception {
        long id = 1;
        User user = DaoUtils.createUser();
        user.setId(id);
        when(userDao.findById(id)).thenReturn(user);
        when(documentDao.findById(id)).thenReturn(DaoUtils.createDocument(user));

        UserUpdateView view = createUpdateView();
        view.setId(id);

        mockMvc.perform(post(URL + "update")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(convertViewToString(view)))
                .andExpect(content().string(SUCCESS));
    }

    private String convertViewToString(Object view) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(view);
    }

    private UserView createView() {
        return mapperFactory.getMapperFacade().map(DaoUtils.createOffice(), UserView.class);
    }

    private UserSaveView createSaveView() {
        return mapperFactory.getMapperFacade().map(DaoUtils.createUser(), UserSaveView.class);
    }

    private UserUpdateView createUpdateView() {
        return mapperFactory.getMapperFacade().map(DaoUtils.createUser(), UserUpdateView.class);
    }
}