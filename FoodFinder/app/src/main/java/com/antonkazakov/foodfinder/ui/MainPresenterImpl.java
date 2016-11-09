package com.antonkazakov.foodfinder.ui;

import android.location.Location;
import android.util.Log;

import com.antonkazakov.foodfinder.Config;
import com.antonkazakov.foodfinder.ui.base.RxBasePresenter;
import com.antonkazakov.foodfinder.data.content.GetPlaces;
import com.antonkazakov.foodfinder.data.location.LocationInteractor;
import com.antonkazakov.foodfinder.data.remote.RetrofitService;
import com.antonkazakov.foodfinder.data.sensor.AzimuthInteractor;
import com.antonkazakov.foodfinder.ui.MainPresenter;
import com.antonkazakov.foodfinder.ui.MainView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by antonkazakov on 29.10.16.
 */

public class MainPresenterImpl extends RxBasePresenter<MainView> implements MainPresenter{

    RetrofitService retrofitService;
    AzimuthInteractor azimuthInteractor;
    LocationInteractor locationInteractor;

    public MainPresenterImpl(RetrofitService retrofitService, LocationInteractor locationInteractor, AzimuthInteractor azimuthInteractor){
        this.retrofitService = retrofitService;
        this.azimuthInteractor = azimuthInteractor;
        this.locationInteractor = locationInteractor;
    }


    @Override
    public void getPlaces(Location location) {
        addSubscription(
                retrofitService.getNearPlaces(location.getLatitude() + "," + location.getLongitude(),
                        Config.RADIUS,
                        Config.TYPE,
                        Config.TOKEN)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<GetPlaces>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(GetPlaces getPlaces) {
                                getPresenterView().onResulstsRefreshed(getPlaces.getResults());
                            }
                        }));
    }


    @Override
    public void observeLocationChanges() {
        addSubscription(
                locationInteractor.observeLocationChanges()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Location>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Location location) {
                                getPresenterView().onLocationChanged(location);
                            }
                        }));
    }


    @Override
    public void observeAzimuthChanges() {
        addSubscription(
                azimuthInteractor.observeAzimuthChanges()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Float>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Float azimuthCorrelation) {
                                getPresenterView().onAzimuthChanged(azimuthCorrelation);
                            }
                        }));
    }


    @Override
    public void onDestroyed() {
        super.onDestroyed();
        clearSubscriptions();
    }

}
