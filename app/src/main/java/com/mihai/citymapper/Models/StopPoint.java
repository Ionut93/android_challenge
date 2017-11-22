package com.mihai.citymapper.Models;

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
}
