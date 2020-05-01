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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cruxlab.sectionedrecyclerview.lib.SectionHeaderLayout;
import com.projects.bookpdf.R;


public class HomeFragment extends Fragment implements ViewModelStoreOwner {

    private HomeViewModel homeViewModel;
    private View view;
    private SectionHeaderLayout sectionHeaderLayout;
    private RecyclerView recyclerHomePage;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(HomeFragment.this
                        , new HomeViewModelFactory(getContext()))
                        .get(HomeViewModel.class);
        view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerHomePage=view.findViewById(R.id.recycler_view_home_page);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerHomePage.setLayoutManager(layoutManager);
        recyclerHomePage.setHasFixedSize(false);
        sectionHeaderLayout=view.findViewById(R.id.section_header_layout_home_page);
        homeViewModel.setAdapter(recyclerHomePage,sectionHeaderLayout);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
