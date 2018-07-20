package com.appbusters.robinkamboj.senseitall.controller;

import android.annotation.TargetApi;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import com.appbusters.robinkamboj.senseitall.view.test_activity.fingerprint_test_fragment.FingerprintTestFragment;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    @SuppressWarnings("FieldCanBeLocal")
    private FingerprintTestFragment fragment;

    public FingerprintHandler(FingerprintTestFragment fragment) {
        this.fragment = fragment;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {

        CancellationSignal cancellationSignal = new CancellationSignal();

        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {

    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {

    }

    @Override
    public void onAuthenticationFailed() {
        fragment.renderUi(FingerprintTestFragment.STATUS_FAILURE);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        fragment.renderUi(FingerprintTestFragment.STATUS_SUCCESS);
    }

}