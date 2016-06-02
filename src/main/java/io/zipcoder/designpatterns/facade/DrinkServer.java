package io.zipcoder.designpatterns.facade;

import io.zipcoder.designpatterns.abstractfactory.BeerDispensingSystem;
import io.zipcoder.designpatterns.abstractfactory.CoffeeDispensingSystem;
import io.zipcoder.designpatterns.abstractfactory.DispensingSystem;
import io.zipcoder.designpatterns.factory.BeerGlass;
import io.zipcoder.designpatterns.factory.CoffeeCup;
import io.zipcoder.designpatterns.factory.Cup;

import java.util.Observable;


public class DrinkServer extends Observable{

    public CoffeeCup orderCoffee(){
        //This is potentially unsafe and a good opportunity to consider using Generics
        setChanged();
        notifyObservers("Coffee");
        return (CoffeeCup)serveDrink(new CoffeeDispensingSystem());
    }

    public BeerGlass orderBeer(){
        setChanged();
        notifyObservers("Beer");
        return (BeerGlass)serveDrink(new BeerDispensingSystem());
    }

    /* SodaCup and SodaDispensingSystem aren't implemented, so we can't do this just yet
    public SodaCup orderSoda(){

        return (SodaCup)serveDrink(new SodaDispensingSystem());
    }/**/

    private Cup serveDrink(DispensingSystem dispensingSystem){
        Cup cup = dispensingSystem.cupDispenser().dispense();
        cup.fill(dispensingSystem.drinkDispenser().dispense());
        //fill the cup
        return cup;
    }
}
