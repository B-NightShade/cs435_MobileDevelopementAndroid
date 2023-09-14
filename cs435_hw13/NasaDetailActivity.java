package com.example.cs435_hw13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NasaDetailActivity extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewDate;
    ImageView imageViewDetail;
    NasaSQLiteHelper nasaSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_detail);

        textViewTitle = findViewById(R.id.textViewTitleDetail);
        textViewDate = findViewById(R.id.textViewDateDetail);
        imageViewDetail = findViewById(R.id.imageViewDetail);

        nasaSQLiteHelper = new NasaSQLiteHelper(getApplicationContext());

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        String title = nasaSQLiteHelper.getTitle(position);
        String date = nasaSQLiteHelper.getDate(position);
        Bitmap image = nasaSQLiteHelper.getImgPath(position);

        textViewTitle.setText(title);
        textViewDate.setText(date);
        imageViewDetail.setImageBitmap(image);
    }
}