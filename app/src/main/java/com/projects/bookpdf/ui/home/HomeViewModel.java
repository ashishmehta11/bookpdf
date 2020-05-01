package com.projects.bookpdf.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.cruxlab.sectionedrecyclerview.lib.SectionDataManager;
import com.cruxlab.sectionedrecyclerview.lib.SectionHeaderLayout;
import com.projects.bookpdf.adapter.HomePageBooksAdapter;
import com.projects.bookpdf.data.Book;
import com.projects.bookpdf.data.HomePageBook;
import com.projects.bookpdf.data.ObjectCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

class HomeViewModel extends ViewModel {
    private Context context;
    private RecyclerView recyclerHomePage;
    private SectionHeaderLayout sectionHeaderLayout;
    HomeViewModel(Context context) {
        this.context = context;
    }
    void setAdapter(RecyclerView recyclerHomePage, SectionHeaderLayout sectionHeaderLayout) {
        this.recyclerHomePage=recyclerHomePage;
        this.sectionHeaderLayout=sectionHeaderLayout;
        SectionDataManager sectionDataManager=new SectionDataManager();
        short s=1;
        Log.e("ok",String.valueOf(ObjectCollection.homePageBook.getBooks().size()));
        for(Map.Entry m:ObjectCollection.homePageBook.getBooks().entrySet()){
            Log.e("Book List",ObjectCollection.homePageBook.getBooks().get(m.getKey().toString()).toString());
            if(s==ObjectCollection.homePageBook.getBooks().size())
            {
                sectionDataManager.addSection(new HomePageBooksAdapter(true
                        ,true
                        ,context
                        ,m.getKey().toString()
                        ,ObjectCollection.homePageBook.getBooks().get(m.getKey().toString())
                        ,true),s);

            }
            else
            {
                sectionDataManager.addSection(new HomePageBooksAdapter(true
                        ,true
                        ,context
                        ,m.getKey().toString()
                        ,ObjectCollection.homePageBook.getBooks().get(m.getKey().toString())
                        ,false),s);

            }
            s++;


        }


        RecyclerView.Adapter adapter=sectionDataManager.getAdapter();
        this.recyclerHomePage.setAdapter(adapter);
        this.sectionHeaderLayout.attachTo(this.recyclerHomePage,sectionDataManager);
    }
}