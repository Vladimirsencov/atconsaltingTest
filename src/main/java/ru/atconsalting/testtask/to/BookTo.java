package ru.atconsalting.testtask.to;

import ru.atconsalting.testtask.model.BookStatus;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class BookTo {
    private final Long id;
    private final String ISBN;
    private final String title;
    private final String authorName;
    private final String status;

    public BookTo(Long id, String isbn, String title, String authorName, BookStatus status, String readerName) {
        this.id = id;
        ISBN = isbn;
        this.title = title;
        this.authorName = authorName;
        this.status = status.statusToString(readerName);
    }

    public Long getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getStatus() {
        return status;
    }

    public static class BookToBuilder {
        private Long id;
        private String ISBN;
        private String title;
        private String authorName;
        private BookStatus status;

        public BookToBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BookToBuilder setISBN(String ISBN) {
            this.ISBN = ISBN;
            return this;
        }

        public BookToBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookToBuilder setAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public BookToBuilder setStatus(BookStatus status) {
            this.status = status;
            return this;
        }

        public BookTo buildBookTo() {
            return new BookTo(id, ISBN, title, authorName, status, null);
        }
    }
}
