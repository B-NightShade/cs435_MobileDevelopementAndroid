package com.example.cs435_hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class teaDetailActivity extends AppCompatActivity {

    private ImageView imageViewTea;
    private TextView textViewTeaName;
    private int indexOfDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_detail);

        imageViewTea = findViewById(R.id.imageViewTea);
        textViewTeaName = findViewById(R.id.textViewTea);

        int index = getIntent().getIntExtra("index", 0);
        indexOfDetail = index;
        Tea tea = Tea.teas.get(index);

        imageViewTea.setImageResource(tea.getImageResourceId());
        textViewTeaName.setText(tea.getName());
    }

    public void onClickDelTea(View view) {
        Tea.teas.remove(indexOfDetail);
        finish();
    }
}