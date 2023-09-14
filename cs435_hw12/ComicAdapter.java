package com.example.cs435_hw12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComicAdapter extends RecyclerView.Adapter{

    ItemAdapterListener itemAdapterListener;

    public interface ItemAdapterListener{
        public void click(int position);
    }


    ComicDatabaseHelper comicDatabaseHelper;
    ComicAdapter(ComicDatabaseHelper comicDatabaseHelper,ItemAdapterListener itemAdapterListener){
        this.comicDatabaseHelper = comicDatabaseHelper;
        this.itemAdapterListener = itemAdapterListener;
    }

    class ComicViewHolder extends RecyclerView.ViewHolder{
        WebView webViewImage;
        TextView textViewTitle;
        TextView textViewNumber;
        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            webViewImage = itemView.findViewById(R.id.webViewImg);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    itemAdapterListener.click(position);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_comic_layout, parent,false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String image = comicDatabaseHelper.getImage(position);
        String title = comicDatabaseHelper.getTitle(position);
        int number = comicDatabaseHelper.getNumber(position);

        ComicViewHolder comicViewHolder = (ComicViewHolder) holder;
        comicViewHolder.webViewImage.loadUrl(image);
        comicViewHolder.textViewNumber.setText(String.valueOf(number));
        comicViewHolder.textViewTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return comicDatabaseHelper.getCount();
    }
}
