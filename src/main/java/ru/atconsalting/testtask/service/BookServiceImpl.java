package ru.atconsalting.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atconsalting.testtask.model.Book;
import ru.atconsalting.testtask.model.User;
import ru.atconsalting.testtask.repository.BookRepository;

import java.util.Collection;

/**
 * Created by Vladimir_Sentsov on 29.07.2016.
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Collection<Book> getAllBooks(User user) {
        return null;
    }

    @Override
    public Collection<Book> getBooksWithLimit(User user, int limit, long offset) {
        return null;
    }

    @Override
    public Book saveBook(Book book) {
        return null;
    }

    @Override
    public boolean deleteBook(long id) {
        return false;
    }

    @Override
    public boolean deleteBookByISBN(String ISBN) {
        return false;
    }

    @Override
    public boolean containsBookByISBN(String ISBN) {
        return false;
    }

    @Override
    public boolean containsBook(long id) {
        return false;
    }

    @Override
    public boolean setReader(User user, long bookId) {
        return false;
    }
}
