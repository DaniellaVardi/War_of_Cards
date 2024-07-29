package com.example.war_of_cards.Model;

import java.io.Serializable;

public class Card implements Serializable {
    private String name;
    private int price;
    private int value;
    private int imageResource;

    public Card() {}

    public Card(String name, int price, int value, int imageResource) {
        this.name = name;
        this.price = price;
        this.value = value;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Card setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getValue() {
        return value;
    }

    public Card setValue(int value) {
        this.value = value;
        return this;
    }

    public int getImageResource() {
        return imageResource;
    }

    public Card setImageResource(int imageResource) {
        this.imageResource = imageResource;
        return this;
    }
}
