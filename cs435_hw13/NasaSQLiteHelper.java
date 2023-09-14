package com.example.cs435_hw13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.File;

public class NasaSQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "nasa.sqlite";
    private static  final int DB_VERSION = 1;
    Context context1;

    public NasaSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        context1 = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE nasa (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, title TEXT, imgPath TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    void insert(String date, String title, String imgPath){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("title", title);
        contentValues.put("imgPath", imgPath);
        sqLiteDatabase.insert("nasa", null, contentValues);
    }

    int getCount(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM nasa";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor.getCount();
    }

    String getTitle(int position){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("nasa", new String[]{"_id", "date", "title", "imgPath"},
                null, null, null, null, null);
        if (cursor.moveToPosition(position)){
            String title = cursor.getString(2);
            return title;
        }
        return "";
    }

    Bitmap getImgPath(int position){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("nasa", new String[]{"_id", "date", "title", "imgPath"},
                null, null, null, null, null);
        if (cursor.moveToPosition(position)){
            String imgPath = cursor.getString(3);
            File file = context1.getFileStreamPath(imgPath);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            return bitmap;
        }
        return null;
    }

    String getDate(int position){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("nasa", new String[]{"_id", "date", "title", "imgPath"},
                null, null, null, null, null);
        if (cursor.moveToPosition(position)){
            String date = cursor.getString(1);
            return date;
        }
        return "";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
