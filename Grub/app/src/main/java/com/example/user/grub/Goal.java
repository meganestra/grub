package com.example.user.grub;

/**
 * Created by user on 19/08/2016.
 */
public class Goal {

    private int calories;

    public Goal (int calories){
        this.calories = calories;
    }

    public int getCalories(){
        return this.calories;
    }

    public boolean checkCaloriesConsumedUnderGoal(Diary diary, String date){
        boolean result = false;
        if(diary.totalCalories(date) <= this.calories){
            result = true;
        }
        return result;
    }

    public int calculateRemainingCalories(Diary diary, String date){
        if(checkCaloriesConsumedUnderGoal(diary, date)){
            return this.calories - diary.totalCalories(date);
        } else {
            return 0;
        }
    }

}
