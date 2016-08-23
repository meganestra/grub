package com.example.user.grub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * Created by user on 19/08/2016.
 */
public class AskActivity extends AppCompatActivity {

    TextView mHeaderAsk;
    EditText mDescriptionInputAsk;
    EditText mDateConsumedAsk;
    EditText mQuantityAsk;
    EditText mMeasureAsk;
    EditText mMealTypeAsk;
    Button mAskEntry;
    TextView mCaloriesAnswer;
    NutritionSearch mSearch;
    Button mAskAddEntry;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        mHeaderAsk = (TextView)findViewById(R.id.header_ask);
        mDescriptionInputAsk = (EditText)findViewById(R.id.description_input_ask);
        mDateConsumedAsk = (EditText)findViewById(R.id.date_consumed_input_ask);
        mQuantityAsk = (EditText)findViewById(R.id.quantity_input_ask);
        mMeasureAsk = (EditText)findViewById(R.id.measure_input_ask);
        mMealTypeAsk = (EditText)findViewById(R.id.meal_type_input_ask);

        mAskEntry = (Button)findViewById(R.id.ask_calories_button);
        mAskAddEntry = (Button)findViewById(R.id.ask_add_button);
        mAskAddEntry.setVisibility(View.INVISIBLE);

        mCaloriesAnswer = (TextView)findViewById(R.id.calories_answer);

        mAskEntry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String description = mDescriptionInputAsk.getText().toString();
                String dateConsumed = mDateConsumedAsk.getText().toString();
                String quantity = mQuantityAsk.getText().toString();
                String measure = mMeasureAsk.getText().toString();
                String mealType = mMealTypeAsk.getText().toString();

                mSearch = new NutritionSearch();
                mSearch.setCallingActivity(AskActivity.this);
                mSearch.getFoodData(quantity, measure, description);

                mAskAddEntry.setVisibility(View.VISIBLE);
                String calories = mCaloriesAnswer.getText().toString();
            }
        });

        mAskAddEntry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                String descriptionToSave = mDescriptionInputAsk.getText().toString();
                String dateConsumedToSave = mDateConsumedAsk.getText().toString();
                String quantityToSave = mQuantityAsk.getText().toString();
                String measureToSave = mMeasureAsk.getText().toString();
                String mealTypeToSave = mMealTypeAsk.getText().toString();
                String caloriesToSave = mCaloriesAnswer.getText().toString();

                openDatabase();
                insertIntoDB(descriptionToSave, dateConsumedToSave, quantityToSave, measureToSave, mealTypeToSave, caloriesToSave);
            }
        });
    }

    public void showCals(){
        int calories = mSearch.getCalories();
        mCaloriesAnswer.setText(calories + "");
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
    }

    protected void insertIntoDB(String description, String dateConsumed, String quantity, String measure, String mealType, String calories){

        String SQL_QUERY = "INSERT INTO food (description, dateConsumed, quantity, measure, calories, meal_type) VALUES ('"+description+"', '"+dateConsumed+"', '"+quantity+"', '"+measure+"', '"+calories+"', '"+mealType+"');";

        Log.d("SQL", SQL_QUERY);

        db.execSQL(SQL_QUERY);
        Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_LONG).show();
    }

}











