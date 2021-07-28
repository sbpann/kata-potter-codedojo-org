package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<Book> all();
    Book find(UUID id);
}
