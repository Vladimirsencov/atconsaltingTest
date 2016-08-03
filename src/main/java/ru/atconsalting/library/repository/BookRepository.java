package ru.atconsalting.library.repository;

import ru.atconsalting.library.model.Book;

import java.util.Collection;


/**
 * Created by Vladimir_Sentso on 28.07.2016.
 */
public interface BookRepository {

    Collection<Book> getAllBooks();

    Collection<Book> getBooksWithLimit(int limit, long offset);

    Book saveOrUpdateBook(Book book);

    boolean deleteBook(Long id);

    boolean deleteBookByISBN(String ISBN);

    Book getBook(long id);

    boolean takeBook(String userName, Long bookId);


    boolean revertBool(Long bookId);
}
