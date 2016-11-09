package com.antonkazakov.radio.data;

import com.antonkazakov.radio.data.content.Station;

import java.util.List;

import rx.Observable;

/**
 * Created by antonkazakov on 22.10.16.
 */

public interface StationsInteractor {

    public Observable<List<Station>> getStations();

}
