package com.projects.bookpdf.data;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePageBook {

    private HashMap<String, ArrayList<Book>> books=new HashMap<String,ArrayList<Book>>();
    private String url="https://www.pdfdrive.com";
    public HomePageBook() {
    }
    public HomePageBook(HashMap<String, ArrayList<Book>> books) {
        this.books = books;
    }

    public HashMap<String, ArrayList<Book>> getBooks() {
        return books;
    }

    public String getUrl() {
        return url;
    }
}
