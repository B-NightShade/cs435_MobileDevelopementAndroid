package com.example.cs435_hw05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskAdapterListener {

    RecyclerView recyclerView;
    String nameMain;
    String descMain;
    String prioMain;

    public static final int RESULT_CODE =42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecyclerViewTasks);
        recyclerView.setAdapter(new TaskAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void onClickAdd(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateTaskActivity.class);
        startActivityForResult(intent, RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == this.RESULT_CODE){
             nameMain = data.getStringExtra("name");
             descMain = data.getStringExtra("desc");
             prioMain = data.getStringExtra("prio");
             Model.tasks.add(new Task(nameMain, descMain, prioMain));
             recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void clickDetail(int position){
        Intent intent = new Intent(getApplicationContext(), TaskDetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}