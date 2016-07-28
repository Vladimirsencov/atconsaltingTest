package ru.atconsalting.testtask.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.atconsalting.testtask.model.Book;

import java.util.Collection;

/**
 * Created by Vladimir_Sentso on 28.07.2016.
 */
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public Collection<Book> getAllBooks() {
        return null;
    }

    @Override
    public Collection<Book> getBooksWithLimit(int limit, long offset) {
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
    public boolean deleteBook(String ISBN) {
        return false;
    }

    @Override
    public boolean setReader(String userName, long bookId) {
        return false;
    }
}
