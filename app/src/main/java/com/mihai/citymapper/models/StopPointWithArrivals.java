package com.mihai.citymapper.models;

import java.util.List;

/**
 * Created by mihai on 25/11/2017.
 */

public class StopPointWithArrivals {

    private StopPoint stopPoint;
    private List<Arrival> stopPointArrivals;

    public StopPointWithArrivals(StopPoint stopPoint, List<Arrival> stopPointArrivals) {
        this.stopPoint = stopPoint;
        this.stopPointArrivals = stopPointArrivals;
    }

    public StopPointWithArrivals(StopPoint stopPoint) {
        this.stopPoint = stopPoint;
    }

    public StopPoint getStopPoint() {
        return stopPoint;
    }

    public List<Arrival> getStopPointArrivals() {
        return stopPointArrivals;
    }

    public void setStopPoint(StopPoint stopPoint) {
        this.stopPoint = stopPoint;
    }

    public void setStopPointArrivals(List<Arrival> stopPointArrivals) {
        this.stopPointArrivals = stopPointArrivals;
    }
}
