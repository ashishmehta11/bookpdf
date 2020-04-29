package com.projects.bookpdf.ui.downloads;

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
import androidx.lifecycle.ViewModelStoreOwner;

import com.projects.bookpdf.R;
import com.projects.bookpdf.data.MainActivityData;

public class DownloadsFragment extends Fragment implements ViewModelStoreOwner {

    private DownloadsViewModel downloadsViewModel;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        downloadsViewModel =
                new ViewModelProvider(DownloadsFragment.this
                        , new DownloadsViewModelFactory(getContext()))
                        .get(DownloadsViewModel.class);
        view = inflater.inflate(R.layout.fragment_downloads, container, false);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

    }
}
