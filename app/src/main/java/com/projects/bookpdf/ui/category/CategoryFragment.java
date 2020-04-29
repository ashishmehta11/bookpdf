package com.projects.bookpdf.ui.category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.projects.bookpdf.R;
import com.projects.bookpdf.data.MainActivityData;

public class CategoryFragment extends Fragment implements ViewModelStoreOwner {

    private CategoryViewModel categoryViewModel;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                new ViewModelProvider(CategoryFragment.this
                        , new CategoryViewModelFactory(getContext()))
                        .get(CategoryViewModel.class);
        Log.e("AJM","catvm :"+categoryViewModel);
        view= inflater.inflate(R.layout.fragment_category, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
