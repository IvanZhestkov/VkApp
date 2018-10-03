package com.itis.android.vkapp.ui.view.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.model.view.MemberViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberViewHolder extends BaseViewHolder<MemberViewModel> {

    @BindView(R.id.civ_profile_image)
    public CircleImageView civProfilePhoto;

    @BindView(R.id.tv_profile_name)
    public TextView tvProfileName;

    public MemberViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(MemberViewModel memberViewModel) {
        Context context = itemView.getContext();

        Glide.with(context)
                .load(memberViewModel.getPhoto())
                .into(civProfilePhoto);
        tvProfileName.setText(memberViewModel.getFullName());
    }

    @Override
    public void unbindViewHolder() {
        tvProfileName.setText(null);
        civProfilePhoto.setImageBitmap(null);
    }
}
