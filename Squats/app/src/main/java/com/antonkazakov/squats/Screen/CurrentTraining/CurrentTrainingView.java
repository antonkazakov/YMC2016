package com.antonkazakov.squats.Screen.CurrentTraining;

/**
 * Created by antonkazakov on 18.10.16.
 */

public interface CurrentTrainingView {

    void onTrainingStart();

    void onTrainingPause();

    void onTrainingFinish();

    void onTrainingRefresh();

    void onSquateComplete();

}
