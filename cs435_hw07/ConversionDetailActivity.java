package com.example.cs435_hw07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ConversionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_detail);

        ConversionDetailFragment conversionDetailFragment =
                (ConversionDetailFragment)  getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerConversionDetail);

        int conversionId = (int)getIntent().getExtras().get("id");
        conversionDetailFragment.setConversionId(conversionId);
    }

}