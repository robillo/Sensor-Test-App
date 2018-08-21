package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class AndroidFragment extends Fragment implements AndroidInterface {

    private String loadUri = null;

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        initialize();
        loadUri();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initialize() {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        if(getActivity() == null) return;

        String version = ((DetailActivity) getActivity()).intentData.getName();
        loadUri = AppConstants.versionMapUri.get(version);
    }

    @Override
    public void loadUri() {
        webView.loadUrl(loadUri);
    }
}
