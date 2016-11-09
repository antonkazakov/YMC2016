package com.antonkazakov.roadsigns.ui.views;

/**
 * Created by antonkazakov on 21.10.16.
 */

public interface BaseView {

    void hideLoading();

    void showLoading();

    void showError(String text);

}
