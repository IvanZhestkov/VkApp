package com.itis.android.vkapp.model.view;

import android.view.View;

import com.itis.android.vkapp.model.WallItem;
import com.itis.android.vkapp.model.view.counter.CommentCounterViewModel;
import com.itis.android.vkapp.model.view.counter.LikeCounterViewModel;
import com.itis.android.vkapp.model.view.counter.RepostCounterViewModel;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;
import com.itis.android.vkapp.ui.view.holder.NewsItemFooterHolder;

public class NewsItemFooterViewModel extends BaseViewModel {

    private int mId;
    private int ownerId;
    private long mDateLong;

    private LikeCounterViewModel mLikes;
    private CommentCounterViewModel mComments;
    private RepostCounterViewModel mReposts;

    public NewsItemFooterViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();
        this.mDateLong = wallItem.getDate();
        this.mLikes = new LikeCounterViewModel(wallItem.getLikes());
        this.mComments = new CommentCounterViewModel(wallItem.getComments());
        this.mReposts = new RepostCounterViewModel(wallItem.getReposts());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public long getDateLong() {
        return mDateLong;
    }

    public void setDateLong(long mDateLong) {
        this.mDateLong = mDateLong;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public void setLikes(LikeCounterViewModel mLikes) {
        this.mLikes = mLikes;
    }

    public CommentCounterViewModel getComments() {
        return mComments;
    }

    public void setComments(CommentCounterViewModel mComments) {
        this.mComments = mComments;
    }

    public RepostCounterViewModel getReposts() {
        return mReposts;
    }

    public void setReposts(RepostCounterViewModel mReposts) {
        this.mReposts = mReposts;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
