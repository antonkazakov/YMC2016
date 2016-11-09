package com.antonkazakov.squats.Screen.Base;

/**
 * Created by antonkazakov on 17.10.16.
 */

public interface Bs<V> {

    void attachView(V mvpView);

    void detachView();

}
