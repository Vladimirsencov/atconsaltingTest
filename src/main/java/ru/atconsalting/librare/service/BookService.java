package ru.atconsalting.librare.service;

import ru.atconsalting.librare.model.Book;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
public interface BookService {

    Collection<Book> getAllBooks();

    Collection<Book> getBooksWithLimit(int limit, long offset);

    Book saveBook(Book book);

    Book updateBook(Book book);

    boolean deleteBook(Long id);

    boolean containsBook(Book book);

    boolean takeBook(String userName, Long id);

    boolean revertBook(Long id);
}
