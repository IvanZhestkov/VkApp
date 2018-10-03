package com.itis.android.vkapp.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.itis.android.vkapp.App;
import com.itis.android.vkapp.R;
import com.itis.android.vkapp.mvp.presenter.BaseFeedPresenter;
import com.itis.android.vkapp.mvp.presenter.NewsFeedPresenter;
import com.itis.android.vkapp.rest.api.WallApi;
import com.itis.android.vkapp.ui.activity.CreatePostActivity;

import javax.inject.Inject;

public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;

    @InjectPresenter
    NewsFeedPresenter mPresenter;

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplicationComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // RX JAVA
       /* mWallApi.get(new WallGetRequestModel(-86529522).toMap())
                .flatMap(new Function<WallGetResponse, ObservableSource<WallItem>>() {
                    @Override
                    public ObservableSource<WallItem> apply(@NonNull WallGetResponse getWallResponse) throws Exception {
                        return Observable.fromIterable(VkListHelper.getWallList(getWallResponse.response));
                    }
                })
                .flatMap(new Function<WallItem, ObservableSource<BaseViewModel>>() {
                    @Override
                    public ObservableSource<BaseViewModel> apply(@NonNull WallItem wallItem) throws Exception {
                        List<BaseViewModel> baseItems = new ArrayList<>();
                        baseItems.add(new NewsItemHeaderViewModel(wallItem));
                        baseItems.add(new NewsItemBodyViewModel(wallItem));
                        baseItems.add(new NewsItemFooterViewModel(wallItem));
                        return Observable.fromIterable(baseItems);
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseViewModel>>() {
                    @Override
                    public void accept(List<BaseViewModel> objects) throws Exception {
                        mAdapter.addItems(objects);
                    }
                });*/

        // LAMBDA
        /*mWallApi.get(new WallGetRequestModel(-100083164).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseViewModels -> mAdapter.addItems(baseViewModels));*/

        // WITHOUT RX
        /*mWallApi.get(new WallGetRequestModel(-100083164).toMap()).enqueue(new Callback<WallGetResponse>() {
            @Override
            public void onResponse(Call<WallGetResponse> call, Response<WallGetResponse> response) {
                List<WallItem> wallItems = VkListHelper.getWallList(response.body().response);
                List<BaseViewModel> list = new ArrayList<>();

                for (WallItem item : wallItems) {
                    list.add(new NewsItemHeaderViewModel(item));
                    list.add(new NewsItemBodyViewModel(item));
                    list.add(new NewsItemFooterViewModel(item));
                }

                *//*List<NewsItemBodyViewModel> list = new ArrayList<>();
                for (WallItem item : response.body().response.getItems()) {
                    list.add(new NewsItemBodyViewModel(item));
                }*//*

                mAdapter.addItems(list);

                Toast.makeText(getActivity(), "Likes: " + response.body().response.getItems().get(0).getLikes().getCount(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<WallGetResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CreatePostActivity.class);
            startActivityForResult(intent, 0);
        });
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public boolean needFab() {
        return true;
    }
}

