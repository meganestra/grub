package com.example.user.grub;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 19/08/2016.
 */
public class DiarySelectActivity extends AppCompatActivity {

    TextView mDateHeader;
    CalendarView mCalendar;
    Button mDateButton;
    Typeface myCustomFont;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_select);

        myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/CutiveMono-Regular.ttf");

        mDateHeader = (TextView)findViewById(R.id.headerDate);
        mDateHeader.setTypeface(myCustomFont);
        mCalendar = (CalendarView)findViewById(R.id.calendar);
        mDateButton = (Button)findViewById(R.id.dateButton);
        mDateButton.setTypeface(myCustomFont);

        mDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Log.d("Grub: ", "clicked");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String selectedDate = sdf.format(new Date(mCalendar.getDate()));

//                Date selectedDate = new Date(mCalendar.getDate());

                Log.d("Grub: date intent sent ", selectedDate);

                Intent intent = new Intent(DiarySelectActivity.this, DiaryActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                startActivity(intent);
            }
        });
    }
}

