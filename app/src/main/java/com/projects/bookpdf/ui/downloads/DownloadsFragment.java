package com.projects.bookpdf.ui.downloads;

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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.projects.bookpdf.R;

public class DownloadsFragment extends Fragment implements ViewModelStoreOwner {
    private DownloadsViewModel downloadsViewModel;
    private View view ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        downloadsViewModel =
                new ViewModelProvider(DownloadsFragment.this
                        , new DownloadsViewModelFactory(getContext()))
                        .get(DownloadsViewModel.class);
        view = inflater.inflate(R.layout.fragment_downloads, container, false);
        RecyclerView recyclerDownloadedBooks = view.findViewById(R.id.recycler_downloaded_books);
        downloadsViewModel.setView(recyclerDownloadedBooks);
        loadTopAdBanner();
        return view;
    }

    private void loadTopAdBanner() {
        LinearLayout linearLayout = view.findViewById(R.id.ad_linear_layout);
        AdView adView = new AdView(requireContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.ad_unit_id_banner));
        linearLayout.removeAllViews();
        linearLayout.addView(adView);
        adView.loadAd(new AdRequest.Builder().build());
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
