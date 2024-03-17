package org.romanzhula.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloControllers {
    @ModelAttribute("greeting")
    public String greeting() {
        return "Hello dear friend, ";
    }

    @ModelAttribute("bookNames")
    public List<String> bookNames() {
        return List.of(
                "Book1",
                "Book2",
                "Book3",
                "Book4"
        );
    }

    @GetMapping("/")
    public String hello(
            @RequestParam String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/{name}")
    public String helloWithName(
            @PathVariable String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello";
    }

}
