package com.antonkazakov.foodfinder.data.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by antonkazakov on 29.10.16.
 */

public class AzimuthInteractorImpl implements AzimuthInteractor {

    private SensorManager sensorManager;
    private Sensor sensor;
    private float azimuthFrom = 0;
    private float azimuthTo = 0;

    public AzimuthInteractorImpl(SensorManager sensorManager){
        this.sensorManager = sensorManager;
        this.sensor = null;
    }


    @Override
    public Observable<Float> observeAzimuthChanges() {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        return Observable.fromEmitter(new Action1<AsyncEmitter<Float>>() {

            @Override
            public void call(AsyncEmitter<Float> floatAsyncEmitter) {
                SensorEventListener sensorEventListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        azimuthFrom = azimuthTo;

                        float[] orientation = new float[3];
                        float[] rMat = new float[9];
                        SensorManager.getRotationMatrixFromVector(rMat, event.values);
                        azimuthTo = (float) ( Math.toDegrees( SensorManager.getOrientation( rMat, orientation )[0] ) + 360 ) % 360;
                        floatAsyncEmitter.onNext(azimuthTo);

                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {
                        // No op
                    }
                };

                sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                floatAsyncEmitter.setCancellation(() -> sensorManager.unregisterListener(sensorEventListener, sensor));
            }
        }, AsyncEmitter.BackpressureMode.BUFFER);
    }
}
