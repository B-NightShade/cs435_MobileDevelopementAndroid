package com.example.exam2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity  implements IntegerAdapter.IntegerAdapterListener{

    static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewHome);

        recyclerView.setAdapter(new IntegerAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear){
            Model.numbers.clear();
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        if (id == R.id.add){
            Random random = new Random();
            int integer = random.nextInt(101);
            Model.numbers.add(integer);
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public void clicked(int position) {
        int original = Model.numbers.get(position);
        original = original * -1;
        Model.numbers.set(position, original);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}