package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.war_of_cards.Model.Card;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class GameSetupActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCards;
    private CardSelectionAdapter cardSelectionAdapter;
    private List<Card> cardList;
    private List<Card> selectedCards;
    private MaterialButton confirmButton;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        mode = getIntent().getStringExtra("mode");

        recyclerViewCards = findViewById(R.id.setup_RV_cards);
        recyclerViewCards.setLayoutManager(new LinearLayoutManager(this));

        confirmButton = findViewById(R.id.setup_BTN_confirm);

        cardList = new ArrayList<>();
        selectedCards = new ArrayList<>();
        // Add your cards to the list with their image resources
//         cardList.add(new Card("Phoenix", 10000, 10000, R.drawable.ic_card_10000));
//         cardList.add(new Card("Knight", 4000, 4000, R.drawable.ic_card_4000));
//         cardList.add(new Card("Knight", 6000, 6000, R.drawable.ic_card_6000));

        cardSelectionAdapter = new CardSelectionAdapter(this, cardList, selectedCards);
        recyclerViewCards.setAdapter(cardSelectionAdapter);

        confirmButton.setOnClickListener(v -> {
            if (selectedCards.size() == 3) {
                Intent intent = new Intent(GameSetupActivity.this, MainActivity.class);
                intent.putExtra("mode", mode);
                intent.putExtra("player1Cards", new ArrayList<>(selectedCards));

                // Assuming player 2 is AI or player 2's selection would be similar to player 1
                if (mode.equals("singlePlayer")) {
                    // Randomly select 3 cards for the AI player
                    ArrayList<Card> aiSelectedCards = new ArrayList<>(cardList.subList(0, 3));
                    intent.putExtra("player2Cards", aiSelectedCards);
                } else {
                    intent.putExtra("player2Cards", new ArrayList<>(selectedCards)); // Modify this as needed for player 2's card selection
                }
                startActivity(intent);
            } else {
                Toast.makeText(GameSetupActivity.this, "Please select exactly 3 cards", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
