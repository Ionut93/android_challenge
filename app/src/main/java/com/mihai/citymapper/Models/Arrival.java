package com.mihai.citymapper.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mihai on 22/11/2017.
 */

public class Arrival {

    @SerializedName("id")
    private String id;

    @SerializedName("stationName")
    private String stationName;

    @SerializedName("destinationName")
    private String destinationName;


}
