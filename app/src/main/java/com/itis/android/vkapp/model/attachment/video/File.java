package com.itis.android.vkapp.model.attachment.video;

import io.realm.RealmObject;

public class File extends RealmObject {

    private String external;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}
