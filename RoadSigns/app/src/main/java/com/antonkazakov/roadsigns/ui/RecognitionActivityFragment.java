package com.antonkazakov.roadsigns.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonkazakov.roadsigns.Config;
import com.antonkazakov.roadsigns.R;
import com.antonkazakov.roadsigns.content.Image;
import com.antonkazakov.roadsigns.content.Result;
import com.antonkazakov.roadsigns.interactor.RecognitionInteractor;
import com.antonkazakov.roadsigns.ui.presenters.RecognitionPresenterImpl;
import com.antonkazakov.roadsigns.ui.views.RecognitionView;
import com.antonkazakov.roadsigns.utils.ImageUtils;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class RecognitionActivityFragment extends Fragment implements RecognitionView{

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.img_sign)
    ImageView imgSign;

    @OnClick(R.id.btn_pick)
    public void onClick(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, Config.GALLERY_REQUEST);
    }

    ProgressDialog barProgressDialog;
    AlertDialog.Builder alertDialog;

    RecognitionInteractor recognitionInteractor;
    RecognitionPresenterImpl recognitionPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recognitionPresenter = new RecognitionPresenterImpl(this,recognitionInteractor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recognition, container, false);
        ButterKnife.bind(this, view);

        initProgressDialog();

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Config.GALLERY_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        InputStream imageStream = getActivity().getContentResolver().openInputStream(data.getData());
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        selectedImage = ImageUtils.getResizedBitmap(selectedImage,800);
                        recognitionPresenter.recognizeImage(ImageUtils.bitmapToFile(selectedImage));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    @Override
    public void loadData(Result result) {

        switch (result.getItem().getName()){
            case Config.PEREHOD:
                tvName.setText("Пешеходный переход");
                break;
            case Config.BRICK:
                tvName.setText("Въезд запрещен");
                break;
            case Config.DONUT:
                tvName.setText("Движение запрещено");
                break;
            case Config.RAILS:
                tvName.setText("Железнодорожный переезд без шлагбаума");
                break;
            case Config.ONEWAY:
                tvName.setText("Дорога с односторонним движением");
        }

        Glide.with(this).load(result.getItem().getUrl()).into(imgSign);
    }

    @Override
    public void hideLoading() {
        if (barProgressDialog!=null){
            barProgressDialog.dismiss();
        }
    }

    @Override
    public void showLoading() {
        if (barProgressDialog!=null){
            barProgressDialog.show();
        }
    }


    @Override
    public void showError(String text) {
        initAlertDialog(text);
    }

    private void initProgressDialog(){
        barProgressDialog = new ProgressDialog(getActivity());
        barProgressDialog.setMessage("Download in progress ...");
        barProgressDialog.setIndeterminate(true);
    }


    private void initAlertDialog(String text){
        alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setCancelable(false);
        alertDialog.setMessage(text);
        alertDialog.setPositiveButton(R.string.choose_another, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.create().show();
    }


}
