package com.example.cs435_hw11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static TextView textViewNumcolors;
    static ColorDBHelper colorDBHelper;
    static ColorAdapter colorAdapter;
    static ProgressBar progressBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add){
            /*
            MyColor myColor = new MyColor();
            int red = myColor.red;
            int blue = myColor.blue;
            int green = myColor.green;
            boolean fav = myColor.favorite;
            colorDBHelper.insert(red,blue,green,fav);
            colorAdapter.notifyDataSetChanged();
             */
            AddDialogFragment addDialogFragment = new AddDialogFragment();
            addDialogFragment.show(getSupportFragmentManager(),"ADD");
            return true;
        }
        if(id == R.id.sort){
            SortFragment sortFragment = new SortFragment();
            sortFragment.show(getSupportFragmentManager(),"SORT");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewHome);
        textViewNumcolors = findViewById(R.id.textViewNumColors);
        progressBar = findViewById(R.id.progressBar);

        colorDBHelper = new ColorDBHelper(getApplicationContext());
        colorDBHelper.getReadableDatabase();

        colorAdapter = new ColorAdapter(colorDBHelper);

        recyclerView.setAdapter(colorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateNumberColors();
    }

    public static void updateNumberColors(){
        textViewNumcolors.setText(String.valueOf(colorDBHelper.getCount()));
    }

}