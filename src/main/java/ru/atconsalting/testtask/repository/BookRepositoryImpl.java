package ru.atconsalting.testtask.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.atconsalting.testtask.LoggedUser;
import ru.atconsalting.testtask.model.Book;
import ru.atconsalting.testtask.model.BookStatus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir_Sentso on 28.07.2016.
 */
@Repository
@Transactional(readOnly = true)
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;


    private final RowMapper<Book> bookMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setAuthorName(rs.getString("Author_Name"));
        book.setISBN(rs.getString("ISBN"));
        book.setTitle(rs.getString("Title"));
        book.setReaderName(rs.getString("Reader_Name"));
        BookStatus bookStatus = null;
        if (book.getReaderName() == null) bookStatus = BookStatus.NOT_USE;
        else if (book.getReaderName().equalsIgnoreCase(LoggedUser.get().getUsername())) {
            bookStatus = BookStatus.USE_CURRENT_READER;
        } else bookStatus = BookStatus.USE_ANOTHER_READER;
        book.setBookStatus(bookStatus);
        return book;
    };

    @Autowired
    public BookRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate, SimpleJdbcInsert jdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedJdbcTemplate;
        this.jdbcInsert = jdbcInsert;
    }

    @Override
    public Collection<Book> getAllBooks() {
        String query = "SELECT * FROM BOOKS";
        return jdbcTemplate.query(query, bookMapper);
    }

    @Override
    public Collection<Book> getBooksWithLimit(int limit, long offset) {
        String query = "SELECT * FROM BOOKS LIMIT :limit OFFSET :offset";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("limit", (long) limit);
        namedParameters.addValue("offset", offset);
        return namedParameterJdbcTemplate.query(query, namedParameters, bookMapper);
    }

    @Override
    @Transactional
    public Book saveOrUpdateBook(Book book) {
        return book.getId() == null ? saveBook(book) : updateBook(book);
    }

    private Book saveBook(Book book) {

        jdbcInsert.withTableName("BOOKS")
                .usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reader_name", book.getReaderName());
        parameters.put("isbn", book.getISBN());
        parameters.put("title", book.getTitle());
        parameters.put("author_name", book.getAuthorName());

        Number key = jdbcInsert.executeAndReturnKey(parameters);
        book.setId(key.longValue());

        return book;
    }

    private Book updateBook(Book book) {
        String query = "UPDATE BOOKS SET READER_NAME = ?,ISBN = ?,TITLE = ?, AUTHOR_NAME = ? WHERE ID = ?";
        jdbcTemplate.update(query, book.getReaderName(), book.getISBN(), book.getTitle(), book.getAuthorName(), book.getId());
        return book;
    }

    @Override
    @Transactional
    public boolean deleteBook(Long id) {
        String query = "DELETE FROM BOOKS WHERE ID = ?";
        return jdbcTemplate.update(query, id) > 0;
    }

    @Override
    @Transactional
    public boolean deleteBookByISBN(String ISBN) {
        String query = "DELETE FROM BOOKS WHERE ISBN = ?";
        return jdbcTemplate.update(query, ISBN) > 0;
    }

    @Override
    public Book getBook(long id) {
        String query = "SELECT * FROM BOOKS WHERE ID = ? ";
        return jdbcTemplate.queryForObject(query, bookMapper, id);
    }

    @Override
    @Transactional
    public boolean takeBook(String userName, Long bookId) {
        String query = "UPDATE BOOKS SET READER_NAME = ? WHERE ID = ?";
        return jdbcTemplate.update(query, userName, bookId) > 0;
    }

    @Override
    @Transactional
    public boolean revertBool(Long bookId) {
        String query = "UPDATE BOOKS SET READER_NAME = NULL WHERE ID = ?";
        return jdbcTemplate.update(query, bookId) > 0;
    }
}
