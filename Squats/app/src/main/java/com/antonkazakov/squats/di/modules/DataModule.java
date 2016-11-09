package com.antonkazakov.squats.di.modules;

import com.antonkazakov.squats.data.DatabaseInteractor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;


@Module
public class DataModule {

    @Provides
    @Singleton
    public Realm provideRealm(){
        return Realm.getDefaultInstance();
    }

    @Provides
    DatabaseInteractor provideDatabaseInteractor() {
        return new DatabaseInteractor(this::provideRealm);
    }


}
