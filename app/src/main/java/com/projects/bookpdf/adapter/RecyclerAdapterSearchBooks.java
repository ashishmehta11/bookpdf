package com.projects.bookpdf.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.projects.bookpdf.R;
import com.projects.bookpdf.data.Book;
import com.projects.bookpdf.data.ObjectCollection;

import java.util.ArrayList;

public class RecyclerAdapterSearchBooks extends RecyclerView.Adapter<RecyclerAdapterSearchBooks.ViewHolder> {
    public static boolean newIncomingDataReached;
    private Context context;
    public RecyclerAdapterSearchBooks(Context context) {
        this.context = context;
        newIncomingDataReached=true;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(newIncomingDataReached&&position>=ObjectCollection.searchBook.getBooks().size()-3)
        {
            newIncomingDataReached=false;
            if(ObjectCollection.searchBook.getTotalLoadedPage()+1<=ObjectCollection.searchBook.getTotalPage())
            {
                //TODO: call for page number :totalLoadedPage + 1 in the search query
                //ObjectCollection.getOneMoreSearchPage(ObjectCollection.searchBook.getTotalLoadedPage()+1);
            }
        }
        Glide.with(context)
                .load(ObjectCollection.searchBook.getBooks().get(position).getBookImageURL())
                .into(holder.bookCover);

        if (ObjectCollection.searchBook.getBooks().get(position).getBookYear() > 0)
            holder.txtYear.setText(String.valueOf(ObjectCollection.searchBook.getBooks().get(position).getBookYear()));
        else
            holder.txtYear.setText("NA");

        holder.txtTitle.setText(ObjectCollection.searchBook.getBooks().get(position).getBookName());

        holder.bookCover.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("coming_from_home",false);
            bundle.putBoolean("coming_from_search",true);
            bundle.putInt("current_book_position",position);
            Navigation.findNavController(v).navigate(R.id.book_details,bundle);
        });
    }

    @Override
    public int getItemCount() {
        return ObjectCollection.searchBook.getBooks().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView bookCover;
        TextView txtTitle,txtYear;
        MaterialCardView itemCard;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCard=itemView.findViewById(R.id.material_card_search_item);
            bookCover=itemView.findViewById(R.id.img_book_cover);
            txtTitle=itemView.findViewById(R.id.txt_title);
            txtYear=itemView.findViewById(R.id.txt_year);
        }
    }
}
