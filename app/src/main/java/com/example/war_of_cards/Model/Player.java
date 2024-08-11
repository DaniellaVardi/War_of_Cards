package com.example.war_of_cards.Model;

import android.util.Log;
import com.example.war_of_cards.Database.DataBaseInterface;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable, DataBaseInterface {
    private static final long serialVersionUID = 1L;
    private String uid = "";
    private String name;
    private String email;
    private int coins;
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;

    public Player(String name, String email) {
        this.name = name;
        this.email = email;
        this.coins = 0;
        this.cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
    }

    public Player() {
    }

    public String getUid() {
        return uid;
    }

    public Player setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Player setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getCoins() {
        return coins;
    }

    public Player setCoins(int coins) {
        this.coins = coins;
        Log.d("Player", "Coins updated: " + coins);
        return this;
    }

    public ArrayList<Card> getCards() {
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
        if (selectedCards.size() < 3) {
            this.selectedCards.add(card);
        } else {
            Log.d("Player", "Cannot select more than 3 cards.");
        }
    }

    public void removeSelectedCard(Card card) {
        this.selectedCards.remove(card);
    }

    public boolean isCardSelected(Card card) {
        return this.selectedCards.contains(card);
    }

    @Override
    public void loadToDataBase() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("Players");

        String id = this.getUid();
        Log.d("Player", "loadToDataBase: " + id);

        ref.child(id).setValue(this)
                .addOnSuccessListener(aVoid -> Log.d("Player", "Data saved successfully"))
                .addOnFailureListener(e -> Log.d("Player", "Failed to save data: " + e.getMessage()));
    }

    public void readPlayerData(String uid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Players").child(uid);

        ref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Player player = task.getResult().getValue(Player.class);
                if (player != null) {
                    Log.d("Player", "Player data: " + player.toString());
                } else {
                    Log.d("Player", "Player data is null");
                }
            } else {
                Log.d("Player", "Failed to read data: " + task.getException());
            }
        });
    }

    @Override
    public String toString() {
        return "Player{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", coins=" + coins +
                ", cards=" + cards +
                ", selectedCards=" + selectedCards +
                '}';
    }
}
