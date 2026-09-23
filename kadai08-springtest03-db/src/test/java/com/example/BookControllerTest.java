package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /books アクセス時、SQLで投入した3件の書籍一覧が取得できること")
    @Sql(scripts = "/sql/test_booklist_insert_books.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    void listBooks_ShouldReturnThreeBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", hasSize(3)))
                .andExpect(model().attribute("newBook", instanceOf(Book.class)))
                .andDo(result -> {
                    @SuppressWarnings("unchecked")
                    List<Book> books = (List<Book>) result.getModelAndView()
                            .getModel().get("books");

                    assertThat(books)
                            .extracting(Book::getTitle)
                            .containsExactly(
                                    "Java完全ガイド",
                                    "Spring Boot実践入門",
                                    "データベース設計の基本");
                });
    }
}
