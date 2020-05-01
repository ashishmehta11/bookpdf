package com.projects.bookpdf.ui.category;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {
    private Context context;

    CategoryViewModel(Context context) {
        this.context = context;
    }
}