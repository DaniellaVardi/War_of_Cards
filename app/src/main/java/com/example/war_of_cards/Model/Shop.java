package com.example.war_of_cards.Model;

import java.util.Arrays;
import java.util.List;

public class Shop {
    private List<Card> cards;

    public Shop() {
        this.cards = Arrays.asList(Deck.DECK);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean purchaseCard(Player player, Card card) {
        if (player.getCoins() >= card.getPrice()) {
            player.removeCoins(card.getPrice());
            player.addCard(card);
            return true;
        }
        return false;
    }
}
