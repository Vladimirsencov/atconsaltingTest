package model;

import java.util.Objects;

/**
 * Created by Vladimir_Sentso on 27.07.2016.
 */
public class Book {

    private String ISBN;
    private String title;
    private String authorName;
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("ISBN='").append(ISBN).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getISBN(), book.getISBN()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthorName(), book.getAuthorName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getISBN(), getTitle(), getAuthorName());
    }
}
