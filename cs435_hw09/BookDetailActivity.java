package com.example.cs435_hw09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetailActivity extends AppCompatActivity {

    ImageView imageViewDetail;
    TextView textViewTitleDetail;
    TextView textViewAuthorDetail;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        textViewAuthorDetail = findViewById(R.id.textViewAuthorDetail);
        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        imageViewDetail = findViewById(R.id.imageViewDetail);

        int index = getIntent().getIntExtra("position", 0);

        book = Model.books.get(index);

        textViewTitleDetail.setText(book.bookName);
        textViewAuthorDetail.setText(book.author);
        imageViewDetail.setImageResource(book.imageId);
    }

    public void clickAddCart(View view) {
        Model.booksInCart.add(book.bookName);
        finish();
    }
}