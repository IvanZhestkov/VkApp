package com.itis.android.vkapp.model.view;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.ui.activity.BaseActivity;
import com.itis.android.vkapp.ui.fragment.InfoLinksFragment;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoLinksViewModel extends BaseViewModel {
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new InfoLinksViewHolder(view);
    }


    public static class InfoLinksViewHolder extends BaseViewHolder<InfoLinksViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinksViewHolder(View itemView) {
            super(itemView);
            App.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {
            itemView.setOnClickListener(view -> {
                Log.d("CLICK_LINK", "click to InfoLinksViewModel");
                mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoLinksFragment(),
                        R.id.main_wrapper);
            });

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
