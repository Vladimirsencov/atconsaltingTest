package ru.atconsalting.library.to;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class BookTo {
    private Long id;
    private String ISBN;
    private String title;
    private String authorName;
    private String readerName;
    private String status;

    public BookTo() {
    }

    public BookTo(Long id, String isbn, String title, String authorName, String readerName) {
        this.id = id;
        ISBN = isbn;
        this.title = title;
        this.authorName = authorName;
        this.readerName = readerName;
    }

    public String getReaderName() {
        return readerName;
    }

    public BookTo setReaderName(String readerName) {
        this.readerName = readerName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BookTo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getISBN() {
        return ISBN;
    }

    public BookTo setISBN(String ISBN) {
        this.ISBN = ISBN;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookTo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookTo setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public BookTo setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookTo{");
        sb.append("id=").append(id);
        sb.append(", ISBN='").append(ISBN).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class BookToBuilder {
        private Long id;
        private String ISBN;
        private String title;
        private String authorName;
        private String readerName;

        public BookToBuilder setReaderName(String readerName1) {
            this.readerName = readerName1;
            return this;
        }


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

        public BookTo buildBookTo() {
            return new BookTo(id, ISBN, title, authorName, readerName);
        }
    }
}
