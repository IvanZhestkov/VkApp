package com.itis.android.vkapp.model.view.attachment;

import android.view.View;

import com.itis.android.vkapp.common.utils.Utils;
import com.itis.android.vkapp.model.attachment.Audio;
import com.itis.android.vkapp.model.view.BaseViewModel;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;
import com.itis.android.vkapp.ui.view.holder.attachment.AudioAttachmentHolder;

public class AudioAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mArtist;

    private String mDuration;


    public AudioAttachmentViewModel(Audio audio) {
        if (audio.getTitle().equals("")) {
            mTitle = "Title";
        } else {
            mTitle = audio.getTitle();
        }

        if (audio.getArtist().equals("")) {
            mArtist = "Various Artist";
        } else {
            mArtist = audio.getArtist();
        }

        mDuration = Utils.parseDuration(audio.getDuration());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentAudio;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new AudioAttachmentHolder(view);
    }


    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getDuration() {
        return mDuration;
    }
}
