package com.antonkazakov.squats.di.modules;

import android.content.Context;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import com.antonkazakov.squats.data.SensorInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by antonkazakov on 17.10.16.
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
    @Singleton
    public SensorInteractor provideSensorIteractor(@NonNull SensorManager sensorManager) {
        return new SensorInteractor(sensorManager);
    }



}
