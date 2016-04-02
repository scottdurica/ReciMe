package com.emrox_riprap.recime.models;

/**
 * Created by scott on 12/12/2015.
 */
public class Ingredient {

    public String name;
    public String amount;
    public int recipeId;

    public Ingredient(){

    }

    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }
    public Ingredient(String name, String amount, int recipeId) {
        this.name = name;
        this.amount = amount;
        this.recipeId = recipeId;
    }
}
