package com.example.war_of_cards;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.war_of_cards.Model.Player;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileName;
    private TextView profilePhoneNumber;
    private TextView profileCoins;
//    private TextView profileCards;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        profileName = findViewById(R.id.profile_name);
        profilePhoneNumber = findViewById(R.id.profile_phone_number);
        profileCoins = findViewById(R.id.profile_coins);
//        profileCards = findViewById(R.id.profile_cards);

        // Retrieve the player object (this is just a placeholder, update accordingly)
        player = getPlayer(); // Implement this method to retrieve the Player object

        // Set the player details to the views
        profileName.setText(player.getName());
        profilePhoneNumber.setText(player.getPhoneNumber());
        profileCoins.setText(String.valueOf(player.getCoins()));
//        profileCards.setText(getPlayerCards(player)); // Implement this method to retrieve the player's card names
    }

    private Player getPlayer() {
        // Retrieve the player object, possibly from an Intent or a saved instance state
        // This is a placeholder method, implement it according to your needs
        return new Player("Player 1", "1234567890");
    }

//    private String getPlayerCards(Player player) {
//        StringBuilder cardsBuilder = new StringBuilder();
//        for (Card card : player.getCards()) {
//            cardsBuilder.append(card.getName()).append("\n");
//        }
//        return cardsBuilder.toString();
//    }
}
