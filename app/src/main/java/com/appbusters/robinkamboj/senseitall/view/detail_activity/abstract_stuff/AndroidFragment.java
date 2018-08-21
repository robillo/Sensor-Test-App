package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.utils.AppConstants;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class AndroidFragment extends Fragment implements AndroidInterface {

    private String loadUri = null;

    @BindView(R.id.error_or_load_text)
    TextView errorOrLoadText;

    @BindView(R.id.load_progress)
    ProgressBar loadingProgressBar;

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

        webView.setWebChromeClient(new WebChromeClient() {
            @SuppressLint("SetTextI18n")
            public void onProgressChanged(WebView view, int progress) {
                errorOrLoadText.setText(R.string.loading);
                loadingProgressBar.setProgress(progress);

                if(progress == 100) {
                    loadingProgressBar.setVisibility(View.GONE);
                    errorOrLoadText.setVisibility(View.GONE);
                }
            }
        });

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
