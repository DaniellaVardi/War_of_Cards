package com.example.war_of_cards.Model;

import com.example.war_of_cards.R;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        // Initialize the deck with 10 cards
//        cards.add(new Card("Peasant", 1000, 1000, R.drawable.ic_card_1000));
        cards.add(new Card("Elf", 2000, 2000, R.drawable.ic_card_2000));
        cards.add(new Card("Centaur", 3000, 3000, R.drawable.ic_card_3000));
        cards.add(new Card("Knight", 4000, 4000, R.drawable.ic_card_4000));
//        cards.add(new Card("Archer", 5000, 5000, R.drawable.ic_card_5000));
        cards.add(new Card("Dragon", 6000, 6000, R.drawable.ic_card_6000));
        cards.add(new Card("Sorcerer", 7000, 7000, R.drawable.ic_card_7000));
//        cards.add(new Card("King", 8000, 8000, R.drawable.ic_card_8000));
//        cards.add(new Card("Queen", 9000, 9000, R.drawable.ic_card_9000));
        cards.add(new Card("Phoenix", 10000, 10000, R.drawable.ic_card_10000));
    }

    public List<Card> getCards() {
        return cards;
    }
}
