package com.example.cs435_hw11;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class AddDialogFragment extends DialogFragment {

    int numberColors;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.fragment_add_dialog,null);
        builder.setView(view);
        builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editTextNum = view.findViewById(R.id.editTextNumber);
                numberColors = Integer.valueOf(String.valueOf(editTextNum.getText()));
                insertColors();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
        return super.onCreateDialog(savedInstanceState);
    }

    public class ColorAsyncTask extends AsyncTask<Integer,Integer,Integer>{
        private int colorsCount;

        @Override
        protected void onPreExecute() {
            colorsCount = 0;
            MainActivity.progressBar.setMax(numberColors -1);
            MainActivity.progressBar.setVisibility(getView().VISIBLE);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            MainActivity.colorAdapter.notifyDataSetChanged();
            MainActivity.updateNumberColors();
            MainActivity.progressBar.setProgress(0);
            MainActivity.progressBar.setVisibility(getView().INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progress = values[0];
            MainActivity.progressBar.setProgress(progress);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            for (;colorsCount<numberColors;colorsCount++){
                MyColor myColor = new MyColor();
                int red = myColor.red;
                int blue = myColor.blue;
                int green = myColor.green;
                boolean fav = myColor.favorite;
                MainActivity.colorDBHelper.insert(red,blue,green,fav);
                publishProgress(colorsCount);
            }
            return null;
        }
    }

    public void insertColors(){
        ColorAsyncTask colorAsyncTask = new ColorAsyncTask();
        colorAsyncTask.execute();
    }
}