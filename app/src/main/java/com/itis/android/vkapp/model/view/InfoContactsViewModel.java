package com.itis.android.vkapp.model.view;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.ui.activity.BaseActivity;
import com.itis.android.vkapp.ui.fragment.InfoContactsFragment;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoContactsViewModel extends BaseViewModel {
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }


    public static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;

        public InfoContactsViewHolder(View itemView) {
            super(itemView);
            App.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {
            itemView.setOnClickListener(view -> {
                Log.d("CLICK_LINK", "click to InfoLinksViewModel");
                mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoContactsFragment(),
                        R.id.main_wrapper);
            });
        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
