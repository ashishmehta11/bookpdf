package com.projects.bookpdf.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class ObjectCollection {
    public static SearchBook searchBook=null;
    public static HomePageBook homePageBook=null;
    public static HomePageNotifier homePageNotifier=new HomePageNotifier();
    public static void setHomePageBook(Activity activity)
    {
        new Thread(() -> {
            try {
                Log.e("setHomePageBook","inside try");
                Document doc = Jsoup.connect(HomePageBook.url).get();
                Log.e("setHomePageBook","recieved document object");
                Elements allBooks = doc.select("[class=files-new]");
                Log.e("setHomePageBook","allBooks.size() : "+allBooks.size());
                Elements cats = doc.select("[class=collection-title mb-2]");
                HashMap<String,ArrayList<Book>> tempBooksCollection= new HashMap<>();
                int catIndex=0;
                Log.e("setHomePageBook","entering for");
                for(int index=0;index<allBooks.size();index++)
                {

                    ArrayList<Book> tempBook = new ArrayList<>();
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
                Log.e("setHomePageBook","exited for");
                homePageBook=new HomePageBook(tempBooksCollection);
                for(Map.Entry m:tempBooksCollection.entrySet())
                {
                    Log.e("Book List ",m.getKey()+"  :=  "+m.getValue().toString());
                }
                activity.runOnUiThread(() -> homePageNotifier.signalHomeFragment());


            } catch (Exception e) {
                Log.e("setHomePageBook","execetion : "+e.getMessage()+"\n\n"+ Arrays.toString(e.getStackTrace()));
            }
        }).start();

    }
    public static class HomePageNotifier extends Observable
    {
        void signalHomeFragment()
        {
            setChanged();
            notifyObservers();
        }
    }
}
