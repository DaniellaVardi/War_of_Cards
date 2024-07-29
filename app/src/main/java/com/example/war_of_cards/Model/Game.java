package com.example.war_of_cards.Model;

import java.util.List;

public class Game {

    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void startGame(List<Card> player1Cards, List<Card> player2Cards) {
        for (int i = 0; i < 3; i++) {
            Card player1Card = player1Cards.get(i);
            Card player2Card = player2Cards.get(i);
            playRound(player1Card, player2Card);
        }

        // Game over, determine the winner
        if (player1Score > player2Score) {
            System.out.println("Player 1 wins the game!");
        } else if (player2Score > player1Score) {
            System.out.println("Player 2 wins the game!");
        } else {
            System.out.println("The game is a draw!");
        }
    }

    private void playRound(Card player1Card, Card player2Card) {
        System.out.println("Player 1 plays: " + player1Card.getName() + " (Value: " + player1Card.getValue() + ")");
        System.out.println("Player 2 plays: " + player2Card.getName() + " (Value: " + player2Card.getValue() + ")");

        if (player1Card.getValue() > player2Card.getValue()) {
            player1Score += 1000;
            System.out.println("Player 1 wins this round!");
        } else if (player2Card.getValue() > player1Card.getValue()) {
            player2Score += 1000;
            System.out.println("Player 2 wins this round!");
        } else {
            System.out.println("This round is a draw!");
        }

        System.out.println("Current score - Player 1: " + player1Score + ", Player 2: " + player2Score);
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }
}
