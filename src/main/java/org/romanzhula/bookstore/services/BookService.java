package org.romanzhula.bookstore.services;

import org.romanzhula.bookstore.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getBooks();
    Optional<BookDTO> getBook(Long id);

    void save(BookDTO bookDTO);
    void update(BookDTO bookDTO);
    void delete(Long id);
}

