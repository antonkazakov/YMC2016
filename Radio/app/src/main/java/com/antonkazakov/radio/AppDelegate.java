package com.antonkazakov.radio;

import android.app.Application;


/**
 * Created by antonkazakov on 22.09.16.
 */

public class AppDelegate extends Application{

    private  RxBus bus;

    private static AppDelegate applicationSingleton;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationSingleton=this;

        bus = new RxBus();
    }

    public static AppDelegate getContext() {
        return applicationSingleton;
    }

    public RxBus getBus(){
        if (bus ==null){
            return new RxBus();
        }else {
            return bus;
        }
    }

}
