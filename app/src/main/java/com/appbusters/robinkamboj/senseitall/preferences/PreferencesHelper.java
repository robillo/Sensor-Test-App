package com.appbusters.robinkamboj.senseitall.preferences;

import java.util.Set;

interface PreferencesHelper {

    String getHeaderText();
    void setHeaderText(String headerText);

    boolean isDontAskAgainCamera();
    void setIsDontAskAgainCamera(boolean dontAskAgain);

    boolean isDontAskAgainFineLocation();
    void setIsDontAskAgainFineLocation(boolean dontAskAgain);

    boolean isDontAskAgainCoarseLocation();
    void setIsDontAskAgainCoarseLocation(boolean dontAskAgain);

    boolean isDontAskAgainBodySensors();
    void setIsDontAskAgainBodySensors(boolean dontAskAgain);

    boolean isDontAskAgainPhoneState();
    void setIsDontAskAgainPhoneState(boolean dontAskAgain);

    boolean isDontAskAgainRecordAudio();
    void setIsDontAskAgainRecordAudio(boolean dontAskAgain);

    boolean isDontAskAgainUseFingerprint();
    void setIsDontAskAgainUseFingerprint(boolean dontAskAgain);

    boolean isDontAsk(String permission);
    void setIsDontAsk(String permission, boolean isDontAsk);

}
