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

public class waterCategoryActivity extends AppCompatActivity {

    private ListView listViewWater;
    private Switch SwitchDelWater;
    ArrayAdapter<Water> adapter;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_category);

        adapter = new ArrayAdapter<Water>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Water.waters
        );

        listViewWater = findViewById(R.id.listViewWater);
        listViewWater.setAdapter(adapter);

        listViewWater.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SwitchDelWater = findViewById(R.id.switchWater);
                if (SwitchDelWater.isChecked()){
                    Log.v("here", String.valueOf(i));
                    Water.waters.remove(i);
                    adapter.notifyDataSetChanged();
                }
                else {
                    int index = i;
                    Intent intentWaterDetail = new Intent(getApplicationContext(), waterDetailActivity.class);
                    intentWaterDetail.putExtra("index", index);
                    startActivity(intentWaterDetail);
                }
            }
        });
    }
}