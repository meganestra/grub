package com.example.user.grub;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by user on 23/08/2016.
 */
public class BreakfastDetailActivity extends AppCompatActivity {

    PieChartView mPiechart;
    PieChartData data;
    TableLayout mBreakfastTable;
    Cursor cursorTotalBreakfastCalories;
    Cursor cursorTotalLunchCalories;
    Cursor cursorTotalDinnerCalories;
    Cursor cursorTotalSnacksCalories;
    SQLiteDatabase db;

    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = false;
    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_detail);

        mPiechart = (PieChartView)findViewById(R.id.breakfast_chart);
        mBreakfastTable = (TableLayout)findViewById(R.id.table_breakfast);

        List<SliceValue> values = new ArrayList<SliceValue>();

            SliceValue breakfast = new SliceValue(totalBreakfastCalories(), ChartUtils.pickColor());
            SliceValue lunch = new SliceValue(totalLunchCalories(), ChartUtils.pickColor());
            SliceValue dinner = new SliceValue(totalDinnerCalories(), ChartUtils.pickColor());
            SliceValue snacks = new SliceValue(totalSnacksCalories(), ChartUtils.pickColor());

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


        Intent intent = getIntent();

    }

    protected void openDatabase() {
        db = openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
    }

    public int totalBreakfastCalories(){
        openDatabase();

        int totalBreakfastCalories = 0;

        String SQL_BREAKFAST_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'breakfast'";

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

        String SQL_LUNCH_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'lunch'";

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

        String SQL_DINNER_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'dinner'";

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

        String SQL_SNACKS_QUERY = "SELECT sum(calories) FROM food WHERE meal_type = 'snack'";

        cursorTotalSnacksCalories = db.rawQuery(SQL_SNACKS_QUERY, null);
        cursorTotalSnacksCalories.moveToFirst();
        totalSnacksCalories = cursorTotalSnacksCalories.getInt(0);
        cursorTotalSnacksCalories.close();
        db.close();

        return totalSnacksCalories;

    }

}
