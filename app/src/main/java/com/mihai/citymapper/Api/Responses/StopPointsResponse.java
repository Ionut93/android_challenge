package com.mihai.citymapper.api.Responses;

import com.google.gson.annotations.SerializedName;
import com.mihai.citymapper.models.StopPoint;

import java.util.List;

/**
 * Created by mihai on 22/11/2017.
 */

public class StopPointsResponse {

    @SerializedName("stopPoints")
    private List<StopPoint> stopPointList;

    public List<StopPoint> getStopPointList() {
        return stopPointList;
    }
}
