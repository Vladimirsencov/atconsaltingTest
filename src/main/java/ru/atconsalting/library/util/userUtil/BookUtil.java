package ru.atconsalting.library.util.userUtil;

import ru.atconsalting.library.model.Book;
import ru.atconsalting.library.to.BookTo;

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

    public static BookTo asTo(String userName, Book book) {
        BookTo bookTo = new BookTo.BookToBuilder()
                .setAuthorName(book.getAuthorName())
                .setId(book.getId())
                .setISBN(book.getISBN())
                .setTitle(book.getTitle())
                .setReaderName(book.getReaderName())
                .buildBookTo();
        return setBookToStatus(bookTo, userName);
    }

    public static Book updateFromTo(Book book, BookTo to) {
        book.setId(to.getId());
        book.setAuthorName(to.getAuthorName());
        book.setISBN(to.getISBN());
        book.setTitle(to.getTitle());
        return book;
    }

    private static BookTo setBookToStatus(BookTo bookTo, String userName) {
        if (bookTo.getReaderName() != null && bookTo.getReaderName().equalsIgnoreCase(userName)) {
            bookTo.setStatus("usedLoggedUser");
        } else if (bookTo.getReaderName() != null && (!bookTo.getReaderName().equalsIgnoreCase(userName))) {
            bookTo.setStatus(bookTo.getReaderName());
        } else {
            bookTo.setStatus("isFree");
        }
        return bookTo;
    }

}