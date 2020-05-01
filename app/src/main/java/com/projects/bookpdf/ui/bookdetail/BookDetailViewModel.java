package com.projects.bookpdf.ui.bookdetail;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projects.bookpdf.activity.MainActivity;
import com.projects.bookpdf.data.ObjectCollection;

import java.util.Observable;
import java.util.Observer;

class BookDetailViewModel extends ViewModel implements Observer {
    private Context context;
    private MutableLiveData<Integer> loadRemainingData;
    BookDetailViewModel(Context context) {
        this.context = context;
        loadRemainingData=new MutableLiveData<>();
        loadRemainingData.setValue(0);
        ObjectCollection.bookDetailNotifier.addObserver(BookDetailViewModel.this);
    }

    //TODO: Download the book!! 
    void downloadBook(String downloadUrl) {
    }
    
    @Override
    public void update(Observable o, Object arg) {
        MainActivity.stopProgressDialog();
        if(o instanceof ObjectCollection.BookDetailNotifier)
            loadRemainingData.setValue(loadRemainingData.getValue()+1);
    }

    MutableLiveData<Integer> getLoadRemainingData() {
        return loadRemainingData;
    }
}