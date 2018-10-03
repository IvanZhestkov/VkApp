package com.itis.android.vkapp.model.view.attachment;

import android.view.View;

import com.itis.android.vkapp.model.attachment.Page;
import com.itis.android.vkapp.model.view.BaseViewModel;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;
import com.itis.android.vkapp.ui.view.holder.attachment.PageAttachmentHolder;


public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }



    public String getmUrl() {
        return mUrl;
    }
}
