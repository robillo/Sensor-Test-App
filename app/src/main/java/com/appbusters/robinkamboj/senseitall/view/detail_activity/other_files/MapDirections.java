package com.appbusters.robinkamboj.senseitall.view.detail_activity.other_files;

import java.util.HashMap;

import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AUGMENTED_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.AV_OUTPUTS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BACK_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BARCODE_READER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BATTERY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.BLUETOOTH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.COMPASS;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FACE_EMOJI;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FINGERPRINT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FLASH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.FRONT_CAMERA;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.GPS_LOCATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.LABEL_GENERATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MOTION_DETECT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.MULTI_TOUCH;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SCREEN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ACCELEROMETER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GRAVITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_GYROSCOPE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LIGHT;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_LINEAR_ACCELERATION;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_MAGNETIC_FIELD;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PRESSURE;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_PROXIMITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_ROTATION_VECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_COUNTER;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SENSOR_STEP_DETECTOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.SOUND;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.TEXT_SCAN;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIBRATOR;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.VIRTUAL_REALITY;
import static com.appbusters.robinkamboj.senseitall.utils.AppConstants.WIFI;

public class MapDirections {

    public static HashMap<String, String> sensorMapDirections = new HashMap<>();
    public static HashMap<String, String> sensorMapHints = new HashMap<>();

    static {
        sensorMapDirections.put(
                SENSOR_PROXIMITY,
                "Place Your Hand Above Proximity Sensor To Increment Counter."
        );
        sensorMapDirections.put(
                SENSOR_ACCELEROMETER,
                "Move The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_LIGHT,
                "Point Your Light Sensor Towards Light To Observe Bar Color Change."
        );
        sensorMapDirections.put(
                BACK_CAMERA,
                "You Should See Back Camera Preview In The Test."
        );
        sensorMapDirections.put(
                FRONT_CAMERA,
                "You Should See Front Camera Preview In The Test."
        );
        sensorMapDirections.put(
                GPS_LOCATION,
                "You Should See Your Location In The Test."
        );
        sensorMapDirections.put(
                WIFI,
                "You Should See Your Current Connection As Well As All Available WiFi Connections In The Test."
        );
        sensorMapDirections.put(
                BLUETOOTH,
                "You Should See Your Current Connection As Well As All Available Bluetooth Connections In The Test."
        );
        sensorMapDirections.put(
                SCREEN,
                "Draw By Using Touch Anywhere On The Screen In The Test."
        );
        sensorMapDirections.put(
                BATTERY,
                "See Your Current Battery Level As Well As Whether It Is Plugged In For Charging Or Not."
        );
        sensorMapDirections.put(
                SOUND,
                "Play A Sound Track And Adjust It's Volume To See If The Speaker And Volume UP/DOWN Buttons Are Working."
        );
        sensorMapDirections.put(
                VIBRATOR,
                "Click Anywhere On The Next Screen To Vibrate Your Screen Once Per Click."
        );
        sensorMapDirections.put(
                AV_OUTPUTS,
                "Plug In/Out To Verify If Headphone Jack Is Functioning Properly Or Not."
        );
        sensorMapDirections.put(
                FLASH,
                "Click Anywhere On The Next Screen To Activate/Deactivate Flash On Your Device."
        );
        sensorMapDirections.put(
                MULTI_TOUCH,
                "Click On The Next Screen Multiple Times Simultaneously To Create Circles On It."
        );
        sensorMapDirections.put(
                FINGERPRINT,
                "Place Your Enrolled Fingerprints On The Fingerprint Sensor To Authenticate The Screen."
        );
        sensorMapDirections.put(
                COMPASS,
                "Rotate Your Phone In The Horizontal Plane (Parallel To The Floor) To Verify If The North Pole Of The Compass Still Points Towards The Same Direction."
        );
        sensorMapDirections.put(
                SENSOR_GYROSCOPE,
                "Rotate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_GRAVITY,
                "Accelerate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_LINEAR_ACCELERATION,
                "Accelerate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_ROTATION_VECTOR,
                "Rotate The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_MAGNETIC_FIELD,
                "Move The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                SENSOR_PRESSURE,
                "Move The Device Forward And Backwards/Left And Right/Up And Down To Observe Drastic Changes."
        );
        sensorMapDirections.put(
                VIRTUAL_REALITY,
                "Move the screen to observe the virtual world."
        );
        sensorMapDirections.put(
                LABEL_GENERATOR,
                "Capture / Upload an image to generate labels."
        );
        sensorMapDirections.put(
                FACE_DETECT,
                "Capture / Upload an image to detect faces."
        );
        sensorMapDirections.put(
                BARCODE_READER,
                "Capture / Upload an image to detect barcodes."
        );
        sensorMapDirections.put(
                TEXT_SCAN,
                "Capture / Upload an image to detect text."
        );
        sensorMapDirections.put(
                SENSOR_STEP_COUNTER,
                "Start walking to track your progress as number of steps you take."
        );
        sensorMapDirections.put(
                SENSOR_STEP_DETECTOR,
                "Start walking to validate if your device detects a step or not."
        );
        //TODO : ATTACH ACTUAL DATA HERE AND NOT UN_ORIGINAL
        sensorMapDirections.put(
                MOTION_DETECT,
                ""
        );
        sensorMapDirections.put(
                FACE_EMOJI,
                ""
        );
        sensorMapDirections.put(
                AUGMENTED_REALITY,
                ""
        );

        sensorMapHints.put(
                SENSOR_PROXIMITY,
                "A proximity sensor is usually located at the top of a mobile screen."
        );
        sensorMapHints.put(
                SENSOR_ACCELEROMETER,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_LIGHT,
                "A light sensor is usually located at the top of a mobile screen."
        );
        sensorMapHints.put(
                BACK_CAMERA,
                "In Phones With Auto-focus Feature, The Focus Should Eventually Shift To The Object You Will Point Towards."
        );
        sensorMapHints.put(
                FRONT_CAMERA,
                "In Phones With Auto-focus Feature, The Focus Should Eventually Shift To The Object You Will Point Towards."
        );
        sensorMapHints.put(
                GPS_LOCATION,
                "Tap the marker for location coordinates (Longitude And Latitude)."
        );
        sensorMapHints.put(
                WIFI,
                "This Might Not Work If Current WiFi State Is Disabled."
        );
        sensorMapHints.put(
                BLUETOOTH,
                "This Might Not Work If Current Bluetooth State Is Disabled."
        );
        sensorMapHints.put(
                SCREEN,
                "You Can Try Drawing Over Places On Screen That You Are Suspicious Of Not Working."
        );
        sensorMapHints.put(
                BATTERY,
                "You Will Also Observe Changes As Your Battery Level Decreases Or Increases."
        );
        sensorMapHints.put(
                SOUND,
                "Alternatively You Can Increase/Decrease Volume By Dragging The Slider."
        );
        sensorMapHints.put(
                VIBRATOR,
                "Avoid Using Vibrations On Call Reception To Avoid Damage To The Vibrator Motor."
        );
        sensorMapHints.put(
                AV_OUTPUTS,
                "On Plugging In The Port, Sometimes It May Momentarily Set State To Plugged And Then Unplugged. This Is Normal."
        );
        sensorMapHints.put(
                FLASH,
                "It Is Recommended To Avoid Turning On Flash For Prolonged Period Of Time To Avoid Device Overheating."
        );
        sensorMapHints.put(
                MULTI_TOUCH,
                "Try To Get Maximum Number Of Circles On The Screen."
        );
        sensorMapHints.put(
                FINGERPRINT,
                "For Fingers Not Enrolled On The Device, Authentication Will Fail. You Can Reset The Screen To Re-test."
        );
        sensorMapHints.put(
                COMPASS,
                "If You Are Not Getting Expected Results, Try Re-calibrating The Device In Settings."
        );
        sensorMapHints.put(
                SENSOR_GYROSCOPE,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_GRAVITY,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_LINEAR_ACCELERATION,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_ROTATION_VECTOR,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_MAGNETIC_FIELD,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                SENSOR_PRESSURE,
                "Moving The Device In One Orientation Results In Hike In One Of The Three Lines: x (Red), y (Green), z (Blue)."
        );
        sensorMapHints.put(
                FACE_DETECT,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                BARCODE_READER,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                TEXT_SCAN,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                VIRTUAL_REALITY,
                "Increase the screen brightness to max for a more pleasant experience."
        );
        sensorMapHints.put(
                LABEL_GENERATOR,
                "MAKE SURE to set the orientation of the selected image correctly."
        );
        sensorMapHints.put(
                SENSOR_STEP_DETECTOR,
                "It is recommended to keep the phone at the waist level or in a pocket as you walk."
        );
        sensorMapHints.put(
                SENSOR_STEP_COUNTER,
                "It is recommended to keep the phone at the waist level or in a pocket as you walk."
        );
        //TODO : ATTACH ACTUAL DATA HERE AND NOT UN_ORIGINAL
        sensorMapHints.put(
                MOTION_DETECT,
                ""
        );
        sensorMapHints.put(
                FACE_EMOJI,
                ""
        );
        sensorMapHints.put(
                AUGMENTED_REALITY,
                ""
        );
    }
}
