package com.example.user.grub;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by user on 23/08/2016.
 */
public class HomeActivity extends AppCompatActivity {

    TextView mHomeHeader;
    Typeface myCustomFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        mHomeHeader = (TextView) findViewById(R.id.home_header);
        myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/CutiveMono-Regular.ttf");
        mHomeHeader.setTypeface(myCustomFont);

        mHomeHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Grub.class);
                startActivity(intent);
            }
        });
    }
}
