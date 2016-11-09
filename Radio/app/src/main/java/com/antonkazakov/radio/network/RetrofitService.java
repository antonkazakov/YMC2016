package com.antonkazakov.radio.network;

import com.antonkazakov.radio.data.content.Station;

import java.util.List;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by antonkazakov on 22.10.16.
 */
public interface RetrofitService {

    @GET("recent")
    Observable<List<Station>> getStations(@Query("token") String token);

}
