package com.example.cs435_hw07;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ConversionListFragment extends Fragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("here", "here");
        View view = inflater.inflate(R.layout.fragment_conversion_list, container, false);
        listView = (ListView) view.findViewById(R.id.listViewHome);
        listView.setAdapter(new ArrayAdapter<Conversion>
                (inflater.getContext(), android.R.layout.simple_list_item_1, Conversion.conversions));
        return view;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        listView.setOnItemClickListener(onItemClickListener);
    }
}