package com.example.user.grub;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Created by user on 20/08/2016.
 */
public class DiaryTest {

    Diary grubDiary;
    Food apple;
    Food cereal;
    Goal goal;

    @Before
    public void before() {
        goal = new Goal(2000);
        grubDiary = new Diary(goal);
        apple = new Food("apple", "21.08.2016", 1, "unit", 35, MealType.SNACK);
        cereal = new Food("cereal", "20.08.2016", 1, "bowl", 220, MealType.BREAKFAST);
    }

    @Test
    public void diaryStartsEmpty(){
        assertEquals(0, grubDiary.entriesCount());
    }

    @Test
    public void diaryCanReceiveFoodEntry(){
        grubDiary.receiveEntry(apple);
        assertEquals(1, grubDiary.entriesCount());
    }

    @Test
    public void diaryCanTotalCaloriesOfAllEntries(){
        grubDiary.receiveEntry(apple);
        grubDiary.receiveEntry(cereal);
        assertEquals(255, grubDiary.totalCalories());
    }

    @Test
    public void diaryCanTotalCaloriesOfAllEntriesForSpecifiedDate(){
        grubDiary.receiveEntry(apple);
        grubDiary.receiveEntry(cereal);
        assertEquals(35, grubDiary.totalCalories("21.08.2016"));
    }

//    @Test
//    public void diaryCanReturnAllEntries(){
//        grubDiary.receiveEntry(apple);
//        grubDiary.receiveEntry(cereal);
//        assertEquals();
//    }

}