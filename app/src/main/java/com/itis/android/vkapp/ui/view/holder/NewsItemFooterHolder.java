package com.itis.android.vkapp.ui.view.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.common.utils.Utils;
import com.itis.android.vkapp.common.utils.VkListHelper;
import com.itis.android.vkapp.model.Place;
import com.itis.android.vkapp.model.WallItem;
import com.itis.android.vkapp.model.countable.Likes;
import com.itis.android.vkapp.model.view.NewsItemFooterViewModel;
import com.itis.android.vkapp.model.view.counter.CommentCounterViewModel;
import com.itis.android.vkapp.model.view.counter.LikeCounterViewModel;
import com.itis.android.vkapp.model.view.counter.RepostCounterViewModel;
import com.itis.android.vkapp.event.LikeEventOnSubscribe;
import com.itis.android.vkapp.rest.api.WallApi;
import com.itis.android.vkapp.rest.model.request.WallGetByIdRequestModel;
import com.itis.android.vkapp.ui.activity.BaseActivity;
import com.itis.android.vkapp.ui.fragment.CommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel> {

    public static final String POST = "post";

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_likes_count)
    TextView tvLikesCount;

    @BindView(R.id.tv_likes_icon)
    TextView tvLikesIcon;

    @BindView(R.id.tv_comments_icon)
    TextView tvCommentsIcon;

    @BindView(R.id.tv_comments_count)
    TextView tvCommentsCount;

    @BindView(R.id.tv_reposts_icon)
    TextView tvRepostIcon;

    @BindView(R.id.tv_reposts_count)
    TextView tvRepostsCount;

    @BindView(R.id.rl_comments)
    View rlComments;

    @BindView(R.id.rl_likes)
    View rlLikes;

    @Inject
    WallApi mWallApi;

    @Inject
    MyFragmentManager mFragmentManager;

    @Inject
    Typeface mGoogleFontTypeface;

    private Resources mResources;
    private Context mContext;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        App.getApplicationComponent().inject(this);

        mContext = itemView.getContext();
        mResources = mContext.getResources();

        ButterKnife.bind(this, itemView);

        tvLikesIcon.setTypeface(mGoogleFontTypeface);
        tvCommentsIcon.setTypeface(mGoogleFontTypeface);
        tvRepostIcon.setTypeface(mGoogleFontTypeface);
    }

    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));

        bindLikes(item.getLikes());
        bindComments(item.getComments());
        bindReposts(item.getReposts());

        rlComments.setOnClickListener(view ->
                mFragmentManager.addFragment((BaseActivity) view.getContext(),
                        CommentsFragment.newInstance(new Place(String.valueOf(item.getOwnerId()), String.valueOf(item.getId()))),
                        R.id.main_wrapper));

        rlLikes.setOnClickListener(view -> like(item));
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Observable<LikeCounterViewModel> likeObservable(int ownerId, int postId, Likes likes) {
        return Observable.create(new LikeEventOnSubscribe(POST, ownerId, postId, likes))
                .observeOn(Schedulers.io())
                .flatMap(count -> mWallApi.getById(new WallGetByIdRequestModel(ownerId, postId).toMap()))
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .doOnNext(this::saveToDb)
                .map(wallItem -> new LikeCounterViewModel(wallItem.getLikes()));
    }

    @SuppressLint("CheckResult")
    public void like(NewsItemFooterViewModel item) {
        WallItem wallItem = getWallItemFromRealm(item.getId());
        likeObservable(wallItem.getOwnerId(), wallItem.getId(), wallItem.getLikes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likes -> {
                    item.setLikes(likes);
                    bindLikes(likes);
                }, error -> error.printStackTrace());
    }

    public WallItem getWallItemFromRealm(int postId) {
        Realm realm = Realm.getDefaultInstance();
        WallItem wallItem = realm.where(WallItem.class)
                .equalTo("id", postId)
                .findFirst();

        return realm.copyFromRealm(wallItem);
    }

    private void bindLikes(LikeCounterViewModel likes) {
        tvLikesCount.setText(String.valueOf(likes.getCount()));
        tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
        tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));
    }

    private void bindComments(CommentCounterViewModel comments) {
        tvCommentsCount.setText(String.valueOf(comments.getCount()));
        tvCommentsCount.setTextColor(mResources.getColor(comments.getTextColor()));
        tvCommentsIcon.setTextColor(mResources.getColor(comments.getIconColor()));

    }

    private void bindReposts(RepostCounterViewModel reposts) {
        tvRepostsCount.setText(String.valueOf(reposts.getCount()));
        tvRepostsCount.setTextColor(mResources.getColor(reposts.getTextColor()));
        tvRepostIcon.setTextColor(mResources.getColor(reposts.getIconColor()));

    }

    @Override
    public void unbindViewHolder() {
        tvDate.setText(null);
        tvLikesCount.setText(null);
        tvCommentsCount.setText(null);
        tvRepostsCount.setText(null);
    }
}
