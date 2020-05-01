package com.projects.bookpdf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projects.bookpdf.R;

import java.util.ArrayList;

public class HomePageInnerRecyclerBooksAdapter extends RecyclerView.Adapter<HomePageInnerRecyclerBooksAdapter.ViewHolder>  {
    private ArrayList bookList;
    private Context context;

    public HomePageInnerRecyclerBooksAdapter(ArrayList bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText((String)bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTitle,txtYear;
        ImageView imgBook;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txt_book_title);
            txtYear=itemView.findViewById(R.id.txt_year);
            imgBook=itemView.findViewById(R.id.book_image);
        }
    }
}
