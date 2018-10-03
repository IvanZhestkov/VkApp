package com.itis.android.vkapp.model.view.counter;

import com.itis.android.vkapp.model.countable.Reposts;

public class RepostCounterViewModel extends CounterViewModel {

    private Reposts mReposts;

    public RepostCounterViewModel(Reposts reposts) {
        super(reposts.getCount());

        this.mReposts = reposts;
        if (mReposts.getUserReposted() == 1) {
            setRedColor();
        }
    }

    public Reposts getReposts() {
        return mReposts;
    }
}