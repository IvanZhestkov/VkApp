package com.itis.android.vkapp.ui.view.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.common.utils.UiHelper;
import com.itis.android.vkapp.model.view.NewsItemBodyViewModel;
import com.itis.android.vkapp.ui.activity.BaseActivity;
import com.itis.android.vkapp.ui.fragment.OpenedPostFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    @BindView(R.id.tv_text)
    TextView mText;

    @BindView(R.id.tv_attachments)
    TextView tvAttachments;

    @Inject
    protected Typeface mFontGoogle;

    @Inject
    MyFragmentManager mFragmentManager;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        App.getApplicationComponent().inject(this);

        ButterKnife.bind(this, itemView);

        if (tvAttachments != null) {
            tvAttachments.setTypeface(mFontGoogle);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        mText.setText(item.getText());
        tvAttachments.setText(item.getmAttachmentString());

        itemView.setOnClickListener(view ->
                mFragmentManager.addFragment((BaseActivity) view.getContext(), OpenedPostFragment.newInstance(item.getId()),
                R.id.main_wrapper));

        UiHelper.getInstance().setUpTextViewWithVisibility(mText, item.getText());
        UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, item.getmAttachmentString());
    }

    @Override
    public void unbindViewHolder() {
        mText.setText(null);
        tvAttachments.setText(null);
        itemView.setOnClickListener(null);
    }
}
