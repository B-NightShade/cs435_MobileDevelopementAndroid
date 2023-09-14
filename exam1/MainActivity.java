package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Integer> arrayList;
    ArrayAdapter<Integer>arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewHome);

        arrayList = new ArrayList<Integer>();
        arrayAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                Model.arrayList
        );
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int result = Model.arrayList.get(i);
                Log.v("here", String.valueOf(result));
                result = result *-1;
                Model.arrayList.set(i, result);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Model.arrayList.remove(Model.arrayList.get(i));
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void onClickAdd(View view) {
        Random rand = new Random();
        int added = rand.nextInt(10);
        Model.arrayList.add(added);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onClickSum(View view) {
        int sum = 0;
        for(int i =0; i < Model.arrayList.size(); i++){
            sum += Model.arrayList.get(i);
        }
        String display = String.valueOf(sum);
        Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();
    }

    public void onClickClear(View view) {
        Model.arrayList.clear();
        arrayAdapter.notifyDataSetChanged();

    }
}