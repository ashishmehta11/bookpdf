package com.projects.bookpdf.data;

import java.util.ArrayList;

public class Book {
    private int bookId;
    private String bookName;
    private String bookUrl;
    private String bookImageURL;
    private String bookDescription;
    private String bookPage;
    private String bookLanguage;
    private int bookYear;
    private String bookSize;
    private String bookTotalDownload;
    private String authors;
    private String downloadUrl;
    private boolean areDetailsFetched;

    public Book(int bookId, String bookName, String bookUrl, String bookImageURL, String bookDescription, String bookPage, int bookYear, String bookSize, String bookTotalDownload,String authors,String bookLanguage,String downloadUrl,boolean areDetailsFetched) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookUrl = bookUrl;
        this.bookImageURL = bookImageURL;
        this.bookDescription = bookDescription;
        this.bookPage = bookPage;
        this.bookYear = bookYear;
        this.bookSize = bookSize;
        this.bookTotalDownload = bookTotalDownload;
        this.authors=authors;
        this.bookLanguage=bookLanguage;
        this.downloadUrl=downloadUrl;
        this.areDetailsFetched=areDetailsFetched;
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

    public String getAuthors() {
        return authors;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public boolean isAreDetailsFetched() {
        return areDetailsFetched;
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
                ", authors='" + authors + '\'' +
                ", bookLanguage='" + bookLanguage + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", areDetailsFetched='" + areDetailsFetched + '\'' +
                '}';
    }
}
