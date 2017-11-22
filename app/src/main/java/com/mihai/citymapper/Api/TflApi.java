package com.mihai.citymapper.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mihai on 22/11/2017.
 */

public interface TflApi {

    @GET("Stoppoint")
    public Call getStopPoints(@Query("lon") double lon,
                              @Query("lat") double lat,
                              @Query("stoptypes") String stopType);
}
