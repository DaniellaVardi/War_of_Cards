package com.example.war_of_cards.Model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Card> Cards;

    public Shop() {
        this.Cards = new ArrayList<>();
        // Add initial cards to the shop
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
