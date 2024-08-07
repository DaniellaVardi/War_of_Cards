package com.example.war_of_cards;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.war_of_cards.Model.Card;
import com.example.war_of_cards.Model.Game;
import com.example.war_of_cards.Model.Player;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    private MaterialTextView player1_LBL_amount;
    private MaterialTextView player2_LBL;
    private MaterialTextView player1_LBL;
    private ImageView player1_IMG_card;
    private ImageView player2_IMG_card;

    private ImageView player1_IMG_card1;
    private ImageView player1_IMG_card2;
    private ImageView player1_IMG_card3;
    private FrameLayout frame1;
    private FrameLayout frame2;
    private FrameLayout frame3;
    private ImageView[] deck = new ImageView[3];
    private FrameLayout[] deckFrame = new FrameLayout[3];
    private MaterialButton main_BTN_makeTurn;
    private MaterialButton main_BTN_endTurn;
    private boolean[] boolImage = {false, false, false};

    private Game game;
    private int chosenCard = -1;
    private boolean isChosen = false;
    private int roundCounter = 0;
    private static final int TOTAL_ROUNDS = 3;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        deck[0] = player1_IMG_card1;
        deck[1] = player1_IMG_card2;
        deck[2] = player1_IMG_card3;

        deckFrame[0] = frame1;
        deckFrame[1] = frame2;
        deckFrame[2] = frame3;

        game = new Game();

        // Retrieve player1 from the intent
        Player player1 = (Player) getIntent().getSerializableExtra("player1");
        if (player1 != null) {
            game.setPlayer1(player1);  // Set player1 in the game
            player1_LBL.setText(player1.getName());

            // Set up cards
            player1_IMG_card1.setImageResource(player1.getSelectedCards().get(0).getImageResource());
            player1_IMG_card2.setImageResource(player1.getSelectedCards().get(1).getImageResource());
            player1_IMG_card3.setImageResource(player1.getSelectedCards().get(2).getImageResource());
        }

        player2_LBL.setText(game.getPlayer2().getName());

        player1_IMG_card.setOnClickListener(v -> playRound());

        player1_IMG_card1.setOnClickListener(v -> cardClick(0));
        player1_IMG_card2.setOnClickListener(v -> cardClick(1));
        player1_IMG_card3.setOnClickListener(v -> cardClick(2));

        main_BTN_makeTurn.setOnClickListener(v -> {
            Card aiCard = game.playRound(chosenCard);
            if (aiCard != null) {
                chosenCard = -1;
                isChosen = false;

                player1_LBL_amount.setText(game.getPlayer1Score() + "");
                main_BTN_makeTurn.setVisibility(View.GONE);
                main_BTN_endTurn.setVisibility(View.VISIBLE);

                player2_IMG_card.setImageResource(aiCard.getImageResource());
            }
        });

        main_BTN_endTurn.setOnClickListener(v -> endTurn());
    }

    @SuppressLint("ResourceAsColor")
    private void cardClick(int cardPlace) {
        if (isChosen) return;

        for (int i = 0; i < boolImage.length; i++) {
            if (i == cardPlace) {
                if (boolImage[i]) {
                    boolImage[i] = false;
                    chosenCard = -1;
                    break;
                }
                boolImage[i] = true;
                chosenCard = cardPlace;
            } else {
                boolImage[i] = false;
            }
        }

        for (int i = 0; i < boolImage.length; i++) {
            if (boolImage[i]) {
                deckFrame[i].setBackgroundColor(Color.GREEN);
            } else {
                deckFrame[i].setBackgroundColor(R.color.gold);
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private void playRound() {
        if (!isChosen) {
            if (chosenCard == -1) {
                Toast.makeText(this, "No Card Chosen", Toast.LENGTH_SHORT).show();
                return;
            }

            deck[chosenCard].setVisibility(View.GONE);
            deckFrame[chosenCard].setVisibility(View.GONE);
            main_BTN_makeTurn.setVisibility(View.VISIBLE);

            player1_IMG_card.setImageResource(game.getPlayer1().getSelectedCards().get(chosenCard).getImageResource());
            deckFrame[chosenCard].setBackgroundColor(R.color.gold);
            isChosen = true;
        } else {
            deck[chosenCard].setImageResource(game.getPlayer1().getSelectedCards().get(chosenCard).getImageResource());
            player1_IMG_card.setImageResource(R.drawable.ic_back_card);
            deck[chosenCard].setVisibility(View.VISIBLE);
            deckFrame[chosenCard].setVisibility(View.VISIBLE);
            main_BTN_makeTurn.setVisibility(View.GONE);

            isChosen = false;
            chosenCard = -1;
        }
    }

    private void endTurn() {
        player1_IMG_card.setImageResource(R.drawable.ic_back_card);
        player2_IMG_card.setImageResource(R.drawable.ic_back_card);
        main_BTN_endTurn.setVisibility(View.GONE);

        roundCounter++;
        if (roundCounter >= TOTAL_ROUNDS) {
            endGame();
        }
    }

    private void endGame() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("player1Score", game.getPlayer1Score());
        intent.putExtra("player2Score", game.getPlayer2Score());
        startActivity(intent);
        finish();
    }

    private void findViews() {
        player1_LBL = findViewById(R.id.player1_LBL);
        player1_LBL_amount = findViewById(R.id.player1_LBL_amount);
        player2_LBL = findViewById(R.id.player2_LBL);

        player1_IMG_card = findViewById(R.id.player1_IMG_card);
        player2_IMG_card = findViewById(R.id.player2_IMG_card);

        player1_IMG_card1 = findViewById(R.id.player1_IMG_card1);
        player1_IMG_card2 = findViewById(R.id.player1_IMG_card2);
        player1_IMG_card3 = findViewById(R.id.player1_IMG_card3);

        frame1 = findViewById(R.id.frame1);
        frame2 = findViewById(R.id.frame2);
        frame3 = findViewById(R.id.frame3);

        main_BTN_makeTurn = findViewById(R.id.main_BTN_makeTurn);
        main_BTN_endTurn = findViewById(R.id.main_BTN_endTurn);
    }
}
