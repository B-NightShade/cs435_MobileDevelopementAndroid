package com.example.cs435_hw13;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class NasaAdapter extends RecyclerView.Adapter{

    NasaSQLiteHelper nasaSQLiteHelper;
    NasaAdapterListener nasaAdapterListener;

    public interface NasaAdapterListener{
        public void click(int position);
    }

    NasaAdapter(NasaSQLiteHelper nasaSQLiteHelper, NasaAdapterListener nasaAdapterListener){
        this.nasaSQLiteHelper = nasaSQLiteHelper;
        this.nasaAdapterListener = nasaAdapterListener;
    }


    class NasaViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewDate;
        ImageView imageView;

        public NasaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            imageView = itemView.findViewById(R.id.imageViewHome);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    nasaAdapterListener.click(position);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_nasa_layout, parent, false);
        return new NasaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String title = nasaSQLiteHelper.getTitle(position);
        String date = nasaSQLiteHelper.getDate(position);
        Bitmap imagePath = nasaSQLiteHelper.getImgPath(position);

        NasaViewHolder nasaViewHolder = (NasaViewHolder) holder;
        nasaViewHolder.textViewTitle.setText(title);
        nasaViewHolder.textViewDate.setText(date);
        nasaViewHolder.imageView.setImageBitmap(imagePath);

    }

    @Override
    public int getItemCount() {
        return nasaSQLiteHelper.getCount();
    }
}
