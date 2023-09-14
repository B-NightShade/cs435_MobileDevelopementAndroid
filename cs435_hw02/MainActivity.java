package com.example.cs435_hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextInput;
    SeekBar seekBarEncrypt;
    TextView slideValEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextEncrypt);
        slideValEn = findViewById(R.id.textViewValEn);
        slideValEn.setText("0");

        Intent intent = getIntent();
        String oMsg = intent.getStringExtra("dMessage");

        seekBarEncrypt = findViewById(R.id.seekBarAmountEncrypt);


        seekBarEncrypt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int slideNum = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                slideNum = seekBarEncrypt.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                slideValEn.setText(Integer.toString(slideNum));
            }
        });

        editTextInput.setText(oMsg);
    }

    public void EncyptMsgClick(View view) {
        String inputUnencrypted = editTextInput.getText().toString();
        int shiftEn = seekBarEncrypt.getProgress();
        String msgEncrypt = "";
        char[] charStringEn = inputUnencrypted.toCharArray();
        for (char c : charStringEn){
            c += shiftEn;
            msgEncrypt = msgEncrypt + c;
        }

        Intent intent = new Intent(getApplicationContext(), DecryptActivity.class);
        intent.putExtra("message", msgEncrypt);
        intent.putExtra("shiftVal",shiftEn);
        startActivity(intent);
    }
}