package com.antonkazakov.squats.Screen.TrainingType;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.antonkazakov.squats.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antonkazakov on 18.10.16.
 */

public class TrainingTypeHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvNum)
    TextView tvNum;

    @BindView(R.id.tvDesc)
    TextView tvDesc;


    public TrainingTypeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
