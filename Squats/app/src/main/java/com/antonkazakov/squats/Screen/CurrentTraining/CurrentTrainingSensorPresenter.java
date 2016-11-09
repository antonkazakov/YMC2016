package com.antonkazakov.squats.Screen.CurrentTraining;

import com.antonkazakov.squats.data.Content.Training;

/**
 * Created by antonkazakov on 18.10.16.
 */

public interface CurrentTrainingSensorPresenter {

    void startSensor();

    void saveTraining(Training training);

}
