package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvHighLevelBase {
    public static final String TAG = "MtkTvHighLevel";

    public MtkTvHighLevelBase() {
        Log.d(TAG, "MtkTvHighlevel object created");
    }

    public boolean closeDispRegion() {
        Log.d(TAG, "closeDispRegion");
        return TVNativeWrapper.HighLevel_native(111, 0, 0, 0, 0, 0, 0) == 1;
    }

    public int exit3rdParty(int i) {
        a.b("exit_3rd_party id=", i, TAG);
        if (i <= 0 || i >= 4) {
            return -1;
        }
        TVNativeWrapper.HighLevel_native(103, 0, i, 0, 0, 0, 0);
        return 0;
    }

    public int exitInternalApp(int i) {
        a.b("exit_Internal_App id=", i, TAG);
        if (i <= 0 || i >= 6) {
            return -1;
        }
        TVNativeWrapper.HighLevel_native(104, 0, i, 0, 0, 0, 0);
        return 0;
    }

    public int getCurrentTvMode() {
        Log.d(TAG, "getCurrentTvMode");
        int iHighLevel_native = TVNativeWrapper.HighLevel_native(107, 0, 0, 0, 0, 0, 0);
        if (iHighLevel_native == 0) {
            return 0;
        }
        return iHighLevel_native == 1 ? 1 : 2;
    }

    public boolean isIn3rdPartyMode() {
        Log.d(TAG, "is_in_3rd_party_mode");
        return TVNativeWrapper.HighLevel_native(106, 0, 0, 0, 0, 0, 0) == 1;
    }

    public int launch3rdParty(int i) {
        a.b("launch_3rd_party id=", i, TAG);
        if (i <= 0 || i >= 4) {
            return -1;
        }
        TVNativeWrapper.HighLevel_native(103, 1, i, 0, 0, 0, 0);
        return 0;
    }

    public int launchInternalApp(int i) {
        a.b("launch_Internal_App id=", i, TAG);
        if (i <= 0 || i >= 6) {
            return -1;
        }
        TVNativeWrapper.HighLevel_native(104, 1, i, 0, 0, 0, 0);
        return 0;
    }

    public boolean muteVDP(int i) {
        a.b("muteVDP mPath", i, TAG);
        return TVNativeWrapper.HighLevel_native(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_NO_CONTENT, i, 1, 0, 0, 0, 0) == 1;
    }

    public int set3rdPartyViewPort(int i, int i2, int i3, int i4) {
        if (i > 10000 || i2 > 10000 || i3 > 10000 || i4 > 10000 || i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i + i3 > 10000 || i2 + i4 > 10000) {
            return -1;
        }
        return TVNativeWrapper.HighLevel_native(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, 0, i, i2, i3, i4, 0);
    }

    public int setBroadcastUiVisibility(boolean z) {
        a.a("setBroadcastUiVisibility ", z, TAG);
        if (z) {
            TVNativeWrapper.HighLevel_native(109, 1, 0, 0, 0, 0, 0);
            return 0;
        }
        TVNativeWrapper.HighLevel_native(109, 0, 0, 0, 0, 0, 0);
        return 0;
    }

    public int setLinuxResume() {
        Log.d(TAG, "setLinuxResume");
        MtkTvUtilBase.saveTimeStamp("[JJ] call setLinuxResume");
        TVNativeWrapper.HighLevel_native(108, 1, 0, 0, 0, 0, 0);
        return 0;
    }

    public int setLinuxStandby() {
        Log.d(TAG, "setLinuxStandby");
        TVNativeWrapper.HighLevel_native(108, 2, 0, 0, 0, 0, 0);
        return 0;
    }

    public int setLinuxSuspend() {
        Log.d(TAG, "setLinuxSuspend");
        TVNativeWrapper.HighLevel_native(108, 0, 0, 0, 0, 0, 0);
        return 0;
    }

    public int setNetworkStatus(int i) {
        a.b("setNetworkStatus ", i, TAG);
        return TVNativeWrapper.HighLevel_native(302, i, 0, 0, 0, 0, 0);
    }

    public int setViewPort(boolean z, int i, int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder();
        sb.append("setViewPort (Decimated)=");
        sb.append(z);
        sb.append(" ");
        sb.append(i);
        sb.append(" ");
        sb.append(i2);
        sb.append(" ");
        sb.append(i3);
        sb.append(" ");
        a.b(sb, i4, TAG);
        if (i < 0 || i >= 1920 || i2 < 0 || i2 >= 1080 || i3 < 0 || i3 > 1920 || i4 < 0 || i4 > 1080) {
            return -1;
        }
        if (z) {
            TVNativeWrapper.HighLevel_native(105, 1, i, i2, i3, i4, 0);
            return 0;
        }
        TVNativeWrapper.HighLevel_native(105, 0, i, i2, i3, i4, 0);
        return 0;
    }

    public int startTV() {
        Log.d(TAG, "startTV");
        TVNativeWrapper.HighLevel_native(100, 0, 0, 0, 0, 0, 0);
        return 0;
    }

    public int stopAll() {
        Log.d(TAG, "stopAll");
        TVNativeWrapper.HighLevel_native(102, 0, 0, 0, 0, 0, 0);
        return 0;
    }

    public int stopHbbtv() {
        Log.d(TAG, "stopHbbtv");
        return TVNativeWrapper.HighLevel_native(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_OUT_OF_SAFE_RANGE_END, 0, 0, 0, 0, 0, 0);
    }

    public int stopTV() {
        Log.d(TAG, "stopTV");
        TVNativeWrapper.HighLevel_native(101, 0, 0, 0, 0, 0, 0);
        return 0;
    }

    public int launchInternalApp(int i, String str) {
        Log.d(TAG, "launchInternalApp internal_app_id=" + i + ", launchOption=" + str);
        if (i <= 0 || i >= 6 || i != 3) {
            return -1;
        }
        int iIndexOf = str.indexOf("=");
        int iIndexOf2 = str.indexOf(",");
        String strReplace = str.substring(iIndexOf + 1, iIndexOf2).trim().replace(" ", "");
        StringBuilder sbB = a.b("launchInternalApp typeStart=", iIndexOf, ", typeEnd=", iIndexOf2, ", type=");
        sbB.append(strReplace);
        Log.d(TAG, sbB.toString());
        int i2 = iIndexOf2 + 1;
        int iIndexOf3 = str.indexOf("=", i2);
        int iIndexOf4 = str.indexOf(",", i2);
        int i3 = iIndexOf3 + 1;
        String strReplace2 = str.substring(i3, iIndexOf4).trim().replace(" ", "");
        StringBuilder sbB2 = a.b("launchInternalApp serviceStart=", iIndexOf3, ", serviceEnd=", iIndexOf4, ", service=");
        sbB2.append(strReplace2);
        Log.d(TAG, sbB2.toString());
        int iIndexOf5 = str.indexOf("=", i3);
        String strReplace3 = str.substring(iIndexOf5 + 1).trim().replace(" ", "");
        Log.d(TAG, "launchInternalApp urlStart=" + iIndexOf5 + ", url=" + strReplace3);
        return TVNativeWrapper.launchHbbtv_native(strReplace, strReplace2, strReplace3);
    }
}
