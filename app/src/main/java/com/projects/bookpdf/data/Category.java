package com.projects.bookpdf.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Category {
    private int categoryId;
    private String categoryTitle;

    public ArrayList<Book> getBooks() {
        return books;
    }

    public LinkedHashMap<String, Category> getSubCategory() {
        return subCategory;
    }

    public LinkedHashMap<String, String> getSubCategoryName() {
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

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalLoadedPage() {
        return totalLoadedPage;
    }

    private String categoryUrl;
    private String categoryImageUrl;

    public Category(int categoryId, String categoryTitle, String categoryUrl, String categoryImageUrl, int totalPage, int totalLoadedPage) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryUrl = categoryUrl;
        this.categoryImageUrl = categoryImageUrl;
        this.totalPage = totalPage;
        this.totalLoadedPage = totalLoadedPage;
    }

    private int totalPage;
    private int totalLoadedPage = 1;
    //TODO: books Array List will contain all the books appearing under this particular category/sub category
    private ArrayList<Book> books = new ArrayList<>();
    //TODO: subCategory hashMap will contain sub category name and sub category data as a Category object
    private LinkedHashMap<String, Category> subCategory = new LinkedHashMap<>();
    //TODO: subCategoryName hashMap will contain sub category name and sub category image url
    private LinkedHashMap<String,String> subCategoryName =null;
}