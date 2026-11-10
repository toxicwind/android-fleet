package com.hisense.uifac;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hisense.uifac.menu.util.MenuConfigManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AlarmAndFanSpeedActivity extends Activity {
    private static final Handler mHandler = new Handler() { // from class: com.hisense.uifac.AlarmAndFanSpeedActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 111) {
                return;
            }
            AlarmAndFanSpeedActivity.updateUI();
        }
    };
    Map<String, String> mMap = new HashMap();
    MenuConfigManager localConfigManager = null;
    List<TextView> textViewList = new ArrayList(ShownItems.values().length);
    UpdateControl updater = new UpdateControl();

    enum ShownItems {
        INNER_TEMPERATURE(types.TEMPERATURE),
        LASER_TEMPERATURE(types.TEMPERATURE),
        _TEMPERATURE(types.TEMPERATURE),
        FPGA_TEMPERATURE(types.TEMPERATURE),
        FAN_1_SPEED(types.SPEED),
        FAN_2_SPEED(types.SPEED),
        FAN_3_SPEED(types.SPEED),
        FAN_4_SPEED(types.SPEED),
        FAN_5_SPEED(types.SPEED),
        FAN_6_SPEED(types.SPEED),
        FAN_7_SPEED(types.SPEED),
        RUNNING_TIME(types.RUNNINGTIME),
        LIGHT_SENSOR(types.SENSOR),
        AMBIENT_LIGHT(types.LIGHT),
        DISTANCE_SENSOR_STRENGTH(types.DISTANCESTRENGTH),
        BACKGROUND_NOISE(types.BACKGROUNDNOISE);

        types myType;
        private int shownName;
        TextView view;
        int shownValue = 0;
        String valueForSensor = "";
        String valueForRunningTime = "";

        enum types {
            ALARM,
            TEMPERATURE,
            SPEED,
            RUNNINGTIME,
            SENSOR,
            LIGHT,
            DISTANCESTRENGTH,
            BACKGROUNDNOISE
        }

        public types getMyType() {
            return this.myType;
        }

        public int getShownValue() {
            return this.shownValue;
        }

        public void setShownValue(int i) {
            this.shownValue = i;
        }

        public void setShownValueForSensor(String str) {
            this.valueForSensor = str;
        }

        public String getShowValueForSensor() {
            return this.valueForSensor;
        }

        public void setShownValueForRunningTime(String str) {
            this.valueForRunningTime = str;
        }

        public String getShowValueForRunningTime() {
            return this.valueForRunningTime;
        }

        public TextView getView() {
            return this.view;
        }

        public void setView(TextView textView) {
            this.view = textView;
        }

        /* JADX WARN: Code duplicated, block: B:27:0x0097 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:29:0x0098 A[SYNTHETIC] */
        public static void updateShownValues(int[] iArr, types typesVar) {
            int i;
            if (iArr == null || iArr.length == 0) {
                return;
            }
            int i2 = 0;
            for (ShownItems shownItems : values()) {
                if (shownItems.getMyType().equals(typesVar)) {
                    if (typesVar.equals(types.SPEED)) {
                        i = i2 + 1;
                        shownItems.setShownValue(iArr[i2] * 30);
                    } else {
                        if (typesVar.equals(types.TEMPERATURE)) {
                            i = i2 + 1;
                            shownItems.setShownValue(iArr[i2]);
                        } else if (typesVar.equals(types.SENSOR)) {
                            shownItems.setShownValueForSensor(Arrays.toString(new int[]{(iArr[0] * 256) + iArr[1], (iArr[2] * 256) + iArr[3], (iArr[4] * 256) + iArr[5], (iArr[6] * 256) + iArr[7], (iArr[8] * 256) + iArr[9]}));
                        }
                        if (i2 >= iArr.length) {
                            return;
                        }
                    }
                    i2 = i;
                    if (i2 >= iArr.length) {
                        return;
                    }
                }
            }
        }

        public static void updateShownValue(int i, types typesVar) {
            for (ShownItems shownItems : values()) {
                if (shownItems.getMyType().equals(typesVar)) {
                    if (typesVar.equals(types.LIGHT)) {
                        shownItems.setShownValue(i);
                    }
                    if (typesVar.equals(types.DISTANCESTRENGTH)) {
                        shownItems.setShownValue(i);
                    }
                    if (typesVar.equals(types.BACKGROUNDNOISE)) {
                        shownItems.setShownValue(i);
                    }
                }
            }
        }

        public static void updateShownStrValue(String str, types typesVar) {
            for (ShownItems shownItems : values()) {
                if (shownItems.getMyType().equals(typesVar) && typesVar.equals(types.RUNNINGTIME)) {
                    shownItems.setShownValueForRunningTime(str);
                }
            }
        }

        ShownItems(types typesVar) {
            this.myType = typesVar;
        }
    }

    private void initMap() {
        this.mMap.put("INNER_TEMPERATURE", getString(R.string.inlet_temp));
        this.mMap.put("LASER_TEMPERATURE", getString(R.string.laser_temp_A));
        this.mMap.put("_TEMPERATURE", getString(R.string.laser_temp_B));
        this.mMap.put("FPGA_TEMPERATURE", getString(R.string.fpga_temp));
        this.mMap.put("FAN_1_SPEED", getString(R.string.fan_speed_1));
        this.mMap.put("FAN_2_SPEED", getString(R.string.fan_speed_2));
        this.mMap.put("FAN_3_SPEED", getString(R.string.fan_speed_3));
        this.mMap.put("FAN_4_SPEED", getString(R.string.fan_speed_4));
        this.mMap.put("FAN_5_SPEED", getString(R.string.fan_speed_5));
        this.mMap.put("FAN_6_SPEED", getString(R.string.fan_speed_6));
        this.mMap.put("FAN_7_SPEED", getString(R.string.fan_speed_7));
        this.mMap.put("RUNNING_TIME", getString(R.string.laser_running));
        this.mMap.put("LIGHT_SENSOR", getString(R.string.light_sensor));
        this.mMap.put("AMBIENT_LIGHT", getString(R.string.ambient_light));
        this.mMap.put("DISTANCE_SENSOR_STRENGTH", getString(R.string.distance_sensor_strength));
        this.mMap.put("BACKGROUND_NOISE", getString(R.string.background_noise));
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initMap();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        super.setContentView(linearLayout);
        for (ShownItems shownItems : ShownItems.values()) {
            LinearLayout linearLayout2 = new LinearLayout(this);
            linearLayout2.setOrientation(0);
            TextView textView = new TextView(this);
            textView.setBackgroundColor(getResources().getColor(R.color.black));
            textView.setTextSize(getResources().getDimension(R.dimen.nav_textsize_laser_f2));
            textView.setText(this.mMap.get(shownItems.name()) + "\t\t");
            TextView textView2 = new TextView(this);
            textView2.setBackgroundColor(getResources().getColor(R.color.black));
            textView2.setTextSize(getResources().getDimension(R.dimen.nav_textsize_laser_f2));
            textView2.setText(shownItems.name());
            shownItems.setView(textView2);
            this.textViewList.add(textView2);
            linearLayout2.addView(textView);
            linearLayout2.addView(textView2);
            linearLayout.addView(linearLayout2);
        }
        getWindow().addFlags(128);
        MenuConfigManager menuConfigManager = MenuConfigManager.getInstance(null);
        this.localConfigManager = menuConfigManager;
        if (menuConfigManager == null) {
            Log.i("Fac_AlarmAndFanSpeedActivity", " Alarm and Fan Speed Activity did not get a MenuConfigManager instance");
        } else {
            this.updater.updateForAnHour();
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        this.updater.shutdown();
        super.onStop();
    }

    @Override // android.app.Activity
    public void onPause() {
        this.updater.shutdown();
        super.onPause();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        this.updater.shutdown();
        super.onDestroy();
    }

    private void printArray(int[] iArr, String str) {
        Log.i("Fac_AlarmAndFanSpeedActivity", "arrayName is :" + str);
        if (iArr == null) {
            Log.i("Fac_AlarmAndFanSpeedActivity", "Array is null");
            return;
        }
        if (iArr.length == 0) {
            Log.i("Fac_AlarmAndFanSpeedActivity", "Array is empty");
            return;
        }
        for (int i : iArr) {
            Log.i("Fac_AlarmAndFanSpeedActivity", "i : " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateValue() {
        MenuConfigManager menuConfigManager = this.localConfigManager;
        if (menuConfigManager == null) {
            return;
        }
        int[] fansSpeed = menuConfigManager.getFansSpeed();
        printArray(fansSpeed, "fanSpeeds");
        if (fansSpeed != null && fansSpeed.length != 0) {
            ShownItems.updateShownValues(fansSpeed, ShownItems.types.SPEED);
        }
        Log.i("Fac_AlarmAndFanSpeedActivity", "***localConfigManager.getBoardTemperature()***");
        int[] boardTemperature = this.localConfigManager.getBoardTemperature();
        printArray(boardTemperature, "boardTemps");
        if (boardTemperature != null && boardTemperature.length != 0) {
            ShownItems.updateShownValues(boardTemperature, ShownItems.types.TEMPERATURE);
        }
        int[] alarms = this.localConfigManager.getAlarms();
        printArray(alarms, "alarms");
        if (alarms != null && alarms.length != 0) {
            ShownItems.updateShownValues(alarms, ShownItems.types.ALARM);
        }
        String laserRuningState = this.localConfigManager.getLaserRuningState();
        if (laserRuningState != null) {
            ShownItems.updateShownStrValue(laserRuningState, ShownItems.types.RUNNINGTIME);
        }
        int[] lightSensorValues = this.localConfigManager.getLightSensorValues();
        if (lightSensorValues != null && lightSensorValues.length != 0) {
            ShownItems.updateShownValues(lightSensorValues, ShownItems.types.SENSOR);
        }
        int ambientLightValue = this.localConfigManager.getLightSensorOptionState() != 0 ? this.localConfigManager.getAmbientLightValue() : -1;
        Log.i("Fac_AlarmAndFanSpeedActivity", "getAmbientLightValue is : " + ambientLightValue);
        ShownItems.updateShownValue(ambientLightValue, ShownItems.types.LIGHT);
        int distanceSensorStrengthValue = this.localConfigManager.getDistanceSensorStrengthValue();
        Log.i("Fac_AlarmAndFanSpeedActivity", "distanceSensorStrengthValue is : " + distanceSensorStrengthValue);
        ShownItems.updateShownValue(distanceSensorStrengthValue, ShownItems.types.DISTANCESTRENGTH);
        int backgroundNoiseValue = this.localConfigManager.getBackgroundNoiseValue();
        Log.i("Fac_AlarmAndFanSpeedActivity", "backgroundNoiseValue is : " + backgroundNoiseValue);
        ShownItems.updateShownValue(backgroundNoiseValue, ShownItems.types.BACKGROUNDNOISE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateUI() {
        for (ShownItems shownItems : ShownItems.values()) {
            shownItems.getView().setText(shownItems.getShownValue() == 0 ? "0 or null" : String.valueOf(shownItems.getShownValue()));
            if (shownItems.getMyType().equals(ShownItems.types.SENSOR)) {
                shownItems.getView().setText(shownItems.getShowValueForSensor() == "" ? "null" : shownItems.getShowValueForSensor());
            }
            if (shownItems.getMyType().equals(ShownItems.types.LIGHT)) {
                shownItems.getView().setText(shownItems.getShownValue() != -1 ? String.valueOf(shownItems.getShownValue()) : "0 or null");
            }
            if (shownItems.getMyType().equals(ShownItems.types.DISTANCESTRENGTH)) {
                shownItems.getView().setText(shownItems.getShownValue() == -1 ? "null" : String.valueOf(shownItems.getShownValue()));
            }
            if (shownItems.getMyType().equals(ShownItems.types.BACKGROUNDNOISE)) {
                shownItems.getView().setText(shownItems.getShownValue() == -1 ? "null" : String.valueOf(shownItems.getShownValue()));
            }
            if (shownItems.getMyType().equals(ShownItems.types.RUNNINGTIME)) {
                shownItems.getView().setText(shownItems.getShowValueForRunningTime() != "" ? String.valueOf(shownItems.getShowValueForRunningTime()) : "null");
            }
        }
    }

    class UpdateControl {
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        UpdateControl() {
        }

        public void updateForAnHour() {
            final ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay = this.scheduler.scheduleWithFixedDelay(new Runnable() { // from class: com.hisense.uifac.AlarmAndFanSpeedActivity.UpdateControl.1
                @Override // java.lang.Runnable
                public void run() {
                    System.out.println("update");
                    AlarmAndFanSpeedActivity.this.updateValue();
                    AlarmAndFanSpeedActivity.mHandler.sendEmptyMessage(111);
                }
            }, 2L, 2L, TimeUnit.SECONDS);
            this.scheduler.schedule(new Runnable(this) { // from class: com.hisense.uifac.AlarmAndFanSpeedActivity.UpdateControl.2
                @Override // java.lang.Runnable
                public void run() {
                    scheduledFutureScheduleWithFixedDelay.cancel(true);
                }
            }, 3600L, TimeUnit.SECONDS);
        }

        public void shutdown() {
            this.scheduler.shutdown();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.i("Fac_AlarmAndFanSpeedActivity", "keyCode " + i + "is pressed");
        if (i == 4207) {
            finish();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
