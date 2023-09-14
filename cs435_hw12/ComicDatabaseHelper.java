package com.example.cs435_hw12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ComicDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "comics.sqlite";
    private static final int DB_VERSION = 1;

    public ComicDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE comics (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "number INTEGER,"
                + "title TEXT,"
                + "cartoon TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    void insert(String title, String image, int number){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", number);
        contentValues.put("title", title);
        contentValues.put("cartoon", image);
        sqLiteDatabase.insert("comics", null, contentValues);
    }

    int getCount(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM comics", null);
        return cursor.getCount();
    }

    public String getImage(int position){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("comics",
                new String[]{"_id", "number", "title", "cartoon"},
                null, null, null, null, null, null);
        if(cursor.moveToPosition(position)){
            String image = cursor.getString(3);
            return image;
        }
        return null;
    }

    public String getTitle(int position){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("comics",
                new String[]{"_id", "number", "title", "cartoon"},
                null, null, null, null, null, null);
        if(cursor.moveToPosition(position)){
            String title = cursor.getString(2);
            return title;
        }
        return null;
    }

    public int getNumber(int position){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("comics",
                new String[]{"_id", "number", "title", "cartoon"},
                null, null, null, null, null, null);
        if(cursor.moveToPosition(position)){
            int number = cursor.getInt(1);
            return number;
        }
        return 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int getId(int position) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("comics",
                new String[]{"_id", "number", "title", "cartoon"},
                null, null, null, null, null, null);
        cursor.moveToPosition(position);
        int id = cursor.getInt(0);
        return id;
    }
}
