package com.itis.android.vkapp.ui.view.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.utils.Utils;
import com.itis.android.vkapp.model.view.attachment.LinkAttachmentViewModel;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkAttachmentViewHolder extends BaseViewHolder<LinkAttachmentViewModel> {

    @BindView(R.id.tv_title)
    public TextView title;

    @BindView(R.id.tv_attachment_url)
    public TextView url;

    public LinkAttachmentViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(LinkAttachmentViewModel linkAttachmentViewModel) {
        itemView.setOnClickListener(view -> Utils.openUrlInActionView(linkAttachmentViewModel.getUrl(), view.getContext()));
        title.setText(linkAttachmentViewModel.getTitle());
        url.setText(linkAttachmentViewModel.getUrl());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
        title.setText(null);
    }
}

