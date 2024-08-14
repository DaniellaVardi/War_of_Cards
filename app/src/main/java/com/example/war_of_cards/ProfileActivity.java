package com.example.war_of_cards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.war_of_cards.Database.DatabaseService;
import com.example.war_of_cards.Logic.CardAdapter;
import com.example.war_of_cards.Model.Card;
import com.example.war_of_cards.Model.Player;

public class ProfileActivity extends AppCompatActivity implements CardAdapter.OnCardClickListener {
    private TextView profileName;
    private TextView profileEmail;
    private TextView profileCoins;
    private RecyclerView profile_RV_items;
    private Player player;
    private Button backButton;
    private DatabaseService dbs;

    @SuppressLint({"MissingInflatedId", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profileCoins = findViewById(R.id.profile_coins);
        profile_RV_items= findViewById(R.id.profile_RV_items);
        backButton = findViewById(R.id.back_BTN);

        dbs = new DatabaseService("Players");
        Log.d("Player","player1 before:"+Player.getInstancePlayer().toString());
        player = Player.getInstancePlayer();
        player.updateFromDB();
        Log.d("Player","player1 after:"+Player.getInstancePlayer().toString());



        CardAdapter cardAdapter = new CardAdapter(this, player.getCards(), this);
        Log.d("CARDS", "player cards:" + player.getCards());

        // Set GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        profile_RV_items.setLayoutManager(gridLayoutManager);
        profile_RV_items.setAdapter(cardAdapter);
        cardAdapter.notifyDataSetChanged();
        updateProfileUI();

        // Set up the return to menu button
        backButton.setOnClickListener(v -> {
            // Start the Main Menu Activity
            Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
            startActivity(intent);
            finish(); // Optional: Close the current activity
        });
    }


    private void updateProfileUI() {
        profileName.setText(player.getName());
        profileEmail.setText(player.getEmail());
        profileCoins.setText(String.valueOf(player.getCoins()));

        // Update UI with the player's cards as needed
    }

    @Override
    public void onCardClick(Card card) {
    }
}
