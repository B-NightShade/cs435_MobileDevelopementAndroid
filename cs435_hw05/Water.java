package com.example.cs435_hw05;

import java.util.ArrayList;

public class Water {
    private String name;
    private int imageResourceId;
    static ArrayList <Water>  waters;


    Water(String name, int imageResourceId){
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName(){
        return name;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

    @Override
    public String toString() {
        return this.name;
    }

    static {
        waters = new ArrayList<Water>();
        waters.add(new Water("La Croix", R.drawable.lacroix));
        waters.add(new Water("Bubly", R.drawable.bubly));
        waters.add(new Water("AHA", R.drawable.aha));
    }
}
