package com.example.war_of_cards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.war_of_cards.Model.Card;
import com.example.war_of_cards.Model.Game;
import com.example.war_of_cards.Model.Player;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String mode = getIntent().getStringExtra("mode");
        ArrayList<Card> player1Cards = (ArrayList<Card>) getIntent().getSerializableExtra("player1Cards");
        ArrayList<Card> player2Cards = (ArrayList<Card>) getIntent().getSerializableExtra("player2Cards");

        Player player1 = new Player("Player 1", "1234567890");
        Player player2 = mode.equals("singlePlayer") ? new Player("AI", "0000000000") : new Player("Player 2", "0987654321");

        player1.setSelectedCards(player1Cards);
        player2.setSelectedCards(player2Cards);

        game = new Game(player1, player2);
        game.startGame(player1Cards, player2Cards);

        Log.d("Game Result", "Player 1 Score: " + game.getPlayer1Score());
        Log.d("Game Result", "Player 2 Score: " + game.getPlayer2Score());

        // Display results or navigate to a result screen
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("player1Score", game.getPlayer1Score());
        intent.putExtra("player2Score", game.getPlayer2Score());
        startActivity(intent);
    }
}
