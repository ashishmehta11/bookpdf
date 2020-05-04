package com.projects.bookpdf.ui.downloads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

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
        RecyclerView recyclerDownloadedBooks=view.findViewById(R.id.recycler_downloaded_books);
        downloadsViewModel.setView(recyclerDownloadedBooks);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
