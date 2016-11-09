package com.antonkazakov.radio.ui.base;

/**
 * Created by antonkazakov on 22.10.16.
 */

public interface BaseView {

    void onError(String textError);

    void showLoading();

    void hideLoading();

}
