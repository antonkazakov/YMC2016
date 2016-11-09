package com.antonkazakov.squats.di.components;

import com.antonkazakov.squats.Screen.TrainingType.TrainingTypeCreateActivity;
import com.antonkazakov.squats.Screen.TrainingType.TrainingTypesFragment;
import com.antonkazakov.squats.Screen.CurrentTraining.CurrentTrainingFragment;
import com.antonkazakov.squats.Screen.Trainings.TrainingsFragment;
import com.antonkazakov.squats.di.modules.AppModule;
import com.antonkazakov.squats.di.modules.DataModule;
import com.antonkazakov.squats.di.modules.SensorModule;

import javax.inject.Singleton;


import dagger.Component;


/**
 * Created by antonkazakov on 27.08.16.
 */
@Singleton
@Component(modules = {AppModule.class, DataModule.class, SensorModule.class})
public interface AppComponent {

    void inject(TrainingTypesFragment trainingTypesFragment);

    void inject(CurrentTrainingFragment currentTrainingFragment);

    void inject(TrainingTypeCreateActivity trainingTypeCreateActivity);

    void inject(TrainingsFragment trainingsFragment);

}
