package com.mihai.citymapper.api.Responses;

import com.mihai.citymapper.models.Arrival;

import java.util.List;

/**
 * Created by mihai on 25/11/2017.
 */

public class ArrivalsResponse {
    private List<Arrival> arrivalList;
    public List<Arrival> getArrivalList() {
        return arrivalList;
    }
}
