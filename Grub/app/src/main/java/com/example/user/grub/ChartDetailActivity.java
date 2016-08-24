package com.example.user.grub;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by user on 23/08/2016.
 */
public class ChartDetailActivity extends AppCompatActivity {

    PieChartView mPiechart;
    PieChartData data;
    Cursor cursorTotalBreakfastCalories;
    Cursor cursorTotalLunchCalories;
    Cursor cursorTotalDinnerCalories;
    Cursor cursorTotalSnacksCalories;
    SQLiteDatabase db;
    TextView mChartHeader;
    String mSelectedDate;
    TextView mKeyBreakfast;
    TextView mKeyLunch;
    TextView mKeyDinner;
    TextView mKeySnacks;

    private boolean hasLabels = true;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = true;
    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;
    private boolean isExploded = true;
    private boolean hasLabelForSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);

//        mChartHeader = (TextView)findViewById(R.id.chart_header);
        mPiechart = (PieChartView)findViewById(R.id.diary_chart);
//
//        mKeyBreakfast = (TextView)findViewById(R.id.key_breakfast);
//        mKeyLunch = (TextView)findViewById(R.id.key_lunch);
//        mKeyDinner = (TextView)findViewById(R.id.key_dinner);
//        mKeySnacks = (TextView)findViewById(R.id.key_snacks);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mSelectedDate = extras.getString("selectedDate");
        Log.d("selectedDateCHART", mSelectedDate);

        List<SliceValue> values = new ArrayList<SliceValue>();

            SliceValue breakfast = new SliceValue(totalBreakfastCalories(), Color.rgb(203,232,107));
            SliceValue lunch = new SliceValue(totalLunchCalories(), Color.rgb(82,97,106));
            SliceValue dinner = new SliceValue(totalDinnerCalories(), Color.rgb(30,32,34));
            SliceValue snacks = new SliceValue(totalSnacksCalories(), Color.rgb(255,255,255));

            values.add(breakfast);
            values.add(lunch);
            values.add(dinner);
            values.add(snacks);

        data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);

        mPiechart.setPieChartData(data);

    }

    protected void openDatabase() {
        db = openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
    }

    public int totalBreakfastCalories(){
        openDatabase();

        int totalBreakfastCalories = 0;

        String SQL_BREAKFAST_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'breakfast' AND dateConsumed =" + "'" + mSelectedDate + "'";

        cursorTotalBreakfastCalories = db.rawQuery(SQL_BREAKFAST_QUERY, null);
        cursorTotalBreakfastCalories.moveToFirst();
        totalBreakfastCalories = cursorTotalBreakfastCalories.getInt(0);
        cursorTotalBreakfastCalories.close();
        db.close();

        return totalBreakfastCalories;

    }

    public int totalLunchCalories(){
        openDatabase();

        int totalLunchCalories = 0;

        String SQL_LUNCH_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'lunch' AND dateConsumed =" + "'" + mSelectedDate + "'";

        cursorTotalLunchCalories = db.rawQuery(SQL_LUNCH_QUERY, null);
        cursorTotalLunchCalories.moveToFirst();
        totalLunchCalories = cursorTotalLunchCalories.getInt(0);
        cursorTotalLunchCalories.close();
        db.close();

        return totalLunchCalories;

    }

    public int totalDinnerCalories(){
        openDatabase();

        int totalDinnerCalories = 0;

        String SQL_DINNER_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'dinner' AND dateConsumed =" + "'" + mSelectedDate + "'";

        cursorTotalDinnerCalories = db.rawQuery(SQL_DINNER_QUERY, null);
        cursorTotalDinnerCalories.moveToFirst();
        totalDinnerCalories = cursorTotalDinnerCalories.getInt(0);
        cursorTotalDinnerCalories.close();
        db.close();

        return totalDinnerCalories;

    }

    public int totalSnacksCalories(){
        openDatabase();

        int totalSnacksCalories = 0;

        String SQL_SNACKS_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'snack' AND dateConsumed =" + "'" + mSelectedDate + "'";

        cursorTotalSnacksCalories = db.rawQuery(SQL_SNACKS_QUERY, null);
        cursorTotalSnacksCalories.moveToFirst();
        totalSnacksCalories = cursorTotalSnacksCalories.getInt(0);
        cursorTotalSnacksCalories.close();
        db.close();

        return totalSnacksCalories;

    }

}
