package com.antonkazakov.squats.Screen.Trainings;

import com.antonkazakov.squats.data.Content.Training;
import com.antonkazakov.squats.Screen.Base.BaseView;

import java.util.List;

/**
 * Created by antonkazakov on 27.10.16.
 */

public interface TrainingView extends BaseView{

    void loadTrainings(List<Training> trainings);

}
