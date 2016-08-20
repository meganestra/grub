package com.example.user.grub;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by user on 20/08/2016.
 */
public class FoodTest {

    Food apple;

    @Before
    public void before(){
        apple = new Food("apple", "21.08.2016", 1, "unit", 35, MealType.SNACK);
    }

    @Test
    public void canGetDescription(){
        assertEquals("apple", apple.getDescription());
    }

    @Test
    public void canGetDateConsumed(){
        assertEquals("21.08.2016", apple.getDateConsumed());
    }

    @Test
    public void canGetQuantity(){
        assertEquals(1, apple.getQuantity());
    }

    @Test
    public void canGetMeasure(){
        assertEquals("unit", apple.getMeasure());
    }

    @Test
    public void canGetCalories(){
        assertEquals(35, apple.getCalories());
    }

    @Test
    public void canGetMealType(){
        assertEquals(MealType.SNACK, apple.getMeal());
    }

}
