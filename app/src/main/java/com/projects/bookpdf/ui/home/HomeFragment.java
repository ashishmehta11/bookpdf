package com.projects.bookpdf.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.cruxlab.sectionedrecyclerview.lib.SectionHeaderLayout;
import com.projects.bookpdf.R;


public class HomeFragment extends Fragment implements ViewModelStoreOwner {

    private static View view = null;
    private static HomeViewModel homeViewModel=null;
    private SectionHeaderLayout sectionHeaderLayout;
    private RecyclerView recyclerHomePage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(homeViewModel==null)
        homeViewModel =
                new ViewModelProvider(HomeFragment.this
                        , new HomeViewModelFactory(getContext()))
                        .get(HomeViewModel.class);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            recyclerHomePage = view.findViewById(R.id.recycler_view_home_page);
            sectionHeaderLayout = view.findViewById(R.id.section_header_layout_home_page);
            homeViewModel.assignViews(recyclerHomePage, sectionHeaderLayout);
            homeViewModel.setAdapter();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
