package com.mihai.citymapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mihai.citymapper.Api.Responses.StopPointsResponse;
import com.mihai.citymapper.Api.RetrfofitGenerator;
import com.mihai.citymapper.Api.TflApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private final double LATITUDE = 51.519425;
    private final double LONGITUDE = -0.169666;
    private final String STOP_TYPE_TUBES = "NaptanMetroStation";
    private final String APP_ID = "3659ce01";
    private final String KEY = "8f84aaf7ba08702d50bef9bf4d438c20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TflApi tflApi = RetrfofitGenerator.createAPI(TflApi.class);

        tflApi.getStopPoints(LONGITUDE, LATITUDE, STOP_TYPE_TUBES, APP_ID, KEY)
                .enqueue(new Callback<StopPointsResponse>() {
                    @Override
                    public void onResponse(Call<StopPointsResponse> call, Response<StopPointsResponse> response) {
                        Log.e(TAG, "onResponse");
                    }

                    @Override
                    public void onFailure(Call<StopPointsResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure");
                    }
                });

    }
}
