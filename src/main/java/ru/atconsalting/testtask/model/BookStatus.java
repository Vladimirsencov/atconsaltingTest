package ru.atconsalting.testtask.model;

/**
 * Created by Vladimir_Sentso on 29.07.2016.
 */
public enum BookStatus {
    NOT_USE {
        @Override
        public String statusToString(Book book) {
            return "isFree";
        }

        @Override
        public String statusToString(String string) {
            return "isFree";
        }
    }, USE_ANOTHER_READER {
        @Override
        public String statusToString(Book book) {
            return statusToString(book.getReaderName());
        }

        @Override
        public String statusToString(String string) {
            return null;
        }
    }, USE_CURRENT_READER {
        @Override
        public String statusToString(Book book) {
            return "canRevert";
        }

        @Override
        public String statusToString(String string) {
            return "canRevert";
        }
    };

    public abstract String statusToString(Book book);

    public abstract String statusToString(String string);
}
