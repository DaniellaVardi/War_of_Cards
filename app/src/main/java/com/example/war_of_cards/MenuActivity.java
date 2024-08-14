package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.war_of_cards.Model.Player;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    Button logout_BTN;
    TextView user_details;

    private MaterialButton onePlayer_BTN;
    private MaterialButton twoPlayer_BTN;
    private MaterialButton profile_BTN;
    private MaterialButton shop_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();

        if (user == null) {
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            user_details.setText(user.getEmail());
        }

        logout_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        onePlayer_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, GameSetupActivity.class);
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
                Log.d("Player","player12345 after menu:"+Player.getInstancePlayer().toString());
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

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        logout_BTN = findViewById(R.id.logout_BTN);
        user_details = findViewById(R.id.user_details);
        onePlayer_BTN = findViewById(R.id.onePlayer_BTN);
        twoPlayer_BTN = findViewById(R.id.twoPlayer_BTN);
        profile_BTN = findViewById(R.id.profile_BTN);
        shop_BTN = findViewById(R.id.shop_BTN);
    }

}



