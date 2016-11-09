package com.antonkazakov.foodfinder.data.location;

import android.hardware.SensorEvent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by antonkazakov on 28.10.16.
 */

public interface LocationInteractor {

    @NonNull
    Observable<Location> observeLocationChanges();

}
