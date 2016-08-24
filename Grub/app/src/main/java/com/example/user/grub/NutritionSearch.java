package com.example.user.grub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 19/08/2016.
 */
public class NutritionSearch {

    private int mCalories;
    TextView mAnswer;
    View view;
    Activity mCaller;

    public NutritionSearch(){
        mCalories = 0;
    }

    public int getCalories(){
        return mCalories;
    }

    public void getFoodData(String quantity, String measure, String description) {

        this.view = view;

        System.out.println("Im inside this");

        String url = "https://api.edamam.com/api/nutrition-data?app_id=b75772a6&app_key=4dfaf6c41e1ba0466e43de674d77fd06&ingr=" + quantity + "%20" + measure + "%20" + description;

        System.out.println(url);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONObject jsonObject) {

                if (jsonObject != null && jsonObject.has("calories")) {
                    mCalories = jsonObject.optInt("calories");
                }

                AskActivity callingActivity = (AskActivity) mCaller;
                callingActivity.showCals();
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Log.e("Grub:", "failure: " + statusCode + " " + throwable.getMessage());
            }

        });
    }

    public void setCallingActivity(Activity callingActivity) {
        mCaller = callingActivity;
    }
}