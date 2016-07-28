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

    boolean deleteBook(long id);

    boolean deleteBookByISBN(String ISBN);

    boolean containsBookByISBN(String ISBN);

    boolean containsBook(long id);

    public boolean setReader(User user, long bookId);
}
