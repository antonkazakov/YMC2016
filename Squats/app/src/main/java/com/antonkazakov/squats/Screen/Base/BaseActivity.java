package com.antonkazakov.squats.Screen.Base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.antonkazakov.squats.AppDelegate;
import com.antonkazakov.squats.di.components.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by antonkazakov on 17.10.16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(AppDelegate.getAppComponent(AppDelegate.getContext()));

    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract void injectDependencies(AppComponent component);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
