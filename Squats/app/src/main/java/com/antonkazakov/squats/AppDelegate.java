package com.antonkazakov.squats;

import android.app.Application;
import android.content.Context;

import com.antonkazakov.squats.di.components.AppComponent;
import com.antonkazakov.squats.di.components.DaggerAppComponent;
import com.antonkazakov.squats.di.modules.AppModule;
import com.antonkazakov.squats.di.modules.DataModule;
import com.antonkazakov.squats.di.modules.SensorModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by antonkazakov on 17.10.16.
 */

public class AppDelegate extends Application{

    AppComponent appComponent;

    private static AppDelegate appDelegate;

    @Override
    public void onCreate() {
        super.onCreate();
        appDelegate=this;

        initRealm();

        appComponent = DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .dataModule(new DataModule())
                        .sensorModule(new SensorModule())
                        .build();
    }

    public void initRealm(){

        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext())
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(4)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);


    }

    public static AppDelegate getContext() {
        return appDelegate;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * Static App component
     * @param context
     * @return
     */
    public static AppComponent getAppComponent(Context context){
        return ((AppDelegate)context).getAppComponent();
    }

}
