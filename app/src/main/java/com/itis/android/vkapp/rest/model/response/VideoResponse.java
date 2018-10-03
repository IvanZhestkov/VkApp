package com.itis.android.vkapp.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.itis.android.vkapp.model.attachment.video.Video;

import java.util.List;

public class VideoResponse {

    public int count;

    @SerializedName("items")
    @Expose
    public List<Video> items;
}
