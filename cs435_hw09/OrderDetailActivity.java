package com.example.cs435_hw09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    TextView textViewOrderDetail;
    ListView listViewOrder;
    ArrayAdapter adapter;
    String date;
    String time;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Model.booksInCart.clear();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewOrderDetail = findViewById(R.id.textViewOrder);
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        String orderTime = String.format("Delivery set for %s at %s", date, time);
        textViewOrderDetail.setText(orderTime);

        listViewOrder = findViewById(R.id.listViewOrder);

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Model.booksInCart
        );

        listViewOrder.setAdapter(adapter);
    }
}