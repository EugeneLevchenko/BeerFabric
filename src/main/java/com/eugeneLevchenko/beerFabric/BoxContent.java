package com.eugeneLevchenko.beerFabric;

public class BoxContent {

    private String name;
    private  int quantity;

    public BoxContent(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" + "name='" + name + '\'' + ", quantity=" + quantity + '}';
    }
}