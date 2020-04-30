package com.projects.bookpdf.ui.bookdetail;

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

public class BookDetailFragment extends Fragment implements ViewModelStoreOwner {

    private BookDetailViewModel bookDetailViewModel;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookDetailViewModel =
                new ViewModelProvider(BookDetailFragment.this
                        , new BookDetailViewModelFactory(getContext()))
                        .get(BookDetailViewModel.class);
        Log.e("AJM","catvm :"+ bookDetailViewModel);
        view= inflater.inflate(R.layout.fragment_book_detail, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
