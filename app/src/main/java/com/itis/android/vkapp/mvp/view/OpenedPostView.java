package com.itis.android.vkapp.mvp.view;

import com.itis.android.vkapp.model.view.NewsItemFooterViewModel;

public interface OpenedPostView extends BaseFeedView {
    void setFooter(NewsItemFooterViewModel viewModel);
}
