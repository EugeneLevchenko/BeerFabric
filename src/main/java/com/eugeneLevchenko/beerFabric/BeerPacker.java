package com.eugeneLevchenko.beerFabric;

import java.util.*;

public class BeerPacker {

    private List<Beer> listOfBeers =new ArrayList<Beer>();
    private List<Byte> listOfAppropriateSizes=new ArrayList<Byte>();
    private List<BoxContent> listOfContents;
    private List<BeerBox> listOfResult=new ArrayList<BeerBox>();
    
    private byte[] arrOfPaxes={6,8,10,12,16,25};

   public List<BeerBox> divideOrder()
    {
        concatSameBeers();
        generateListOfBoxSizes(getEntireQuantity());
        fillBeerToBoxes();
        return listOfResult;
    }

    private List<BeerBox> fillBeerToBoxes()
    {
        for (int i=0;i<listOfAppropriateSizes.size();i++)
        {
            listOfContents=new ArrayList<BoxContent>();
            int restOfBeerBottles=0;
            for (int x=0;x<listOfBeers.size();x++)
            {
                restOfBeerBottles+=(listOfBeers.get(x).getQuantity()*listOfBeers.get(x).getCell())*2;
            }
            int curBeerBottles=0;
            if (curBeerBottles <= listOfAppropriateSizes.get(i)) {
                int sizeOfBox=(listOfAppropriateSizes.get(i))*2;
                while (curBeerBottles!=sizeOfBox)
                {
                    if (listOfBeers.isEmpty())
                    {
                        break;
                    }
                    int quantityNextBeer=(listOfBeers.get(0).getQuantity())*2;
                    int nextQuantity=(curBeerBottles+quantityNextBeer);
                    if (nextQuantity<=sizeOfBox)
                    {
                        listOfContents.add(new BoxContent(listOfBeers.get(0).getCode(), listOfBeers.get(0).getQuantity()));
                        curBeerBottles += (listOfBeers.get(0).getQuantity() * listOfBeers.get(0).getCell())*2;
                        restOfBeerBottles -= curBeerBottles;
                        listOfBeers.remove(0);
                    }else {
                        int availableQuantity=sizeOfBox-curBeerBottles;
                        int half=(availableQuantity/2);
                        if (listOfBeers.get(0).getCell()==0.5)
                        {
                            half*=2;
                        }
                        listOfContents.add(new BoxContent(listOfBeers.get(0).getCode(), half));
                        int changed=listOfBeers.get(0).getQuantity()-half;
                        listOfBeers.set(0,new Beer(listOfBeers.get(0).getCode(),listOfBeers.get(0).getCell(),changed));
                        curBeerBottles += availableQuantity;
                    }
                }
            }
            listOfResult.add(new BeerBox(listOfAppropriateSizes.get(i),listOfContents));
        }
        System.out.println(listOfResult);
        return listOfResult;
    }

   private List<Byte> generateListOfBoxSizes(double actualQuantity)
    {
        double remainder=-1.764;//it can be any number,just for initialization
        double quotient=-1;
        double tempInput=actualQuantity;
        for (int i=0;i<arrOfPaxes.length;i++)
        {
            if (actualQuantity<arrOfPaxes[0])
            {
                listOfAppropriateSizes.add(arrOfPaxes[0]);
                break;
            }
            quotient=tempInput/arrOfPaxes[i];
            if(quotient<1)
            {
                remainder=tempInput-arrOfPaxes[i];
                remainder=Math.abs(remainder);
                if (remainder<=1)
                {
                    listOfAppropriateSizes.add(arrOfPaxes[i]);
                    break;
                }
                else {
                    for (int z = i - 1; z > -1; z--)
                    {
                        remainder=tempInput-arrOfPaxes[z];
                        if (remainder>=arrOfPaxes[0]-1)
                        {
                            listOfAppropriateSizes.add(arrOfPaxes[z]);
                            tempInput-=arrOfPaxes[z];
                            i=-1;
                            break;
                        }
                    }
                }
            }
            else if (quotient==1)
            {
                listOfAppropriateSizes.add(arrOfPaxes[i]);
                break;
            }
            else if (quotient>1){
                if (i==arrOfPaxes.length-1)
                {
                    remainder=tempInput-arrOfPaxes[i];
                    if (remainder<arrOfPaxes[0]-1)
                    {
                        for (int z = i - 1; z > -1; z--)
                        {
                            remainder=tempInput-arrOfPaxes[z];
                            if (remainder>=arrOfPaxes[0]-1)
                            {
                                listOfAppropriateSizes.add(arrOfPaxes[z]);
                                tempInput-=arrOfPaxes[z];
                                i=-1;
                                break;
                            }
                        }
                    }
                    else {
                        listOfAppropriateSizes.add(arrOfPaxes[i]);
                        tempInput-=arrOfPaxes[i];
                        i=-1;
                        continue;
                    }
                }
            }
        }
        return listOfAppropriateSizes;
    }

    private double getEntireQuantity()
    {
        double entireQuantity=0;
        for (int i=0;i<listOfBeers.size();i++)
        {
            entireQuantity+=listOfBeers.get(i).getCell()*listOfBeers.get(i).getQuantity();
        }
        if (entireQuantity%1!=0)
        {
            entireQuantity+=0.5;
        }
        return entireQuantity;
    }

   public List<Beer> addBeerToOrderList(String code,float volume,int quantity)
    {
        if (!isTextValid(code))
        {
            try {
                throw new IllegalArgumentException();
            }
            catch (IllegalArgumentException e){
                System.out.println("Your code of beer is empty!");
                System.exit(0);
            }
        }

        if (!isVolumeValid(volume))
        {
            try {
                throw new IllegalArgumentException();
            }
            catch (IllegalArgumentException e){
                System.out.println("Volume is incorrect!");
                System.exit(0);
            }
        }

        if (!isQuantityValid(quantity))
        {
            try {
                throw new IllegalArgumentException();
            }
            catch (IllegalArgumentException e){
                System.out.println("Quantity is incorrect!");
                System.exit(0);
            }
        }

        listOfBeers.add(new Beer(code,volume,quantity));
        return listOfBeers;
    }

    private boolean isTextValid(String textToCheck) {
        return textToCheck. matches(".*\\w.*");
    }

    private boolean isVolumeValid(float volume)
    {
        if (volume==0.5||volume==1)
        {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isQuantityValid(int quantity)
    {
        if (quantity>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    private List<Beer> concatSameBeers()
    {
        for (int i = 0; i < listOfBeers.size(); i++) {
            for (int j = i+1; j < listOfBeers.size(); j++) {
                if (listOfBeers.get(i).getCode().equals(listOfBeers.get(j).getCode()))
                {
                    if (listOfBeers.get(i).getCell()==(listOfBeers.get(j).getCell()))
                    {
                        int tempQuantity= listOfBeers.get(i).getQuantity()+listOfBeers.get(j).getQuantity();
                        listOfBeers.set(i,new Beer(listOfBeers.get(i).getCode(),listOfBeers.get(i).getCell(),tempQuantity));
                        listOfBeers.remove(j);
                    }
                }
            }
        }
        System.out.println(listOfBeers);
        return listOfBeers;
    }

    public List<BeerBox> getListOfResult() {
        return listOfResult;
    }
}