package com.itis.android.vkapp.model.view.counter;

import com.itis.android.vkapp.model.countable.Likes;

public class LikeCounterViewModel extends CounterViewModel {

    private Likes mLikes;

    public LikeCounterViewModel(Likes likes) {
        super(likes.getCount());

        this.mLikes = likes;

        if (mLikes.getUserLikes() == 1) {
            setRedColor();
        }
    }

    public Likes getLikes() {
        return mLikes;
    }
}
