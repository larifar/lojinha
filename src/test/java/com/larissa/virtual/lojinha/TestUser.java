package com.larissa.virtual.lojinha;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.larissa.virtual.lojinha.controller.UserController;
import com.larissa.virtual.lojinha.enums.AddressType;
import com.larissa.virtual.lojinha.model.Address;
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

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
                "Usuario" + new Random().nextInt(1000),
                "usuario" + new Random().nextInt(1000) +"@gmail.com",
                Integer.toString(new Random().nextInt(1000000000)),
                new BCryptPasswordEncoder().encode("123")
        );
        Address address1 = new Address(
                "Rua Alameda Jorgeson",
                "123",
                "casa 12",
                "Bairro do zezinho",
                "SP",
                "JorgeTown",
                "12345123",
                AddressType.BILL
        );
        user.getAddresses().add(address1);
        ObjectMapper mapper = new ObjectMapper();
        ResultActions apiResult = mockMvc.perform(
                (MockMvcRequestBuilders.post("/saveUser")
                        .content(mapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)));

        User objectResponse = mapper
                .readValue(apiResult.andReturn().getResponse().getContentAsString(), User.class);
        assertNotNull(objectResponse);
        assertEquals(user.getName(), objectResponse.getName());

        assertEquals(user.getEmail(), objectResponse.getEmail());
        assertEquals(user.getCPF(), objectResponse.getCPF());
        assertNotNull(objectResponse.getAddresses());
        assertEquals(1, objectResponse.getAddresses().size());
    }
}
