package com.examsys.controllers;

import com.examsys.entity.User;
import com.examsys.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @Test
    @Order(1)
    @DisplayName("Test find all users")
    void findAll() throws Exception{

        List<User> userList = new ArrayList<>();

        User user1 = new User(1L, "floppy", "123", "egor", "user");
        User user2 = new User(2L, "werwer", "123", "dima", "admin");

        userList.add(user1);
        userList.add(user2);
        String expected = gson.toJson(userList);

        String actual = mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    @DisplayName("Test find one user")
    void findOne() throws Exception{
        User user = new User(1L, "floppy", "123", "egor", "user");

        String expected = gson.toJson(user);

        String actual = mvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    @DisplayName("Test create user")
    void createUser() throws Exception{

        User actual_user = new User("murlo", "123", "sonya", "user");

        String actual_json = gson.toJson(actual_user);
        String expected = "{\"error\":null,\"message\":\"SUCCESS\"}";

        String actual = mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual_json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    @DisplayName("Test authorize")
    void authorize() throws Exception{

        User user = new User("floppy", "123");

        String expected = "{\"error\":null,\"message\":\"1;user\"}";
        String actual_json = gson.toJson(user);

        String actual = mvc.perform(post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual_json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    @Order(5)
    @DisplayName("Test replace user")
    void replaceUser() throws Exception{
        User actual_user = new User("murlo", "123", "sonya", "admin");
        User expected_user = new User(8L, "murlo", "123", "sonya", "admin");

        String actual_json = gson.toJson(actual_user);
        String expected = gson.toJson(expected_user);

        String actual = mvc.perform(put("/users/8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual_json)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(expected);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    @DisplayName("Test delete user")
    void deleteUser() throws Exception{
        String actual = mvc.perform(delete("/users/3")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);

        System.out.println(actual);
        Assertions.assertNotNull(actual);
    }
}