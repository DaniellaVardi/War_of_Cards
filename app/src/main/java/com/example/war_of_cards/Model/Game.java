package com.example.war_of_cards.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;

    public Game() {
        player2 = Player.getInstancePlayerAI();

        // Initialize AI cards
        initAiCards();

        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;

        // Ensure player1 has selected cards if they were not set already
        if (this.player1.getSelectedCards().isEmpty() && this.player1.getCards().size() >= 3) {
            this.player1.getSelectedCards().add(this.player1.getCards().get(0));
            this.player1.getSelectedCards().add(this.player1.getCards().get(1));
            this.player1.getSelectedCards().add(this.player1.getCards().get(2));
        }

        // Ensure player2 has selected cards if they were not set already
        if (this.player2.getSelectedCards().isEmpty() && this.player2.getCards().size() >= 3) {
            this.player2.getSelectedCards().add(this.player2.getCards().get(0));
            this.player2.getSelectedCards().add(this.player2.getCards().get(1));
            this.player2.getSelectedCards().add(this.player2.getCards().get(2));
        }
    }

    private void initAiCards() {
        int x = 0;
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        do {
            int temp = random.nextInt(Deck.CARDS.length);
            if (set.contains(temp)) continue;

            set.add(temp);
            x++;

            player2.addSelectedCard(Deck.CARDS[temp]);

        } while (x < 3);
    }

    public Card playRound(int chosenCard) {
        Random random = new Random();
        if (player2.getSelectedCards().isEmpty() || player1.getSelectedCards().isEmpty()) return null;

        Card card1 = player1.getSelectedCards().get(chosenCard);
        int randomIndex = random.nextInt(player2.getSelectedCards().size());
        Card card2 = player2.getSelectedCards().get(randomIndex);

        if (card1.getValue() > card2.getValue()) {
            player1Score += 1000;
        } else if (card1.getValue() < card2.getValue()) {
            player2Score += 1000;
        } else {
            Log.d("Game", "The round is a draw.");
        }

        player2.getSelectedCards().remove(card2);
        player2.setCoins(player2.getCoins() + player2Score);

        Log.d("Player","player1:"+player1.toString());
        Log.d("Player","player2:"+player2.toString());
        Log.d("SCORE", "player1 score = " + player1Score);
        Log.d("SCORE", "player2 score = " + player2Score);

        return card2;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    @Override
    public String toString() {
        return "Game{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", player1Score=" + player1Score +
                ", player2Score=" + player2Score +
                '}';
    }

}
