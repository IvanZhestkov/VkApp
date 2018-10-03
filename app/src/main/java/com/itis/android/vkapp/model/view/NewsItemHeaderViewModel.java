package com.itis.android.vkapp.model.view;

import android.view.View;

import com.itis.android.vkapp.model.WallItem;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;
import com.itis.android.vkapp.ui.view.holder.NewsItemHeaderHolder;

public class NewsItemHeaderViewModel extends BaseViewModel {

    private int mId;

    private String mProfilePhoto;
    private String mProfileName;

    private boolean mIsRepost;
    private String mRepostProfileName;

    public NewsItemHeaderViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();

        this.mProfilePhoto = wallItem.getSenderPhoto();
        this.mProfileName = wallItem.getSenderName();

        this.mIsRepost = wallItem.haveSharedRepost();

        if (mIsRepost) {
            this.mRepostProfileName = wallItem.getSharedRepost().getSenderName();
        }
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemHeader;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemHeaderHolder(view);
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public void setProfilePhoto(String mProfilePhoto) {
        this.mProfilePhoto = mProfilePhoto;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public void setProfileName(String mProfileName) {
        this.mProfileName = mProfileName;
    }

    public boolean isRepost() {
        return mIsRepost;
    }

    public void setIsRepost(boolean mIsRepost) {
        this.mIsRepost = mIsRepost;
    }

    public String getRepostProfileName() {
        return mRepostProfileName;
    }

    public void setRepostProfileName(String mRepostProfileName) {
        this.mRepostProfileName = mRepostProfileName;
    }
}
