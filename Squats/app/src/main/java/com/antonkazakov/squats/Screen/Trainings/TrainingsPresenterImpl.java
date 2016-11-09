package com.antonkazakov.squats.Screen.Trainings;

import android.util.Log;

import com.antonkazakov.squats.data.Content.Training;
import com.antonkazakov.squats.data.DatabaseInteractor;
import com.antonkazakov.squats.Screen.Base.RxBasePresenter;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by antonkazakov on 27.10.16.
 */

public class TrainingsPresenterImpl extends RxBasePresenter<TrainingView> implements TrainingsPresenter{

    DatabaseInteractor databaseInteractor;

    public TrainingsPresenterImpl(DatabaseInteractor databaseInteractor){
        this.databaseInteractor = databaseInteractor;
    }

    @Override
    public void getTrainings() {
        addSubscription(
                databaseInteractor.getTrainings()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<Training>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("TEST", "onError: ", e);
                            }

                            @Override
                            public void onNext(List<Training> trainings) {
                                getPresenterView().loadTrainings(trainings);
                            }
                        }));
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        clearSubscriptions();
    }

}
