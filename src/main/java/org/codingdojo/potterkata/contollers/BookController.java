package org.codingdojo.potterkata.contollers;

import org.codingdojo.potterkata.models.Book;
import org.codingdojo.potterkata.services.DefaultBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BookController {
    private final DefaultBookService bookService;

    public BookController(DefaultBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    List<Book> all() {
       return bookService.all();
    }

    @GetMapping("/book/{id}")
    Book find(@PathVariable String id) {
        return bookService.find(UUID.fromString(id));
    }
}
