package com.example.cs435_hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    TextView textViewNameDetail;
    TextView textViewDescDetail;
    Spinner spinnerDetail;
    int index;
    Task task;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String [] options = getResources().getStringArray(R.array.spinnerPriorities);
        int index = spinnerDetail.getSelectedItemPosition();
        task.priority = options[index];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        textViewNameDetail = findViewById(R.id.textViewNameDetail);
        textViewDescDetail = findViewById(R.id.textViewDescDetail);
        spinnerDetail = findViewById(R.id.spinnerPrioDetail);

        index = getIntent().getIntExtra("position",0);
        task = Model.tasks.get(index);

        textViewNameDetail.setText(task.taskName);
        textViewDescDetail.setText(task.description);
        spinnerDetail.setSelection(Integer.parseInt(task.priority)-1);

    }

    public void onClickDelete(View view) {
        Model.tasks.remove(task);
        finish();
    }
}