package com.itis.android.vkapp.rest.api;

import com.itis.android.vkapp.rest.model.response.Full;
import com.itis.android.vkapp.rest.model.response.VideoResponse;


import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface VideoApi {

    @GET(ApiMethods.VIDEO_GET)
    io.reactivex.Observable<Full<VideoResponse>> get(@QueryMap Map<String, String> map);
}
