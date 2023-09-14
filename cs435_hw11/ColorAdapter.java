package com.example.cs435_hw11;

import android.app.appsearch.AppSearchSchema;
import android.content.ClipData;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ColorAdapter extends RecyclerView.Adapter{

    ColorDBHelper colorDBHelper;


    ColorAdapter(ColorDBHelper colorDBHelper){
        this.colorDBHelper = colorDBHelper;
    }



    class colorViewHolder extends RecyclerView.ViewHolder{
        TextView textViewSwatch;
        TextView textViewRGB;
        CheckBox checkBoxFav;

        public colorViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSwatch = itemView.findViewById(R.id.textViewSwatch);
            textViewRGB = itemView.findViewById(R.id.textViewRGB);
            checkBoxFav = itemView.findViewById(R.id.checkBoxFavorite);

            checkBoxFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean checked = checkBoxFav.isChecked();
                    int position = getAdapterPosition();
                    colorDBHelper.update(checked,position);
                }
            });
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_layout,parent,false);
        return new colorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int red = colorDBHelper.getRed(position);
        int blue = colorDBHelper.getBlue(position);
        int green = colorDBHelper.getGreen(position);
        boolean favorite = colorDBHelper.getFav(position);
        Log.v("help", String.valueOf(favorite));
        colorViewHolder colorViewHolder = (ColorAdapter.colorViewHolder) holder;
        String res = String.format("%s, %s, %s", String.valueOf(red), String.valueOf(green), String.valueOf(blue));
        colorViewHolder.textViewRGB.setText(res);
        colorViewHolder.textViewSwatch.setBackgroundColor(Color.rgb(red, green,blue));
        if (favorite){
            colorViewHolder.checkBoxFav.setChecked(true);
        }else{
            colorViewHolder.checkBoxFav.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return colorDBHelper.getCount();
    }
}
