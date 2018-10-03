package com.itis.android.vkapp.ui.view.holder;

import android.view.View;
import android.widget.TextView;

import com.itis.android.vkapp.R;
import com.itis.android.vkapp.model.view.InfoStatusViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoStatusViewHolder extends BaseViewHolder<InfoStatusViewModel> {

    @BindView(R.id.tv_status_text)
    public TextView tvStatusText;

    @BindView(R.id.tv_description_text)
    TextView tvDescriptionText;

    @BindView(R.id.tv_site_text)
    TextView tvSiteText;


    public InfoStatusViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(InfoStatusViewModel infoStatusViewModel) {
        tvStatusText.setText(infoStatusViewModel.getStatus());
        tvDescriptionText.setText(infoStatusViewModel.getDescription());
        tvSiteText.setText(infoStatusViewModel.getSite());
    }

    @Override
    public void unbindViewHolder() {
        tvStatusText.setText(null);
        tvDescriptionText.setText(null);
        tvSiteText.setText(null);
    }
}