package com.antonkazakov.squats.Screen.Trainings;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.antonkazakov.squats.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antonkazakov on 27.10.16.
 */

public class TrainingViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tvStart)
    TextView tvStart;

    @BindView(R.id.tvEnd)
    TextView tvEnd;

    @BindView(R.id.tvNumber)
    TextView tvNumber;

    public TrainingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
