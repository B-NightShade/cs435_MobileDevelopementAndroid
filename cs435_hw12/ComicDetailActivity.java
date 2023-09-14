package com.example.cs435_hw12;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class ComicDetailActivity extends AppCompatActivity {

    TextView textViewTitleDetail;
    TextView textViewNumDetail;
    WebView webViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        textViewNumDetail = findViewById(R.id.textViewNumDetail);
        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        webViewDetail = findViewById(R.id.webViewDetail);

        int id = getIntent().getIntExtra("ID", 0);

        ComicDatabaseHelper comicDatabaseHelper = new ComicDatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = comicDatabaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("comics",
                new String[]{"_id", "number", "title", "cartoon"},
                "_id = ?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor.moveToFirst()){
            textViewTitleDetail.setText(cursor.getString(2));
            webViewDetail.loadUrl(cursor.getString(3));
            textViewNumDetail.setText(String.valueOf(cursor.getInt(1)));
        }
    }
}