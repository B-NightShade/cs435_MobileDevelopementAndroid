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

public class AnimalCategoryActivity extends AppCompatActivity {

    private ListView listViewAnimals;
    private Switch switchAnimals;
    ArrayAdapter<Animal>adapter;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_category);


        adapter = new ArrayAdapter<Animal>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Animal.Animals
        );

        listViewAnimals = findViewById(R.id.listViewAnimals);
        listViewAnimals.setAdapter(adapter);

        listViewAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switchAnimals = findViewById(R.id.switchDeleteAnimal);
                if (switchAnimals.isChecked()){
                    Log.v("here", String.valueOf(i));
                    Animal.Animals.remove(i);
                    adapter.notifyDataSetChanged();
                }
                else {
                    int index = i;
                    Intent intentAnimalDetail = new Intent(getApplicationContext(), animalDetailActivity.class);
                    intentAnimalDetail.putExtra("index", index);
                    startActivity(intentAnimalDetail);
                }
            }
        });

    }
}