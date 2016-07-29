package ru.atconsalting.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atconsalting.testtask.model.Book;
import ru.atconsalting.testtask.model.BookStatus;
import ru.atconsalting.testtask.model.User;
import ru.atconsalting.testtask.repository.BookRepository;

import java.util.Collection;
import java.util.stream.Collectors;

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
        return bookRepository.getAllBooks().stream()
                .peek(book -> setBookStatus(user, book))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Book> getBooksWithLimit(User user, int limit, long offset) {
        return bookRepository.getBooksWithLimit(limit, offset).stream()
                .peek(book -> setBookStatus(user, book))
                .collect(Collectors.toList());

    }

    private void setBookStatus(User user, Book book) {
        if (book.getReaderName() == null) {
            book.setBookStatus(BookStatus.NOT_USE);
        } else if (book.getReaderName().equals(user.getUserName())) {
            book.setBookStatus(BookStatus.USE_CURRENT_READER);
        } else book.setBookStatus(BookStatus.USE_ANOTHER_READER);
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
    public boolean deleteBook(Book book) {
        boolean result = bookRepository.deleteBook(book.getId());
        return result || bookRepository.deleteBookByISBN(book.getISBN());
    }


    @Override
    public boolean containsBook(Book book) {
        return bookRepository.getBook(book.getId()) != null;
    }

    @Override
    public boolean setReader(User user, Book book) {
        return bookRepository.setReader(user.getUserName(), book.getId());
    }
}
