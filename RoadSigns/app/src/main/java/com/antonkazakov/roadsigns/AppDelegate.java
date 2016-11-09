package com.antonkazakov.roadsigns;

import android.app.Application;


/**
 * Created by antonkazakov on 22.09.16.
 */

public class AppDelegate extends Application{

    private static AppDelegate applicationSingleton;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationSingleton=this;
    }

    public static AppDelegate getContext() {
        return applicationSingleton;
    }

}
