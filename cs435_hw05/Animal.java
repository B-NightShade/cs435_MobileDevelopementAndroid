package com.example.cs435_hw05;

import java.util.ArrayList;

public class Animal {
    private String name;
    private int imageResourceId;
    static ArrayList<Animal> Animals;

    Animal(String name, int imageResourceId){
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
        Animals = new ArrayList<Animal>();
        Animals.add(new Animal("French Bulldog", R.drawable.dog));
        Animals.add(new Animal("elephant", R.drawable.elephant));
        Animals.add(new Animal("snow leopard", R.drawable.snowleopard));
    }
}
