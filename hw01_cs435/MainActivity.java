package com.example.hw01_cs435;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textviewResponse);
    }

    public void giveAnswerClick(View view) {
        String possibleRes[] = getResources().getStringArray(R.array.responses);
        int ranIndex = (int) Math.floor(Math.random()*(19-0+1)+0);
        String answer = possibleRes[ranIndex];
        textViewResult.setText(answer);
    }
}