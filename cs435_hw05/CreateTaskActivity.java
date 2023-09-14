package com.example.cs435_hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }

    public void onClickSubmit(View view) {
        EditText editTextName = findViewById(R.id.editTextTaskName);
        EditText editTextDesc = findViewById(R.id.editTextTaskDesc);
        Spinner spinnerPrio = findViewById(R.id.spinnerPriorities);
        String [] options = getResources().getStringArray(R.array.spinnerPriorities);
        int index = spinnerPrio.getSelectedItemPosition();
        Intent intent = new Intent();
        intent.putExtra("name", editTextName.getText().toString());
        intent.putExtra("desc", editTextDesc.getText().toString());
        intent.putExtra("prio",options[index]);

        setResult(MainActivity.RESULT_CODE, intent);
        finish();
    }

}