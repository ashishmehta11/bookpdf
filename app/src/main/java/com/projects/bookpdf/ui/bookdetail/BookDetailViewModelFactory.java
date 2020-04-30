package com.projects.bookpdf.ui.bookdetail;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class BookDetailViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    BookDetailViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(BookDetailViewModel.class))
            return (T) new BookDetailViewModel(context);
        return null;

    }
}
