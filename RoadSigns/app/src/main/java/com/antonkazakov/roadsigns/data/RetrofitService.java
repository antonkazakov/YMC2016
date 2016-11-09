package com.antonkazakov.roadsigns.data;

import com.antonkazakov.roadsigns.content.RecognizeResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by antonkazakov on 21.10.16.
 */

public interface RetrofitService {


    @Multipart
    @POST("search")
    Call<RecognizeResponse> recognizeImage(@Part("token") RequestBody token,
                                           @Part("image\"; filename=\"picture.png\" ") RequestBody file);

}
