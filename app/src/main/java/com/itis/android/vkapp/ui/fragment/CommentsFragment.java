package com.itis.android.vkapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.model.Place;
import com.itis.android.vkapp.mvp.presenter.BaseFeedPresenter;
import com.itis.android.vkapp.mvp.presenter.CommentsPresenter;
import com.itis.android.vkapp.ui.activity.CreatePostActivity;

import butterknife.ButterKnife;

public class CommentsFragment extends BaseFeedFragment {

    @InjectPresenter
    CommentsPresenter mPresenter;

    Place mPlace;

    public static CommentsFragment newInstance(Place place) {
        Bundle args = new Bundle();
        args.putAll(place.toBundle());

        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplicationComponent().inject(this);

        mPlace = new Place(getArguments());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseActivity(), CreatePostActivity.class);
            intent.putExtra("type", "comment");
            intent.putExtra("owner_id", Integer.parseInt(mPlace.getOwnerId()));
            intent.putExtra("id", Integer.parseInt(mPlace.getPostId()));
            startActivityForResult(intent, 0);
        });
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setPlace(mPlace);
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }

    @Override
    public boolean needFab() {
        return true;
    }
}
