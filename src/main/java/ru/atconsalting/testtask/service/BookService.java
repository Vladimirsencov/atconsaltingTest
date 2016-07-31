package ru.atconsalting.testtask.service;

import ru.atconsalting.testtask.model.Book;
import ru.atconsalting.testtask.model.User;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
public interface BookService {

    Collection<Book> getAllBooks(User user);

    Collection<Book> getBooksWithLimit(User user, int limit, long offset);

    Book saveBook(Book book);

    Book updateBook(Book book);

    boolean deleteBook(Book book);

    boolean containsBook(Book book);

    boolean takeBook(User user, Book book);

    boolean revertBook(Book book);
}
