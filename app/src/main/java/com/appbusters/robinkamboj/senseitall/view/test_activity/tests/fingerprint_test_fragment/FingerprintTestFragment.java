package com.appbusters.robinkamboj.senseitall.view.test_activity.tests.fingerprint_test_fragment;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.controller.FingerprintHandler;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.KEYGUARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FingerprintTestFragment extends Fragment implements FingerprintTestInterface {

    public static final int STATUS_RESET = 0;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAILURE = 2;

    private KeyStore keyStore;
    private static final String KEY_NAME = "example_key";
    private Cipher cipher;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.reset_button)
    TextView resetButton;

    @BindView(R.id.fingerprint_text_status)
    TextView fingerprintStatus;

    @BindView(R.id.fingerprint_image)
    ImageView fingerprintImage;

    public FingerprintTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fingerprint_test, container, false);
        setup(v);
        return v;
    }

    @SuppressLint("NewApi")
    @Override
    public void setup(View v) {
        ButterKnife.bind(this, v);

        Glide.with(this).load(R.drawable.fingerprint).into(fingerprintImage);

        renderUi(STATUS_RESET);

        checkIfConfiguredForFingerprint();
    }

    @Override
    public void renderUi(int status) {
        if(getActivity() == null) return;
        if(status == STATUS_RESET) {
            fingerprintImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorMajorDark));
            resetButton.setVisibility(View.INVISIBLE);
            resetButton.setClickable(false);
            fingerprintStatus.setVisibility(View.INVISIBLE);
        }
        else if(status == STATUS_SUCCESS) {
            fingerprintImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.green_shade_three));
            resetButton.setVisibility(View.VISIBLE);
            resetButton.setClickable(true);
            fingerprintStatus.setVisibility(View.VISIBLE);
            fingerprintStatus.setText(getActivity().getString(R.string.fingerprint_success));
        }
        else if(status == STATUS_FAILURE) {
            fingerprintImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_shade_four));
            resetButton.setVisibility(View.VISIBLE);
            resetButton.setClickable(true);
            fingerprintStatus.setVisibility(View.VISIBLE);
            fingerprintStatus.setText(getActivity().getString(R.string.fingerprint_failure));
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void checkIfConfiguredForFingerprint() {
        if(getActivity() == null) return;

        KeyguardManager keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager =
                (FingerprintManager) getActivity().getSystemService(Context.FINGERPRINT_SERVICE);

        if (keyguardManager != null && !keyguardManager.isKeyguardSecure()) {
            Toast.makeText(getActivity(), R.string.security_not_enabled, Toast.LENGTH_LONG).show();
        }
        if (fingerprintManager != null && !fingerprintManager.hasEnrolledFingerprints()) {
            Toast.makeText(getActivity(), R.string.no_fingerprints, Toast.LENGTH_LONG).show();
        }
        else {
            generateKey();

            if (cipherInit()) {
                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                FingerprintHandler helper = new FingerprintHandler(this);
                helper.startAuth(fingerprintManager, cryptoObject);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES,
                    "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "fingerprint provider exception", 400);
            View view = snackbar.getView();
            TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            return;
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            Toast.makeText(getActivity(), "Oops. An Exception Occurred!", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            Toast.makeText(getActivity(), "Oops. Failed To Get Cypher Instance!", Toast.LENGTH_SHORT).show();
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            Toast.makeText(getActivity(), "Oops. Failed To Init Cypher Instance!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @SuppressLint("NewApi")
    @OnClick(R.id.reset_button)
    public void setResetButton() {
        renderUi(0);
        checkIfConfiguredForFingerprint();
    }
}
