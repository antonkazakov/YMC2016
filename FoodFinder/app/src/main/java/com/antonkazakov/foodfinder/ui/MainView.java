package com.antonkazakov.foodfinder.ui;

import android.location.Location;

import com.antonkazakov.foodfinder.ui.base.BaseView;
import com.antonkazakov.foodfinder.data.content.Result;

import java.util.List;

/**
 * Created by antonkazakov on 29.10.16.
 */

public interface MainView extends BaseView{

    void onResulstsRefreshed(List<Result> resultList);

    void onLocationChanged(Location location);

    void onAzimuthChanged(float azimuthValue);

}
