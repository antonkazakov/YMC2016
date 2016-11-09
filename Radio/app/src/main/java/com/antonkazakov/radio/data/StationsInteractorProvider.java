package com.antonkazakov.radio.data;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class StationsInteractorProvider {


    private static StationsInteractor stationsInteractor;

    public static StationsInteractor getStationsInteractor() {
        if(stationsInteractor==null){
            return new StationsInteractorImpl();
        }
        return stationsInteractor;
    }

}
