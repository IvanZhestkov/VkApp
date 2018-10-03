package com.itis.android.vkapp.di.module;

import com.itis.android.vkapp.common.manager.MyFragmentManager;
import com.itis.android.vkapp.common.manager.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Singleton
    @Provides
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }

    @Singleton
    @Provides
    NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
