package ru.atconsalting.testtask.util.userUtil;

import ru.atconsalting.testtask.model.Book;
import ru.atconsalting.testtask.to.BookTo;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class BookUtil {

    public static Book createFromTo(BookTo bookTo) {
        Book book = new Book();
        book.setAuthorName(bookTo.getAuthorName());
        book.setISBN(bookTo.getISBN());
        book.setTitle(bookTo.getTitle());
        return book;
    }

    public static BookTo asTo(Book book) {
        return new BookTo.BookToBuilder()
                .setAuthorName(book.getAuthorName())
                .setId(book.getId())
                .setISBN(book.getISBN())
                .setStatus(book.getBookStatus())
                .setTitle(book.getTitle())
                .buildBookTo();
    }

    public static Book updateFromTo(Book book, BookTo to) {
        book.setAuthorName(to.getAuthorName());
        book.setISBN(to.getISBN());
        book.setTitle(to.getTitle());
        return book;
    }
}
