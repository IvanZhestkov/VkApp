package com.itis.android.vkapp.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.itis.android.vkapp.App;
import com.itis.android.vkapp.CurrentUser;
import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.common.manager.NetworkManager;
import com.itis.android.vkapp.model.Profile;
import com.itis.android.vkapp.mvp.view.MainView;
import com.itis.android.vkapp.rest.api.UsersApi;
import com.itis.android.vkapp.rest.model.request.UsersGetRequestModel;
import com.itis.android.vkapp.ui.activity.SettingActivity;
import com.itis.android.vkapp.ui.fragment.BaseFragment;
import com.itis.android.vkapp.ui.fragment.BoardFragment;
import com.itis.android.vkapp.ui.fragment.GroupRulesFragment;
import com.itis.android.vkapp.ui.fragment.InfoFragment;
import com.itis.android.vkapp.ui.fragment.MembersFragment;
import com.itis.android.vkapp.ui.fragment.MyPostsFragment;
import com.itis.android.vkapp.ui.fragment.NewsFeedFragment;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    MyFragmentManager myFragmentManager;

    @Inject
    UsersApi mUserApi;

    @Inject
    NetworkManager mNetworkManager;

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getCurrentUser();
            getViewState().signedIn();
        }
    }

    public MainPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public Observable<Profile> getProfileFromNetwork() {
        return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb);
    }

    private Observable<Profile> getProfileFromDb() {
        return Observable.fromCallable(getListFromRealmCallable());
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Callable<Profile> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }

    @SuppressLint("CheckResult")
    private void getCurrentUser() {
        mNetworkManager.getNetworkObservable()
                .flatMap(aBoolean -> {
                    if (!CurrentUser.isAuthorized()) {
                        getViewState().startSignIn();
                    }
                    return aBoolean
                            ? getProfileFromNetwork()
                            : getProfileFromDb();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(profile -> getViewState().showCurrentUser(profile),
                        Throwable::printStackTrace);
    }

    public void drawerItemClick(int id) {
        BaseFragment fragment = null;

        switch (id) {
            case 1:
                fragment = new NewsFeedFragment();
                break;
            case 2:
                fragment = new MyPostsFragment();
                break;
            case 3:
                getViewState().startActivityFromDrawer(SettingActivity.class);
                return;
            case 4:
                fragment = new MembersFragment();
                break;
            case 5:
                fragment = new BoardFragment();
                break;
            case 6:
                fragment = new InfoFragment();
                break;
            case 7:
                fragment = new GroupRulesFragment();
                break;
        }

        if (fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
            getViewState().showFragmentFromDrawer(fragment);
        }
    }
}
