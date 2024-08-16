package com.example.war_of_cards.Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.war_of_cards.Database.DatabaseService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uid = "";
    private String name;
    private String email;
    private int coins;
    private static ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;
    private static DatabaseService dbs;
    private static Player instancePlayer = null;
    private static Player instanceAI = null;

    private Player(String uid,String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.coins = 0;
        cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        dbs = new DatabaseService("Players");
    }

    private Player() {
        this.coins = 0;
        cards = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        dbs = new DatabaseService("Players");
    }

    public static void init(String uid, String name, String email, String type) {
        if(Objects.equals(type, "Player")) {
            instancePlayer = new Player(uid, name, email);
            Log.d("Player","jhfhff1:"+Player.getInstancePlayer().toString());

        }else {
            instanceAI = new Player();
            Log.d("Player","jhfhff2:"+Player.getInstancePlayer().toString());


        }
    }

    public static Player getInstancePlayerAI() {
        return instanceAI;
    }
    public static Player getInstancePlayer() {
        return instancePlayer;
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

    public static void setCards(ArrayList<Card> cs) {
        cards = cs;
    }

    public ArrayList<Card> getSelectedCards() {
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
        setCoins(coins);
        Log.d("Coins", "coins:" + coins);
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

    public static void save(String id) {
        Log.d("Player", "loadToDataBase: " + id);
        if (dbs != null) dbs.save(instancePlayer, id);
        else Log.e("Player", "DatabaseService not initialized");

    }

    public static void readPlayerData(String uid) {
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

    @NonNull
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

    public void updateFromDB() {
        dbs.updatePlayer(uid, new DatabaseService.DataLoadCallback() {

            @Override
            public void onDataLoaded(Player player) {
                instancePlayer = player;
            }

            @Override
            public void onDataLoadFailed(String error) {
                Log.d("Player", "Failed update player !");
            }

        });
    }
}
