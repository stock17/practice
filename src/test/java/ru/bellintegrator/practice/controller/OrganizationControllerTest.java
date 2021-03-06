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
import ru.bellintegrator.practice.exception.GlobalExceptionHandler;
import ru.bellintegrator.practice.exception.NoSuchIdException;
import ru.bellintegrator.practice.daoimpl.DaoUtils;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.filter.OrganizationRequestFilter;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrganizationControllerTest {

    private static final String URL = "/api/organization/";

    private final String SUCCESS = "{\"result\":\"success\"}";
    private final String ERROR_TEMPLATE = "\\{\"error\":.*\\}";
    private final String DATA_TEMPLATE  = "\\{\"data\":.*\\}";
    private final String LIST_TEMPLATE  = "\\{\"data\":\\[.*\\]\\}";

    private MockMvc mockMvc;

    @Autowired
    private OrganizationController controller;

    @MockBean
    private OrganizationDao mockDao;

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
        mockMvc.perform(post(URL + "save")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(convertViewToString(createView())))
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
        Organization organization = DaoUtils.createOrganization();
        when(mockDao.findById(organization.getId())).thenReturn(organization);

        mockMvc.perform(get(URL + organization.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.matchesPattern(DATA_TEMPLATE)))
                .andExpect(content().string(containsString(organization.getName())));
    }

    @Test
    void givenIdIsNotValid_whenGetId_whenReturnError() throws Exception {
        long badId = 999;
        when(mockDao.findById(badId)).thenThrow(new NoSuchIdException("No such entity"));

        mockMvc.perform(get(URL + badId))
                .andDo(print())
                .andExpect(content().string(Matchers.matchesPattern(ERROR_TEMPLATE)));
    }

    @Test
    void whenPostList_whenReturnData() throws Exception {
        Organization organization = DaoUtils.createOrganization();
        OrganizationRequestFilter filter = new OrganizationRequestFilter();
        filter.setName(organization.getName());
        when(mockDao.findByFilter(any())).thenReturn(List.of(organization));

        mockMvc.perform(post(URL + "list")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(filter)))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.matchesPattern(LIST_TEMPLATE)))
                .andExpect(content().string(containsString(organization.getName())));
    }

    @Test
    void whenPostUpdate_thenReturnSuccess() throws Exception {

        Organization organization = DaoUtils.createOrganization();
        when(mockDao.findById(organization.getId())).thenReturn(organization);

        OrganizationView view = createView();
        view.setId(organization.getId());

        mockMvc.perform(post(URL + "update")
                .characterEncoding("UTF-8")
                .contentType("application/json")
                .content(convertViewToString(view)))
                .andExpect(content().string(SUCCESS));
    }

    private String convertViewToString(OrganizationView view) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(view);
    }

    private OrganizationView createView() {
        return mapperFactory.getMapperFacade().map(DaoUtils.createOrganization(), OrganizationView.class);

    }
}