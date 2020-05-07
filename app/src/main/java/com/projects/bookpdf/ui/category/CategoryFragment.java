package com.projects.bookpdf.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.bookpdf.R;

public class CategoryFragment extends Fragment implements ViewModelStoreOwner {

    public static CategoryViewModel categoryViewModel=null;
    public static View view=null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(categoryViewModel==null)
        categoryViewModel =
                new ViewModelProvider(CategoryFragment.this
                        , new CategoryViewModelFactory(getContext()))
                        .get(CategoryViewModel.class);
        if(view==null) {
            view = inflater.inflate(R.layout.fragment_category, container, false);
            GridView books=view.findViewById(R.id.grid_view_books);
            RecyclerView category=view.findViewById(R.id.recycler_view_category);
            RecyclerView subCategory=view.findViewById(R.id.recycler_view_sub_category);
            TextView txtSubCategoryTitle=view.findViewById(R.id.txt_sub_category_title);
            categoryViewModel.getToggleSubCategoryVisibility().observe(getViewLifecycleOwner(),
                    aBoolean -> {
                        if(aBoolean)
                        {
                            subCategory.setVisibility(View.VISIBLE);
                            txtSubCategoryTitle.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            subCategory.setVisibility(View.GONE);
                            txtSubCategoryTitle.setVisibility(View.GONE);
                        }
                    });
            categoryViewModel.setViews(books,category,subCategory,getActivity());
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
