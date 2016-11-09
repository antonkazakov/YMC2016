package com.antonkazakov.squats.Screen.TrainingType;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.antonkazakov.squats.Widget.DividerItemDecoration;
import com.antonkazakov.squats.Widget.EmptyRecyclerView;
import com.antonkazakov.squats.data.Content.TrainingType;
import com.antonkazakov.squats.data.DatabaseInteractor;
import com.antonkazakov.squats.Screen.CurrentTraining.CurrentTrainingActivity;
import com.antonkazakov.squats.R;
import com.antonkazakov.squats.Screen.Base.BaseFragment;
import com.antonkazakov.squats.Widget.ClickListener;
import com.antonkazakov.squats.Widget.RecyclerTouchListener;
import com.antonkazakov.squats.di.components.AppComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class TrainingTypesFragment extends BaseFragment implements TrainingTypeView{

    List<TrainingType> trainingTypes = new ArrayList<>();
    TrainingTypesAdapter trainingTypesAdapter;

    TrainingTypePresenterImpl trainingTypePresenter;

    @Inject
    DatabaseInteractor databaseInteractor;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView recyclerView;

    @OnClick(R.id.fab)
    public void onAddClick(){
        startActivity(new Intent(getActivity(),TrainingTypeCreateActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        trainingTypePresenter = new TrainingTypePresenterImpl(databaseInteractor);
        trainingTypePresenter.attachView(this);
        trainingTypesAdapter = new TrainingTypesAdapter(trainingTypes);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView(view);


    }

    @Override
    public void onResume() {
        super.onResume();
        trainingTypePresenter.getTrainingTypes();

    }



    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_training_types;
    }

    @Override
    protected void injectDependencies(AppComponent component) {
        component.inject(this);
    }


    private void initRecyclerView(View view){
        recyclerView.setEmptyView(view.findViewById(android.R.id.empty));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(trainingTypesAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), CurrentTrainingActivity.class)
                .putExtra("trainId",trainingTypes.get(position).getId()));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void getTrainingType(TrainingType trainingType) {

    }

    @Override
    public void getTrainingTypes(List<TrainingType> trainingTypes1) {
        trainingTypes.clear();
        trainingTypes.addAll(trainingTypes1);
        trainingTypesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTrainingTypeAdded() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
