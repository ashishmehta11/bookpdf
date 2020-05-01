package com.projects.bookpdf.data;

import java.util.ArrayList;
import java.util.HashMap;

public class HomePageBook {

    private HashMap<String, ArrayList<Book>> books=new HashMap<String,ArrayList<Book>>();
    public static String url="https://www.pdfdrive.com";
    public HomePageBook(HashMap<String, ArrayList<Book>> books) {
        this.books = books;
    }

    public HashMap<String, ArrayList<Book>> getBooks() {
        return books;
    }

}
