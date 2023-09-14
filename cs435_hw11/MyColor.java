package com.example.cs435_hw11;

import java.util.Random;

public class MyColor {
    public int red;
    public int blue;
    public int green;
    public boolean favorite;

    MyColor(){
        Random random = new Random();
        this.red = random.nextInt(256);
        this.blue = random.nextInt(256);
        this.green = random.nextInt(256);
        this.favorite = false;
    }
}
