package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.war_of_cards.Database.DatabaseService;
import com.example.war_of_cards.Logic.CardAdapter;
import com.example.war_of_cards.Model.Card;
import com.example.war_of_cards.Model.Player;
import com.example.war_of_cards.Model.Shop;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity implements CardAdapter.OnCardClickListener {

    private Shop shop;
    private Player player;
    private TextView amountTextView;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<Card> cardList;
    private MaterialButton purchaseButton;
    private MaterialButton backButton;
    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shop = new Shop();
        player = Player.getInstancePlayer(); // Hardcoded player initialization for testing

        // Initialize DatabaseService with a reference to the players node
        databaseService = new DatabaseService("Players");

        amountTextView = findViewById(R.id.shop_LBL_amount);
        recyclerView = findViewById(R.id.shop_RV_items);
        purchaseButton = findViewById(R.id.shop_BTN_purchase);
        backButton = findViewById(R.id.shop_BTN_back);

        // Initialize card list with cards from the Shop
        cardList = new ArrayList<>(shop.getCards());

        // Clear selected state before setting up the adapter
        clearSelectedState();

        cardAdapter = new CardAdapter(this, cardList, this);

        // Set GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(cardAdapter);

        amountTextView.setText("Amount: " + player.getCoins()); // Display player's amount

        purchaseButton.setOnClickListener(v -> {
            int totalCost = 0;
            List<Card> cardsToBuy = new ArrayList<>();
            boolean purchaseSuccess = false;

            // Iterate through the card list to process selected cards
            for (Card card : cardList) {
                if (card.isSelected()) {
                    // Check if the player already owns the card based on card id
                    boolean alreadyOwnsCard = player.getCards().stream()
                            .anyMatch(existingCard -> existingCard.getId().equals(card.getId()));

                    if (!alreadyOwnsCard) {
                        totalCost += card.getValue();
                        cardsToBuy.add(card);
                    } else {
                        Toast.makeText(ShopActivity.this, "Already have the card: " + card.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            // Calculate the cost and update coins
            if (totalCost > player.getCoins()) {
                Toast.makeText(ShopActivity.this, "Not enough coins to complete the purchase.", Toast.LENGTH_SHORT).show();
            } else if (!cardsToBuy.isEmpty()) {
                // Deduct the cost from player's coins and update card list
                player.setCoins(player.getCoins() - totalCost);

                // Add purchased cards to player's card list
                player.getCards().addAll(cardsToBuy);

                // Save updated player data to the database
                savePlayerData();
                purchaseSuccess = true;
            }

            if (purchaseSuccess) {
                Toast.makeText(ShopActivity.this, "Purchase successful!", Toast.LENGTH_SHORT).show();
                // Navigate back to the menu
                Intent intent = new Intent(ShopActivity.this, MenuActivity.class);
                startActivity(intent);
                finish(); // Close ShopActivity
            }
        });

        backButton.setOnClickListener(v -> {
            // Navigate back to the menu
            Intent intent = new Intent(ShopActivity.this, MenuActivity.class);
            startActivity(intent);
            finish(); // Close ShopActivity
        });
    }

    private void clearSelectedState() {
        for (Card card : cardList) {
            card.setSelected(false); // Clear selection state
        }
    }

    private void savePlayerData() {
        // Save player cards and coins to the database
        databaseService.save(player, player.getUid());
    }

    @Override
    public void onCardClick(Card card) {
        // Toggle card selection
        if (card.isSelected()) {
            card.setSelected(false);
            Toast.makeText(this, "Card deselected: " + card.getName(), Toast.LENGTH_SHORT).show();
        } else {
            card.setSelected(true);
            Toast.makeText(this, "Card selected: " + card.getName(), Toast.LENGTH_SHORT).show();
        }
        cardAdapter.notifyDataSetChanged(); // Refresh the adapter to reflect the selection change
    }
}
