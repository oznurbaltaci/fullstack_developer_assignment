package com.linktera.linkteraquiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.request.BookRequest;
import com.linktera.linkteraquiz.service.BookService;
import com.linktera.linkteraquiz.web.controller.BookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.Paths.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@ActiveProfiles("test")
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Book> bookList;

    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        this.bookList.add(new Book(UUID.randomUUID(), "Ask ve Gurur", "Jane Austen",2));
        this.bookList.add(new Book(UUID.randomUUID(), "Mai ve Siyah", "Halit Ziya",0));
        this.bookList.add(new Book(UUID.randomUUID(), "Icimizdeki Seytan", "Sabahattin Ali",4));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void shouldFetchAllBooks() throws Exception {

        given(bookService.getList()).willReturn(bookList);


        this.mockMvc.perform((RequestBuilder) get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookList.size())));
    }

}
