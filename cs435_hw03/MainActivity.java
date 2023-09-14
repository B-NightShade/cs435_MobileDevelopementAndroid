package com.example.cs435_hw03;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView wrong;
    TextView right;
    TextView question;
    Button red;
    Button blue;
    Button green;
    String [] colors = {"RED", "BLUE", "GREEN"};
    String [] words = {"red", "blue", "green"};
    int color;
    int rightGuess;
    int wrongGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            wrongGuess = savedInstanceState.getInt("num wrong");
            rightGuess = savedInstanceState.getInt("num right");
        }

        wrong = findViewById(R.id.textViewWrong);
        right = findViewById(R.id.textViewRight);
        question = findViewById(R.id.textViewQuestion);
        red = findViewById(R.id.buttonRed);
        red.setBackgroundColor(Color.RED);
        blue = findViewById(R.id.buttonBlue);
        blue.setBackgroundColor(Color.BLUE);
        green = findViewById(R.id.buttonGreen);
        green.setBackgroundColor(Color.GREEN);

        wrong.setText("Wrong \n" + (wrongGuess));
        right.setText("Right \n" + (rightGuess));
        setQuestionText();
    }

    void setQuestionText(){
        int random_int_word = (int)Math.floor(Math.random()*(2+1)+0);
        question.setText(words[random_int_word]);
        int random_int_color = (int)Math.floor(Math.random()*(2+1)+0);
        while(random_int_color == random_int_word){
            random_int_color = (int)Math.floor(Math.random()*(2+1)+0);
        }
        String colorName = colors[random_int_color];
        if (Objects.equals(colorName, "RED")){
            color = Color.RED;
        }
        if (Objects.equals(colorName, "BLUE")){
            color = Color.BLUE;
        }
        if (Objects.equals(colorName, "GREEN")){
            color = Color.GREEN;
        }
        question.setTextColor(color);
    }

    public void onClickRed(View view) {
        if (color == Color.RED){
            rightGuess ++;
            right.setText("Right \n" + (rightGuess));
            setQuestionText();
        }
        else{
            wrongGuess++;
            wrong.setText("Wrong \n" + (wrongGuess));
        }
    }

    public void onClickGreen(View view) {
        if (color == Color.GREEN){
            rightGuess ++;
            right.setText("Right \n" + (rightGuess));
            setQuestionText();
        }
        else{
            wrongGuess++;
            wrong.setText("Wrong \n" + (wrongGuess));
        }
    }

    public void onClickBlue(View view) {
        if (color == Color.BLUE){
            rightGuess ++;
            right.setText("Right \n" + (rightGuess));
            setQuestionText();
        }
        else{
            wrongGuess++;
            wrong.setText("Wrong \n" + (wrongGuess));
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        right.setText("Right \n" + (rightGuess));
        wrong.setText("Wrong \n" + (wrongGuess));
    }

    @Override
    protected void onPause() {
        super.onPause();
        right.setText("Right \n 0");
        wrong.setText("Wrong \n 0");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num wrong", wrongGuess);
        outState.putInt("num right", rightGuess);
    }
}
