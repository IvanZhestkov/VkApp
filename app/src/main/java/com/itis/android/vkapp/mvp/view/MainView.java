package com.itis.android.vkapp.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.itis.android.vkapp.model.Profile;
import com.itis.android.vkapp.ui.fragment.BaseFragment;

public interface MainView extends MvpView {
    void startSignIn();
    void signedIn();
    void showCurrentUser(Profile profile);
    void showFragmentFromDrawer(BaseFragment baseFragment);
    void startActivityFromDrawer(Class<?> act);
}
