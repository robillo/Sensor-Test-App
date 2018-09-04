package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.ML_VISION.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MachineLearningDialogFragment extends DialogFragment implements MachineLearningDialogInterface {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.results)
    TextView results;

    @BindView(R.id.header_text)
    TextView headerText;

    public MachineLearningDialogFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_machine_learning_dialog_fragment, container);
        setup(v);
        return v;
    }

    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);
    }

    public void setResults(String text) {
        progressBar.setVisibility(View.GONE);
        results.setText(Html.fromHtml(text));
    }

    public void setHeader(String header) {
        @SuppressWarnings("StringBufferReplaceableByString")
        StringBuilder builder = new StringBuilder(header).append(" Results");
        headerText.setText(builder.toString());
    }
}
