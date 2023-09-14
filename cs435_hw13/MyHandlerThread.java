package com.example.cs435_hw13;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MyHandlerThread extends HandlerThread {
    private Context context;
    private Handler mainHandler;
    final String KEY = "FGdMLjieVfmLWTnMsh8qim1mA9DOzaELpGj6JsLM";
    NasaSQLiteHelper nasaSQLiteHelper;

    public MyHandlerThread(Context context, Handler mainHandler) {
        super("MyHandlerThread");
        this.context= context;
        this.mainHandler = mainHandler;
    }

    @Override
    public void run() {
        nasaSQLiteHelper = new NasaSQLiteHelper(context.getApplicationContext());
        int year = MainActivity.year +1;

        for (int i=1996; i <year;i++) {
            try {
                URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=" + KEY + "&date="+i + "-" + MainActivity.datePicked);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = bufferedReader.readLine();
                JSONObject jsonObject = new JSONObject(line);
                String img = jsonObject.getString("url");
                String date = jsonObject.getString("date");
                String title = jsonObject.getString("title");
                String mediaType = jsonObject.getString("media_type");

                if(mediaType.equals("image")) {
                    URL imgURL = new URL(img);
                    InputStream inputStream = imgURL.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    FileOutputStream fileOutputStream = context.openFileOutput(date, Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.close();
                    nasaSQLiteHelper.insert(date, title, date);
                    mainHandler.sendEmptyMessage(0);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
