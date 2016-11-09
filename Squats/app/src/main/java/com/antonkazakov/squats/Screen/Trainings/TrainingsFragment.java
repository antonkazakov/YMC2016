package com.antonkazakov.squats.Screen.Trainings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.antonkazakov.squats.Widget.DividerItemDecoration;
import com.antonkazakov.squats.data.Content.Training;
import com.antonkazakov.squats.data.DatabaseInteractor;
import com.antonkazakov.squats.R;
import com.antonkazakov.squats.Screen.Base.BaseFragment;
import com.antonkazakov.squats.Screen.TrainingType.TrainingTypeCreateActivity;
import com.antonkazakov.squats.Widget.ClickListener;
import com.antonkazakov.squats.Widget.RecyclerTouchListener;
import com.antonkazakov.squats.di.components.AppComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class TrainingsFragment extends BaseFragment implements TrainingView{

    List<Training> trainings = new ArrayList<>();
    TrainingsAdapter trainingsAdapter;

    TrainingsPresenterImpl trainingsPresenter;


    @Inject
    DatabaseInteractor databaseInteractor;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        trainingsPresenter = new TrainingsPresenterImpl(databaseInteractor);
        trainingsPresenter.attachView(this);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        trainingsAdapter = new TrainingsAdapter(trainings);
        initRecyclerView();

    }

    @Override
    public void onResume() {
        super.onResume();
        trainingsPresenter.getTrainings();

    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_trainings;
    }

    @Override
    protected void injectDependencies(AppComponent component) {
        component.inject(this);
    }

    private void initRecyclerView(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(trainingsAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadTrainings(List<Training> trainings1) {
        Log.i("sfdsf", "loadTrainings: " + trainings1.size());
        trainings.clear();
        trainings.addAll(trainings1);
        trainingsAdapter.notifyDataSetChanged();
    }


}
