package com.mihai.citymapper.api;

import com.mihai.citymapper.api.Responses.ArrivalsResponse;
import com.mihai.citymapper.api.Responses.StopPointsResponse;
import com.mihai.citymapper.models.Arrival;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mihai on 22/11/2017.
 */

public interface TflApi {

    @GET("StopPoint")
    public Call<StopPointsResponse> getStopPoints(@Query("lon") double lon,
                                                  @Query("lat") double lat,
                                                  @Query("stoptypes") String stopType,
                                                  @Query("app_id") String appId,
                                                  @Query("app_key") String appKey);

    @GET("StopPoint/{id}/arrivals")
    public Call<List<Arrival>> getArrivals(@Path("id") String stopPointId,
                                           @Query("app_id") String appId,
                                           @Query("app_key") String appKey);

    @GET("StopPoint/{ids}")
    public Call getStopPointsForId(@Path("ids") String id,
                                   @Query("app_id") String appId,
                                   @Query("app_key") String appKey);
}
