package com.projects.bookpdf.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projects.bookpdf.R;
import com.projects.bookpdf.data.MainActivityData;
import com.projects.bookpdf.data.ObjectCollection;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private CardView home, category, search, downloads, exit;
    private EditText editTextSearch;
    private TextView txtTitle,txtMessasge;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private NavController navController;
    private View confirmDialogView,searchDialogView,progressView;
    private AlertDialog confirmDialog,searchDialog;
    private MaterialCardView yesCard,noCard,searchCard;
    public static Dialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: setting progress dialog
        progressView=getLayoutInflater().inflate(R.layout.progress_wheel,null,false);
        progressDialog=new Dialog(MainActivity.this,R.style.Theme_AppCompat_Light_Dialog_Alert);
        progressDialog.setContentView(progressView);
        progressDialog.setCancelable(false);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        SpinKitView progressDialog=progressView.findViewById(R.id.spin_kit);
        Sprite sprite=new FadingCircle();
        sprite.setColor(R.color.colorPrimary);
        progressDialog.setIndeterminateDrawable(sprite);
        //TODO: Initializing and creating views for confirmation dialog
        confirmDialogView=getLayoutInflater().inflate(R.layout.confirm_dialog,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setView(confirmDialogView);
        confirmDialog= builder.create();
        yesCard=confirmDialogView.findViewById(R.id.material_card_yes);
        yesCard.setOnClickListener(v -> finish());
        noCard=confirmDialogView.findViewById(R.id.material_card_no);
        noCard.setOnClickListener(v -> confirmDialog.dismiss());
        txtMessasge=confirmDialogView.findViewById(R.id.txt_confirm_dialog_text);

        //TODO: Initializing and creating views for search dialog
        searchDialogView=getLayoutInflater().inflate(R.layout.search_dialog,null);
        builder=new AlertDialog.Builder(MainActivity.this);
        builder.setView(searchDialogView);
        searchCard=searchDialogView.findViewById(R.id.material_card_search);
        editTextSearch=searchDialogView.findViewById(R.id.edit_txt_search);
        searchCard.setOnClickListener(v -> {
            if(editTextSearch.length()>0)
            {
                searchDialog.dismiss();
                navController.navigate(R.id.search);
                //TODO: Call for Searching books
            }
        });
        editTextSearch.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId== EditorInfo.IME_ACTION_GO)
                searchCard.callOnClick();
            return false;
        });
        searchDialog=builder.create();

        txtTitle = findViewById(R.id.txt_title);
        txtTitle.setText(MainActivityData.title);

        //TODO: Sliding Pane Layout.
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

        //TODO: NavController.
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
            editTextSearch.setText("");
            searchDialog.show();
        });
        downloads = findViewById(R.id.card_view_downloads);
        downloads.setOnClickListener(v -> {
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            navController.navigate(R.id.downloads);
        });
        exit = findViewById(R.id.card_view_exit);
        exit.setOnClickListener(v -> showConfirmationDialog(getString(R.string.txt_dialog_exit_title)));
    }

    private void showConfirmationDialog(String msg) {
        txtMessasge.setText(msg);
        confirmDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        else if (!MainActivityData.title.equalsIgnoreCase("Home")) {
            navController.navigate(R.id.home);
        } else
            showConfirmationDialog(getString(R.string.txt_dialog_exit_title));
    }
    public static void showProgressDialog()
    {
        if(!progressDialog.isShowing())
        progressDialog.show();
    }
    public static void stopProgressDialog()
    {
        if(progressDialog.isShowing())
        progressDialog.dismiss();
    }
}
