package org.romanzhula.bookstore.services.implementations;

import org.junit.jupiter.api.Test;
import org.romanzhula.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplIntegrationTest {

    @Autowired
    BookService bookService;

    @Test
    void getAllBooksSuccess() {
        //given

        //when
        var result = bookService.getBooks();

        //then
        assertEquals(4, result.size());
    }

}