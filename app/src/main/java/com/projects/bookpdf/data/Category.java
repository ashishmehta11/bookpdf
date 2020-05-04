package com.projects.bookpdf.data;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private int categoryId;
    private String categoryTitle;

    public ArrayList<Book> getBooks() {
        return books;
    }

    public HashMap<Integer, Category> getSubCategory() {
        return subCategory;
    }

    public HashMap<Integer, String> getSubCategoryName() {
        return subCategoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public int getTotalLoadedPage() {
        return totalLoadedPage;
    }

    private String categoryUrl;
    private String categoryImageUrl;

    public Category(int categoryId, String categoryTitle, String categoryUrl, String categoryImageUrl, String totalPage, int totalLoadedPage) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryUrl = categoryUrl;
        this.categoryImageUrl = categoryImageUrl;
        this.totalPage = totalPage;
        this.totalLoadedPage = totalLoadedPage;
    }

    private String totalPage;
    private int totalLoadedPage = 1;
    private ArrayList<Book> books = new ArrayList<>();
    private HashMap<Integer, Category> subCategory = new HashMap<Integer, Category>();
    private HashMap<Integer, String> subCategoryName = new HashMap<Integer, String>();
}