package com.mihai.citymapper.activities.mainActivity;

import android.util.Log;

import com.mihai.citymapper.api.Responses.StopPointsResponse;
import com.mihai.citymapper.api.RetrfofitGenerator;
import com.mihai.citymapper.api.TflApi;
import com.mihai.citymapper.models.Arrival;
import com.mihai.citymapper.models.StopPoint;
import com.mihai.citymapper.models.StopPointWithArrivals;
import com.mihai.citymapper.viewModels.StopPointWithArrivalsViewModel;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mihai.citymapper.Constants.APP_ID;
import static com.mihai.citymapper.Constants.KEY;
import static com.mihai.citymapper.Constants.LATITUDE;
import static com.mihai.citymapper.Constants.LONGITUDE;
import static com.mihai.citymapper.Constants.STOP_TYPE_TUBES;

/**
 * Created by mihai on 24/11/2017.
 */

public class MainActivityPresenter {

    private final String TAG = MainActivityPresenter.class.getSimpleName();
    private final TflApi tflApi;
    private final MainActivityView view;

    private Call<StopPointsResponse> stopPointsResponseCall;
    private Call<List<Arrival>> arrivalsResponseCall;
    private StopPointWithArrivalsViewModel model;

    private List<StopPointWithArrivals> stopPointWithArrivals;


    public MainActivityPresenter(MainActivityView view, StopPointWithArrivalsViewModel model) {
        tflApi = RetrfofitGenerator.createAPI(TflApi.class);
        stopPointWithArrivals = new ArrayList<>();
        this.view = view;
        this.model = model;

    }

    public void getNearbyStops() {
        if (view != null) {
            view.showProgress();
        }
        stopPointsResponseCall = tflApi.getStopPoints(LONGITUDE, LATITUDE, STOP_TYPE_TUBES, APP_ID, KEY);
        stopPointsResponseCall.enqueue(new Callback<StopPointsResponse>() {
            @Override
            public void onResponse(Call<StopPointsResponse> call, Response<StopPointsResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {
                    if (response.body() != null) {
                        StopPointsResponse stopPointsResponse = response.body();
                        if (stopPointsResponse != null && stopPointsResponse.getStopPointList().size() > 0) {
                            List<StopPoint> stopPointList = stopPointsResponse.getStopPointList();
                            stopPointWithArrivals.clear();
                            for (StopPoint stopPoint : stopPointList) {
                                stopPointWithArrivals.add(new StopPointWithArrivals(stopPoint));
                            }
                            getArrivalsForStops(0);
                        } else {
                            //TODO show message with no stoppoints arround
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<StopPointsResponse> call, Throwable t) {
                if (t instanceof UnknownHostException) {
                    if (view != null) {
                        view.showNoInternetErrorMessage("To be able to provide exact data, you must have a valid internet connection");
                    }
                }
                if (view != null) {
                    view.hideProgress();
                }
                //TODO show message with error
            }
        });
    }

    private void getArrivalsForStops(final int currentStopPointPosition) {
        if (stopPointWithArrivals.size() > currentStopPointPosition) {
            StopPointWithArrivals stopPoint = stopPointWithArrivals.get(currentStopPointPosition);
            arrivalsResponseCall = tflApi.getArrivals(stopPoint.getStopPoint().getId(), APP_ID, KEY);
            arrivalsResponseCall.enqueue(new Callback<List<Arrival>>() {
                @Override
                public void onResponse(Call<List<Arrival>> call, Response<List<Arrival>> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        List<Arrival> arrivals = response.body();
                        if (arrivals != null) {
                            Collections.sort(arrivals);
                        }
                        stopPointWithArrivals.get(currentStopPointPosition).setStopPointArrivals(arrivals);
                        if (stopPointWithArrivals.size() > currentStopPointPosition + 1) {
                            getArrivalsForStops(currentStopPointPosition + 1);
                        } else {
                            model.getData().setValue(stopPointWithArrivals);
                            if (view != null) {
                                view.hideProgress();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Arrival>> call, Throwable t) {
                    //TODO show message with error
                    Log.e(TAG, "onFailure " + t.getMessage() );
                    if (stopPointWithArrivals.size() > currentStopPointPosition + 1) {
                        getArrivalsForStops(currentStopPointPosition + 1);
                    } else {
                        model.getData().setValue(stopPointWithArrivals);
                        if (view != null) {
                            view.hideProgress();
                        }
                    }
                }
            });
        }
    }

    public void refreshArrivals() {
        if (stopPointWithArrivals != null && stopPointWithArrivals.size() > 0) {
            getArrivalsForStops(0);
        } else {
            getNearbyStops();
        }
    }

    public void cancelRequests() {
        if (stopPointsResponseCall != null) {
            stopPointsResponseCall.cancel();
        }
        if (arrivalsResponseCall != null) {
            arrivalsResponseCall.cancel();
        }
    }

    public void setStopPointWithArrivals(List<StopPointWithArrivals> stopPointWithArrivals) {
        this.stopPointWithArrivals = stopPointWithArrivals;
    }
}
