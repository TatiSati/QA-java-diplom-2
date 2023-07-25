package model;

import java.util.ArrayList;


public class OrderCreate {
    private ArrayList<String> ingredients;

    public OrderCreate(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {

        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {

        this.ingredients = ingredients;
    }

}
