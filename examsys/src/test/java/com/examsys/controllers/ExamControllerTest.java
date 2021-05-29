package com.examsys.controllers;

import com.examsys.entity.Exam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class ExamControllerTest {

    @Autowired
    private MockMvc mvc;

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Test
    @Order(1)
    @DisplayName("Test find all exams")
    void findAll() throws Exception{
        List<Exam> examList = new ArrayList<>();

        Exam exam1 = new Exam(6L, "Вычислительные системы", "2021-05-05 18:00:00", "2021-05-10 18:00:00");
        Exam exam2 = new Exam(7L, "Политические понятия", "2021-05-05 18:00:00", "2021-05-10 18:00:00");
        Exam exam3 = new Exam(8L, "Изобразительные искусства", "2021-05-05 18:00:00", "2021-05-10 18:00:00");
        Exam exam4 = new Exam(9L, "Географическое положение Российской Империи 19 века", "2021-05-05 18:00:00", "2021-05-05 18:30:00");
        Exam exam5 = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");

        examList.add(exam1);
        examList.add(exam2);
        examList.add(exam3);
        examList.add(exam4);
        examList.add(exam5);

        String expected = gson.toJson(examList);

        String actual = mvc.perform(get("/exam")
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
    @DisplayName("Test find one exam by id")
    void findOne() throws Exception {

        Exam exam = new Exam(11L, "test","2021-05-05 18:00:00", "2021-05-20 18:50:00");

        String expected = gson.toJson(exam);

        String actual = mvc.perform(get("/exam/11")
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
    @DisplayName("Test create exam")
    void createExam() throws Exception{

        Exam expected_exam = new Exam(22L,"test2","2021-05-05 18:00:00", "2021-05-20 18:50:00");
        Exam exam = new Exam( "test2","2021-05-05 18:00:00", "2021-05-20 18:50:00");

        String actual_exam = gson.toJson(exam);
        String expected = gson.toJson(expected_exam);

        String actual = mvc.perform(post("/exam/")
                .content(actual_exam)
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
    @DisplayName("Test replace exam")
    void replaceExam() throws Exception{
        Exam expected_exam = new Exam(20L,"replace_test","2021-05-05 18:00:00", "2021-05-20 18:50:00");
        Exam exam = new Exam( "replace_test","2021-05-05 18:00:00", "2021-05-20 18:50:00");

        String actual_exam = gson.toJson(exam);
        String expected = gson.toJson(expected_exam);

        String actual = mvc.perform(put("/exam/20")
                .content(actual_exam)
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
    @Order(5)
    @DisplayName("Test delete exam")
    void deleteExam() throws Exception{
        String actual = mvc.perform(delete("/exam/10")
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