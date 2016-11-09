package com.antonkazakov.squats.data;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.AsyncEmitter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by antonkazakov on 18.10.16.
 */

public class SensorInteractor {

    private Sensor sensor;
    private SensorManager sensorManager;

    @Inject
    public SensorInteractor(@NonNull SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        this.sensor = null;
    }

    @NonNull
    public Observable<SensorEvent> observeSensorChanged() {

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return Observable.fromEmitter(new Action1<AsyncEmitter<SensorEvent>>() {
            @Override
            public void call(final AsyncEmitter<SensorEvent> sensorEventAsyncEmitter) {
                final SensorEventListener sensorListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {
                        sensorEventAsyncEmitter.onNext(sensorEvent);
                    }

                    @Override
                    public void onAccuracyChanged(Sensor originSensor, int i) {

                    }
                };
                sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                sensorEventAsyncEmitter.setCancellation(() -> sensorManager.unregisterListener(sensorListener, sensor));

            }
        }, AsyncEmitter.BackpressureMode.LATEST);
    }


}
