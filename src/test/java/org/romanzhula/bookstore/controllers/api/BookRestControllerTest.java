package org.romanzhula.bookstore.controllers.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.romanzhula.bookstore.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetAllBooks() throws Exception {
        var mockResponse = mockMvc.perform(get("/api/book/all"))
                .andReturn()
                .getResponse();

        var books = objectMapper.readValue(mockResponse.getContentAsString(), new TypeReference<List<BookDTO>>() {
        });

        assertEquals(4, books.size());
        assertEquals(1, books.getFirst().getId());
    }

    @Test
    public void testCreateBook() throws Exception {
        var book = BookDTO.builder()
                .id(44L)
                .name("Book_44")
                .author("Author_44")
                .description("Book_44 is interesting.")
                .price(407.23)
                .build();

        var content = objectMapper.writeValueAsString(book); //convert object to JSON(String)

        var mockResponse = mockMvc
                .perform(post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        )
                .andReturn()
                .getResponse()
        ;

        assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
    }

    @Test
    public void testCreateBookNotValidByName() throws Exception {
        var book = BookDTO.builder()
                .id(44L)
                .name("TestBook_3")
                .author("Author_44")
                .description("Book_44 is interesting.")
                .price(407.23)
                .build();

        var content = objectMapper.writeValueAsString(book); //convert object to JSON(String)

        var mockResponse = mockMvc
                .perform(post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andReturn()
                .getResponse()
                ;

        assertEquals(HttpStatus.BAD_REQUEST.value(), mockResponse.getStatus());
        assertTrue(mockResponse.getContentAsString().contains("Book with this name existed yet!"));
    }

}