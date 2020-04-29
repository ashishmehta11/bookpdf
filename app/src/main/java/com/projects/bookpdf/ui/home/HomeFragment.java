package com.projects.bookpdf.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;

import com.projects.bookpdf.R;
import com.projects.bookpdf.data.MainActivityData;
import com.projects.bookpdf.ui.category.CategoryFragment;
import com.projects.bookpdf.ui.category.CategoryViewModel;
import com.projects.bookpdf.ui.category.CategoryViewModelFactory;

public class HomeFragment extends Fragment implements ViewModelStoreOwner {

    private HomeViewModel homeViewModel;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(HomeFragment.this
                        , new HomeViewModelFactory(getContext()))
                        .get(HomeViewModel.class);
        view= inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

    }
}
