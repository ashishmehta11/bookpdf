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
    HomeViewModel(Context context) {
        this.context = context;
        ObjectCollection.homePageNotifier.addObserver(HomeViewModel.this);
    }

    void setAdapter(RecyclerView recyclerHomePage, SectionHeaderLayout sectionHeaderLayout) {
        MainActivity.showProgressDialog();
        this.recyclerHomePage = recyclerHomePage;
        this.sectionHeaderLayout = sectionHeaderLayout;
        SectionDataManager sectionDataManager=new SectionDataManager();
        if(ObjectCollection.homePageBook!=null) {
            short s = 1;
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
        RecyclerView.Adapter adapter = sectionDataManager.getAdapter();
        this.recyclerHomePage.setAdapter(adapter);
        this.sectionHeaderLayout.attachTo(this.recyclerHomePage, sectionDataManager);
    }

    @Override
    public void update(Observable o, Object arg) {
        MainActivity.stopProgressDialog();
        if(o instanceof ObjectCollection.HomePageNotifier)
        {
            SectionDataManager sectionDataManager=new SectionDataManager();
            if(ObjectCollection.homePageBook!=null) {
                short s = 1;
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
            RecyclerView.Adapter adapter = sectionDataManager.getAdapter();
            recyclerHomePage.setAdapter(adapter);
            sectionHeaderLayout.attachTo(recyclerHomePage, sectionDataManager);
        }
    }
}