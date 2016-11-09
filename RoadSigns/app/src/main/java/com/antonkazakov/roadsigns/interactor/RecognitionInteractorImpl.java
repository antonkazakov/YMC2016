package com.antonkazakov.roadsigns.interactor;

import android.net.Uri;
import android.util.Log;

import com.antonkazakov.roadsigns.Config;
import com.antonkazakov.roadsigns.content.RecognizeResponse;
import com.antonkazakov.roadsigns.data.RetrofitFactory;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

/**
 * Created by antonkazakov on 21.10.16.
 */

public class RecognitionInteractorImpl implements RecognitionInteractor {

    RecognitionCompleteListener recognitionCompleteListener;


    public RecognitionInteractorImpl(RecognitionCompleteListener recognitionCompleteListener){
        this.recognitionCompleteListener = recognitionCompleteListener;
    }


    @Override
    public void getProcessedImage(String path) {


        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), Config.TOKEN);
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), new File(path));


        RetrofitFactory.getRetrofitService()
                .recognizeImage(name, fbody)
                .enqueue(new Callback<RecognizeResponse>() {
                    @Override
                    public void onResponse(Call<RecognizeResponse> call, Response<RecognizeResponse> response) {

                        if (response.code()==200&&response.body().getResults().size()!=0){
                            recognitionCompleteListener.onSuccess(response.body());
                        }else if (response.code()!=200){
                            recognitionCompleteListener.onError(response.message());
                        }else if(response.body().getResults().size()==0){
                            recognitionCompleteListener.onError("NOTHING FOUND");
                        }

                    }

                    @Override
                    public void onFailure(Call<RecognizeResponse> call, Throwable t) {
                        recognitionCompleteListener.onError(t.getLocalizedMessage());
                    }
                });

    }

}
