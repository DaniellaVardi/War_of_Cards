package com.example.war_of_cards.Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String phoneNumber;
    private int coins;
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;

    public Player(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.coins = 0;
        this.cards = new ArrayList<Card>();
        this.selectedCards = new ArrayList<Card>();
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Player setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getCoins() {
        return coins;
    }

    public Player setCoins(int coins) {
        this.coins = coins;
        return this;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player setCards(ArrayList<Card> cards) {
        this.cards = cards;
        return this;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public Player setSelectedCards(ArrayList<Card> selectedCards) {
        this.selectedCards = selectedCards;
        return this;
    }


    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCoins(int amount) {
        this.coins -= amount;
    }

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public void addSelectedCard(Card card) {
        this.selectedCards.add(card);
    }


    @Override
    public String toString() {
        return "Player{" +
                "coins=" + coins +
                ", cards=" + cards +
                ", selectedCards=" + selectedCards +
                ", name='" + name + '\'' +
                '}';
    }
}


