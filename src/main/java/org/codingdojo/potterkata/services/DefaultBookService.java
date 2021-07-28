package org.codingdojo.potterkata.services;
import org.codingdojo.potterkata.models.Book;
import org.codingdojo.potterkata.repositories.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultBookService implements BookService{
    private final BookRepository repository;

    public DefaultBookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> all() {
        return this.repository.findAll();
    }

    public Book find(UUID id) {
        Optional<Book> book = this.repository.findById(id);
        if (book.isEmpty()) {
            return null;
        }
        return book.get();
    }
}
