package com.itis.android.vkapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.itis.android.vkapp.App;
import com.itis.android.vkapp.consts.ApiConstants;
import com.itis.android.vkapp.model.Group;
import com.itis.android.vkapp.model.view.BaseViewModel;
import com.itis.android.vkapp.model.view.InfoContactsViewModel;
import com.itis.android.vkapp.model.view.InfoLinksViewModel;
import com.itis.android.vkapp.model.view.InfoStatusViewModel;
import com.itis.android.vkapp.mvp.view.BaseFeedView;
import com.itis.android.vkapp.rest.api.GroupsApi;
import com.itis.android.vkapp.rest.model.request.GroupsGetByIdRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

@InjectViewState
public class InfoPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    GroupsApi mGroupApi;

    public InfoPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
                .flatMap(full -> Observable.fromIterable(full.response))
                .doOnNext(this::saveToDb)
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    private List<BaseViewModel> parsePojoModel(Group group) {
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new InfoStatusViewModel(group));
        items.add(new InfoContactsViewModel());
        items.add(new InfoLinksViewModel());

        return items;
    }


    public Callable<Group> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Group result = realm.where(Group.class)
                    .equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
                    .findFirst();
            return realm.copyFromRealm(result);
        };
    }
}
