package com.itis.android.vkapp.model.view.counter;

import com.itis.android.vkapp.model.countable.Comments;

public class CommentCounterViewModel extends CounterViewModel {

    private Comments mComments;

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        this.mComments = comments;
    }

    public Comments getComments() {
        return mComments;
    }
}