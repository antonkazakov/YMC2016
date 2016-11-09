package com.antonkazakov.squats.Screen.CurrentTraining;

import android.hardware.SensorEvent;
import android.util.Log;

import com.antonkazakov.squats.data.Content.Training;
import com.antonkazakov.squats.data.DatabaseInteractor;
import com.antonkazakov.squats.Screen.Base.RxBasePresenter;
import com.antonkazakov.squats.data.SensorInteractor;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by antonkazakov on 18.10.16.
 */

public class CurrentTrainingSensorPresenterImpl  extends RxBasePresenter<CurrentTrainingView> implements CurrentTrainingSensorPresenter{

    SensorInteractor sensorInteractor;
    DatabaseInteractor databaseInteractor;

    private float lastZ;
    private float deltaZ = 0;
    private long lastTime=0;
    long curTime;

    private final int averageValue = 15;
    private final int averageDelta = 10;
    private final int squatsDelay = 800;

    public CurrentTrainingSensorPresenterImpl(DatabaseInteractor databaseInteractor, SensorInteractor sensorInteractor){
        this.databaseInteractor = databaseInteractor;
        this.sensorInteractor = sensorInteractor;
    }

    @Override
    public void startSensor() {
        addSubscription(
                sensorInteractor.observeSensorChanged()
                        .filter(sensorEvent -> {
                            float[] mGravity = sensorEvent.values.clone();
                            curTime = System.currentTimeMillis();
                            return Math.abs(lastZ - mGravity[2])>averageDelta;
                        })
                        .filter(sensorEvent -> {
                            float[] mGravity = sensorEvent.values.clone();
                            deltaZ = Math.abs(lastZ - mGravity[2]);
                            return deltaZ>averageValue;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SensorEvent>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(SensorEvent sensorEvent) {
                            lastZ = sensorEvent.values[2];
                            if ((curTime - lastTime) > squatsDelay) {
                                lastTime = curTime;
                                getPresenterView().onSquateComplete();
                            }
                        }
                    }));
    }



    @Override
    public void saveTraining(Training training) {
        addSubscription(
                databaseInteractor.addTraining(training)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Training>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Training training) {
                                getPresenterView().onTrainingFinish();
                            }
                        }));
    }


    @Override
    public void onDestroyed() {
        super.onDestroyed();
        clearSubscriptions();
    }


}
