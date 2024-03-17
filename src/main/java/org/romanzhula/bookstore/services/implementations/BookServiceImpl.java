package org.romanzhula.bookstore.services.implementations;

import jakarta.annotation.PostConstruct;
import org.romanzhula.bookstore.dto.BookDTO;
import org.romanzhula.bookstore.services.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private List<BookDTO> bookDTOList = new ArrayList<>();

    @Override
    public List<BookDTO> getBooks() {
        return bookDTOList;
    }

    @Override
    public Optional<BookDTO> getBook(Long id) {
        return bookDTOList.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(BookDTO bookDTO) {
        if (getBook(bookDTO.getId()).isEmpty()) {
            bookDTOList.add(bookDTO);
        }
    }

    @Override
    public void update(BookDTO bookDTO) {
        bookDTOList = bookDTOList.stream()
                .map(existingBook -> existingBook
                        .getId().equals(bookDTO.getId()) ? bookDTO : existingBook
                )
                .toList();
    }

    @Override
    public void delete(Long id) {
        bookDTOList.removeIf(el -> el.getId().equals(id)); //remove only if this book was in list
    }

    @PostConstruct
    public void initBookDTOList() {
        bookDTOList.addAll(
                List.of(
                        BookDTO.builder()
                                .id(1L)
                                .name("Book_1")
                                .author("Author_1")
                                .description("Book_1 about a good weather.")
                                .price(277.44)
                                .build(),

                        BookDTO.builder()
                                .id(2L)
                                .name("Book_2")
                                .author("Author_2")
                                .description("Book_2 about great people.")
                                .price(214.74)
                                .build(),

                        BookDTO.builder()
                                .id(3L)
                                .name("Book_3")
                                .author("Author_3")
                                .description("Book_3 about a big wood.")
                                .price(177.57)
                                .build()
                )
        );
    }

}
