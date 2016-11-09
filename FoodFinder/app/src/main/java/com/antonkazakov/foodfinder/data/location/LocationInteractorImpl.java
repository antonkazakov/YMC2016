package com.antonkazakov.foodfinder.data.location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by antonkazakov on 28.10.16.
 */

public class LocationInteractorImpl implements LocationInteractor{

    GoogleApiClient googleApiClient;

    public LocationInteractorImpl(GoogleApiClient googleApiClient){
        this.googleApiClient = googleApiClient;
    }


    @NonNull
    @Override
    public Observable<Location> observeLocationChanges() {

        LocationRequest locationRequest =  LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        return Observable.fromEmitter(new Action1<AsyncEmitter<Location>>() {
            @Override
            public void call(final AsyncEmitter<Location> locationAsyncEmitter) {

                LocationListener locationListener = locationAsyncEmitter::onNext;

                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = connectionResult ->
                        locationAsyncEmitter.onError(new Throwable("Failed to connect to Google Play Services!"));

                GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        try {
                            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
                        } catch (SecurityException e) {
                            locationAsyncEmitter.onError(new Throwable("Location permission not available!"));
                        }
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        locationAsyncEmitter.onError(new Throwable("Connection lost to Google Play Services"));

                    }
                };
                googleApiClient.registerConnectionCallbacks(connectionCallbacks);
                googleApiClient.registerConnectionFailedListener(onConnectionFailedListener);
                googleApiClient.connect();

                locationAsyncEmitter.setCancellation(() -> {
                    LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationListener);
                    googleApiClient.unregisterConnectionCallbacks(connectionCallbacks);
                    googleApiClient.unregisterConnectionFailedListener(onConnectionFailedListener);
                });
            }

        }, AsyncEmitter.BackpressureMode.LATEST);

    }
}
