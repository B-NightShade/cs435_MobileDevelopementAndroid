package com.example.cs435_hw12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements ComicAdapter.ItemAdapterListener {

    RecyclerView recyclerViewHome;
    ComicDatabaseHelper comicDatabaseHelper;
    static ComicAdapter comicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewHome = findViewById(R.id.recyclerViewHome);

        comicDatabaseHelper = new ComicDatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = comicDatabaseHelper.getReadableDatabase();

        comicAdapter = new ComicAdapter(comicDatabaseHelper, this);
        recyclerViewHome.setAdapter(comicAdapter);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    @Override
    public void click(int position) {
        int id = comicDatabaseHelper.getId(position);
        Intent intent = new Intent(getApplicationContext(),ComicDetailActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    private class MyAsyncTask extends AsyncTask{
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            comicAdapter.notifyDataSetChanged();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            HttpURLConnection httpURLConnection = null;
            try {
                Log.v("hey", "waiting");
                URL url = new URL("https://xkcd.com/info.0.json");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.v("here", "BAD CONNECTION");
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String JSONtext = bufferedReader.readLine();
                JSONObject jsonobject = new JSONObject(JSONtext);
                int number = jsonobject.getInt("num");
                int count = comicDatabaseHelper.getCount();
                int topNumber = comicDatabaseHelper.getNumber(1);
                Log.v("HERE", String.valueOf(number));
                Log.v("TOP", String.valueOf(topNumber));
                if (count != 100){
                    for (int i = number; i > (number - 100); i--) {
                        String website = "https://xkcd.com/" + i + "/info.0.json";
                        URL url1 = new URL(website);
                        httpURLConnection = (HttpURLConnection) url1.openConnection();
                        InputStream inputStream2 = httpURLConnection.getInputStream();
                        if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                            Log.v("here", "BAD CONNECTION");
                        }
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
                        String JSON = bufferedReader2.readLine();
                        JSONObject jsonObject2 = new JSONObject(JSON);
                        int num = jsonObject2.getInt("num");
                        String img = jsonObject2.getString("img");
                        String title = jsonObject2.getString("title");
                        comicDatabaseHelper.insert(title, img, num);
                        Log.v("HEY", String.valueOf(i));
                    }
            }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }
            return null;
        }
    }
}