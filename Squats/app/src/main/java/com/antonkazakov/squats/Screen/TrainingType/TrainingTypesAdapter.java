package com.antonkazakov.squats.Screen.TrainingType;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.antonkazakov.squats.AppDelegate;
import com.antonkazakov.squats.data.Content.TrainingType;
import com.antonkazakov.squats.R;

import java.util.List;

/**
 * Created by antonkazakov on 18.10.16.
 */

public class TrainingTypesAdapter extends RecyclerView.Adapter<TrainingTypeHolder>{

    List<TrainingType> trainingTypeList;

    public TrainingTypesAdapter(List<TrainingType> trainingTypeList){
        this.trainingTypeList = trainingTypeList;
    }

    @Override
    public TrainingTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrainingTypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.training_type_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(TrainingTypeHolder holder, int position) {
        holder.tvName.setText(trainingTypeList.get(position).getName());
        holder.tvNum.setText(trainingTypeList.get(position).getNumSquats());
        holder.tvDesc.setText(trainingTypeList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return trainingTypeList.size();
    }

}
