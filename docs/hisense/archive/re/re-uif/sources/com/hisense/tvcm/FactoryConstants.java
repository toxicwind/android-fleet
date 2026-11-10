package com.hisense.tvcm;

import com.hisense.tvs.CESIConstants;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;

/* JADX INFO: loaded from: classes.dex */
public class FactoryConstants {
    public static final int BALANCE_AMP_LEFT_INDEX = FacPropertyDemo.HIFAC_MIN_BALANCE;
    public static final int OUTPUT_TVS_BALANCE_CENTER;
    public static final int OUTPUT_TVS_BALANCE_LEFT;
    public static final int OUTPUT_TVS_BALANCE_RIGHT;
    public static final int OUTPUT_TVS_SPEAKER_ALL;
    public static final int OUTPUT_TVS_SPEAKER_MAIN;
    public static final int OUTPUT_TVS_SPEAKER_TOP_TWEETER;
    public static final int OUTPUT_TVS_SPEAKER_TWEETER;
    public static final int OUTPUT_TVS_SPEAKER_WOOFER;
    public static boolean canBluetooth3DBroadcastReceive = false;

    static {
        int i = FacPropertyDemo.HIFAC_MAX_BALANCE;
        OUTPUT_TVS_SPEAKER_ALL = CESIConstants.getValue("SpeakerUnitType_e", MtkTvRatingConvert2Goo.RATING_STR_ALL);
        OUTPUT_TVS_SPEAKER_MAIN = CESIConstants.getValue("SpeakerUnitType_e", "MAIN");
        OUTPUT_TVS_SPEAKER_TOP_TWEETER = CESIConstants.getValue("SpeakerUnitType_e", "TOP_TWEETER");
        OUTPUT_TVS_SPEAKER_TWEETER = CESIConstants.getValue("SpeakerUnitType_e", "TWEETER");
        OUTPUT_TVS_SPEAKER_WOOFER = CESIConstants.getValue("SpeakerUnitType_e", "WOOFER");
        OUTPUT_TVS_BALANCE_CENTER = CESIConstants.getValue("SpeakerBalance_e", "CENTER");
        OUTPUT_TVS_BALANCE_LEFT = CESIConstants.getValue("SpeakerBalance_e", "LEFT");
        OUTPUT_TVS_BALANCE_RIGHT = CESIConstants.getValue("SpeakerBalance_e", "RIGHT");
    }
}
