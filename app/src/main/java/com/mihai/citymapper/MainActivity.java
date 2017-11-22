package com.mihai.citymapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final double LATITUDE = 51.519425;
    private final double LONGITUDE = -0.169666;
    private final String STOPTYPETUBES = "NaptanMetroStation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
