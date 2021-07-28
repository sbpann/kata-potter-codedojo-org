package org.codingdojo.potterkata.seeds;
import org.codingdojo.potterkata.models.Book;
import org.codingdojo.potterkata.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookSeed {
    private static final Logger log = LoggerFactory.getLogger(BookSeed.class);
    private static BookRepository bookRepository;

    public BookSeed(BookRepository bookRepository) {
        BookSeed.bookRepository = bookRepository;
    }

    public static void seed() {
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setName("Potter#"+(i+1));
            book.setPrice(8.);
            log.info("Preloading " + bookRepository.save(book));
        }
    }
}
