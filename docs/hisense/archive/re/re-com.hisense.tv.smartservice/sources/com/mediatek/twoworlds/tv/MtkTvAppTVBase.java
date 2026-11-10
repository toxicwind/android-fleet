package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvFreqChgParamBase;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAppTVBase {
    public static final int JNI_APPTV_SYS_STATUS_AFTER_ASPECT_RATIO_CHG = 9;
    public static final int JNI_APPTV_SYS_STATUS_AFTER_ZOOM_MODE_CHG = 11;
    public static final int JNI_APPTV_SYS_STATUS_BEFORE_ASPECT_RATIO_CHG = 8;
    public static final int JNI_APPTV_SYS_STATUS_BEFORE_ZOOM_MODE_CHG = 10;
    public static final int JNI_APPTV_SYS_STATUS_CHANNEL_SCANNING = 6;
    public static final int JNI_APPTV_SYS_STATUS_MENU_PAUSE = 1;
    public static final int JNI_APPTV_SYS_STATUS_MENU_RESUME = 0;
    public static final int JNI_APPTV_SYS_STATUS_MMP_PAUSE = 3;
    public static final int JNI_APPTV_SYS_STATUS_MMP_RESUME = 2;
    public static final int JNI_APPTV_SYS_STATUS_SCAN_FINISHED = 7;
    public static final int JNI_APPTV_SYS_STATUS_WIZARD_PAUSE = 5;
    public static final int JNI_APPTV_SYS_STATUS_WIZARD_RESUME = 4;
    public static final String SYS_AFTER_ASPECT_RATIO_CHG = "After_ASP_Ratio_chg";
    public static final String SYS_AFTER_ZOOM_MODE_CHG = "SAfter_Zoom_Mode_Chg";
    public static final String SYS_BEFORE_ASPECT_RATIO_CHG = "Before_ASP_Ratio_chg";
    public static final String SYS_BEFORE_ZOOM_MODE_CHG = "Before_Zoom_Mode_Chg";
    public static final String SYS_CHANNEL_SCANNING = "Channel_Scaning";
    public static final String SYS_MENU_PAUSE = "Menu_Pause";
    public static final String SYS_MENU_RESUME = "Menu_Resume";
    public static final String SYS_MMP_PAUSE = "MMP_Pause";
    public static final String SYS_MMP_RESUME = "MMP_Resume";
    public static final String SYS_SCAN_FINISHED = "Scan_Finished";
    public static final String SYS_WIZARD_PAUSE = "Wizard_Pause";
    public static final String SYS_WIZARD_RESUME = "Wizard_Resume";
    public static Map<String, Integer> SysStatus_map = new HashMap();
    public static final String TAG = "MtkTvAppTVBase";

    public MtkTvAppTVBase() {
        setSysStatus_map();
    }

    private boolean isFregChgInfoValid(int i, int i2, int i3, int i4) {
        if (i != 0 && i != 1 && i != 2 && i != 3) {
            Log.d(TAG, "The contype is invalid\n");
            return false;
        }
        switch (i2) {
            case 0:
            case 1:
            case 3:
            case 4:
            case 6:
            case 7:
                break;
            case 2:
            case 5:
                if (i3 != 0 && i3 != 1 && i3 != 2 && i3 != 3 && i3 != 4) {
                    Log.d(TAG, "The Sat Pol is invalid\n");
                    return false;
                }
                break;
            default:
                Log.d(TAG, "The Freq type is invalid\n");
                return false;
        }
        switch (i4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
                return true;
            default:
                Log.d(TAG, "The tuner mod is invalid\n");
                return false;
        }
    }

    public static int saveTimestamp(String str) {
        Log.d(TAG, "Enter saveTimestamp\n");
        return TVNativeWrapper.AppTVsaveTimestamp_native(str);
    }

    public static void setSysStatus_map() {
        SysStatus_map.put(SYS_MENU_RESUME, 0);
        SysStatus_map.put(SYS_MENU_PAUSE, 1);
        SysStatus_map.put(SYS_MMP_RESUME, 2);
        SysStatus_map.put(SYS_MMP_PAUSE, 3);
        SysStatus_map.put(SYS_WIZARD_RESUME, 4);
        SysStatus_map.put(SYS_WIZARD_PAUSE, 5);
        SysStatus_map.put(SYS_CHANNEL_SCANNING, 6);
        SysStatus_map.put(SYS_SCAN_FINISHED, 7);
        SysStatus_map.put(SYS_BEFORE_ASPECT_RATIO_CHG, 8);
        SysStatus_map.put(SYS_AFTER_ASPECT_RATIO_CHG, 9);
        SysStatus_map.put(SYS_BEFORE_ZOOM_MODE_CHG, 10);
        SysStatus_map.put(SYS_AFTER_ZOOM_MODE_CHG, 11);
    }

    public boolean AutoClockPhasePostionCondSuccess(String str) {
        Log.d(TAG, "Enter AutoClockPhasePostionCondSuccess\n");
        return TVNativeWrapper.AppTVAutoClockPhasePostionCondSuccess_native(str);
    }

    public boolean AutoColorCondSuccess(String str) {
        Log.d(TAG, "Enter AutoColorCondSuccess\n");
        return TVNativeWrapper.AppTVAutoColorCondSuccess_native(str);
    }

    public long GetConnAttrAGC(String str) {
        Log.d(TAG, "Enter GetConnAttrAGC + \n");
        return TVNativeWrapper.GetConnAtrrAGC_native(str);
    }

    public int GetConnAttrBER(String str) {
        Log.d(TAG, "Enter GetConnAttrBER + \n");
        return TVNativeWrapper.GetConnAttrBER_native(str);
    }

    public long GetConnAttrDBMSNR(String str) {
        Log.d(TAG, "Enter GetConnAtttrDBMSNR + \n");
        return TVNativeWrapper.GetConnAttrDBMSNR_native(str);
    }

    public long GetConnAttrUEC(String str) {
        Log.d(TAG, "Enter GetConnAttrUEC + \n");
        return TVNativeWrapper.GetConnAttrUEC_native(str);
    }

    public String GetCurrentChannelServiceText() {
        Log.d(TAG, "Enter GetCurrentChannelServiceText + \n");
        return TVNativeWrapper.GetCurrentChannelServiceText_native();
    }

    public int GetModulation(String str) {
        Log.d(TAG, "Enter GetModulation + \n");
        return TVNativeWrapper.GetModulation_native(str);
    }

    public int GetSignalBER(String str) {
        Log.d(TAG, "Enter GetSignalBER + \n");
        return TVNativeWrapper.GetSignalBER_native(str);
    }

    public long GetSymRate(String str) {
        Log.d(TAG, "Enter GetSymRate + \n");
        return TVNativeWrapper.GetSymRate_native(str);
    }

    public int GetVideoSrcTag3DType(String str) {
        Log.d(TAG, "Enter GetVideoSrcTag3DType  \n");
        return TVNativeWrapper.GetVideoSrcTag3DType_native(str);
    }

    public boolean VideoSrcIsProgressive(String str) {
        Log.d(TAG, "Enter VideoSrcIsProgressive \n");
        return TVNativeWrapper.VideoSrcIsProgressive_native(str);
    }

    public int changeFreq(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase) {
        Log.d(TAG, "Enter changeFreq\n");
        if (mtkTvFreqChgParamBase != null) {
            StringBuilder sbA = a.a("ConType = ");
            sbA.append(mtkTvFreqChgParamBase.getConType());
            sbA.append(", freqType = ");
            sbA.append(mtkTvFreqChgParamBase.getfreqType());
            sbA.append(", freq = ");
            sbA.append(mtkTvFreqChgParamBase.getfreq());
            sbA.append(", tunerMod = ");
            sbA.append(mtkTvFreqChgParamBase.gettunerMod());
            sbA.append(", symRate = ");
            sbA.append(mtkTvFreqChgParamBase.getsymRate());
            sbA.append(", SatPol = ");
            sbA.append(mtkTvFreqChgParamBase.getSatPol());
            sbA.append(", SatLstId = ");
            sbA.append(mtkTvFreqChgParamBase.getSatLstId());
            sbA.append(", SatLstRecId = ");
            sbA.append(mtkTvFreqChgParamBase.getSatLstRecId());
            sbA.append("\n");
            Log.d(TAG, sbA.toString());
            if (isFregChgInfoValid(mtkTvFreqChgParamBase.getConType(), mtkTvFreqChgParamBase.getfreqType(), mtkTvFreqChgParamBase.getSatPol(), mtkTvFreqChgParamBase.gettunerMod())) {
                return TVNativeWrapper.AppTVchangeFreq_native(str, mtkTvFreqChgParamBase);
            }
        }
        return -1;
    }

    public int getCapabilityEx(int i) {
        int iAppTVGetCapabilityEx_native = TVNativeWrapper.AppTVGetCapabilityEx_native(i);
        Log.d(TAG, a.a("getCapabilityEx(", i, "), ret = ", iAppTVGetCapabilityEx_native, "\n"));
        return iAppTVGetCapabilityEx_native;
    }

    public int getMatchedChannel(int i, boolean z, int i2, int i3) {
        Log.d(TAG, "Enter getMatchedChannel\n");
        return TVNativeWrapper.AppTVgetMatchedChannel_native(i, z, i2, i3);
    }

    public int setAutoClockPhasePostion(String str) {
        Log.d(TAG, "Enter setAutoClockPhasePostion\n");
        return TVNativeWrapper.AppTVsetAutoClockPhasePostion_native(str);
    }

    public int setAutoColor(String str) {
        Log.d(TAG, "Enter setAutoColor\n");
        return TVNativeWrapper.AppTVsetAutoColor_native(str);
    }

    public int setFinetuneFreq(String str, int i, boolean z) {
        Log.d(TAG, "Enter setFinetuneFreq\n");
        return TVNativeWrapper.AppTVsetFinetuneFreq_native(str, i, z);
    }

    public int setVideoMute(String str, boolean z) {
        Log.d(TAG, "Enter setVideoMute\n");
        return TVNativeWrapper.AppTVsetVideoMute_native(str, z);
    }

    public int swapTmpUnlockInfo(int i) {
        Log.d(TAG, "Enter swapTmpUnlockInfo + mode_id:\n" + i);
        return TVNativeWrapper.AppTV_swapTmpUnlockInfo_native(i);
    }

    public int unblockSvc(String str, boolean z) {
        Log.d(TAG, "Enter unblockSvc\n");
        return TVNativeWrapper.AppTVunblockSvc_native(str, z);
    }

    public int unlockService(String str) {
        Log.d(TAG, "Enter unlockService\n");
        return TVNativeWrapper.AppTVunlockService_native(str);
    }

    public int updatedSysStatus(String str) {
        Log.d(TAG, "Enter UpdatedSysStatus\n");
        int iIntValue = SysStatus_map.get(str).intValue();
        Log.d(TAG, "ww_status:" + iIntValue);
        return TVNativeWrapper.AppTVupdatedSysStatus_native(iIntValue);
    }

    public int updatedSysStatus(String str, String str2) {
        Log.d(TAG, "Enter updatedSysStatus + \n");
        int iIntValue = SysStatus_map.get(str2).intValue();
        Log.d(TAG, "ww_status:" + iIntValue);
        return TVNativeWrapper.AppTVupdatedSysStatus_native(str, iIntValue);
    }
}
