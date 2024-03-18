package org.romanzhula.bookstore.repositories;

import org.junit.jupiter.api.Test;
import org.romanzhula.bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testSave() {
        //given
        var book = new Book();
        book.setId(5L);
        book.setName("TestBook_5");

        //when
        var savedBook = bookRepository.save(book);
        var retrievedBook = bookRepository.findById(savedBook.getId());

        //then
        assertTrue(retrievedBook.isPresent());
    }

    @Test
    void testSaveCheckName() {
        //given
        var book = new Book();
        book.setId(5L);
        book.setName("TestBook_5");

        //when
        var savedBook = bookRepository.save(book);
        var retrievedBook = bookRepository.findById(savedBook.getId());

        //then
        assertTrue(retrievedBook.isPresent());
        assertEquals("TestBook_5", retrievedBook.get().getName());
    }

}