package com.itis.android.vkapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.itis.android.vkapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webView;

    public static ImageFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //in xml?
        webView.setBackgroundColor(getResources().getColor(R.color.colorDefaultWhite));

        webView.loadUrl(getArguments().getString("url"));
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_image;
    }
}
