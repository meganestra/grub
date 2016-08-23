package com.example.user.grub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        mCaloriesAnswer = (TextView)findViewById(R.id.calories_answer);

        mSearch = new NutritionSearch();

        mAskEntry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("ask activity", "clicked ask button");

                String quantity = mQuantityAsk.getText().toString();
                Log.d("quantity", quantity);
                String measure = mMeasureAsk.getText().toString();
                Log.d("measure", measure);
                String description = mDescriptionInputAsk.getText().toString();
                Log.d("description", description);

                mSearch.getFoodData(quantity, measure, description);
                int calories = mSearch.getCalories();
//                if(calories == null){
//
//                }
                mCaloriesAnswer.setText(calories + "");

            }
        });
    }
}














