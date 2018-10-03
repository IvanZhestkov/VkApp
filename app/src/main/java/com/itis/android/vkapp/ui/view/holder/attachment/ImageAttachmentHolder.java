package com.itis.android.vkapp.ui.view.holder.attachment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.model.view.attachment.ImageAttachmentViewModel;
import com.itis.android.vkapp.ui.activity.BaseActivity;
import com.itis.android.vkapp.ui.fragment.ImageFragment;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAttachmentHolder extends BaseViewHolder<ImageAttachmentViewModel> {

    @BindView(R.id.iv_attachment_image)
    public ImageView image;

    @Inject
    MyFragmentManager mFragmentManager;

    public ImageAttachmentHolder(View itemView) {
        super(itemView);

        App.getApplicationComponent().inject(this);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(ImageAttachmentViewModel imageAttachmentViewModel) {

        if (imageAttachmentViewModel.needClick) {
            itemView.setOnClickListener(view ->
                    mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                            ImageFragment.newInstance(imageAttachmentViewModel.getPhotoUrl()), R.id.main_wrapper));
        }

        Glide.with(itemView.getContext())
                .load(imageAttachmentViewModel.getPhotoUrl())
                .into(image);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        image.setImageBitmap(null);
    }
}
