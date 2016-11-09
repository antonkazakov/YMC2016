package com.antonkazakov.radio.ui.stations;

import com.antonkazakov.radio.AppDelegate;
import com.antonkazakov.radio.data.content.BusEvents;
import com.antonkazakov.radio.data.StationsInteractorProvider;
import com.antonkazakov.radio.data.content.Station;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class StationsPresenterImpl implements StationsPresenter{

    public StationsView stationsView;

    private Subscription busSubscription;

    public StationsPresenterImpl(StationsView stationsView){
        this.stationsView = stationsView;
    }

    @Override
    public void getStations() {
        StationsInteractorProvider
                .getStationsInteractor()
                .getStations()
                .doOnSubscribe(() -> stationsView.showLoading())
                .doOnTerminate(() -> stationsView.hideLoading())
                .subscribe(new Observer<List<Station>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Station> stations) {
                        stationsView.refreshStations(stations);
                    }
                });
    }


    @Override
    public void bindBus() {
        busSubscription = AppDelegate.getContext().getBus().toObserverable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        o -> {
                            if (o instanceof BusEvents.Message) {
                                stationsView.updateView(((BusEvents.Message) o).type);
                            }
                        }
                );
    }

    @Override
    public void unbindBus() {
        if (busSubscription != null && !busSubscription.isUnsubscribed()) {
            busSubscription.unsubscribe();
        }
    }


}


