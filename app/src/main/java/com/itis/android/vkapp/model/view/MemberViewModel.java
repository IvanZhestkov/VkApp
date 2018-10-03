package com.itis.android.vkapp.model.view;

import android.view.View;

import com.itis.android.vkapp.model.Member;
import com.itis.android.vkapp.model.Profile;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;
import com.itis.android.vkapp.ui.view.holder.MemberViewHolder;

public class MemberViewModel extends BaseViewModel {

    private int id;

    private int groupId;

    private String photo;

    private String mFullName;

    public MemberViewModel() {
    }

    public MemberViewModel(Member member) {
        this.id = member.getId();
        this.groupId = member.getGroupId();
        this.photo = member.getPhoto();
        this.mFullName = member.getFullName();
    }

    public MemberViewModel(Profile profile) {
        this.photo = profile.getPhoto();
        this.mFullName = profile.getFullName();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Member;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new MemberViewHolder(view);
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFullName() {
        return mFullName;
    }
}
