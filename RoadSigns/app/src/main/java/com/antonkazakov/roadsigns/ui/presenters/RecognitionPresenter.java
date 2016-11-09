package com.antonkazakov.roadsigns.ui.presenters;

import okhttp3.MultipartBody;

/**
 * Created by antonkazakov on 22.10.16.
 */

public interface RecognitionPresenter {

    void recognizeImage(String path);

    void onDestroy();

}
