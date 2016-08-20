package com.example.user.grub;

import java.util.ArrayList;

/**
 * Created by user on 19/08/2016.
 */
public class Diary {

    private ArrayList<Food> entries;

    public Diary (Goal goal) {
        this.entries = new ArrayList<Food>();
    }

    public ArrayList<Food> getEntries(){
        return this.entries;
    }

    public ArrayList<Food> getEntries(String date){

        ArrayList<Food> dateFilteredEntries = new ArrayList<Food>();

        for(Food food : this.entries){
            if(food.getDateConsumed() == date){
                dateFilteredEntries.add(food);
            }
        }
        return dateFilteredEntries;
    }

    public int entriesCount(){
        return this.entries.size();
    }

    public void receiveEntry(Food entry){
        this.entries.add(entry);
    }

    public int totalCalories(){
        int total = 0;
        for(Food food : this.entries){
            total += food.getCalories();
        }
        return total;
    }

    public int totalCalories(String date){
        int total = 0;
        for(Food food : this.entries){
            if(food.getDateConsumed() == date) {
                total += food.getCalories();
            }
        }
        return total;
    }

}
