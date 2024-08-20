package com.example.war_of_cards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.war_of_cards.Logic.CardAdapter;
import com.example.war_of_cards.Model.Card;
import com.example.war_of_cards.Model.Player;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class GameSetupActivity extends AppCompatActivity implements CardAdapter.OnCardClickListener {

    private RecyclerView setup_RV_cards;
    private MaterialButton setup_BTN_confirm;
    private MaterialButton setup_BTN_back;
    private List<Card> playerCards;
    private CardAdapter cardAdapter;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        setup_RV_cards = findViewById(R.id.setup_RV_cards);
        setup_BTN_confirm = findViewById(R.id.setup_BTN_confirm);
        setup_BTN_back = findViewById(R.id.setup_BTN_back);

        setup_BTN_confirm.setEnabled(false); // Disable button initially

        player = Player.getInstancePlayer(); // Initialize Player

        if (player == null) {
            Log.e("GameSetupActivity", "Player instance is null! Please initialize the player properly.");
            Toast.makeText(this, "Error: Player instance is not initialized.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity to avoid further issues
            return;
        }

        Log.d("Player", "player details:" + player.toString());

        // Clear selected cards when entering GameSetupActivity
        clearSelectedCards();

        // Initialize playerCards with the available cards
        playerCards = player.getCards();

        // Deselect all cards at the start
        for (Card card : playerCards) {
            card.setSelected(false);
        }

        cardAdapter = new CardAdapter(this, playerCards, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        setup_RV_cards.setLayoutManager(gridLayoutManager);
        setup_RV_cards.setAdapter(cardAdapter);

        setup_BTN_confirm.setOnClickListener(v -> {
            ArrayList<Card> selectedCards = new ArrayList<>(player.getSelectedCards());
            player.setSelectedCards(selectedCards);  // Ensure selected cards are set

            Intent intent = new Intent(GameSetupActivity.this, MainActivity.class);
            Log.d("Player", "player12345 after confirm:" + Player.getInstancePlayer().toString());
            startActivity(intent);
        });

        setup_BTN_back.setOnClickListener(v -> {
            // Navigate back to the previous activity or menu
            Intent intent = new Intent(GameSetupActivity.this, MenuActivity.class); // Replace MenuActivity with your actual menu activity class
            startActivity(intent);
            finish(); // Close the activity
        });
    }

    private void clearSelectedCards() {
        if (player != null && player.getSelectedCards() != null) {
            player.getSelectedCards().clear(); // Clear the list
        }
    }

    @SuppressLint({"ResourceAsColor", "NotifyDataSetChanged"})
    @Override
    public void onCardClick(Card card) {
        if (player.getSelectedCards().contains(card)) {
            player.getSelectedCards().remove(card);
            card.setSelected(false);
        } else {
            if (player.getSelectedCards().size() < 3) {
                player.getSelectedCards().add(card);
                card.setSelected(true);
            } else {
                Toast.makeText(this, "You can only select 3 cards", Toast.LENGTH_SHORT).show();
            }
        }

        cardAdapter.notifyDataSetChanged();
        setup_BTN_confirm.setEnabled(player.getSelectedCards().size() == 3);

        if (setup_BTN_confirm.isEnabled()) {
            setup_BTN_confirm.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.gold));
        } else {
            setup_BTN_confirm.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.gray));
        }
    }
}
