package com.antonkazakov.foodfinder.ui;

import android.hardware.Camera;
import android.location.Location;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antonkazakov.foodfinder.AppComponent;
import com.antonkazakov.foodfinder.data.sensor.AzimuthInteractor;
import com.antonkazakov.foodfinder.ui.base.BaseFragment;
import com.antonkazakov.foodfinder.utils.CalculateUtils;
import com.antonkazakov.foodfinder.data.location.LocationInteractor;
import com.antonkazakov.foodfinder.R;
import com.antonkazakov.foodfinder.data.remote.RetrofitService;
import com.antonkazakov.foodfinder.data.content.Result;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment implements  SurfaceHolder.Callback,MainView {

    List<Result> resultList = new ArrayList<>();
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;
    private boolean isCameraviewOn = false;
    MainPresenterImpl testpres;
    Location mylocation;

    @Inject
    RetrofitService retrofit;

    @Inject
    LocationInteractor locationInteractor;

    @Inject
    AzimuthInteractor azimuthInteractor;

    @BindView(R.id.cameraview)
    SurfaceView surfaceView;

    @BindView(R.id.layout)
    RelativeLayout layout;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.rating)
    RatingBar ratingBar;

    @BindView(R.id.img_logo)
    ImageView imgLogo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testpres = new MainPresenterImpl(retrofit,locationInteractor,azimuthInteractor);
        testpres.attachView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSurfaceHolder = surfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        if (isCameraviewOn) {
            mCamera.stopPreview();
            isCameraviewOn = false;
        }

        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.startPreview();
                isCameraviewOn = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();
        mCamera.setDisplayOrientation(90);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
        isCameraviewOn = false;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void injectDependencies(AppComponent component) {
        component.inject(this);
    }


    @Override
    public void onResume() {
        super.onResume();

        testpres.observeLocationChanges();
        testpres.observeAzimuthChanges();
    }


    @Override
    public void onResulstsRefreshed(List<Result> resultList1) {
        if (resultList1!=null) {
            resultList.clear();
            resultList.addAll(resultList1);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        testpres.getPlaces(mylocation);
    }



    @Override
    public void onStop() {
        super.onStop();
        testpres.onDestroyed();
    }

    @Override
    public void onAzimuthChanged(float azimuthValue) {

        if (resultList.size()!=0){

            if (layout.getVisibility() == View.VISIBLE){
                layout.setVisibility(View.GONE);
                tvName.setText("");
                tvAddress.setText("");
            }

            for (int i = 0; i<resultList.size(); i++){
                double minAngle = CalculateUtils.calculateAzimuthAccuracy(CalculateUtils.calculateTeoreticalAzimuth(resultList.get(i).getGeometry(),mylocation)).get(0);
                double maxAngle = CalculateUtils.calculateAzimuthAccuracy(CalculateUtils.calculateTeoreticalAzimuth(resultList.get(i).getGeometry(),mylocation)).get(1);
                if (CalculateUtils.isBetween(minAngle, maxAngle, azimuthValue)) {
                    if (layout.getVisibility() == View.GONE){

                        layout.setVisibility(View.VISIBLE);
                        tvName.setText(resultList.get(i).getName());
                        tvAddress.setText(resultList.get(i).getVicinity());
                        if (resultList.get(i).getRating()!=null){
                            ratingBar.setRating(resultList.get(i).getRating());
                        }
                        Glide.with(this).load(resultList.get(i).getIcon()).into(imgLogo);

                    }

                }
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }





}
