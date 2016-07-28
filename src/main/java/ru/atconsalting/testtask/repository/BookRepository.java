package ru.atconsalting.testtask.repository;

import ru.atconsalting.testtask.model.Book;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 28.07.2016.
 */
public interface BookRepository {

    Collection<Book> getAllBooks();

    Collection<Book> getBooksWithLimit(int limit, long offset);

    Book saveBook(Book book);

    boolean deleteBook(long id);

    boolean deleteBook(String ISBN);

    boolean setReader(String userName, long bookId);
}
