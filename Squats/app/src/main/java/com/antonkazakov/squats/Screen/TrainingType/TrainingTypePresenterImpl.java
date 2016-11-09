package com.antonkazakov.squats.Screen.TrainingType;

import com.antonkazakov.squats.data.Content.TrainingType;
import com.antonkazakov.squats.data.DatabaseInteractor;
import com.antonkazakov.squats.Screen.Base.RxBasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by antonkazakov on 18.10.16.
 */

public class TrainingTypePresenterImpl extends RxBasePresenter<TrainingTypeView> implements TrainingTypePresenter {

    private DatabaseInteractor databaseInteractor;

    @Inject
    public TrainingTypePresenterImpl(DatabaseInteractor databaseInteractor){
        this.databaseInteractor = databaseInteractor;
    }


    @Override
    public void addTrainingType(TrainingType trainingType) {
        addSubscription(
                databaseInteractor.addTrainingType(trainingType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrainingType>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TrainingType trainingType) {
                        getPresenterView().onTrainingTypeAdded();
                    }
                })
        );
    }

    @Override
    public void getTrainingTypes() {
        databaseInteractor.getTrainingTypes()
                .subscribe(new Observer<List<TrainingType>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<TrainingType> trainingTypes) {
                        getPresenterView().getTrainingTypes(trainingTypes);
                    }
                });
    }

    @Override
    public void deleteTrainingType(int id) {

    }

    @Override
    public void editTrainingType(TrainingType trainingType) {
        
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        databaseInteractor.onDestroy();
    }
}
