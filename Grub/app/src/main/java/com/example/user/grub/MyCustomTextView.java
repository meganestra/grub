package com.example.user.grub;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 24/08/2016.
 */
public class MyCustomTextView extends TextView {

    public MyCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface((Typeface.createFromAsset(context.getAssets(), "fonts/CutiveMono-Regular.ttf")));
    }

}
