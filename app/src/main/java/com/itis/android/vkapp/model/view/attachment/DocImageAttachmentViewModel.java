package com.itis.android.vkapp.model.view.attachment;

import android.view.View;

import com.itis.android.vkapp.common.utils.Utils;
import com.itis.android.vkapp.model.attachment.doc.Document;
import com.itis.android.vkapp.model.attachment.doc.Size;
import com.itis.android.vkapp.model.view.BaseViewModel;
import com.itis.android.vkapp.ui.view.holder.BaseViewHolder;
import com.itis.android.vkapp.ui.view.holder.attachment.DocImageAttachmentHolder;

import java.util.List;

public class DocImageAttachmentViewModel extends BaseViewModel {


    private String mTitle;
    private String mSize;
    private String mExt;

    private String mImage;

    private String mUrl;


    public DocImageAttachmentViewModel(Document doc) {
        if (doc.getTitle().equals("")) {
            mTitle = "Document";
        } else {
            mTitle = Utils.removeExtFromText(doc.getTitle());
        }

        mUrl = doc.getUrl();
        mSize = Utils.formatSize(doc.getSize());
        mExt = "." + doc.getExt();

        List<Size> sizes = doc.getPreview().getPhoto().getSizes();
        mImage = sizes.get(sizes.size() - 1).getSrc();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDocImage;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new DocImageAttachmentHolder(view);
    }


    public String getmUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSize() {
        return mSize;
    }

    public String getExt() {
        return mExt;
    }

    public String getImage() {
        return mImage;
    }
}

