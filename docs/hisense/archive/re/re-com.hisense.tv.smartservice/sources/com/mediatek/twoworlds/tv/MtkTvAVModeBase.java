package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvAudioApdBase;
import com.mediatek.twoworlds.tv.model.MtkTvAudioInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvScreenModeOverscan;
import com.mediatek.twoworlds.tv.model.MtkTvVideoInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvVideoStreamInfoBase;
import com.mediatek.twoworlds.tv.model.TvProviderAudioTrackBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAVModeBase {
    public static final int AUDIO_INFO_GET_TYPE_AD_SUPPORT = 0;
    public static final int AUDIO_INFO_GET_TYPE_AIAQ_SUPPORT = 1;
    public static final int AUDIO_INFO_GET_TYPE_DTS_VERSION = 2;
    public static final int AUDIO_INFO_SET_TYPE_SPDIF_FMT = 0;
    public static final String TAG = "TV_MtkTvAVModeBase";
    public static final int VIDEOINFO_TYPE_AUTO_BACKLIGHT = 1;
    public static final int VIDEOINFO_TYPE_HDMI_PC_TIMING = 4;
    public static final int VIDEOINFO_TYPE_HDR_EOTF_TYPE = 2;
    public static final int VIDEOINFO_TYPE_HISTOGRAM_CONTROL = 3;
    public static final int VIDEOINFO_TYPE_HISTOGRAM_DATA = 2;
    public static final int VIDEOINFO_TYPE_HISTOGRAM_INFO = 3;
    public static final int VIDEOINFO_TYPE_LUMA_STATISTICS = 4;
    public static final int VIDEOINFO_TYPE_SAT_BY_HUE_HISTOGRAM = 5;
    public static final int VIDEOINFO_TYPE_SAT_BY_HUE_WIN_RANGE = 1;
    public String audioString = "";

    public enum ScreenModeType {
        SCRN_MODE_TYPE_UNKNOWN(0),
        SCRN_MODE_TYPE_NORMAL(1),
        SCRN_MODE_TYPE_LETTERBOX(2),
        SCRN_MODE_TYPE_PAN_SCAN(3),
        SCRN_MODE_TYPE_USER_DEFINED(4),
        SCRN_MODE_TYPE_NON_LINEAR_ZOOM(5),
        SCRN_MODE_TYPE_DOT_BY_DOT(6),
        SCRN_MODE_TYPE_CUSTOM_DEF_0(7),
        SCRN_MODE_TYPE_CUSTOM_DEF_1(8),
        SCRN_MODE_TYPE_CUSTOM_DEF_2(9),
        SCRN_MODE_TYPE_CUSTOM_DEF_3(10),
        SCRN_MODE_TYPE_CUSTOM_DEF_4(11),
        SCRN_MODE_TYPE_CUSTOM_DEF_5(12),
        SCRN_MODE_TYPE_CUSTOM_DEF_6(13),
        SCRN_MODE_TYPE_CUSTOM_DEF_7(14),
        SCRN_MODE_TYPE_NLZ_CUSTOM_DEF_0(15),
        SCRN_MODE_TYPE_NLZ_CUSTOM_DEF_1(16),
        SCRN_MODE_TYPE_NLZ_CUSTOM_DEF_2(17),
        SCRN_MODE_TYPE_NLZ_CUSTOM_DEF_3(18);

        public int mScreenMode;

        ScreenModeType(int i) {
            this.mScreenMode = i;
        }

        public int getScreenMode() {
            return this.mScreenMode;
        }
    }

    public int[] getAllPictureMode() {
        Log.d(TAG, "Enter getAllPictureMode, directly return\n");
        return TVNativeWrapper.getAllPictureMode_native();
    }

    public int[] getAllScreenMode() {
        Log.d(TAG, "Enter getAllScreenMode, directly return\n");
        return TVNativeWrapper.getAllScreenMode_native();
    }

    public int[] getAllSoundEffect() {
        Log.d(TAG, "Enter getAllSoundEffect, directly return\n");
        return TVNativeWrapper.getAllSoundEffect_native();
    }

    public MtkTvAudioApdBase getApdText(int i) {
        Log.d(TAG, "Enter getApdText Here.");
        MtkTvAudioApdBase mtkTvAudioApdBase = new MtkTvAudioApdBase();
        if (TVNativeWrapper.getApdText_native(i, mtkTvAudioApdBase) != 0) {
            return null;
        }
        Log.d(TAG, "Enter getApdText Here");
        return mtkTvAudioApdBase;
    }

    public List<TvProviderAudioTrackBase> getAudioAvailableRecord() {
        Log.d(TAG, "getAudioAvailableRecord");
        ArrayList arrayList = new ArrayList();
        TVNativeWrapper.getAudioAvailableRecord_native(arrayList);
        return arrayList;
    }

    public int getAudioDecType() {
        Log.i(TAG, "getAudioDecType =-1");
        return TVNativeWrapper.getAudioDecType_native();
    }

    public MtkTvAudioInfoBase getAudioInfoData(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase) {
        Log.i(TAG, "getAudioInfoData type=" + i);
        TVNativeWrapper.getAudioInfoData_native(i, mtkTvAudioInfoBase);
        return mtkTvAudioInfoBase;
    }

    public int getAudioInfoValue(int i) {
        Log.i(TAG, "getAudioInfoValue type=" + i);
        return TVNativeWrapper.getAudioInfoValue_native(i);
    }

    public String getAudioLang() {
        Log.d(TAG, "getAudioLang");
        TVNativeWrapper.getAudioLang_native(this);
        return this.audioString;
    }

    public MtkTvScreenModeOverscan getCrntOverscan() {
        Log.d(TAG, "Enter getCrntOverscan\n");
        MtkTvScreenModeOverscan mtkTvScreenModeOverscan = new MtkTvScreenModeOverscan();
        if (TVNativeWrapper.getCrntOverscan_native(mtkTvScreenModeOverscan) == 0) {
            Log.d(TAG, "Enter getCrntOverscan success\n");
            return mtkTvScreenModeOverscan;
        }
        Log.d(TAG, "Enter getCrntOverscan fail\n");
        return null;
    }

    public TvProviderAudioTrackBase getCurrentAd() {
        Log.d(TAG, "Enter getCurrentAd Here.");
        TvProviderAudioTrackBase tvProviderAudioTrackBase = new TvProviderAudioTrackBase();
        if (TVNativeWrapper.getCurrentAd_native(tvProviderAudioTrackBase) != 0) {
            return null;
        }
        StringBuilder sbA = a.a("Enter getCurrentAudio Here");
        sbA.append(tvProviderAudioTrackBase.toString());
        Log.d(TAG, sbA.toString());
        return tvProviderAudioTrackBase;
    }

    public TvProviderAudioTrackBase getCurrentAudio() {
        Log.d(TAG, "Enter getCurrentAudio Here.");
        TvProviderAudioTrackBase tvProviderAudioTrackBase = new TvProviderAudioTrackBase();
        if (TVNativeWrapper.getCurrentAudio_native(tvProviderAudioTrackBase) != 0) {
            return null;
        }
        Log.d(TAG, "Enter getCurrentAudio Here");
        return tvProviderAudioTrackBase;
    }

    public MtkTvVideoStreamInfoBase getCurrentVideoStreamInfo() {
        Log.d(TAG, "Enter getCurrentVideoStreamInfo Here.");
        MtkTvVideoStreamInfoBase mtkTvVideoStreamInfoBase = new MtkTvVideoStreamInfoBase();
        if (TVNativeWrapper.getCurrentVideoStreamInfo_native(mtkTvVideoStreamInfoBase) != 0) {
            return null;
        }
        Log.d(TAG, "Enter getCurrentVideoStreamInfo Here");
        return mtkTvVideoStreamInfoBase;
    }

    public List<TvProviderAudioTrackBase> getInputSourceAudioAvailableRecord() {
        Log.d(TAG, "getInputSourceAudioAvailableRecord");
        ArrayList arrayList = new ArrayList();
        TVNativeWrapper.getInputSourceAudioAvailableRecord_native(arrayList);
        return arrayList;
    }

    public TvProviderAudioTrackBase getInputSourceCurrentAudio() {
        Log.d(TAG, "Enter getInputSourceCurrentAudio Here.");
        TvProviderAudioTrackBase tvProviderAudioTrackBase = new TvProviderAudioTrackBase();
        if (TVNativeWrapper.getInputSourceCurrentAudio_native(tvProviderAudioTrackBase) != 0) {
            return null;
        }
        StringBuilder sbA = a.a("Enter getInputSourceCurrentAudio Here");
        sbA.append(tvProviderAudioTrackBase.toString());
        Log.d(TAG, sbA.toString());
        return tvProviderAudioTrackBase;
    }

    public int getPictureMode() {
        Log.d(TAG, "Enter getPictureMode, directly return\n");
        return TVNativeWrapper.getPictureMode_native();
    }

    public int getScreenMode() {
        Log.d(TAG, "Enter getScreenMode\n");
        int screenMode_native = TVNativeWrapper.getScreenMode_native();
        Log.d(TAG, "Leave getScreenMode\n");
        return screenMode_native;
    }

    public int getSoundEffect() {
        Log.d(TAG, "Enter getAllSoundEffect, directly return\n");
        return TVNativeWrapper.getSoundEffect_native();
    }

    public MtkTvVideoInfoBase getVideoInfoData(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase) {
        Log.i(TAG, "getVideoInfoData type=" + i);
        TVNativeWrapper.getVideoInfoData_native(i, mtkTvVideoInfoBase);
        return mtkTvVideoInfoBase;
    }

    public int getVideoInfoValue(int i) {
        Log.i(TAG, "getVideoInfoValue type=" + i);
        return TVNativeWrapper.getVideoInfoValue_native(i);
    }

    public boolean isFreeze(String str) {
        Log.d(TAG, "isFreeze\n");
        return TVNativeWrapper.isFreeze_native(str);
    }

    public boolean isZoomEnable() {
        Log.d(TAG, "Enter isZoomEnable\n");
        boolean zIsZoomEnable_native = TVNativeWrapper.isZoomEnable_native();
        Log.d(TAG, "Leave isZoomEnable\n");
        return zIsZoomEnable_native;
    }

    public boolean selectAudioById(String str) {
        int iSelectAudioById_native;
        Log.d(TAG, "selectAudioById");
        if (str == null) {
            iSelectAudioById_native = TVNativeWrapper.unselectAudio_native();
        } else {
            int i = Integer.parseInt(str);
            Log.d(TAG, "Enter selectAudioById Here.stream_tag :" + i + "audio_id:" + str);
            iSelectAudioById_native = TVNativeWrapper.selectAudioById_native(i);
        }
        return iSelectAudioById_native == 0;
    }

    public int selectDefaultAudio(String str) {
        Log.d(TAG, "Enter selectDefaultAudio\n");
        return TVNativeWrapper.selectDefaultAudio_native(str);
    }

    public boolean selectMainSubAudioById(int i, int i2) {
        Log.d(TAG, "selectAudioById main:" + i + " sub:" + i2);
        return TVNativeWrapper.selectMainSubAudioById_native(i, i2) == 0;
    }

    public int setAudioInfoData(int i, MtkTvAudioInfoBase mtkTvAudioInfoBase) {
        Log.i(TAG, "setAudioInfoData type=" + i);
        return TVNativeWrapper.setAudioInfoData_native(i, mtkTvAudioInfoBase);
    }

    public int setAudioInfoValue(int i, int i2) {
        Log.i(TAG, "setAudioInfoValue type=" + i + " val=" + i2);
        return TVNativeWrapper.setAudioInfoValue_native(i, i2);
    }

    public void setAudioLang(String str) {
        a.c("setAudioLang =", str, TAG);
        this.audioString = str;
    }

    public int setCrntOverscan(int i, int i2, int i3, int i4) {
        Log.d(TAG, "Enter setCrntOverscan\n");
        int crntOverscan_native = TVNativeWrapper.setCrntOverscan_native(i, i2, i3, i4);
        Log.d(TAG, "Leave setCrntOverscan\n");
        return crntOverscan_native;
    }

    public int setFreeze(String str, boolean z) {
        Log.d(TAG, "setFreeze\n");
        return TVNativeWrapper.setFreeze_native(str, z);
    }

    public int setNextAudioLang() {
        Log.d(TAG, "setNextAudioLang");
        return TVNativeWrapper.setNextAudioLang_native();
    }

    public int setPictureMode(int i) {
        Log.d(TAG, "Enter setPictureMode, directly return\n");
        TVNativeWrapper.setPictureMode_native(i);
        return 0;
    }

    public int setScreenMode(int i) {
        Log.d(TAG, "Enter setScreenMode\n");
        int screenMode_native = TVNativeWrapper.setScreenMode_native(i);
        Log.d(TAG, "Leave setScreenMode\n");
        return screenMode_native;
    }

    public int setSoundEffect(int i) {
        Log.d(TAG, "Enter getAllSoundEffect, directly return\n");
        TVNativeWrapper.setSoundEffect_native(i);
        return 0;
    }

    public int setVideoInfoData(int i, MtkTvVideoInfoBase mtkTvVideoInfoBase) {
        Log.i(TAG, "setVideoInfoData type=" + i);
        return TVNativeWrapper.setVideoInfoData_native(i, mtkTvVideoInfoBase);
    }

    public int setVideoInfoValue(int i, int i2) {
        Log.i(TAG, "setVideoInfoValue type=" + i + " val=" + i2);
        return TVNativeWrapper.setVideoInfoValue_native(i, i2);
    }

    public int updateOverscanIni() {
        Log.d(TAG, "Enter updateOverscanIni\n");
        int iUpdateOverscanIni_native = TVNativeWrapper.updateOverscanIni_native();
        Log.d(TAG, "Leave updateOverscanIni\n");
        return iUpdateOverscanIni_native;
    }
}
