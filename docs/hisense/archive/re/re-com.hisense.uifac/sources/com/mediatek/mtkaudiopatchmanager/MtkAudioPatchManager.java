package com.mediatek.mtkaudiopatchmanager;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.hdmi.IHdmiControlService;
import android.hardware.hdmi.IHdmiSystemAudioModeChangeListener;
import android.media.AudioDevicePort;
import android.media.AudioDevicePortConfig;
import android.media.AudioGain;
import android.media.AudioGainConfig;
import android.media.AudioManager;
import android.media.AudioPatch;
import android.media.AudioPortConfig;
import android.media.AudioSystem;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.util.Log;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MtkAudioPatchManager {
    private static final String TAG = "MtkAudioPatchManager";
    private final AudioManager mAudioManager;
    private final Context mContext;
    private DeviceHandler mHandler;
    private final HdmiSystemAudioModeChangeListener mHdmiAudioModeListener;
    private IHdmiControlService mHdmiControlService;
    private final HdmiObserver mHdmiObserver;
    private Method methodResetAudioPortGeneration;
    private AudioDevicePort mAudioSource = null;
    private AudioPatch mAudioPatch = null;
    List<AudioDevicePort> mAudioSinks = new ArrayList();
    private int mDesiredSamplingRate = 0;
    private int mDesiredChannelMask = 1;
    private int mDesiredFormat = 1;
    private boolean mBluetoothStatus = false;
    private boolean mHdmiStatus = false;
    private boolean mHpStatus = false;
    private boolean mHdmiPowerOnStatus = false;
    private boolean mHdmiPowerOffStatus = false;
    private int mSinkDevice = 0;
    private int mCurrentIndex = 0;
    private int mCurrentMaxIndex = 0;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.mediatek.mtkaudiopatchmanager.MtkAudioPatchManager.1
        /* JADX WARN: Code duplicated, block: B:70:0x0281  */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            byte b;
            int intExtra;
            Log.d(MtkAudioPatchManager.TAG, "onReceive action: " + intent.getAction());
            String action = intent.getAction();
            if (action.equals("android.media.STREAM_DEVICES_CHANGED_ACTION")) {
                int intExtra2 = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_DEVICES", -1);
                int intExtra3 = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_DEVICES", -1);
                int intExtra4 = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                Log.i(MtkAudioPatchManager.TAG, "onReceive device:" + intExtra2 + "  preDevice:" + intExtra3 + "  streamType:" + intExtra4);
                if (intExtra4 == 3) {
                    if (intExtra2 == 2 && AudioSystem.DEVICE_OUT_ALL_A2DP_SET.contains(Integer.valueOf(intExtra3))) {
                        Log.d(MtkAudioPatchManager.TAG, "BLUETOOTH_STATE_DISCONNECTED Do Disconnect");
                        MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 2), 10L);
                        return;
                    } else if (intExtra3 != 1073741824 && AudioSystem.DEVICE_OUT_ALL_A2DP_SET.contains(Integer.valueOf(intExtra2))) {
                        Log.d(MtkAudioPatchManager.TAG, "BLUETOOTH_STATE_DISCONNECTED Do Connecting");
                        MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 1), 50L);
                        return;
                    }
                }
            }
            if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                int intExtra5 = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", -1);
                if (intExtra5 == 0) {
                    Log.d(MtkAudioPatchManager.TAG, "BLUETOOTH_STATE_DISCONNECTED Do Nothing");
                } else if (intExtra5 == 1) {
                    Log.d(MtkAudioPatchManager.TAG, "BLUETOOTH_STATE_CONNECTING");
                } else if (intExtra5 == 2) {
                    Log.d(MtkAudioPatchManager.TAG, "BLUETOOTH_STATE_CONNECTED");
                    MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 1), 50L);
                } else if (intExtra5 != 3) {
                    Log.d(MtkAudioPatchManager.TAG, "unhandled BLUETOOTH state:" + intExtra5);
                } else {
                    Log.d(MtkAudioPatchManager.TAG, "BLUETOOTH_STATE_DISCONNECTING");
                }
            }
            if (action.equals("android.intent.action.HEADSET_PLUG")) {
                boolean z = intent.getIntExtra("state", 0) != 0;
                int intExtra6 = intent.getIntExtra("device", 0);
                String stringExtra = intent.getStringExtra("portName");
                String stringExtra2 = intent.getStringExtra("address");
                boolean z2 = intent.getIntExtra("microphone", 0) != 0;
                Log.d(MtkAudioPatchManager.TAG, "device:" + intExtra6 + " deviceName: " + stringExtra + " connetcted : " + z + " address:" + stringExtra2 + " hasMicrophone : " + z2);
                if (z2) {
                    if (z || !stringExtra.contains("USB-Audio")) {
                        Log.d(MtkAudioPatchManager.TAG, "hasMicrophone, unhandled HEADSET_PLUG ");
                    } else {
                        Log.d(MtkAudioPatchManager.TAG, "USB-Audio  disconnected");
                        MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 8, intExtra6, 0), 50L);
                    }
                } else if (z) {
                    if (stringExtra.contains("USB-Audio")) {
                        Log.d(MtkAudioPatchManager.TAG, "USB-Audio  connected");
                        MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 7, intExtra6, 0), 50L);
                    } else {
                        Log.d(MtkAudioPatchManager.TAG, "headphone connected");
                        MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 5), 50L);
                    }
                } else if (stringExtra.contains("USB-Audio")) {
                    Log.d(MtkAudioPatchManager.TAG, "USB-Audio  disconnected");
                    MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 8, intExtra6, 0), 50L);
                } else {
                    Log.d(MtkAudioPatchManager.TAG, "headphone disconnected");
                    MtkAudioPatchManager.this.mHandler.sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 6), 50L);
                }
            }
            if (action.equals("android.media.VOLUME_CHANGED_ACTION") || action.equals("android.media.STREAM_MUTE_CHANGED_ACTION")) {
                int iHashCode = action.hashCode();
                if (iHashCode != -1940635523) {
                    if (iHashCode == 1920758225 && action.equals("android.media.STREAM_MUTE_CHANGED_ACTION")) {
                        b = 1;
                    } else {
                        b = -1;
                    }
                } else if (action.equals("android.media.VOLUME_CHANGED_ACTION")) {
                    b = 0;
                } else {
                    b = -1;
                }
                if (b != 0) {
                    if (b != 1) {
                        Log.w(MtkAudioPatchManager.TAG, "Unrecognized intent: " + intent);
                        return;
                    }
                    if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) != 3) {
                        return;
                    }
                } else if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) != 3 || (intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0)) == MtkAudioPatchManager.this.mCurrentIndex) {
                    return;
                } else {
                    MtkAudioPatchManager.this.mCurrentIndex = intExtra;
                }
                MtkAudioPatchManager.this.updateVolume();
            }
        }
    };

    private static int toInt(boolean z) {
        return z ? 1 : 0;
    }

    private class DeviceHandler extends Handler {
        public DeviceHandler(Looper looper) {
            super(looper);
            Log.d(MtkAudioPatchManager.TAG, "start DeviceHandler looper");
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    boolean zUpdateBluetoothStatus = MtkAudioPatchManager.this.updateBluetoothStatus();
                    if (!MtkAudioPatchManager.this.mBluetoothStatus || !zUpdateBluetoothStatus) {
                        sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 1), 50L);
                        return;
                    } else {
                        MtkAudioPatchManager.this.updateAudioPatch(128);
                        return;
                    }
                case 2:
                    boolean zUpdateBluetoothStatus2 = MtkAudioPatchManager.this.updateBluetoothStatus();
                    if (MtkAudioPatchManager.this.mBluetoothStatus || !zUpdateBluetoothStatus2) {
                        sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 2), 50L);
                        return;
                    } else {
                        MtkAudioPatchManager.this.updateAudioPatch(1073741824);
                        return;
                    }
                case 3:
                    boolean zUpdateHdmiStatus = MtkAudioPatchManager.this.updateHdmiStatus();
                    if (!MtkAudioPatchManager.this.mHdmiStatus || !zUpdateHdmiStatus) {
                        sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 3), 50L);
                        return;
                    }
                    Log.d(MtkAudioPatchManager.TAG, "handle HDMI_POWER_ON");
                    MtkAudioPatchManager.this.updateAudioPatch(MtkTvScanDvbsBase.SB_DVBS_CONFIG_INSTALL_SCRAMBLE_SVC_ONLY);
                    MtkAudioPatchManager.this.mHdmiPowerOnStatus = false;
                    return;
                case 4:
                    if (!MtkAudioPatchManager.this.updateHdmiStatus() || MtkAudioPatchManager.this.mHdmiStatus) {
                        sendMessageDelayed(Message.obtain(MtkAudioPatchManager.this.mHandler, 4), 50L);
                        return;
                    }
                    Log.d(MtkAudioPatchManager.TAG, "handle HDMI_POWER_OFF");
                    MtkAudioPatchManager.this.updateAudioPatch(1073741824);
                    MtkAudioPatchManager.this.mHdmiPowerOffStatus = false;
                    return;
                case 5:
                    boolean zUpdateHpStatus = MtkAudioPatchManager.this.updateHpStatus();
                    if (!MtkAudioPatchManager.this.mHpStatus || !zUpdateHpStatus) {
                        Log.d(MtkAudioPatchManager.TAG, "unhandled updateHpStatus HEADPHONES_CONNECTED");
                        return;
                    } else {
                        MtkAudioPatchManager.this.updateAudioPatch(8);
                        return;
                    }
                case 6:
                    boolean zUpdateHpStatus2 = MtkAudioPatchManager.this.updateHpStatus();
                    if (MtkAudioPatchManager.this.mHpStatus || !zUpdateHpStatus2) {
                        Log.d(MtkAudioPatchManager.TAG, "unhandled updateHpStatus HEADPHONES_DISCONNECTED");
                        return;
                    } else {
                        MtkAudioPatchManager.this.updateAudioPatch(1073741824);
                        return;
                    }
                case 7:
                    if (MtkAudioPatchManager.this.updateUSBStatus()) {
                        MtkAudioPatchManager.this.updateAudioPatch(message.arg1 == 0 ? 67108864 : 16384);
                        return;
                    } else {
                        Log.e(MtkAudioPatchManager.TAG, "unhandled USB_DEVICE_CONNECTED");
                        return;
                    }
                case 8:
                    if (MtkAudioPatchManager.this.updateUSBStatus()) {
                        MtkAudioPatchManager.this.updateAudioPatch(1073741824);
                        return;
                    } else {
                        Log.e(MtkAudioPatchManager.TAG, "unhandled USB_DEVICE_DISCONNECTED");
                        return;
                    }
                default:
                    throw new IllegalStateException("unhandled message: " + message.what);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVolume() {
        AudioGain audioGain;
        int iMaxValue;
        this.mCurrentIndex = this.mAudioManager.getStreamVolume(3);
        int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
        this.mCurrentMaxIndex = streamMaxVolume;
        float f = 0.0f;
        if (streamMaxVolume > 0) {
            try {
                f = this.mCurrentIndex / streamMaxVolume;
            } catch (ArithmeticException unused) {
                Log.e(TAG, "updateVolume catch ArithmeticException");
            }
        }
        Log.i(TAG, "updateVolume mStreamVolume: " + f + " mCurrentIndex:" + this.mCurrentIndex + " mCurrentMaxIndex: " + this.mCurrentMaxIndex);
        AudioDevicePort audioDevicePort = this.mAudioSource;
        if (audioDevicePort != null) {
            AudioGainConfig audioGainConfigBuildConfig = null;
            if (audioDevicePort.gains().length > 0) {
                AudioGain[] audioGainArrGains = this.mAudioSource.gains();
                int length = audioGainArrGains.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        audioGain = null;
                        break;
                    }
                    audioGain = audioGainArrGains[i];
                    if ((audioGain.mode() & 1) != 0) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (audioGain != null) {
                    int iMaxValue2 = (audioGain.maxValue() - audioGain.minValue()) / audioGain.stepValue();
                    int iMinValue = audioGain.minValue();
                    if (f < 1.0f) {
                        iMaxValue = iMinValue + (audioGain.stepValue() * ((int) (((double) (f * iMaxValue2)) + 0.5d)));
                    } else {
                        iMaxValue = audioGain.maxValue();
                    }
                    Log.d(TAG, "gainValue: " + iMaxValue);
                    audioGainConfigBuildConfig = audioGain.buildConfig(1, audioGain.channelMask(), new int[]{iMaxValue}, 0);
                } else {
                    Log.w(TAG, "No audio source gain with MODE_JOINT support exists.");
                }
            }
            if (audioGainConfigBuildConfig != null) {
                AudioManager.setAudioPortGain(this.mAudioSource, audioGainConfigBuildConfig);
                return;
            } else {
                Log.i(TAG, "sourceGainConfig can not create!");
                return;
            }
        }
        Log.i(TAG, "unavailable AudioSource!");
    }

    public MtkAudioPatchManager(Context context) {
        this.mHandler = null;
        this.methodResetAudioPortGeneration = null;
        Log.d(TAG, "MtkAudioPatchManager++");
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mHandler = new DeviceHandler(context.getMainLooper());
        this.mHdmiObserver = new HdmiObserver(this.mHandler);
        this.mHdmiAudioModeListener = new HdmiSystemAudioModeChangeListener(this.mHandler);
        this.mHdmiControlService = IHdmiControlService.Stub.asInterface(ServiceManager.getService("hdmi_control"));
        try {
            Method declaredMethod = Class.forName("android.media.AudioManager").getDeclaredMethod("resetAudioPortGeneration", null);
            this.methodResetAudioPortGeneration = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (ClassNotFoundException unused) {
            Log.e(TAG, "android.media.AudioManager NOT Found");
        } catch (NoSuchMethodException unused2) {
            Log.e(TAG, "NO func resetAudioPortGeneration");
        }
        Log.d(TAG, "MtkAudioPatchManager--");
    }

    private static boolean intArrayContains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private void registerReceivers() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        intentFilter.addAction("android.media.STREAM_MUTE_CHANGED_ACTION");
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.STREAM_DEVICES_CHANGED_ACTION");
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
    }

    private void unRegisterReceivers() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    private void registerContentObserver() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        String[] strArr = {"hdmi_control_enabled", "hdmi_control_auto_wakeup_enabled", "hdmi_control_auto_device_off_enabled", "hdmi_system_audio_control_enabled", "mhl_input_switching_enabled", "mhl_power_charge_enabled"};
        for (int i = 0; i < 6; i++) {
            contentResolver.registerContentObserver(Settings.Global.getUriFor(strArr[i]), false, this.mHdmiObserver);
        }
    }

    private void unRegisterContentObserver() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mHdmiObserver);
    }

    private void registerHdmiAudioModeListener() {
        IHdmiControlService iHdmiControlService = this.mHdmiControlService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.addSystemAudioModeChangeListener(this.mHdmiAudioModeListener);
                return;
            } catch (RemoteException e) {
                Log.w(TAG, "Error registering listeners to HdmiControlService:", e);
                return;
            }
        }
        Log.w(TAG, "HdmiControlService is not available");
    }

    private void unRegisterHdmiAudioModeListener() {
        IHdmiControlService iHdmiControlService = this.mHdmiControlService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.removeSystemAudioModeChangeListener(this.mHdmiAudioModeListener);
                return;
            } catch (RemoteException e) {
                Log.w(TAG, "Error unregistering listeners to HdmiControlService:", e);
                return;
            }
        }
        Log.w(TAG, "HdmiControlService is not available");
    }

    public boolean createAudioPatch() {
        int i;
        Log.d(TAG, "createAudioPatch");
        registerHdmiAudioModeListener();
        registerReceivers();
        registerContentObserver();
        updateBluetoothStatus();
        updateHdmiStatus();
        if (this.mHdmiStatus) {
            i = MtkTvScanDvbsBase.SB_DVBS_CONFIG_INSTALL_SCRAMBLE_SVC_ONLY;
        } else {
            i = this.mBluetoothStatus ? 128 : 1073741824;
        }
        return createAudioPatchLocked(i);
    }

    public boolean releaseAudioPatch() {
        Log.d(TAG, "releaseAudioPatch");
        return releaseAudioPatchLocked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateBluetoothStatus() {
        findAudioSinkFromAudioPolicy(this.mAudioSinks);
        Log.d(TAG, "updateBluetoothStatus mAudioSink size = " + this.mAudioSinks.size());
        if (this.mAudioSinks.size() < 1) {
            return false;
        }
        for (AudioDevicePort audioDevicePort : this.mAudioSinks) {
            if (audioDevicePort.role() == 2 && (audioDevicePort instanceof AudioDevicePort) && audioDevicePort.type() == 128) {
                Log.d(TAG, "find AudioDevicePort A2DP: " + audioDevicePort);
                this.mBluetoothStatus = true;
                return true;
            }
        }
        Log.d(TAG, "AudioDevicePort A2DP isn't exist!");
        this.mBluetoothStatus = false;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateHdmiStatus() {
        findAudioSinkFromAudioPolicy(this.mAudioSinks);
        Log.d(TAG, "updateHdmiStatus mAudioSink size = " + this.mAudioSinks.size());
        if (this.mAudioSinks.size() < 1) {
            return false;
        }
        for (AudioDevicePort audioDevicePort : this.mAudioSinks) {
            if (audioDevicePort.role() == 2 && (audioDevicePort instanceof AudioDevicePort) && audioDevicePort.type() == 262144) {
                Log.d(TAG, "find AudioDevicePort hdmi: " + audioDevicePort);
                this.mHdmiStatus = true;
                return true;
            }
        }
        Log.d(TAG, "AudioDevicePort hdmi isn't exist!");
        this.mHdmiStatus = false;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateHpStatus() {
        findAudioSinkFromAudioPolicy(this.mAudioSinks);
        Log.d(TAG, "updateHpStatus mAudioSink size = " + this.mAudioSinks.size());
        if (this.mAudioSinks.size() < 1) {
            return false;
        }
        for (AudioDevicePort audioDevicePort : this.mAudioSinks) {
            if (audioDevicePort.role() == 2 && (audioDevicePort instanceof AudioDevicePort) && audioDevicePort.type() == 8) {
                Log.d(TAG, "find AudioDevicePort Hp: " + audioDevicePort);
                this.mHpStatus = true;
                return true;
            }
        }
        Log.d(TAG, "AudioDevicePort Hp isn't exist!");
        this.mHpStatus = false;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateUSBStatus() {
        findAudioSinkFromAudioPolicy(this.mAudioSinks);
        Log.d(TAG, "updateUSBStatus mAudioSink size = " + this.mAudioSinks.size());
        return this.mAudioSinks.size() >= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAudioPatch(int i) {
        Log.d(TAG, "updateAudioPatch++");
        createAudioPatchLocked(i);
        Log.d(TAG, "updateAudioPatch--");
    }

    private boolean createAudioPatchLocked(int i) {
        AudioGainConfig audioGainConfigBuildConfig;
        AudioDevicePortConfig audioDevicePortConfigBuildConfig;
        AudioGain audioGain;
        int iMaxValue;
        ArrayList<AudioDevicePort> arrayList = new ArrayList();
        try {
            this.methodResetAudioPortGeneration.invoke(null, new Object[0]);
        } catch (IllegalAccessException unused) {
            Log.e(TAG, "IllegalAccessException on methodResetAudioPortGeneration");
        } catch (InvocationTargetException unused2) {
            Log.e(TAG, "InvocationTargetExcept on methodResetAudioPortGeneration");
        }
        if (AudioManager.listAudioDevicePorts(arrayList) != 0) {
            Log.d(TAG, "createAudioPatchLocked fail! ");
            return false;
        }
        Log.d(TAG, "list devicePorts size = " + arrayList.size());
        for (AudioDevicePort audioDevicePort : arrayList) {
            Log.i(TAG, "devicePorts: " + audioDevicePort.toString());
            if (audioDevicePort.role() == 1 && audioDevicePort.type() == -2147221504) {
                this.mAudioSource = audioDevicePort;
                Log.d(TAG, "find devicePort DEVICE_IN_LOOPBACK: " + audioDevicePort);
                break;
            }
        }
        if (this.mAudioSource == null) {
            Log.e(TAG, "Cannot find devicePort, failed to create patch");
            return false;
        }
        int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        float f = streamMaxVolume > 0 ? streamVolume / streamMaxVolume : 0.0f;
        try {
            Log.i(TAG, "mStreamVolume: " + f + " mCurrentIndex:" + streamVolume + " mCurrentMaxIndex: " + streamMaxVolume);
        } catch (ArithmeticException unused3) {
            Log.e(TAG, "createAudioPatchLocked catch ArithmeticException");
        }
        if (this.mAudioSource.gains().length > 0) {
            AudioGain[] audioGainArrGains = this.mAudioSource.gains();
            int length = audioGainArrGains.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    audioGain = null;
                    break;
                }
                audioGain = audioGainArrGains[i2];
                if ((audioGain.mode() & 1) != 0) {
                    break;
                }
                i2++;
            }
            if (audioGain != null) {
                int iMaxValue2 = (audioGain.maxValue() - audioGain.minValue()) / audioGain.stepValue();
                int iMinValue = audioGain.minValue();
                if (f < 1.0f) {
                    iMaxValue = iMinValue + (audioGain.stepValue() * ((int) (((double) (f * iMaxValue2)) + 0.5d)));
                } else {
                    iMaxValue = audioGain.maxValue();
                }
                Log.d(TAG, "gainValue: " + iMaxValue);
                audioGainConfigBuildConfig = audioGain.buildConfig(1, audioGain.channelMask(), new int[]{iMaxValue}, 0);
            } else {
                Log.w(TAG, "No audio source gain with MODE_JOINT support exists.");
                audioGainConfigBuildConfig = null;
            }
        } else {
            audioGainConfigBuildConfig = null;
        }
        ArrayList<AudioPortConfig> arrayList2 = new ArrayList();
        for (AudioDevicePort audioDevicePort2 : this.mAudioSinks) {
            AudioDevicePortConfig audioDevicePortConfigActiveConfig = audioDevicePort2.activeConfig();
            int iSamplingRate = this.mDesiredSamplingRate;
            int iChannelMask = this.mDesiredChannelMask;
            int i3 = this.mDesiredFormat;
            if (audioDevicePortConfigActiveConfig != null) {
                if (iSamplingRate == 0) {
                    iSamplingRate = audioDevicePortConfigActiveConfig.samplingRate();
                }
                if (iChannelMask == 1) {
                    iChannelMask = audioDevicePortConfigActiveConfig.channelMask();
                }
                if (i3 == 1) {
                    iChannelMask = audioDevicePortConfigActiveConfig.format();
                }
            }
            if (audioDevicePortConfigActiveConfig == null || audioDevicePortConfigActiveConfig.samplingRate() != iSamplingRate || audioDevicePortConfigActiveConfig.channelMask() != iChannelMask || audioDevicePortConfigActiveConfig.format() != i3) {
                if (!intArrayContains(audioDevicePort2.samplingRates(), iSamplingRate) && audioDevicePort2.samplingRates().length > 0) {
                    iSamplingRate = audioDevicePort2.samplingRates()[0];
                }
                if (!intArrayContains(audioDevicePort2.channelMasks(), iChannelMask)) {
                    iChannelMask = 1;
                }
                if (!intArrayContains(audioDevicePort2.formats(), i3)) {
                    i3 = 1;
                }
                audioDevicePortConfigActiveConfig = audioDevicePort2.buildConfig(iSamplingRate, iChannelMask, i3, (AudioGainConfig) null);
            }
            if (i != 1073741824) {
                if (audioDevicePort2.type() == i) {
                    arrayList2.add(audioDevicePortConfigActiveConfig);
                }
            } else {
                Log.d(TAG, "createAudioPatchLocked add device " + audioDevicePort2.type());
                arrayList2.add(audioDevicePortConfigActiveConfig);
            }
        }
        AudioDevicePort audioDevicePort3 = this.mAudioSource;
        if (audioDevicePort3 != null) {
            AudioPortConfig audioPortConfigActiveConfig = audioDevicePort3.activeConfig();
            int iSamplingRate2 = this.mDesiredSamplingRate;
            int iChannelMask2 = this.mDesiredChannelMask;
            int i4 = this.mDesiredFormat;
            if (audioPortConfigActiveConfig != null) {
                if (iSamplingRate2 == 0) {
                    iSamplingRate2 = audioPortConfigActiveConfig.samplingRate();
                }
                if (iChannelMask2 == 1) {
                    iChannelMask2 = audioPortConfigActiveConfig.channelMask();
                }
                if (i4 == 1) {
                    i4 = audioPortConfigActiveConfig.format();
                }
            }
            audioDevicePortConfigBuildConfig = this.mAudioSource.buildConfig(iSamplingRate2, iChannelMask2, i4, audioGainConfigBuildConfig);
        } else {
            audioDevicePortConfigBuildConfig = null;
        }
        if (audioDevicePortConfigBuildConfig != null && arrayList2.size() > 0) {
            if (this.mAudioPatch != null) {
                Log.d(TAG, "release last patch mAudioPatch +++");
                AudioManager.releaseAudioPatch(this.mAudioPatch);
                this.mAudioPatch = null;
                Log.d(TAG, "release last patch mAudioPatch ---");
            } else {
                Log.d(TAG, "mAudioPatch is null");
            }
            AudioPatch[] audioPatchArr = {this.mAudioPatch};
            try {
                this.methodResetAudioPortGeneration.invoke(null, new Object[0]);
            } catch (IllegalAccessException unused4) {
                Log.e(TAG, "IllegalAccessException on methodResetAudioPortGeneration");
            } catch (InvocationTargetException unused5) {
                Log.e(TAG, "InvocationTargetExcept on methodResetAudioPortGeneration");
            }
            ArrayList<AudioPatch> arrayList3 = new ArrayList();
            AudioManager.listAudioPatches(arrayList3);
            Log.d(TAG, "clear all audio patchs size = " + arrayList3.size());
            for (AudioPatch audioPatch : arrayList3) {
                Log.i(TAG, "AudioPatch: " + audioPatch.toString());
                AudioManager.releaseAudioPatch(audioPatch);
            }
            Log.i(TAG, "srcPortConfig: " + audioDevicePortConfigBuildConfig.toString());
            for (AudioPortConfig audioPortConfig : arrayList2) {
                Log.i(TAG, "sinkPortConfig: " + audioPortConfig.toString());
            }
            if (this.mAudioPatch != null) {
                Log.d(TAG, "before update mAudioPatch: " + this.mAudioPatch.toString());
            }
            int iCreateAudioPatch = AudioManager.createAudioPatch(audioPatchArr, new AudioPortConfig[]{audioDevicePortConfigBuildConfig}, (AudioPortConfig[]) arrayList2.toArray(new AudioPortConfig[arrayList2.size()]));
            AudioPatch audioPatch2 = audioPatchArr[0];
            this.mAudioPatch = audioPatch2;
            if (audioPatch2 != null) {
                Log.d(TAG, "after update mAudioPatch: " + this.mAudioPatch.toString());
            }
            if (audioGainConfigBuildConfig != null) {
                AudioManager.setAudioPortGain(this.mAudioSource, audioGainConfigBuildConfig);
            }
            return iCreateAudioPatch == 0;
        }
        Log.e(TAG, "failed to create patch");
        return false;
    }

    private boolean releaseAudioPatchLocked() {
        Log.d(TAG, "releaseAudioPatchLocked");
        this.mHandler.removeMessages(1);
        unRegisterReceivers();
        unRegisterContentObserver();
        unRegisterHdmiAudioModeListener();
        AudioPatch audioPatch = this.mAudioPatch;
        if (audioPatch != null) {
            Log.i(TAG, audioPatch.toString());
            AudioManager.releaseAudioPatch(this.mAudioPatch);
            this.mAudioPatch = null;
            Log.d(TAG, "releaseAudioPatchLocked done!");
        }
        return true;
    }

    private void findAudioSinkFromAudioPolicy(List<AudioDevicePort> list) {
        list.clear();
        try {
            this.methodResetAudioPortGeneration.invoke(null, new Object[0]);
        } catch (IllegalAccessException unused) {
            Log.e(TAG, "IllegalAccessException on methodResetAudioPortGeneration");
        } catch (InvocationTargetException unused2) {
            Log.e(TAG, "InvocationTargetExcept on methodResetAudioPortGeneration");
        }
        this.mSinkDevice = this.mAudioManager.getDevicesForStream(3);
        Log.d(TAG, "findAudioSinkFromAudioPolicy sinkDevice = " + Integer.toHexString(this.mSinkDevice));
        ArrayList<AudioDevicePort> arrayList = new ArrayList();
        if (AudioManager.listAudioDevicePorts(arrayList) != 0) {
            Log.d(TAG, "fail to listAudioDevicePorts!");
            return;
        }
        for (AudioDevicePort audioDevicePort : arrayList) {
            if ((audioDevicePort.type() & this.mSinkDevice) != 0 && (audioDevicePort.type() & Integer.MIN_VALUE) == 0) {
                list.add(audioDevicePort);
            }
        }
    }

    private class HdmiObserver extends ContentObserver {
        public HdmiObserver(Handler handler) {
            super(handler);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:20:0x0060  */
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            byte b;
            String lastPathSegment = uri.getLastPathSegment();
            boolean booleanSetting = MtkAudioPatchManager.this.readBooleanSetting(lastPathSegment, true);
            Log.d(MtkAudioPatchManager.TAG, "HdmiObserver:onChange enabled:" + booleanSetting);
            switch (lastPathSegment) {
                case "hdmi_control_enabled":
                    b = 0;
                    break;
                case "mhl_input_switching_enabled":
                    b = 3;
                    break;
                case "mhl_power_charge_enabled":
                    b = 4;
                    break;
                case "hdmi_control_auto_wakeup_enabled":
                    b = 1;
                    break;
                case "hdmi_control_auto_device_off_enabled":
                    b = 2;
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                Log.d(MtkAudioPatchManager.TAG, "HDMI_CONTROL_ENABLED");
                return;
            }
            if (b == 1) {
                Log.d(MtkAudioPatchManager.TAG, "HDMI_CONTROL_AUTO_WAKEUP_ENABLED");
                return;
            }
            if (b == 2) {
                Log.d(MtkAudioPatchManager.TAG, "HDMI_CONTROL_AUTO_DEVICE_OFF_ENABLED");
                return;
            }
            if (b == 3) {
                Log.d(MtkAudioPatchManager.TAG, "MHL_INPUT_SWITCHING_ENABLED");
                return;
            }
            if (b != 4) {
                Log.d(MtkAudioPatchManager.TAG, "unhandled option:" + lastPathSegment);
                return;
            }
            Log.d(MtkAudioPatchManager.TAG, "MHL_POWER_CHARGE_ENABLED");
        }
    }

    boolean readBooleanSetting(String str, boolean z) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        toInt(z);
        return Settings.Global.getInt(contentResolver, str, z ? 1 : 0) == 1;
    }

    private final class HdmiSystemAudioModeChangeListener extends IHdmiSystemAudioModeChangeListener.Stub {
        private Handler mHdmiListenerHandler;

        public HdmiSystemAudioModeChangeListener(Handler handler) {
            this.mHdmiListenerHandler = null;
            this.mHdmiListenerHandler = handler;
        }

        public void onStatusChanged(boolean z) {
            Log.d(MtkAudioPatchManager.TAG, "HdmiSystemAudioModeChanged = " + z + ", mHdmiPowerOnStatus = " + MtkAudioPatchManager.this.mHdmiPowerOnStatus + ", mHdmiPowerOffStatus = " + MtkAudioPatchManager.this.mHdmiPowerOffStatus);
            if (!z || MtkAudioPatchManager.this.mHdmiPowerOnStatus) {
                if (z || MtkAudioPatchManager.this.mHdmiPowerOffStatus) {
                    return;
                }
                this.mHdmiListenerHandler.removeMessages(4);
                MtkAudioPatchManager.this.mHdmiPowerOffStatus = true;
                Handler handler = this.mHdmiListenerHandler;
                handler.sendMessageDelayed(Message.obtain(handler, 4), 200L);
                return;
            }
            this.mHdmiListenerHandler.removeMessages(3);
            MtkAudioPatchManager.this.mHdmiPowerOnStatus = true;
            Handler handler2 = this.mHdmiListenerHandler;
            handler2.sendMessageDelayed(Message.obtain(handler2, 3), 200L);
        }
    }
}
