package com.antonkazakov.foodfinder.ui;

import android.location.Location;

/**
 * Created by antonkazakov on 30.10.16.
 */

public interface MainPresenter {

    void getPlaces(Location location);

    void observeLocationChanges();

    void observeAzimuthChanges();

}
