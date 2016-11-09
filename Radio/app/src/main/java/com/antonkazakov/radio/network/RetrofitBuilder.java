package com.antonkazakov.radio.network;

import android.support.annotation.NonNull;

import com.antonkazakov.radio.Config;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class RetrofitBuilder {

    @NonNull
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor.getLoggingInterceptor())
            .addNetworkInterceptor(chain -> chain.proceed(provideRequest(chain)))
            .build();



    @NonNull
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    @NonNull
    public static <T> T getRetrofitService(Class<T> tClass) {
        return  retrofit.create(tClass);
    }


    @NonNull
    private static Request provideRequest(Interceptor.Chain chain){

        return chain.request().newBuilder()
                .method(chain.request().method(), chain.request().body())
                .build();
    }

}
