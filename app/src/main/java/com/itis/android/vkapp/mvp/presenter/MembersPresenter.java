package com.itis.android.vkapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.itis.android.vkapp.App;
import com.itis.android.vkapp.consts.ApiConstants;
import com.itis.android.vkapp.model.Member;
import com.itis.android.vkapp.model.view.BaseViewModel;
import com.itis.android.vkapp.model.view.MemberViewModel;
import com.itis.android.vkapp.mvp.view.BaseFeedView;
import com.itis.android.vkapp.rest.api.GroupsApi;
import com.itis.android.vkapp.rest.model.request.GroupsGetMembersRequestModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    GroupsApi mGroupApi;

    public MembersPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getMembers(new GroupsGetMembersRequestModel(
                ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(baseItemResponseFull -> Observable.fromIterable(baseItemResponseFull.response.getItems()))
                .doOnNext(this::saveToDb)
                .map(MemberViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(MemberViewModel::new);
    }

    public Callable<List<Member>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Member> results = realm.where(Member.class)
                    .findAll().sort(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }
}
