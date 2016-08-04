package ru.atconsalting.library.web.ajaxcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.atconsalting.library.model.Book;
import ru.atconsalting.library.service.BookService;
import ru.atconsalting.library.to.BookTo;
import ru.atconsalting.library.util.userUtil.BookUtil;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
@RestController
@RequestMapping(value = "/ajax/books")
public class AjaxBookController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AjaxBookController.class);
    @Autowired
    private BookService service;

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookTo> getAllBooks(@PathVariable("userName") String userName) {
        LOGGER.info("Get all books");
        return service.getAllBooks().stream()
                .map(book -> BookUtil.asTo(userName, book))
                .collect(toList());
    }

    @RequestMapping(value = "/{userName}/{limit}/{offset}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookTo> getAllBooksWithLimit(@PathVariable("userName") String userName,
                                             @PathVariable("limit") Integer limit,
                                             @PathVariable("offset") Long offset) {
        LOGGER.info(String.format("Get book for user %s with limit %d with offset %d", userName, limit, offset));
        return service.getBooksWithLimit(limit, offset).stream()
                .map(book -> BookUtil.asTo(userName, book))
                .collect(toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") Long id) {
        LOGGER.info("Delete books with" + id);
        service.deleteBook(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void revertBook(@PathVariable("id") Long id) {
        LOGGER.info("revert books with" + id);
        service.revertBook(id);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@PathVariable("id") Long id) {
        LOGGER.info("get Book with " + id);
        return service.getBook(id);
    }

    @RequestMapping(value = "/{id}/{userName}", method = RequestMethod.PUT)
    public void takeBook(@PathVariable("userName") String userName, @PathVariable("id") Long id) {
        LOGGER.info("Take  book" + id);
        service.takeBook(userName, id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveOrUpdateBook(BookTo bookTo) {
        LOGGER.info("Create or update  book" + bookTo);
        try {
            if (bookTo.getId() == null || bookTo.getId() == 0L) {
                service.saveBook(BookUtil.createFromTo(bookTo));
            } else {
                service.updateBook(BookUtil.updateFromTo(new Book(), bookTo));
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("exception.duplicate ISBN");
        }
    }
}
