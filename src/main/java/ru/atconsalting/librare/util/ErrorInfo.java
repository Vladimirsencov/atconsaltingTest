package ru.atconsalting.librare.util;

/**
 * Created by Vladimir_Sentso on 31.07.2016.
 */
public class ErrorInfo {
    public final String url;
    public final String cause;
    public final String detail;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getMessage();
    }
}
