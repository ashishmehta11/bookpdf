package com.projects.bookpdf.ui.downloads;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.projects.bookpdf.ui.category.CategoryViewModel;

public class DownloadsViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    public DownloadsViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new DownloadsViewModel(context);
    }
}
