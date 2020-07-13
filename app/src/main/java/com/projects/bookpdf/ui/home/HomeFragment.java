package com.projects.bookpdf.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.cruxlab.sectionedrecyclerview.lib.SectionHeaderLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.projects.bookpdf.R;


public class HomeFragment extends Fragment implements ViewModelStoreOwner {
    private static final String TAG = "HomeFragment";
    public static View view = null;
    public static HomeViewModel homeViewModel=null;
    private SectionHeaderLayout sectionHeaderLayout;
    private RecyclerView recyclerHomePage;
    private AdView adViewTop;

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
            loadTopAdBanner();
        }
        return view;
    }

    private void loadTopAdBanner() {
        LinearLayout linearLayout = view.findViewById(R.id.ad_linear_layout);
        AdView adView = new AdView(requireContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.ad_unit_id_banner));
        linearLayout.addView(adView);
        adView.loadAd(new AdRequest.Builder().build());
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e(TAG, "onAdFailedToLoad: i :  " + i);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "onAdLoaded: ");
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
