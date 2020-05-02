package com.projects.bookpdf.data;

import java.util.ArrayList;

public class SearchBook {


    private ArrayList<Book> books=new ArrayList<Book>();
    private String searchUrl;
    private String searchQuery;
    private int totalPage;
    private int totalLoadedPage=1;

    public SearchBook(ArrayList<Book> books,String searchUrl, String searchQuery, int totalPage) {
        this.books = books;
        this.searchUrl = searchUrl;
        this.searchQuery = searchQuery;
        this.totalPage = totalPage;
    }
    public void setBooks(ArrayList<Book> books) {
        this.books.addAll(books);
    }
    public ArrayList<Book> getBooks() {
        return books;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalLoadedPage() {
        return totalLoadedPage;
    }
}
