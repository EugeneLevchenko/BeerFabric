package com.eugeneLevchenko.beerFabric;

public class App
{
    public static void main( String[] args )
    {
        BeerPacker bp=new BeerPacker();
        //input
        bp.addBeerToOrderList("Stella",1f,15);
        bp.addBeerToOrderList("Pax",0.5f,3);
        bp.addBeerToOrderList("Kozel",1f,10);

        //output
        bp.divideOrder();
    }
}