package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button singlePlayerButton = findViewById(R.id.onePlayer_BTN);
        Button multiplayerButton = findViewById(R.id.twoPlayer_BTN);

        singlePlayerButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.putExtra("mode", "singlePlayer");
            startActivity(intent);
        });

        multiplayerButton.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.putExtra("mode", "multiplayer");
            startActivity(intent);
        });
    }
}
