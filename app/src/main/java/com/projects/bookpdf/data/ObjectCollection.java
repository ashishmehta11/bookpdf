package com.projects.bookpdf.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;

public class ObjectCollection {
    public static SearchBook searchBook = null;
    public static HomePageBook homePageBook = null;
    public static HomePageNotifier homePageNotifier = new HomePageNotifier();
    public static BookDetailNotifier bookDetailNotifier = new BookDetailNotifier();
    public static SearchResultNotifier searchResultNotifier = new SearchResultNotifier();
    public static MoreSearchPagesNotifier moreSearchPagesNotifier = new MoreSearchPagesNotifier();
    public static int totalNoOfHomePageCatsLeft;
    public static int currHomePageCats = 0;

    public static void setHomePageBook(Activity activity) {
    new Thread(() -> {
        try {
//                TODO:: get Original home page book
            currHomePageCats = 0;
            Document doc = Jsoup.connect(HomePageBook.url).get();
            Elements allBooks = doc.select("[class=files-new]");
            Elements cats = doc.select("[class=collection-title mb-2]");
            HashMap<String, ArrayList<Book>> tempBooksCollection = new HashMap<>();
            int catIndex = 0;
            Log.e("setHomePageBook", "entering for");
            if (allBooks.size() > 0)
                ObjectCollection.homePageBook = new HomePageBook();
            for (int index = 0; index < allBooks.size(); index++) {
                ArrayList<Book> tempBook = new ArrayList<>();
                Element books = allBooks.get(index);
                if (books.hasText()) {
                    for (int subIndex = 0; subIndex < books.select("li").size(); subIndex++) {
                        Book b;
                        if (!books.select("li").get(subIndex).hasClass("liad")) {
                            int bookId = Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                            String bookImageUrl = books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                            String bookUrl = books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                            String bookName = books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                            String bookYear = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                            String bookSize = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                            String bookTotalDownload = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                            String bookPage = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                            String bookDescription = "";
                            String authors = "";
                            String bookLanguage = "";
                            String downloadUrl = "";
                            boolean areDetailsFetched = false;
                            b = new Book(bookId, bookName, bookUrl, bookImageUrl, bookDescription, bookPage, bookYear, bookSize, bookTotalDownload, authors, bookLanguage, downloadUrl, areDetailsFetched);
                            tempBook.add(b);
                        }
                    }
                    ObjectCollection.homePageBook.getBooks().put(cats.get(catIndex++).select("a").text(), tempBook);

                }
            }
            activity.runOnUiThread(() -> homePageNotifier.signalHomeFragment(0));
            ArrayList<Book> tempBook ;
//               TODO:: Manually add category for home page book
            HashMap<String, String> categoryWiseBooks = new HashMap<String, String>();
            categoryWiseBooks.put("Hacking", "https://www.pdfdrive.com/certified-ethical-hacker-books.html");
            categoryWiseBooks.put("Politics & Laws", "https://www.pdfdrive.com/category/15");
            categoryWiseBooks.put("Technology and Development", "https://www.pdfdrive.com/search?q=latest+developments&pagecount=&pubyear=2015");
//          TODO:: Get category wise book

            for (Map.Entry categoryWiseBook : categoryWiseBooks.entrySet()) {
                doc = Jsoup.connect(categoryWiseBook.getValue().toString()).get();
                Elements books = doc.select("[class=files-new]");
                tempBook = new ArrayList<>();
                for (int subIndex = 0; subIndex < books.select("li").size(); subIndex++) {
                    Book b;
                    if (!books.select("li").get(subIndex).hasClass("liad")) {
                        int bookId = Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                        String bookImageUrl = books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                        String bookUrl = books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                        String bookName = books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                        String bookYear = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                        String bookSize = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                        String bookTotalDownload = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                        String bookPage = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                        String bookDescription = "";
                        String authors = "";
                        String bookLanguage = "";
                        String downloadUrl = "";
                        boolean areDetailsFetched = false;
                        b = new Book(bookId, bookName, bookUrl, bookImageUrl, bookDescription, bookPage, bookYear, bookSize, bookTotalDownload, authors, bookLanguage, downloadUrl, areDetailsFetched);
                        tempBook.add(b);
                    }
                }
                ObjectCollection.homePageBook.getBooks().put(categoryWiseBook.getKey().toString(), tempBook);
                activity.runOnUiThread(() -> homePageNotifier.signalHomeFragment(0));
            }
            tempBook = new ArrayList<Book>();
            HashMap<String, String> special = new HashMap<>();
            special.put("https://www.pdfdrive.com/search?q=chanakya%20neeti&more=true", "https://www.pdfdrive.com/chanakya-neeti-e196841625.html");
            special.put("https://www.pdfdrive.com/search?q=Kamasutra+VATSYAYANA", "https://www.pdfdrive.com/kamasutra-vatsyayana-e34555033.html");
            special.put("https://www.pdfdrive.com/rich-dad-poor-dad-books.html", "https://www.pdfdrive.com/rich-dad-poor-dad-e136494023.html");
            special.put("https://www.pdfdrive.com/search?q=The+POWER+of+Your+Subconscious+Mind", "https://www.pdfdrive.com/the-power-of-your-subconscious-mind-e34352419.html");
            special.put("https://www.pdfdrive.com/search?q=Who+Will+Cry+When+You+Die&more=true", "https://www.pdfdrive.com/who-will-cry-when-you-die-life-lessons-from-the-monk-who-sold-his-ferrari-e196281352.html");
            special.put("https://www.pdfdrive.com/search?q=art+of+non+giving+fuck&more=true", "https://www.pdfdrive.com/the-subtle-art-of-not-giving-a-fck-a-counterintuitive-approach-to-living-a-good-life-e194691631.html");
            special.put("https://www.pdfdrive.com/art-of-war-books.html", "https://www.pdfdrive.com/sun-tzu-on-the-art-of-war-e28283867.html");
            special.put("https://www.pdfdrive.com/stay-hungry-stay-foolish-e43157992.html", "https://www.pdfdrive.com/stay-hungry-stay-foolish-e43157992.html");
            special.put("https://www.pdfdrive.com/search?q=Sapiens%3A+A+Brief+History+of+Humankind", "https://www.pdfdrive.com/sapiens-a-brief-history-of-humankind-e175870479.html");
            special.put("https://www.pdfdrive.com/search?q=Why+I+Killed+the+Mahatma&pagecount=&pubyear=&searchin=&more=true", "https://www.pdfdrive.com/why-i-killed-the-mahatma-understanding-godses-defence-e195131686.html");
//               TODO:: Get category wise book
            for (Map.Entry specialBook : special.entrySet()) {
                doc = Jsoup.connect(specialBook.getKey().toString()).get();
                Elements books = doc.select("[class=files-new]");
                for (int subIndex = 0; subIndex < books.select("li").size(); subIndex++) {
                    Book b;
                    if ((!books.select("li").get(subIndex).hasClass("liad")) && (books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href").toString().equals(specialBook.getValue().toString()))) {
                        Log.e("Inside", "Inside if");
                        int bookId = Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                        String bookImageUrl = books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                        String bookUrl = books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                        String bookName = books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                        String bookYear = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                        String bookSize = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                        String bookTotalDownload = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                        String bookPage = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                        String bookDescription = "";
                        String authors = "";
                        String bookLanguage = "";
                        String downloadUrl = "";
                        boolean areDetailsFetched = false;
                        b = new Book(bookId, bookName, bookUrl, bookImageUrl, bookDescription, bookPage, bookYear, bookSize, bookTotalDownload, authors, bookLanguage, downloadUrl, areDetailsFetched);
                        tempBook.add(b);
                        books = null;
                        Log.e("temp book",b.toString());
                        b=null;
                        break;
                    }
                }
            }
            ObjectCollection.homePageBook.getBooks().put("Special Books by Us",tempBook);
            activity.runOnUiThread(() -> homePageNotifier.signalHomeFragment(1));

        } catch (Exception e) {
            Log.e("setHomePageBook", "execetion : " + e.getMessage() + "\n\n" + Arrays.toString(e.getStackTrace()));
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
                Log.e("Book Url k:-",bookUrl);
                //TODO: Write code here and fill the variables above by using 'bookUrl' parameter.
                Document getDataFromBookUrl = Jsoup.connect(bookUrl).get();
                String sessionId=getDataFromBookUrl.select("[id=previewButtonMain]").attr("data-preview").toString();
                String dataId=getDataFromBookUrl.select("[id=previewButtonMain]").attr("data-id").toString();
                downloadUrl="https://www.pdfdrive.com/download.pdf?id="+dataId+"&h="+sessionId.split("session=")[1]+"&u=cache&ext=pdf";
                authors=getDataFromBookUrl.select("[itemprop=creator]").text().toString();
                bookLanguage=getDataFromBookUrl.select("[class=info-green]").last().text().toString();

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

    public static void searchForBook(String query,Activity activity) {
        new Thread(() -> {
        try {
            String searchQuery=query.replace(" ","+");
            String searchUrl="https://www.pdfdrive.com/search?q="+searchQuery;
            Document doc ;
            doc = Jsoup.connect(searchUrl).get();
            Elements books = doc.select("[class=files-new]");
            int totalPage=Integer.parseInt(doc.select("[class=Zebra_Pagination]").select("li").last().previousElementSibling().text());
            searchBook=new SearchBook(searchUrl,query,totalPage);
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
                    searchBook.getBooks().add(b);
                }
            }
            activity.runOnUiThread(() ->searchResultNotifier.notifyHomeActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        }).start();
    }

    public static void getOneMoreSearchPage(int pageNo, String searchUrl,FragmentActivity activity) {
        searchUrl+="&page="+pageNo;
        Log.e("Search Url",searchUrl);
        final String finalSearchUrl = searchUrl;
        new Thread(() -> {
            try {
                Document doc;
                doc = Jsoup.connect(finalSearchUrl).get();
                Elements books = doc.select("[class=files-new]");
                int totalPage=Integer.parseInt(doc.select("[class=Zebra_Pagination]").select("li").last().previousElementSibling().text());
                for(int subIndex=0;subIndex<books.select("li").size();subIndex++) {
                    Book b;
                    if (!books.select("li").get(subIndex).hasClass("liad")) {
                        int bookId = Integer.parseInt(books.select("li").get(subIndex).select("[class=file-left]").select("a").attr("data-id"));
                        String bookImageUrl = books.select("li").get(subIndex).select("[class=file-left]").select("img").attr("abs:src");
                        String bookUrl = books.select("li").get(subIndex).select("[class=file-right]").select("a").attr("abs:href");
                        String bookName = books.select("li").get(subIndex).select("[class=file-right]").select("h2").text();
                        String bookYear = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-year]").text();
                        String bookSize = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-size hidemobile]").text();
                        String bookTotalDownload = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-hit]").text();
                        String bookPage = books.select("li").get(subIndex).select("[class=file-right]").select("[class=fi-pagecount]").text();
                        String bookDescription = "";
                        String authors = "";
                        String bookLanguage = "";
                        String downloadUrl = "";
                        boolean areDetailsFetched = false;
                        b = new Book(bookId, bookName, bookUrl, bookImageUrl, bookDescription, bookPage, bookYear, bookSize, bookTotalDownload, authors, bookLanguage, downloadUrl, areDetailsFetched);
                        searchBook.getBooks().add(b);
                    }
                }
                activity.runOnUiThread(() -> moreSearchPagesNotifier.searchViewModelNotifier());
                if(books.select("li").size()>0)
                {
                    searchBook.setTotalLoadedPage(searchBook.getTotalLoadedPage()+1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }).start();
        }


    public static class HomePageNotifier extends Observable
    {
        void signalHomeFragment(int i)
        {
            setChanged();
            notifyObservers(i);
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

    public static class SearchResultNotifier extends Observable
    {
        void notifyHomeActivity()
        {
            setChanged();
            notifyObservers();
        }
    }

    public static class MoreSearchPagesNotifier extends Observable
    {
        void searchViewModelNotifier()
        {
            setChanged();
            notifyObservers();
        }
    }
}
