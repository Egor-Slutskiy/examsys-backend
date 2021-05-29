package com.examsys.controllers;

import com.examsys.entity.Exam;
import com.examsys.entity.Question;
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
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Test
    @Order(1)
    @DisplayName("Test find all questions")
    public void findAll() throws Exception{

        List<Question> questionList = new ArrayList<>();

        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");

        Question question1 = new Question(7L, "11231", "?:da?:zxc?:?)", "?)", exam, 11L);
        Question question2 = new Question(8L, "Norm", "?:da?:zxc?:?)", "?)", exam, 11L);
        Question question3 = new Question(9L, "Norm", "?:da?:ss?:?)", "?)", exam, 11L);

        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);

        String expected = gson.toJson(questionList);

        String actual = mvc.perform(get("/question")
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
    @DisplayName("Test find by exam id")
    public void findByExam() throws Exception{
        List<Question> questionList = new ArrayList<>();

        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");

        Question question1 = new Question(7L, "11231", "?:da?:zxc?:?)", "?)", exam, 11L);
        Question question2 = new Question(8L, "Norm", "?:da?:zxc?:?)", "?)", exam, 11L);
        Question question3 = new Question(9L, "Norm", "?:da?:ss?:?)", "?)", exam, 11L);

        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);

        String expected = gson.toJson(questionList);

        String actual = mvc.perform(get("/question/find_by_exam/11")
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
    @DisplayName("Test find question by id")
    public void findOne() throws Exception{
        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        Question question = new Question(9L, "Norm", "?:da?:ss?:?)", "?)", exam, 11L);

        String expected = gson.toJson(question);

        String actual = mvc.perform(get("/question/9")
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
    @DisplayName("Test question create")
    public void questionCreate() throws Exception{
        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        Question question_exp = new Question(10L, "jtest", "?:da?:ss?:?)", "?)", exam, 11L);

        Question question = new Question(10L, "jtest", "?:da?:ss?:?)", "?)", 11L);

        String actual_question = gson.toJson(question);
        String expected = gson.toJson(question_exp);

        String actual = mvc.perform(post("/question")
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual_question)
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
    @DisplayName("Test replace question")
    public void replaceQuestion() throws Exception{
        Exam exam = new Exam(11L, "test", "2021-05-05 18:00:00", "2021-05-20 18:50:00");
        Question question_exp = new Question(10L, "jtest2", "?:da?:ss?:?)", "?)", exam, 11L);

        Question question = new Question(10L, "jtest2", "?:da?:ss?:?)", "?)", 11L);

        String actual_question = gson.toJson(question);
        String expected = gson.toJson(question_exp);

        String actual = mvc.perform(put("/question/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(actual_question)
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
    public void deleteQuestion() throws Exception{
        String actual = mvc.perform(delete("/question/10")
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