package com.example.cs435_hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class waterDetailActivity extends AppCompatActivity {

    private ImageView imageViewWater;
    private TextView textViewWaterName;
    private int indexOfDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_detail);

        imageViewWater = findViewById(R.id.imageViewWater);
        textViewWaterName = findViewById(R.id.textViewWater);
        int index = getIntent().getIntExtra("index", 0);
        indexOfDetail =index;
        Water water = Water.waters.get(index);

        imageViewWater.setImageResource(water.getImageResourceId());
        textViewWaterName.setText(water.getName());
    }

    public void onClickDelWater(View view) {
        Water.waters.remove(indexOfDetail);
        finish();
    }
}