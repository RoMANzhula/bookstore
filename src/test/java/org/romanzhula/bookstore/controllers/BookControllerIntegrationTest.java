package org.romanzhula.bookstore.controllers;

import org.junit.jupiter.api.Test;
import org.romanzhula.bookstore.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testBookCreateIsNameNotValid() throws Exception {
        var book = BookDTO.builder()
                .name("")
                .author("Qwerty")
                .description("Some...")
                .price(123D)
                .build();

        mockMvc
                .perform(post("/book/create")
                        .flashAttr("bookDTO", book)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Books title can not be empty!"))) //must be without "'"(apostraph)
        ;
    }

    @Test
    void testBookCreateIsAuthorNotValid() throws Exception {
        var book = BookDTO.builder()
                .name("Qwerty")
                .author("")
                .description("Some...")
                .price(123D)
                .build();

        mockMvc
                .perform(post("/book/create")
                        .flashAttr("bookDTO", book)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Author can not be empty!")))
        ;
    }

    @Test
    void testBookCreateIsDescriptionNotValid() throws Exception {
        var book = BookDTO.builder()
                .name("Qwerty")
                .author("Some...")
                .description("")
                .price(123D)
                .build();

        mockMvc
                .perform(post("/book/create")
                        .flashAttr("bookDTO", book)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Books description can not be empty!"))) //without "'"
        ;
    }

    @Test
    void testBookCreateIsPriceNotValidNotNull() throws Exception {
        var book = BookDTO.builder()
                .name("Qwerty")
                .author("Some...")
                .description("Some...")
                .price(null)
                .build();

        mockMvc
                .perform(post("/book/create")
                        .flashAttr("bookDTO", book)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Price can not be empty!")))
        ;
    }

    @Test
    void testBookCreateIsPriceNotValidNotLess() throws Exception {
        var book = BookDTO.builder()
                .name("Qwerty")
                .author("Some...")
                .description("Some...")
                .price(25D)
                .build();

        mockMvc
                .perform(post("/book/create")
                        .flashAttr("bookDTO", book)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Price can not be less than 50.00 $")))
        ;
    }

    @Test
    void testBookCreateIsAllFieldsValid() throws Exception {
        var book = BookDTO.builder()
                .name("With_name")
                .author("Qwerty")
                .description("Some...")
                .price(123D)
                .build();

        mockMvc
                .perform(post("/book/create")
                        .flashAttr("bookDTO", book)
                )
                .andExpect(model().hasNoErrors())
        ;
    }

    @Test
    void testBookUpdateIsNameNotValid() throws Exception {
        var bookId = 1L;
        var updatedBook = new BookDTO();
        updatedBook.setId(bookId);
        updatedBook.setName("");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setDescription("Updated Description");
        updatedBook.setPrice(77.77);

        mockMvc
                .perform(post("/book/update/{id}", bookId)
                        .flashAttr("bookToUpdate", updatedBook)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Books title can not be empty!"))) //must be without "'"(apostraph)
        ;
    }

    @Test
    void testBookUpdateIsAuthorNotValid() throws Exception {
        var bookId = 1L;
        var updatedBook = new BookDTO();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Name");
        updatedBook.setAuthor("");
        updatedBook.setDescription("Updated Description");
        updatedBook.setPrice(77.77);

        mockMvc
                .perform(post("/book/update/{id}", bookId)
                        .flashAttr("bookToUpdate", updatedBook)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Author can not be empty!")))
        ;
    }

    @Test
    void testBookUpdateIsDescriptionNotValid() throws Exception {
        var bookId = 1L;
        var updatedBook = new BookDTO();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Name");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setDescription("");
        updatedBook.setPrice(77.77);

        mockMvc
                .perform(post("/book/update/{id}", bookId)
                        .flashAttr("bookToUpdate", updatedBook)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Books description can not be empty!"))) //without "'"
        ;
    }

    @Test
    void testBookUpdateIsPriceNotValidNotNull() throws Exception {
        var bookId = 1L;
        var updatedBook = new BookDTO();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Name");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setDescription("Updated Description");
        updatedBook.setPrice(null);

        mockMvc
                .perform(post("/book/update/{id}", bookId)
                        .flashAttr("bookToUpdate", updatedBook)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Price can not be empty!")))
        ;
    }

    @Test
    void testBookUpdateIsPriceNotValidNotLess() throws Exception {
        var bookId = 1L;
        var updatedBook = new BookDTO();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Name");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setDescription("Updated Description");
        updatedBook.setPrice(19.99);

        mockMvc
                .perform(post("/book/update/{id}", bookId)
                        .flashAttr("bookToUpdate", updatedBook)
                )
                .andExpect(model().hasErrors())
                .andExpect(content().string(containsString("Price can not be less than 50.00 $")))
        ;
    }



    @Test
    void testBookUpdateIsAllFieldsValid() throws Exception {
        var bookId = 1L;
        var updatedBook = new BookDTO();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Name");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setDescription("Updated Description");
        updatedBook.setPrice(99.99);

        mockMvc.perform(post("/book/update/{id}", bookId)
                        .flashAttr("bookToUpdate", updatedBook))
                .andExpect(model().hasNoErrors());
    }

}