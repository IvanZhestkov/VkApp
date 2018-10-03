package com.itis.android.vkapp.di.module;

import com.itis.android.vkapp.rest.RestClient;
import com.itis.android.vkapp.rest.api.AccountApi;
import com.itis.android.vkapp.rest.api.BoardApi;
import com.itis.android.vkapp.rest.api.GroupsApi;
import com.itis.android.vkapp.rest.api.UsersApi;
import com.itis.android.vkapp.rest.api.VideoApi;
import com.itis.android.vkapp.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {
    private RestClient mRestClient;

    public RestModule() {
        mRestClient = new RestClient();
    }

    @Singleton
    @Provides
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Singleton
    @Provides
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }

    @Singleton
    @Provides
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }

    @Singleton
    @Provides
    public GroupsApi provideGroupsApi() {
        return mRestClient.createService(GroupsApi.class);
    }

    @Singleton
    @Provides
    public BoardApi provideBoardApi() {
        return mRestClient.createService(BoardApi.class);
    }

    @Singleton
    @Provides
    public VideoApi provideVideoApi() {
        return mRestClient.createService(VideoApi.class);
    }

    @Provides
    @Singleton
    public AccountApi provideAccountApi() {
        return mRestClient.createService(AccountApi.class);
    }
}
