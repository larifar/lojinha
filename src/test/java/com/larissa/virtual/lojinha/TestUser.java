package com.larissa.virtual.lojinha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.larissa.virtual.lojinha.controller.UserController;
import com.larissa.virtual.lojinha.model.User;
import com.larissa.virtual.lojinha.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LojinhaApplication.class)
public class TestUser {
    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;
    @Autowired
    private WebApplicationContext webContext;

    @Test
    public void testApiSaveUser() throws Exception {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webContext); //traz info do contexto pro mockito, fazem requisição para a api
        MockMvc mockMvc = builder.build();

        User user = new User(
                "Usuario",
                "usuario@gmail.com",
                "323261430886",
                new BCryptPasswordEncoder().encode("123")
        );
        ObjectMapper mapper = new ObjectMapper();
        ResultActions apiResult = mockMvc.perform(
                (MockMvcRequestBuilders.post("/saveUser")
                        .content(mapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)));

        User objectResponse = mapper
                .readValue(apiResult.andReturn().getResponse().getContentAsString(), User.class);
        assertEquals(user.getEmail(), objectResponse.getEmail());
    }
}
