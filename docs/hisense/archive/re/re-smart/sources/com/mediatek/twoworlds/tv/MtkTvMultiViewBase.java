package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMultiViewBase {
    public static final int MULTIVIEW_RET_FAIL = -1;
    public static final int MULTIVIEW_RET_OK = 0;
    public static final String MULTI_VIEW_MAIN = "main";
    public static final String MULTI_VIEW_SUB = "sub";
    public static final String TAG = "TV_MtkTvMultiViewBase";
    public static final int TV_MODE_NORMAL = 0;
    public static final int TV_MODE_PIP = 1;
    public static final int TV_MODE_POP = 2;

    public static class Region_Info_T {
        public static final String TAG = "Region_Info_T";
        public int height;
        public int width;
        public int x;
        public int y;

        public Region_Info_T(int i, int i2, int i3, int i4) {
            this.x = i;
            this.y = i2;
            this.width = i3;
            this.height = i4;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    public int getAudioFocus() {
        Log.d(TAG, "Enter getAudioFocus\n");
        return TVNativeWrapper.getAudioFocus_native();
    }

    public int getChgSource() {
        Log.d(TAG, "Enter getChgSource\n");
        int chgSource_native = TVNativeWrapper.getChgSource_native();
        a.a("getChgSource = ", chgSource_native, "\n", TAG);
        return chgSource_native;
    }

    public int getPOPTunerFocus() {
        Log.d(TAG, "Enter getPOPTunerFocus\n");
        return TVNativeWrapper.getPOPTunerFocus_native();
    }

    public boolean isMultiviewInputsourceAvailable(int i, String str) {
        Log.d(TAG, "Enter isMultiviewInputsourceAvailable, ui1_input_src = " + i + ", designate path = " + str + "\n");
        if (str == null) {
            Log.d(TAG, "Fail to isMultiviewInputsourceAvailable, path is null \n");
            return false;
        }
        if (!str.equals("main") && !str.equals("sub")) {
            Log.d(TAG, "Fail to isMultiviewInputsourceAvailable, path is illegal \n");
            return false;
        }
        boolean zIsMultiviewInputsourceAvailable_native = TVNativeWrapper.isMultiviewInputsourceAvailable_native(i, str);
        Log.d(TAG, "Leave isMultiviewInputsourceAvailable, is_available = " + zIsMultiviewInputsourceAvailable_native + "\n");
        return zIsMultiviewInputsourceAvailable_native;
    }

    public boolean isTvRunning(String str) {
        return TVNativeWrapper.isTvRunning_native(str);
    }

    public int onlyChgFocus(String str) {
        a.a("Enter onlyChgFocus, change focus designate path = ", str, "\n", TAG);
        if (str == null) {
            Log.d(TAG, "Fail to onlyChgFocus, path is null \n");
            return -1;
        }
        if (str.equals("main") || str.equals("sub")) {
            return TVNativeWrapper.onlyChgFocus_native(str);
        }
        Log.d(TAG, "Fail to onlyChgFocus, path is not main or sub \n");
        return -1;
    }

    public int setAudioFocus(String str) {
        a.a("Enter setAudioFocus, audio focus designate path = ", str, "\n", TAG);
        if (str == null) {
            Log.d(TAG, "Fail to setAudioFocus, path is null \n");
            return -1;
        }
        if (str.equals("main") || str.equals("sub")) {
            return TVNativeWrapper.setAudioFocus_native(str);
        }
        Log.d(TAG, "Fail to setAudioFocus, path is not main or sub \n");
        return -1;
    }

    public int setAudioFocusbySourceid(int i) {
        a.a("Enter setAudioFocusbySourceid, source_id = ", i, "\n", TAG);
        if (i >= 0) {
            return TVNativeWrapper.setAudioFocusbySourceid_native(i);
        }
        Log.d(TAG, "Fail to setAudioFocusbySourceid, (source_id < 0) \n");
        return -1;
    }

    public int setChgSource(boolean z) {
        Log.d(TAG, "Enter setChgSource\n");
        return TVNativeWrapper.setChgSource_native(z);
    }

    public int setNewTvMode(int i) {
        a.a("Enter setNewTvMode, tv mode = ", i, "\n", TAG);
        if (i == 0 || i == 2 || i == 1) {
            return TVNativeWrapper.setNewTvMode_native(i);
        }
        Log.d(TAG, "Fail to setNewTvMode, tv_mode is illegal!\n");
        return -1;
    }

    public int startMainVideo(int i, Region_Info_T region_Info_T) {
        StringBuilder sbA = a.a("Enter startMainVideo ui1_main_input_src = ", i, ", region.x = ");
        sbA.append(region_Info_T.getX());
        sbA.append(", region.y = ");
        sbA.append(region_Info_T.getY());
        sbA.append(", region.width = ");
        sbA.append(region_Info_T.getWidth());
        sbA.append(", region.height = ");
        sbA.append(region_Info_T.getHeight());
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        return TVNativeWrapper.startMainVideo_native(i, region_Info_T);
    }

    public int startSubVideo(int i, Region_Info_T region_Info_T) {
        Log.d(TAG, "Enter startSubVideo , ui1_sub_input_src = " + i + "\n");
        Log.d(TAG, "Enter startSubVideo , sub_region.x = " + region_Info_T.getX() + ", sub_region.y = " + region_Info_T.getY() + ", sub_region.width = " + region_Info_T.getWidth() + ", sub_region.height = " + region_Info_T.getHeight() + "\n");
        return TVNativeWrapper.startSubVideo_native(i, region_Info_T);
    }

    public int stopMainVideo() {
        Log.d(TAG, "Enter stopMainVideo\n");
        return TVNativeWrapper.stopMainVideo_native();
    }

    public int stopSubVideo() {
        Log.d(TAG, "Enter stopSubVideo\n");
        return TVNativeWrapper.stopSubVideo_native();
    }
}
