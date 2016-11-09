package com.antonkazakov.radio.ui.stations;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonkazakov.radio.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class StationsHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.img_cover)
    ImageView imgCover;

    public StationsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
