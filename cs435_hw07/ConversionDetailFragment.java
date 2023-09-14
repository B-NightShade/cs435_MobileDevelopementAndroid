package com.example.cs435_hw07;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ConversionDetailFragment extends Fragment {

    private TextView textViewtop;
    private TextView textViewBottom;
    private TextView textViewResult;
    private EditText editText;
    private Button buttonConvert;
    private int conversionId=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversion_detail, container, false);
        textViewtop = view.findViewById(R.id.textViewTop);
        textViewBottom = view.findViewById(R.id.textViewBottom);
        textViewResult = view.findViewById(R.id.textViewAnswer);
        editText = view.findViewById(R.id.editTextTextInput);
        buttonConvert = view.findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String value = String.valueOf(editText.getText());
                 if (!value.equals("")) {
                     try {
                         float startVal = Float.parseFloat(value);
                         String finalVal = Conversion.result(conversionId, startVal);
                         String finalValue = finalVal;
                         textViewResult.setText(finalValue);
                     }catch(Exception e){
                         Log.v("parsing", "parsing Error");
                     }
                 }
            }
        }
        );
        return view;
    }
    void setConversionId(int id){
        this.conversionId = id;
    }

    @Override
    public void onStart() {
        super.onStart();
        Conversion conversion = Conversion.conversions[conversionId];
        textViewtop.setText(conversion.getTop());
        textViewBottom.setText(conversion.getBottom());
    }

}