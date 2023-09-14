package com.example.cs435_hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class DecryptActivity extends AppCompatActivity {

    EditText decyptTextEdit;
    SeekBar seekBarDec;
    int shiftDec;
    TextView slideValDec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
        decyptTextEdit = findViewById(R.id.editTextDecrypt);
        seekBarDec = findViewById(R.id.seekBarAmountDec);
        slideValDec = findViewById(R.id.textViewDecVal);

        Intent intent = getIntent();
        String dMsg = intent.getStringExtra("message");

        shiftDec = intent.getIntExtra("shiftVal",0);
        seekBarDec.setProgress(shiftDec);
        seekBarDec.setEnabled(false);
        slideValDec.setText(Integer.toString(shiftDec));
        decyptTextEdit.setText(dMsg);

    }

    public void decryptMsgClick(View view) {
        String EncryptedMsg = decyptTextEdit.getText().toString();
        String msgDecrypt = "";
        char[] charStringEn = EncryptedMsg.toCharArray();
        for (char c : charStringEn){
            c -= shiftDec;
            msgDecrypt = msgDecrypt + c;
        }
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("dMessage", msgDecrypt);
        startActivity(intent);
    }
}