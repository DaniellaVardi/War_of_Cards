package com.example.war_of_cards.Model;

import android.util.Log;

import com.example.war_of_cards.Database.DataBaseInterface;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int value;
    private int power;
    private int imageResource;
    private boolean selected; // Add this field

    public Card(String name, int value, int power, int imageResource) {
        this.name = name;
        this.value = value;
        this.power = power;
        this.imageResource = imageResource;
        this.selected = false; // Initialize as not selected
    }

    // Getters and setters for the new field
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Other existing getters and setters...
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

//    @Override
//    public void loadToDataBase() {
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference ref = db.getReference("Cards");
//
//        String id = this.getUid();
//        Log.d("Player", "loadToDataBase: " + id);
//
//        ref.child(id).setValue(this)
//                .addOnSuccessListener(aVoid -> Log.d("Player", "Data saved successfully"))
//                .addOnFailureListener(e -> Log.d("Player", "Failed to save data: " + e.getMessage()));
//    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", power=" + power +
                ", imageResource=" + imageResource +
                ", selected=" + selected +
                '}';
    }
}
