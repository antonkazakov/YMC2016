package com.antonkazakov.radio.data;

import com.antonkazakov.radio.Config;
import com.antonkazakov.radio.network.RetrofitBuilder;
import com.antonkazakov.radio.network.RetrofitService;
import com.antonkazakov.radio.data.content.Station;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class StationsInteractorImpl implements StationsInteractor{

    @Override
    public Observable<List<Station>> getStations(){
        return RetrofitBuilder.getRetrofitService(RetrofitService.class)
                .getStations(Config.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
