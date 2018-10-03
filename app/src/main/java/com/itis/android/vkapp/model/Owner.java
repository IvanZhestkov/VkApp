package com.itis.android.vkapp.model;

import com.vk.sdk.api.model.Identifiable;

public interface Owner extends Identifiable {

    String getFullName();
    String getPhoto();
}
