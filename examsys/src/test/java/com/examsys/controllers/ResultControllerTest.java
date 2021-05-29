package com.examsys.controllers;

import com.examsys.entity.Exam;
import com.examsys.entity.Response;
import com.examsys.entity.Result;
import com.examsys.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
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
class ResultControllerTest {

    @Autowired
    private MockMvc mvc;

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @Test
    @Order(1)
    @DisplayName("Test find all results")
    void findAll() throws Exception{
        List<Result> resultList = new ArrayList<>();

        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        User user = new User(1L, "floppy", "123", "egor", "user");

        Result result1 = new Result(3L, exam, 11L, user, 1L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");
        Result result2 = new Result(4L, exam, 11L, user, 1L, "[?):correct, ?):correct, ?):correct], 3/3");
        Result result3 = new Result(5L, exam, 11L, user, 1L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");

        resultList.add(result1);
        resultList.add(result2);
        resultList.add(result3);

        String expected = gson.toJson(resultList);

        String actual = mvc.perform(get("/result")
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
    @DisplayName("Test find by id")
    void findOne() throws Exception{
        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        User user = new User(1L, "floppy", "123", "egor", "user");

        Result result = new Result(3L, exam, 11L, user, 1L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");

        String expected = gson.toJson(result);

        String actual = mvc.perform(get("/result/3")
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
    @DisplayName("Test find by user id")
    void findByUser() throws Exception{

        List<Result> resultList = new ArrayList<>();

        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        User user = new User(1L, "floppy", "123", "egor", "user");

        Result result1 = new Result(3L, exam, 11L, user, 1L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");
        Result result2 = new Result(4L, exam, 11L, user, 1L, "[?):correct, ?):correct, ?):correct], 3/3");
        Result result3 = new Result(5L, exam, 11L, user, 1L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");

        resultList.add(result1);
        resultList.add(result2);
        resultList.add(result3);

        String expected = gson.toJson(resultList);

        String actual = mvc.perform(get("/result/user/1")
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
    @Order(4)
    @DisplayName("Test find by exam id")
    void findByExam() throws Exception {
        List<Result> resultList = new ArrayList<>();

        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        User user = new User(1L, "floppy", "123", "egor", "user");

        Result result1 = new Result(3L, exam, 11L, user, 1L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");
        Result result2 = new Result(4L, exam, 11L, user, 1L, "[?):correct, ?):correct, ?):correct], 3/3");
        Result result3 = new Result(5L, exam, 11L, user, 1L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");

        resultList.add(result1);
        resultList.add(result2);
        resultList.add(result3);

        String expected = gson.toJson(resultList);

        String actual = mvc.perform(get("/result/exam/11")
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
    @Order(4)
    @DisplayName("Test create result")
    void createResult() throws Exception{
        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        User user = new User(2L, "werwer", "123", "dima", "admin");

        Result result = new Result(9L, exam, 11L, user, 2L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");
        Result result1 = new Result(11L, 2L, "[zxc?:wrong, ?):correct, ?):correct], 2/3");

        String actual_result = gson.toJson(result1);
        String expected = gson.toJson(result);

        String actual = mvc.perform(post("/result")
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual_result)
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
    @Order(5)
    @DisplayName("Test replace result")
    void replaceResult() throws Exception{

        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        User user = new User(2L, "werwer", "123", "dima", "admin");

        Result result = new Result(8L, exam, 11L, user, 2L, "[?):correct, ?):correct, ?):correct], 3/3");
        Result result1 = new Result(11L, 2L, "[?):correct, ?):correct, ?):correct], 3/3");

        String actual_result = gson.toJson(result1);
        String expected = gson.toJson(result);

        String actual = mvc.perform(put("/result/8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual_result)
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
    @DisplayName("Test delete question")
    void deleteResult() throws Exception{
        String actual = mvc.perform(delete("/result/8")
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