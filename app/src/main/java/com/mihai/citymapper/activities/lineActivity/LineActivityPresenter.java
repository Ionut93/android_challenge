package com.mihai.citymapper.activities.lineActivity;

import android.location.Location;

import com.mihai.citymapper.Constants;
import com.mihai.citymapper.api.RetrfofitGenerator;
import com.mihai.citymapper.api.TflApi;
import com.mihai.citymapper.models.StopPoint;
import com.mihai.citymapper.viewModels.StopPointsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mihai on 26/11/2017.
 */

public class LineActivityPresenter {

    private StopPointsViewModel stopPointsViewModel;
    private TflApi tflApi;
    private int minDistancePosition;

    public LineActivityPresenter(StopPointsViewModel stopPointsViewModel) {
        this.stopPointsViewModel = stopPointsViewModel;
        tflApi = RetrfofitGenerator.createAPI(TflApi.class);
    }

    public void getLineStops(String lineId) {
        tflApi.getStopPointsForLine(lineId, Constants.APP_ID, Constants.KEY)
                .enqueue(new Callback<List<StopPoint>>() {
                    @Override
                    public void onResponse(Call<List<StopPoint>> call, Response<List<StopPoint>> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            List<StopPoint> stopPointList = response.body();
                            updateDistance(stopPointList);
                            stopPointsViewModel.getStopPointMutableLiveData().setValue(stopPointList);
                        } 
                    }

                    @Override
                    public void onFailure(Call<List<StopPoint>> call, Throwable t) {

                    }
                });
    }

    private void updateDistance(List<StopPoint> stopPoints) {
        Location myLocation = new Location("");
        myLocation.setLatitude(Constants.LATITUDE);
        myLocation.setLongitude(Constants.LONGITUDE);
        Location stopPointLocation = new Location("");
        float minDistance = 0;
        minDistancePosition = 0;
        int position = 0;
        for (StopPoint stopPoint : stopPoints) {
            stopPointLocation.setLongitude(stopPoint.getLon());
            stopPointLocation.setLatitude(stopPoint.getLatitude());
            float distanceToStop = myLocation.distanceTo(stopPointLocation);
            if (minDistance == 0 || distanceToStop < minDistance) {
                minDistance = distanceToStop;
                minDistancePosition = position;
            }
            stopPoint.setDistanceToStopPoint(distanceToStop);
            position++;
        }
    }

    public int getMinDistancePosition() {
        return minDistancePosition;
    }

    public void setMinDistancePosition(int minPosition) {
        this.minDistancePosition = minPosition;
    }
}
