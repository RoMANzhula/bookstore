package org.romanzhula.bookstore.controllers.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.romanzhula.bookstore.dto.BookDTO;
import org.romanzhula.bookstore.services.BookService;
import org.romanzhula.bookstore.validations.BookCreateMarker;
import org.romanzhula.bookstore.validations.BookUpdateMarker;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Validated
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getBooks() {
        var allBooks = bookService.getBooks();

        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(
            @PathVariable Long id
    ) {
        var book = bookService.getBook(id).orElseThrow();

        return ResponseEntity.ok(book);
    }

    @Validated({BookCreateMarker.class})
    @PostMapping("/create")
    public ResponseEntity<Void> createBook(
            @Valid
            @RequestBody BookDTO bookDTO
    ) {
        bookService.save(bookDTO);

        return ResponseEntity.ok().build();
    }

    @Validated({BookUpdateMarker.class})
    @PutMapping("/update")
    public ResponseEntity<Void> updateBook(
            @PathVariable Long id,
            @Valid
            @RequestBody BookDTO bookDTO
    ) {
        bookDTO.setId(id);
        bookService.update(bookDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long id
    ) {
        bookService.delete(id);

        return ResponseEntity.ok().build();
    }

}
