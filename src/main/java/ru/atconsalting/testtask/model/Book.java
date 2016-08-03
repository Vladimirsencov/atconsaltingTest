package ru.atconsalting.testtask.model;

import java.util.Objects;

/**
 * Created by Vladimir_Sentso on 27.07.2016.
 */
public class Book {

    private Long id;
    private String ISBN;
    private String title;
    private String authorName;
    private String readerName;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", ISBN='").append(ISBN).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", readerName='").append(readerName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(ISBN, book.ISBN) &&
                Objects.equals(title, book.title) &&
                Objects.equals(authorName, book.authorName) &&
                Objects.equals(readerName, book.readerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ISBN, title, authorName, readerName);
    }
}
