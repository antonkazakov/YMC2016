package com.antonkazakov.foodfinder;

import android.app.Application;
import android.content.Context;

import com.antonkazakov.foodfinder.data.location.LocationModule;
import com.antonkazakov.foodfinder.data.remote.NetworkModule;
import com.antonkazakov.foodfinder.data.sensor.SensorModule;

/**
 * Created by antonkazakov on 29.10.16.
 */

public class AppDelegate extends Application{

    AppComponent appComponent;

    private static AppDelegate appDelegate;

    @Override
    public void onCreate() {
        super.onCreate();

        appDelegate = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .locationModule(new LocationModule())
                .sensorModule(new SensorModule())
                .build();
    }

    public static AppDelegate getContext() {
        return appDelegate;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppComponent getAppComponent(Context context){
        return ((AppDelegate)context).getAppComponent();
    }

}
