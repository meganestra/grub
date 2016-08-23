package com.example.user.grub;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by user on 19/08/2016.
 */
public class NutritionSearch {

    private int mCalories;

    public NutritionSearch(){
        mCalories = 0;
    }

    public int getCalories(){
        return mCalories;
    }

    public void getFoodData(String quantity, String measure, String description) {

        System.out.println("Im inside this");

        String url = "https://api.edamam.com/api/nutrition-data?app_id=b75772a6&app_key=4dfaf6c41e1ba0466e43de674d77fd06&ingr=" + quantity + "%20" + measure + "%20" + description;

        System.out.println(url);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONObject jsonObject) {

                Log.d("json object", jsonObject.toString());

                if (jsonObject != null && jsonObject.has("calories")) {

                    Log.d("json object", jsonObject.toString());

                    mCalories = jsonObject.optInt("calories");

                }
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Log.e("Grub:", "failure: " + statusCode + " " + throwable.getMessage());
            }

        });
    }
}
