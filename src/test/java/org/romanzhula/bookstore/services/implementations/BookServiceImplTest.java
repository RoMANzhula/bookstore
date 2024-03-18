package org.romanzhula.bookstore.services.implementations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.romanzhula.bookstore.models.Book;
import org.romanzhula.bookstore.repositories.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //changing real repositories and services
class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookServiceImpl;

    @Test
    void getBooksSuccess() {
        //given
        var book1 = new Book();
        book1.setId(1L);
        book1.setName("Book_1");

        var book2 = new Book();
        book2.setId(2L);
        book2.setName("Book_2");

        var bookList = List.of(book1, book2);

        //when
        when(bookRepository.findAll()).thenReturn(bookList);
        var result = bookServiceImpl.getBooks();

        //then
        assertEquals(2, result.size());
        assertEquals(1L, result.getFirst().getId());
        assertEquals("Book_2", result.get(1).getName());
    }

    @Test
    void getBooksSuccessCounter() {
        //given
        var book1 = new Book();
        book1.setId(1L);
        book1.setName("Book_1");

        var book2 = new Book();
        book2.setId(2L);
        book2.setName("Book_2");

        var bookList = List.of(book1, book2);

        //when
        when(bookRepository.findAll()).thenReturn(bookList);
        var result = bookServiceImpl.getBooks();
        verify(bookRepository, times(1)).findAll(); //how times called method findAll()

        //then

    }

}