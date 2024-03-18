package org.romanzhula.bookstore.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.romanzhula.bookstore.dto.BookDTO;
import org.romanzhula.bookstore.services.BookService;
import org.romanzhula.bookstore.validations.BookCreateMarker;
import org.romanzhula.bookstore.validations.BookUpdateMarker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Validated
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

    @Validated({BookCreateMarker.class})
    @PostMapping("/create")
    public String createBook(
            @ModelAttribute("bookDTO")
            @Valid BookDTO bookDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "createBook";
        }

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

    @Validated({BookUpdateMarker.class})
    @PostMapping("/update/{id}")
    public String updateBook(
            @PathVariable Long id,
            @ModelAttribute("bookToUpdate")
            @Valid BookDTO bookDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "updateBook";
        }

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
