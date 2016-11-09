package com.antonkazakov.squats.Screen.Trainings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.antonkazakov.squats.Utils;
import com.antonkazakov.squats.data.Content.Training;
import com.antonkazakov.squats.R;

import java.util.List;

/**
 * Created by antonkazakov on 18.10.16.
 */

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingViewHolder>{

    List<Training> trainings;

    public TrainingsAdapter(List<Training> trainings){
        this.trainings = trainings;
    }

    @Override
    public TrainingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrainingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trainings_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(TrainingViewHolder holder, int position) {
        holder.tvStart.setText(Utils.toDate(trainings.get(position).getStartTime()));
        holder.tvEnd.setText(Utils.toDate(trainings.get(position).getEndTime()));
        holder.tvNumber.setText(trainings.get(position).getCount().toString());
    }


    @Override
    public int getItemCount() {
        return trainings.size();
    }

}
