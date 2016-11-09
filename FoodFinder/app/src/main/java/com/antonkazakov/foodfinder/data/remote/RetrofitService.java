package com.antonkazakov.foodfinder.data.remote;


import com.antonkazakov.foodfinder.data.content.GetPlaces;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by antonkazakov on 05.07.16.
 */
public interface RetrofitService {

    @GET("json")
    Observable<GetPlaces> getNearPlaces(@Query("location") String location,
                                        @Query("radius") int radius,
                                        @Query("types") String types,
                                        @Query("key") String key);

}
