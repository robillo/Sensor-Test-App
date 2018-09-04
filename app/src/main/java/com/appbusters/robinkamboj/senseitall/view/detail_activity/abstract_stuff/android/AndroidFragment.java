package com.appbusters.robinkamboj.senseitall.view.detail_activity.abstract_stuff.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.model.recycler.LearnMoreItem;
import com.appbusters.robinkamboj.senseitall.view.detail_activity.DetailActivity;
import com.appbusters.robinkamboj.senseitall.view.learn_more_activity.LearnMoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.KITKAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LOLLIPOP;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MARSHMALLOW;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.NOUGAT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.OREO;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.PIE;

public abstract class AndroidFragment extends Fragment implements AndroidInterface {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private String[] headers, descriptions, images;
    private List<LearnMoreItem> list = new ArrayList<>();

//    private String loadUri = null;
//
//    @BindView(R.id.error_or_load_text)
//    TextView errorOrLoadText;
//
//    @BindView(R.id.load_progress)
//    ProgressBar loadingProgressBar;
//
//    @BindView(R.id.web_view)
//    WebView webView;

    @Override
    public void setup(View v) {
//        ButterKnife.bind(this, v);
//
//        initialize();
//        loadUri();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initialize() {

//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        if(getActivity() == null) return;
//
//        String version = ((DetailActivity) getActivity()).intentData.getName();
//        loadUri = AppConstants.versionMapUri.get(version);

    }

    @Override
    public void loadUri() {
//        if(isConnected(getContext())) {
//            webView.setWebChromeClient(new WebChromeClient() {
//                @SuppressLint("SetTextI18n")
//                public void onProgressChanged(WebView view, int progress) {
//                    errorOrLoadText.setText(R.string.loading);
//                    loadingProgressBar.setProgress(progress);
//
//                    if(progress == 100) {
//                        loadingProgressBar.setVisibility(View.GONE);
//                        errorOrLoadText.setVisibility(View.GONE);
//                    }
//                }
//            });
//
//            webView.setWebViewClient(new WebViewClient());
//            webView.loadUrl(loadUri);
//        }
//        else {
//            if(getActivity() == null) return;
//            Snackbar mySnackbar = Snackbar.make(((DetailActivity) getActivity()).coordinatorLayout,
//                    "NO INTERNET CONNECTION", Snackbar.LENGTH_INDEFINITE);
//            mySnackbar.setAction("RETRY", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    loadUri();
//                }
//            });
//            mySnackbar.show();
//        }
    }

    @Override
    public boolean isConnected(Context context) {
//
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        if (null != cm) {
//            NetworkInfo info = cm.getActiveNetworkInfo();
//
//            return (info != null && info.isConnected());
//        }
        return false;
    }


    @Override
    public void setupView(View v) {
        ButterKnife.bind(this, v);

        initializeData();

        setAdapter();
    }

    @Override
    public void initializeData() {
        DetailActivity activity = (DetailActivity) getActivity();
        if(activity == null) return;
        String recyclerName = activity.recyclerName;

        switch (recyclerName) {
            case PIE: {
                headers = getResources().getStringArray(R.array.pie_headers);
                descriptions = getResources().getStringArray(R.array.pie_descriptions);
                images = getResources().getStringArray(R.array.pie_images);
                break;
            }
            case OREO: {
                headers = getResources().getStringArray(R.array.oreo_headers);
                descriptions = getResources().getStringArray(R.array.oreo_descriptions);
                images = getResources().getStringArray(R.array.oreo_images);
                break;
            }
            case NOUGAT: {
                headers = getResources().getStringArray(R.array.nougat__headers);
                descriptions = getResources().getStringArray(R.array.nougat_descriptions);
                images = getResources().getStringArray(R.array.nougat_images);
                break;
            }
            case MARSHMALLOW: {
                headers = getResources().getStringArray(R.array.marshmallow_headers);
                descriptions = getResources().getStringArray(R.array.marshmallow_descriptions);
                images = getResources().getStringArray(R.array.marshmallow_images);
                break;
            }
            case LOLLIPOP: {
                headers = getResources().getStringArray(R.array.lollipop_headers);
                descriptions = getResources().getStringArray(R.array.lollipop_descriptions);
                images = getResources().getStringArray(R.array.lollipop_images);
                break;
            }
            case KITKAT: {
                headers = getResources().getStringArray(R.array.kitkat_headers);
                descriptions = getResources().getStringArray(R.array.kitkat_descriptions);
                images = getResources().getStringArray(R.array.kitkat_images);
                break;
            }
        }

        for(int i=0; i<headers.length; i++) {
            list.add(new LearnMoreItem(null, headers[i], descriptions[i]));
        }
    }

    @Override
    public void setAdapter() {
        LearnMoreAdapter adapter = new LearnMoreAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
