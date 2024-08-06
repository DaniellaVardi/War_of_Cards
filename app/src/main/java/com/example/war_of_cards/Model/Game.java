package com.example.war_of_cards.Model;

import android.widget.Toast;

import com.example.war_of_cards.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {

    private Player player1;
    private Player player2;
    private int player1Score;
    private int player2Score;

    public Game() {
        this.player1 = new Player("Daniel","0543550597");
        this.player2 = new Player("AI","0543550597");


        this.player1.addCard(new Card("Phoenix", 10000, 10000, R.drawable.ic_card_10000));
        this.player1.addCard(new Card("Knight", 4000, 4000, R.drawable.ic_card_4000));
        this.player1.addCard(new Card("Knight", 6000, 6000, R.drawable.ic_card_6000));

        initAiCards();

        this.player1.getSelectedCards().add(player1.getCards().get(0));
        this.player1.getSelectedCards().add(player1.getCards().get(1));
        this.player1.getSelectedCards().add(player1.getCards().get(2));

        this.player2.getSelectedCards().add(player1.getCards().get(0));
        this.player2.getSelectedCards().add(player1.getCards().get(1));
        this.player2.getSelectedCards().add(player1.getCards().get(2));

        this.player1Score = 0;
        this.player2Score = 0;
    }

    private void initAiCards(){
        int x = 0;
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        do {
            int temp = random.nextInt(Deck.DECK.length);
            if (set.contains(temp)) continue;

            set.add(temp);
            x++;

            player2.addCard(Deck.DECK[temp]);

        } while (x < 3);
    }

    public Card playRound(int chosenCard) {
        Random random = new Random();

        if (player2.getSelectedCards().isEmpty()) return null;
        if (player1.getSelectedCards().isEmpty()) return null;

        Card card1 = player1.getSelectedCards().get(chosenCard);
//        Card card2 = player2.getSelectedCards().get(player2.getSelectedCards().size()-1);
        int randomIndex = random.nextInt(player2.getCards().size());
        Card card2 = player2.getCards().get(randomIndex);

        if(card1.getValue() > card2.getValue())
            player1Score += 1000;
        else if (card1.getValue() < card2.getValue())
            player2Score += 1000;
        else
            System.out.println("The game is a draw!");

        player2.getSelectedCards().remove(card2);

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
