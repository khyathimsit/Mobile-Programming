package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mWeatherDisplay;
    String mForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        mWeatherDisplay = (TextView) findViewById(R.id.tv_display_weather);
        Intent intentToStart = getIntent();
        if (intentToStart != null) {
            if(intentToStart.hasExtra(Intent.EXTRA_TEXT)) {
                mWeatherDisplay.setText(mForecast);
            }
        }
    }
}