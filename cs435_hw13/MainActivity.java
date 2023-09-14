package com.example.cs435_hw13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
                                                                NasaAdapter.NasaAdapterListener{
    static ProgressBar progressBarHome;
    RecyclerView recyclerViewHome;
    static String datePicked;
    private Handler mainHandler;
    private MyHandlerThread myHandlerThread;
    NasaSQLiteHelper nasaSQLiteHelper;
    NasaAdapter nasaAdapter;
    static int year;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarHome = findViewById(R.id.progressBarMain);
        progressBarHome.setVisibility(View.INVISIBLE);

        recyclerViewHome = findViewById(R.id.recyclerViewHome);

        nasaSQLiteHelper = new NasaSQLiteHelper(getApplicationContext());
        nasaSQLiteHelper.getReadableDatabase();

        nasaAdapter = new NasaAdapter(nasaSQLiteHelper, this);
        recyclerViewHome.setAdapter(nasaAdapter);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        mainHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.v("here", "hey");
                nasaAdapter.notifyDataSetChanged();
                if (!myHandlerThread.isAlive()){
                    progressBarHome.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    public void clickSelectDate(View view) {
        DateFragment dateFragment = new DateFragment(this);
        dateFragment.show(getSupportFragmentManager(),"DATE");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int month = i1+1;
        int day = i2;
        year = i;

        /*
        Calendar calendar = Calendar.getInstance();
        int currMonth = calendar.get(Calendar.MONTH)+1;
        int currDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currYear = calendar.get(Calendar.YEAR);

        if(year == currYear && day > currDay && month == currMonth){
            afterCurrent = 1;
        }
        if(year == currYear && month > currMonth){
            afterCurrent = 1;
        }
        if(year > currYear){
            afterCurrent = 1;
        }
         */
        datePicked =month + "-" +day;

        Log.v("here", datePicked);


        myHandlerThread = new MyHandlerThread(getApplicationContext(), mainHandler);
        progressBarHome.setVisibility(View.VISIBLE);
        myHandlerThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandlerThread.quit();
    }

    @Override
    public void click(int position) {
        Intent intent = new Intent(getApplicationContext(), NasaDetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}