package com.projects.bookpdf.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.bookpdf.R;
import com.projects.bookpdf.adapter.RecyclerAdapterSearchBooks;
import com.projects.bookpdf.data.MainActivityData;
import com.projects.bookpdf.data.ObjectCollection;

public class SearchFragment extends Fragment implements ViewModelStoreOwner {

    private SearchViewModel searchViewModel;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        searchViewModel =
                new ViewModelProvider(SearchFragment.this,
                        new SearchViewModelFactory(requireContext()))
                .get(SearchViewModel.class);
        view= inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.recycler_search_books);
        searchViewModel.setAdapter(recyclerView);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
