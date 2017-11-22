package com.mihai.citymapper.Api;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mihai on 22/11/2017.
 */

public class RetrfofitGenerator {

    private static final String API_BASE_URL = "https://api.tfl.gov.uk/";

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor);

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofitInstance = retrofitBuilder.build();

    public static <T> T createAPI(Class<T> apiClass) {
        return retrofitInstance.create(apiClass);
    }
}
