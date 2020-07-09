package com.projects.bookpdf.ui.home;

import android.os.Bundle;
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
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.projects.bookpdf.Constants;
import com.projects.bookpdf.R;


public class HomeFragment extends Fragment implements ViewModelStoreOwner {

    public static View view = null;
    public static HomeViewModel homeViewModel=null;
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
            AudienceNetworkAds.initialize(requireContext());
            view = inflater.inflate(R.layout.fragment_home, container, false);
            loadFBBanner();
            recyclerHomePage = view.findViewById(R.id.recycler_view_home_page);
            sectionHeaderLayout = view.findViewById(R.id.section_header_layout_home_page);
            homeViewModel.assignViews(recyclerHomePage, sectionHeaderLayout);
            homeViewModel.setAdapter();
        }
        return view;
    }
    private void loadFBBanner() {
        AdSettings.addTestDevice(getString(R.string.testDeviceID));
        if (Constants.isOnline(requireContext())) {
            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(requireContext(), getResources().getString(R.string.fb_banner), AdSize.BANNER_HEIGHT_50);
            LinearLayout adContainer = (LinearLayout) view.findViewById(R.id.fb_banner_container);
            adContainer.addView(adView);
            adView.loadAd();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
