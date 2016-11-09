package com.antonkazakov.radio.ui.stations;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.antonkazakov.radio.Config;
import com.antonkazakov.radio.R;
import com.antonkazakov.radio.data.content.Station;
import com.antonkazakov.radio.widget.ClickListener;
import com.antonkazakov.radio.widget.DividerItemDecoration;
import com.antonkazakov.radio.widget.RecyclerTouchListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StationsActivity extends AppCompatActivity implements StationsView {

    StationsPresenterImpl stationsPresenter;

    private RadioService musicSrv;
    private Intent playIntent;

    private boolean isPlaying;
    private boolean musicBound = false;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.img_cover)
    ImageView imgCover;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.img_big_cover)
    ImageView imgBigCover;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.linear_layout_bottom_sheet)
    LinearLayout bottomSheetLayout;

    @BindView(R.id.btn_play)
    ImageButton btnPlay;

    @OnClick(R.id.fab)
    public void onClick(){
        if (isPlaying){
            if (musicSrv!=null)
                musicSrv.pauseSong();
        }else {
            if (musicSrv!=null)
            musicSrv.resumeSong();
        }
    }

    @OnClick(R.id.btn_play)
    public void onPlayClick(){
        if (isPlaying){
            if (musicSrv!=null)
                musicSrv.pauseSong();
        }else {
            if (musicSrv!=null)
                musicSrv.resumeSong();
        }
    }


    @OnClick(R.id.btn_next)
    public void playNext(){
        if (currentPosition < stations.size()-1){
            currentPosition++;
            initLayout(stations.get(currentPosition));
            musicSrv.playSong(stations.get(currentPosition).getStreams().get(0).getStream());
        }
    }

    @OnClick(R.id.btn_previous)
    public void playPrevious(){
        if (currentPosition > 0){
            currentPosition--;
            initLayout(stations.get(currentPosition));
            musicSrv.playSong(stations.get(currentPosition).getStreams().get(0).getStream());
        }
    }


    int currentPosition;

    BottomSheetBehavior bottomSheetBehavior;

    List<Station> stations = new ArrayList<>();
    StationsAdapter stationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);
        ButterKnife.bind(this);
        floatingActionButton.setVisibility(View.GONE);
        stationsAdapter  = new StationsAdapter(stations);

        initRecyclerView();
        initBottomSheet();

        stationsPresenter = new StationsPresenterImpl(this);
        stationsPresenter.getStations();

    }

    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RadioService.RadioBinder binder = (RadioService.RadioBinder)service;
            musicSrv = binder.getService();
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    private void initBottomSheet(){

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_DRAGGING == newState) {
                    floatingActionButton.animate().scaleX(0).scaleY(0).setDuration(150).start();
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    floatingActionButton.animate().scaleX(1).scaleY(1).setDuration(150).start();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    private void initRecyclerView(){

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(stationsAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                floatingActionButton.setVisibility(View.VISIBLE);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                currentPosition = position;
                initLayout(stations.get(position));
                musicSrv.playSong(stations.get(position).getStreams().get(0).getStream());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void initLayout(Station station){

        Glide.with(this)
                .load(station.getImage().getUrl())
                .placeholder(R.drawable.empty_image_big)
                .into(imgBigCover);

        Glide.with(this)
                .load(station.getImage().getUrl())
                .placeholder(R.drawable.empty_image_medium)
                .into(imgCover);

        tvInfo.setText(station.getCategories().get(0).getTitle());
        tvTitle.setText(station.getName());

    }




    @Override
    protected void onStart() {
        super.onStart();
        stationsPresenter.bindBus();
        playIntent = new Intent(this, RadioService.class);
        bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        startService(playIntent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        stationsPresenter.unbindBus();
    }


    @Override
    protected void onDestroy() {
        unbindService(musicConnection);
        stopService(playIntent);
        musicSrv=null;
        super.onDestroy();
    }

    @Override
    public void refreshStations(List<Station> stations1) {
        stations.clear();
        stations.addAll(stations1);
        stationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateView(int tag) {
        switch (tag){
            case Config.TAG_START:
                isPlaying = true;
                btnPlay.setImageDrawable(ContextCompat.getDrawable(StationsActivity.this,R.drawable.pause_big));
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(StationsActivity.this,R.drawable.fab_pause));
                break;
            case Config.TAG_PAUSE:
                isPlaying = false;
                btnPlay.setImageDrawable(ContextCompat.getDrawable(StationsActivity.this,R.drawable.play_big));
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(StationsActivity.this,R.drawable.fab_play));
                break;
            case Config.TAG_DONE:
                isPlaying = true;
                btnPlay.setImageDrawable(ContextCompat.getDrawable(StationsActivity.this,R.drawable.pause_big));
                floatingActionButton.setImageDrawable(ContextCompat.getDrawable(StationsActivity.this,R.drawable.fab_pause));
                break;
        }
    }


    @Override
    public void onError(String textError) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
