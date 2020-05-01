package com.projects.bookpdf.ui.home;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.cruxlab.sectionedrecyclerview.lib.SectionDataManager;
import com.cruxlab.sectionedrecyclerview.lib.SectionHeaderLayout;
import com.projects.bookpdf.adapter.HomePageBooksAdapter;

import java.util.ArrayList;
import java.util.Arrays;

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
        sectionDataManager.addSection(new HomePageBooksAdapter(true
                ,true
                ,context
                ,"Text1"
                ,new ArrayList<>(Arrays.asList("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"))
        ,false),s);
        s++;
        sectionDataManager.addSection(new HomePageBooksAdapter(true
                ,true
                ,context
                ,"Text2"
                ,new ArrayList<>(Arrays.asList("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")),false),s);
        s++;
        sectionDataManager.addSection(new HomePageBooksAdapter(true
                ,true
                ,context
                ,"Text3"
                ,new ArrayList<>(Arrays.asList("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")),false),s);
        s++;
        sectionDataManager.addSection(new HomePageBooksAdapter(true
                ,true
                ,context
                ,"Text4"
                ,new ArrayList<>(Arrays.asList("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")),false),s);
        s++;
        sectionDataManager.addSection(new HomePageBooksAdapter(true
                ,true
                ,context
                ,"Text5"
                ,new ArrayList<>(Arrays.asList("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")),false),s);
        s++;
        sectionDataManager.addSection(new HomePageBooksAdapter(true
                ,true
                ,context
                ,"Text6"
                ,new ArrayList<>(Arrays.asList("One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")),true),s);
        RecyclerView.Adapter adapter=sectionDataManager.getAdapter();
        this.recyclerHomePage.setAdapter(adapter);
        this.sectionHeaderLayout.attachTo(this.recyclerHomePage,sectionDataManager);
    }
}