package com.antonkazakov.squats.Screen.CurrentTraining;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.antonkazakov.squats.DialogController;
import com.antonkazakov.squats.data.Content.Training;
import com.antonkazakov.squats.data.DatabaseInteractor;
import com.antonkazakov.squats.R;
import com.antonkazakov.squats.Screen.Base.BaseFragment;
import com.antonkazakov.squats.data.SensorInteractor;
import com.antonkazakov.squats.di.components.AppComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class CurrentTrainingFragment extends BaseFragment implements CurrentTrainingView{

    MediaPlayer mp,mp2;

    @BindView(R.id.btn_action)
    Button btn_action;

    @OnClick(R.id.btn_action)
    public void onActionClick(){
        updateSquatsCounter();
    }

    @OnLongClick(R.id.btn_action)
    public boolean onFinishClick(){
        if (count!=0){
            save();
        }
        return true;
    }

    @Inject
    SensorInteractor sensorInteractor;

    @Inject
    DatabaseInteractor databaseInteractor;

    private int count;
    private long startTime;
    private int trainType;

    CurrentTrainingSensorPresenterImpl currentTrainingSensorPresenter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_current_training;
    }

    @Override
    protected void injectDependencies(AppComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mp = MediaPlayer.create(getActivity(), R.raw.squat);
        mp2 = MediaPlayer.create(getActivity(), R.raw.complete);
        count = 0;
        if (getArguments() != null) {
            trainType = getArguments().getInt("trainId");
        }
        DialogController.infoDialog(getActivity(),getString(R.string.info3)).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_action.setText(count + getString(R.string.squats_count));
        currentTrainingSensorPresenter = new CurrentTrainingSensorPresenterImpl(databaseInteractor,sensorInteractor);
        currentTrainingSensorPresenter.attachView(this);
    }

    public static CurrentTrainingFragment newInstance(int trainId) {
        CurrentTrainingFragment fragment = new CurrentTrainingFragment();
        Bundle args = new Bundle();
        args.putInt("trainId", trainId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        currentTrainingSensorPresenter.startSensor();
    }



    @Override
    public void onPause() {
        super.onPause();
        currentTrainingSensorPresenter.onDestroyed();
    }

    @Override
    public void onTrainingStart() {

    }

    @Override
    public void onTrainingPause() {

    }

    @Override
    public void onTrainingFinish() {
        mp2.start();
        count = 0;
        startTime = 0;
        btn_action.setText(count + getString(R.string.squats_count));
    }

    @Override
    public void onTrainingRefresh() {

    }

    @Override
    public void onSquateComplete() {
        updateSquatsCounter();
    }


    private void updateSquatsCounter(){

        if (count==0){
            startTime = System.currentTimeMillis();
        }

        count++;

        mp.start();
        btn_action.setText(count + getString(R.string.squats_count));
    }

    private void save(){
        Training training = new Training(trainType, startTime, System.currentTimeMillis(),count);
        currentTrainingSensorPresenter.saveTraining(training);
    }



}
