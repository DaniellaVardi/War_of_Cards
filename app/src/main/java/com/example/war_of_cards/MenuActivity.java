package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {

    private MaterialButton onePlayer_BTN;
    private MaterialButton twoPlayer_BTN;
    private MaterialButton profile_BTN;
    private MaterialButton shop_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();

        onePlayer_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        twoPlayer_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profile_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        shop_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

    }

    private void findViews() {
        onePlayer_BTN = findViewById(R.id.onePlayer_BTN);
        twoPlayer_BTN = findViewById(R.id.twoPlayer_BTN);
        profile_BTN = findViewById(R.id.profile_BTN);
        shop_BTN = findViewById(R.id.shop_BTN);
    }

}



