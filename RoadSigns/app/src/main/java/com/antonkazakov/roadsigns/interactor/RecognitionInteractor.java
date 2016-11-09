package com.antonkazakov.roadsigns.interactor;

import android.net.Uri;

import com.antonkazakov.roadsigns.content.RecognizeResponse;

import java.io.File;

import okhttp3.MultipartBody;

/**
 * Created by antonkazakov on 21.10.16.
 */

public interface RecognitionInteractor {

    interface RecognitionCompleteListener{

        void onError(String errorBody);

        void onSuccess(RecognizeResponse recognizeResponse);

    }

    void getProcessedImage(String path);

}
