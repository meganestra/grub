package com.example.user.grub;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 18/08/2016.
 */
public class SavedTextPreferences {

    private static final String PREF_SAVEDTEXT = "savedText";

    public static void setStoredText(Context context, String text){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(PREF_SAVEDTEXT, text);

        editor.apply();

    }

    public static String getStoredText(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String text = sharedPreferences.getString(PREF_SAVEDTEXT, null);

        return text;

    }


}
