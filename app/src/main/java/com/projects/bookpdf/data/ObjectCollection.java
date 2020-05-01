package com.projects.bookpdf.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.os.AsyncTask;
import android.util.Log;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObjectCollection {
    public static SearchBook searchBook=null;
    public static HomePageBook homePageBook=null;

    public static void setHomePageBook()
    {
        homePageBook=new HomePageBook();
        HashMap<String, ArrayList<Book>> books=new HashMap<String,ArrayList<Book>>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect(homePageBook.getUrl()).get();
                    Elements allBooks = doc.select("[class=files-new]");
                    Elements cats = doc.select("[class=collection-title mb-2]");
                    HashMap<String,ArrayList<Book>> tempBooksCollection=new HashMap<String,ArrayList<Book>>();
//                    Log.e("cat",cats.select("a").text());
                    int catIndex=0;
                    for(int index=0;index<allBooks.size();index++)
                    {

                        ArrayList<Book> tempBook = new ArrayList<Book>();
                        Element books=allBooks.get(index);
                        if (books.hasText())
                        {
                            for(int subIndex=0;subIndex<books.select("li").size();subIndex++)
                            {
                                Book b;
                                if(!books.select("li").get(subIndex).hasClass("liad")) {
                                    int bookId=Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                                    String bookImageUrl=books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                                    String bookUrl=books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                                    String bookName=books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                                    int bookYear=Integer.parseInt(books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text());
                                    String bookSize=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                                    String bookTotalDownload=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                                    String bookPage=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                                    String bookDescription="";
                                    b=new Book(bookId,bookName, bookUrl, bookImageUrl,bookDescription, bookPage,bookYear,bookSize,bookTotalDownload);
                                    tempBook.add(b);
                                }
                            }

                            tempBooksCollection.put(cats.get(catIndex++).select("a").text(),tempBook);
                            tempBook=null;
                        }

                    }
                    homePageBook=new HomePageBook(tempBooksCollection);

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
            }
        }).start();
    }
}
