package com.mihai.citymapper.Api;

import com.mihai.citymapper.Api.Responses.StopPointsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
