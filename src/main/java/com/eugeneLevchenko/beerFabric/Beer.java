package com.eugeneLevchenko.beerFabric;

public class Beer {

    private String code;
    private float cell;
    private int quantity;

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", cell=" + cell + ", quantity=" + quantity + '}';
    }

    public Beer(String code, float volume, int quantity) {
        this.code = code;
        this.cell = volume;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public float getCell() {
        return cell;
    }

    public int getQuantity() {
        return quantity;
    }
}