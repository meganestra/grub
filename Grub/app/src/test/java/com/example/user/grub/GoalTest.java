package com.example.user.grub;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 20/08/2016.
 */
public class GoalTest {

    Goal goal;
    Diary grubDiary;
    Food apple;
    Food cereal;
    Food pasta;

    @Before
    public void before(){
        goal = new Goal(2000);
        grubDiary = new Diary(goal);
        apple = new Food("apple", "21.08.2016", 1, "unit", 35, MealType.SNACK);
        cereal = new Food("cereal", "20.08.2016", 1, "bowl", 220, MealType.BREAKFAST);
        pasta = new Food("pasta", "21.08.2016", 10, "bowl", 2050, MealType.DINNER);
    }

    @Test
    public void canGetGoal(){
        assertEquals(2000, goal.getCalories());
    }

    @Test
    public void canCheckGoalHasBeenMet(){
        grubDiary.receiveEntry(pasta);
        assertEquals(false, goal.checkCaloriesConsumedUnderGoal(grubDiary, "21.08.2016"));
    }

    @Test
    public void canCheckGoalHasNotBeenMet(){
        grubDiary.receiveEntry(apple);
        grubDiary.receiveEntry(cereal);
        assertEquals(true, goal.checkCaloriesConsumedUnderGoal(grubDiary, "20.08.2016"));
    }

    @Test
    public void canCalculateRemainingCaloriesWhenGoalHasNotBeenMet(){
        grubDiary.receiveEntry(apple);
        grubDiary.receiveEntry(cereal);
        assertEquals(1780, goal.calculateRemainingCalories(grubDiary, "20.08.2016"));
    }

    @Test
    public void canCalculateRemainingCaloriesWhenGoalHasBeenMet(){
        grubDiary.receiveEntry(pasta);
        assertEquals(0, goal.calculateRemainingCalories(grubDiary, "21.08.2016"));
    }

}
