package com.example.cs435_hw11;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ColorDBHelper extends SQLiteOpenHelper {
    private  static final String DB_NAME = "colors.sqlite";
    private  static final int DB_VERSION =1;
    static boolean addGroupBy;
    static int sort;

    public ColorDBHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE colors (_id INTEGER PRIMARY KEY AUTOINCREMENT, red INTEGER, blue INTEGER, green INTEGER, favorite NUMERIC);";
        sqLiteDatabase.execSQL(create);
    }

    void insert(int red, int blue, int green, boolean fav){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("red", red);
        contentValues.put("blue", blue);
        contentValues.put("green", green);
        contentValues.put("favorite", fav);
        sqLiteDatabase.insert("colors", null,contentValues);
    }

    int getCount(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from colors", null);
        return cursor.getCount();
    }

    public int getRed(int position){
        int red = 0;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = ("SELECT * from colors");
        if(addGroupBy && sort == 1){
            query += " ORDER by red DESC";
        }
        if(addGroupBy && sort == 2){
            query += " ORDER by blue DESC";
        }
        if(addGroupBy && sort == 3){
            query += " ORDER by green DESC";
        }
        if(addGroupBy && sort == 4){
            query += " ORDER by favorite DESC";
        }
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToPosition(position)){
            red = cursor.getInt(1);
        }
        return red;
    }
    public int getBlue(int position){
        int blue = 0;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = ("SELECT * from colors");
        if(addGroupBy && sort == 1){
            query += " ORDER by red DESC";
        }
        if(addGroupBy && sort == 2){
            query += " ORDER by blue DESC";
        }
        if(addGroupBy && sort == 3){
            query += " ORDER by green DESC";
        }
        if(addGroupBy && sort == 4){
            query += " ORDER by favorite DESC";
        }
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToPosition(position)){
            blue = cursor.getInt(2);
        }
        return blue;
    }

    public int getGreen(int position){
        int green = 0;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = ("SELECT * from colors");
        if(addGroupBy && sort == 1){
            query += " ORDER by red DESC";
        }
        if(addGroupBy && sort == 2){
            query += " ORDER by blue DESC";
        }
        if(addGroupBy && sort == 3){
            query += " ORDER by green DESC";
        }
        if(addGroupBy && sort == 4){
            query += " ORDER by favorite DESC";
        }
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToPosition(position)){
            green = cursor.getInt(3);
        }
        return green;
    }

    public boolean getFav(int position){
        int fav = 0;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = ("SELECT * from colors");
        if(addGroupBy && sort == 1){
            query += " ORDER by red DESC";
        }
        if(addGroupBy && sort == 2){
            query += " ORDER by blue DESC";
        }
        if(addGroupBy && sort == 3){
            query += " ORDER by green DESC";
        }
        if(addGroupBy && sort == 4){
            query += " ORDER by favorite DESC";
        }
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToPosition(position)){
            fav = cursor.getInt(4);
            Log.v("here in code", String.valueOf(fav));
        }
        if (fav == 0){
            return false;
        }
        return true;
    }

    public void update(boolean checked, int position){
        int favorite=0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues updatedFavorite = new ContentValues();
        Log.v("here", String.valueOf(checked));
        if (checked){
            updatedFavorite.put("favorite", 1);
        }else{
            updatedFavorite.put("favorite", 0);
        }
        String query = ("SELECT * from colors");
        if(addGroupBy && sort == 1){
            query += " ORDER by red DESC";
        }
        if(addGroupBy && sort == 2){
            query += " ORDER by blue DESC";
        }
        if(addGroupBy && sort == 3){
            query += " ORDER by green DESC";
        }
        if(addGroupBy && sort == 4){
            query += " ORDER by favorite DESC";
        }
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToPosition(position)){
            favorite = cursor.getInt(0);
        }
        sqLiteDatabase.update("colors",
                updatedFavorite, "_id = ?",new String[]{String.valueOf(favorite)});
        MainActivity.colorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
