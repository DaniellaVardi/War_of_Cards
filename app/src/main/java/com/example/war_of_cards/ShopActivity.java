package com.example.war_of_cards;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shop = new Shop();
        player = new Player(); // Initialize the player object properly in your code

        amountTextView = findViewById(R.id.shop_LBL_amount);
        recyclerView = findViewById(R.id.shop_RV_items);
        purchaseButton = findViewById(R.id.shop_BTN_purchase);

        cardList = new ArrayList<>();
//                cardList.add(new Card("Phoenix", 10000, R.drawable.ic_card_10000));
//                cardList.add(new Card("Sorcerer", 7000, R.drawable.ic_card_7000));
//                cardList.add(new Card("Dragon", 6000, R.drawable.ic_card_6000));
//                cardList.add(new Card("Knight", 4000, R.drawable.ic_card_4000));


        cardAdapter = new CardAdapter(this, cardList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

        amountTextView.setText("Amount: " + player.getCoins()); // Display player's amount

        purchaseButton.setOnClickListener(v -> {
            // Implement purchase logic here
        });
    }

    @Override
    public void onCardClick(Card card) {
        // Handle card click, possibly adding it to a purchase list or showing details
    }
}



