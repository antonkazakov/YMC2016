package com.antonkazakov.squats.data;

import android.support.annotation.NonNull;

import com.antonkazakov.squats.data.Content.Training;
import com.antonkazakov.squats.data.Content.TrainingType;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import io.realm.Realm;
import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by antonkazakov on 18.10.16.
 */

public class DatabaseInteractor {

    private Lazy<Realm> realm;

    @Inject
    public DatabaseInteractor(@NonNull Lazy<Realm> realm) {
        this.realm = realm;
    }

    /**
     * Add new training
     * @param training
     */
    public Observable<Training> addTraining(@NonNull Training training){

        return Observable.fromEmitter(new Action1<AsyncEmitter<Training>>() {
            @Override
            public void call(AsyncEmitter<Training> trainingTypeAsyncEmitter) {
                realm.get().executeTransactionAsync(realm1 -> {
                    int newId=1;
                    if (realm1.where(Training.class).findFirst()!=null){
                        newId = realm1.where(Training.class).max("train_id").intValue() + 1;
                    }
                    training.setId(newId);
                    realm1.copyToRealmOrUpdate(training);
                }, () -> trainingTypeAsyncEmitter.onNext(training), error -> trainingTypeAsyncEmitter.onError(error));
            }
        }, AsyncEmitter.BackpressureMode.NONE);
    }


    /**
     * Add new Training Type
     * @param trainingType
     * @return
     */
    public Observable<TrainingType> addTrainingType(final TrainingType trainingType){

        return  Observable.fromEmitter(new Action1<AsyncEmitter<TrainingType>>() {
            @Override
            public void call(AsyncEmitter<TrainingType> trainingTypeAsyncEmitter) {
                realm.get().executeTransactionAsync(realm1 -> {
                    int newId=1;
                    if (realm1.where(TrainingType.class).findFirst()!=null){
                        newId = realm1.where(TrainingType.class).max("trainingtype_id").intValue() + 1;
                    }
                    trainingType.setId(newId);
                    realm1.copyToRealmOrUpdate(trainingType);
                }, () -> trainingTypeAsyncEmitter.onNext(trainingType),
                        error -> trainingTypeAsyncEmitter.onError(error));
            }
        }, AsyncEmitter.BackpressureMode.NONE);

    }

    /**
     * Get training Types
     * @return
     */
    public Observable<List<TrainingType>> getTrainingTypes(){

        return realm.get()
                .where(TrainingType.class)
                .findAllAsync()
                .asObservable()
                .map(trainings -> realm.get().copyFromRealm(trainings))
                .asObservable();
    }


    /**
     * Get training list
     * @return
     */
    public Observable<List<Training>> getTrainings(){

        return realm.get()
                .where(Training.class)
                .findAll()
                .asObservable()
                .map(trainings -> realm.get().copyFromRealm(trainings))
                .asObservable();

    }


    public void onDestroy() {
        this.realm.get().close();
    }


}
