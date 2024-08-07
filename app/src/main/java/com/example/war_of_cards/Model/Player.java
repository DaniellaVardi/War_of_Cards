package com.example.war_of_cards.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable
    private String name;
    private String phoneNumber;
    private int coins;
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;

    public Player(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.coins = 0;
        this.cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
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
        Log.d(" ", "The round is a draw.");
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
