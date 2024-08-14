package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shop = new Shop();
        player = Player.getInstancePlayer();// Hardcoded player initialization for testing

        player.setCoins(6000);

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

            for (Card card : cardList) {
                if (card.isSelected()) {
                    totalCost += card.getValue();
                    if (!player.getCards().contains(card)) {
                        cardsToBuy.add(card);
                    } else {
                        Toast.makeText(ShopActivity.this, "Already have the card: " + card.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            if (totalCost > player.getCoins()) {
                Toast.makeText(ShopActivity.this, "Not enough coins to complete the purchase.", Toast.LENGTH_SHORT).show();
            } else {
                for (Card card : cardsToBuy) {
                    if (shop.purchaseCard(player, card)) {
                        Toast.makeText(ShopActivity.this, "Card purchased: " + card.getName(), Toast.LENGTH_SHORT).show();
                        // Navigate back to the menu
                        Intent intent = new Intent(ShopActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish(); // Close ShopActivity
                    }
                }
                cardAdapter.notifyDataSetChanged();
                amountTextView.setText("Amount: " + player.getCoins()); // Update player's amount

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
