package com.mihai.citymapper.api;

import java.util.Collections;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mihai on 22/11/2017.
 */

public class RetrfofitGenerator {

    private static final String API_BASE_URL = "https://api.tfl.gov.uk/";

/*private static    ConnectionSpec spec = new
            ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();*/


    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    /*
        private static final ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build();
    */
    private static final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
            //  .connectionSpecs(Collections.singletonList(spec))
            .addInterceptor(loggingInterceptor);

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofitInstance = retrofitBuilder.build();

    public static <T> T createAPI(Class<T> apiClass) {
        return retrofitInstance.create(apiClass);
    }
}
