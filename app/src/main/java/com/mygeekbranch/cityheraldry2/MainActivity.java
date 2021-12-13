package com.mygeekbranch.cityheraldry2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new CitiesFragment() )
                    .commit();

        }


    }
}