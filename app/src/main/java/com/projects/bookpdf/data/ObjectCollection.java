package com.projects.bookpdf.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;

public class ObjectCollection {
    public static SearchBook searchBook=null;
    public static HomePageBook homePageBook=null;
    public static HomePageNotifier homePageNotifier=new HomePageNotifier();
    public static BookDetailNotifier bookDetailNotifier=new BookDetailNotifier();
    public static int totalNoOfHomePageCatsLeft;
    public static int currHomePageCats=0;
    public static void setHomePageBook(Activity activity)
    {
        new Thread(() -> {
            try {
                currHomePageCats=0;
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
                                String bookYear=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                                String bookSize=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                                String bookTotalDownload=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                                String bookPage=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                                String bookDescription="";
                                String authors="";
                                String bookLanguage="";
                                String downloadUrl="";
                                boolean areDetailsFetched=false;
                                b=new Book(bookId,bookName, bookUrl, bookImageUrl,bookDescription, bookPage,bookYear,bookSize,bookTotalDownload,authors,bookLanguage,downloadUrl,areDetailsFetched);
                                tempBook.add(b);
                            }
                        }
                        tempBooksCollection.put(cats.get(catIndex++).select("a").text(),tempBook);
                        tempBook=null;
                    }

                }

                doc = Jsoup.connect("https://www.pdfdrive.com/certified-ethical-hacker-books.html").get();
                Elements books = doc.select("[class=files-new]");
                homePageBook=new HomePageBook(tempBooksCollection);
                ArrayList<Book> tempBook = new ArrayList<>();
                for(int subIndex=0;subIndex<books.select("li").size();subIndex++)
                {

                    Book b;
                    if(!books.select("li").get(subIndex).hasClass("liad")) {
                        int bookId=Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                        String bookImageUrl=books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                        String bookUrl=books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                        String bookName=books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                        String bookYear=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                        String bookSize=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                        String bookTotalDownload=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                        String bookPage=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                        String bookDescription="";
                        String authors="";
                        String bookLanguage="";
                        String downloadUrl="";
                        boolean areDetailsFetched=false;
                        b=new Book(bookId,bookName, bookUrl, bookImageUrl,bookDescription, bookPage,bookYear,bookSize,bookTotalDownload,authors,bookLanguage,downloadUrl,areDetailsFetched);
                        tempBook.add(b);
                    }
                }
                tempBooksCollection.put("Hacking",tempBook);

//                politis and law
                doc = Jsoup.connect("https://www.pdfdrive.com/category/15").get();
                books = doc.select("[class=files-new]");

                tempBook = new ArrayList<>();
                for(int subIndex=0;subIndex<books.select("li").size();subIndex++)
                {

                    Book b;
                    if(!books.select("li").get(subIndex).hasClass("liad")) {
                        int bookId=Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                        String bookImageUrl=books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                        String bookUrl=books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                        String bookName=books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                        String bookYear=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                        String bookSize=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                        String bookTotalDownload=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                        String bookPage=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                        String bookDescription="";
                        String authors="";
                        String bookLanguage="";
                        String downloadUrl="";
                        boolean areDetailsFetched=false;
                        b=new Book(bookId,bookName, bookUrl, bookImageUrl,bookDescription, bookPage,bookYear,bookSize,bookTotalDownload,authors,bookLanguage,downloadUrl,areDetailsFetched);
                        tempBook.add(b);
                    }
                }
                tempBooksCollection.put("Politics & Laws",tempBook);
//            Latest Development
                doc = Jsoup.connect("https://www.pdfdrive.com/search?q=latest+developments&pagecount=&pubyear=2015").get();
                books = doc.select("[class=files-new]");

                tempBook=null;
                tempBook = new ArrayList<>();
                for(int subIndex=0;subIndex<books.select("li").size();subIndex++)
                {

                    Book b;
                    if(!books.select("li").get(subIndex).hasClass("liad")) {
                        int bookId=Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                        String bookImageUrl=books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                        String bookUrl=books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                        String bookName=books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                        String bookYear=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                        String bookSize=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                        String bookTotalDownload=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                        String bookPage=books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                        String bookDescription="";
                        String authors="";
                        String bookLanguage="";
                        String downloadUrl="";
                        boolean areDetailsFetched=false;
                        b=new Book(bookId,bookName, bookUrl, bookImageUrl,bookDescription, bookPage,bookYear,bookSize,bookTotalDownload,authors,bookLanguage,downloadUrl,areDetailsFetched);
                        tempBook.add(b);
                    }
                }
                tempBooksCollection.put("Technology and Development",tempBook);
                homePageBook=new HomePageBook(tempBooksCollection);
                //TODO : To temporary make the application work , uncomment the below code.
                //activity.runOnUiThread(() -> homePageNotifier.signalHomeFragment());

                //TODO: All the categories are not being loaded in home page accoridng to pdf drive.
                totalNoOfHomePageCatsLeft=1;//TODO: make this digit dynamic!
                for(int i=1;i<=totalNoOfHomePageCatsLeft;i++)
                {
                    loadRemainingHomePageBooks(activity);
                }

            } catch (Exception e) {
                Log.e("setHomePageBook","execetion : "+e.getMessage()+"\n\n"+ Arrays.toString(e.getStackTrace()));
            }
        }).start();

    }

    private static void loadRemainingHomePageBooks(Activity activity)
    {
        new Thread(() -> {
            try
            {

                //TODO: write code here...
                currHomePageCats++;
                if(currHomePageCats>=totalNoOfHomePageCatsLeft)
                    activity.runOnUiThread(() -> homePageNotifier.signalHomeFragment());
            }
            catch (Exception e)
            {
                Log.e("loadRemainingHomeBooks","Exception : "+e.getMessage()+"\n\n"+ Arrays.toString(e.getStackTrace()));
            }
        }).start();

    }
    //TODO: use this method to load book details from home page object
    public static void getIndividualBookDetails(String headerText, int position, String bookUrl,FragmentActivity activity)
    {
        new Thread(() -> {
            try {

                Log.e("Book Url k:-",bookUrl);
                //TODO: Write code here and fill the variables above by using 'bookUrl' parameter.
                Document getDataFromBookUrl = Jsoup.connect(bookUrl).get();
                String sessionId=getDataFromBookUrl.select("[id=previewButtonMain]").attr("data-preview").toString();
                String dataId=getDataFromBookUrl.select("[id=previewButtonMain]").attr("data-id").toString();
                String downloadUrl="https://www.pdfdrive.com/download.pdf?id="+dataId+"&h="+sessionId.split("session=")[1]+"&u=cache&ext=pdf";
                String authors=getDataFromBookUrl.select("[itemprop=creator]").text().toString();
                String bookLanguage=getDataFromBookUrl.select("[class=info-green]").last().text().toString();
                //TODO: Don't touch below code!
                Objects.requireNonNull(homePageBook.getBooks().get(headerText)).get(position).setBookLanguage(bookLanguage);
                Objects.requireNonNull(homePageBook.getBooks().get(headerText)).get(position).setAuthors(authors);
                Objects.requireNonNull(homePageBook.getBooks().get(headerText)).get(position).setDownloadUrl(downloadUrl);
                Objects.requireNonNull(homePageBook.getBooks().get(headerText)).get(position).setAreDetailsFetched(true);
                activity.runOnUiThread(() -> bookDetailNotifier.notifyBookDetailViewModel());
            } catch (Exception e) {
                Log.e("getBookDetails", "Exception : " + e.getMessage() + "\n\n" + Arrays.toString(e.getStackTrace()));
            }
        }).start();
    }

    //TODO: use this method to load book details from Search book object
    public static void getIndividualBookDetails(int position, String bookUrl,FragmentActivity activity)
    {
        new Thread(() -> {
            try {
                String bookLanguage="";
                String authors="";
                String downloadUrl="";
                //TODO: Write code here and fill the variables above by using 'bookUrl' parameter.


                //TODO: Don't touch below code!
                Objects.requireNonNull(searchBook.getBooks()).get(position).setBookLanguage(bookLanguage);
                Objects.requireNonNull(searchBook.getBooks()).get(position).setAuthors(authors);
                Objects.requireNonNull(searchBook.getBooks()).get(position).setDownloadUrl(downloadUrl);
                Objects.requireNonNull(searchBook.getBooks()).get(position).setAreDetailsFetched(true);
                activity.runOnUiThread(() -> bookDetailNotifier.notifyBookDetailViewModel());
            } catch (Exception e) {
                Log.e("getBookDetails", "Exception : " + e.getMessage() + "\n\n" + Arrays.toString(e.getStackTrace()));
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

    public static class BookDetailNotifier extends Observable
    {
        void notifyBookDetailViewModel()
        {
            setChanged();
            notifyObservers();
        }
    }
}
