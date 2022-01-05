package com.ananayis.mylibrarychallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAll , btnCurrently , btnAlready , btnWishList , btnFavorite , btnAbout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtn();

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AllBooksActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initBtn(){
        btnAll = findViewById(R.id.btnAll);
        btnCurrently = findViewById(R.id.btnCurrently);
        btnAlready = findViewById(R.id.btnAlready);
        btnWishList = findViewById(R.id.btnWishList);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnAbout = findViewById(R.id.btnAbout);
    }
}