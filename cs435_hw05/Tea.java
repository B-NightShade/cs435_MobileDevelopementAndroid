package com.example.cs435_hw05;

import java.util.ArrayList;

public class Tea {
    private String name;
    private int imageResourceId;
    static ArrayList<Tea> teas;

    Tea(String name, int imageResourceId){
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
        teas = new ArrayList<Tea>();
        teas.add(new Tea("Peppermint", R.drawable.peppermint));
        teas.add(new Tea("Chamomile", R.drawable.chamomile));
        teas.add( new Tea("Earl Grey", R.drawable.earlgrey));
    }
}
