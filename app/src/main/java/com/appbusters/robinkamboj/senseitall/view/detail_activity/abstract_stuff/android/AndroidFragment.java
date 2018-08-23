package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
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

        if(getActivity() == null) return;

        String version = ((DetailActivity) getActivity()).intentData.getName();
        loadUri = AppConstants.versionMapUri.get(version);

    }

    @Override
    public void loadUri() {
        if(isConnected(getContext())) {
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
            webView.loadUrl(loadUri);
        }
        else {
            if(getActivity() == null) return;
            Snackbar mySnackbar = Snackbar.make(((DetailActivity) getActivity()).coordinatorLayout,
                    "NO INTERNET CONNECTION", Snackbar.LENGTH_INDEFINITE);
            mySnackbar.setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadUri();
                }
            });
            mySnackbar.show();
        }
    }

    @Override
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();

            return (info != null && info.isConnected());
        }
        return false;
    }
}
