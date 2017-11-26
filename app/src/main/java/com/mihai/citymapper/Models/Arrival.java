package com.mihai.citymapper.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mihai on 22/11/2017.
 */

public class Arrival implements Comparable<Arrival>, Parcelable {

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

    @SerializedName("lineId")
    private String lineId;

    protected Arrival(Parcel in) {
        id = in.readString();
        stationName = in.readString();
        destinationName = in.readString();
        expectedArrival = in.readString();
        timeToStation = in.readInt();
        lineId = in.readString();
    }

    public static final Creator<Arrival> CREATOR = new Creator<Arrival>() {
        @Override
        public Arrival createFromParcel(Parcel in) {
            return new Arrival(in);
        }

        @Override
        public Arrival[] newArray(int size) {
            return new Arrival[size];
        }
    };

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

    public String getLineId() {
        return lineId;
    }

    @Override
    public int compareTo(@NonNull Arrival arrival) {

        return this.timeToStation - arrival.timeToStation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(stationName);
        parcel.writeString(destinationName);
        parcel.writeString(expectedArrival);
        parcel.writeInt(timeToStation);
        parcel.writeString(lineId);
    }
}
