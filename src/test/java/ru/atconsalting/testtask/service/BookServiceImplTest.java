package ru.atconsalting.testtask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.atconsalting.testtask.model.Book;

import javax.sql.DataSource;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)

public class BookServiceImplTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BookService bookService;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    public void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(5L);
        DataSource dataSource = jdbcTemplate.getDataSource();
        Thread.sleep(1000);

        Thread.sleep(1000);
        Assert.assertEquals("true", true, bookService.containsBook(book));
    }

    @Test
    public void testGetBooksWithLimit() throws Exception {

    }

    @Test
    public void testSaveBook() throws Exception {

    }

    @Test
    public void testUpdateBook() throws Exception {

    }

    @Test
    public void testDeleteBook() throws Exception {

    }

    @Test
    public void testContainsBook() throws Exception {

    }

    @Test
    public void testSetReader() throws Exception {

    }

}