package com.eugeneLevchenko.beerFabric;

import java.util.List;

public class BeerBox {

    private byte boxPack;
    private List<BoxContent> listOfContent;

    public BeerBox(byte boxPack,List<BoxContent> listOfContent) {
        this.boxPack = boxPack;
        this.listOfContent = listOfContent;
    }

    @Override
    public String toString() {
        return "BeerBox{" +
                "boxPack=" + boxPack +
                ", content:" + listOfContent +
                '}';
    }
}