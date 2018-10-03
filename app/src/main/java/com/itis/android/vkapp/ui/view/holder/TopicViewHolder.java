package com.itis.android.vkapp.ui.view.holder;

import android.view.View;
import android.widget.TextView;

import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.model.Place;
import com.itis.android.vkapp.model.view.TopicViewModel;
import com.itis.android.vkapp.ui.activity.BaseActivity;
import com.itis.android.vkapp.ui.fragment.TopicCommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

    @BindView(R.id.tv_title)
    public TextView tvTitle;

    @BindView(R.id.tv_comments_count)
    public TextView tvCommentsCount;

    @Inject
    MyFragmentManager mFragmentManager;

    public TopicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        App.getApplicationComponent().inject(this);
    }

    @Override
    public void bindViewHolder(TopicViewModel topicViewModel) {
        itemView.setOnClickListener(view ->
                mFragmentManager.addFragment((BaseActivity) view.getContext(),
                        TopicCommentsFragment.newInstance(new Place(String.valueOf(topicViewModel.getGroupId()), String.valueOf(topicViewModel.getId()))),
                        R.id.main_wrapper));

        tvTitle.setText(topicViewModel.getTitle());
        tvCommentsCount.setText(topicViewModel.getCommentsCount());
    }

    @Override
    public void unbindViewHolder() {
        tvTitle.setText(null);
        tvCommentsCount.setText(null);
    }
}
