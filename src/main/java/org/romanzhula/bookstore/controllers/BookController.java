package org.romanzhula.bookstore.controllers;

import lombok.RequiredArgsConstructor;
import org.romanzhula.bookstore.dto.BookDTO;
import org.romanzhula.bookstore.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/all")
    public String getBooks(
            Model model
    ) {
        var books = bookService.getBooks();
        model.addAttribute("books", books);

        return "books";
    }

    @GetMapping("/{id}")
    public String getBook(
            @PathVariable Long id,
            Model model
    ) {
        var book = bookService.getBook(id).orElseThrow();
        model.addAttribute("book", book);

        return "book";
    }

    @GetMapping("/create")
    public String showCreateBookForm() {
        return "createBook";
    }

    @PostMapping("/create")
    public String createBook(
            BookDTO bookDTO
    ) {
        bookService.save(bookDTO);

        return "redirect:/book/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBookForm(
            @PathVariable Long id,
            Model model
    ) {
        var book = bookService.getBook(id).orElseThrow();
        model.addAttribute("bookToUpdate", book);

        return "updateBook";
    }

    @PostMapping("/update/{id}")
    public String updateBook(
            @PathVariable Long id,
            BookDTO bookDTO
    ) {
        bookDTO.setId(id);
        bookService.update(bookDTO);

        return "redirect:/book/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(
            @PathVariable Long id
    ) {
        bookService.delete(id);

        return "redirect:/book/all";
    }

    @ModelAttribute("bookDTO")
    public BookDTO bookDTO() {
        return new BookDTO();
    }
}
