package com.antonkazakov.squats.Screen.TrainingType;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.antonkazakov.squats.data.Content.TrainingType;
import com.antonkazakov.squats.data.DatabaseInteractor;
import com.antonkazakov.squats.R;
import com.antonkazakov.squats.Screen.Base.BaseActivity;
import com.antonkazakov.squats.di.components.AppComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class TrainingTypeCreateActivity extends BaseActivity implements TrainingTypeView{

    TrainingTypePresenterImpl trainingTypePresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DatabaseInteractor databaseInteractor;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_desc)
    EditText et_sesc;

    @BindView(R.id.et_num)
    EditText et_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_type_create);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        trainingTypePresenter = new TrainingTypePresenterImpl(databaseInteractor);
        trainingTypePresenter.attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.training_types_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:
                    if (et_name.getText().toString().trim().length()!=0&& et_num.getText().toString().trim().length()!=0) {
                        TrainingType trainingType = new TrainingType(et_name.getText().toString(),
                                et_sesc.getText().toString(),
                                et_num.getText().toString());
                        trainingTypePresenter.addTrainingType(trainingType);
                    }else {
                        Toast.makeText(TrainingTypeCreateActivity.this,"Fill all fields",Toast.LENGTH_LONG).show();
                    }

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void injectDependencies(AppComponent component) {
        component.inject(this);
    }

    @Override
    public void getTrainingType(TrainingType trainingType) {

    }

    @Override
    public void getTrainingTypes(List<TrainingType> trainingTypes) {

    }

    @Override
    public void onTrainingTypeAdded() {
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
