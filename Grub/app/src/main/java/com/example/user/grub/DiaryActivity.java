package com.example.user.grub;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    TextView mBreakfastTableTitle;
    TableLayout mBreakfastTable;
    TextView mLunchTableTitle;
    TableLayout mLunchTable;
    TextView mDinnerTableTitle;
    TableLayout mDinnerTable;
    TextView mSnacksTableTitle;
    TableLayout mSnacksTable;
    String mSelectedDateFormatted;
    Typeface myCustomFont;
    TextView mDiaryTotalCalories;
    Cursor cursorTotal;
    Button mDetailViewButton;

    private SQLiteDatabase db;
    private Cursor cursorBreakfast;
    private Cursor cursorLunch;
    private Cursor cursorDinner;
    private Cursor cursorSnacks;
    private Cursor cursorCount;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/CutiveMono-Regular.ttf");

        mHeaderDiary = (TextView)findViewById(R.id.header_diary);
        mDiaryDate = (TextView)findViewById(R.id.diary_date);
        mBreakfastTable = (TableLayout)findViewById(R.id.table_breakfast);
        mLunchTable = (TableLayout)findViewById(R.id.table_lunch);
        mDinnerTable = (TableLayout)findViewById(R.id.table_dinner);
        mSnacksTable = (TableLayout)findViewById(R.id.table_snacks);
        mBreakfastTableTitle = (TextView)findViewById(R.id.table_breakfast_title);
        mLunchTableTitle = (TextView)findViewById(R.id.table_lunch_title);
        mDinnerTableTitle = (TextView)findViewById(R.id.table_dinner_title);
        mSnacksTableTitle = (TextView)findViewById(R.id.table_snacks_title);
        mDiaryTotalCalories = (TextView)findViewById(R.id.diary_total_calories);
        mDetailViewButton = (Button)findViewById(R.id.detail_view_button);
        mDetailViewButton.setTypeface(myCustomFont);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedDate = extras.getString("selectedDate");

        String[] parts = selectedDate.split("/");
        mSelectedDateFormatted = parts[0] + "." + parts[1] + "." + parts[2];

        Log.d("Grub: intent received ", mSelectedDateFormatted);
        mDiaryDate.setText(selectedDate);

        openDatabase();

        mDiaryTotalCalories.setText("total calories: " + calculateTotalCalories() + "");

        String BREAKFAST_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + mSelectedDateFormatted + "'" + "AND meal_type = 'breakfast'";
        cursorBreakfast = db.rawQuery(BREAKFAST_SELECT_SQL, null);
        cursorBreakfast.moveToFirst();
        createBreakfastTable(mSelectedDateFormatted);

        String LUNCH_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + mSelectedDateFormatted + "'" + "AND meal_type = 'lunch'";
        cursorLunch = db.rawQuery(LUNCH_SELECT_SQL, null);
        cursorLunch.moveToFirst();
        createLunchTable(mSelectedDateFormatted);

        String DINNER_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + mSelectedDateFormatted + "'" + "AND meal_type = 'dinner'";
        cursorDinner = db.rawQuery(DINNER_SELECT_SQL, null);
        cursorDinner.moveToFirst();
        createDinnerTable(mSelectedDateFormatted);

        String SNACK_SELECT_SQL = "SELECT * FROM food WHERE dateConsumed =" + "'" + mSelectedDateFormatted + "'" + "AND meal_type = 'snack'";
        cursorSnacks = db.rawQuery(SNACK_SELECT_SQL, null);
        cursorSnacks.moveToFirst();
        createSnacksTable(mSelectedDateFormatted);

        mDetailViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, ChartDetailActivity.class);
                intent.putExtra("selectedDate", mSelectedDateFormatted);
                startActivity(intent);
            }
        });
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
    }


    protected int calculateTotalCalories() {

        int total = 0;

        String REMAINING_SQL_QUERY = "SELECT sum(calories) FROM food WHERE dateConsumed =" + "'" + mSelectedDateFormatted + "'";
        cursorTotal = db.rawQuery(REMAINING_SQL_QUERY, null);
        cursorTotal.moveToFirst();
        total = cursorTotal.getInt(0);
//        cursorTotal.close();
//        db.close();

        return total;

    }

    protected void createBreakfastTable(String date){

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'breakfast'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        int count = cursorCount.getInt(0);
        cursorCount.close();

        if(count > 0) {

            for (int i = 0; i < count; i++) {

                cursorBreakfast.moveToPosition(i);

                TableRow row = new TableRow(this);

                TextView description = new TextView(this);
                description.setTypeface(myCustomFont);
                description.setTextSize(35);
                TextView dateConsumed = new TextView(this);
                dateConsumed.setTypeface(myCustomFont);
                dateConsumed.setTextSize(35);
                TextView quantity = new TextView(this);
                quantity.setTypeface(myCustomFont);
                quantity.setTextSize(35);
                TextView measure = new TextView(this);
                measure.setTypeface(myCustomFont);
                measure.setTextSize(35);
                TextView calories = new TextView(this);
                calories.setTypeface(myCustomFont);
                calories.setTextSize(35);

                description.setText(cursorBreakfast.getString(1));
                dateConsumed.setText(cursorBreakfast.getString(2));
                quantity.setText(cursorBreakfast.getString(3));
                measure.setText(cursorBreakfast.getString(4));
                calories.setText(cursorBreakfast.getString(5));

                row.addView(description);
                row.addView(quantity);
                row.addView(measure);
                row.addView(calories);

                mBreakfastTable.addView(row);
            }
        } else {

            mBreakfastTable.setVisibility(View.INVISIBLE);

        }
    }

    protected void createLunchTable(String date){

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'lunch'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        int count = cursorCount.getInt(0);
        cursorCount.close();

        if(count > 0 ) {

            for (int i = 0; i < count; i++) {

                cursorLunch.moveToPosition(i);

                TableRow row = new TableRow(this);

                TextView description = new TextView(this);
                description.setTypeface(myCustomFont);
                description.setTextSize(35);
                TextView dateConsumed = new TextView(this);
                dateConsumed.setTypeface(myCustomFont);
                dateConsumed.setTextSize(35);
                TextView quantity = new TextView(this);
                quantity.setTypeface(myCustomFont);
                quantity.setTextSize(35);
                TextView measure = new TextView(this);
                measure.setTypeface(myCustomFont);
                measure.setTextSize(35);
                TextView calories = new TextView(this);
                calories.setTypeface(myCustomFont);
                calories.setTextSize(35);

                description.setText(cursorLunch.getString(1));
                dateConsumed.setText(cursorLunch.getString(2));
                quantity.setText(cursorLunch.getString(3));
                measure.setText(cursorLunch.getString(4));
                calories.setText(cursorLunch.getString(5));

                row.addView(description);
                row.addView(quantity);
                row.addView(measure);
                row.addView(calories);

                mLunchTable.addView(row);
            }
        } else {

            mLunchTable.setVisibility(View.INVISIBLE);

        }
    }

    protected void createDinnerTable(String date){

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'dinner'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        int count = cursorCount.getInt(0);
        cursorCount.close();

        if(count > 0) {

            for (int i = 0; i < count; i++) {

                cursorDinner.moveToPosition(i);

                TableRow row = new TableRow(this);

                TextView description = new TextView(this);
                description.setTypeface(myCustomFont);
                description.setTextSize(35);
                TextView dateConsumed = new TextView(this);
                dateConsumed.setTypeface(myCustomFont);
                dateConsumed.setTextSize(35);
                TextView quantity = new TextView(this);
                quantity.setTypeface(myCustomFont);
                quantity.setTextSize(35);
                TextView measure = new TextView(this);
                measure.setTypeface(myCustomFont);
                measure.setTextSize(35);
                TextView calories = new TextView(this);
                calories.setTypeface(myCustomFont);
                calories.setTextSize(35);

                description.setText(cursorDinner.getString(1));
                dateConsumed.setText(cursorDinner.getString(2));
                quantity.setText(cursorDinner.getString(3));
                measure.setText(cursorDinner.getString(4));
                calories.setText(cursorDinner.getString(5));

                row.addView(description);
                row.addView(quantity);
                row.addView(measure);
                row.addView(calories);

                mDinnerTable.addView(row);
            }
        } else {

            mDinnerTable.setVisibility(View.INVISIBLE);

        }
    }

    protected void createSnacksTable(String date) {

        String COUNT_SQL = "SELECT COUNT(*) FROM food WHERE dateConsumed =" + "'" + date + "'" + "AND meal_type = 'snack'";

        cursorCount = db.rawQuery(COUNT_SQL, null);
        cursorCount.moveToFirst();
        int count = cursorCount.getInt(0);
        cursorCount.close();

        if (count > 0) {

            for (int i = 0; i < count; i++) {

                cursorSnacks.moveToPosition(i);

                TableRow row = new TableRow(this);

                TextView description = new TextView(this);
                description.setTypeface(myCustomFont);
                description.setTextSize(35);
                TextView dateConsumed = new TextView(this);
                dateConsumed.setTypeface(myCustomFont);
                dateConsumed.setTextSize(35);
                TextView quantity = new TextView(this);
                quantity.setTypeface(myCustomFont);
                quantity.setTextSize(35);
                TextView measure = new TextView(this);
                measure.setTypeface(myCustomFont);
                measure.setTextSize(35);
                TextView calories = new TextView(this);
                calories.setTypeface(myCustomFont);
                calories.setTextSize(35);

                description.setText(cursorSnacks.getString(1));
                dateConsumed.setText(cursorSnacks.getString(2));
                quantity.setText(cursorSnacks.getString(3));
                measure.setText(cursorSnacks.getString(4));
                calories.setText(cursorSnacks.getString(5));

                row.addView(description);
                row.addView(quantity);
                row.addView(measure);
                row.addView(calories);

                mSnacksTable.addView(row);
            }
        }  else {

            mSnacksTable.setVisibility(View.INVISIBLE);

        }
    }


}







