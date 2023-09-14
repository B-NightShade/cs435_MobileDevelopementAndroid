package com.example.cs435_hw09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

public class CartDetailActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    ListView listViewCart;
    ArrayAdapter<String>adapter;
    String date;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);

        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Model.booksInCart
        );

        listViewCart = findViewById(R.id.listViewCart);
        listViewCart.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.deleteCartItems){
            AlertDialog.Builder builder = new AlertDialog.Builder(CartDetailActivity.this);
            builder.setTitle("REMOVE ALL ITEMS FROM CART");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Model.booksInCart.clear();
                    adapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickSetDate(View view) {
        DateFragment dateFragment = new DateFragment(this);
        dateFragment.show(getSupportFragmentManager(),"DATE");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        i1 = i1 +1;
        date = String.format("%d/%d/%d", i1, i2,i);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        time = String.format("%d:%02d", i, i1);
    }

    public void clickSetTime(View view) {
        TimeFragment timeFragment = new TimeFragment(this);
        timeFragment.show(getSupportFragmentManager(),"TIME");
    }

    public void clickSubmitOrder(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CartDetailActivity.this);
        String order = String.format("Set Delivery for %s at %s", date, time);
        builder.setTitle(order);
        builder.setMessage("Continue?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

}