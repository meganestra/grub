package com.example.user.grub;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by user on 19/08/2016.
 */
public class DiaryActivity extends AppCompatActivity {

    TextView mHeaderDiary;
    TextView mDiaryDate;
    TextView mDescription;
    TextView mBreakfastTableTitle;
    TableLayout mBreakfastTable;
    TextView mLunchTableTitle;
    TableLayout mLunchTable;
    TextView mDinnerTableTitle;
    TableLayout mDinnerTable;
    TextView mSnacksTableTitle;
    TableLayout mSnacksTable;

    private SQLiteDatabase db;
    private Cursor cursorBreakfast;
    private Cursor cursorLunch;
    private Cursor cursorDinner;
    private Cursor cursorSnacks;
    private Cursor cursorCount;

    private int mBreakfastCount;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        mHeaderDiary = (TextView)findViewById(R.id.header_diary);
        mDiaryDate = (TextView)findViewById(R.id.diary_date);
        mDescription = (TextView)findViewById(R.id.description);
        mBreakfastTable = (TableLayout)findViewById(R.id.table_breakfast);
        mLunchTable = (TableLayout)findViewById(R.id.table_lunch);
        mDinnerTable = (TableLayout)findViewById(R.id.table_dinner);
        mSnacksTable = (TableLayout)findViewById(R.id.table_snacks);
        mBreakfastTableTitle = (TextView)findViewById(R.id.table_breakfast_title);
        mLunchTableTitle = (TextView)findViewById(R.id.table_lunch_title);
        mDinnerTableTitle = (TextView)findViewById(R.id.table_dinner_title);
        mSnacksTableTitle = (TextView)findViewById(R.id.table_snacks_title);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedDate = extras.getString("selectedDate");

        String[] parts = selectedDate.split("/");
        String selectedDateFormatted = parts[0] + "." + parts[1] + "." + parts[2];

        Log.d("Grub: intent received ", selectedDateFormatted);
        mDiaryDate.setText(selectedDate);

        openDatabase();

        String BREAKFAST_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + selectedDateFormatted + "'" + "AND meal_type = 'breakfast'";
        cursorBreakfast = db.rawQuery(BREAKFAST_SELECT_SQL, null);
        cursorBreakfast.moveToFirst();
        createBreakfastTable(selectedDateFormatted);

        String LUNCH_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + selectedDateFormatted + "'" + "AND meal_type = 'lunch'";
        cursorLunch = db.rawQuery(LUNCH_SELECT_SQL, null);
        cursorLunch.moveToFirst();
        createLunchTable(selectedDateFormatted);

        String DINNER_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + selectedDateFormatted + "'" + "AND meal_type = 'dinner'";
        cursorDinner = db.rawQuery(DINNER_SELECT_SQL, null);
        cursorDinner.moveToFirst();
        createDinnerTable(selectedDateFormatted);

        String SNACK_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + selectedDateFormatted + "'" + "AND meal_type = 'snack'";
        cursorSnacks = db.rawQuery(SNACK_SELECT_SQL, null);
        cursorSnacks.moveToFirst();
        createSnacksTable(selectedDateFormatted);

        mBreakfastTableTitle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentBreakfastDetail = new Intent(DiaryActivity.this, BreakfastDetailActivity.class);
                startActivity(intentBreakfastDetail);
            }
        });

    }

    protected void openDatabase() {
        db = openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
    }

    protected void createBreakfastTable(String date){

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'breakfast'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        mBreakfastCount = cursorCount.getInt(0);
        cursorCount.close();

        for(int i=0; i < mBreakfastCount; i++){

            cursorBreakfast.moveToPosition(i);

            TableRow row = new TableRow(this);

            TextView description = new TextView(this);
            TextView dateConsumed = new TextView(this);
            TextView quantity = new TextView(this);
            TextView measure = new TextView(this);
            TextView calories = new TextView(this);
//            TextView mealType = new TextView(this);

            description.setText(cursorBreakfast.getString(1));
            dateConsumed.setText(cursorBreakfast.getString(2));
            quantity.setText(cursorBreakfast.getString(3));
            measure.setText(cursorBreakfast.getString(4));
            calories.setText(cursorBreakfast.getString(5));
//            mealType.setText(cursorBreakfast.getString(6));

            row.addView(description);
            row.addView(quantity);
            row.addView(measure);
            row.addView(calories);
//            row.addView(mealType);

            mBreakfastTable.addView(row);
        }
    }

    protected void createLunchTable(String date){

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'lunch'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        int count = cursorCount.getInt(0);
        cursorCount.close();

        for(int i=0; i < count; i++){

            cursorLunch.moveToPosition(i);

            TableRow row = new TableRow(this);

            TextView description = new TextView(this);
            TextView dateConsumed = new TextView(this);
            TextView quantity = new TextView(this);
            TextView measure = new TextView(this);
            TextView calories = new TextView(this);
//            TextView mealType = new TextView(this);

            description.setText(cursorLunch.getString(1));
            dateConsumed.setText(cursorLunch.getString(2));
            quantity.setText(cursorLunch.getString(3));
            measure.setText(cursorLunch.getString(4));
            calories.setText(cursorLunch.getString(5));
//            mealType.setText(cursorLunch.getString(6));

            row.addView(description);
            row.addView(quantity);
            row.addView(measure);
            row.addView(calories);
//            row.addView(mealType);

            mLunchTable.addView(row);
        }
    }

    protected void createDinnerTable(String date){

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'dinner'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        int count = cursorCount.getInt(0);
        cursorCount.close();

        for(int i=0; i < count; i++){

            cursorDinner.moveToPosition(i);

            TableRow row = new TableRow(this);

            TextView description = new TextView(this);
            TextView dateConsumed = new TextView(this);
            TextView quantity = new TextView(this);
            TextView measure = new TextView(this);
            TextView calories = new TextView(this);
//            TextView mealType = new TextView(this);

            description.setText(cursorDinner.getString(1));
            dateConsumed.setText(cursorDinner.getString(2));
            quantity.setText(cursorDinner.getString(3));
            measure.setText(cursorDinner.getString(4));
            calories.setText(cursorDinner.getString(5));
//            mealType.setText(cursorDinner.getString(6));

            row.addView(description);
            row.addView(quantity);
            row.addView(measure);
            row.addView(calories);
//            row.addView(mealType);

            mDinnerTable.addView(row);
        }
    }

    protected void createSnacksTable(String date){

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'snack'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        int count = cursorCount.getInt(0);
        cursorCount.close();

        for(int i=0; i < count; i++){

            cursorSnacks.moveToPosition(i);

            TableRow row = new TableRow(this);

            TextView description = new TextView(this);
            TextView dateConsumed = new TextView(this);
            TextView quantity = new TextView(this);
            TextView measure = new TextView(this);
            TextView calories = new TextView(this);
//            TextView mealType = new TextView(this);

            description.setText(cursorSnacks.getString(1));
            dateConsumed.setText(cursorSnacks.getString(2));
            quantity.setText(cursorSnacks.getString(3));
            measure.setText(cursorSnacks.getString(4));
            calories.setText(cursorSnacks.getString(5));
//            mealType.setText(cursorSnacks.getString(6));

            row.addView(description);
            row.addView(quantity);
            row.addView(measure);
            row.addView(calories);
//            row.addView(mealType);

            mSnacksTable.addView(row);
        }
    }
}







