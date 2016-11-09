package com.antonkazakov.squats.Screen.TrainingType;

import com.antonkazakov.squats.data.Content.TrainingType;

/**
 * Created by antonkazakov on 18.10.16.
 */

public interface TrainingTypePresenter {

    void addTrainingType(TrainingType trainingType);

    void getTrainingTypes();

    void deleteTrainingType(int id);

    void editTrainingType(TrainingType trainingType);

}
