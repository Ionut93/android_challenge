package com.mihai.citymapper.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mihai on 22/11/2017.
 */

public class Arrival implements Comparable<Arrival> {

    @SerializedName("id")
    private String id;

    @SerializedName("stationName")
    private String stationName;

    @SerializedName("destinationName")
    private String destinationName;

    @SerializedName("expectedArrival")
    private String expectedArrival;

    @SerializedName("timeToStation")
    private int timeToStation;

    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    @Override
    public int compareTo(@NonNull Arrival arrival) {

        return this.timeToStation - arrival.timeToStation;
    }
}
