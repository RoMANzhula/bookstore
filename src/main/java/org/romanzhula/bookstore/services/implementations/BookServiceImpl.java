package org.romanzhula.bookstore.services.implementations;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.romanzhula.bookstore.dto.BookDTO;
import org.romanzhula.bookstore.mappers.BookMapper;
import org.romanzhula.bookstore.repositories.BookRepository;
import org.romanzhula.bookstore.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookDTO> getBooks() {
        var books = IterableUtils.toList(bookRepository.findAll());
        return books.stream()
                .map(BookMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public Optional<BookDTO> getBook(Long id) {
        var book = bookRepository.findById(id);
        return book.map(BookMapper.INSTANCE::toDTO);
    }

    @Override
    public void save(BookDTO bookDTO) {
        bookRepository.save(BookMapper.INSTANCE.toModel(bookDTO));
    }

    @Override
    public void update(BookDTO bookDTO) {
        if (bookRepository.findById(bookDTO.getId()).isPresent()) {
            bookRepository.save(BookMapper.INSTANCE.toModel(bookDTO));
        }
    }

    @Override
    public void delete(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        }
    }

}
