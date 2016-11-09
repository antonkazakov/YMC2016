package com.antonkazakov.squats.Screen.TrainingType;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.antonkazakov.squats.R;
import com.antonkazakov.squats.Screen.Base.BaseActivity;
import com.antonkazakov.squats.di.components.AppComponent;

import butterknife.BindView;

public class TrainingTypeChooseActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_type_choose);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void injectDependencies(AppComponent component) {

    }



}
