package com.antonkazakov.foodfinder.data.sensor;

import android.hardware.SensorEvent;

import rx.Observable;

/**
 * Created by antonkazakov on 29.10.16.
 */

public interface AzimuthInteractor {

    Observable<Float> observeAzimuthChanges();

}
