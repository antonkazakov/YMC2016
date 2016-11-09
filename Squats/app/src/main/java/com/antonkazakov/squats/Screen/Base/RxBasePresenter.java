package com.antonkazakov.squats.Screen.Base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by antonkazakov on 17.10.16.
 */

public abstract class RxBasePresenter<V> extends BasePresenter<V>{

    private CompositeSubscription subscriptions = new CompositeSubscription();


    public void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    public void removeSubscription(Subscription subscription){
        subscriptions.remove(subscription);
    }

    public void clearSubscriptions(){
        subscriptions.clear();
    }

    @Override
    public void onDestroyed() {
        clearSubscriptions();
    }

}
