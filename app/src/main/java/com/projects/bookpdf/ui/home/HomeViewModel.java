package com.projects.bookpdf.ui.home;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.cruxlab.sectionedrecyclerview.lib.SectionDataManager;
import com.cruxlab.sectionedrecyclerview.lib.SectionHeaderLayout;
import com.projects.bookpdf.activity.MainActivity;
import com.projects.bookpdf.adapter.HomePageBooksAdapter;
import com.projects.bookpdf.data.Book;
import com.projects.bookpdf.data.ObjectCollection;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Handler;

class HomeViewModel extends ViewModel implements Observer {
    private Context context;
    private RecyclerView recyclerHomePage;
    private SectionHeaderLayout sectionHeaderLayout;
    private SectionDataManager sectionDataManager=new SectionDataManager();
    private short s = 1;
    HomeViewModel(Context context) {
        this.context = context;
        ObjectCollection.homePageNotifier.addObserver(HomeViewModel.this);
    }

    void setAdapter(RecyclerView recyclerHomePage, SectionHeaderLayout sectionHeaderLayout) {
        MainActivity.showProgressDialog();
        this.recyclerHomePage = recyclerHomePage;
        this.sectionHeaderLayout = sectionHeaderLayout;

        RecyclerView.Adapter adapter = sectionDataManager.getAdapter();
        this.recyclerHomePage.setAdapter(adapter);
        this.sectionHeaderLayout.attachTo(this.recyclerHomePage, sectionDataManager);
        if(ObjectCollection.homePageBook!=null) {

            for (Map.Entry<String, ArrayList<Book>> m : ObjectCollection.homePageBook.getBooks().entrySet()) {
                boolean isLast;
                isLast = s == ObjectCollection.homePageBook.getBooks().size();
                HomePageBooksAdapter homePageBooksAdapter = new HomePageBooksAdapter(
                        true
                        , true
                        , context
                        , m.getKey()
                        , m.getValue()
                        , isLast);
                sectionDataManager.addSection(homePageBooksAdapter, s);
                s++;
            }
            MainActivity.stopProgressDialog();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof ObjectCollection.HomePageNotifier)
        {
            if(ObjectCollection.homePageBook!=null) {
                if(sectionDataManager.getSectionCount()>0)
                {
                    HomePageBooksAdapter obj=sectionDataManager.getSectionAdapter(sectionDataManager.getSectionCount()-1);
                    HomePageBooksAdapter homePageBooksAdapter = new HomePageBooksAdapter(
                            true
                            , true
                            , context
                            , obj.getHeaderText()
                            , obj.getBookList()
                            , false);
                    s--;
                    sectionDataManager.replaceSection(sectionDataManager.getSectionCount()-1
                    ,homePageBooksAdapter,s);
                    s++;
                }
                int i=1;
                for (Map.Entry<String, ArrayList<Book>> m : ObjectCollection.homePageBook.getBooks().entrySet()) {
                    if(i<=sectionDataManager.getSectionCount()) {
                        i++;
                        continue;
                    }
                    boolean isLast;
                    isLast = s == ObjectCollection.homePageBook.getBooks().size();
                    HomePageBooksAdapter homePageBooksAdapter = new HomePageBooksAdapter(
                            true
                            , true
                            , context
                            , m.getKey()
                            , m.getValue()
                            , isLast);
                    sectionDataManager.addSection(homePageBooksAdapter, s);
                    s++;
                    i++;
                }
            }
        }
        MainActivity.stopProgressDialog();
    }
}