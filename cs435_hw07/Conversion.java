package com.example.cs435_hw07;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

public class Conversion {
    private String name;
    private String top;
    private String bottom;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static final Conversion[] conversions ={
           new Conversion("Fahrenheit to Celsius", "Fahrenheit", "Celsius"),
           new Conversion("Miles to Kilometers", "Miles", "Kilometers"),
            new Conversion("Yards to Meters", "Yards", "Meters"),
            new Conversion("Gallons to Liters", "Gallons", "Liters")
    };

    public Conversion(String name, String top, String bottom){
        this.name = name;
        this.top = top;
        this.bottom = bottom;
    }

    public String getName(){
        return name;
    }
    public String getTop(){
        return top;
    }
    public String getBottom(){return bottom; }

    public static String result(int id, float input){
        float result = 0;
        String resultingVal = "";
        if(id == 0){
            result = Float.parseFloat(df.format((input - 32)*5/9));
        }
        if(id == 1) {
            result = Float.parseFloat(df.format(input * 1.609));
        }
        if(id ==2) {
            result = (float) ((input *3*12*2.54)/100);
            result = Float.parseFloat(df.format(result));
        }
        if(id ==3){
            result = Float.parseFloat(df.format(input * 3.78541178));
        }
        resultingVal = String.format("%.2f", result);
        return resultingVal;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
