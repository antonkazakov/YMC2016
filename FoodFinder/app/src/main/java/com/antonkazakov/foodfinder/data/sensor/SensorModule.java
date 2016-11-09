package com.antonkazakov.foodfinder.data.sensor;

import android.content.Context;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import com.antonkazakov.foodfinder.data.sensor.AzimuthInteractor;
import com.antonkazakov.foodfinder.data.sensor.AzimuthInteractorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by antonkazakov on 30.10.16.
 */
@Module
public class SensorModule {

    @NonNull
    @Provides
    @Singleton
    public SensorManager provideSensorManager(@NonNull Context context) {
        return (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    @NonNull
    @Provides
    public static AzimuthInteractor provideAzimuth(@NonNull SensorManager sensorManager){
        return new AzimuthInteractorImpl(sensorManager);
    }

}
