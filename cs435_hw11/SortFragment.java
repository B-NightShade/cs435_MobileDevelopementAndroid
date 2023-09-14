package com.example.cs435_hw11;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class SortFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.fragment_sort,null);
        builder.setView(view);
        builder.setTitle("Sort by:");
        builder.setItems(R.array.Sorts, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i ==0){
                    Log.v("sort", "red");
                    ColorDBHelper.addGroupBy = true;
                    ColorDBHelper.sort=1;
                    MainActivity.colorAdapter.notifyDataSetChanged();
                }
                if (i == 1){
                    Log.v("sort", "blue");
                    ColorDBHelper.addGroupBy = true;
                    ColorDBHelper.sort=2;
                    MainActivity.colorAdapter.notifyDataSetChanged();
                }
                if (i == 2){
                    Log.v("sort", "green");
                    ColorDBHelper.addGroupBy = true;
                    ColorDBHelper.sort=3;
                    MainActivity.colorAdapter.notifyDataSetChanged();
                }
                if (i == 3){
                    Log.v("sort", "favorite");
                    ColorDBHelper.addGroupBy = true;
                    ColorDBHelper.sort=4;
                    MainActivity.colorAdapter.notifyDataSetChanged();
                }
                if (i == 4){
                    Log.v("sort", "default");
                    ColorDBHelper.addGroupBy = false;
                    ColorDBHelper.sort=0;
                    MainActivity.colorAdapter.notifyDataSetChanged();
                }
            }
        });
        builder.show();
        return super.onCreateDialog(savedInstanceState);
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }
 */
}