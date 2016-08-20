package com.example.user.grub;


/**
 * Created by user on 19/08/2016.
 */
public class Food {

    private String description;
    private String dateConsumed;
    private int quantity;
    private String measure;
    private int calories;
    private MealType meal;

    public Food (String description, String dateConsumed, int quantity, String measure, int calories, MealType meal) {
        this.description = description;
        this.dateConsumed = dateConsumed;
        this.quantity = quantity;
        this.measure = measure;
        this.calories = calories;
        this.meal = meal;
    }

    public String getDescription(){
        return this.description;
    }

    public String getDateConsumed(){
        return this.dateConsumed;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public String getMeasure(){
        return this.measure;
    }

    public int getCalories(){
        return this.calories;
    }

    public MealType getMeal(){
        return this.meal;
    }


}
