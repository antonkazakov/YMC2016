package com.antonkazakov.squats.Screen.TrainingType;

import com.antonkazakov.squats.data.Content.TrainingType;
import com.antonkazakov.squats.Screen.Base.BaseView;

import java.util.List;

/**
 * Created by antonkazakov on 18.10.16.
 */

public interface TrainingTypeView extends BaseView{

    void getTrainingType(TrainingType trainingType);

    void getTrainingTypes(List<TrainingType> trainingTypes);

    void onTrainingTypeAdded();
}
