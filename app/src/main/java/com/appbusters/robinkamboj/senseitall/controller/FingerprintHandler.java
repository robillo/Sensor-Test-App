package com.appbusters.robinkamboj.senseitall.controller;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.view.activities.sensors.fingerprint.FingerprintActivity;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private FingerprintActivity view;
    private CancellationSignal cancellationSignal;
    private Context appContext;
    private boolean is_orient_same;

    public FingerprintHandler(Context context, boolean is_orient_same) {
        appContext = context;
        this.is_orient_same = is_orient_same;
    }

    public void startAuth(FingerprintManager manager,
                          FingerprintManager.CryptoObject cryptoObject) {

        cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.USE_FINGERPRINT) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId,
                                      CharSequence errString) {
        if(is_orient_same){
            Toast.makeText(appContext,
                    "Authentication error\n" + errString,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId,
                                     CharSequence helpString) {
        if(is_orient_same){
            Toast.makeText(appContext,
                    "Authentication help\n" + helpString,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAuthenticationFailed() {
        Snackbar.make(view.activity_fingerprint, "Authentication Failed.", Snackbar.LENGTH_INDEFINITE).setAction("Okay", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    public void onAuthenticationSucceeded(
            FingerprintManager.AuthenticationResult result) {

        Snackbar.make(view.activity_fingerprint, "Authentication Succeeded.", Snackbar.LENGTH_SHORT).setAction("Okay", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();

    }

}