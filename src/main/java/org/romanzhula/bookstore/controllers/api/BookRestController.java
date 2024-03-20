package org.romanzhula.bookstore.controllers.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.romanzhula.bookstore.dto.BookDTO;
import org.romanzhula.bookstore.services.BookService;
import org.romanzhula.bookstore.validations.BookCreateMarker;
import org.romanzhula.bookstore.validations.BookUpdateMarker;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Validated
@Slf4j //for programs logs
public class BookRestController {

    private final BookService bookService;

    @Async
    @GetMapping("/bookstore-name")
    public CompletableFuture<String> getBookstoreName() {
        log.info(Thread.currentThread().getName());
        return CompletableFuture.completedFuture("MyBookstore");
    }

    @Async
    @GetMapping("/bookstore-id")
    public CompletableFuture<Integer> getBookstoreId() {
        log.info(Thread.currentThread().getName());
        return CompletableFuture.completedFuture(1);
    }

    @Async
    @GetMapping("/async")
    public CompletableFuture<String> asyncDemoPresent() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }

            log.info("Future1");
            return "Future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }

            log.info("Future2");
            return "Future2";
        });

        CompletableFuture<String> combinedFuture = future1.thenCombine(future2, (res1, res2) -> {
            log.info("Combined");
            return "Result: " + res1 + " and " + res2;
        });

        return combinedFuture;

    }

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
