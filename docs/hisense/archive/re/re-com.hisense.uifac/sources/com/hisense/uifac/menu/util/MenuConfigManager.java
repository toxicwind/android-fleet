package com.hisense.uifac.menu.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.hisense.tvcm.ApVsTILmap;
import com.hisense.tvcm.FacParamManager;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uiadapter.TVComponenetListener;
import com.hisense.uiadapter.TVComponent;
import com.hisense.uiadapter.TVFunction;
import com.hisense.uiadapter.TVOption;
import com.hisense.uifac.ExternalStorageHelper;
import com.hisense.uifac.FacRootMenu;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.MenuMain;
import com.hisense.uifac.mic.HisenseFacMicTest;
import com.hisense.uifac.util.FacFileUtils;
import com.hisense.uifac.util.IMtkFunCallback;
import com.hisense.uifac.util.IRootMenuUiListener;
import com.hisense.uifac.util.USBUtils;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class MenuConfigManager {
    private static String CHINADRMKEY_ROOTDIR = "DEFAULT_CHINADRMKEY/";
    private static String CIPLUS_ROOTDIR = "CIPLUS5660";
    private static String DTCPIP_ROOTDIR = "DTCPIPCOMMON";
    private static String HDCP14_ROOTDIR = "HDCP14KEY";
    private static String HDCP2X_ROOTDIR = "HDCP22KEY";
    private static String MARLIN_ROOTDIR = "MARLINCOMMON";
    private static String MGK_ROOTDIR = "MGKKEY";
    private static String PVR_ROOTDIR = "PVR000COMMON";
    private static String SQV_ROOTDIR = "SQV000COMMON";
    private static String WIDEVINE_ROOTDIR = "DEFAULT_WIDEVINEKEY/";
    private static MenuConfigManager mConfigManager;
    public static String mSetDeviceIDStatus;
    static TVFunction mTVFunction;
    Context mContext;
    public DemuraPattern mDemuraPattern_Mode;
    private TVOption<Integer> mHisOption;
    public LocalDimmingStatus mLocalDimmingStatus;
    public PictureCurveMode mPictureCurve_Mode;
    TVComponent mTVComponent;
    public TestPattern mTestPattern_Mode;
    public TestPattern_hardware mTestPattern_Mode_hardware;
    Toast toastView;
    int minValue = 0;
    int maxValue = 255;
    int defaultValue = 0;
    private IRootMenuUiListener mRootMenuListener = null;
    private Object mObject = new Object();
    private ArrayList<TVComponenetListener> mListener = new ArrayList<>();
    private Object mMicTightnessLock = new Object();
    private double mMicOneTightnessResult = 0.0d;
    private double mMicTwoTightnessResult = 0.0d;
    private String dolbyIQUserBinSizeInfo = null;
    private String dolbyIQUserBinTimeInfo = null;
    private int mVcomType = 0;

    public int[] getAlarms() {
        return null;
    }

    public boolean isSNSupport(String str) {
        return true;
    }

    public boolean isWIFISupported() {
        return true;
    }

    public enum PictureCurveMode {
        BRIGHTNESS,
        CONTRAST,
        COLOR,
        SHARPNESS;

        public static PictureCurveMode getName(int i) {
            if (i == 1) {
                return CONTRAST;
            }
            if (i == 2) {
                return COLOR;
            }
            if (i == 3) {
                return SHARPNESS;
            }
            return BRIGHTNESS;
        }
    }

    public enum TestPattern_hardware {
        OFF,
        RED,
        GREEN,
        BLUE,
        WHITE,
        BLACK,
        CYAN,
        PURPLE,
        YELLOW;

        public static TestPattern_hardware getName(int i) {
            TestPattern_hardware testPattern_hardware = OFF;
            switch (i) {
                case 0:
                    return testPattern_hardware;
                case 1:
                    return RED;
                case 2:
                    return GREEN;
                case 3:
                    return BLUE;
                case 4:
                    return WHITE;
                case 5:
                    return BLACK;
                case 6:
                    return CYAN;
                case 7:
                    return PURPLE;
                case 8:
                    return YELLOW;
                default:
                    return testPattern_hardware;
            }
        }
    }

    public enum TestPattern {
        OFF,
        RED,
        GREEN,
        BLUE,
        WHITE,
        BLACK,
        CIRCLE;

        public static TestPattern getName(int i) {
            TestPattern testPattern = OFF;
            switch (i) {
                case 0:
                    return testPattern;
                case 1:
                    return RED;
                case 2:
                    return GREEN;
                case 3:
                    return BLUE;
                case 4:
                    return WHITE;
                case 5:
                    return BLACK;
                case 6:
                    return CIRCLE;
                default:
                    return testPattern;
            }
        }
    }

    public enum DemuraPattern {
        OFF,
        DOT,
        GRAY0,
        GRAY76,
        GRAY128,
        GRAY240,
        GRAY255;

        public static DemuraPattern getName(int i) {
            switch (i) {
                case 1:
                    return DOT;
                case 2:
                    return GRAY0;
                case 3:
                    return GRAY76;
                case 4:
                    return GRAY128;
                case 5:
                    return GRAY240;
                case 6:
                    return GRAY255;
                default:
                    return OFF;
            }
        }
    }

    public static MenuConfigManager getInstance(Context context) {
        if (mConfigManager == null) {
            mConfigManager = new MenuConfigManager(context);
        }
        return mConfigManager;
    }

    public static boolean isAudioMute() {
        return TVFunction.isMute();
    }

    public static void setAudioMute(boolean z) {
        TVFunction.setMute(z);
    }

    public void setTestPatternHardwareModeOff() {
        this.mTestPattern_Mode_hardware = TestPattern_hardware.OFF;
    }

    public void setTestPatternModeOff() {
        this.mTestPattern_Mode = TestPattern.OFF;
    }

    public void setDemuraPatternModeOff() {
        this.mDemuraPattern_Mode = DemuraPattern.OFF;
    }

    private MenuConfigManager(Context context) {
        PictureCurveMode pictureCurveMode = PictureCurveMode.BRIGHTNESS;
        this.mTestPattern_Mode_hardware = TestPattern_hardware.OFF;
        this.mTestPattern_Mode = TestPattern.OFF;
        this.mDemuraPattern_Mode = DemuraPattern.OFF;
        this.mLocalDimmingStatus = LocalDimmingStatus.Off;
        this.mContext = context;
        this.mTVComponent = TVComponent.getInstance(context);
        mTVFunction = TVFunction.getInstance(context);
        this.toastView = new Toast(this.mContext);
    }

    public void adcCalibration() {
        if (!mTVFunction.autoADCCalibration()) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_factorymenu_adcng));
            Log.i("Fac_MenuConfigManager", "adc calibration fail!!");
        } else {
            displayHisToast(this.mContext.getResources().getString(R.string.str_factorymenu_adcok));
            Log.i("Fac_MenuConfigManager", "adc calibration success!!");
        }
    }

    public void adjustTestPattern(boolean z) {
        int iOrdinal;
        int iOrdinal2 = this.mTestPattern_Mode.ordinal();
        if (z) {
            iOrdinal = iOrdinal2 == TestPattern.CIRCLE.ordinal() ? TestPattern.OFF.ordinal() : iOrdinal2 + 1;
        } else {
            iOrdinal = iOrdinal2 == TestPattern.OFF.ordinal() ? TestPattern.CIRCLE.ordinal() : iOrdinal2 - 1;
        }
        this.mTestPattern_Mode = TestPattern.getName(iOrdinal);
    }

    public void adjustTestPattern_hardware(boolean z) {
        int iOrdinal;
        int iOrdinal2 = this.mTestPattern_Mode_hardware.ordinal();
        if (z) {
            iOrdinal = iOrdinal2 == TestPattern_hardware.YELLOW.ordinal() ? TestPattern_hardware.OFF.ordinal() : iOrdinal2 + 1;
        } else {
            iOrdinal = iOrdinal2 == TestPattern_hardware.OFF.ordinal() ? TestPattern_hardware.YELLOW.ordinal() : iOrdinal2 - 1;
        }
        this.mTestPattern_Mode_hardware = TestPattern_hardware.getName(iOrdinal);
    }

    public void adjustDemuraPattern(boolean z) {
        int iOrdinal;
        int iOrdinal2 = this.mDemuraPattern_Mode.ordinal();
        if (z) {
            iOrdinal = iOrdinal2 == DemuraPattern.GRAY255.ordinal() ? DemuraPattern.OFF.ordinal() : iOrdinal2 + 1;
        } else {
            iOrdinal = iOrdinal2 == DemuraPattern.OFF.ordinal() ? DemuraPattern.GRAY255.ordinal() : iOrdinal2 - 1;
        }
        this.mDemuraPattern_Mode = DemuraPattern.getName(iOrdinal);
    }

    public void setWBPattern(int i) {
        mTVFunction.setWBPattern(i);
    }

    public boolean isInWBPatternTest() {
        String str = SystemProperties.get("sys.vt.factory.wb_pattern_test", "false");
        Log.i("wb_pattern_test property", SystemProperties.get("sys.vt.factory.wb_pattern_test", "false"));
        return str.equals("true");
    }

    public boolean setOpenMirror(boolean z) {
        return mTVFunction.setOpenMirror(z);
    }

    public void setFilterChangeTime(Handler handler) {
        if (mTVFunction.setFilterChangeTime()) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_factorymenu_filterchangetimeok));
            handler.sendEmptyMessage(115);
        } else {
            displayHisToast(this.mContext.getResources().getString(R.string.str_factorymenu_filterchangetimeng));
            handler.sendEmptyMessage(115);
        }
    }

    public boolean setMirrorValue(int i) {
        return mTVFunction.setMirrorValue(i);
    }

    public void displayHisToast(String str) {
        Log.i("Fac_MenuConfigManager", "displayHisToast str = " + str);
        TextView textView = new TextView(this.mContext);
        textView.setBackgroundColor(-7829368);
        textView.setTextColor(-16776961);
        textView.setPadding(10, 10, 10, 10);
        textView.setText(str);
        textView.setTextSize(32.0f);
        this.toastView.setView(textView);
        this.toastView.setDuration(1);
        if (this.mContext.getResources().getString(R.string.remote_pair_success).equalsIgnoreCase(str)) {
            this.toastView.setGravity(80, 0, 0);
        } else {
            this.toastView.setGravity(17, 0, 0);
        }
        this.toastView.show();
    }

    public String FindFileOnUSB(String str) throws Throwable {
        Log.i("Fac_MenuConfigManager", "FindFileOnUSB filename = " + str);
        if (new File("/mnt/media_rw/" + str).exists()) {
            Log.i("Fac_MenuConfigManager", "FindFileOnUSB TF/SD Card found!!!");
            return "/mnt/media_rw/" + str;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file = new File(it.next() + "/" + str);
                if (file.exists()) {
                    return file.getAbsolutePath();
                }
            }
        }
        Log.i("Fac_MenuConfigManager", "FindFileOnUSB filepath = " + ((String) null));
        return null;
    }

    public String findChinaDrmKeyFilePathOnUSB() throws Throwable {
        CHINADRMKEY_ROOTDIR = FacPropertyDemo.HIFAC_CHINA_DRM_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + CHINADRMKEY_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            return "/mnt/media_rw//" + CHINADRMKEY_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "chinadrm list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "chindrm file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + CHINADRMKEY_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("chinadrm file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "For china drm key the path is : " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find chindrm file" + file2);
            }
            return null;
        }
        Log.i("Fac_MenuConfigManager", "chindrm file list is null or is empty");
        return null;
    }

    public String findWideVineFilePathOnUSB() throws Throwable {
        if (!"2g".equalsIgnoreCase(FacPropertyDemo.HIFAC_DDR)) {
            WIDEVINE_ROOTDIR = FacPropertyDemo.HIFAC_WIDE_VINE_KEY_FILENAME + "/";
        } else if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            WIDEVINE_ROOTDIR = FacPropertyDemo.HIFAC_WIDE_VINE_2GMICKEY_FILENAME + "/";
        } else {
            WIDEVINE_ROOTDIR = FacPropertyDemo.HIFAC_WIDE_VINE_2GKEY_FILENAME + "/";
        }
        File file = new File("/mnt/media_rw//" + WIDEVINE_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            return "/mnt/media_rw//" + WIDEVINE_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "widevine list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "widevine file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + WIDEVINE_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("widevine file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find widevine file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find widevine file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "widevine file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " widevine filepath=" + ((String) null));
        return null;
    }

    public String findHdcp14PathOnUSB() throws Throwable {
        HDCP14_ROOTDIR = FacPropertyDemo.HIFAC_HDCP14_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + HDCP14_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            return "/mnt/media_rw//" + HDCP14_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "hdcp14 list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "hdcp14 file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + HDCP14_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("hdcp14 file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find hdcp14 file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find hdcp14 file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "hdcp14 file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " hdcp14 filepath=" + ((String) null));
        return null;
    }

    public String findHdcp2xPathOnUSB() throws Throwable {
        HDCP2X_ROOTDIR = FacPropertyDemo.HIFAC_HDCP2X_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + HDCP2X_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            return "/mnt/media_rw//" + HDCP2X_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "hdcp2x list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "hdcp2x file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + HDCP2X_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("hdcp2x file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find hdcp2x file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find hdcp2x file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "hdcp2x file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " hdcp2x filepath=" + ((String) null));
        return null;
    }

    public String findCIPlusPathOnUSB() throws Throwable {
        CIPLUS_ROOTDIR = FacPropertyDemo.HIFAC_CIPLUS_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + CIPLUS_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            return "/mnt/media_rw//" + CIPLUS_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "ciplus file list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "ciplus file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + CIPLUS_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("ciplus file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find ciplus file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find ciplus file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "ciplus file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " ciplus filepath=" + ((String) null));
        return null;
    }

    public ArrayList<Boolean> get3DConfig() {
        Boolean bool = Boolean.FALSE;
        ArrayList<Boolean> arrayList = new ArrayList<>();
        arrayList.add(bool);
        arrayList.add(bool);
        arrayList.add(bool);
        return arrayList;
    }

    public String getDateInfo(boolean z) {
        Date date = new Date(Build.TIME);
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss ", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(timeZone);
        String str = simpleDateFormat.format(date);
        if (!z) {
            Log.i("Fac_MenuConfigManager", "getDateInfo, str: " + str);
            return str;
        }
        Calendar calendar = Calendar.getInstance(timeZone);
        Date date2 = new Date(getDataTime());
        calendar.setTime(date2);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss ", Locale.ENGLISH);
        simpleDateFormat2.setTimeZone(timeZone);
        String str2 = simpleDateFormat2.format(date2);
        Log.i("Fac_MenuConfigManager", "data time is " + str2);
        return getCurrentVersionYear(str2.substring(0, 4)) + str2.substring(5, 7) + str2.substring(8, 10);
    }

    public String updateStrVersion(String str) {
        if (str == null || str.length() <= 1) {
            return "";
        }
        String strSubstring = str.substring(0, str.length() - 1);
        Log.i("Fac_MenuConfigManager", "updateStrVersion str = " + strSubstring);
        StringBuffer stringBuffer = new StringBuffer(strSubstring);
        stringBuffer.append(".");
        Log.i("Fac_MenuConfigManager", "updateStrVersion newStr = " + ((Object) stringBuffer));
        return stringBuffer.toString();
    }

    public int getDefault(String str) {
        if (str.equals("HIFAC_ADC_AUTOCOLOR") || str.equals("HIFAC_SETLOCAL_KOR") || str.equals("HIFAC_SETLANGUAGE_FOR_KOR")) {
            return 0;
        }
        TVOption option = this.mTVComponent.getOption(str);
        this.mHisOption = option;
        if (option != null) {
            int iIntValue = ((Integer) option.get()).intValue();
            Log.i("Fac_MenuConfigManager", "getDefault String:" + str + "=" + iIntValue);
            return iIntValue;
        }
        return this.defaultValue;
    }

    public String getChipID() {
        return mTVFunction.getChipID();
    }

    public String getDefaultMacAddress() {
        return mTVFunction.getDefaultMacAddr();
    }

    public String getDeviceID() {
        return mTVFunction.getDeviceID();
    }

    public String getChinaDrmKey() {
        return mTVFunction.getChinaDrmKey();
    }

    public String getHdcpKey() {
        return mTVFunction.getHdcpKey();
    }

    public String getHdcp14Key() {
        return mTVFunction.getHdcp14Key();
    }

    public String getHdcp2xKey() {
        return mTVFunction.getHdcp2xKey();
    }

    public String getCIPlusKey() {
        return mTVFunction.getCIPlusKey();
    }

    public String getPlayReadyKey() {
        return mTVFunction.getPlayReadyKey();
    }

    public String getPlayReady25Key() {
        return mTVFunction.getPlayready25Key();
    }

    public String getPlayReady40Key() {
        return mTVFunction.getPlayready40Key();
    }

    public String getNetflixKey() {
        return mTVFunction.getMGKKey();
    }

    public String getQDAKey() {
        return mTVFunction.getQDAKey();
    }

    public int checkTconTempVr() {
        return mTVFunction.checkTconTempVr();
    }

    public String getTconTempVersion() {
        return mTVFunction.getTconTempVersion();
    }

    public String getAttestationKey() {
        return mTVFunction.getAttestationKey();
    }

    public String getDtcpIpKey() {
        return mTVFunction.getDtcpIpKey();
    }

    public String getMarlinKey() {
        return mTVFunction.getMarlinKey();
    }

    public String getSeeQVaultKey() {
        return mTVFunction.getSeeQVaultKey();
    }

    public String getPVRKey() {
        return mTVFunction.getPVRKey();
    }

    public boolean isKeyWriteProtecEnabled() {
        return mTVFunction.isKeyWriteProtecEnabled();
    }

    public String getNewFangChuanHuoString() {
        String newMachineIDentity = mTVFunction.getNewMachineIDentity();
        return (newMachineIDentity == null || newMachineIDentity.isEmpty() || newMachineIDentity.contains("0000000000")) ? "NULL" : newMachineIDentity;
    }

    public String getOrignalFangChuanHuoString() {
        return mTVFunction.getOrignalMachineIDentity();
    }

    public String getIPAddr() {
        return mTVFunction.getIPAddr();
    }

    public String getMacAddress() {
        return mTVFunction.getMacAddress();
    }

    public String getCountryName() {
        return mTVFunction.getCountryName();
    }

    public String getLogoName() {
        return mTVFunction.getLogoName();
    }

    public String getlanguageName() {
        return mTVFunction.getlanguageName();
    }

    public String getBTMacAddress() {
        return mTVFunction.getBTMacAddress();
    }

    public int getMax(String str) {
        if (str.equals("HIFAC_ADC_AUTOCOLOR")) {
            return 0;
        }
        TVOption option = this.mTVComponent.getOption(str);
        this.mHisOption = option;
        if (option != null) {
            int iIntValue = ((Integer) option.getMax()).intValue();
            Log.i("Fac_MenuConfigManager", "getMax String:" + str + "Max=" + iIntValue);
            return iIntValue;
        }
        return this.maxValue;
    }

    public int getMin(String str) {
        if (str.equals("HIFAC_ADC_AUTOCOLOR")) {
            return 0;
        }
        TVOption option = this.mTVComponent.getOption(str);
        this.mHisOption = option;
        if (option != null) {
            int iIntValue = ((Integer) option.getMin()).intValue();
            Log.i("Fac_MenuConfigManager", "getMin String:" + str + "Min=" + iIntValue);
            return iIntValue;
        }
        return this.minValue;
    }

    public int getStep(String str) {
        TVOption option = this.mTVComponent.getOption(str);
        this.mHisOption = option;
        if (option == null) {
            return 1;
        }
        int iIntValue = ((Integer) option.getStep()).intValue();
        Log.i("Fac_MenuConfigManager", "getStep String:" + str + "step =" + iIntValue);
        return iIntValue;
    }

    public String getVersionInfo() {
        String strSubstring;
        String str;
        String strSubstring2;
        String strSubstring3;
        String strSubstring4;
        StringBuffer stringBuffer = new StringBuffer();
        String productTypeName = mTVFunction.getProductTypeName();
        String str2 = productTypeName + "_" + mTVFunction.getVersionInfomation() + ".";
        Log.i("Fac_MenuConfigManager", "old type = " + str2);
        int length = str2.length();
        String strSubstring5 = str2.substring(str2.indexOf("_") + 1, str2.indexOf("."));
        String str3 = "00";
        if (FacPropertyDemo.HIFAC_LASER_PROJECTOR) {
            strSubstring2 = str2.substring(0, str2.indexOf("_"));
            strSubstring = "";
            str = "00";
        } else {
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = 0;
                    break;
                }
                if (Character.isDigit(str2.charAt(i))) {
                    break;
                }
                i++;
            }
            String strSubstring6 = str2.substring(0, i);
            if (strSubstring6.toUpperCase().matches("LED")) {
                strSubstring6 = MtkTvRatingConvert2Goo.STR_CA_TV_E;
            } else if (strSubstring6.toUpperCase().matches("LHD")) {
                strSubstring6 = "H";
            } else if (strSubstring6.toUpperCase().matches("LT")) {
                strSubstring6 = "T";
            } else if (strSubstring6.toUpperCase().matches("PX")) {
                strSubstring6 = "X";
            } else if (strSubstring6.toUpperCase().matches("LTDN")) {
                strSubstring6 = "TN";
            }
            int i2 = i;
            while (true) {
                if (i2 >= length) {
                    i2 = 0;
                    break;
                }
                if (!Character.isDigit(str2.charAt(i2))) {
                    break;
                }
                i2++;
            }
            strSubstring = str2.substring(i, i2);
            String strSubstring7 = str2.substring(i2, str2.indexOf("_"));
            if ("X".equalsIgnoreCase(strSubstring6)) {
                strSubstring7 = "00";
            }
            Log.i("Fac_MenuConfigManager", "preStr = " + strSubstring6);
            Log.i("Fac_MenuConfigManager", "forkTypeStr = " + strSubstring5);
            Log.i("Fac_MenuConfigManager", "functionStr = " + strSubstring7);
            Log.i("Fac_MenuConfigManager", "dimensionStr = " + strSubstring);
            str = null;
            String str4 = null;
            int i3 = 0;
            while (true) {
                if (i3 < strSubstring7.length()) {
                    if (!strSubstring7.contains("(")) {
                        if (Character.isDigit(strSubstring7.charAt(i3)) || i3 == 0 || !Character.isDigit(strSubstring7.charAt(i3 - 1))) {
                            i3++;
                            str = strSubstring7;
                            str4 = "00";
                        } else if (i3 < strSubstring7.length()) {
                            strSubstring3 = strSubstring7.substring(0, i3);
                            strSubstring4 = strSubstring7.substring(i3, strSubstring7.length());
                        }
                    } else {
                        strSubstring3 = strSubstring7.substring(0, strSubstring7.indexOf("("));
                        strSubstring4 = strSubstring7.substring(strSubstring7.indexOf("("), strSubstring7.length());
                    }
                    str = strSubstring3;
                    str3 = strSubstring4;
                    strSubstring2 = strSubstring6;
                }
                strSubstring2 = strSubstring6;
                str3 = str4;
            }
        }
        Log.i("Fac_MenuConfigManager", "forkTypeStr is " + strSubstring5 + ";functionTwoStr " + str3 + ";functionOneStr" + str);
        String strSubstring8 = str2.substring(str2.indexOf("."));
        StringBuilder sb = new StringBuilder();
        sb.append("versionStr is ");
        sb.append(strSubstring8);
        Log.i("Fac_MenuConfigManager", sb.toString());
        if ("X".equalsIgnoreCase(strSubstring2)) {
            stringBuffer.append(strSubstring2);
            stringBuffer.append(".");
            stringBuffer.append(strSubstring5);
            stringBuffer.append(".");
            stringBuffer.append(strSubstring);
            stringBuffer.append(".");
            stringBuffer.append(str);
            stringBuffer.append(".");
            stringBuffer.append(str3);
            stringBuffer.append(strSubstring8);
        } else {
            stringBuffer.append(strSubstring2);
            stringBuffer.append(strSubstring);
            stringBuffer.append(".");
            stringBuffer.append(strSubstring5);
            stringBuffer.append(".");
            stringBuffer.append(str);
            stringBuffer.append(".");
            stringBuffer.append(str3);
            stringBuffer.append(strSubstring8);
        }
        Log.i("Fac_MenuConfigManager", "productname = " + productTypeName + ";   newTypeSBuffer=" + ((Object) stringBuffer));
        return stringBuffer.toString();
    }

    public void hideHisToast() {
        this.toastView.cancel();
    }

    public boolean is3DSupported() {
        return mTVFunction.is3DSupported();
    }

    public void setMacAddrFromOption(final Context context) {
        if (context == null) {
            return;
        }
        String macAddress = mTVFunction.getMacAddress();
        if (macAddress == null || macAddress.equals("NG") || "00:00:00:00:00:00".equals(macAddress)) {
            mConfigManager.isSetMacAddr(context);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show mac addr warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_macc_key_ready_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.isSetMacAddr(context);
            }
        });
        builder.create().show();
    }

    public void isSetMacAddr(Context context) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.2
            @Override // java.lang.Runnable
            public void run() {
                MenuConfigManager.this.setMacContent(null);
            }
        }).start();
    }

    public void doUsbUpgrade(Context context) {
        mTVFunction.doUsbUpgrade(context);
    }

    public boolean isSSCSupported() {
        return TVComponent.isSSCSupport();
    }

    public boolean isSSCVByOneEnable() {
        return TVComponent.isSSCVByOneEnable();
    }

    public boolean isSSCDdrEnable() {
        return TVComponent.isSSCDdrEnable();
    }

    public boolean isTestPattern() {
        return !this.mTestPattern_Mode.equals(TestPattern.OFF);
    }

    public boolean isTestPattern_hardware() {
        return !this.mTestPattern_Mode_hardware.equals(TestPattern_hardware.OFF);
    }

    public boolean isDemuraPattern() {
        return !this.mDemuraPattern_Mode.equals(DemuraPattern.OFF);
    }

    public void presetChannel(String str) {
        if (mTVFunction.isCurrentSourceDTV()) {
            mTVFunction.presetDTVChannel(str);
        } else {
            mTVFunction.presetATVChannel(str);
        }
    }

    public void presetChannel(String str, Handler handler) {
        if (mTVFunction.isCurrentSourceDTV()) {
            mTVFunction.presetDTVChannel(str, handler);
        } else {
            mTVFunction.presetATVChannel(str, handler);
        }
    }

    public void resetFactoryOK(IMtkFunCallback iMtkFunCallback) {
        mTVFunction.factoryOk(iMtkFunCallback);
    }

    public void resetProtectly() {
        mTVFunction.clearProtectlyInFactory();
    }

    public void resetUnprotectly() {
        mTVFunction.clearUnprotectlyInFactory();
    }

    public void resetDataInit(Handler handler) {
        mTVFunction.dataInitInFactory(handler);
    }

    public void setNewFangChuanHuoValue(String str) {
        mTVFunction.setNewMachineIDentity(str);
    }

    public boolean setMacAddr(String str) {
        return mTVFunction.setMacAddress(str);
    }

    public boolean setCountryIndex(int i) {
        return mTVFunction.setCountryIndex(i);
    }

    public boolean setLogoIndex(int i) {
        return mTVFunction.setLogoIndex(i);
    }

    public boolean setLangIndex(int i) {
        return mTVFunction.setLangIndex(i);
    }

    public boolean setDTSSwitch(int i) {
        return mTVFunction.setDTSSwitch(i);
    }

    public void setMacAddrFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.3
            @Override // java.lang.Runnable
            public void run() {
                MenuConfigManager.this.setMacContent(writeCallback);
            }
        }).start();
    }

    public void setDeviceIDFromUDisk() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.4
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String strFindWideVineFilePathOnUSB = MenuConfigManager.this.findWideVineFilePathOnUSB();
                if (strFindWideVineFilePathOnUSB == null || strFindWideVineFilePathOnUSB.length() <= 0) {
                    MenuConfigManager menuConfigManager = MenuConfigManager.this;
                    menuConfigManager.displayHisToast(menuConfigManager.mContext.getResources().getString(R.string.str_wide_vine_key_no));
                    return;
                }
                Log.i("Fac_MenuConfigManager", " setDeviceIDFromUDisk device ID file path=" + strFindWideVineFilePathOnUSB);
                File file = new File(strFindWideVineFilePathOnUSB);
                if (file.isDirectory() && file.exists()) {
                    final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX);
                    File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.4.1
                        @Override // java.io.FileFilter
                        public boolean accept(File file2) {
                            if (file2.isFile()) {
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolKeyNameValid(false, name);
                                }
                                if (!TextUtils.isEmpty(name)) {
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                    if (fileArrListFiles != null) {
                        if (fileArrListFiles.length > 0) {
                            String string = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  device ID file name is     " + string);
                            if (MenuConfigManager.mTVFunction.setWideVineKey(string)) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getDeviceID())) {
                                    Log.i("Fac_MenuConfigManager", "GET WIDEVINE KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    MenuConfigManager menuConfigManager2 = MenuConfigManager.this;
                                    menuConfigManager2.displayHisToast(menuConfigManager2.mContext.getResources().getString(R.string.str_wide_vine_key_fail));
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string);
                                    Log.i("Fac_MenuConfigManager", "delete widewine key, path is " + string + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        MenuConfigManager menuConfigManager3 = MenuConfigManager.this;
                                        menuConfigManager3.displayHisToast(menuConfigManager3.mContext.getResources().getString(R.string.str_wide_vine_key_fail));
                                    }
                                }
                                MenuConfigManager menuConfigManager4 = MenuConfigManager.this;
                                menuConfigManager4.displayHisToast(menuConfigManager4.mContext.getResources().getString(R.string.str_wide_vine_key_yes));
                                return;
                            }
                            MenuConfigManager menuConfigManager5 = MenuConfigManager.this;
                            menuConfigManager5.displayHisToast(menuConfigManager5.mContext.getResources().getString(R.string.str_wide_vine_key_fail));
                            return;
                        }
                        MenuConfigManager menuConfigManager6 = MenuConfigManager.this;
                        menuConfigManager6.displayHisToast(menuConfigManager6.mContext.getResources().getString(R.string.str_wide_vine_key_no));
                        return;
                    }
                    return;
                }
                MenuConfigManager menuConfigManager7 = MenuConfigManager.this;
                menuConfigManager7.displayHisToast(menuConfigManager7.mContext.getResources().getString(R.string.str_wide_vine_key_no));
            }
        }).start();
    }

    public void setProductionKey() {
        if (new File("/product/ctskey/attestkey").exists()) {
            mTVFunction.setProductionKey("/product/ctskey/attestkey");
        }
    }

    public boolean getProducitonKey() {
        return mTVFunction.getProductionKey();
    }

    public void setDeviceIDFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.5
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindWideVineFilePathOnUSB = MenuConfigManager.this.findWideVineFilePathOnUSB();
                if (strFindWideVineFilePathOnUSB == null || strFindWideVineFilePathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_wide_vine_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setDeviceIDFromUDiskWithCB device ID file path=" + strFindWideVineFilePathOnUSB);
                    File file = new File(strFindWideVineFilePathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.5.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (file2.isFile()) {
                                    String name = file2.getName();
                                    if (z) {
                                        return MenuConfigManager.this.isAntiFoolKeyNameValid(false, name);
                                    }
                                    if (!TextUtils.isEmpty(name) && name.endsWith("bin")) {
                                        return true;
                                    }
                                }
                                return false;
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  device ID file name is     " + string2);
                            if (MenuConfigManager.mTVFunction.setWideVineKey(string2)) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getDeviceID())) {
                                    Log.i("Fac_MenuConfigManager", "GET WIDEWINE KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_wide_vine_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete widevine key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_wide_vine_key_fail);
                                    } else {
                                        Log.i("Fac_MenuConfigManager", "set widevien key is success!!");
                                        writeCallback.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_wide_vine_key_yes));
                                        return;
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_wide_vine_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_wide_vine_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_wide_vine_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                writeCallback.writeEnd(false, string);
            }
        }).start();
    }

    public void setPlayReadyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.6
            @Override // java.lang.Runnable
            public void run() {
                String wideKey = MenuConfigManager.this.getWideKey();
                if (wideKey == null || wideKey.isEmpty() || "NG".equals(wideKey)) {
                    FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                    if (writeCallback2 == null) {
                        MenuConfigManager menuConfigManager = MenuConfigManager.this;
                        menuConfigManager.displayHisToast(menuConfigManager.mContext.getResources().getString(R.string.str_playready_key_fail));
                    } else {
                        writeCallback2.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_fail));
                    }
                    Log.i("Fac_MenuConfigManager", " setPlayReadyFromUDiskWithCB no wideVineKey");
                    return;
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
                    Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV file path=/vendor/tvconfig/config/playready30");
                    if (!MenuConfigManager.mTVFunction.setPlayready30Key("/vendor/tvconfig/config/playready30")) {
                        writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_fail));
                        return;
                    } else {
                        String string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_yes);
                        if (!FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                            writeCallback.writeEnd(true, string);
                        }
                    }
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
                    MenuConfigManager.this.sleep(100L);
                    Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV 40 file path=/perm/orig_pr");
                    if (!MenuConfigManager.mTVFunction.setPlayready40Key("/perm/orig_pr")) {
                        writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_fail));
                        return;
                    } else {
                        String string2 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_yes);
                        if (!FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                            writeCallback.writeEnd(true, string2);
                        }
                    }
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                    MenuConfigManager.this.sleep(100L);
                    Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV file path=/vendor/tvconfig/config/playready25");
                    if (MenuConfigManager.mTVFunction.setPlayready25Key("/vendor/tvconfig/config/playready25")) {
                        writeCallback.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_yes));
                    } else {
                        writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready25_key_fail));
                    }
                }
            }
        }).start();
    }

    public void setChinaDrmKeyFromUDisk() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.7
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String strFindChinaDrmKeyFilePathOnUSB = MenuConfigManager.this.findChinaDrmKeyFilePathOnUSB();
                if (strFindChinaDrmKeyFilePathOnUSB == null || strFindChinaDrmKeyFilePathOnUSB.length() <= 0) {
                    MenuConfigManager menuConfigManager = MenuConfigManager.this;
                    menuConfigManager.displayHisToast(menuConfigManager.mContext.getResources().getString(R.string.str_china_drm_key_no));
                    return;
                }
                String str = strFindChinaDrmKeyFilePathOnUSB + "chinadrm_key";
                String str2 = strFindChinaDrmKeyFilePathOnUSB + "chinadrm_bak";
                Log.i("Fac_MenuConfigManager", " setChinaDrmKeyFromUDisk chinaDrmKey file path=" + str);
                File file = new File(str);
                if (file.isDirectory() && file.exists()) {
                    final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_CHINA_DRM_KEY_FILENAME_PREFIX);
                    File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.7.1
                        @Override // java.io.FileFilter
                        public boolean accept(File file2) {
                            if (!file2.isFile()) {
                                return false;
                            }
                            String name = file2.getName();
                            if (z) {
                                return MenuConfigManager.this.isAntiFoolKeyNameValid(true, name);
                            }
                            return !TextUtils.isEmpty(name) && name.endsWith("bin");
                        }
                    });
                    if (fileArrListFiles.length > 0) {
                        String string = fileArrListFiles[0].toString();
                        Log.i("Fac_MenuConfigManager", "the  chinaDrmKey file name is     " + string);
                        if (MenuConfigManager.mTVFunction.setChinaDrmKey(string)) {
                            MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                            File file2 = new File(string);
                            File file3 = new File(str2);
                            if (!file3.exists()) {
                                file3.mkdirs();
                            }
                            Log.i("Fac_MenuConfigManager", "setChinaDrmKeyFromUDisk  newFilePath:" + file3 + "/" + file2.getName());
                            StringBuilder sb = new StringBuilder();
                            sb.append(file3);
                            sb.append("/");
                            sb.append(file2.getName());
                            file2.renameTo(new File(sb.toString()));
                            MenuConfigManager menuConfigManager2 = MenuConfigManager.this;
                            menuConfigManager2.displayHisToast(menuConfigManager2.mContext.getResources().getString(R.string.str_china_drm_key_yes));
                            return;
                        }
                        MenuConfigManager menuConfigManager3 = MenuConfigManager.this;
                        menuConfigManager3.displayHisToast(menuConfigManager3.mContext.getResources().getString(R.string.str_china_drm_key_fail));
                        return;
                    }
                    MenuConfigManager menuConfigManager4 = MenuConfigManager.this;
                    menuConfigManager4.displayHisToast(menuConfigManager4.mContext.getResources().getString(R.string.str_china_drm_key_no));
                    return;
                }
                MenuConfigManager menuConfigManager5 = MenuConfigManager.this;
                menuConfigManager5.displayHisToast(menuConfigManager5.mContext.getResources().getString(R.string.str_china_drm_key_no));
            }
        }).start();
    }

    public void setChinaDrmKeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.8
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindChinaDrmKeyFilePathOnUSB = MenuConfigManager.this.findChinaDrmKeyFilePathOnUSB();
                if (strFindChinaDrmKeyFilePathOnUSB == null || strFindChinaDrmKeyFilePathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_china_drm_key_no);
                } else {
                    String str = strFindChinaDrmKeyFilePathOnUSB + "chinadrm_key";
                    String str2 = strFindChinaDrmKeyFilePathOnUSB + "chinadrm_bak";
                    Log.i("Fac_MenuConfigManager", " setChinaDrmKeyFromUDisk chinaDrmKey file path=" + str);
                    File file = new File(str);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_CHINA_DRM_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.8.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolKeyNameValid(true, name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("bin");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  chinaDrmKey file name is     " + string2);
                            if (MenuConfigManager.mTVFunction.setChinaDrmKey(string2)) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                File file2 = new File(string2);
                                File file3 = new File(str2);
                                if (!file3.exists()) {
                                    file3.mkdirs();
                                }
                                Log.i("Fac_MenuConfigManager", "setChinaDrmKeyFromUDisk  newFilePath:" + file3 + "/" + file2.getName());
                                StringBuilder sb = new StringBuilder();
                                sb.append(file3);
                                sb.append("/");
                                sb.append(file2.getName());
                                file2.renameTo(new File(sb.toString()));
                                writeCallback.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_china_drm_key_yes));
                                return;
                            }
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_china_drm_key_fail);
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_china_drm_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_china_drm_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                writeCallback.writeEnd(false, string);
            }
        }).start();
    }

    public void setHDCPKeyFromUDiskWithCB(FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        if (!new File("/tvconfig/config/certificate/hs-hdcp22-key.bin").exists()) {
            writeCallback.writeEnd(false, this.mContext.getResources().getString(R.string.str_hdcp_key_no));
        } else if (mTVFunction.setHdcpKey("/tvconfig/config/certificate/hs-hdcp22-key.bin")) {
            writeCallback.writeEnd(true, this.mContext.getResources().getString(R.string.str_hdcp_key_yes));
        } else {
            writeCallback.writeEnd(false, this.mContext.getResources().getString(R.string.str_hdcp_key_fail));
        }
    }

    public void setHDCPKey() {
        if (!new File("/tvconfig/config/certificate/hs-hdcp22-key.bin").exists()) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_hdcp_key_no));
        } else if (mTVFunction.setHdcpKey("/tvconfig/config/certificate/hs-hdcp22-key.bin")) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_hdcp_key_yes));
        } else {
            displayHisToast(this.mContext.getResources().getString(R.string.str_hdcp_key_fail));
        }
    }

    public void isSetPlayReadyKey(Context context) {
        String playready40Key;
        if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
            playready40Key = mTVFunction.getPlayready25Key();
        } else if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
            playready40Key = mTVFunction.getPlayReadyKey();
        } else {
            playready40Key = FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT ? mTVFunction.getPlayready40Key() : null;
        }
        if (playready40Key.equals("NG")) {
            mConfigManager.setPlayReadyFromTV();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show playReady warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_play_ready_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setPlayReadyFromTV();
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        alertDialogCreate.getButton(-2).setFocusable(true);
        alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
        alertDialogCreate.getButton(-2).requestFocus();
    }

    public void isSetWindevineKey(Context context) {
        if (mTVFunction.getDeviceID().equals("NG")) {
            mConfigManager.setDeviceIDFromUDisk();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show Windevine warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_device_id_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setDeviceIDFromUDisk();
            }
        });
        builder.create().show();
    }

    public void isSetHdcp14Key(Context context) {
        if (mTVFunction.getHdcp14Key().equals("NG")) {
            mConfigManager.setHDCP14KeyFromUDisk();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show hdcp1.4 warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_hdcp14_key_ready_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setHDCP14KeyFromUDisk();
            }
        });
        builder.create().show();
    }

    public void isSetHdcp2xKey(Context context) {
        if (mTVFunction.getHdcp2xKey().equals("NG")) {
            mConfigManager.setHDCP2xKeyFromUDisk();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show hdcp2.2 warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_hdcp22_key_ready_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setHDCP2xKeyFromUDisk();
            }
        });
        builder.create().show();
    }

    public void setHDCP14KeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.13
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindHdcp14PathOnUSB = MenuConfigManager.this.findHdcp14PathOnUSB();
                if (strFindHdcp14PathOnUSB == null || strFindHdcp14PathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setHDCP14KeyFromUDiskWithCB file path=" + strFindHdcp14PathOnUSB);
                    File file = new File(strFindHdcp14PathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_HDCP14_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.13.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolHDCPKeyNameValid(true, name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("bin");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  hdcp14 file name is     " + string2);
                            boolean hdcp14Key = MenuConfigManager.mTVFunction.setHdcp14Key(string2);
                            Log.i("Fac_MenuConfigManager", "the  setHdcp14Key returns      " + hdcp14Key);
                            if (hdcp14Key) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getHdcp14Key())) {
                                    Log.i("Fac_MenuConfigManager", "GET HDCP14 KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete hdcp1.4 key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_fail);
                                    } else {
                                        writeCallback.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_yes));
                                        return;
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                writeCallback.writeEnd(false, string);
            }
        }).start();
    }

    public void setHDCP14KeyFromUDisk() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.14
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindHdcp14PathOnUSB = MenuConfigManager.this.findHdcp14PathOnUSB();
                if (strFindHdcp14PathOnUSB == null || strFindHdcp14PathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setHDCP14KeyFromUDisk file path=" + strFindHdcp14PathOnUSB);
                    File file = new File(strFindHdcp14PathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_HDCP14_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.14.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolHDCPKeyNameValid(true, name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("bin");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  hdcp14 file name is     " + string2);
                            boolean hdcp14Key = MenuConfigManager.mTVFunction.setHdcp14Key(string2);
                            Log.i("Fac_MenuConfigManager", "the  setHdcp14Key returns      " + hdcp14Key);
                            if (hdcp14Key) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getHdcp14Key())) {
                                    Log.i("Fac_MenuConfigManager", "GET HDCP14 KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete hdcp1.4 key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_fail);
                                    } else {
                                        MenuConfigManager.this.displayHisToast(MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_yes));
                                        return;
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp14_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                MenuConfigManager.this.displayHisToast(string);
            }
        }).start();
    }

    public void setHDCP2xKeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.15
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindHdcp2xPathOnUSB = MenuConfigManager.this.findHdcp2xPathOnUSB();
                if (strFindHdcp2xPathOnUSB == null || strFindHdcp2xPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setHDCP2xKeyFromUDiskWithCB file path=" + strFindHdcp2xPathOnUSB);
                    File file = new File(strFindHdcp2xPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_HDCP2X_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.15.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (file2.isFile()) {
                                    String name = file2.getName();
                                    if (z) {
                                        return MenuConfigManager.this.isAntiFoolHDCPKeyNameValid(false, name);
                                    }
                                    if (!TextUtils.isEmpty(name) && name.endsWith("enc")) {
                                        return true;
                                    }
                                }
                                return false;
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  hdcp2x file name is     " + string2);
                            boolean hdcp2xKey = MenuConfigManager.mTVFunction.setHdcp2xKey(string2);
                            Log.i("Fac_MenuConfigManager", "setHdcp2xKey returns      " + hdcp2xKey);
                            if (hdcp2xKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getHdcp2xKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET HDCP2X KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_fail);
                                } else {
                                    new File(string2);
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete hdcp2.2 key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_fail);
                                    } else {
                                        writeCallback.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_yes));
                                        return;
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                writeCallback.writeEnd(false, string);
            }
        }).start();
    }

    public void setCIPlusKeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        if (writeCallback == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.16
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindCIPlusPathOnUSB = MenuConfigManager.this.findCIPlusPathOnUSB();
                if (strFindCIPlusPathOnUSB == null || strFindCIPlusPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_ci_plus_key_not_exit);
                } else {
                    Log.i("Fac_MenuConfigManager", " setCIPlusKeyFromUDiskWithCB file path=" + strFindCIPlusPathOnUSB);
                    File file = new File(strFindCIPlusPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_CI_PLUS_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.16.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolCIPlusKeyNameValid(name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("key");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the ci plus file name is     " + string2);
                            boolean cIPlusKey = MenuConfigManager.mTVFunction.setCIPlusKey(string2);
                            Log.i("Fac_MenuConfigManager", "setCIPlusKey returns      " + cIPlusKey);
                            if (cIPlusKey) {
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getCIPlusKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET CI PLUS KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_ciplus_key_fail);
                                } else {
                                    boolean zDelete = new File(string2).delete();
                                    Log.i("Fac_MenuConfigManager", "delete ciplus key, path is " + strFindCIPlusPathOnUSB + " result is " + zDelete);
                                    if (!zDelete) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_ciplus_key_fail);
                                    } else {
                                        writeCallback.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_ciplus_key_yes));
                                        return;
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_ciplus_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_ciplus_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_ciplus_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                writeCallback.writeEnd(false, string);
            }
        }).start();
    }

    public void setHDCP2xKeyFromUDisk() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.17
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindHdcp2xPathOnUSB = MenuConfigManager.this.findHdcp2xPathOnUSB();
                if (strFindHdcp2xPathOnUSB == null || strFindHdcp2xPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setHDCP2xKeyFromUDiskWithCB file path=" + strFindHdcp2xPathOnUSB);
                    File file = new File(strFindHdcp2xPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_HDCP2X_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.17.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (file2.isFile()) {
                                    String name = file2.getName();
                                    if (z) {
                                        return MenuConfigManager.this.isAntiFoolHDCPKeyNameValid(false, name);
                                    }
                                    if (!TextUtils.isEmpty(name) && name.endsWith("enc")) {
                                        return true;
                                    }
                                }
                                return false;
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  hdcp2x file name is     " + string2);
                            boolean hdcp2xKey = MenuConfigManager.mTVFunction.setHdcp2xKey(string2);
                            Log.i("Fac_MenuConfigManager", "setHdcp2xKey returns      " + hdcp2xKey);
                            if (hdcp2xKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getHdcp2xKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET HDCP2X KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_fail);
                                } else {
                                    new File(string2);
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", " setHDCP2xKeyFromUDisk delete hdcp2.2 key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_fail);
                                    } else {
                                        MenuConfigManager.this.displayHisToast(MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_yes));
                                        return;
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_hdcp2x_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                MenuConfigManager.this.displayHisToast(string);
            }
        }).start();
    }

    public void setValue(String str, int i) {
        Log.i("Fac_MenuConfigManager", "enter setValue ***************** itemID is " + str + "value is" + i);
        if (str.equals("HIFAC_ADC_AUTOCOLOR")) {
            if (mTVFunction.isCurrentSourceComponent() || mTVFunction.isCurrentSourceVGA()) {
                adcCalibration();
                return;
            }
            return;
        }
        if (str.equals("HIFAC_SETLOCAL_KOR") || str.equals("HIFAC_SETLANGUAGE_FOR_KOR")) {
            return;
        }
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            if (str.equals("FACTORY_OPTION_VCOM_VOLTAGE")) {
                setVcomType(1);
            }
            if (str.equals("FACTORY_OPTION_VCOM2_VOLTAGE")) {
                setVcomType(2);
            }
        } else if (str.equals("FACTORY_OPTION_VCOM_VOLTAGE")) {
            setVcomType(0);
        }
        TVOption option = this.mTVComponent.getOption(str);
        this.mHisOption = option;
        if (option != null) {
            Log.i("Fac_MenuConfigManager", "set String:" + str + "=" + i);
            if (!str.equals("HIFAC_RDRV") && !str.equals("HIFAC_GDRV") && !str.equals("HIFAC_BDRV") && !str.equals("HIFAC_RCUT") && !str.equals("HIFAC_GCUT")) {
                str.equals("HIFAC_BCUT");
            }
            this.mHisOption.set(Integer.valueOf(i));
            if (str.equals("HIFAC_OSDLANGUAGE")) {
                MenuMain.setInterruptExit(true);
            }
            if (str.equals("HIFAC_PEQ_1_STEP") || str.equals("HIFAC_PEQ_2_STEP") || str.equals("HIFAC_PEQ_3_STEP") || str.equals("HIFAC_PEQ_4_STEP") || str.equals("HIFAC_PEQ_5_STEP") || str.equals("HIFAC_PEQ_6_STEP") || str.equals("HIFAC_PEQ_7_STEP") || str.equals("HIFAC_PEQ_8_STEP")) {
                notifyListener(str);
                return;
            }
            return;
        }
        this.defaultValue = i;
    }

    public int[] getFansSpeed() {
        return mTVFunction.getFansSpeed();
    }

    public int[] getBoardTemperature() {
        return mTVFunction.getBoardTemperature();
    }

    public int[] getLightSensorValues() {
        return mTVFunction.getLightSensorValues();
    }

    public int getAmbientLightValue() {
        return mTVFunction.getAmbientLightValue();
    }

    public int getLightSensorOptionState() {
        return mTVFunction.getLightSensorOptionState();
    }

    public boolean setVcomVoltage(int i) {
        return mTVFunction.setVcomVoltage(i);
    }

    public boolean saveVcomVoltage(int i) {
        return mTVFunction.saveVcomVoltage(i);
    }

    public int getVcomVoltage() {
        return mTVFunction.getVcomVoltage();
    }

    public void upgradeFRC() {
        mTVFunction.upgradeFRC();
    }

    public String getFRCVersion() {
        return mTVFunction.getFRCVersion();
    }

    public String getTconVersion() {
        return mTVFunction.getTconVersion();
    }

    public String getTypeCVersion() {
        return mTVFunction.getTypeCVersion();
    }

    public String getWideKey() {
        return mTVFunction.getWideKey();
    }

    public void upgradeBacklightDriver() {
        mTVFunction.upgradeBacklightDriver();
    }

    public String getBacklightDriverVersion() {
        return mTVFunction.getBacklightDriverVersion();
    }

    public String getLCMRunningStatus() {
        String lCMRunningStatusValue = getLCMRunningStatusValue();
        return (lCMRunningStatusValue == null || lCMRunningStatusValue.isEmpty() || "ff".equals(lCMRunningStatusValue)) ? "NG" : "OK";
    }

    public String getLCMRunningStatusValue() {
        return mTVFunction.getLCMRunningStatus();
    }

    public void switchSource(FacPropertyDemo.SourceEnum sourceEnum) {
        mTVFunction.switchSource(sourceEnum);
    }

    public void pauseTv(boolean z) {
        mTVFunction.pauseTv(z);
    }

    public boolean setFirstTimePowerOn(int i) {
        return mTVFunction.setFirstTimePowerOn(i);
    }

    public FacPropertyDemo.SourceEnum getCurrentInputSourceEnum() {
        return mTVFunction.getCurrentInputSourceEnum();
    }

    public int getMicDevice() {
        return mTVFunction.getMicDevice();
    }

    public void setMicDevice(int i) {
        mTVFunction.setMicDevice(i);
    }

    public void setUartDebugEnable() {
        mTVFunction.setUartDebugEnable();
    }

    public long getDataTime() {
        return mTVFunction.getDataTime();
    }

    public String getDataDateInfo() {
        return new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss ", Locale.ENGLISH).format(new Date(SystemProperties.getLong("ro.data.build.date.utc", 0L) * 1000));
    }

    public String getDataVersion() {
        return mTVFunction.getDataVersion();
    }

    public boolean isShutdownBroadcastFromFactory() {
        return mTVFunction.isShutdownBroadcastFromFactory();
    }

    public String getProductTypeName() {
        return mTVFunction.getProductTypeName();
    }

    public String getTvScreenVersion() {
        return mTVFunction.getTvScreenVersion();
    }

    public String getTvChipVersion() {
        return mTVFunction.getTvChipVersion();
    }

    public boolean setVcomCardParam(String str, int i) {
        return mTVFunction.setVcomCardParam(str, i);
    }

    public int closeVcomCardParam() {
        return mTVFunction.closeVcomCardParam();
    }

    public boolean setBackLightOn(boolean z) {
        return mTVFunction.setBackLightOn(z);
    }

    public int setLocalDimmingSwitch(boolean z) {
        return mTVFunction.setLocalDimmingSwitch(z);
    }

    public int setLocalDimmingState(int i) {
        return mTVFunction.setLocalDimmingState(i);
    }

    public int getLocalDimmingState() {
        return mTVFunction.getLocalDimmingState();
    }

    public void clearKeys() {
        mTVFunction.clearKeys();
    }

    public boolean clearHdcp14Key() {
        return mTVFunction.clearHdcp14Key();
    }

    public boolean clearHdcp22Key() {
        return mTVFunction.clearHdcp22Key();
    }

    public boolean clearWideVineKey() {
        return mTVFunction.clearWideVineKey();
    }

    public boolean clearPlayReadyKey() {
        return mTVFunction.clearPlayReadyKey();
    }

    public boolean isWooferModeOn() {
        return mTVFunction.isWooferModeOn();
    }

    public int getCWValue1() {
        return mTVFunction.getCWValue1();
    }

    public int getCWValue2() {
        return mTVFunction.getCWValue2();
    }

    public boolean setCWValue1(int i) {
        return mTVFunction.setCWValue1(i);
    }

    public boolean setCWValue2(int i) {
        return mTVFunction.setCWValue2(i);
    }

    public String getTVRunningTime() {
        return mTVFunction.getTVRunningTime();
    }

    public static int getMirrorValue() {
        return mTVFunction.getMirrorValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAntiFoolKeyNameValid(boolean z, String str) {
        String str2;
        Log.d("Fac_MenuConfigManager", "isAntiFoolKeyNameValid, file name: " + str);
        String str3 = z ? "drm" : "wide vine";
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", str3 + " file name invalid: file name is null");
        } else {
            int iIndexOf = str.indexOf(".");
            if (iIndexOf > 0) {
                str = str.substring(0, iIndexOf);
            }
            if (z) {
                str2 = FacPropertyDemo.HIFAC_CHINA_DRM_KEY_FILENAME_PREFIX;
            } else {
                str2 = FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX;
            }
            if (!str.startsWith(str2)) {
                Log.e("Fac_MenuConfigManager", str3 + " file name invalid: prefix not match");
            } else {
                Log.d("Fac_MenuConfigManager", "isAntiFoolKeyNameValid, file name: " + str);
                Log.d("Fac_MenuConfigManager", "isAntiFoolKeyNameValid, fileName.length(): " + str.length());
                if (str.length() != 30) {
                    Log.e("Fac_MenuConfigManager", str3 + " file name invalid: length not match");
                } else {
                    try {
                        long j = Long.parseLong(str.substring(0, str.length()).replaceFirst(str2, ""));
                        if (j >= 1) {
                            return true;
                        }
                        Log.e("Fac_MenuConfigManager", str3 + " file name invalid: index error, " + j);
                    } catch (Exception e) {
                        Log.e("HisFactory", str3 + " file name invalid", e);
                    }
                }
            }
        }
        return false;
    }

    public boolean isAntiFoolHDCPKeyNameValid(boolean z, String str) {
        String str2;
        Log.d("Fac_MenuConfigManager", "isAntiFoolHDCPKeyNameValid, file name: " + str);
        String str3 = z ? "hdcp1.4" : "hdcp2.2";
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", str3 + " file name invalid: file name is null");
            return false;
        }
        if (z) {
            str2 = FacPropertyDemo.HIFAC_HDCP14_KEY_FILENAME_PREFIX;
        } else {
            str2 = FacPropertyDemo.HIFAC_HDCP2X_KEY_FILENAME_PREFIX;
        }
        Log.e("Fac_MenuConfigManager", " isAntiFoolHDCPKeyNameValid prefix = " + str2);
        if (!str.toUpperCase().startsWith(str2)) {
            Log.e("Fac_MenuConfigManager", str3 + " file name invalid: prefix not match");
            return false;
        }
        if (str.toLowerCase().endsWith(z ? ".bin" : ".enc")) {
            return true;
        }
        Log.e("Fac_MenuConfigManager", str3 + " file name invalid: suffix not match");
        return false;
    }

    public boolean isAntiFoolCIPlusKeyNameValid(String str) {
        Log.d("Fac_MenuConfigManager", "isAntiFoolHDCPKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", "ci plus file name invalid: file name is null");
            return false;
        }
        if (!str.toUpperCase().startsWith(FacPropertyDemo.HIFAC_CI_PLUS_KEY_FILENAME_PREFIX)) {
            Log.e("Fac_MenuConfigManager", "ci plus file name invalid: prefix not match");
            return false;
        }
        if (str.toLowerCase().endsWith(".key")) {
            return true;
        }
        Log.e("Fac_MenuConfigManager", "ci plus file name invalid: suffix not match");
        return false;
    }

    public int getSensorLuxValue() {
        return mTVFunction.getSensorLuxValue();
    }

    public String getMcuVersionInfo() {
        return mTVFunction.getMcuVersionInfo();
    }

    public String getDlpMcuVersion() {
        return mTVFunction.getDlpMcuVersion();
    }

    public String getLedEffectVersion() {
        return mTVFunction.getLedEffectVersion();
    }

    public String getSoundMcuVersion() {
        return mTVFunction.getSoundMcuVersion();
    }

    public String getFPGAVersion() {
        return mTVFunction.getFPGAVersion();
    }

    public FacPropertyDemo.Logo getLogoEnum() {
        return mTVFunction.getLogoEnum();
    }

    public String getFullLogoName() {
        return mTVFunction.getFullLogoName();
    }

    public String getLogoForEMSAEnum() {
        return mTVFunction.getLogoForEMSAEnum();
    }

    public boolean setGamma(int[] iArr) {
        return mTVFunction.setGamma(iArr);
    }

    public boolean closeGamma(int[] iArr) {
        return mTVFunction.closeGamma(iArr);
    }

    public boolean resetGamma(int i) {
        return mTVFunction.resetGamma(i);
    }

    public int setFactoryProtectFlag(int i) {
        return mTVFunction.setFactoryProtectFlag(i);
    }

    public int getFactoryProtectFlag() {
        return mTVFunction.getFactoryProtectFlag();
    }

    public int getUpgradeFlag() {
        return mTVFunction.getUpgradeFlag();
    }

    public void setUpgradeFlag(int i) {
        mTVFunction.setUpgradeFlag(i);
    }

    public int getRegion() {
        return mTVFunction.getRegion();
    }

    public String getBrand() {
        return mTVFunction.getBrand();
    }

    public void setPlayReadyFromTV() {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.18
            @Override // java.lang.Runnable
            public void run() {
                if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
                    Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV file path=/vendor/tvconfig/config/playready30");
                    if (!MenuConfigManager.mTVFunction.setPlayready30Key("/vendor/tvconfig/config/playready30")) {
                        MenuConfigManager menuConfigManager = MenuConfigManager.this;
                        menuConfigManager.displayHisToast(menuConfigManager.mContext.getResources().getString(R.string.str_playready_key_fail));
                        return;
                    } else {
                        MenuConfigManager menuConfigManager2 = MenuConfigManager.this;
                        menuConfigManager2.displayHisToast(menuConfigManager2.mContext.getResources().getString(R.string.str_playready_key_yes));
                    }
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
                    MenuConfigManager.this.sleep(100L);
                    Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV 40 file path=/perm/orig_pr");
                    if (!MenuConfigManager.mTVFunction.setPlayready40Key("/perm/orig_pr")) {
                        MenuConfigManager menuConfigManager3 = MenuConfigManager.this;
                        menuConfigManager3.displayHisToast(menuConfigManager3.mContext.getResources().getString(R.string.str_playready_key_fail));
                        return;
                    } else {
                        MenuConfigManager menuConfigManager4 = MenuConfigManager.this;
                        menuConfigManager4.displayHisToast(menuConfigManager4.mContext.getResources().getString(R.string.str_playready_key_yes));
                    }
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                    MenuConfigManager.this.sleep(100L);
                    Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV file path=/vendor/tvconfig/config/playready25");
                    if (MenuConfigManager.mTVFunction.setPlayready25Key("/vendor/tvconfig/config/playready25")) {
                        MenuConfigManager menuConfigManager5 = MenuConfigManager.this;
                        menuConfigManager5.displayHisToast(menuConfigManager5.mContext.getResources().getString(R.string.str_playready_key_yes));
                    } else {
                        MenuConfigManager menuConfigManager6 = MenuConfigManager.this;
                        menuConfigManager6.displayHisToast(menuConfigManager6.mContext.getResources().getString(R.string.str_playready25_key_fail));
                    }
                }
            }
        }).start();
    }

    public boolean setPlayReadyFromSerial() {
        boolean playready40Key;
        if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
            Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV file path=/vendor/tvconfig/config/playready30");
            playready40Key = mTVFunction.setPlayready30Key("/vendor/tvconfig/config/playready30");
        } else {
            playready40Key = false;
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
            Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV 40 file path=/perm/orig_pr");
            playready40Key = mTVFunction.setPlayready40Key("/perm/orig_pr");
        }
        if (!FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
            return playready40Key;
        }
        Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV file path=/vendor/tvconfig/config/playready25");
        return mTVFunction.setPlayready25Key("/vendor/tvconfig/config/playready25");
    }

    private File getMacFile(FacRootMenu.WriteCallback writeCallback) throws Throwable {
        String appointedMacFileName = mTVFunction.getAppointedMacFileName();
        String strFindFileOnUSB = FindFileOnUSB(appointedMacFileName);
        Log.i("Fac_MenuConfigManager", "getMacFilePath path = " + strFindFileOnUSB);
        if (strFindFileOnUSB == null) {
            Log.i("Fac_MenuConfigManager", "MAC file path is NULL!!!");
            if (writeCallback == null) {
                displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_macfilenotexist) + "\n" + appointedMacFileName);
            }
            return null;
        }
        File file = new File(strFindFileOnUSB);
        if (file.exists()) {
            return file;
        }
        Log.i("Fac_MenuConfigManager", "MAC file is not exit!!");
        if (writeCallback != null) {
            return null;
        }
        displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_macfilenotexist) + "\n" + appointedMacFileName);
        return null;
    }

    private void setRegionForMac(int i) {
        Log.i("Fac_MenuConfigManager", "setRegionForMac countryIndex = " + i);
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (mTVFunction.isRegionX(i) == 0) {
                mTVFunction.setRegionTilValue(0);
                return;
            }
            if (mTVFunction.isRegionX(i) == 1) {
                mTVFunction.setRegionTilValue(1);
                return;
            }
            if (mTVFunction.isRegionX(i) == 2) {
                mTVFunction.setRegionTilValue(2);
                return;
            } else if (mTVFunction.isRegionX(i) == 3) {
                mTVFunction.setRegionTilValue(3);
                return;
            } else {
                mTVFunction.setRegionTilValue(4);
                return;
            }
        }
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            int iIsRegionX = mTVFunction.isRegionX(i);
            Log.i("Fac_MenuConfigManager", "setRegionForMac regionIndex = " + iIsRegionX);
            if (iIsRegionX == 5) {
                mTVFunction.setRegionTilValue(5);
            } else {
                mTVFunction.setRegionTilValue(6);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0223  */
    /* JADX WARN: Code duplicated, block: B:102:0x0227  */
    /* JADX WARN: Code duplicated, block: B:104:0x022d  */
    /* JADX WARN: Code duplicated, block: B:105:0x0230  */
    /* JADX WARN: Code duplicated, block: B:116:0x0251 A[PHI: r11
      0x0251: PHI (r11v5 boolean) = (r11v4 boolean), (r11v4 boolean), (r11v17 boolean) binds: [B:107:0x0235, B:108:0x0237, B:115:0x0250] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:122:0x0264  */
    /* JADX WARN: Code duplicated, block: B:123:0x0267  */
    /* JADX WARN: Code duplicated, block: B:125:0x026d  */
    /* JADX WARN: Code duplicated, block: B:126:0x026f  */
    /* JADX WARN: Code duplicated, block: B:132:0x0281  */
    /* JADX WARN: Code duplicated, block: B:135:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:137:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:138:0x02b3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:139:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:142:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:144:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:147:0x02de  */
    /* JADX WARN: Code duplicated, block: B:149:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:151:0x0310  */
    /* JADX WARN: Code duplicated, block: B:154:0x031b  */
    /* JADX WARN: Code duplicated, block: B:156:0x0323  */
    /* JADX WARN: Code duplicated, block: B:158:0x034d  */
    /* JADX WARN: Code duplicated, block: B:161:0x0357  */
    /* JADX WARN: Code duplicated, block: B:163:0x035f  */
    /* JADX WARN: Code duplicated, block: B:165:0x0389  */
    /* JADX WARN: Code duplicated, block: B:168:0x0393  */
    /* JADX WARN: Code duplicated, block: B:170:0x039b  */
    /* JADX WARN: Code duplicated, block: B:172:0x03c5  */
    /* JADX WARN: Code duplicated, block: B:175:0x03cf  */
    /* JADX WARN: Code duplicated, block: B:177:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:179:0x0401  */
    /* JADX WARN: Code duplicated, block: B:186:0x041b  */
    /* JADX WARN: Code duplicated, block: B:187:0x041d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:194:0x042d  */
    /* JADX WARN: Code duplicated, block: B:196:0x0457  */
    /* JADX WARN: Code duplicated, block: B:203:0x046d  */
    /* JADX WARN: Code duplicated, block: B:205:0x0497  */
    /* JADX WARN: Code duplicated, block: B:214:0x04b9  */
    /* JADX WARN: Code duplicated, block: B:217:0x04dc  */
    /* JADX WARN: Code duplicated, block: B:218:0x04de  */
    /* JADX WARN: Code duplicated, block: B:21:0x0047  */
    /* JADX WARN: Code duplicated, block: B:220:0x054f  */
    /* JADX WARN: Code duplicated, block: B:23:0x004f  */
    /* JADX WARN: Code duplicated, block: B:249:0x007e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:252:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:255:0x00f6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:258:0x0132 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x0079  */
    /* JADX WARN: Code duplicated, block: B:261:0x016e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:264:0x02d7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:267:0x0315 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:270:0x0352 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:273:0x038e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:276:0x03ca A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:279:0x0406 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:282:0x045c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:285:0x049c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0083  */
    /* JADX WARN: Code duplicated, block: B:30:0x008b  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:35:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:39:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:42:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:44:0x0103  */
    /* JADX WARN: Code duplicated, block: B:46:0x012d  */
    /* JADX WARN: Code duplicated, block: B:49:0x0137  */
    /* JADX WARN: Code duplicated, block: B:51:0x013f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0169  */
    /* JADX WARN: Code duplicated, block: B:60:0x0183  */
    /* JADX WARN: Code duplicated, block: B:61:0x0185 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:67:0x019a  */
    /* JADX WARN: Code duplicated, block: B:68:0x019e  */
    /* JADX WARN: Code duplicated, block: B:74:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:75:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:81:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:82:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:84:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:93:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:95:0x020a  */
    /* JADX WARN: Instruction removed from duplicated block: B:132:0x0281, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:147:0x02de, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:149:0x02e6, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:154:0x031b, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:156:0x0323, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:161:0x0357, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:163:0x035f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:168:0x0393, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:170:0x039b, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:175:0x03cf, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:177:0x03d7, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:194:0x042d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:203:0x046d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:214:0x04b9, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:21:0x0047, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:23:0x004f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:28:0x0083, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:30:0x008b, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:35:0x00bf, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:37:0x00c7, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:42:0x00fb, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:44:0x0103, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:49:0x0137, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:51:0x013f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:84:0x01d3, please report this as an issue */
    private boolean setCountryAndLogoAndLanguage(ArrayList<String> arrayList, FacRootMenu.WriteCallback writeCallback) {
        int i;
        String str;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int i2;
        int logoIndexByStr;
        int countryIndexByString;
        int logoIndexByStr2;
        int i3;
        String str2;
        boolean z6;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        String str3;
        String str4;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int index = -1;
        if (arrayList == null || arrayList.size() != 6) {
            i = 0;
            str = null;
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            i2 = -1;
            logoIndexByStr = -1;
        } else {
            Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguage ergodic========");
            if (FacPropertyDemo.HIFAC_MARKET == FacPropertyDemo.MarketType.US) {
                countryIndexByString = ApVsTILmap.getCountryIndexByString(arrayList.get(1));
                if (countryIndexByString != 0) {
                    z2 = true;
                }
                if (!z2 && FacPropertyDemo.HIFAC_MARKET_HK && MtkTvConfigTypeBase.S3166_CFG_COUNT_HKG.equalsIgnoreCase(arrayList.get(1))) {
                    countryIndexByString = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_TIME_BOUND;
                    z2 = true;
                }
                if (!z2) {
                    for (FacPropertyDemo.CountryForRegion1 countryForRegion1 : FacPropertyDemo.CountryForRegion1.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion1 =" + countryForRegion1.getName());
                        if (countryForRegion1.getName().equalsIgnoreCase(arrayList.get(1))) {
                            countryIndexByString = countryForRegion1.getIndex();
                            z2 = true;
                        }
                    }
                }
                if (!z2) {
                    for (FacPropertyDemo.CountryForRegion2 countryForRegion2 : FacPropertyDemo.CountryForRegion2.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion2 =" + countryForRegion2.getName());
                        if (countryForRegion2.getName().equalsIgnoreCase(arrayList.get(1))) {
                            countryIndexByString = countryForRegion2.getIndex();
                            z2 = true;
                        }
                    }
                }
                if (!z2) {
                    for (FacPropertyDemo.CountryForRegion3 countryForRegion3 : FacPropertyDemo.CountryForRegion3.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion3 =" + countryForRegion3.getName());
                        if (countryForRegion3.getName().equalsIgnoreCase(arrayList.get(1))) {
                            countryIndexByString = countryForRegion3.getIndex();
                            z2 = true;
                        }
                    }
                }
                if (!z2) {
                    for (FacPropertyDemo.CountryForRegion0 countryForRegion0 : FacPropertyDemo.CountryForRegion0.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion0 =" + countryForRegion0.getName());
                        if (countryForRegion0.getName().equalsIgnoreCase(arrayList.get(1))) {
                            countryIndexByString = countryForRegion0.getIndex();
                            z2 = true;
                        }
                    }
                }
                if (!z2) {
                    for (FacPropertyDemo.CountryForRegion4 countryForRegion4 : FacPropertyDemo.CountryForRegion4.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion4 =" + countryForRegion4.getName());
                        if (countryForRegion4.getName().equalsIgnoreCase(arrayList.get(1))) {
                            countryIndexByString = countryForRegion4.getIndex();
                            z2 = true;
                        }
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_MONITOR && !z2) {
                    countryIndexByString = ApVsTILmap.getCountryIndexByString(arrayList.get(1));
                    if (countryIndexByString > 0) {
                        return false;
                    }
                    z2 = true;
                }
                if (FacPropertyDemo.HIFAC_MARKET_TW && !z2) {
                    if (arrayList.get(1).equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_TWN)) {
                        countryIndexByString = 52;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_JP && !z2) {
                    if (arrayList.get(1).equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_JPN)) {
                        countryIndexByString = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_KO && !z2) {
                    if (arrayList.get(1).equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_KOR)) {
                        countryIndexByString = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_IDX_CHANGED;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (z2) {
                    Log.i("Fac_MenuConfigManager", "get Country = " + arrayList.get(1));
                }
                if (!FacPropertyDemo.HIFAC_MARKET_HK || FacPropertyDemo.HIFAC_MARKET_MONITOR || FacPropertyDemo.HIFAC_PRODUCT_OEM) {
                    logoIndexByStr2 = ApVsTILmap.getLogoIndexByStr(arrayList.get(2));
                    if (logoIndexByStr2 <= 0) {
                        return false;
                    }
                    z5 = true;
                } else {
                    logoIndexByStr2 = -1;
                    z5 = false;
                }
                logoIndexByStr = 95;
                if (FacPropertyDemo.HIFAC_MARKET_TW && !z5) {
                    str4 = arrayList.get(2);
                    if ("ben".equalsIgnoreCase(str4)) {
                        logoIndexByStr2 = 60;
                        z5 = true;
                    } else if ("tos".equalsIgnoreCase(str4)) {
                        z5 = true;
                        logoIndexByStr2 = 95;
                    } else {
                        z5 = false;
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_JP || z5) {
                    logoIndexByStr = logoIndexByStr2;
                } else {
                    String str5 = arrayList.get(2);
                    if ("his".equalsIgnoreCase(str5)) {
                        z5 = true;
                        logoIndexByStr = 1;
                    } else if ("tos".equalsIgnoreCase(str5)) {
                        z5 = true;
                    } else {
                        z5 = false;
                        logoIndexByStr = logoIndexByStr2;
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_KO && !z5) {
                    str3 = arrayList.get(2);
                    if ("his".equalsIgnoreCase(str3)) {
                        z5 = true;
                        logoIndexByStr = 1;
                    } else {
                        logoIndexByStr = ApVsTILmap.getLogoIndexByStr(str3);
                        if (logoIndexByStr > 0) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                    }
                }
                if (!z5 && (logoIndexByStr = ApVsTILmap.getLogoIndexByStr(arrayList.get(2))) > 0) {
                    z5 = true;
                }
                if (z5) {
                    String str6 = arrayList.get(2);
                    Log.i("Fac_MenuConfigManager", "get logoName = " + str6);
                    mTVFunction.setBrand(str6);
                }
                if (FacPropertyDemo.HIFAC_MARKET_HK) {
                    index = ApVsTILmap.getLanguageIndexByString(arrayList.get(3));
                    if (index > 0) {
                        return false;
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                for (FacPropertyDemo.Language language : FacPropertyDemo.Language.values()) {
                    if (language.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = language.getIndex();
                        z3 = true;
                    }
                }
                if (!z3) {
                    for (FacPropertyDemo.LanguageForRegion1 languageForRegion1 : FacPropertyDemo.LanguageForRegion1.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion1 =" + languageForRegion1.getName());
                        if (languageForRegion1.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForRegion1.getIndex();
                            z3 = true;
                        }
                    }
                }
                if (!z3) {
                    for (FacPropertyDemo.LanguageForRegion2 languageForRegion2 : FacPropertyDemo.LanguageForRegion2.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion2 =" + languageForRegion2.getName());
                        if (languageForRegion2.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForRegion2.getIndex();
                            z3 = true;
                        }
                    }
                }
                if (!z3) {
                    for (FacPropertyDemo.LanguageForRegion3 languageForRegion3 : FacPropertyDemo.LanguageForRegion3.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion3 =" + languageForRegion3.getName());
                        if (languageForRegion3.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForRegion3.getIndex();
                            z3 = true;
                        }
                    }
                }
                if (!z3) {
                    for (FacPropertyDemo.LanguageForRegion0 languageForRegion0 : FacPropertyDemo.LanguageForRegion0.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion0 =" + languageForRegion0.getName());
                        if (languageForRegion0.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForRegion0.getIndex();
                            z3 = true;
                        }
                    }
                }
                if (!z3) {
                    for (FacPropertyDemo.LanguageForRegion4 languageForRegion4 : FacPropertyDemo.LanguageForRegion4.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion4 =" + languageForRegion4.getName());
                        if (languageForRegion4.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForRegion4.getIndex();
                            z3 = true;
                        }
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_MONITOR && !z3) {
                    index = ApVsTILmap.getLanguageIndexByString(arrayList.get(3));
                    if (index > 0) {
                        return false;
                    }
                    z3 = true;
                }
                if (FacPropertyDemo.HIFAC_MARKET_TW && !z3) {
                    for (FacPropertyDemo.LanguageForTWN languageForTWN : FacPropertyDemo.LanguageForTWN.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic LanguageForTWN =" + languageForTWN.getName());
                        if (languageForTWN.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForTWN.getIndex();
                            z3 = true;
                        }
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_JP && !z3) {
                    for (FacPropertyDemo.LanguageForJPN languageForJPN : FacPropertyDemo.LanguageForJPN.values()) {
                        Log.i("Fac_MenuConfigManager", "ergodic LanguageForJPN =" + languageForJPN.getName());
                        if (languageForJPN.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForJPN.getIndex();
                            z3 = true;
                        }
                    }
                }
                if (FacPropertyDemo.HIFAC_MARKET_KO && !z3 && MtkTvConfigTypeBase.S639_CFG_LANG_KOR.equalsIgnoreCase(arrayList.get(3))) {
                    index = 71;
                    z3 = true;
                }
                if (z3) {
                    Log.i("Fac_MenuConfigManager", "get langName = " + arrayList.get(3));
                }
                str2 = arrayList.get(4);
                if (str2 != null) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                Log.i("Fac_MenuConfigManager", "end file is " + arrayList.get(5));
                boolean zEqualsIgnoreCase = arrayList.get(5).equalsIgnoreCase("#");
                Log.i("Fac_MenuConfigManager", "mac addr length is " + arrayList.get(0).length() + ";  isCountryValid is " + z2 + ";  isLogoValid is " + z5 + ";   isLangValid is " + z3 + ";   isFileEndValid is " + zEqualsIgnoreCase);
                z4 = z6;
                str = str2;
                i = 0;
                i2 = index;
                index = countryIndexByString;
                z = zEqualsIgnoreCase;
            } else {
                countryIndexByString = -1;
            }
            z2 = false;
            if (!z2) {
                countryIndexByString = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_TIME_BOUND;
                z2 = true;
            }
            if (!z2) {
                while (i15 < r10) {
                    Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion1 =" + countryForRegion1.getName());
                    if (countryForRegion1.getName().equalsIgnoreCase(arrayList.get(1))) {
                        countryIndexByString = countryForRegion1.getIndex();
                        z2 = true;
                    }
                }
            }
            if (!z2) {
                while (i14 < r10) {
                    Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion2 =" + countryForRegion2.getName());
                    if (countryForRegion2.getName().equalsIgnoreCase(arrayList.get(1))) {
                        countryIndexByString = countryForRegion2.getIndex();
                        z2 = true;
                    }
                }
            }
            if (!z2) {
                while (i13 < r10) {
                    Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion3 =" + countryForRegion3.getName());
                    if (countryForRegion3.getName().equalsIgnoreCase(arrayList.get(1))) {
                        countryIndexByString = countryForRegion3.getIndex();
                        z2 = true;
                    }
                }
            }
            if (!z2) {
                while (i12 < r10) {
                    Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion0 =" + countryForRegion0.getName());
                    if (countryForRegion0.getName().equalsIgnoreCase(arrayList.get(1))) {
                        countryIndexByString = countryForRegion0.getIndex();
                        z2 = true;
                    }
                }
            }
            if (!z2) {
                while (i11 < r10) {
                    Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion4 =" + countryForRegion4.getName());
                    if (countryForRegion4.getName().equalsIgnoreCase(arrayList.get(1))) {
                        countryIndexByString = countryForRegion4.getIndex();
                        z2 = true;
                    }
                }
            }
            if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
                countryIndexByString = ApVsTILmap.getCountryIndexByString(arrayList.get(1));
                if (countryIndexByString > 0) {
                    return false;
                }
                z2 = true;
            }
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                if (arrayList.get(1).equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_TWN)) {
                    countryIndexByString = 52;
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                if (arrayList.get(1).equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_JPN)) {
                    countryIndexByString = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR;
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            if (FacPropertyDemo.HIFAC_MARKET_KO) {
                if (arrayList.get(1).equalsIgnoreCase(MtkTvConfigTypeBase.S3166_CFG_COUNT_KOR)) {
                    countryIndexByString = MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_IDX_CHANGED;
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            if (z2) {
                Log.i("Fac_MenuConfigManager", "get Country = " + arrayList.get(1));
            }
            if (FacPropertyDemo.HIFAC_MARKET_HK) {
                logoIndexByStr2 = ApVsTILmap.getLogoIndexByStr(arrayList.get(2));
                if (logoIndexByStr2 <= 0) {
                    return false;
                }
                z5 = true;
            } else {
                logoIndexByStr2 = ApVsTILmap.getLogoIndexByStr(arrayList.get(2));
                if (logoIndexByStr2 <= 0) {
                    return false;
                }
                z5 = true;
            }
            logoIndexByStr = 95;
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                str4 = arrayList.get(2);
                if ("ben".equalsIgnoreCase(str4)) {
                    logoIndexByStr2 = 60;
                    z5 = true;
                } else if ("tos".equalsIgnoreCase(str4)) {
                    z5 = true;
                    logoIndexByStr2 = 95;
                } else {
                    z5 = false;
                }
            }
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                logoIndexByStr = logoIndexByStr2;
            } else {
                logoIndexByStr = logoIndexByStr2;
            }
            if (FacPropertyDemo.HIFAC_MARKET_KO) {
                str3 = arrayList.get(2);
                if ("his".equalsIgnoreCase(str3)) {
                    z5 = true;
                    logoIndexByStr = 1;
                } else {
                    logoIndexByStr = ApVsTILmap.getLogoIndexByStr(str3);
                    if (logoIndexByStr > 0) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                }
            }
            if (!z5) {
                z5 = true;
            }
            if (z5) {
                String str7 = arrayList.get(2);
                Log.i("Fac_MenuConfigManager", "get logoName = " + str7);
                mTVFunction.setBrand(str7);
            }
            if (FacPropertyDemo.HIFAC_MARKET_HK) {
                index = ApVsTILmap.getLanguageIndexByString(arrayList.get(3));
                if (index > 0) {
                    return false;
                }
                z3 = true;
            } else {
                z3 = false;
            }
            while (i3 < r14) {
                if (language.getName().equalsIgnoreCase(arrayList.get(3))) {
                    index = language.getIndex();
                    z3 = true;
                }
            }
            if (!z3) {
                while (i10 < r6) {
                    Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion1 =" + languageForRegion1.getName());
                    if (languageForRegion1.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = languageForRegion1.getIndex();
                        z3 = true;
                    }
                }
            }
            if (!z3) {
                while (i9 < r5) {
                    Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion2 =" + languageForRegion2.getName());
                    if (languageForRegion2.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = languageForRegion2.getIndex();
                        z3 = true;
                    }
                }
            }
            if (!z3) {
                while (i8 < r5) {
                    Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion3 =" + languageForRegion3.getName());
                    if (languageForRegion3.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = languageForRegion3.getIndex();
                        z3 = true;
                    }
                }
            }
            if (!z3) {
                while (i7 < r5) {
                    Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion0 =" + languageForRegion0.getName());
                    if (languageForRegion0.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = languageForRegion0.getIndex();
                        z3 = true;
                    }
                }
            }
            if (!z3) {
                while (i6 < r5) {
                    Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion4 =" + languageForRegion4.getName());
                    if (languageForRegion4.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = languageForRegion4.getIndex();
                        z3 = true;
                    }
                }
            }
            if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
                index = ApVsTILmap.getLanguageIndexByString(arrayList.get(3));
                if (index > 0) {
                    return false;
                }
                z3 = true;
            }
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                while (i5 < r5) {
                    Log.i("Fac_MenuConfigManager", "ergodic LanguageForTWN =" + languageForTWN.getName());
                    if (languageForTWN.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = languageForTWN.getIndex();
                        z3 = true;
                    }
                }
            }
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                while (i4 < r5) {
                    Log.i("Fac_MenuConfigManager", "ergodic LanguageForJPN =" + languageForJPN.getName());
                    if (languageForJPN.getName().equalsIgnoreCase(arrayList.get(3))) {
                        index = languageForJPN.getIndex();
                        z3 = true;
                    }
                }
            }
            if (FacPropertyDemo.HIFAC_MARKET_KO) {
                index = 71;
                z3 = true;
            }
            if (z3) {
                Log.i("Fac_MenuConfigManager", "get langName = " + arrayList.get(3));
            }
            str2 = arrayList.get(4);
            if (str2 != null) {
                z6 = true;
            } else {
                z6 = false;
            }
            Log.i("Fac_MenuConfigManager", "end file is " + arrayList.get(5));
            boolean zEqualsIgnoreCase2 = arrayList.get(5).equalsIgnoreCase("#");
            Log.i("Fac_MenuConfigManager", "mac addr length is " + arrayList.get(0).length() + ";  isCountryValid is " + z2 + ";  isLogoValid is " + z5 + ";   isLangValid is " + z3 + ";   isFileEndValid is " + zEqualsIgnoreCase2);
            z4 = z6;
            str = str2;
            i = 0;
            i2 = index;
            index = countryIndexByString;
            z = zEqualsIgnoreCase2;
        }
        if (arrayList == null || arrayList.size() != 6 || arrayList.get(i).length() != 36 || !z2 || !z5 || !z3 || !z || !z4) {
            Log.i("Fac_MenuConfigManager", "content format error!!");
            if (writeCallback != null) {
                return false;
            }
            displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format));
            return false;
        }
        Log.i("Fac_MenuConfigManager", "countryIndex = " + index);
        setRegionForMac(index);
        setCountryIndex(index);
        setLogoIndex(logoIndexByStr);
        setLangIndex(i2);
        if (setProjectId(str) == 0) {
            return true;
        }
        if (writeCallback != null) {
            return false;
        }
        displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_content_bad_format));
        return false;
    }

    private boolean setMacToUDisk(File file, String str, long j, String str2, String str3, String str4, String str5, String str6, FacRootMenu.WriteCallback writeCallback) {
        if (file != null && file.exists()) {
            String hexString = Long.toHexString(j);
            Log.i("Fac_MenuConfigManager", "str=" + hexString + "  lenth= " + hexString.length());
            while (hexString.length() < 12) {
                hexString = "0" + hexString;
            }
            String str7 = (hexString.substring(0, 2).toUpperCase() + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + hexString.substring(2, 4).toUpperCase() + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + hexString.substring(4, 6).toUpperCase() + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + hexString.substring(6, 8).toUpperCase() + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + hexString.substring(8, 10).toUpperCase() + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + hexString.substring(10, 12).toUpperCase()) + str.substring(17, 36);
            Log.i("Fac_MenuConfigManager", "data after changing=" + str7);
            try {
                String absolutePath = file.getAbsolutePath();
                Log.i("Fac_MenuConfigManager", "start write mac file!   " + absolutePath);
                mTVFunction.writeFile(absolutePath, str7, false, true);
                mTVFunction.writeFile(absolutePath, str2, true, true);
                mTVFunction.writeFile(absolutePath, str3, true, true);
                mTVFunction.writeFile(absolutePath, str4, true, true);
                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    mTVFunction.writeFile(absolutePath, str6, true, true);
                }
                mTVFunction.writeFile(absolutePath, str5, true, true);
                mTVFunction.writeFile(absolutePath, "#", true, false);
                mTVFunction.syncFs();
                Thread.sleep(1000L);
                Log.i("Fac_MenuConfigManager", "write mac over!");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Fac_MenuConfigManager", "****write mac addr error********");
                if (writeCallback == null) {
                    displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_writedataerror));
                }
            }
        }
        return false;
    }

    public void isSetMGKKey(Context context) {
        if (mTVFunction.getMGKKey().equals("NG")) {
            mConfigManager.setMGKKeyFromUDisk(null);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show isSetMGKKey warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_mgk_key_ready_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.19
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setMGKKeyFromUDisk(null);
            }
        });
        builder.create().show();
    }

    public void setMGKKeyFromUDisk(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.20
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindMGKPathOnUSB = MenuConfigManager.this.findMGKPathOnUSB();
                if (strFindMGKPathOnUSB == null || strFindMGKPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_mgk_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setMGKKeyFromUDisk file path=" + strFindMGKPathOnUSB);
                    File file = new File(strFindMGKPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(MenuConfigManager.this.getMGKName());
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.20.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolMGKKeyNameValid(name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("enc");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  mgk file name is     " + string2);
                            boolean mGKKey = MenuConfigManager.mTVFunction.setMGKKey(string2);
                            Log.i("Fac_MenuConfigManager", "mgk returns =  " + mGKKey);
                            if (mGKKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getNetflixKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET mgk KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_mgk_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete mgk key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_mgk_key_fail);
                                    } else {
                                        String string3 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_mgk_key_yes);
                                        FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                                        if (writeCallback2 == null) {
                                            MenuConfigManager.this.displayHisToast(string3);
                                            return;
                                        } else {
                                            writeCallback2.writeEnd(true, string3);
                                            return;
                                        }
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_mgk_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_mgk_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_mgk_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                FacRootMenu.WriteCallback writeCallback3 = writeCallback;
                if (writeCallback3 == null) {
                    MenuConfigManager.this.displayHisToast(string);
                } else {
                    writeCallback3.writeEnd(false, string);
                }
            }
        }).start();
    }

    public String findMGKPathOnUSB() throws Throwable {
        MGK_ROOTDIR = getMGKFolderName();
        File file = new File("/mnt/media_rw//" + MGK_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            Log.i("Fac_MenuConfigManager", "mgk file get");
            return "/mnt/media_rw//" + MGK_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "mgk list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "mgk file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + MGK_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("mgk file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find mgk file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find mgk file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "mgk file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " mgk filepath=" + ((String) null));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAntiFoolMGKKeyNameValid(String str) {
        Log.d("Fac_MenuConfigManager", "isAntiFoolMGKKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", "mgk file name invalid: file name is null");
            return false;
        }
        String mGKName = getMGKName();
        if (!str.startsWith(mGKName)) {
            Log.e("Fac_MenuConfigManager", "mgk file name invalid: prefix not match");
            return false;
        }
        if (str.length() != 30) {
            Log.e("Fac_MenuConfigManager", "mgk file name invalid: length not match");
            return false;
        }
        try {
            long j = Long.parseLong(str.replaceFirst(mGKName, ""));
            if (j >= 1) {
                return true;
            }
            Log.e("Fac_MenuConfigManager", "mgk file name invalid: index error, " + j);
            return false;
        } catch (Exception e) {
            Log.e("HisFactory", "mgk file name invalid", e);
            return false;
        }
    }

    public void isSetAttenstationKey(Context context) {
        if (mTVFunction.getAttestationKey().equals("NG")) {
            mConfigManager.setAttestationFromTV(null);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show isSetAttenstationKey warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_attest_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.21
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setAttestationFromTV(null);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        alertDialogCreate.getButton(-2).setFocusable(true);
        alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
        alertDialogCreate.getButton(-2).requestFocus();
    }

    public void setAttestationFromTV(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.22
            @Override // java.lang.Runnable
            public void run() {
                int i;
                String str;
                String wideKey = MenuConfigManager.this.getWideKey();
                if (wideKey == null || wideKey.isEmpty() || "NG".equals(wideKey)) {
                    FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                    if (writeCallback2 == null) {
                        MenuConfigManager menuConfigManager = MenuConfigManager.this;
                        menuConfigManager.displayHisToast(menuConfigManager.mContext.getResources().getString(R.string.str_attest_key_fail));
                    } else {
                        writeCallback2.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_attest_key_fail));
                    }
                    Log.i("Fac_MenuConfigManager", " setAttestationFromTV no wideVineKey");
                    return;
                }
                String strSubstring = wideKey.substring(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX.length());
                Log.i("Fac_MenuConfigManager", " setAttestationFromTV file wideVineKey=" + strSubstring);
                if (strSubstring != null) {
                    i = Integer.parseInt(strSubstring);
                    Log.i("Fac_MenuConfigManager", " setAttestationFromTV file wideVineIndex=" + i);
                } else {
                    i = 0;
                }
                int i2 = i / 100000;
                if (i2 > 0 && i2 < FacParamManager.FAC_ATTEST_COUNT) {
                    str = "perm/orig_attest//attestkey" + i2 + ".bin";
                } else {
                    str = "perm/orig_attest//attestkey.bin";
                }
                Log.i("Fac_MenuConfigManager", " setAttestationFromTV file attPath=" + str);
                Log.i("Fac_MenuConfigManager", " setAttestationFromTV file path=" + str);
                if (MenuConfigManager.mTVFunction.setAttestationKey(str)) {
                    FacRootMenu.WriteCallback writeCallback3 = writeCallback;
                    if (writeCallback3 == null) {
                        MenuConfigManager menuConfigManager2 = MenuConfigManager.this;
                        menuConfigManager2.displayHisToast(menuConfigManager2.mContext.getResources().getString(R.string.str_attest_yes));
                        return;
                    } else {
                        writeCallback3.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_attest_yes));
                        return;
                    }
                }
                FacRootMenu.WriteCallback writeCallback4 = writeCallback;
                if (writeCallback4 == null) {
                    MenuConfigManager menuConfigManager3 = MenuConfigManager.this;
                    menuConfigManager3.displayHisToast(menuConfigManager3.mContext.getResources().getString(R.string.str_attest_key_fail));
                } else {
                    writeCallback4.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_attest_key_fail));
                }
            }
        }).start();
    }

    public int setProjectId(String str) {
        return mTVFunction.setProjectid(str);
    }

    public String getProjectId() {
        return mTVFunction.getProjectId();
    }

    public int[] getVcomsValue() {
        return mTVFunction.getVcomsValue();
    }

    public void addTVComponenetListener(TVComponenetListener tVComponenetListener) {
        this.mListener.add(tVComponenetListener);
    }

    public void notifyListener(String str) {
        ArrayList<TVComponenetListener> arrayList = this.mListener;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<TVComponenetListener> it = this.mListener.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged(str);
        }
    }

    public void removeTVComponenetListener(TVComponenetListener tVComponenetListener) {
        this.mListener.remove(tVComponenetListener);
    }

    public boolean setMGKKeyForSerial(String str) {
        return mTVFunction.setMGKKey(str);
    }

    public int getUsbCount() {
        int i = 0;
        for (UsbDevice usbDevice : ((UsbManager) this.mContext.getSystemService("usb")).getDeviceList().values()) {
            if (usbDevice.getDeviceClass() == 0 && usbDevice.getInterface(0).getInterfaceClass() == 8) {
                i++;
            }
        }
        Log.i("Fac_MenuConfigManager", "getUsbCount num = " + i);
        return i;
    }

    public boolean setOttSrc(int i) {
        return mTVFunction.setOttSrc(i);
    }

    public boolean setAttestationForSerial() {
        String str;
        String wideKey = getWideKey();
        int i = 0;
        if (wideKey == null || wideKey.isEmpty() || "NG".equals(wideKey)) {
            return false;
        }
        String strSubstring = wideKey.substring(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX.length());
        Log.i("Fac_MenuConfigManager", " setAttestationFromTV file wideVineKey=" + strSubstring);
        if (strSubstring != null) {
            i = Integer.parseInt(strSubstring);
            Log.i("Fac_MenuConfigManager", " setAttestationFromTV file wideVineIndex=" + i);
        }
        int i2 = i / 100000;
        if (i2 > 0 && i2 < FacParamManager.FAC_ATTEST_COUNT) {
            str = "perm/orig_attest//attestkey" + i2 + ".bin";
        } else {
            str = "perm/orig_attest//attestkey.bin";
        }
        Log.i("Fac_MenuConfigManager", " setAttestationFromTV file attPath=" + str);
        Log.i("Fac_MenuConfigManager", " setAttestationFromTV file path=" + str);
        return mTVFunction.setAttestationKey(str);
    }

    public boolean setAttestAndPlayreadyForEeprom() {
        int i;
        String str;
        String wideKey = getWideKey();
        if (wideKey == null || wideKey.isEmpty() || "NG".equals(wideKey)) {
            return false;
        }
        String strSubstring = wideKey.substring(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX.length());
        Log.i("Fac_MenuConfigManager", " setAttestAndPlayreadyForEeprom file wideVineKey=" + strSubstring);
        if (strSubstring != null) {
            i = Integer.parseInt(strSubstring);
            Log.i("Fac_MenuConfigManager", " setAttestAndPlayreadyForEeprom file wideVineIndex=" + i);
        } else {
            i = 0;
        }
        int i2 = i / 100000;
        if (i2 > 0 && i2 < FacParamManager.FAC_ATTEST_COUNT) {
            str = "perm/orig_attest//attestkey" + i2 + ".bin";
        } else {
            str = "perm/orig_attest//attestkey.bin";
        }
        Log.i("Fac_MenuConfigManager", " setAttestAndPlayreadyForEeprom file attPath=" + str);
        boolean attestationKey = mTVFunction.setAttestationKey(str);
        if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
            Log.i("Fac_MenuConfigManager", " setPlayReadyFromTV 40 file path=/perm/orig_pr");
            if (!mTVFunction.setPlayready40Key("/perm/orig_pr")) {
                attestationKey = false;
            }
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
            Log.i("Fac_MenuConfigManager", " setAttestAndPlayreadyForEeprom file path=/vendor/tvconfig/config/playready30");
            if (!mTVFunction.setPlayready30Key("/vendor/tvconfig/config/playready30")) {
                attestationKey = false;
            }
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
            Log.i("Fac_MenuConfigManager", " setAttestAndPlayreadyForEeprom file path=/vendor/tvconfig/config/playready25");
            if (!mTVFunction.setPlayready25Key("/vendor/tvconfig/config/playready25")) {
                return false;
            }
        }
        return attestationKey;
    }

    public String getCurrentSourceName() {
        return mTVFunction.getCurrentSourceName();
    }

    public int setTestPattern(int i, int i2, int i3, int i4) {
        return mTVFunction.setTestPattern(i, i2, i3, i4);
    }

    public String readLogFromTvcertificate() {
        return mTVFunction.readLog();
    }

    public void setLogToTvcertificate(String str) {
        mTVFunction.setLogToFile(str);
    }

    public String getWifiMacAddress() {
        return mTVFunction.getWifiMacAddress();
    }

    public String getQDAName() {
        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
            String logoName = getLogoName();
            if ("his".equalsIgnoreCase(logoName)) {
                return FacPropertyDemo.QDA_NAME;
            }
            if ("tos".equalsIgnoreCase(logoName)) {
                return FacPropertyDemo.QDA_NAME;
            }
            if ("lca".equalsIgnoreCase(logoName)) {
                return FacPropertyDemo.QDA_LCA_NAME;
            }
        }
        return FacPropertyDemo.QDA_NAME;
    }

    public String getMGKName() {
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            return FacPropertyDemo.HIFAC_MGK_TVS_KEY_FILENAME_PREFIX;
        }
        String logoName = getLogoName();
        if ("his".equalsIgnoreCase(logoName)) {
            return FacPropertyDemo.HIFAC_MGK_KEY_FILENAME_PREFIX;
        }
        if ("tos".equalsIgnoreCase(logoName)) {
            return FacPropertyDemo.HIFAC_MGK_KEY_FILENAME_PREFIX;
        }
        return "shp".equalsIgnoreCase(logoName) ? "NG" : FacPropertyDemo.HIFAC_MGK_OEM_KEY_FILENAME_PREFIX;
    }

    public void setPlayReadyAndAttestationFromTV(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.23
            @Override // java.lang.Runnable
            public void run() {
                String string;
                String string2;
                int i;
                String str;
                MenuConfigManager.mTVFunction.setLogToFile("setPlayReadyAndAttestationFromTV start");
                String wideKey = MenuConfigManager.this.getWideKey();
                boolean playready25Key = false;
                if (wideKey == null || wideKey.isEmpty() || "NG".equals(wideKey)) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_attest_key_fail);
                    Log.i("Fac_MenuConfigManager", " setPlayReadyAndAttestationFromTV no wideVineKey");
                } else {
                    String strSubstring = wideKey.substring(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX.length());
                    Log.i("Fac_MenuConfigManager", " setPlayReadyAndAttestationFromTV file wideVineKey=" + strSubstring);
                    if (strSubstring != null) {
                        i = Integer.parseInt(strSubstring);
                        Log.i("Fac_MenuConfigManager", " setPlayReadyAndAttestationFromTV file wideVineIndex=" + i);
                    } else {
                        i = 0;
                    }
                    int i2 = i / 100000;
                    MenuConfigManager.mTVFunction.setLogToFile("setPlayReadyAndAttestationFromTV attestKeyIndex " + i2);
                    if (i2 > 0 && i2 < FacParamManager.FAC_ATTEST_COUNT) {
                        str = "perm/orig_attest//attestkey" + i2 + ".bin";
                    } else {
                        str = "perm/orig_attest//attestkey.bin";
                    }
                    Log.i("Fac_MenuConfigManager", " setPlayReadyAndAttestationFromTV file attPath=" + str);
                    Log.i("Fac_MenuConfigManager", " setAttestationFromTV file path=" + str);
                    if (MenuConfigManager.mTVFunction.setAttestationKey(str)) {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_attest_yes);
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_attest_key_fail);
                    }
                    MenuConfigManager.this.sleep(200L);
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_30KEY_SUPPORT) {
                    Log.i("Fac_MenuConfigManager", " setPlayReadyAndAttestationFromTV file path=/vendor/tvconfig/config/playready30");
                    playready25Key = MenuConfigManager.mTVFunction.setPlayready40Key("/vendor/tvconfig/config/playready30");
                    if (!playready25Key) {
                        String string3 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_fail);
                        MenuConfigManager.this.showToastOnRootMenu(string + "\n" + string3);
                        return;
                    }
                    string2 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_yes);
                } else {
                    string2 = "";
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_40KEY_SUPPORT) {
                    MenuConfigManager.this.sleep(100L);
                    Log.i("Fac_MenuConfigManager", " setPlayReadyAndAttestationFromTV file path=/perm/orig_pr");
                    playready25Key = MenuConfigManager.mTVFunction.setPlayready40Key("/perm/orig_pr");
                    if (!playready25Key) {
                        String string4 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_fail);
                        MenuConfigManager.this.showToastOnRootMenu(string + "\n" + string4);
                        writeCallback.writeEnd(playready25Key ^ true, "write end");
                        return;
                    }
                    string2 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_yes);
                }
                if (FacPropertyDemo.HIFAC_PLAY_READY_25KEY_SUPPORT) {
                    MenuConfigManager.this.sleep(100L);
                    Log.i("Fac_MenuConfigManager", " setPlayReadyAndAttestationFromTV file path=/vendor/tvconfig/config/playready25");
                    playready25Key = MenuConfigManager.mTVFunction.setPlayready25Key("/vendor/tvconfig/config/playready25");
                    if (playready25Key) {
                        string2 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready_key_yes);
                    } else {
                        string2 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_playready25_key_fail);
                    }
                }
                MenuConfigManager.mTVFunction.setLogToFile("setPlayReadyAndAttestationFromTV end");
                writeCallback.writeEnd(playready25Key, "write end");
                MenuConfigManager.this.showToastOnRootMenu(string + "\n" + string2);
            }
        }).start();
    }

    public void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRootMenuListener(IRootMenuUiListener iRootMenuUiListener) {
        this.mRootMenuListener = iRootMenuUiListener;
    }

    public void showToastOnRootMenu(String str) {
        IRootMenuUiListener iRootMenuUiListener = this.mRootMenuListener;
        if (iRootMenuUiListener != null) {
            iRootMenuUiListener.showToast(str);
        } else {
            displayHisToast(str);
        }
    }

    public boolean mgkNameIsRight() {
        String netflixKey = getNetflixKey();
        Log.i("Fac_MenuConfigManager", " mgkNameIsRight mgkName =" + netflixKey);
        if (netflixKey != null && !netflixKey.isEmpty()) {
            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                return netflixKey.contains(FacPropertyDemo.HIFAC_MGK_TVS_KEY_FILENAME_PREFIX);
            }
            String logoName = getLogoName();
            Log.i("Fac_MenuConfigManager", " mgkNameIsRight logo =" + logoName);
            if (logoName != null && !logoName.isEmpty()) {
                if ("his".equalsIgnoreCase(logoName) || "tos".equalsIgnoreCase(logoName)) {
                    if ("2g".equalsIgnoreCase(FacPropertyDemo.HIFAC_DDR)) {
                        return netflixKey.contains("MGK000MSD9980HISDV2GHS");
                    }
                    return netflixKey.contains(FacPropertyDemo.HIFAC_MGK_KEY_FILENAME_PREFIX);
                }
                if ("tos".equalsIgnoreCase(logoName)) {
                    return netflixKey.contains(FacPropertyDemo.HIFAC_MGK_TOSHIBA_KEY_FILENAME_PREFIX);
                }
                if ("shp".equalsIgnoreCase(logoName)) {
                    return false;
                }
                return netflixKey.contains(FacPropertyDemo.HIFAC_MGK_OEM_KEY_FILENAME_PREFIX);
            }
        }
        return false;
    }

    public boolean isFrcSupport() {
        return mTVFunction.isFrcSupport();
    }

    public boolean isTconSupport() {
        return mTVFunction.isTconSupport();
    }

    public boolean isBluSupport() {
        return mTVFunction.isBluSupport();
    }

    public String getQDAFolderName() {
        if (FacPropertyDemo.HIFAC_IS_LASER4K) {
            String logoName = getLogoName();
            if ("his".equalsIgnoreCase(logoName)) {
                return FacPropertyDemo.QDA_FLODER_NAME;
            }
            if ("tos".equalsIgnoreCase(logoName)) {
                return FacPropertyDemo.QDA_FLODER_NAME;
            }
            if ("lca".equalsIgnoreCase(logoName)) {
                return FacPropertyDemo.QDA_LCA_FLODER_NAME;
            }
        }
        return FacPropertyDemo.QDA_FLODER_NAME;
    }

    public String getMGKFolderName() {
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            return FacPropertyDemo.HIFAC_MGK_TVS_KEY_FILENAME;
        }
        String logoName = getLogoName();
        if ("his".equalsIgnoreCase(logoName)) {
            return FacPropertyDemo.HIFAC_MGK_KEY_FILENAME + "/";
        }
        if ("tos".equalsIgnoreCase(logoName)) {
            return FacPropertyDemo.HIFAC_MGK_KEY_FILENAME + "/";
        }
        if ("shp".equalsIgnoreCase(logoName)) {
            return "";
        }
        return FacPropertyDemo.HIFAC_MGK_OEM_KEY_FILENAME + "/";
    }

    public boolean isWideVineNameIsRight() {
        String deviceID = getDeviceID();
        Log.i("Fac_MenuConfigManager", " isWideVineNameIsRight widevineName =" + deviceID);
        Log.i("Fac_MenuConfigManager", " isWideVineNameIsRight widevineName1 =" + FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX);
        Log.i("Fac_MenuConfigManager", " isWideVineNameIsRight widevineName2 =" + FacPropertyDemo.HIFAC_MIC_SUPPORT);
        if (!deviceID.startsWith(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX)) {
            return false;
        }
        Log.i("Fac_MenuConfigManager", " isWideVineNameIsRight widevineName3 =" + FacPropertyDemo.HIFAC_MIC_SUPPORT);
        return true;
    }

    public boolean isHaveMGK() {
        String netflixKey = getNetflixKey();
        return !"NG".equalsIgnoreCase(netflixKey) && netflixKey.length() > 0;
    }

    public void startTestMic(final int i) {
        HisenseFacMicTest.getInstance().initMicParam(this.mContext);
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.24
            @Override // java.lang.Runnable
            public void run() {
                String str;
                synchronized (MenuConfigManager.this.mMicTightnessLock) {
                    double dStartMicTightnessTest = HisenseFacMicTest.getInstance().startMicTightnessTest(i);
                    str = "MIC TEST" + i + " result is " + dStartMicTightnessTest;
                    if (i == 0) {
                        MenuConfigManager.this.mMicOneTightnessResult = dStartMicTightnessTest;
                    } else {
                        MenuConfigManager.this.mMicTwoTightnessResult = dStartMicTightnessTest;
                    }
                }
                MenuConfigManager.this.displayHisToast(str);
                MenuConfigManager.this.notifyListener("FACTORY_MIC");
            }
        }).start();
    }

    public String getMicDb(int i) {
        String str;
        synchronized (this.mMicTightnessLock) {
            this.mMicOneTightnessResult = HisenseFacMicTest.getInstance().getMicOneResult();
            this.mMicTwoTightnessResult = HisenseFacMicTest.getInstance().getMicTwoResult();
            if (i == 0) {
                str = "" + this.mMicOneTightnessResult;
            } else {
                str = "" + this.mMicTwoTightnessResult;
            }
        }
        return str;
    }

    public String getMicTestResult() {
        int micTestResult = mTVFunction.getMicTestResult();
        if (micTestResult == 1) {
            return " MIC OK";
        }
        if (micTestResult == 44) {
            return " MIC NG";
        }
        return " MIC NULL";
    }

    public void startExternalMicTest(final int i) {
        HisenseFacMicTest.getInstance().initMicParam(this.mContext);
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.25
            @Override // java.lang.Runnable
            public void run() {
                String str;
                synchronized (MenuConfigManager.this.mMicTightnessLock) {
                    double micStatus = HisenseFacMicTest.getInstance().getMicStatus(i);
                    str = "MIC TEST" + i + " result is " + micStatus;
                    if (i == 0) {
                        MenuConfigManager.this.mMicOneTightnessResult = micStatus;
                    } else {
                        MenuConfigManager.this.mMicTwoTightnessResult = micStatus;
                    }
                }
                MenuConfigManager.this.displayHisToast(str);
                MenuConfigManager.this.notifyListener("FACTORY_MIC");
            }
        }).start();
    }

    public String getMicSwitchStatus() {
        return 1 == mTVFunction.getMicSwitchStatus() ? "ON" : "OFF";
    }

    public boolean isMicSwitchOn() {
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            return 1 == mTVFunction.getMicSwitchStatus();
        }
        Log.i("Fac_MenuConfigManager", " isMicSwitchOn not mic, return true");
        return true;
    }

    public void readDriverInfo() {
        mTVFunction.readDriverInfo();
    }

    public String cover3CharacterLogToFullSpelling(String str) {
        return str.equals("his") ? "hisense" : str;
    }

    public void setProjectIdFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.26
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String projectIdFileName = MenuConfigManager.mTVFunction.getProjectIdFileName();
                File projectIdFile = MenuConfigManager.this.getProjectIdFile(writeCallback);
                if (projectIdFile == null || !projectIdFile.exists()) {
                    Log.i("Fac_MenuConfigManager", "setProjectIdFromUDiskWithCB file is not exit");
                    writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_not_exist) + "\n" + projectIdFileName);
                    return;
                }
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(projectIdFile));
                    String line = bufferedReader.readLine();
                    ArrayList arrayList = new ArrayList();
                    if (line == null) {
                        writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_empty));
                        bufferedReader.close();
                        return;
                    }
                    Log.i("Fac_MenuConfigManager", "fileContent is " + line);
                    arrayList.add(line);
                    while (true) {
                        String line2 = bufferedReader.readLine();
                        if (line2 == null) {
                            break;
                        }
                        Log.i("Fac_MenuConfigManager", "fileContent is " + line2);
                        arrayList.add(line2);
                    }
                    Log.i("Fac_MenuConfigManager", "strContentArraylist size is " + arrayList.size());
                    String str = (String) arrayList.get(0);
                    String projectId = MenuConfigManager.this.getProjectId();
                    if (!str.equals(projectId)) {
                        if (!MenuConfigManager.this.setProjectIdWithUDisk(arrayList, writeCallback)) {
                            Log.i("Fac_MenuConfigManager", "setProjectIdWithUDisk return false ");
                            writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_content_bad_format));
                            bufferedReader.close();
                            return;
                        }
                        bufferedReader.close();
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        writeCallback.writeEnd(true, MenuConfigManager.this.mContext.getResources().getString(R.string.str_facmenu_set_projectid_success));
                        Log.i("Fac_MenuConfigManager", "SET Projectid succeed");
                        return;
                    }
                    Log.i("Fac_MenuConfigManager", "file is " + str + " ;  getProject is  " + projectId);
                    writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_facmenu_set_projectid_repeat));
                    bufferedReader.close();
                } catch (IOException unused) {
                    writeCallback.writeEnd(false, MenuConfigManager.this.mContext.getResources().getString(R.string.str_facmenu_read_projectid_file_error));
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getProjectIdFile(FacRootMenu.WriteCallback writeCallback) throws Throwable {
        String projectIdFileName = mTVFunction.getProjectIdFileName();
        String strFindFileOnUSB = FindFileOnUSB(projectIdFileName);
        Log.i("Fac_MenuConfigManager", "getProjectIdFilePath path = " + strFindFileOnUSB);
        if (strFindFileOnUSB == null) {
            Log.i("Fac_MenuConfigManager", "Project Id file path is NULL!!!");
            if (writeCallback == null) {
                displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_not_exist) + "\n" + projectIdFileName);
            }
            return null;
        }
        File file = new File(strFindFileOnUSB);
        if (file.exists()) {
            return file;
        }
        Log.i("Fac_MenuConfigManager", "Project Id file is not exit!!");
        if (writeCallback == null) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_not_exist) + "\n" + projectIdFileName);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setProjectIdWithUDisk(ArrayList<String> arrayList, FacRootMenu.WriteCallback writeCallback) {
        String str;
        boolean z;
        boolean z2;
        if (arrayList == null || arrayList.size() != 2) {
            str = null;
            z = false;
            z2 = false;
        } else {
            str = arrayList.get(0);
            z = str != null;
            Log.i("Fac_MenuConfigManager", "end file is " + arrayList.get(1));
            z2 = arrayList.get(1).equalsIgnoreCase("#");
        }
        if (arrayList == null || arrayList.size() != 2 || arrayList.get(0).length() != 3 || !z || !z2) {
            Log.i("Fac_MenuConfigManager", "content format error!!");
            if (writeCallback == null) {
                displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_content_bad_format));
            }
            return false;
        }
        int projectId = setProjectId(str);
        Log.i("Fac_MenuConfigManager", "setProjectIdResult:" + projectId);
        return projectId == 0;
    }

    public String getWidevineLastName() {
        String wideKey = getWideKey();
        if (wideKey == null || wideKey.isEmpty() || wideKey.equals("NG")) {
            return "HisenseMicTestPcm";
        }
        String strSubstring = wideKey.substring(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX.length());
        Log.i("Fac_MenuConfigManager", " getWidevineLastName wideVineKey=" + strSubstring);
        return "HisenseMicTestPcm" + strSubstring;
    }

    public void updateOSDC() {
        String line;
        try {
            File file = new File("/vendor/tvconfig/config/module/Customer_Module.ini");
            if (!file.exists()) {
                Log.i("Fac_MenuConfigManager", "OSDC file is not exit!!");
                mTVFunction.setOSDCFlag(false);
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line2 = bufferedReader.readLine();
            if (line2 == null) {
                Log.i("Fac_MenuConfigManager", "OSDC fileContent is null");
                bufferedReader.close();
                mTVFunction.setOSDCFlag(false);
                return;
            }
            Log.i("Fac_MenuConfigManager", "OSDC fileContent is " + line2);
            if (line2.contains("F_BACKEND_ENABLE_OSDC")) {
                if (getOSDCFlag(line2) == 1) {
                    mTVFunction.setOSDCFlag(true);
                    return;
                } else {
                    mTVFunction.setOSDCFlag(false);
                    return;
                }
            }
            do {
                line = bufferedReader.readLine();
                if (line == null) {
                    return;
                }
                Log.i("Fac_MenuConfigManager", "OSDC2 fileContent is " + line);
            } while (!line.contains("F_BACKEND_ENABLE_OSDC"));
            if (getOSDCFlag(line) == 1) {
                mTVFunction.setOSDCFlag(true);
            } else {
                mTVFunction.setOSDCFlag(false);
            }
        } catch (IOException unused) {
        }
    }

    private int getOSDCFlag(String str) {
        Log.i("Fac_MenuConfigManager", "getOSDCFlag body = " + str);
        int iIndexOf = str.indexOf("=");
        Log.i("Fac_MenuConfigManager", "getOSDCFlag position1 = " + iIndexOf);
        if (iIndexOf < 0) {
            return 0;
        }
        int iIndexOf2 = str.indexOf(";");
        Log.i("Fac_MenuConfigManager", "getOSDCFlag position2 = " + iIndexOf2);
        if (iIndexOf2 < 0) {
            return 0;
        }
        String strTrim = str.substring(iIndexOf + 1, iIndexOf2).trim();
        Log.i("Fac_MenuConfigManager", "getOSDCFlag temp = " + strTrim);
        return Integer.valueOf(strTrim).intValue();
    }

    public String getZDMicTestResult() {
        int zDMicTestResult = mTVFunction.getZDMicTestResult();
        if (zDMicTestResult == 1) {
            return " ZDMIC OK";
        }
        if (zDMicTestResult == 44) {
            return " ZDMIC NG";
        }
        return " ZDMIC NULL";
    }

    public boolean isZDMicTestOK() {
        return mTVFunction.getZDMicTestResult() == 1;
    }

    private boolean setCountryAndLogoAndLanguageForSA(ArrayList<String> arrayList, FacRootMenu.WriteCallback writeCallback) {
        String str;
        int i;
        int logoValueForSA;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int index = -1;
        if (arrayList != null && arrayList.size() == 7) {
            Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguageForSA start");
            int index2 = -1;
            z3 = false;
            for (FacPropertyDemo.CountryForRegion5 countryForRegion5 : FacPropertyDemo.CountryForRegion5.values()) {
                Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion5 =" + countryForRegion5.getName());
                if (countryForRegion5.getName().equalsIgnoreCase(arrayList.get(1))) {
                    index2 = countryForRegion5.getIndex();
                    z3 = true;
                }
            }
            if (!z3) {
                for (FacPropertyDemo.CountryForRegion6 countryForRegion6 : FacPropertyDemo.CountryForRegion6.values()) {
                    Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion6 =" + countryForRegion6.getName());
                    if (countryForRegion6.getName().equalsIgnoreCase(arrayList.get(1))) {
                        int index3 = countryForRegion6.getIndex();
                        arrayList.get(1);
                        index2 = index3;
                        z3 = true;
                    }
                }
            }
            if (z3) {
                Log.i("Fac_MenuConfigManager", "get Country = " + arrayList.get(1));
                String str2 = arrayList.get(2);
                logoValueForSA = mTVFunction.getLogoValueForSA(str2);
                if (logoValueForSA > 0) {
                    mTVFunction.setBrand(str2);
                    z5 = false;
                    for (FacPropertyDemo.LanguageForRegion5 languageForRegion5 : FacPropertyDemo.LanguageForRegion5.values()) {
                        if (languageForRegion5.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForRegion5.getIndex();
                            z5 = true;
                        }
                    }
                    if (!z5) {
                        for (FacPropertyDemo.LanguageForRegion6 languageForRegion6 : FacPropertyDemo.LanguageForRegion6.values()) {
                            Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion6 =" + languageForRegion6.getName());
                            if (languageForRegion6.getName().equalsIgnoreCase(arrayList.get(3))) {
                                index = languageForRegion6.getIndex();
                                z5 = true;
                            }
                        }
                    }
                    if (z5) {
                        Log.i("Fac_MenuConfigManager", "get langName = " + arrayList.get(3));
                        arrayList.get(4);
                        str = arrayList.get(5);
                        boolean z6 = str != null;
                        Log.i("Fac_MenuConfigManager", "end file is " + arrayList.get(6));
                        if (arrayList.get(6).equalsIgnoreCase("#")) {
                            z = z6;
                            z2 = true;
                            i = index;
                            index = index2;
                            z4 = true;
                        } else {
                            z = z6;
                            z2 = true;
                            i = index;
                            index = index2;
                            z4 = false;
                        }
                    }
                }
            }
            return false;
        }
        str = null;
        i = -1;
        logoValueForSA = -1;
        z = false;
        z2 = false;
        z3 = false;
        z4 = false;
        z5 = false;
        Log.i("Fac_MenuConfigManager", "mac addr length is " + arrayList.get(0).length() + ";  isCountryValid is " + z3 + ";  isLogoValid is " + z2 + ";   isLangValid is " + z5 + ";   isFileEndValid is " + z4);
        if (arrayList == null || arrayList.size() != 7 || arrayList.get(0).length() != 36 || !z3 || !z2 || !z5 || !z4 || !z) {
            Log.i("Fac_MenuConfigManager", "content format error!!");
            if (writeCallback == null) {
                displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format));
            }
            return false;
        }
        Log.i("Fac_MenuConfigManager", "countryIndex = " + index);
        setRegionForMac(index);
        setCountryIndex(index);
        setLogoIndex(logoValueForSA);
        setLangIndex(i);
        if (setProjectId(str) == 0) {
            return true;
        }
        if (writeCallback == null) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_content_bad_format));
        }
        return false;
    }

    public void setLogoForLogoAndDigitalPressed(int i) {
        Log.i("Fac_MenuConfigManager", "setLogoForLogoAndDigitalPressed key = " + i);
        if (i < 0 || i > 9) {
            return;
        }
        String brand = mTVFunction.getBrand();
        Log.i("Fac_MenuConfigManager", "setLogoForLogoAndDigitalPressed brand = " + brand);
        String name = "";
        String name2 = FacPropertyDemo.LogoForBGH_SABrandIsolation.bgh.getName();
        String name3 = FacPropertyDemo.LogoForBGH_SABrandIsolation.tel.getName();
        String name4 = FacPropertyDemo.LogoForBGH_SABrandIsolation.his.getName();
        int index = 0;
        if (!name2.equalsIgnoreCase(brand) && !name3.equalsIgnoreCase(brand) && !name4.equalsIgnoreCase(brand)) {
            FacPropertyDemo.LogoForNEWSAN_SABrandIsolation[] logoForNEWSAN_SABrandIsolationArrValues = FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values();
            int length = logoForNEWSAN_SABrandIsolationArrValues.length;
            int index2 = 0;
            while (index < length) {
                if (!logoForNEWSAN_SABrandIsolationArrValues[index].getName().equalsIgnoreCase(brand)) {
                    return;
                }
                index2 = FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()[i].getIndex();
                name = FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()[i].getName();
                index++;
            }
            index = index2;
        } else if (i == 0) {
            index = FacPropertyDemo.LogoForBGH_SABrandIsolation.bgh.getIndex();
            name = name2;
        } else if (i == 1) {
            index = FacPropertyDemo.LogoForBGH_SABrandIsolation.tel.getIndex();
            name = name3;
        } else if (i == 2) {
            index = FacPropertyDemo.LogoForBGH_SABrandIsolation.his.getIndex();
            name = name4;
        }
        Log.i("Fac_MenuConfigManager", "setLogoForLogoAndDigitalPressed index = " + index);
        Log.i("Fac_MenuConfigManager", "setLogoForLogoAndDigitalPressed newbrand = " + name);
        if (name.isEmpty()) {
            return;
        }
        mTVFunction.setBrand(name);
        mTVFunction.setLogoIndex(index);
    }

    public String getLogoFullNameSA() {
        return mTVFunction.getLogoFullNameForSA();
    }

    public String getLogoFullNameJPN() {
        return mTVFunction.getLogoFullNameForJPN();
    }

    public String getLogoFullNameMon() {
        return mTVFunction.getLogoFullNameMon();
    }

    public String getClientType() {
        return mTVFunction.getClientType();
    }

    public boolean isHaveClientType() {
        String clientType = getClientType();
        Log.i("Fac_MenuConfigManager", "isHaveClientType type = " + clientType);
        return (clientType == null || clientType.isEmpty()) ? false : true;
    }

    public void addTVPresetChannelForSA(int i) {
        mTVFunction.addTVPresetChannelForSA(i);
    }

    public int getFacLogStatus() {
        return mTVFunction.getFacLogStatus();
    }

    public void setFacLogStatus(int i) {
        mTVFunction.setFacLogStatus(i);
    }

    public void generateEepKeyFile() {
        mTVFunction.generateEepKeyFile();
    }

    public String setEepromKeyAfterEnterFac() {
        Log.i("Fac_MenuConfigManager", "setEepromKeyAfterEnterFac");
        String string = this.mContext.getResources().getString(R.string.str_qda_key_fail);
        String string2 = this.mContext.getResources().getString(R.string.str_hdcp14_key_fail);
        String string3 = this.mContext.getResources().getString(R.string.str_hdcp2x_key_fail);
        String string4 = this.mContext.getResources().getString(R.string.str_wide_vine_key_fail);
        String string5 = this.mContext.getResources().getString(R.string.str_mgk_key_fail);
        String string6 = this.mContext.getResources().getString(R.string.str_facmenu_setmacfail);
        String[] fileNameList = FacFileUtils.getFileNameList("/perm/keyname/eepkey/");
        if (fileNameList == null || fileNameList.length == 0) {
            Log.i("Fac_MenuConfigManager", "setEepromKeyAfterEnterFac file is null");
            return string6 + "\n" + string + "\n" + string2 + "\n" + string3 + "\n" + string4 + "\n" + string5;
        }
        int length = fileNameList.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            String str = fileNameList[i];
            StringBuilder sb = new StringBuilder();
            String[] strArr = fileNameList;
            sb.append("setEepromKeyAfterEnterFac file path = ");
            sb.append(str);
            Log.i("Fac_MenuConfigManager", sb.toString());
            if (str.startsWith(getQDAName())) {
                if (mTVFunction.setQDAKey("/perm/keyname/eepkey/" + str)) {
                    string = this.mContext.getResources().getString(R.string.str_qda_key_yes);
                } else {
                    z = true;
                }
            }
            if (str.startsWith(FacPropertyDemo.HIFAC_HDCP14_KEY_FILENAME_PREFIX)) {
                if (mTVFunction.setHdcp14Key("/perm/keyname/eepkey/" + str)) {
                    string2 = this.mContext.getResources().getString(R.string.str_hdcp14_key_yes);
                } else {
                    z = true;
                }
            }
            if (str.startsWith(FacPropertyDemo.HIFAC_HDCP2X_KEY_FILENAME_PREFIX)) {
                if (mTVFunction.setHdcp2xKey("/perm/keyname/eepkey/" + str)) {
                    string3 = this.mContext.getResources().getString(R.string.str_hdcp2x_key_yes);
                } else {
                    z = true;
                }
            }
            if (str.startsWith(getMGKName())) {
                if (mTVFunction.setMGKKey("/perm/keyname/eepkey/" + str)) {
                    string5 = this.mContext.getResources().getString(R.string.str_mgk_key_yes);
                } else {
                    z = true;
                }
            }
            if (str.startsWith(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX)) {
                if (mTVFunction.setWideVineKey("/perm/keyname/eepkey/" + str)) {
                    string4 = this.mContext.getResources().getString(R.string.str_wide_vine_key_yes);
                } else {
                    z = true;
                }
                if (!setAttestAndPlayreadyForEeprom()) {
                    z = true;
                }
            }
            if (str.startsWith("mac")) {
                string6 = setMacAddrFromEeprom("/perm/keyname/eepkey/" + str);
                String macAddress = mTVFunction.getMacAddress();
                if (macAddress.equals("NG") || "00:00:00:00:00:00".equals(macAddress)) {
                    z = true;
                }
            }
            i++;
            fileNameList = strArr;
        }
        String str2 = string6 + "\n" + string + "\n" + string2 + "\n" + string3 + "\n" + string4 + "\n" + string5;
        Log.i("Fac_MenuConfigManager", "setEepromKeyAfterEnterFac file errMsg = " + str2);
        if (!z) {
            mTVFunction.cleanEepKeyFile();
        }
        return str2;
    }

    public String setMacAddrFromEeprom(String str) {
        String string = this.mContext.getResources().getString(R.string.str_facmenu_setmacfail);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            String line = bufferedReader.readLine();
            ArrayList<String> arrayList = new ArrayList<>();
            if (line == null) {
                bufferedReader.close();
                return string;
            }
            Log.i("Fac_MenuConfigManager", "fileContent is " + line);
            arrayList.add(line);
            while (true) {
                String line2 = bufferedReader.readLine();
                if (line2 == null) {
                    break;
                }
                Log.i("Fac_MenuConfigManager", "fileContent is " + line2);
                arrayList.add(line2);
            }
            Log.i("Fac_MenuConfigManager", "strContentArraylist size is " + arrayList.size());
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                if (!setCountryAndLogoAndLanguageForSAEeprom(arrayList, null)) {
                    Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguageForSAEeprom return false ");
                    bufferedReader.close();
                    return string;
                }
                mTVFunction.setClientType(arrayList.get(4));
                arrayList.get(5);
            } else {
                if (!setCountryAndLogoAndLanguage(arrayList, null)) {
                    Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguage return false ");
                    bufferedReader.close();
                    return string;
                }
                arrayList.get(4);
            }
            String str2 = arrayList.get(0);
            arrayList.get(1);
            arrayList.get(2);
            arrayList.get(3);
            Log.i("Fac_MenuConfigManager", "set mac str=" + str2);
            bufferedReader.close();
            try {
                String str3 = str2.substring(0, 2) + str2.substring(3, 5) + str2.substring(6, 8) + str2.substring(9, 11) + str2.substring(12, 14) + str2.substring(15, 17);
                if (!setMacAddr(str2.substring(0, 2) + ":" + str2.substring(3, 5) + ":" + str2.substring(6, 8) + ":" + str2.substring(9, 11) + ":" + str2.substring(12, 14) + ":" + str2.substring(15, 17))) {
                    return string;
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return this.mContext.getResources().getString(R.string.str_facmenu_setmacsuccess);
            } catch (Exception unused) {
                return this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format);
            }
        } catch (IOException unused2) {
            return string;
        }
    }

    private boolean setCountryAndLogoAndLanguageForSAEeprom(ArrayList<String> arrayList, FacRootMenu.WriteCallback writeCallback) {
        String str;
        int i;
        int logoValueForSA;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int index = -1;
        if (arrayList != null && arrayList.size() == 7) {
            Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguageForSAEeprom start");
            int index2 = -1;
            z3 = false;
            for (FacPropertyDemo.CountryForRegion5 countryForRegion5 : FacPropertyDemo.CountryForRegion5.values()) {
                Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion5 =" + countryForRegion5.getName());
                if (countryForRegion5.getName().equalsIgnoreCase(arrayList.get(1))) {
                    index2 = countryForRegion5.getIndex();
                    z3 = true;
                }
            }
            if (!z3) {
                for (FacPropertyDemo.CountryForRegion6 countryForRegion6 : FacPropertyDemo.CountryForRegion6.values()) {
                    Log.i("Fac_MenuConfigManager", "ergodic CountryForRegion6 =" + countryForRegion6.getName());
                    if (countryForRegion6.getName().equalsIgnoreCase(arrayList.get(1))) {
                        index2 = countryForRegion6.getIndex();
                        z3 = true;
                    }
                }
            }
            if (z3) {
                Log.i("Fac_MenuConfigManager", "get Country = " + arrayList.get(1));
                String str2 = arrayList.get(2);
                logoValueForSA = mTVFunction.getLogoValueForSA(str2);
                if (logoValueForSA > 0) {
                    mTVFunction.setBrand(str2);
                    z5 = false;
                    for (FacPropertyDemo.LanguageForRegion5 languageForRegion5 : FacPropertyDemo.LanguageForRegion5.values()) {
                        if (languageForRegion5.getName().equalsIgnoreCase(arrayList.get(3))) {
                            index = languageForRegion5.getIndex();
                            z5 = true;
                        }
                    }
                    if (!z5) {
                        for (FacPropertyDemo.LanguageForRegion6 languageForRegion6 : FacPropertyDemo.LanguageForRegion6.values()) {
                            Log.i("Fac_MenuConfigManager", "ergodic LanguageForRegion6 =" + languageForRegion6.getName());
                            if (languageForRegion6.getName().equalsIgnoreCase(arrayList.get(3))) {
                                index = languageForRegion6.getIndex();
                                z5 = true;
                            }
                        }
                    }
                    if (z5) {
                        Log.i("Fac_MenuConfigManager", "get langName = " + arrayList.get(3));
                        str = arrayList.get(5);
                        boolean z6 = str != null;
                        Log.i("Fac_MenuConfigManager", "end file is " + arrayList.get(6));
                        boolean zEqualsIgnoreCase = arrayList.get(6).equalsIgnoreCase("#");
                        Log.i("Fac_MenuConfigManager", "mac addr length is " + arrayList.get(0).length() + ";  isCountryValid is " + z3 + ";  isLogoValid is true;   isLangValid is " + z5 + ";   isFileEndValid is " + zEqualsIgnoreCase);
                        z2 = zEqualsIgnoreCase;
                        z4 = true;
                        z = z6;
                        i = index;
                        index = index2;
                    }
                }
            }
            return false;
        }
        str = null;
        i = -1;
        logoValueForSA = -1;
        z = false;
        z2 = false;
        z3 = false;
        z4 = false;
        z5 = false;
        if (arrayList == null || arrayList.size() != 7 || arrayList.get(0).length() != 17 || !z3 || !z4 || !z5 || !z2 || !z) {
            Log.i("Fac_MenuConfigManager", "content format error!!");
            if (writeCallback == null) {
                displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format));
            }
            return false;
        }
        Log.i("Fac_MenuConfigManager", "countryIndex = " + index);
        setRegionForMac(index);
        setCountryIndex(index);
        setLogoIndex(logoValueForSA);
        setLangIndex(i);
        if (setProjectId(str) == 0) {
            return true;
        }
        if (writeCallback == null) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_content_bad_format));
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:144:0x0416 A[Catch: all -> 0x0403, TryCatch #1 {all -> 0x0403, blocks: (B:10:0x0018, B:142:0x0407, B:144:0x0416, B:145:0x041a), top: B:172:0x0018 }] */
    /* JADX WARN: Code duplicated, block: B:145:0x041a A[Catch: all -> 0x0403, TRY_LEAVE, TryCatch #1 {all -> 0x0403, blocks: (B:10:0x0018, B:142:0x0407, B:144:0x0416, B:145:0x041a), top: B:172:0x0018 }] */
    /* JADX WARN: Code duplicated, block: B:186:0x041f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public void setMacContent(FacRootMenu.WriteCallback writeCallback) {
        Throwable th;
        String string;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        boolean z;
        long j;
        synchronized (this.mObject) {
            File macFile = getMacFile(writeCallback);
            if (macFile == null || !macFile.exists()) {
                Log.i("Fac_MenuConfigManager", "isSetMacAddr file is not exit");
                if (writeCallback != null) {
                    writeCallback.writeEnd(false, this.mContext.getResources().getString(R.string.str_facmenu_macfilenotexist) + "\n" + mTVFunction.getAppointedMacFileName());
                }
                return;
            }
            BufferedReader bufferedReader = null;
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(macFile));
                    try {
                        try {
                            String line = bufferedReader2.readLine();
                            try {
                                if (line == null) {
                                    Log.i("Fac_MenuConfigManager", "fileContent is null");
                                    String string2 = this.mContext.getResources().getString(R.string.str_facmenu_macfileempty);
                                    if (writeCallback != null) {
                                        writeCallback.writeEnd(false, string2);
                                    } else {
                                        displayHisToast(string2);
                                    }
                                    bufferedReader2.close();
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return;
                                }
                                ArrayList<String> arrayList = new ArrayList<>();
                                Log.i("Fac_MenuConfigManager", "fileContent is " + line);
                                arrayList.add(line);
                                while (true) {
                                    String line2 = bufferedReader2.readLine();
                                    if (line2 == null) {
                                        break;
                                    }
                                    Log.i("Fac_MenuConfigManager", "fileContent is " + line2);
                                    arrayList.add(line2);
                                }
                                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                                    if (!setCountryAndLogoAndLanguageForSA(arrayList, null)) {
                                        Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguage return false ");
                                        bufferedReader2.close();
                                        if (writeCallback != null) {
                                            writeCallback.writeEnd(false, this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format));
                                        }
                                        try {
                                            bufferedReader2.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                        return;
                                    }
                                    str = arrayList.get(0);
                                    String str10 = arrayList.get(1);
                                    str3 = arrayList.get(2);
                                    str4 = arrayList.get(3);
                                    String str11 = arrayList.get(4);
                                    String str12 = arrayList.get(5);
                                    mTVFunction.setClientType(str11);
                                    str2 = str10;
                                    str6 = str12;
                                    str5 = str11;
                                    str9 = str4;
                                    str8 = str3;
                                    str7 = str;
                                } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                    if (!setCountryAndLogoAndLanguageForJP(arrayList, writeCallback)) {
                                        Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguageForJP return false ");
                                        String string3 = this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format);
                                        if (writeCallback != null) {
                                            writeCallback.writeEnd(false, string3);
                                        } else {
                                            displayHisToast(string3);
                                        }
                                        bufferedReader2.close();
                                        try {
                                            bufferedReader2.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                        }
                                        return;
                                    }
                                    str7 = arrayList.get(0);
                                    str2 = null;
                                    str8 = null;
                                    str9 = null;
                                    str5 = null;
                                    str6 = arrayList.get(1);
                                } else {
                                    if (!setCountryAndLogoAndLanguage(arrayList, writeCallback)) {
                                        Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguage return false ");
                                        String string4 = this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format);
                                        if (writeCallback != null) {
                                            writeCallback.writeEnd(false, string4);
                                        } else {
                                            displayHisToast(string4);
                                        }
                                        bufferedReader2.close();
                                        try {
                                            bufferedReader2.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                        return;
                                    }
                                    str = arrayList.get(0);
                                    str2 = arrayList.get(1);
                                    str3 = arrayList.get(2);
                                    str4 = arrayList.get(3);
                                    str5 = null;
                                    str6 = arrayList.get(4);
                                    str9 = str4;
                                    str8 = str3;
                                    str7 = str;
                                }
                                Log.i("Fac_MenuConfigManager", "set mac str=" + str7);
                                bufferedReader2.close();
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                                try {
                                    String str13 = str7.substring(0, 2) + str7.substring(3, 5) + str7.substring(6, 8) + str7.substring(9, 11) + str7.substring(12, 14) + str7.substring(15, 17);
                                    String str14 = str7.substring(0, 2) + ":" + str7.substring(3, 5) + ":" + str7.substring(6, 8) + ":" + str7.substring(9, 11) + ":" + str7.substring(12, 14) + ":" + str7.substring(15, 17);
                                    String str15 = str7.substring(19, 21) + str7.substring(22, 24) + str7.substring(25, 27) + str7.substring(28, 30) + str7.substring(31, 33) + str7.substring(34, 36);
                                    long j2 = Long.parseLong(str13, 16);
                                    Log.i("Fac_MenuConfigManager", "decimalContent1 = " + j2);
                                    long j3 = Long.parseLong(str15, 16);
                                    Log.i("Fac_MenuConfigManager", "decimalContent2 = " + j3);
                                    if (j2 > j3) {
                                        Log.i("Fac_MenuConfigManager", "wrong mac address range");
                                        String string5 = this.mContext.getResources().getString(R.string.str_facmenu_dataerror);
                                        if (writeCallback != null) {
                                            writeCallback.writeEnd(false, string5);
                                        } else {
                                            displayHisToast(string5);
                                        }
                                        return;
                                    }
                                    if (j2 <= j3) {
                                        Log.i("Fac_MenuConfigManager", "right mac address range" + str14);
                                        if (setMacAddr(str14)) {
                                            j = j2 + 1;
                                            z = true;
                                        } else {
                                            j = j2;
                                            z = false;
                                        }
                                        if (!setMacToUDisk(macFile, str7, j, str2, str8, str9, str6, str5, writeCallback)) {
                                            String string6 = this.mContext.getResources().getString(R.string.str_facmenu_writedataerror);
                                            if (writeCallback != null) {
                                                writeCallback.writeEnd(false, string6);
                                            } else {
                                                displayHisToast(string6);
                                            }
                                            return;
                                        }
                                    } else {
                                        z = false;
                                    }
                                    if (!z) {
                                        String str16 = str7;
                                        String string7 = this.mContext.getResources().getString(R.string.str_facmenu_setmacfail);
                                        if (writeCallback != null) {
                                            writeCallback.writeEnd(false, string7);
                                        } else {
                                            displayHisToast(string7);
                                        }
                                        Log.i("Fac_MenuConfigManager", "SET MAC " + str16.substring(0, 17) + " failed");
                                        return;
                                    }
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException e6) {
                                        e6.printStackTrace();
                                    }
                                    String string8 = this.mContext.getResources().getString(R.string.str_facmenu_setmacsuccess);
                                    if (writeCallback != null) {
                                        writeCallback.writeEnd(true, string8);
                                    } else {
                                        displayHisToast(string8);
                                    }
                                    Log.i("Fac_MenuConfigManager", "SET MAC " + str7.substring(0, 17) + " succeed");
                                    return;
                                } catch (Exception e7) {
                                    Log.i("Fac_MenuConfigManager", "get mac content exception " + e7);
                                    String string9 = this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format);
                                    if (writeCallback != null) {
                                        writeCallback.writeEnd(false, string9);
                                    } else {
                                        displayHisToast(string9);
                                    }
                                    return;
                                }
                            } catch (IOException unused) {
                                bufferedReader = bufferedReader2;
                                string = this.mContext.getResources().getString(R.string.str_facmenu_readmacerror);
                                if (writeCallback != null) {
                                    writeCallback.writeEnd(true, string);
                                } else {
                                    displayHisToast(string);
                                }
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e8) {
                                        e8.printStackTrace();
                                    }
                                }
                                return;
                            }
                        } catch (IOException unused2) {
                            bufferedReader = bufferedReader2;
                            string = this.mContext.getResources().getString(R.string.str_facmenu_readmacerror);
                            if (writeCallback != null) {
                                writeCallback.writeEnd(true, string);
                            } else {
                                displayHisToast(string);
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader == null) {
                            throw th;
                        }
                        try {
                            bufferedReader.close();
                            throw th;
                        } catch (IOException e9) {
                            e9.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException unused3) {
            }
            throw th;
        }
    }

    public void updateFacLogStatus(int i) {
        mTVFunction.updateFacLogStatus(i);
    }

    public boolean isHaveRegion() {
        if (!FacPropertyDemo.HIFAC_MARKET_TW && !FacPropertyDemo.HIFAC_MARKET_HK) {
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM) || FacPropertyDemo.HIFAC_MARKET_SA) {
                return true;
            }
            FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.US);
        }
        return false;
    }

    public boolean isBrandIsolate() {
        return mTVFunction.isBrandIsolate();
    }

    public String[] geBrandIsolateList() {
        return mTVFunction.geBrandIsolateList();
    }

    public void setChannelInitFileFromUdisk() throws Throwable {
        String strFindChannelInitFileOnUSB = findChannelInitFileOnUSB();
        if (!new File(strFindChannelInitFileOnUSB).exists()) {
            displayHisToast("can not find ChannelInit");
        } else if (mTVFunction.setChannelInitFileFromUdisk(strFindChannelInitFileOnUSB)) {
            displayHisToast("ChannelInit OK");
        } else {
            displayHisToast("ChannelInit NG");
        }
    }

    public String findChannelInitFileOnUSB() throws Throwable {
        String str = mTVFunction.getProductTypeName() + "ChannelInit.db";
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "ChannelInitFile list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "ChannelInit file list is null");
        }
        String absolutePath = "";
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file = new File(it.next() + "/" + str);
                StringBuilder sb = new StringBuilder();
                sb.append("ChannelInit file name is ");
                sb.append(file);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file.exists()) {
                    absolutePath = file.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find ChannelInit file " + absolutePath);
                } else {
                    Log.i("Fac_MenuConfigManager", "can't find ChannelInit file" + file);
                }
            }
        } else {
            Log.i("Fac_MenuConfigManager", "ChannelInit file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " ChannelInit filepath=" + absolutePath);
        return absolutePath;
    }

    public void updateSscDefaultValueForTemp() {
        mTVFunction.updateSscDefaultValueForTemp();
    }

    public String get5GChannel() {
        return mTVFunction.get5GChannel();
    }

    public boolean isStartFromTiXiao() {
        return SystemProperties.getInt("ro.boot.bootenterfactory", 0) == 1 && SystemProperties.getInt("sys.vt.after_factory_reset", 0) == 1;
    }

    public boolean isStartInFacMode() {
        return 1 == SystemProperties.getInt("ro.boot.bootenterfactory", 0);
    }

    public enum LocalDimmingStatus {
        Off,
        OddColumnsBright,
        EvenColumnsBright;

        public static LocalDimmingStatus getName(int i) {
            LocalDimmingStatus localDimmingStatus = Off;
            if (i == 0) {
                return localDimmingStatus;
            }
            if (i != 1) {
                return i != 2 ? localDimmingStatus : EvenColumnsBright;
            }
            return OddColumnsBright;
        }
    }

    public void adjustLocalDimmingStatus() {
        int iOrdinal = this.mLocalDimmingStatus.ordinal();
        this.mLocalDimmingStatus = LocalDimmingStatus.getName(iOrdinal == LocalDimmingStatus.EvenColumnsBright.ordinal() ? LocalDimmingStatus.Off.ordinal() : iOrdinal + 1);
    }

    public int getVcomVoltage2(int i) {
        return mTVFunction.getVcomVoltage2(i);
    }

    public boolean saveVcomVoltage2(int i, int i2) {
        return mTVFunction.saveVcomVoltage2(i, i2);
    }

    public boolean setVcomVoltage2(int i, int i2) {
        return mTVFunction.setVcomVoltage2(i, i2);
    }

    public synchronized void setVcomType(int i) {
        new Throwable().printStackTrace();
        Log.i("Fac_MenuConfigManager", " setVcomType mVcomType=" + this.mVcomType);
        this.mVcomType = i;
    }

    public synchronized int getVcomType() {
        Log.i("Fac_MenuConfigManager", " getVcomType mVcomType=" + this.mVcomType);
        return this.mVcomType;
    }

    public boolean setVcomPanelType(int i) {
        return mTVFunction.setVcomPanelType(i);
    }

    public String getDolbyIqUpdateFile() throws Throwable {
        String strFindFileOnUSB = FindFileOnUSB(FacParamManager.HIFAC_DOLBY_UPDATE_FILENAME);
        Log.i("Fac_MenuConfigManager", "getDolbyUpdateFilePath path = " + strFindFileOnUSB);
        return strFindFileOnUSB;
    }

    public boolean isHaveTuner() {
        return mTVFunction.isHaveTuner();
    }

    public String getSlaveBacklightDriverVersion() {
        return mTVFunction.getSlaveBacklightDriverVersion();
    }

    public String getSlaveLCMRunningStatus() {
        String slaveLCMRunningStatus = mTVFunction.getSlaveLCMRunningStatus();
        return (slaveLCMRunningStatus == null || slaveLCMRunningStatus.isEmpty() || "ff".equals(slaveLCMRunningStatus)) ? "NG" : "OK";
    }

    public boolean isDualBluSupport() {
        return mTVFunction.isDualBluSupport();
    }

    public String getDolbyIQUserBinInfo() {
        return mTVFunction.getDolbyIQUserBinInfo();
    }

    public void readDolbyUserBinInfo() {
        String dolbyIQUserBinInfo = getDolbyIQUserBinInfo();
        if (dolbyIQUserBinInfo.equalsIgnoreCase("N/A")) {
            this.dolbyIQUserBinSizeInfo = dolbyIQUserBinInfo;
            this.dolbyIQUserBinSizeInfo = dolbyIQUserBinInfo;
        } else {
            this.dolbyIQUserBinTimeInfo = dolbyIQUserBinInfo.substring(0, 20);
            this.dolbyIQUserBinSizeInfo = dolbyIQUserBinInfo.substring(21);
        }
    }

    public String getDolbyIQUserBinSizeInfo() {
        readDolbyUserBinInfo();
        Log.i("Fac_MenuConfigManager", "dolbyIQUserBinSizeInfo:" + this.dolbyIQUserBinSizeInfo);
        return this.dolbyIQUserBinSizeInfo;
    }

    public String getDolbyIQUserBinTimeInfo() {
        readDolbyUserBinInfo();
        Log.i("Fac_MenuConfigManager", "dolbyIQUserBinTimeInfo:" + this.dolbyIQUserBinTimeInfo);
        return this.dolbyIQUserBinTimeInfo;
    }

    public void setDTCPIPKeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.27
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindDTCPIPPathOnUSB = MenuConfigManager.this.findDTCPIPPathOnUSB();
                if (strFindDTCPIPPathOnUSB == null || strFindDTCPIPPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_dtcpip_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setDTCPIPKeyFromUDiskWithCB file path=" + strFindDTCPIPPathOnUSB);
                    File file = new File(strFindDTCPIPPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_DTCPIP_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.27.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolDTCPIPKeyNameValid(name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("bin");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  dtcpip file name is     " + string2);
                            boolean dTCPIPKey = MenuConfigManager.mTVFunction.setDTCPIPKey(string2);
                            Log.i("Fac_MenuConfigManager", "the  dtcpip returns      " + dTCPIPKey);
                            if (dTCPIPKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getDtcpIpKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET dtcpip KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_dtcpip_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete dtcpip key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_dtcpip_key_fail);
                                    } else {
                                        String string3 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_dtcpip_key_yes);
                                        FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                                        if (writeCallback2 != null) {
                                            writeCallback2.writeEnd(true, string3);
                                            return;
                                        } else {
                                            MenuConfigManager.this.displayHisToast(string3);
                                            return;
                                        }
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_dtcpip_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_dtcpip_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_dtcpip_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                FacRootMenu.WriteCallback writeCallback3 = writeCallback;
                if (writeCallback3 != null) {
                    writeCallback3.writeEnd(false, string);
                } else {
                    MenuConfigManager.this.displayHisToast(string);
                }
            }
        }).start();
    }

    public void setDTCPIPKeyFromUDisk() {
        setDTCPIPKeyFromUDiskWithCB(null);
    }

    public String findDTCPIPPathOnUSB() throws Throwable {
        DTCPIP_ROOTDIR = FacPropertyDemo.HIFAC_DTCPIP_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + DTCPIP_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            Log.i("Fac_MenuConfigManager", "findDTCPIPPathOnUSB return ");
            return "/mnt/media_rw//" + DTCPIP_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "dtcpip list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "dtcpip file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + DTCPIP_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("dtcpip file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find dtcpip file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find dtcpip file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "dtcpip file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " dtcpip filepath=" + ((String) null));
        return null;
    }

    public boolean isAntiFoolDTCPIPKeyNameValid(String str) {
        Log.d("Fac_MenuConfigManager", "isAntiFoolDTCPIPKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", "DTCPIP file name invalid: file name is null");
        } else {
            String str2 = FacPropertyDemo.HIFAC_DTCPIP_KEY_FILENAME_PREFIX;
            if (!str.startsWith(str2)) {
                Log.e("Fac_MenuConfigManager", "DTCPIP file name invalid: prefix not match");
            } else if (str.length() != 27) {
                Log.e("Fac_MenuConfigManager", "DTCPIP file name invalid: length not match, fileName.length()= " + str.length() + ", length = 27");
            } else if (!str.toLowerCase().endsWith(".bin")) {
                Log.e("Fac_MenuConfigManager", "DTCPIP file name invalid: suffix not match");
            } else {
                try {
                    long j = Long.parseLong(str.substring(0, str.length() - 4).replaceFirst(str2, ""));
                    if (j >= 1) {
                        return true;
                    }
                    Log.e("Fac_MenuConfigManager", "DTCPIP file name invalid: index error, " + j);
                } catch (Exception e) {
                    Log.e("HisFactory", "DTCPIP file name invalid", e);
                }
            }
        }
        return false;
    }

    public void setMarlinKeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.28
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindMarlinPathOnUSB = MenuConfigManager.this.findMarlinPathOnUSB();
                if (strFindMarlinPathOnUSB == null || strFindMarlinPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_marlin_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setMarlinKeyFromUDiskWithCB file path=" + strFindMarlinPathOnUSB);
                    File file = new File(strFindMarlinPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_DTCPIP_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.28.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolMarlinKeyNameValid(name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("bin");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  marlin file name is     " + string2);
                            boolean marlinKey = MenuConfigManager.mTVFunction.setMarlinKey(string2);
                            Log.i("Fac_MenuConfigManager", "the  marlin returns      " + marlinKey);
                            if (marlinKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getMarlinKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET marlin KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_marlin_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete marlin key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_marlin_key_fail);
                                    } else {
                                        String string3 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_marlin_key_yes);
                                        FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                                        if (writeCallback2 != null) {
                                            writeCallback2.writeEnd(true, string3);
                                            return;
                                        } else {
                                            MenuConfigManager.this.displayHisToast(string3);
                                            return;
                                        }
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_marlin_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_marlin_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_marlin_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                FacRootMenu.WriteCallback writeCallback3 = writeCallback;
                if (writeCallback3 != null) {
                    writeCallback3.writeEnd(false, string);
                } else {
                    MenuConfigManager.this.displayHisToast(string);
                }
            }
        }).start();
    }

    public void setMarlinKeyFromUDisk() {
        setMarlinKeyFromUDiskWithCB(null);
    }

    public String findMarlinPathOnUSB() throws Throwable {
        DTCPIP_ROOTDIR = FacPropertyDemo.HIFAC_MARLIN_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + MARLIN_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            Log.i("Fac_MenuConfigManager", "findMarlinPathOnUSB return ");
            return "/mnt/media_rw//" + MARLIN_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "marlin list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "marlin file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + MARLIN_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("marlin file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find marlin file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find marlin file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "marlin file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " marlin filepath=" + ((String) null));
        return null;
    }

    public boolean isAntiFoolMarlinKeyNameValid(String str) {
        Log.d("Fac_MenuConfigManager", "isAntiFoolMarlinKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", "Marlin file name invalid: file name is null");
        } else {
            String str2 = FacPropertyDemo.HIFAC_MARLIN_KEY_FILENAME_PREFIX;
            if (!str.startsWith(str2)) {
                Log.e("Fac_MenuConfigManager", "Marlin file name invalid: prefix not match");
            } else if (str.length() != 27) {
                Log.e("Fac_MenuConfigManager", "Marlin file name invalid: length not match, fileName.length()= " + str.length() + ", length = 27");
            } else if (!str.toLowerCase().endsWith(".bin")) {
                Log.e("Fac_MenuConfigManager", "Marlin file name invalid: suffix not match");
            } else {
                try {
                    long j = Long.parseLong(str.substring(0, str.length() - 4).replaceFirst(str2, ""));
                    if (j >= 1) {
                        return true;
                    }
                    Log.e("Fac_MenuConfigManager", "Marlin file name invalid: index error, " + j);
                } catch (Exception e) {
                    Log.e("HisFactory", "Marlin file name invalid", e);
                }
            }
        }
        return false;
    }

    public void setSQVKeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.29
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindSQVPathOnUSB = MenuConfigManager.this.findSQVPathOnUSB();
                if (strFindSQVPathOnUSB == null || strFindSQVPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_sqv_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setSQVKeyFromUDiskWithCB file path=" + strFindSQVPathOnUSB);
                    File file = new File(strFindSQVPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_SQV_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.29.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolSQVKeyNameValid(name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("bin");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  sqv file name is     " + string2);
                            boolean sQVKey = MenuConfigManager.mTVFunction.setSQVKey(string2);
                            Log.i("Fac_MenuConfigManager", "the  sqv returns      " + sQVKey);
                            if (sQVKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getSeeQVaultKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET sqv KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_sqv_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete sqv key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_sqv_key_fail);
                                    } else {
                                        String string3 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_sqv_key_yes);
                                        FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                                        if (writeCallback2 != null) {
                                            writeCallback2.writeEnd(true, string3);
                                            return;
                                        } else {
                                            MenuConfigManager.this.displayHisToast(string3);
                                            return;
                                        }
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_sqv_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_sqv_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_sqv_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                FacRootMenu.WriteCallback writeCallback3 = writeCallback;
                if (writeCallback3 != null) {
                    writeCallback3.writeEnd(false, string);
                } else {
                    MenuConfigManager.this.displayHisToast(string);
                }
            }
        }).start();
    }

    public void setSQVKeyFromUDisk() {
        setSQVKeyFromUDiskWithCB(null);
    }

    public String findSQVPathOnUSB() throws Throwable {
        SQV_ROOTDIR = FacPropertyDemo.HIFAC_SQV_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + SQV_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            Log.i("Fac_MenuConfigManager", "findSQVPathOnUSB return ");
            return "/mnt/media_rw//" + SQV_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "SQV list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "SQV file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + SQV_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("SQV file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find SQV file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find SQV file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "SQV file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " SQV filepath=" + ((String) null));
        return null;
    }

    public boolean isAntiFoolSQVKeyNameValid(String str) {
        Log.d("Fac_MenuConfigManager", "isAntiFoolSQVKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", "SQV file name invalid: file name is null");
        } else {
            String str2 = FacPropertyDemo.HIFAC_SQV_KEY_FILENAME_PREFIX;
            if (!str.startsWith(str2)) {
                Log.e("Fac_MenuConfigManager", "SQV file name invalid: prefix not match");
            } else if (str.length() != 27) {
                Log.e("Fac_MenuConfigManager", "SQV file name invalid: length not match, fileName.length()= " + str.length() + ", length = 27");
            } else if (!str.toLowerCase().endsWith(".bin")) {
                Log.e("Fac_MenuConfigManager", "SQV file name invalid: suffix not match");
            } else {
                try {
                    long j = Long.parseLong(str.substring(0, str.length() - 4).replaceFirst(str2, ""));
                    if (j >= 1) {
                        return true;
                    }
                    Log.e("Fac_MenuConfigManager", "SQV file name invalid: index error, " + j);
                } catch (Exception e) {
                    Log.e("HisFactory", "SQV file name invalid", e);
                }
            }
        }
        return false;
    }

    public void setPVRKeyFromUDiskWithCB(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.30
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindPVRPathOnUSB = MenuConfigManager.this.findPVRPathOnUSB();
                if (strFindPVRPathOnUSB == null || strFindPVRPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_pvr_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setPVRKeyFromUDiskWithCB file path=" + strFindPVRPathOnUSB);
                    File file = new File(strFindPVRPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_PVR_KEY_FILENAME_PREFIX);
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.30.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolPVRKeyNameValid(name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("bin");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  pvr file name is     " + string2);
                            boolean pVRKey = MenuConfigManager.mTVFunction.setPVRKey(string2);
                            Log.i("Fac_MenuConfigManager", "the  pvr returns      " + pVRKey);
                            if (pVRKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getPVRKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET pvr KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_pvr_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete pvr key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_pvr_key_fail);
                                    } else {
                                        String string3 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_pvr_key_yes);
                                        FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                                        if (writeCallback2 != null) {
                                            writeCallback2.writeEnd(true, string3);
                                            return;
                                        } else {
                                            MenuConfigManager.this.displayHisToast(string3);
                                            return;
                                        }
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_pvr_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_pvr_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_pvr_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                FacRootMenu.WriteCallback writeCallback3 = writeCallback;
                if (writeCallback3 != null) {
                    writeCallback3.writeEnd(false, string);
                } else {
                    MenuConfigManager.this.displayHisToast(string);
                }
            }
        }).start();
    }

    public void setPVRKeyFromUDisk() {
        setPVRKeyFromUDiskWithCB(null);
    }

    public String findPVRPathOnUSB() throws Throwable {
        PVR_ROOTDIR = FacPropertyDemo.HIFAC_PVR_KEY_FILENAME + "/";
        File file = new File("/mnt/media_rw//" + PVR_ROOTDIR);
        if (file.isDirectory() && file.exists()) {
            Log.i("Fac_MenuConfigManager", "findPVRPathOnUSB return ");
            return "/mnt/media_rw//" + PVR_ROOTDIR;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "PVR list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "PVR file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + PVR_ROOTDIR);
                StringBuilder sb = new StringBuilder();
                sb.append("PVR file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find PVR file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find PVR file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "PVR file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " PVR filepath=" + ((String) null));
        return null;
    }

    public boolean isAntiFoolPVRKeyNameValid(String str) {
        Log.d("Fac_MenuConfigManager", "isAntiFoolPVRKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", "PVR file name invalid: file name is null");
        } else {
            String str2 = FacPropertyDemo.HIFAC_PVR_KEY_FILENAME_PREFIX;
            if (!str.startsWith(str2)) {
                Log.e("Fac_MenuConfigManager", "PVR file name invalid: prefix not match");
            } else if (str.length() != 27) {
                Log.e("Fac_MenuConfigManager", "PVR file name invalid: length not match, fileName.length()= " + str.length() + ", length = 27");
            } else if (!str.toLowerCase().endsWith(".bin")) {
                Log.e("Fac_MenuConfigManager", "PVR file name invalid: suffix not match");
            } else {
                try {
                    long j = Long.parseLong(str.substring(0, str.length() - 4).replaceFirst(str2, ""));
                    if (j >= 1) {
                        return true;
                    }
                    Log.e("Fac_MenuConfigManager", "PVR file name invalid: index error, " + j);
                } catch (Exception e) {
                    Log.e("HisFactory", "PVR file name invalid", e);
                }
            }
        }
        return false;
    }

    public void isSetDTCPIPKey(Context context) {
        if (mTVFunction.getDtcpIpKey().equals("NG")) {
            mConfigManager.setDTCPIPKeyFromUDisk();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show dtcpip warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_dtcpip_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.31
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setDTCPIPKeyFromUDisk();
            }
        });
        builder.create().show();
    }

    public void isSetMarlinKey(Context context) {
        if (mTVFunction.getMarlinKey().equals("NG")) {
            mConfigManager.setMarlinKeyFromUDisk();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show marlin warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_marlin_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.32
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setMarlinKeyFromUDisk();
            }
        });
        builder.create().show();
    }

    public void isSetSQVKey(Context context) {
        if (mTVFunction.getDtcpIpKey().equals("NG")) {
            mConfigManager.setSQVKeyFromUDisk();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show sqv warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_sqv_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.33
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setSQVKeyFromUDisk();
            }
        });
        builder.create().show();
    }

    public void isSetPVRKey(Context context) {
        if (mTVFunction.getPVRKey().equals("NG")) {
            mConfigManager.setPVRKeyFromUDisk();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show pvr warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_pvr_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.34
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setPVRKeyFromUDisk();
            }
        });
        builder.create().show();
    }

    public String getAutoGammaStatus() {
        return mTVFunction.getAutoGammaStatus();
    }

    public void setTvsTvTuner(int i) {
        mTVFunction.setTvsTvTuner(i);
    }

    public String getF1Result() {
        return mTVFunction.getF1Result();
    }

    private boolean setCountryAndLogoAndLanguageForJP(ArrayList<String> arrayList, FacRootMenu.WriteCallback writeCallback) {
        String str;
        boolean z;
        boolean z2;
        if (arrayList == null || arrayList.size() != 3) {
            str = null;
            z = false;
            z2 = false;
        } else {
            Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguageForJP ergodic========");
            str = arrayList.get(1);
            z = str != null;
            Log.i("Fac_MenuConfigManager", "end file is " + arrayList.get(2));
            z2 = arrayList.get(2).equalsIgnoreCase("#");
        }
        Log.i("Fac_MenuConfigManager", "mac addr length is " + arrayList.get(0).length());
        if (arrayList == null || arrayList.size() != 3 || arrayList.get(0).length() != 36 || !z2 || !z) {
            Log.i("Fac_MenuConfigManager", "setCountryAndLogoAndLanguageForJP content format error!!");
            if (writeCallback == null) {
                displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_mac_file_content_bad_format));
            }
            return false;
        }
        if (setProjectId(str) == 0) {
            return true;
        }
        if (writeCallback == null) {
            displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_projectid_file_content_bad_format));
        }
        return false;
    }

    private String getCurrentVersionYear(String str) {
        Log.i("Fac_MenuConfigManager", "getCurrentVersionYear year = " + str);
        int iIntValue = Integer.valueOf(str).intValue();
        Log.i("Fac_MenuConfigManager", "getCurrentVersionYear yearInt = " + iIntValue);
        int i = (iIntValue + (-2010)) % 26;
        Log.i("Fac_MenuConfigManager", "getCurrentVersionYear yearIndex = " + i);
        char c = (char) (i + 65);
        Log.i("Fac_MenuConfigManager", "getCurrentVersionYear yearChar = " + c);
        String strValueOf = String.valueOf(c);
        Log.i("Fac_MenuConfigManager", "getCurrentVersionYear yearString = " + strValueOf);
        return strValueOf;
    }

    public int convertDipToPix(int i) {
        Log.i("Fac_MenuConfigManager", "convertDipToPix dip = " + i);
        float f = this.mContext.getResources().getDisplayMetrics().density;
        Log.i("Fac_MenuConfigManager", "convertDipToPix density = " + f);
        int i2 = (int) ((((float) i) * f) + 0.5f);
        Log.i("Fac_MenuConfigManager", "convertDipToPix value = " + i2);
        return i2;
    }

    public String getACASId() {
        return mTVFunction.getACASId();
    }

    public String getHDDSize() {
        return mTVFunction.getHDDSize();
    }

    public int getACASState() {
        return mTVFunction.getACASState();
    }

    public void setUsbOverCurrent() {
        mTVFunction.setUSBOverCurrent();
        displayHisToast("Set USB Done!");
    }

    public int getSubWooferTotal() {
        return mTVFunction.getSubWooferTotal();
    }

    public void setTVSLog() {
        mTVFunction.setTVSLog(getCurrentUSBPath());
    }

    public String getCurrentUSBPath() {
        List<USBUtils.UsbPathAndLabel> usbPath = USBUtils.getUsbPath(this.mContext);
        if (usbPath.size() < 1) {
            Log.e("Fac_MenuConfigManager", "no usb detected");
            return "";
        }
        String internalPath = usbPath.get(0).getInternalPath();
        Log.e("Fac_MenuConfigManager", "getCurrentUSBPath filePath = " + internalPath);
        return internalPath;
    }

    public void clearOMRunningTime(Context context) {
        String string;
        if (mTVFunction.clearOMRunningTime()) {
            string = this.mContext.getResources().getString(R.string.str_factorymenu_success_clear_omrunningtime);
        } else {
            string = this.mContext.getResources().getString(R.string.str_factorymenu_fail_clear_omrunningtime);
        }
        displayHisToast(string);
    }

    public void setAGC(Context context) {
        Log.e("Fac_MenuConfigManager", "set AGC: isAGCSuccess = " + mTVFunction.setLaserAutoGraphCorrection(new int[]{28, 35, 43, 29, 33, 3, 38, 0, -6, 0, 1, -2}));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show setAGC warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_set_agc_title);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.35
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("Fac_MenuConfigManager", "set AGC : ResetAGCResult = " + MenuConfigManager.mTVFunction.resetGraphCorrection());
            }
        });
        builder.create().show();
    }

    public void calibrationOfBackgroundNoise(Context context) {
        String string;
        mTVFunction.setBackgroundNoiseValue(0);
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int distanceSensorStrengthValue = mTVFunction.getDistanceSensorStrengthValue();
        boolean z = distanceSensorStrengthValue <= mTVFunction.getBackgroundNoisePredeterminedValue();
        mTVFunction.setBackgroundNoiseValue((int) (distanceSensorStrengthValue * mTVFunction.getBackgroundNoiseCorrectionFactorValue()));
        if (z) {
            string = this.mContext.getResources().getString(R.string.str_factorymenu_success_calibration);
        } else {
            string = this.mContext.getResources().getString(R.string.str_factorymenu_fail_calibration);
        }
        displayHisToast(string);
    }

    public int getDistanceSensorStrengthValue() {
        return mTVFunction.getDistanceSensorStrengthValue();
    }

    public int getBackgroundNoiseValue() {
        return mTVFunction.getBackgroundNoiseValue();
    }

    public String getLaserRuningState() {
        return mTVFunction.getLaserRuningState();
    }

    public String getLaserRuningTime() {
        return mTVFunction.getLaserRuningTime();
    }

    public String getFirstPowerOnTime() {
        return mTVFunction.getFirstPowerOnTime();
    }

    public String getStartOnCount() {
        return mTVFunction.getStartOnCount();
    }

    public String getTvsLightSensor() {
        return mTVFunction.getTvsLightSensor();
    }

    public int getLastSource() {
        return mTVFunction.getLastSource();
    }

    public int setTVSSavingMode() {
        return mTVFunction.setTVSSavingMode();
    }

    public void setMtkSourceStatus(boolean z) {
        mTVFunction.setMtkSourceStatus(z);
    }

    public int getTvsCurrentChannelNumber() {
        return mTVFunction.getTvsCurrentChannelNumber();
    }

    public String getTvsCurrentChannelTuner() {
        return mTVFunction.getTvsCurrentChannelTuner();
    }

    public boolean isHaveHDD() {
        return mTVFunction.isHaveHDD();
    }

    public boolean setMajorMaintenance() {
        return mTVFunction.setMajorMaintenance();
    }

    public boolean setMinorMaintenance() {
        return mTVFunction.setMinorMaintenance();
    }

    public int getJBExecuteCount() {
        return mTVFunction.getJBExecuteCount();
    }

    public int getOffrsExecuteCount() {
        return mTVFunction.getOffrsExecuteCount();
    }

    public String getJBExecuteTime() {
        return mTVFunction.getJBExecuteTime();
    }

    public String getOffrsExecuteTime() {
        return mTVFunction.getOffrsExecuteTime();
    }

    public int getErrDetCount() {
        return mTVFunction.getErrDetCount();
    }

    public void setModelSelection(int i) {
        mTVFunction.setModelSelection(i);
    }

    public int setMuteColor(int i) {
        return mTVFunction.setMuteColor(i);
    }

    public boolean isFileExit(String str) {
        return mTVFunction.isFileExit(str);
    }

    public int setTwelveVoltsStatus(int i) {
        return mTVFunction.setTwelveVoltsStatus(i);
    }

    public int initInnertconBoard() {
        return mTVFunction.initInnertconBoard();
    }

    public String findQDAPathOnUSB() throws Throwable {
        String qDAFolderName = getQDAFolderName();
        File file = new File("/mnt/media_rw//" + qDAFolderName);
        if (file.isDirectory() && file.exists()) {
            Log.i("Fac_MenuConfigManager", "qda file get");
            return "/mnt/media_rw//" + qDAFolderName;
        }
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null) {
            for (int i = 0; i < externalStoragePath.size(); i++) {
                Log.i("Fac_MenuConfigManager", "qda list [" + i + "] is " + externalStoragePath.get(i));
            }
        } else {
            Log.i("Fac_MenuConfigManager", "qda file list is null");
        }
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file2 = new File(it.next() + "/" + qDAFolderName);
                StringBuilder sb = new StringBuilder();
                sb.append("qda file name is ");
                sb.append(file2);
                Log.i("Fac_MenuConfigManager", sb.toString());
                if (file2.isDirectory() && file2.exists()) {
                    String absolutePath = file2.getAbsolutePath();
                    Log.i("Fac_MenuConfigManager", "find qda file " + absolutePath);
                    if (absolutePath.endsWith("/")) {
                        return absolutePath;
                    }
                    return absolutePath + "/";
                }
                Log.i("Fac_MenuConfigManager", "can't find qda file" + file2);
            }
        } else {
            Log.i("Fac_MenuConfigManager", "qda file list is null or is empty");
        }
        Log.i("Fac_MenuConfigManager", " qda filepath=" + ((String) null));
        return null;
    }

    public boolean isAntiFoolQDAKeyNameValid(String str) {
        Log.d("Fac_MenuConfigManager", "isAntiFoolQDAKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Log.e("Fac_MenuConfigManager", "QDA file name invalid: file name is null");
            return false;
        }
        String qDAName = getQDAName();
        Log.e("Fac_MenuConfigManager", " isAntiFoolQDAKeyNameValid prefix = " + qDAName);
        if (!str.toUpperCase().startsWith(qDAName)) {
            Log.e("Fac_MenuConfigManager", "QDA file name invalid: prefix not match");
            return false;
        }
        if (str.toLowerCase().endsWith(".bin")) {
            return true;
        }
        Log.e("Fac_MenuConfigManager", "QDA file name invalid: suffix not match");
        return false;
    }

    public void setQDAKeyFromUDisk(final FacRootMenu.WriteCallback writeCallback) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.36
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                String string;
                String strFindQDAPathOnUSB = MenuConfigManager.this.findQDAPathOnUSB();
                if (strFindQDAPathOnUSB == null || strFindQDAPathOnUSB.length() <= 0) {
                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_qda_key_no);
                } else {
                    Log.i("Fac_MenuConfigManager", " setQDAKeyFromUDisk file path=" + strFindQDAPathOnUSB);
                    File file = new File(strFindQDAPathOnUSB);
                    if (file.isDirectory() && file.exists()) {
                        final boolean z = !TextUtils.isEmpty(MenuConfigManager.this.getQDAName());
                        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.uifac.menu.util.MenuConfigManager.36.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file2) {
                                if (!file2.isFile()) {
                                    return false;
                                }
                                String name = file2.getName();
                                if (z) {
                                    return MenuConfigManager.this.isAntiFoolQDAKeyNameValid(name);
                                }
                                return !TextUtils.isEmpty(name) && name.endsWith("enc");
                            }
                        });
                        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                            String string2 = fileArrListFiles[0].toString();
                            Log.i("Fac_MenuConfigManager", "the  qda file name is     " + string2);
                            boolean qDAKey = MenuConfigManager.mTVFunction.setQDAKey(string2);
                            Log.i("Fac_MenuConfigManager", "qda returns =  " + qDAKey);
                            if (qDAKey) {
                                MenuConfigManager.mSetDeviceIDStatus = "set_device_id_success";
                                if ("NG".equalsIgnoreCase(MenuConfigManager.this.getQDAKey())) {
                                    Log.i("Fac_MenuConfigManager", "GET qda KEY FORM LOWER LAYER IS NULL!!!!!!!!!!!!!!!!!!!!!!!");
                                    string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_qda_key_fail);
                                } else {
                                    int iRemoveFile = MenuConfigManager.mTVFunction.removeFile(string2);
                                    Log.i("Fac_MenuConfigManager", "delete qda key, path is " + string2 + " result is " + iRemoveFile);
                                    if (iRemoveFile != 0) {
                                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_qda_key_fail);
                                    } else {
                                        String string3 = MenuConfigManager.this.mContext.getResources().getString(R.string.str_qda_key_yes);
                                        FacRootMenu.WriteCallback writeCallback2 = writeCallback;
                                        if (writeCallback2 == null) {
                                            MenuConfigManager.this.displayHisToast(string3);
                                            return;
                                        } else {
                                            writeCallback2.writeEnd(true, string3);
                                            return;
                                        }
                                    }
                                }
                            } else {
                                string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_qda_key_fail);
                            }
                        } else {
                            string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_qda_key_no);
                        }
                    } else {
                        string = MenuConfigManager.this.mContext.getResources().getString(R.string.str_qda_key_no);
                    }
                }
                if (string == null) {
                    string = "";
                }
                FacRootMenu.WriteCallback writeCallback3 = writeCallback;
                if (writeCallback3 == null) {
                    MenuConfigManager.this.displayHisToast(string);
                } else {
                    writeCallback3.writeEnd(false, string);
                }
            }
        }).start();
    }

    public void isSetQDAKey(Context context) {
        if (mTVFunction.getQDAKey().equals("NG")) {
            mConfigManager.setQDAKeyFromUDisk(null);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show isSetQDAKey warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_qda_key_ready_exist);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.37
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MenuConfigManager.mConfigManager.setQDAKeyFromUDisk(null);
            }
        });
        builder.create().show();
    }

    public boolean isSupportQDAKey() {
        String logoName = getLogoName();
        Log.i("Fac_MenuConfigManager", "isSupportQDAKey, LOGO = " + logoName);
        if ("his".equalsIgnoreCase(logoName) || "tos".equalsIgnoreCase(logoName) || "lca".equalsIgnoreCase(logoName)) {
            Log.i("Fac_MenuConfigManager", "isSupportQDAKey, result = true");
            return true;
        }
        Log.i("Fac_MenuConfigManager", "isSupportQDAKey, result = false");
        return false;
    }

    public String getQDAUUID() {
        return mTVFunction.getQDAUUID();
    }

    public String getLogoFullName() {
        return mTVFunction.getLogoFullName();
    }

    public boolean isHavePermKey() {
        String[] fileNameList = FacFileUtils.getFileNameList("/perm/keyname/eepkey/");
        if (fileNameList != null && fileNameList.length != 0) {
            return true;
        }
        Log.i("Fac_MenuConfigManager", "isHavePermKey file is null");
        return false;
    }

    public int getTvsTPC() {
        return mTVFunction.getTvsTPC();
    }

    public boolean getEmmcType() {
        return mTVFunction.getEmmcType();
    }

    public boolean setServiceTag(FacRootMenu.WriteCallback writeCallback) throws Throwable {
        int length;
        File sTFile = getSTFile(writeCallback);
        if (sTFile == null || !sTFile.exists()) {
            Log.i("Fac_MenuConfigManager", "setServiceTag file is not exit");
            if (writeCallback != null) {
                writeCallback.writeEnd(false, this.mContext.getResources().getString(R.string.str_facmenu_stfilenotexist) + "\nservice_tag");
            }
            return false;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(sTFile));
            String line = bufferedReader.readLine();
            if (line == null) {
                Log.i("Fac_MenuConfigManager", "fileContent is null");
                String string = this.mContext.getResources().getString(R.string.str_facmenu_stfileempty);
                if (writeCallback != null) {
                    writeCallback.writeEnd(false, string);
                } else {
                    displayHisToast(string);
                }
                bufferedReader.close();
                return false;
            }
            ArrayList arrayList = new ArrayList();
            Log.i("Fac_MenuConfigManager", "fileContent is " + line);
            arrayList.add(line);
            while (true) {
                String line2 = bufferedReader.readLine();
                if (line2 == null) {
                    break;
                }
                Log.i("Fac_MenuConfigManager", "fileContent is " + line2);
                arrayList.add(line2);
            }
            String str = (String) arrayList.get(0);
            int iIndexOf = str.indexOf(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING);
            String strSubstring = str.substring(iIndexOf + 2);
            Log.i("Fac_MenuConfigManager", "secondSt is " + strSubstring);
            String strSubstring2 = str.substring(0, iIndexOf);
            Log.i("Fac_MenuConfigManager", "firstSt is " + strSubstring2);
            if (strSubstring.startsWith("ST") && strSubstring2.startsWith("ST") && strSubstring.length() == 16 && strSubstring2.length() == 16) {
                String strSubstring3 = strSubstring2.substring(6, 10);
                Log.i("Fac_MenuConfigManager", "service tag strFirstNumber = " + strSubstring3);
                int iIntValue = Integer.valueOf(strSubstring3).intValue();
                Log.i("Fac_MenuConfigManager", "service tag intFirstNumber = " + iIntValue);
                String strSubstring4 = strSubstring.substring(6, 10);
                Log.i("Fac_MenuConfigManager", "service tag strSecondeNumber = " + strSubstring4);
                int iIntValue2 = Integer.valueOf(strSubstring4).intValue();
                Log.i("Fac_MenuConfigManager", "service tag intSecondeNumber = " + iIntValue2);
                if (iIntValue > iIntValue2) {
                    String string2 = this.mContext.getResources().getString(R.string.str_facmenu_st_file_content_bad_format);
                    bufferedReader.close();
                    if (writeCallback != null) {
                        writeCallback.writeEnd(false, string2);
                    } else {
                        displayHisToast(string2);
                    }
                    return false;
                }
                boolean serviceTag = mTVFunction.setServiceTag(strSubstring2);
                String strValueOf = String.valueOf(iIntValue + 1);
                Log.i("Fac_MenuConfigManager", "newFirstNumber =   " + strValueOf);
                if (strValueOf != null && (length = 4 - strValueOf.length()) > 0) {
                    String str2 = "";
                    for (int i = 0; i < length; i++) {
                        str2 = str2 + "0";
                    }
                    strValueOf = str2 + strValueOf;
                }
                Log.i("Fac_MenuConfigManager", "newFirstNumber1 =   " + strValueOf);
                String str3 = strSubstring2.substring(0, 6) + strValueOf + strSubstring2.substring(10, 16);
                Log.i("Fac_MenuConfigManager", "newFirstSt =   " + str3);
                String str4 = str3 + "--" + strSubstring;
                Log.i("Fac_MenuConfigManager", "newSt =   " + str4);
                String absolutePath = sTFile.getAbsolutePath();
                Log.i("Fac_MenuConfigManager", "start write st file!   " + absolutePath);
                mTVFunction.writeFile(absolutePath, str4, false, true);
                mTVFunction.writeFile(absolutePath, "#", true, false);
                mTVFunction.syncFs();
                bufferedReader.close();
                Thread.sleep(1000L);
                Log.i("Fac_MenuConfigManager", "write st over!");
                String string3 = this.mContext.getResources().getString(R.string.str_facmenu_setstsuccess);
                if (!serviceTag) {
                    string3 = this.mContext.getResources().getString(R.string.str_facmenu_setstfail);
                }
                if (writeCallback != null) {
                    writeCallback.writeEnd(false, string3);
                    return true;
                }
                displayHisToast(string3);
                return true;
            }
            Log.i("Fac_MenuConfigManager", "service tag length error");
            String string4 = this.mContext.getResources().getString(R.string.str_facmenu_st_file_content_bad_format);
            bufferedReader.close();
            if (writeCallback != null) {
                writeCallback.writeEnd(false, string4);
            } else {
                displayHisToast(string4);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private File getSTFile(FacRootMenu.WriteCallback writeCallback) throws Throwable {
        String strFindFileOnUSB = FindFileOnUSB("service_tag.txt");
        Log.i("Fac_MenuConfigManager", "getSTFile path = " + strFindFileOnUSB);
        if (strFindFileOnUSB == null) {
            Log.i("Fac_MenuConfigManager", "ST file path is NULL!!!");
            if (writeCallback == null) {
                displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_stfilenotexist) + "\nservice_tag.txt");
            }
            return null;
        }
        File file = new File(strFindFileOnUSB);
        if (file.exists()) {
            return file;
        }
        Log.i("Fac_MenuConfigManager", "ST file is not exit!!");
        if (writeCallback != null) {
            return null;
        }
        displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_stfilenotexist) + "\nservice_tag.txt");
        return null;
    }

    public void isSetServiceTag(Context context) throws Throwable {
        String serviceTag = getServiceTag();
        if (serviceTag == null || serviceTag.equalsIgnoreCase("NG") || serviceTag.isEmpty()) {
            mConfigManager.setServiceTag(null);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Log.e("Fac_MenuConfigManager", "Show hdcp1.4 warning dialog " + context);
        builder.setTitle(R.string.str_facmenu_st_ready_exist);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.38
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) throws Throwable {
                MenuConfigManager.mConfigManager.setServiceTag(null);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.39
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.show();
        alertDialogCreate.getButton(-2).setFocusable(true);
        alertDialogCreate.getButton(-2).setFocusableInTouchMode(true);
        alertDialogCreate.getButton(-2).requestFocus();
        alertDialogCreate.getButton(-2).setOnFocusChangeListener(new View.OnFocusChangeListener(this) { // from class: com.hisense.uifac.menu.util.MenuConfigManager.40
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Log.i("Fac_MenuConfigManager", "enter onFocusChange arg1 = " + z);
                if (z) {
                    view.requestFocus();
                }
            }
        });
    }

    public String getServiceTag() {
        return mTVFunction.getServiceTag();
    }

    public int getFacCheckLogStatus() {
        return mTVFunction.getFacCheckLogStatus();
    }
}
