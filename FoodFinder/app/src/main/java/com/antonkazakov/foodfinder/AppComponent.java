package com.antonkazakov.foodfinder;

import com.antonkazakov.foodfinder.data.location.LocationModule;
import com.antonkazakov.foodfinder.data.remote.NetworkModule;
import com.antonkazakov.foodfinder.data.sensor.SensorModule;
import com.antonkazakov.foodfinder.ui.MainActivity;
import com.antonkazakov.foodfinder.ui.MainActivityFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by antonkazakov on 29.10.16.
 */

@Singleton
@Component(modules = {LocationModule.class, NetworkModule.class, AppModule.class, SensorModule.class})
public interface AppComponent {

    void inject(MainActivityFragment mainActivityFragment);

}
