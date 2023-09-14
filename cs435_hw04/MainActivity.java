package com.example.cs435_hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewScreen;
    TextView textViewShowOperand;
    double termOne;
    double termTwo;
    boolean opSet;
    double result;
    boolean newTerm = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewScreen = findViewById(R.id.textViewScreen);
        textViewShowOperand = findViewById(R.id.textViewOperation);
    }

    private void appendNumber(int i) {
        if(newTerm) {
            textViewScreen.append(Integer.toString(i));
        }
    }

    public void onClickSeven(View view) {
        appendNumber(7);
    }

    public void onClick8(View view) {
        appendNumber(8);
    }

    public void onClick9(View view) {
        appendNumber(9);
    }

    public void onClick4(View view) {
        appendNumber(4);
    }

    public void onClick5(View view) {
        appendNumber(5);
    }

    public void onClick6(View view) {
        appendNumber(6);
    }

    public void onClick1(View view) {
        appendNumber(1);
    }

    public void onClick2(View view) {
        appendNumber(2);
    }

    public void onClick3(View view) {
        appendNumber(3);
    }

    public void onClickZero(View view) {
        appendNumber(0);
    }

    public void onClickDot(View view) {
        String currentTerm = String.valueOf(textViewScreen.getText());
        if (!currentTerm.contains(".")){
            textViewScreen.append(".");
        }
    }

    public void onClickPlus(View view) {
        String t1 = String.valueOf(textViewScreen.getText());
        if(!opSet && !t1.equals("") && !t1.equals(".")){
            opSet = true;
            setFirstTerm();
            textViewScreen.setText("");
            textViewShowOperand.setText("+");
            newTerm = true;
        }
    }

    private void setFirstTerm() {
        String t1 = String.valueOf(textViewScreen.getText());
        if(!t1.equals("") && !t1.equals(".")){
            termOne = Double.parseDouble(t1);
            Log.v("TERM", String.valueOf(termOne));
        }
    }

    public void onClickEquals(View view) {
        String t2 = String.valueOf(textViewScreen.getText());
        if((opSet!=false) && (!t2.equals("")) && (!t2.equals("."))){
            String operandType = String.valueOf(textViewShowOperand.getText());
            termTwo = Double.parseDouble(t2);
            textViewShowOperand.setText("=");
            if (operandType.equals("+")){
                result = termOne + termTwo;
            }
            if (operandType.equals("-")){
                result = termOne - termTwo;
            }
            if (operandType.equals("*")){
                result = termOne * termTwo;
            }
            if (operandType.equals("%")){
                result = termOne % termTwo;
            }
            if (operandType.equals("/")){
                if(termTwo != 0) {
                    result = termOne / termTwo;
                }
                else{
                    result = Double.NaN;
                }
            }
            if (operandType.equals("^")){
                result = 1;
                for (int i = 0; i < termTwo; i++){
                    result *= termOne;
                }
            }
            textViewScreen.setText(String.valueOf(result));
            termOne = termTwo;
            opSet = false;
            newTerm = false;
        }
    }

    public void onClickMinus(View view) {
        String t1 = String.valueOf(textViewScreen.getText());
        if(!opSet && !t1.equals("") && !t1.equals(".")){
            opSet = true;
            setFirstTerm();
            textViewScreen.setText("");
            textViewShowOperand.setText("-");
            newTerm = true;
        }
    }

    public void onClickTimes(View view) {
        String t1 = String.valueOf(textViewScreen.getText());
        if(!opSet && !t1.equals("") && !t1.equals(".")){
            opSet = true;
            setFirstTerm();
            textViewScreen.setText("");
            textViewShowOperand.setText("*");
            newTerm = true;
        }
    }

    public void onClickMod(View view) {
        String t1 = String.valueOf(textViewScreen.getText());
        if(!opSet && !t1.equals("") && !t1.equals(".")){
            opSet = true;
            setFirstTerm();
            textViewScreen.setText("");
            textViewShowOperand.setText("%");
            newTerm = true;
        }
    }

    public void onClickExponent(View view) {
        String t1 = String.valueOf(textViewScreen.getText());
        if(!opSet && !t1.equals("") && !t1.equals(".")){
            opSet = true;
            setFirstTerm();
            textViewScreen.setText("");
            textViewShowOperand.setText("^");
            newTerm = true;
        }
    }

    public void onClickClear(View view) {
        opSet = false;
        textViewScreen.setText("");
        textViewShowOperand.setText("");
        newTerm = true;
    }

    public void onClickSign(View view) {
        String checkBlank = String.valueOf(textViewScreen.getText());
        if(checkBlank.equals("")){
            textViewScreen.append("-");
        }
        if(checkBlank.equals("-")){
            textViewScreen.setText("");
        }
    }

    public void onClickDivide(View view) {
        String t1 = String.valueOf(textViewScreen.getText());
        if(!opSet && !t1.equals("")&& !t1.equals(".")){
            opSet = true;
            setFirstTerm();
            textViewScreen.setText("");
            textViewShowOperand.setText("/");
            newTerm = true;
        }
    }
}