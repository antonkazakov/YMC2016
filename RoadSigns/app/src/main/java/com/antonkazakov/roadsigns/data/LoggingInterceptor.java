package com.antonkazakov.roadsigns.data;



import com.antonkazakov.roadsigns.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by antonkazakov on 23.09.16.
 */

public class LoggingInterceptor implements Interceptor{

    private final Interceptor loggingInterceptor;

    private LoggingInterceptor(){

        loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

    }

    public static Interceptor getLoggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return loggingInterceptor.intercept(chain);
    }


}
