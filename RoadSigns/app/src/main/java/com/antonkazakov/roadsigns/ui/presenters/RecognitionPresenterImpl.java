package com.antonkazakov.roadsigns.ui.presenters;

import com.antonkazakov.roadsigns.content.RecognizeResponse;
import com.antonkazakov.roadsigns.interactor.RecognitionInteractorImpl;
import com.antonkazakov.roadsigns.ui.views.RecognitionView;
import com.antonkazakov.roadsigns.interactor.RecognitionInteractor;

import java.io.File;

import okhttp3.MultipartBody;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class RecognitionPresenterImpl implements RecognitionPresenter, RecognitionInteractor.RecognitionCompleteListener{

    private RecognitionView recognitionView;
    private RecognitionInteractor recognitionInteractor;

    public RecognitionPresenterImpl(RecognitionView recognitionView, RecognitionInteractor recognitionInteractor){
        this.recognitionView = recognitionView;
        this.recognitionInteractor = new RecognitionInteractorImpl(this);
    }

    @Override
    public void onError(String errorBody) {
        if (recognitionView!=null){
            recognitionView.hideLoading();
            recognitionView.showError(errorBody);
        }
    }

    @Override
    public void onSuccess(RecognizeResponse recognizeResponse) {
        if (recognitionView!=null){
            recognitionView.hideLoading();
            if (recognizeResponse.getResults().size()!=0){
                recognitionView.loadData(recognizeResponse.getResults().get(0));
            }
        }
    }

    @Override
    public void recognizeImage(String path) {
        if (recognitionView!=null){
            recognitionView.showLoading();
        }
        recognitionInteractor.getProcessedImage(path);
    }

    @Override
    public void onDestroy() {
        if (recognitionView!=null){
            recognitionView = null;
        }
    }

}
