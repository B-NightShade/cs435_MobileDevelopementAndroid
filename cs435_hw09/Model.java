package com.example.cs435_hw09;

import java.util.ArrayList;

public class Model {
    public static ArrayList<Book>books;
    public static ArrayList<String>booksInCart;

    static{
        books = new ArrayList<Book>();
        books.add(new Book("Catch 22", "Joseph Heller", R.drawable.catch22));
        books.add(new Book("Catcher in the Rye", "J.D. Sallinger", R.drawable.catcher_in_the_rye));
        books.add(new Book("Life of Pi", "Yann Martel", R.drawable.life_of_pi));
        books.add(new Book("The Scarlet Letter", "Nathaniel Hawthorne", R.drawable.scarlett_letter));
        books.add(new Book("To Kill a Moking Bird", "Harper Lee", R.drawable.to_kill_a_mockingbird));
    }

    static{
        booksInCart = new ArrayList<>();
    }
}
