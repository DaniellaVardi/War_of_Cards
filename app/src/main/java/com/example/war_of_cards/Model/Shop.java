package com.example.war_of_cards.Model;

import java.util.Arrays;
import java.util.List;

public class Shop {
    private List<Card> cards;

    public Shop() {
        this.cards = Arrays.asList(Deck.CARDS);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean purchaseCard(Player player, Card card) {
        if (player.getCoins() >= card.getValue()) {
            player.removeCoins(card.getValue());
            player.addCard(card);
            return true;
        }
        return false;
    }
}
