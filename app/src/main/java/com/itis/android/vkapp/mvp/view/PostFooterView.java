package com.itis.android.vkapp.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.itis.android.vkapp.model.WallItem;
import com.itis.android.vkapp.model.view.counter.LikeCounterViewModel;

public interface PostFooterView extends MvpView {

    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}