package com.example.cs435_hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.ToggleButton;

public class TeaCategoryActivity extends AppCompatActivity {

    private ListView listViewTea;
    private Switch switchDelTea;
    ArrayAdapter<Tea> adapter;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_category);

        adapter = new ArrayAdapter<Tea>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Tea.teas
        );

        listViewTea = findViewById(R.id.listViewTea);
        listViewTea.setAdapter(adapter);

        listViewTea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switchDelTea = findViewById(R.id.switchDelTea);
                if (switchDelTea.isChecked()){
                    Log.v("here", String.valueOf(i));
                    Tea.teas.remove(i);
                    adapter.notifyDataSetChanged();
                }
                else {
                    int index = i;
                    Intent intentTeaDetail = new Intent(getApplicationContext(), teaDetailActivity.class);
                    intentTeaDetail.putExtra("index", index);
                    startActivity(intentTeaDetail);
                }
            }
        });
    }
}