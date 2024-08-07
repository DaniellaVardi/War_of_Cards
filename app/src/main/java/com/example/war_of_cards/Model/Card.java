package com.example.war_of_cards.Model;

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
