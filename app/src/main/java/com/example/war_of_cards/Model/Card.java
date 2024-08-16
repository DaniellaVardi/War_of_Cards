package com.example.war_of_cards.Model;

import com.example.war_of_cards.Database.DatabaseService;
import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id; // Unique identifier for the card
    private String name;
    private int value;
    private int power;
    private int imageResource;
    private boolean selected;
    private static DatabaseService dbs;

    public Card() {
        this.selected = false; // Initialize as not selected
        dbs = new DatabaseService("Cards");
    }

    public Card(String id, String name, int value, int power, int imageResource) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.power = power;
        this.imageResource = imageResource;
        this.selected = false; // Initialize as not selected
        dbs = new DatabaseService("Cards");
    }

    // Getters and setters for all fields, including id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", power=" + power +
                ", imageResource=" + imageResource +
                ", selected=" + selected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id.equals(card.id); // Compare based on card id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash based on card id
    }
}
