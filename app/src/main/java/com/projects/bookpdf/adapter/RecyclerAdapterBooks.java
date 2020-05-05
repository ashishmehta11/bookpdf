package com.projects.bookpdf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projects.bookpdf.R;
import com.projects.bookpdf.data.Book;
import com.projects.bookpdf.data.ObjectCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RecyclerAdapterBooks extends RecyclerView.Adapter<RecyclerAdapterBooks.ViewHolder> {
    public static boolean morePagesLoaded;
    private Context context;
    private HashMap<Integer, ArrayList<Book>> bookList;
    private String currentCategory;
    private String currentSubCategory;
    private FragmentActivity activity;

    public RecyclerAdapterBooks(Context context, HashMap<Integer, ArrayList<Book>> bookList, String currentCategory, String currentSubCategory, FragmentActivity activity) {
        this.context = context;
        this.bookList = bookList;
        this.currentCategory = currentCategory;
        this.currentSubCategory = currentSubCategory;
        this.activity = activity;
        morePagesLoaded = true;
    }

    public void setMorePages()
    {
        ArrayList<Book> tmpCollection;
        ArrayList<Book> tmpList=new ArrayList<>();
        if(currentSubCategory==null)
            tmpCollection = Objects.requireNonNull(ObjectCollection.category.get(currentCategory)).getBooks();
        else
            tmpCollection = Objects.requireNonNull(ObjectCollection.category.get(currentCategory)).getSubCategory().get(currentSubCategory).getBooks();
        int noOfBooks=0;
        for(Map.Entry<Integer,ArrayList<Book>> entry :bookList.entrySet())
        {
            noOfBooks+=entry.getValue().size();
        }
        if(bookList.get(bookList.size()-1).size()==1)
        {
            if(tmpCollection.size()>noOfBooks) {
                bookList.get(bookList.size()-1).add(tmpCollection.get(noOfBooks));
                noOfBooks++;
            }
        }
        int k=bookList.size();
        for(;noOfBooks<tmpCollection.size();noOfBooks++)
        {
            tmpList.add(tmpCollection.get(noOfBooks));
            if(tmpList.size()==2||noOfBooks==tmpCollection.size()-1)
            {
                bookList.put(k,tmpList);
                k++;
                tmpList=new ArrayList<>();
            }
        }
        notifyDataSetChanged();
        morePagesLoaded=true;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_books_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position >= bookList.size() - 3 && morePagesLoaded) {
            if (currentSubCategory == null) {
                if (ObjectCollection.category.get(currentCategory).getTotalLoadedPage() + 1
                        <=
                        ObjectCollection.category.get(currentCategory).getTotalPage()) {
                    morePagesLoaded = false;
                    ObjectCollection.loadMorePagesForCategory(currentCategory, activity);
                }
            } else {
                if (ObjectCollection.category.get(currentCategory).getSubCategory().get(currentSubCategory).getTotalLoadedPage() + 1
                        <=
                        ObjectCollection.category.get(currentCategory).getSubCategory().get(currentSubCategory).getTotalPage()) {
                    morePagesLoaded = false;
                    ObjectCollection.loadMorePagesForCategory(currentCategory, currentSubCategory, activity);
                }
            }
        }
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) holder.cardLeft.getLayoutParams();
        if (position == bookList.size() - 1)
            params.bottomMargin = 184;
        else
            params.bottomMargin = 16;
        holder.cardLeft.setLayoutParams(params);

        if (bookList.get(position).size() == 1) {
            holder.cardRight.setVisibility(View.GONE);
            holder.txtTitleLeft.setText(bookList.get(position).get(0).getBookName());
            Glide.with(context)
                    .load(bookList.get(position).get(0).getBookImageURL())
                    .into(holder.imgBookLeft);
            if (bookList.get(position).get(0).getBookYear().length() <= 0)
                holder.txtYearLeft.setText("NA");
            else
                holder.txtYearLeft.setText(bookList.get(position).get(0).getBookYear());
            holder.cardLeft.setOnClickListener(v ->{

            });
        } else if (bookList.get(position).size() == 2) {
            holder.txtTitleLeft.setText(bookList.get(position).get(0).getBookName());
            Glide.with(context)
                    .load(bookList.get(position).get(0).getBookImageURL())
                    .into(holder.imgBookLeft);
            if (bookList.get(position).get(0).getBookYear().length() <= 0)
                holder.txtYearLeft.setText("NA");
            else
                holder.txtYearLeft.setText(bookList.get(position).get(0).getBookYear());
            holder.cardLeft.setOnClickListener(v -> {

            });

            holder.txtTitleRight.setText(bookList.get(position).get(1).getBookName());
            Glide.with(context)
                    .load(bookList.get(position).get(1).getBookImageURL())
                    .into(holder.imgBookRight);
            if (bookList.get(position).get(1).getBookYear().length() <= 0)
                holder.txtYearRight.setText("NA");
            else
                holder.txtYearRight.setText(bookList.get(position).get(1).getBookYear());
            holder.cardRight.setOnClickListener( v ->{

            });
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardLeft, cardRight;
        TextView txtTitleLeft, txtTitleRight;
        TextView txtYearLeft, txtYearRight;
        ImageView imgBookLeft, imgBookRight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardLeft = itemView.findViewById(R.id.card_view_left);
            cardRight = itemView.findViewById(R.id.card_view_right);
            txtTitleLeft = itemView.findViewById(R.id.txt_title_left);
            txtTitleRight = itemView.findViewById(R.id.txt_title_right);
            txtYearLeft = itemView.findViewById(R.id.txt_year_left);
            txtYearRight = itemView.findViewById(R.id.txt_year_right);
            imgBookLeft = itemView.findViewById(R.id.img_book_cover_left);
            imgBookRight = itemView.findViewById(R.id.img_book_cover_right);
        }
    }
}
