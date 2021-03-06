package ru.atconsalting.library.service;

import ru.atconsalting.library.model.Book;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
public interface BookService {

    Collection<Book> getAllBooks();

    Collection<Book> getBooksWithLimit(int limit, long offset);

    Book saveBook(Book book);

    Book getBook(Long id);

    Book updateBook(Book book);

    boolean deleteBook(Long id);

    boolean containsBook(Book book);

    boolean takeBook(String userName, Long id);

    boolean revertBook(Long id);
}
