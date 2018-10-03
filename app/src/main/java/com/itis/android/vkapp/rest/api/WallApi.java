package com.itis.android.vkapp.rest.api;

import com.itis.android.vkapp.model.CommentItem;
import com.itis.android.vkapp.rest.model.response.Full;
import com.itis.android.vkapp.rest.model.response.ItemWithSendersResponse;
import com.itis.android.vkapp.rest.model.response.WallGetByIdResponse;
import com.itis.android.vkapp.rest.model.response.WallGetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WallApi {

    /*@GET(ApiMethods.WALL_GET)
    Call<WallGetResponse> get(@Query("owner_id") String ownerId,
                              @Query("access_token") String accessToken,
                              @Query("extended") Integer extended,
                              @Query("v") String version);*/

    @GET(ApiMethods.WALL_GET)
    Observable<WallGetResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<WallGetByIdResponse> getById(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
