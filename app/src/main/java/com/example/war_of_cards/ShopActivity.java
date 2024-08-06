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
import com.example.war_of_cards.Model.Deck;
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
        player = new Player(); // Initialize the player object properly in your code

        amountTextView = findViewById(R.id.shop_LBL_amount);
        recyclerView = findViewById(R.id.shop_RV_items);
        purchaseButton = findViewById(R.id.shop_BTN_purchase);
        backButton = findViewById(R.id.shop_BTN_back);

        // Initialize card list with cards from the Deck class
        cardList = new ArrayList<>(List.of(Deck.DECK));

        cardAdapter = new CardAdapter(this, cardList, this);

        // Set GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(cardAdapter);

        amountTextView.setText("Amount: " + player.getCoins()); // Display player's amount

        purchaseButton.setOnClickListener(v -> {
            // Implement purchase logic here
        });

        backButton.setOnClickListener(v -> {
            // Navigate back to the menu
            Intent intent = new Intent(ShopActivity.this, MenuActivity.class); // Replace MenuActivity with your actual menu activity class
            startActivity(intent);
//            finish(); // Optional: call finish() if you want to close this activity
        });
    }

    @Override
    public void onCardClick(Card card) {
        // Handle card click, possibly adding it to a purchase list or showing details
        Toast.makeText(this, "Clicked Card", Toast.LENGTH_SHORT).show();
    }
}
