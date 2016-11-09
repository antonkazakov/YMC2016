package com.antonkazakov.foodfinder.data.location;

import android.content.Context;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import com.antonkazakov.foodfinder.data.sensor.AzimuthInteractor;
import com.antonkazakov.foodfinder.data.sensor.AzimuthInteractorImpl;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by antonkazakov on 29.10.16.
 */
@Module
public class LocationModule {

    @NonNull
    @Provides
    @Singleton
    public static GoogleApiClient provideGoogleApiClient(@NonNull Context context){
        return new GoogleApiClient.Builder(context).addApi(LocationServices.API).build();
    }

    @NonNull
    @Provides
    public static LocationInteractor provideLocationProvider(@NonNull GoogleApiClient googleApiClient){
        return new LocationInteractorImpl(googleApiClient);
    }

}
