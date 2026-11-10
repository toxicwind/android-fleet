package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvChannelInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvExternalUIStatusBase;
import com.mediatek.twoworlds.tv.model.MtkTvFreqChgParamBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBroadcastBase {
    public static final int BRDCST_CM_CTRL_GET_AGC = 5;
    public static final int BRDCST_CM_CTRL_GET_BER = 0;
    public static final int BRDCST_CM_CTRL_GET_DBM_SNR = 1;
    public static final int BRDCST_CM_CTRL_GET_MODULATION = 4;
    public static final int BRDCST_CM_CTRL_GET_SYM_RATE = 3;
    public static final int BRDCST_CM_CTRL_GET_TS_LOCK_STATUS = 6;
    public static final int BRDCST_CM_CTRL_GET_UEC = 2;
    public static final int BRDCST_RET_FAIL = -1;
    public static final int BRDCST_RET_INVALID_ARG = -2;
    public static final int BRDCST_RET_OK = 0;
    protected static final boolean FORWARD_KEY_TO_LINUX_WORLD = false;
    protected static final String TAG = "MtkTvBroadcast ";
    protected MtkTvKeyEventBase mKeyEvent = new MtkTvKeyEventBase();

    private boolean isFregChgInfoValid(int i, int i2, int i3, int i4) {
        if (i != 0 && i != 1 && i != 2 && i != 3) {
            Log.d(TAG, "The contype is invalid\n");
            return FORWARD_KEY_TO_LINUX_WORLD;
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
                    return FORWARD_KEY_TO_LINUX_WORLD;
                }
                break;
            default:
                Log.d(TAG, "The Freq type is invalid\n");
                return FORWARD_KEY_TO_LINUX_WORLD;
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
                return FORWARD_KEY_TO_LINUX_WORLD;
        }
    }

    public int channelSelect(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z) {
        Log.d(TAG, "Enter channelSelect: \n");
        if (mtkTvChannelInfoBase != null) {
            Log.d(TAG, "svlId = " + mtkTvChannelInfoBase.getSvlId() + ", svlRecId = " + mtkTvChannelInfoBase.getSvlRecId() + ", channellId = " + mtkTvChannelInfoBase.getChannelId() + ", isNeedCheckBarkerChannel = " + z + "\n");
            return TVNativeWrapper.channelSelect_native(mtkTvChannelInfoBase, z);
        }
        Log.e(TAG, "The 'channelInfo' is null!\n");
        return -2;
    }

    public int channelSelect(MtkTvChannelInfoBase mtkTvChannelInfoBase, boolean z, int i) {
        Log.d(TAG, "Enter channelSelect: \n");
        if (mtkTvChannelInfoBase != null) {
            Log.d(TAG, "svlId = " + mtkTvChannelInfoBase.getSvlId() + ", svlRecId = " + mtkTvChannelInfoBase.getSvlRecId() + ", channellId = " + mtkTvChannelInfoBase.getChannelId() + ", isNeedCheckBarkerChannel = " + z + ", focus_id = " + i + "\n");
            return TVNativeWrapper.channelSelect_native(mtkTvChannelInfoBase, z, i);
        }
        Log.e(TAG, "The 'channelInfo' is null!\n");
        return -2;
    }

    public int channelSelect(int i, boolean z) {
        Log.d(TAG, "Enter channelSelect: \n");
        if (i != 0) {
            Log.d(TAG, "channelId = " + i + ", isNeedCheckBarkerChannel = " + z + "\n");
            return TVNativeWrapper.channelSelectByChannelId_native(i, z);
        }
        Log.e(TAG, "The 'channelId' is invalid!\n");
        return -2;
    }

    public int channelSelectSilently(MtkTvChannelInfoBase mtkTvChannelInfoBase, int i) {
        Log.d(TAG, "Enter channelSelect: \n");
        if (mtkTvChannelInfoBase != null) {
            Log.d(TAG, "svlId = " + mtkTvChannelInfoBase.getSvlId() + ", svlRecId = " + mtkTvChannelInfoBase.getSvlRecId() + ", channellId = " + mtkTvChannelInfoBase.getChannelId() + "\n");
            return TVNativeWrapper.channelSelectSilently_native(mtkTvChannelInfoBase, i, -1);
        }
        Log.e(TAG, "The 'channelInfo' is null!\n");
        return -2;
    }

    public int channelSelectSilently(MtkTvChannelInfoBase mtkTvChannelInfoBase, int i, int i2) {
        Log.d(TAG, "Enter channelSelect: \n");
        if (mtkTvChannelInfoBase != null) {
            Log.d(TAG, "svlId = " + mtkTvChannelInfoBase.getSvlId() + ", svlRecId = " + mtkTvChannelInfoBase.getSvlRecId() + ", channellId = " + mtkTvChannelInfoBase.getChannelId() + "streamMode = " + i2 + "\n");
            return TVNativeWrapper.channelSelectSilently_native(mtkTvChannelInfoBase, i, i2);
        }
        Log.e(TAG, "The 'channelInfo' is null!\n");
        return -2;
    }

    public int channelSelectByChannelNumber(int i) {
        Log.d(TAG, "Enter channelSelectByChannelNumber: majorNo = " + i + "\n");
        return TVNativeWrapper.channelSelectByChannelNumber_native(i, 0, FORWARD_KEY_TO_LINUX_WORLD);
    }

    public int channelSelectByChannelNumber(int i, int i2) {
        Log.d(TAG, "Enter channelSelectByChannelNumber: majorNo = " + i + ", minorNo = " + i2 + "\n");
        return TVNativeWrapper.channelSelectByChannelNumber_native(i, i2, true);
    }

    public int syncStop(String str, boolean z) {
        Log.d(TAG, "Enter syncStop\n");
        return TVNativeWrapper.syncStop_native(str, z);
    }

    public boolean isSignalLoss() {
        Log.d(TAG, "Enter isSignalLoss\n");
        return TVNativeWrapper.isSignalLoss_native();
    }

    public int getSignalLevel() {
        Log.d(TAG, "Enter getSignalLevel\n");
        return TVNativeWrapper.getSignalLevel_native();
    }

    public int getSignalQuality() {
        Log.d(TAG, "Enter getSignalQuality\n");
        return TVNativeWrapper.getSignalQuality_native();
    }

    public boolean isOrigScrambleAudio(String str) {
        Log.d(TAG, "Enter isOrigScrambleAudio\n");
        return TVNativeWrapper.isOrigScrambleStrm_native(str, 0);
    }

    public boolean isOrigScrambleVideo(String str) {
        Log.d(TAG, "Enter isOrigScrambleVideo\n");
        return TVNativeWrapper.isOrigScrambleStrm_native(str, 1);
    }

    public int getConnectAttr(String str, int i) {
        Log.d(TAG, "Enter getConnectAttr\n");
        return TVNativeWrapper.getConnectAttr_native(str, i);
    }

    public int changeFreq(String str, MtkTvFreqChgParamBase mtkTvFreqChgParamBase) {
        Log.d(TAG, "Enter changeFreq\n");
        if (mtkTvFreqChgParamBase == null) {
            return -1;
        }
        Log.d(TAG, "ConType = " + mtkTvFreqChgParamBase.getConType() + ", freqType = " + mtkTvFreqChgParamBase.getfreqType() + ", freq = " + mtkTvFreqChgParamBase.getfreq() + ", tunerMod = " + mtkTvFreqChgParamBase.gettunerMod() + ", symRate = " + mtkTvFreqChgParamBase.getsymRate() + ", SatPol = " + mtkTvFreqChgParamBase.getSatPol() + ", SatLstId = " + mtkTvFreqChgParamBase.getSatLstId() + ", SatLstRecId = " + mtkTvFreqChgParamBase.getSatLstRecId() + "\n");
        if (isFregChgInfoValid(mtkTvFreqChgParamBase.getConType(), mtkTvFreqChgParamBase.getfreqType(), mtkTvFreqChgParamBase.getSatPol(), mtkTvFreqChgParamBase.gettunerMod())) {
            return TVNativeWrapper.changeFreq_native(str, mtkTvFreqChgParamBase);
        }
        return -1;
    }

    public int transferExternalUIStatus(MtkTvExternalUIStatusBase mtkTvExternalUIStatusBase) {
        Log.d(TAG, "Enter transferExternalUIStatus\n");
        if (mtkTvExternalUIStatusBase == null) {
            return -1;
        }
        Log.d(TAG, "uiId = " + mtkTvExternalUIStatusBase.getUIId() + ", isShow = " + mtkTvExternalUIStatusBase.getShow() + "\n");
        return TVNativeWrapper.transferExternalUIStatus_native(mtkTvExternalUIStatusBase);
    }
}
