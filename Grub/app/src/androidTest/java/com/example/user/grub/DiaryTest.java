package com.example.user.grub;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Created by user on 20/08/2016.
 */
public class DiaryTest {

    Diary grubDiary;

    @Before
    public void before() {
        grubDiary = new Diary();
    }

    @Test
    public void diaryStartsEmpty(){
        assertEquals(0, grubDiary.entriesCount());
    }

}
