package ru.atconsalting.librare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atconsalting.librare.model.Book;
import ru.atconsalting.librare.repository.BookRepository;

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
    public Collection<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Collection<Book> getBooksWithLimit(int limit, long offset) {
        return bookRepository.getBooksWithLimit(limit, offset);
    }


    @Override
    public Book saveBook(Book book) {
        return bookRepository.saveOrUpdateBook(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.saveOrUpdateBook(book);
    }


    @Override
    public boolean deleteBook(Long id) {
        return bookRepository.deleteBook(id);

    }


    @Override
    public boolean containsBook(Book book) {
        return bookRepository.getBook(book.getId()) != null;
    }


    @Override
    public boolean takeBook(String userName, Long id) {
        return bookRepository.takeBook(userName, id);
    }

    @Override
    public boolean revertBook(Long id) {
        return bookRepository.revertBool(id);
    }
}
