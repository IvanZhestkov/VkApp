package com.itis.android.vkapp.model.view.attachment;

import android.view.View;

import com.itis.android.vkapp.model.attachment.Link;
import com.itis.android.vkapp.model.view.BaseViewModel;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;
import com.itis.android.vkapp.ui.view.holder.attachment.LinkExternalAttachmentHolder;


public class LinkExternalViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    private String mImageUrl;

    public LinkExternalViewModel(Link link) {

        if (link.getTitle() == null || link.getTitle().equals("")) {
            if (link.getName() != null) {
                mTitle = link.getName();
            } else {
                mTitle = "Link";
            }
        } else {
            mTitle = link.getTitle();
        }

        mUrl = link.getUrl();

        mImageUrl = link.getPhoto().getPhoto604();
    }



    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLinkExternal;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new LinkExternalAttachmentHolder(view);
    }


    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}