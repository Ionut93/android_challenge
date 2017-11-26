package com.mihai.citymapper.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mihai on 22/11/2017.
 */

public class StopPoint {

    @SerializedName("id")
    private String id;

    @SerializedName("commonName")
    private String name;

    @SerializedName("distance")
    private double distanceToStopPoint;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double lon;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDistanceToStopPoint() {
        return distanceToStopPoint;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLon() {
        return lon;
    }

    public void setDistanceToStopPoint(double distanceToStopPoint) {
        this.distanceToStopPoint = distanceToStopPoint;
    }
}
