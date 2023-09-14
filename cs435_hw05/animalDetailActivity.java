package com.example.cs435_hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class animalDetailActivity extends AppCompatActivity {

    private ImageView imageViewAnimal;
    private TextView textViewAnimalName;
    private int indexOfDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        imageViewAnimal = findViewById(R.id.imageViewAnimal);
        textViewAnimalName = findViewById(R.id.textViewAnimal);

        int index = getIntent().getIntExtra("index", 0);
        indexOfDetail = index;
        Animal animal = Animal.Animals.get(index);

        imageViewAnimal.setImageResource(animal.getImageResourceId());
        textViewAnimalName.setText(animal.getName());
    }

    public void OnClickDelAnimal(View view) {
        Animal.Animals.remove(indexOfDetail);
        finish();
    }
}