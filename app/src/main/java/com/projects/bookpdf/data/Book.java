package com.projects.bookpdf.data;

import java.util.ArrayList;

public class Book {
    private int bookId;
    String bookName;
    String bookUrl;
    String bookImageURL;
    String bookDescription;
    String bookPage;
    int bookYear;
    String bookSize;
    String bookTotalDownload;

    public Book(int bookId, String bookName, String bookUrl, String bookImageURL, String bookDescription, String bookPage, int bookYear, String bookSize, String bookTotalDownload) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookUrl = bookUrl;
        this.bookImageURL = bookImageURL;
        this.bookDescription = bookDescription;
        this.bookPage = bookPage;
        this.bookYear = bookYear;
        this.bookSize = bookSize;
        this.bookTotalDownload = bookTotalDownload;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public String getBookImageURL() {
        return bookImageURL;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getBookPage() {
        return bookPage;
    }

    public int getBookYear() {
        return bookYear;
    }

    public String getBookSize() {
        return bookSize;
    }

    public String getBookTotalDownload() {
        return bookTotalDownload;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookUrl='" + bookUrl + '\'' +
                ", bookImageURL='" + bookImageURL + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookPage='" + bookPage + '\'' +
                ", bookYear=" + bookYear +
                ", bookSize='" + bookSize + '\'' +
                ", bookTotalDownload='" + bookTotalDownload + '\'' +
                '}';
    }
}
