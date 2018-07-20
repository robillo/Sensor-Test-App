package com.appbusters.robinkamboj.senseitall.view.test_activity.fingerprint_test_fragment;

import android.view.View;

public interface FingerprintTestInterface {

    void setup(View v);

    void renderUi(int status);

    void checkIfConfiguredForFingerprint();

}
