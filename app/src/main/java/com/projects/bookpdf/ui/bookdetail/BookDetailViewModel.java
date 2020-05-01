package com.projects.bookpdf.ui.bookdetail;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projects.bookpdf.activity.MainActivity;
import com.projects.bookpdf.data.ObjectCollection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    void downloadBook(String downloadUrl) {
        Log.w("Download url f method",downloadUrl);

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