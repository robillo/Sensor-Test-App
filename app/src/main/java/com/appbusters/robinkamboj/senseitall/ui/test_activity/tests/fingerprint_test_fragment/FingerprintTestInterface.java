package com.appbusters.robinkamboj.senseitall.ui.test_activity.tests.fingerprint_test_fragment;

import android.view.View;

public interface FingerprintTestInterface {

    void setup(View v);

    void renderUi(int status);

    void checkIfConfiguredForFingerprint();

}
