package com.projects.bookpdf.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.projects.bookpdf.R;
import com.projects.bookpdf.data.MainActivityData;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends AppCompatActivity {
    private CardView home, category, search, downloads, exit;
    private TextView txtTitle;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.txt_title);
        txtTitle.setText(MainActivityData.title);

        slidingUpPanelLayout = findViewById(R.id.umano_sliding_layout);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState,
                                            SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    txtTitle.setText(getString(R.string.select_an_option));
                } else
                    txtTitle.setText(MainActivityData.title);
            }
        });


        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            MainActivityData.title = destination.getLabel().toString();
            txtTitle.setText(MainActivityData.title);
        });

        home = findViewById(R.id.card_view_home);
        home.setOnClickListener(v -> {
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            navController.navigate(R.id.home);
        });
        category = findViewById(R.id.card_view_category);
        category.setOnClickListener(v -> {
                    slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    navController.navigate(R.id.category);
                }
        );
        search = findViewById(R.id.card_view_search);
        search.setOnClickListener(v -> {
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            navController.navigate(R.id.search);
        });
        downloads = findViewById(R.id.card_view_downloads);
        downloads.setOnClickListener(v -> {
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            navController.navigate(R.id.downloads);
        });
        exit = findViewById(R.id.card_view_exit);
        exit.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
        if (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        else if (!MainActivityData.title.equalsIgnoreCase("Home")) {
            navController.navigate(R.id.home);
        } else
            finish();
    }
}
