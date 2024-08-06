package com.example.war_of_cards;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
    private List<Card> playerCards;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        setup_RV_cards = findViewById(R.id.setup_RV_cards);
        setup_BTN_confirm = findViewById(R.id.setup_BTN_confirm);

        // Initialize playerCards with the player's cards
        playerCards = getPlayerCards();

        cardAdapter = new CardAdapter(this, playerCards, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        setup_RV_cards.setLayoutManager(gridLayoutManager);
        setup_RV_cards.setAdapter(cardAdapter);

        setup_BTN_confirm.setOnClickListener(v -> {
            // Handle card selection confirmation
            // Example: print selected cards
            List<Card> selectedCards = getSelectedCards();
            for (Card card : selectedCards) {
                System.out.println("Selected Card: " + card.getName());
            }
        });
    }

    private List<Card> getPlayerCards() {
        // Create a sample player and cards for testing
        Player player = new Player("John Doe", "123456789");
        player.addCard(new Card("Card 1", 100, 10, R.drawable.ic_card_2000));
        player.addCard(new Card("Card 2", 150, 15, R.drawable.ic_card_4000));
        player.addCard(new Card("Card 3", 200, 20, R.drawable.ic_card_6000));
        player.addCard(new Card("Card 4", 250, 25, R.drawable.ic_card_10000));
        return player.getCards();
    }

    private List<Card> getSelectedCards() {
        // Example method to retrieve selected cards
        // Implement logic to get selected cards from your adapter or UI
        return new ArrayList<>();
    }

    @Override
    public void onCardClick(Card card) {
        // Handle card click, possibly adding it to a selection list
        // Example: Toggle card selection
        Toast.makeText(this, "Clicked Card", Toast.LENGTH_SHORT).show();
        System.out.println("Clicked Card: " + card.getName());
    }
}
