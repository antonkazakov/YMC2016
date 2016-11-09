package com.antonkazakov.radio.ui.stations;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antonkazakov.radio.AppDelegate;
import com.antonkazakov.radio.R;
import com.antonkazakov.radio.data.content.Station;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class StationsAdapter extends RecyclerView.Adapter<StationsHolder> {

    List<Station> stations;

    public StationsAdapter(List<Station> stations){
        this.stations = stations;
    }


    @Override
    public StationsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StationsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.stations_item,parent,false));
    }

    @Override
    public void onBindViewHolder(StationsHolder holder, int position) {

        holder.tvTitle.setText(stations.get(position).getName());
        holder.tvInfo.setText(stations.get(position).getCategories().get(0).getTitle());
        Glide.with(AppDelegate.getContext())
                .load(stations.get(position).getImage().getUrl())
                .placeholder(R.drawable.empty_image_small2)
                .into(holder.imgCover);
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }


}
