package com.itis.android.vkapp.rest.api;

import com.itis.android.vkapp.model.CommentItem;
import com.itis.android.vkapp.model.Topic;
import com.itis.android.vkapp.rest.model.response.BaseItemResponse;
import com.itis.android.vkapp.rest.model.response.Full;
import com.itis.android.vkapp.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BoardApi {

    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
