package com.example.cs435_hw07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        ConversionListFragment conversionListFragment =
                (ConversionListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentConversionList);

        conversionListFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View frame = (FrameLayout) findViewById(R.id.frameTablet);
                if(frame != null){
                    ConversionDetailFragment conversionDetailFragment = new ConversionDetailFragment();
                    conversionDetailFragment.setConversionId(i);

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameTablet, conversionDetailFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.commit();
                }else {
                    Intent intent = new Intent(getApplicationContext(), ConversionDetailActivity.class);
                    intent.putExtra("id", i);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}