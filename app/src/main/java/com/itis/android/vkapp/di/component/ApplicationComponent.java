package com.itis.android.vkapp.di.component;

import com.itis.android.vkapp.common.manager.NetworkManager;
import com.itis.android.vkapp.di.module.ApplicationModule;
import com.itis.android.vkapp.di.module.ManagerModule;
import com.itis.android.vkapp.di.module.RestModule;
import com.itis.android.vkapp.model.view.CommentBodyViewModel;
import com.itis.android.vkapp.model.view.CommentFooterViewModel;
import com.itis.android.vkapp.model.view.InfoContactsViewModel;
import com.itis.android.vkapp.model.view.InfoLinksViewModel;
import com.itis.android.vkapp.mvp.presenter.BoardPresenter;
import com.itis.android.vkapp.mvp.presenter.CommentsPresenter;
import com.itis.android.vkapp.mvp.presenter.InfoContactsPresenter;
import com.itis.android.vkapp.mvp.presenter.InfoLinksPresenter;
import com.itis.android.vkapp.mvp.presenter.InfoPresenter;
import com.itis.android.vkapp.mvp.presenter.MainPresenter;
import com.itis.android.vkapp.mvp.presenter.MembersPresenter;
import com.itis.android.vkapp.mvp.presenter.NewsFeedPresenter;
import com.itis.android.vkapp.mvp.presenter.OpenedCommentPresenter;
import com.itis.android.vkapp.mvp.presenter.OpenedPostPresenter;
import com.itis.android.vkapp.mvp.presenter.TopicCommentsPresenter;
import com.itis.android.vkapp.ui.activity.BaseActivity;
import com.itis.android.vkapp.ui.activity.MainActivity;
import com.itis.android.vkapp.ui.fragment.CommentsFragment;
import com.itis.android.vkapp.ui.fragment.InfoContactsFragment;
import com.itis.android.vkapp.ui.fragment.InfoLinksFragment;
import com.itis.android.vkapp.ui.fragment.NewsFeedFragment;
import com.itis.android.vkapp.ui.fragment.OpenedCommentFragment;
import com.itis.android.vkapp.ui.fragment.OpenedPostFragment;
import com.itis.android.vkapp.ui.fragment.TopicCommentsFragment;
import com.itis.android.vkapp.ui.view.holder.NewsItemBodyHolder;
import com.itis.android.vkapp.ui.view.holder.NewsItemFooterHolder;
import com.itis.android.vkapp.ui.view.holder.TopicViewHolder;
import com.itis.android.vkapp.ui.view.holder.attachment.ImageAttachmentHolder;
import com.itis.android.vkapp.ui.view.holder.attachment.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    // activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    //fragment
    void inject(NewsFeedFragment fragment);
    void inject(OpenedPostFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(TopicCommentsFragment fragment);
    void inject(InfoContactsFragment fragment);
    void inject(InfoLinksFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(TopicViewHolder holder);
    void inject(InfoContactsViewModel.InfoContactsViewHolder holder);
    void inject(InfoLinksViewModel.InfoLinksViewHolder holder);

    //presenter
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);
    void inject(OpenedPostPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(OpenedCommentPresenter presenter);
    void inject(TopicCommentsPresenter presenter);
    void inject(InfoContactsPresenter presenter);
    void inject(InfoLinksPresenter presenter);

    //managers
    void inject(NetworkManager manager);
}
