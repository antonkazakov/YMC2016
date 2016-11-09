package com.antonkazakov.roadsigns.data;

import android.support.annotation.NonNull;

import com.antonkazakov.roadsigns.Config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by antonkazakov on 21.10.16.
 */

public class RetrofitFactory {

    @NonNull
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor.getLoggingInterceptor())
            .addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    return chain.proceed(provideRequest(chain));
                }
            })
            //.readTimeout(Config.TIMEOUT, TimeUnit.MILLISECONDS)
            //.connectTimeout(Config.TIMEOUT, TimeUnit.MILLISECONDS)
            .build();



    @NonNull
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @NonNull
    public static RetrofitService getRetrofitService() {
        return retrofit.create(RetrofitService.class);
    }


    @NonNull
    private static Request provideRequest(Interceptor.Chain chain){
        return chain.request().newBuilder()
                .method(chain.request().method(), chain.request().body())
                .build();
    }




}
