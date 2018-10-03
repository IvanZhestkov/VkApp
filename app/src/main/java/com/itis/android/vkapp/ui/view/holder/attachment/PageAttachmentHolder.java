package com.itis.android.vkapp.ui.view.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.utils.Utils;
import com.itis.android.vkapp.model.view.attachment.PageAttachmentViewModel;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageAttachmentHolder extends BaseViewHolder<PageAttachmentViewModel> {

    @BindView(R.id.tv_title)
    public TextView title;

    public PageAttachmentHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(PageAttachmentViewModel pageAttachmentViewModel) {
        itemView.setOnClickListener(view -> Utils.openUrlInActionView(pageAttachmentViewModel.getmUrl(), view.getContext()));
        title.setText(pageAttachmentViewModel.getTitle());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
    }


}
