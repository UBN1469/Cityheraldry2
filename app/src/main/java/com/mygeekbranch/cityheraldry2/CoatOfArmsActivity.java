package com.mygeekbranch.cityheraldry2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class CoatOfArmsActivity extends AppCompatActivity {
    // Эта activity для показа герба в портретной ориентации (только герб видно )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coat_of_arms);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        if (savedInstanceState ==null){
            // Если эта activity запускается первый раз (с каждым новым гербомпервый раз)
// то перенаправим параметр фрагменту
            CoatOfArmsFragment details = new CoatOfArmsFragment();
            details.setArguments(getIntent().getExtras());
            // Добавим фрагмент на activity
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, details).commit();


        }
    }
}