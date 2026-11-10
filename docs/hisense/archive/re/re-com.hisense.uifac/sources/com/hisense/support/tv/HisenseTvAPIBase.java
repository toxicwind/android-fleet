package com.hisense.support.tv;

import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import com.hisense.avmiddleware.AvMiddleWareManager;
import com.hisense.avmiddleware.HisAudioApi;
import com.hisense.avmiddleware.HisHdmiApi;
import com.hisense.avmiddleware.HisVideoApi;
import com.mediatek.LedFactory.LedFactory;
import com.mediatek.twoworlds.tv.MtkTvConfig;
import com.mediatek.twoworlds.tv.MtkTvInputSource;
import com.mediatek.twoworlds.tv.MtkTvInputSourceBase;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import com.mediatek.twoworlds.tv.model.MtkTvRatingConvert2Goo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class HisenseTvAPIBase {
    int MAX_DTV_LANGUAGE;
    protected Context mContext;
    String[] val_language_dtv;
    public HisAudioApi mHisAudioApi = null;
    public HisVideoApi mHisVideoApi = null;
    public AvMiddleWareManager mAvMiddleWareManager = null;
    private LedFactory mMicLedController = null;

    public boolean adjust4096GammaPattenr(int[] iArr, int i) {
        return false;
    }

    public int getDefaultAudioVolume() {
        return 15;
    }

    public int getFactoryTconStatus() {
        return 1;
    }

    public int getLocaldimmingEnable() {
        return 1;
    }

    public boolean getLvdsMode() {
        return true;
    }

    public boolean getSensorEnableStatus() {
        return false;
    }

    public int setLVSDSscStatusByOnce(int i) {
        return 1;
    }

    public int setSensorEnable(boolean z) {
        return 0;
    }

    public HisenseTvAPIBase() {
        String[] strArr = {MtkTvConfigTypeBase.S639_CFG_LANG_ENG, MtkTvConfigTypeBase.S639_CFG_LANG_ENG, MtkTvConfigTypeBase.S639_CFG_LANG_FRE, MtkTvConfigTypeBase.S639_CFG_LANG_SPA, MtkTvConfigTypeBase.S639_CFG_LANG_POR, MtkTvConfigTypeBase.S639_CFG_LANG_ARA, MtkTvConfigTypeBase.S639_CFG_LANG_RUS, MtkTvConfigTypeBase.S639_CFG_LANG_CHI, MtkTvConfigTypeBase.S639_CFG_LANG_VIE, MtkTvConfigTypeBase.S639_CFG_LANG_THA, MtkTvConfigTypeBase.S639_CFG_LANG_MMR, MtkTvConfigTypeBase.S639_CFG_LANG_UZB, MtkTvConfigTypeBase.S639_CFG_LANG_HIN, MtkTvConfigTypeBase.S639_CFG_LANG_UKR, "msa", MtkTvConfigTypeBase.S639_CFG_LANG_HBR, MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, MtkTvConfigTypeBase.S639_CFG_LANG_GER, MtkTvConfigTypeBase.S639_CFG_LANG_ITA, MtkTvConfigTypeBase.S639_CFG_LANG_SWE, MtkTvConfigTypeBase.S638_CFG_LANG_DAN, MtkTvConfigTypeBase.S639_CFG_LANG_FIN, MtkTvConfigTypeBase.S639_CFG_LANG_NOR, MtkTvConfigTypeBase.S639_CFG_LANG_CZE, MtkTvConfigTypeBase.S639_CFG_LANG_SLK, MtkTvConfigTypeBase.S639_CFG_LANG_POL, MtkTvConfigTypeBase.S639_CFG_LANG_HUN, MtkTvConfigTypeBase.S639_CFG_LANG_BUL, MtkTvConfigTypeBase.S639_CFG_LANG_TUR, MtkTvConfigTypeBase.S639_CFG_LANG_PER, MtkTvConfigTypeBase.S639_CFG_LANG_IND, MtkTvConfigTypeBase.S639_CFG_LANG_HRV, MtkTvConfigTypeBase.S639_CFG_LANG_SRP, "mac", "alb", MtkTvConfigTypeBase.S639_CFG_LANG_LAV, MtkTvConfigTypeBase.S639_CFG_LANG_EST, MtkTvConfigTypeBase.S639_CFG_LANG_LIT, "kur", MtkTvConfigTypeBase.S639_CFG_LANG_SLV, MtkTvConfigTypeBase.S639_CFG_LANG_DUT, MtkTvConfigTypeBase.S639_CFG_LANG_RUM, MtkTvConfigTypeBase.S639_CFG_LANG_GRE, "arm", "Geo", MtkTvConfigTypeBase.S639_CFG_LANG_URD, MtkTvConfigTypeBase.S639_CFG_LANG_AMH, "kir", MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, MtkTvConfigTypeBase.S639_CFG_LANG_AZE, "bsn", "icl", "byl", MtkTvConfigTypeBase.S639_CFG_LANG_HIN, "mld", "bng", "mog", "cnr"};
        this.val_language_dtv = strArr;
        this.MAX_DTV_LANGUAGE = strArr.length;
    }

    protected HisenseTvAPIBase(Context context) {
        String[] strArr = {MtkTvConfigTypeBase.S639_CFG_LANG_ENG, MtkTvConfigTypeBase.S639_CFG_LANG_ENG, MtkTvConfigTypeBase.S639_CFG_LANG_FRE, MtkTvConfigTypeBase.S639_CFG_LANG_SPA, MtkTvConfigTypeBase.S639_CFG_LANG_POR, MtkTvConfigTypeBase.S639_CFG_LANG_ARA, MtkTvConfigTypeBase.S639_CFG_LANG_RUS, MtkTvConfigTypeBase.S639_CFG_LANG_CHI, MtkTvConfigTypeBase.S639_CFG_LANG_VIE, MtkTvConfigTypeBase.S639_CFG_LANG_THA, MtkTvConfigTypeBase.S639_CFG_LANG_MMR, MtkTvConfigTypeBase.S639_CFG_LANG_UZB, MtkTvConfigTypeBase.S639_CFG_LANG_HIN, MtkTvConfigTypeBase.S639_CFG_LANG_UKR, "msa", MtkTvConfigTypeBase.S639_CFG_LANG_HBR, MtkTvConfigTypeBase.S639_CFG_LANG_ZHO, MtkTvConfigTypeBase.S639_CFG_LANG_GER, MtkTvConfigTypeBase.S639_CFG_LANG_ITA, MtkTvConfigTypeBase.S639_CFG_LANG_SWE, MtkTvConfigTypeBase.S638_CFG_LANG_DAN, MtkTvConfigTypeBase.S639_CFG_LANG_FIN, MtkTvConfigTypeBase.S639_CFG_LANG_NOR, MtkTvConfigTypeBase.S639_CFG_LANG_CZE, MtkTvConfigTypeBase.S639_CFG_LANG_SLK, MtkTvConfigTypeBase.S639_CFG_LANG_POL, MtkTvConfigTypeBase.S639_CFG_LANG_HUN, MtkTvConfigTypeBase.S639_CFG_LANG_BUL, MtkTvConfigTypeBase.S639_CFG_LANG_TUR, MtkTvConfigTypeBase.S639_CFG_LANG_PER, MtkTvConfigTypeBase.S639_CFG_LANG_IND, MtkTvConfigTypeBase.S639_CFG_LANG_HRV, MtkTvConfigTypeBase.S639_CFG_LANG_SRP, "mac", "alb", MtkTvConfigTypeBase.S639_CFG_LANG_LAV, MtkTvConfigTypeBase.S639_CFG_LANG_EST, MtkTvConfigTypeBase.S639_CFG_LANG_LIT, "kur", MtkTvConfigTypeBase.S639_CFG_LANG_SLV, MtkTvConfigTypeBase.S639_CFG_LANG_DUT, MtkTvConfigTypeBase.S639_CFG_LANG_RUM, MtkTvConfigTypeBase.S639_CFG_LANG_GRE, "arm", "Geo", MtkTvConfigTypeBase.S639_CFG_LANG_URD, MtkTvConfigTypeBase.S639_CFG_LANG_AMH, "kir", MtkTvConfigTypeBase.S639_CFG_LANG_KAZ, MtkTvConfigTypeBase.S639_CFG_LANG_AZE, "bsn", "icl", "byl", MtkTvConfigTypeBase.S639_CFG_LANG_HIN, "mld", "bng", "mog", "cnr"};
        this.val_language_dtv = strArr;
        this.MAX_DTV_LANGUAGE = strArr.length;
        this.mContext = context;
        SystemProperties.get("ro.hisense.market");
    }

    public void initMicLed() {
        if (SystemProperties.getBoolean("ro.vt.ffga", true)) {
            LedFactory ledFactory = new LedFactory();
            this.mMicLedController = ledFactory;
            ledFactory.initLedFactory();
        }
    }

    public int getConfigValue(String str) {
        return getConfigValue(-1, str);
    }

    public int getConfigValue(int i, String str) {
        int configValue_native;
        synchronized (HisenseTvAPIBase.class) {
            configValue_native = HisenseTVNativeWrapper.getConfigValue_native(i, str);
        }
        return configValue_native;
    }

    public int setConfigIntArrayValue(String str, int[] iArr, int i) {
        int configIntArrayValue_native;
        synchronized (HisenseTvAPIBase.class) {
            configIntArrayValue_native = HisenseTVNativeWrapper.setConfigIntArrayValue_native(str, iArr, i);
        }
        return configIntArrayValue_native;
    }

    public int[] getConfigIntArrayValue(String str, int i) {
        return HisenseTVNativeWrapper.getConfigIntArrayValue_native(-1, i, str);
    }

    public int setConfigValue(String str, int i) {
        return setConfigValue(-1, str, i, 3);
    }

    public int setConfigValue(int i, String str, int i2, int i3) {
        int configValue_native;
        synchronized (HisenseTvAPIBase.class) {
            configValue_native = HisenseTVNativeWrapper.setConfigValue_native(i, str, i2, i3);
        }
        return configValue_native;
    }

    public String getConfigString(String str) {
        String configString_native;
        synchronized (HisenseTvAPIBase.class) {
            configString_native = HisenseTVNativeWrapper.getConfigString_native(str);
        }
        return configString_native;
    }

    public int setConfigString(String str, String str2) {
        int configString_native;
        synchronized (HisenseTvAPIBase.class) {
            configString_native = HisenseTVNativeWrapper.setConfigString_native(str, str2);
        }
        return configString_native;
    }

    public boolean isFactoryMode() {
        return 1 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FACTORY_MODE) || 255 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FACTORY_MODE);
    }

    public boolean setFactoryMode(boolean z) {
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FACTORY_MODE, z ? 1 : 0);
        if (configValue == 0 && z) {
            SystemProperties.set("persist.vt.factory.mode", "1");
            Settings.System.putInt(this.mContext.getContentResolver(), "com.jamdeo.setting.TV_FACTORY_MODE", 1);
        } else {
            SystemProperties.set("persist.vt.factory.mode", "0");
            SystemProperties.set("sys.vt.factory.switch.touser", "1");
            if (SystemProperties.getInt("sys.vt.user.switch.tofactory", 0) == 1) {
                SystemProperties.set("sys.vt.user.switch.tofactory", "0");
            }
            Settings.System.putInt(this.mContext.getContentResolver(), "com.jamdeo.setting.TV_FACTORY_MODE", 0);
        }
        return configValue == 0;
    }

    public boolean setFactoryModeToAudioPicture(int i) {
        return AvMiddleWareManager.setFactoryMode(i) == 0;
    }

    public String getMarketRegion() {
        return SystemProperties.get("ro.vendor.mtk.system.marketregion", "us");
    }

    public boolean isAgingMode() {
        boolean z = 1 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_AGING_MODE);
        Log.d("TV_HisenseTvAPI", "isAgingMode value is " + z + "\n");
        return z;
    }

    public boolean setAgingMode(boolean z) {
        Log.d("TV_HisenseTvAPI", "setAgingMode relValue is " + setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_AGING_MODE, z ? 1 : 0) + "\n");
        int agingModeEnable = HisVideoApi.setAgingModeEnable(z ? 1 : 0);
        Log.d("TV_HisenseTvAPI", "setAgingModeEnable relValue is " + agingModeEnable + "\n");
        if (agingModeEnable == 0) {
            Settings.System.putInt(this.mContext.getContentResolver(), "com.jamdeo.setting.TV_AGING_MODE", z ? 1 : 0);
        }
        return agingModeEnable == 0;
    }

    public int getToFacMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOFAC_MODE);
        if (configValue == 255) {
            return 1;
        }
        return configValue;
    }

    public boolean setToFacMode(int i) {
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TOFAC_MODE, i);
        if (configValue == 0) {
            if (i == 0) {
                try {
                    SystemProperties.set("persist.vt.tofac_mode", MtkTvRatingConvert2Goo.RATING_STR_U);
                } catch (Exception e) {
                    Log.d("TV_HisenseTvAPI", e.toString());
                }
            } else if (i == 1) {
                try {
                    SystemProperties.set("persist.vt.tofac_mode", MtkTvRatingConvert2Goo.RATING_STR_M);
                } catch (Exception e2) {
                    Log.d("TV_HisenseTvAPI", e2.toString());
                }
            }
        }
        return configValue == 0;
    }

    public boolean setWhiteBalanceValue(String str, int i) {
        int factoryWhiteBalanceOffsetBlue;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_R)) {
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceGainRed(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_G)) {
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceGainGreen(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_B)) {
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceGainBlue(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_R)) {
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceOffsetRed(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_G)) {
            factoryWhiteBalanceOffsetBlue = HisVideoApi.setFactoryWhiteBalanceOffsetGreen(i);
        } else {
            factoryWhiteBalanceOffsetBlue = str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_B) ? HisVideoApi.setFactoryWhiteBalanceOffsetBlue(i) : 0;
        }
        return factoryWhiteBalanceOffsetBlue == 0;
    }

    public int[] getWhiteBalanceValue(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_R)) {
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainRed();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_G)) {
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainGreen();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAIN_B)) {
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainBlue();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_R)) {
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetRed();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_G)) {
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetGreen();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_OFFSET_B)) {
            iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetBlue();
        }
        return iArr;
    }

    public int[] getFactoryWhiteBalanceGainRed() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getFactoryWhiteBalanceGainRed();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceGainGreen() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getFactoryWhiteBalanceGainGreen();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceGainBlue() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getFactoryWhiteBalanceGainBlue();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceOffsetRed() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getFactoryWhiteBalanceOffsetRed();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceOffsetGreen() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getFactoryWhiteBalanceOffsetGreen();
        return iArr;
    }

    public int[] getFactoryWhiteBalanceOffsetBlue() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getFactoryWhiteBalanceOffsetBlue();
        return iArr;
    }

    public boolean setWhiteBalancePanel(String str, int i) {
        return (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PANEL_TYPE) ? HisVideoApi.setPanel(i) : 0) == 0;
    }

    public int getWhiteBalancePanel(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PANEL_TYPE)) {
            return HisVideoApi.getPanel();
        }
        return 0;
    }

    public boolean setColorTemperature(String str, int i) {
        return (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_COLOR_TEMP) ? HisVideoApi.setFactoryColorTemperature(i) : 0) == 0;
    }

    public int getColorTemperature() {
        return HisVideoApi.getFactoryColorTemperature();
    }

    public boolean setPrescaleValue(String str, int i) {
        return (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOC_PRESCALE) ? HisAudioApi.setSoundSocPrescale(i) : 0) == 0;
    }

    public int getPrescaleValue(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOC_PRESCALE)) {
            return HisAudioApi.getSoundSocPrescale();
        }
        return 0;
    }

    public int[] getPictureMode() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getPictureModeByFactory();
        return iArr;
    }

    public int[] getBrightness() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getBrightnessByFactory();
        return iArr;
    }

    public int[] getContrast() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getContrastByFactory();
        return iArr;
    }

    public int[] getColor() {
        int[] iArr = new int[10];
        iArr[3] = HisVideoApi.getSaturationByFactory();
        return iArr;
    }

    public int[] getSharpness() {
        int[] iArr = new int[10];
        iArr[4] = HisVideoApi.getSharpnessByFactory();
        return iArr;
    }

    public int[] getBacklight() {
        int[] iArr = new int[10];
        iArr[5] = HisVideoApi.getBacklightPercentByFactory();
        return iArr;
    }

    public boolean setPictureModeDetailValue(String str, int i) {
        int backlightPercentByFactory;
        if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_BRIGHTNESS)) {
            backlightPercentByFactory = HisVideoApi.setBrightnessByFactory(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_CONTRAST)) {
            backlightPercentByFactory = HisVideoApi.setContrastByFactory(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_SAT)) {
            backlightPercentByFactory = HisVideoApi.setSaturationByFactory(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_VIDEO_VID_SHP)) {
            backlightPercentByFactory = HisVideoApi.setSharpnessByFactory(i);
        } else {
            backlightPercentByFactory = str.equals(MtkTvConfigTypeBase.CFG_DISP_DISP_DISP_BACK_LIGHT) ? HisVideoApi.setBacklightPercentByFactory(i) : 0;
        }
        return backlightPercentByFactory == 0;
    }

    public boolean setVideoCurveDetailValue(String str, int i) {
        int sharpnessMax;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MIN)) {
            sharpnessMax = HisVideoApi.setBrightnessMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_20)) {
            sharpnessMax = HisVideoApi.setBrightness20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MID)) {
            sharpnessMax = HisVideoApi.setBrightnessMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_80)) {
            sharpnessMax = HisVideoApi.setBrightness80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BRI_MAX)) {
            sharpnessMax = HisVideoApi.setBrightnessMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MIN)) {
            sharpnessMax = HisVideoApi.setContrastMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_20)) {
            sharpnessMax = HisVideoApi.setContrast20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MID)) {
            sharpnessMax = HisVideoApi.setContrastMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_80)) {
            sharpnessMax = HisVideoApi.setContrast80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_CNT_MAX)) {
            sharpnessMax = HisVideoApi.setContrastMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MIN)) {
            sharpnessMax = HisVideoApi.setSaturationMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_20)) {
            sharpnessMax = HisVideoApi.setSaturation20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MID)) {
            sharpnessMax = HisVideoApi.setSaturationMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_80)) {
            sharpnessMax = HisVideoApi.setSaturation80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SAT_MAX)) {
            sharpnessMax = HisVideoApi.setSaturationMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MIN)) {
            sharpnessMax = HisVideoApi.setHueMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_20)) {
            sharpnessMax = HisVideoApi.setHue20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MID)) {
            sharpnessMax = HisVideoApi.setHueMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_80)) {
            sharpnessMax = HisVideoApi.setHue80(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_HUE_MAX)) {
            sharpnessMax = HisVideoApi.setHueMax(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MIN)) {
            sharpnessMax = HisVideoApi.setSharpnessMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_20)) {
            sharpnessMax = HisVideoApi.setSharpness20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MID)) {
            sharpnessMax = HisVideoApi.setSharpnessMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_80)) {
            sharpnessMax = HisVideoApi.setSharpness80(i);
        } else {
            sharpnessMax = str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SHP_MAX) ? HisVideoApi.setSharpnessMax(i) : 0;
        }
        return sharpnessMax == 0;
    }

    public int[] getBrightnessMin() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getBrightnessMin();
        return iArr;
    }

    public int[] getBrightness20() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getBrightness20();
        return iArr;
    }

    public int[] getBrightnessMid() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getBrightnessMid();
        return iArr;
    }

    public int[] getBrightness80() {
        int[] iArr = new int[10];
        iArr[3] = HisVideoApi.getBrightness80();
        return iArr;
    }

    public int[] getBrightnessMax() {
        int[] iArr = new int[10];
        iArr[4] = HisVideoApi.getBrightnessMax();
        return iArr;
    }

    public int[] getContrastMin() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getContrastMin();
        return iArr;
    }

    public int[] getContrast20() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getContrast20();
        return iArr;
    }

    public int[] getContrastMid() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getContrastMid();
        return iArr;
    }

    public int[] getContrast80() {
        int[] iArr = new int[10];
        iArr[3] = HisVideoApi.getContrast80();
        return iArr;
    }

    public int[] getContrastMax() {
        int[] iArr = new int[10];
        iArr[4] = HisVideoApi.getContrastMax();
        return iArr;
    }

    public int[] getSaturationMin() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getSaturationMin();
        return iArr;
    }

    public int[] getSaturation20() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getSaturation20();
        return iArr;
    }

    public int[] getSaturationMid() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getSaturationMid();
        return iArr;
    }

    public int[] getSaturation80() {
        int[] iArr = new int[10];
        iArr[3] = HisVideoApi.getSaturation80();
        return iArr;
    }

    public int[] getSaturationMax() {
        int[] iArr = new int[10];
        iArr[4] = HisVideoApi.getSaturationMax();
        return iArr;
    }

    public int[] getHueMin() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getHueMin();
        return iArr;
    }

    public int[] getHue20() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getHue20();
        return iArr;
    }

    public int[] getHueMid() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getHueMid();
        return iArr;
    }

    public int[] getHue80() {
        int[] iArr = new int[10];
        iArr[3] = HisVideoApi.getHue80();
        return iArr;
    }

    public int[] getHueMax() {
        int[] iArr = new int[10];
        iArr[4] = HisVideoApi.getHueMax();
        return iArr;
    }

    public int[] getSharpnessMin() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getSharpnessMin();
        return iArr;
    }

    public int[] getSharpness20() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getSharpness20();
        return iArr;
    }

    public int[] getSharpnessMid() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getSharpnessMid();
        return iArr;
    }

    public int[] getSharpness80() {
        int[] iArr = new int[10];
        iArr[3] = HisVideoApi.getSharpness80();
        return iArr;
    }

    public int[] getSharpnessMax() {
        int[] iArr = new int[10];
        iArr[4] = HisVideoApi.getSharpnessMax();
        return iArr;
    }

    public boolean doProtectedClear() {
        int configValue = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TYPE_PROTECTED_CLEAN, 1);
        Log.i("TV_HisenseTvAPI", "doProtectedClear  relValue = " + configValue);
        if (configValue != 0 || AvMiddleWareManager.resetFactoryUnprotectedSetting() == 0) {
        }
        return false;
    }

    public boolean doFactoryOKReset() {
        if (setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_TYPE_FACTORY_OK, 1) != 0) {
            Log.i("TV_HisenseTvAPI", "doFactoryOKReset error 1");
            return false;
        }
        if (AvMiddleWareManager.resetFactoryOKSetting() != 0) {
            Log.i("TV_HisenseTvAPI", "doFactoryOKReset error 2");
            return false;
        }
        HisenseTVNativeWrapper.doresetPub();
        return true;
    }

    public boolean doFactoryOKReset(boolean z, IFactoryCleanAppsListener iFactoryCleanAppsListener) {
        Log.i("TV_HisenseTvAPI", "doFactoryOKReset.......,");
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "user_setup_complete", 0, -3);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "tv_user_setup_complete", 0, -3);
        boolean zDoFactoryOKReset = doFactoryOKReset();
        Log.e("TV_HisenseTvAPI", " setFactoryConfiguration..result.. " + zDoFactoryOKReset);
        if (getMarketRegion().equalsIgnoreCase("JP")) {
            setConfigValue(MtkTvConfigTypeBase.CFG_INPUT_SRC_INPUT_SRC_TVS_RCU_TYPE, 0);
        }
        HisenseTVNativeWrapper.setEthernetWolCtl_native(false);
        HisenseTVNativeWrapper.setWifiWolCtl_native(false);
        try {
            SystemProperties.set("ctl.reset", "/data/misc/wifi/WifiConfigStore.xml");
        } catch (Exception e) {
            Log.d("TV_HisenseTvAPI", e.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/wifi/softap.conf");
        } catch (Exception e2) {
            Log.d("TV_HisenseTvAPI", e2.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/wifi/ipconfig.txt");
        } catch (Exception e3) {
            Log.d("TV_HisenseTvAPI", e3.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/ethernet/ipconfig.txt");
        } catch (Exception e4) {
            Log.d("TV_HisenseTvAPI", e4.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/ppp/pppoe.data");
        } catch (Exception e5) {
            Log.d("TV_HisenseTvAPI", e5.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/bluedroid/bt_config.conf");
        } catch (Exception e6) {
            Log.d("TV_HisenseTvAPI", e6.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/misc/bluedroid/bt_config.bak");
        } catch (Exception e7) {
            Log.d("TV_HisenseTvAPI", e7.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/property/persistent_properties");
        } catch (Exception e8) {
            Log.d("TV_HisenseTvAPI", e8.toString());
        }
        try {
            SystemProperties.set("ctl.reset", "/data/system/users/0/settings_global.xml");
        } catch (Exception e9) {
            Log.d("TV_HisenseTvAPI", e9.toString());
        }
        Log.e("TV_HisenseTvAPI", " finish start thread cleanApps ");
        Log.e("TV_HisenseTvAPI", "at the end of the do factoryOK");
        File file = new File("/data/local/misc/third_party_bootanimation.zip");
        if (file.exists()) {
            Log.i("TV_HisenseTvAPI", "advertisement animation exist, delete return: " + file.delete());
        }
        if (!zDoFactoryOKReset) {
            return true;
        }
        try {
            SystemProperties.set("persist.vt.tofac_mode", MtkTvRatingConvert2Goo.RATING_STR_U);
            return true;
        } catch (Exception e10) {
            Log.d("TV_HisenseTvAPI", e10.toString());
            return true;
        }
    }

    public boolean doMasterClear() {
        HisenseTVNativeWrapper.setEthernetWolCtl_native(false);
        HisenseTVNativeWrapper.setWifiWolCtl_native(false);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "user_setup_complete", 0, -3);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "tv_user_setup_complete", 0, -3);
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.setFlags(268435456);
        if (this.mContext != null) {
            Log.d("TV_HisenseTvAPI", "doMasterClear  mContext is not null");
            this.mContext.sendBroadcast(intent);
            return true;
        }
        Log.d("TV_HisenseTvAPI", "doMasterClear  mContext is null");
        return false;
    }

    public boolean setBacklightModeDetailValue(String str, int i) {
        int backlightMax;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MIN)) {
            backlightMax = HisVideoApi.setBacklightMin(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_20)) {
            backlightMax = HisVideoApi.setBacklight20(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MID)) {
            backlightMax = HisVideoApi.setBacklightMid(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_80)) {
            backlightMax = HisVideoApi.setBacklight80(i);
        } else {
            backlightMax = str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_BACKLIGHT_MAX) ? HisVideoApi.setBacklightMax(i) : 0;
        }
        return backlightMax == 0;
    }

    public boolean setPwmFrequency(String str, int i) {
        return (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PWM_FREQ) ? HisVideoApi.setBacklightPWMFreq(i) : 0) == 0;
    }

    public int getPwmFrequency(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PWM_FREQ)) {
            return HisVideoApi.getBacklightPWMFreq();
        }
        return 0;
    }

    public int[] getBacklightMin() {
        int[] iArr = new int[10];
        iArr[0] = HisVideoApi.getBacklightMin();
        return iArr;
    }

    public int[] getBacklight20() {
        int[] iArr = new int[10];
        iArr[1] = HisVideoApi.getBacklight20();
        return iArr;
    }

    public int[] getBacklightMid() {
        int[] iArr = new int[10];
        iArr[2] = HisVideoApi.getBacklightMid();
        return iArr;
    }

    public int[] getBacklight80() {
        int[] iArr = new int[10];
        iArr[3] = HisVideoApi.getBacklight80();
        return iArr;
    }

    public int[] getBacklightMax() {
        int[] iArr = new int[10];
        iArr[4] = HisVideoApi.getBacklightMax();
        return iArr;
    }

    public boolean setAudioMode(String str, int i) {
        return (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOUND_MODE) ? HisAudioApi.setSoundMode(i) : 0) == 0;
    }

    public boolean setAudioModeDetailValue(String str, int i) {
        int geq;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1)) {
            geq = HisAudioApi.setGEQ(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2)) {
            geq = HisAudioApi.setGEQ(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3)) {
            geq = HisAudioApi.setGEQ(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4)) {
            geq = HisAudioApi.setGEQ(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5)) {
            geq = HisAudioApi.setGEQ(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_6)) {
            geq = HisAudioApi.setGEQ(5, i);
        } else {
            geq = str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_7) ? HisAudioApi.setGEQ(6, i) : 0;
        }
        return geq == 0;
    }

    public int[] getAllAudioModeValue(String str, int i) {
        int[] iArr = new int[10];
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_SOUND_MODE)) {
            iArr[0] = HisAudioApi.getSoundMode();
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_1)) {
            iArr[0] = HisAudioApi.getGEQ(0);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_2)) {
            iArr[0] = HisAudioApi.getGEQ(1);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_3)) {
            iArr[0] = HisAudioApi.getGEQ(2);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_4)) {
            iArr[0] = HisAudioApi.getGEQ(3);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_5)) {
            iArr[0] = HisAudioApi.getGEQ(4);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_6)) {
            iArr[0] = HisAudioApi.getGEQ(5);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_EQ_BAND_7)) {
            iArr[0] = HisAudioApi.getGEQ(6);
        }
        return iArr;
    }

    public boolean setAudioCurveDetailValue(String str, int i) {
        int soundVolCurveVal;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MIN)) {
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_20)) {
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MID)) {
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_75)) {
            soundVolCurveVal = HisAudioApi.setSoundVolCurveVal(3, i);
        } else {
            soundVolCurveVal = str.equals(MtkTvConfigTypeBase.CFG_FACTORY_AUD_VOL_MAX) ? HisAudioApi.setSoundVolCurveVal(4, i) : 0;
        }
        return soundVolCurveVal == 0;
    }

    public boolean setAudioCurveDetailValueNew(int i, int i2) {
        return HisAudioApi.setSoundVolCurveVal(i, i2) == 0;
    }

    public int[] getVolumeCurve0() {
        int[] iArr = new int[10];
        iArr[0] = HisAudioApi.getSoundVolCurveVal(0);
        return iArr;
    }

    public int[] getVolumeCurve25() {
        int[] iArr = new int[10];
        iArr[1] = HisAudioApi.getSoundVolCurveVal(1);
        return iArr;
    }

    public int[] getVolumeCurve50() {
        int[] iArr = new int[10];
        iArr[2] = HisAudioApi.getSoundVolCurveVal(2);
        return iArr;
    }

    public int[] getVolumeCurve75() {
        int[] iArr = new int[10];
        iArr[3] = HisAudioApi.getSoundVolCurveVal(3);
        return iArr;
    }

    public int[] getVolumeCurve100() {
        int[] iArr = new int[10];
        iArr[4] = HisAudioApi.getSoundVolCurveVal(4);
        return iArr;
    }

    public int getVolumeCurve(int i) {
        return HisAudioApi.getSoundVolCurveVal(i);
    }

    public boolean setFactoryFunctionNumber(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public String getFactoryFunctionNumber(String str) {
        return getConfigString(str);
    }

    public boolean setBootUpLogo(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FAC_BOOTUP_LOGO, i) == 0;
    }

    public int getBootUpLogo() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FAC_BOOTUP_LOGO);
    }

    public boolean setTvLanguage(String str, int i) {
        int configValue = setConfigValue(str, i);
        if (str.equals(MtkTvConfigTypeBase.CFG_SYSTEM_LANGUAGE) && i > 0 && i < this.MAX_DTV_LANGUAGE) {
            String str2 = this.val_language_dtv[i];
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_SUBTITLE_SUBTITLE_LANG, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_SUBTITLE_SUBTITLE_LANG_2ND, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_GUI_AUD_LANG_AUD_LANGUAGE, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_GUI_AUD_LANG_AUD_2ND_LANGUAGE, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_TTX_LANG_TTX_DIGTL_ES_SELECT, str2);
            MtkTvConfig.getInstance().setLanguage(MtkTvConfigTypeBase.CFG_GUI_LANG_GUI_LANGUAGE, str2);
        }
        return configValue == 0;
    }

    public int getTvLanguage(String str) {
        return getConfigValue(str);
    }

    public boolean setTvCountry(String str, int i) {
        int configValue = setConfigValue(str, i);
        HisenseTVNativeWrapper.factoryWriteFile_native("/perm/country.cfg", i + "");
        Log.v("TV_HisenseTvAPI", "[wifiCountry path  /perm/country.cfg");
        return configValue == 0;
    }

    public int getTvCountry(String str) {
        return getConfigValue(str);
    }

    public boolean setTvBrand(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BRAND, str) == 0;
    }

    public String getTvBrand() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BRAND);
    }

    public String getSoftwareVersion() {
        return SystemProperties.get("ro.vt.build.softversion");
    }

    public String getMachineName(String str) {
        return SystemProperties.get("ro.vt.product.model");
    }

    public boolean setMachineNumber(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public String getMachineNumber(String str) {
        return getConfigString(str);
    }

    public boolean setSerialNewNumber(String str, String str2) {
        return setConfigString(str, str2) == 0;
    }

    public String getSerialNewNumber(String str) {
        return getConfigString(str);
    }

    public boolean setOverScanTop(int[] iArr) {
        return HisVideoApi.setOverscanTop(iArr[0]) == 0;
    }

    public int[] getOverScanTop() {
        int[] iArr = new int[4];
        iArr[0] = HisVideoApi.getOverscanTop();
        return iArr;
    }

    public boolean setOverScanBottom(int[] iArr) {
        return HisVideoApi.setOverscanBottom(iArr[1]) == 0;
    }

    public int[] getOverScanBottom() {
        int[] iArr = new int[4];
        iArr[1] = HisVideoApi.getOverscanBottom();
        return iArr;
    }

    public boolean setOverScanLeft(int[] iArr) {
        return HisVideoApi.setOverscanLeft(iArr[2]) == 0;
    }

    public int[] getOverScanLeft() {
        int[] iArr = new int[4];
        iArr[2] = HisVideoApi.getOverscanLeft();
        return iArr;
    }

    public boolean setOverScanRight(int[] iArr) {
        return HisVideoApi.setOverscanRight(iArr[3]) == 0;
    }

    public int[] getOverScanRight() {
        int[] iArr = new int[4];
        iArr[3] = HisVideoApi.getOverscanRight();
        return iArr;
    }

    public boolean setTestPatternValue(String str, int[] iArr, int i) {
        int insidePattern;
        if (!str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_WB_TEST_PATTERN)) {
            insidePattern = 0;
        } else if (iArr[0] == 0) {
            insidePattern = HisVideoApi.setInsidePattern(255);
        } else {
            insidePattern = HisVideoApi.setInsidePattern(iArr[1]);
        }
        return insidePattern == 0;
    }

    public boolean setFirstTimePowerOn(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public int getRegionValue(String str) {
        return getConfigValue(str);
    }

    public boolean setRegionValue(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public int getFirstTimePowerOn(String str) {
        return getConfigValue(str);
    }

    public int getByPassValue(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PQ_BYPASS)) {
            return HisVideoApi.getBypassPqEnable();
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_BYPASS)) {
            return HisVideoApi.getBypassGammaEnable();
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SCE_BYPASS)) {
            return HisVideoApi.getBypassSceEnable();
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_XVYCC_BYPASS)) {
            return HisVideoApi.getBypassXvyccEnable();
        }
        return 0;
    }

    public int getAVCEnableValue(String str) {
        return HisAudioApi.getAVCFeature(0);
    }

    public boolean setByPassValue(String str, int i) {
        int bypassXvyccEnable;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_PQ_BYPASS)) {
            bypassXvyccEnable = HisVideoApi.setBypassPqEnable(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_BYPASS)) {
            bypassXvyccEnable = HisVideoApi.setBypassGammaEnable(i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_SCE_BYPASS)) {
            bypassXvyccEnable = HisVideoApi.setBypassSceEnable(i);
        } else {
            bypassXvyccEnable = str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_XVYCC_BYPASS) ? HisVideoApi.setBypassXvyccEnable(i) : 0;
        }
        return bypassXvyccEnable == 0;
    }

    public boolean setAVCEnableValue(String str, int i) {
        return HisAudioApi.setAVCFeature(0, i) == 0;
    }

    public int getVcomValue(String str) {
        return getConfigValue(str);
    }

    public boolean setVcomValue(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public int getUartCtrlState() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_UART_CTRL);
        if (configValue == 1 || configValue == 255) {
            return 1;
        }
        return configValue;
    }

    public boolean setUartCtrlState(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_UART_CTRL, i) == 0;
    }

    public int getTvRunTime(String str) {
        return getConfigValue(str);
    }

    public boolean setTvRunTime(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public int getPowerControlMode(String str) {
        return getConfigValue(str);
    }

    public boolean setPowerControlMode(String str, int i) {
        return setConfigValue(str, i) == 0;
    }

    public void startTestB2BHWPortWorkThread() {
        Log.v("TV_HisenseTvAPI", "[B2B]sstartTestB2BHWPortWorkThread Thread.\n");
        HisenseTVNativeWrapper.startTestB2BHWPortWorkThread_native();
    }

    public void startSerialWorkThread() {
        Log.v("TV_HisenseTvAPI", "startSerialWorkThread.\n");
        HisenseTVNativeWrapper.startSerialWorkThread_native();
    }

    public boolean writeToExternalControl(int i, byte[] bArr, int i2, int i3) {
        Log.d("TV_HisenseTvAPI", "Calling writeToExternalControl interface in handler.");
        if (HisenseTVNativeWrapper.writeToExternalControl_native(i, bArr, i2, i3) == 0) {
            return true;
        }
        Log.e("TV_HisenseTvAPI", "Write to serial port jni error");
        return false;
    }

    public boolean setGammaOperation(String str, int[] iArr, int i) {
        int testPattern;
        if (str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_FACTORY_GAMMA_SELECT)) {
            testPattern = HisVideoApi.setAutoGammaEnable(iArr[0]);
        } else {
            testPattern = str.equals(MtkTvConfigTypeBase.CFG_FACTORY_VID_GAMMA_ADJUST_PATTERN) ? HisVideoApi.setTestPattern(transformTestPatternIndex(iArr)) : 0;
        }
        return testPattern == 0;
    }

    public int getAudioPeq(String str) {
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(0);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(1);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(2);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(3);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(4);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(5);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(6);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_FREQ)) {
            return HisAudioApi.getSoundSocPEQFreq(7);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(0);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(1);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(2);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(3);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(4);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(5);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(6);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_GAIN)) {
            return HisAudioApi.getSoundSocPEQGain(7);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_Q)) {
            return HisAudioApi.getSoundSocPEQQ(0);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_Q)) {
            return HisAudioApi.getSoundSocPEQQ(1);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_Q)) {
            return HisAudioApi.getSoundSocPEQQ(2);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_Q)) {
            return HisAudioApi.getSoundSocPEQQ(3);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_Q)) {
            return HisAudioApi.getSoundSocPEQQ(4);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_Q)) {
            return HisAudioApi.getSoundSocPEQQ(5);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_Q)) {
            return HisAudioApi.getSoundSocPEQQ(6);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_Q)) {
            return HisAudioApi.getSoundSocPEQQ(7);
        }
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_HPF_FREQUENCY)) {
            return HisAudioApi.getSoundSocHPFFeature(1);
        }
        return 0;
    }

    public boolean setAudioPeq(String str, int i) {
        int soundSocHPFFeature;
        if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(5, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(6, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_FREQ)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQFreq(7, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(5, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(6, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_GAIN)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQGain(7, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_1_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(0, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_2_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(1, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_3_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(2, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_4_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(3, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_5_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(4, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_6_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(5, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_7_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(6, i);
        } else if (str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_PEQ_8_Q)) {
            soundSocHPFFeature = HisAudioApi.setSoundSocPEQQ(7, i);
        } else {
            soundSocHPFFeature = str.equals(MtkTvConfigTypeBase.CFG_FAC_AUD_HPF_FREQUENCY) ? HisAudioApi.setSoundSocHPFFeature(1, i) : 0;
        }
        return soundSocHPFFeature == 0;
    }

    public boolean setAudHpfStatus(boolean z) {
        return HisAudioApi.setSoundSocHPFFeature(0, z ? 1 : 0) == 0;
    }

    public boolean getAudHpfStatus() {
        return HisAudioApi.getSoundSocHPFFeature(0) == 1;
    }

    public boolean setAudPeqEnableStatus(boolean z) {
        return HisAudioApi.setSoundSocPEQEnable(z ? 1 : 0) == 0;
    }

    public int getAudPeqEnableStatus() {
        return HisAudioApi.getSoundSocPEQEnable();
    }

    public boolean setDTSSwitch(int i) {
        Log.d("TV_HisenseTvAPI", "set DTSSwitch : value = " + i);
        return setConfigValue(MtkTvConfigTypeBase.CFG_FAC_AUD_FACTORY_MODE, i) == 0;
    }

    public String getSlaveBluStatus() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SLAVE_BLU_STATUS);
        Log.i("TV_HisenseTvAPI", "getSlaveBluStatus: " + configString);
        return configString;
    }

    public int getBluNums() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_BLU_NUMS);
    }

    public int getAVCThreshold() {
        return HisAudioApi.getAVCFeature(1);
    }

    public boolean setAVCThreshold(int i) {
        return HisAudioApi.setAVCFeature(1, i) == 0;
    }

    public int getSocDrc() {
        return HisAudioApi.getSoundSocDRCFeature(1);
    }

    public boolean setSocDrc(int i) {
        return HisAudioApi.setSoundSocDRCFeature(1, i) == 0;
    }

    public boolean setSocDrcEnable(boolean z) {
        return HisAudioApi.setSoundSocDRCFeature(0, z ? 1 : 0) == 1;
    }

    public boolean getSocDrcEnable() {
        return HisAudioApi.getSoundSocDRCFeature(0) == 1;
    }

    public List<MtkTvInputSourceBase.InputSourceRecord> getCurrentSupportSource() {
        int inputSourceTotalNumber = MtkTvInputSource.getInstance().getInputSourceTotalNumber();
        Log.i("TV_HisenseTvAPI", "getCurrentSupportSource record num = " + inputSourceTotalNumber);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < inputSourceTotalNumber; i++) {
            MtkTvInputSourceBase.InputSourceRecord inputSourceRecord = new MtkTvInputSourceBase.InputSourceRecord();
            MtkTvInputSource.getInstance().getInputSourceRecbyidx(i, inputSourceRecord);
            Log.i("TV_HisenseTvAPI", "getCurrentSupportSource record name = " + inputSourceRecord.getInputType().name());
            Log.i("TV_HisenseTvAPI", "getCurrentSupportSource record name = " + inputSourceRecord.getInternalIdx());
            Log.i("TV_HisenseTvAPI", "getCurrentSupportSource record name = " + inputSourceRecord.getId());
            arrayList.add(inputSourceRecord);
        }
        return arrayList;
    }

    public boolean setOledPixelShiftTypeValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TYPE, i) == 0;
    }

    public int getOledPixelShiftTypeValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TYPE);
    }

    public boolean setOledPixelShiftTimerValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TIMER, i) == 0;
    }

    public int getOledPixelShiftTimerValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_TIMER);
    }

    public boolean setOledPixelShiftShapeValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_SHAPE, i) == 0;
    }

    public int getOledPixelShiftShapeValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_PIXEL_SHIFT_SHAPE);
    }

    public boolean isHaveFte() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "user_setup_complete", -3) == 0 || Settings.Secure.getInt(this.mContext.getContentResolver(), "tv_user_setup_complete", -3) == 0;
    }

    public int isSupportVcom() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_SUPPORT_VCOM);
    }

    public boolean isSupportSecondVcom() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_SUPPORT_2VCOM) == 1;
    }

    public boolean setMICTestValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_TEST, i) == 0;
    }

    public int getMICTestValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_TEST);
    }

    public boolean setWifiCheckConfigValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_WIFI_CONFIG_CHECK, i) == 0;
    }

    public boolean setZDMICValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_ZD_MIC, i) == 0;
    }

    public int getZDMICValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_ZD_MIC);
    }

    public boolean setCustomerTypeValue(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_CUSTOMER_TYPE, str) == 0;
    }

    public String getCustomerTypeValue() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_CUSTOMER_TYPE);
    }

    public boolean setColorTuner(int i) {
        return HisVideoApi.setFactoryColorTunerColor(i) == 0;
    }

    public int getColorTuner() {
        return HisVideoApi.getFactoryColorTunerColor();
    }

    public boolean setCTHue(int i) {
        return HisVideoApi.setFactoryColorTunerHue(i) == 0;
    }

    public int getCTHue() {
        return HisVideoApi.getFactoryColorTunerHue();
    }

    public boolean setCTSaturation(int i) {
        return HisVideoApi.setFactoryColorTunerSaturation(i) == 0;
    }

    public int getCTSaturation() {
        return HisVideoApi.getFactoryColorTunerSaturation();
    }

    public boolean setCTBrightness(int i) {
        return HisVideoApi.setFactoryColorTunerBrightness(i) == 0;
    }

    public int getCTBrightness() {
        return HisVideoApi.getFactoryColorTunerBrightness();
    }

    public boolean setPictureMode(int i) {
        return HisVideoApi.setPictureModeByFactory(i) == 0;
    }

    public boolean setWB20PointLevel(int i) {
        return HisVideoApi.setFactoryMultiPointWhiteBalanceLevel(i) == 0;
    }

    public int getWB20PointLevel() {
        return HisVideoApi.getFactoryMultiPointWhiteBalanceLevel();
    }

    public boolean setWB20PointRed(int i) {
        return HisVideoApi.setFactoryMultiPointWhiteBalanceRed(i) == 0;
    }

    public int getWB20PointRed() {
        return HisVideoApi.getFactoryMultiPointWhiteBalanceRed();
    }

    public boolean setWB20PointGreen(int i) {
        return HisVideoApi.setFactoryMultiPointWhiteBalanceGreen(i) == 0;
    }

    public int getWB20PointGreen() {
        return HisVideoApi.getFactoryMultiPointWhiteBalanceGreen();
    }

    public boolean setWB20PointBlue(int i) {
        return HisVideoApi.setFactoryMultiPointWhiteBalanceBlue(i) == 0;
    }

    public int getWB20PointBlue() {
        return HisVideoApi.getFactoryMultiPointWhiteBalanceBlue();
    }

    public boolean setEnterVcomVideoParameter(int i) {
        setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_ENTER_VCOM, 1);
        return HisVideoApi.setVcomTestEnable(1) == 0;
    }

    public boolean setExitVcomVideoParameter(int i) {
        setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_ENTER_VCOM, 0);
        return HisVideoApi.setVcomTestEnable(0) == 0;
    }

    private int transformTestPatternIndex(int[] iArr) {
        int i = 2;
        int[] iArr2 = new int[2];
        if (iArr[0] == 0) {
            return 255;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == 255) {
                iArr2[0] = iArr2[0] + 1;
                iArr2[1] = i2;
            }
        }
        Log.d("TV_HisenseTvAPI", "describe[0] = " + iArr2[0] + " ; describe[1]" + iArr2[1]);
        if (iArr2[0] == 0) {
            i = 4;
        } else if (iArr2[0] == 3) {
            i = 3;
        } else if (iArr2[1] == 1) {
            i = 0;
        } else if (iArr2[1] == 2) {
            i = 1;
        }
        Log.d("TV_HisenseTvAPI", "transformTestPatternIndex = " + i);
        return i;
    }

    public boolean setFactoryOperatorStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OPERATOR_SATUS, i) == 0;
    }

    public int getFactoryOperatorStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OPERATOR_SATUS);
    }

    public int[] getSignalVideoFormat() {
        return HisVideoApi.getSignalVideoFormat();
    }

    public int setCurrentSource(int i) {
        return AvMiddleWareManager.setCurrentSource(i);
    }

    public int getMicSwitchStatus() {
        String str;
        if (this.mMicLedController == null || (str = SystemProperties.get("ro.product.board")) == null || str.equalsIgnoreCase("")) {
            return 0;
        }
        if (str.equalsIgnoreCase("MT9900")) {
            return this.mMicLedController.GetMicMuteStatus(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED);
        }
        return this.mMicLedController.GetMicMuteStatus(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED);
    }

    public int getAutoGammaResult() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_AUTO_GAMMA_CHECK);
    }

    public int setAutoGammaEnable(int i) {
        return HisVideoApi.setAutoGammaEnable(i);
    }

    public String getAutoGammaList() {
        return HisVideoApi.getAutoGammaListStr();
    }

    public int[] getAutoGammaColorTemperatureList() {
        ArrayList<Integer> autoGammaList = HisVideoApi.getAutoGammaList();
        if (autoGammaList == null || autoGammaList.stream() == null) {
            return null;
        }
        return autoGammaList.stream().mapToInt(new ToIntFunction() { // from class: com.hisense.support.tv.-$$Lambda$HisenseTvAPIBase$Lz2VYA4URK8bgkauh8W2rKvp7mw
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
    }

    public boolean setUpgradeFlag(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_UPGRADE_FLAG, i) == 0;
    }

    public int getUpgradeFlag() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_UPGRADE_FLAG);
    }

    public int setMicLed(int i) {
        LedFactory ledFactory = this.mMicLedController;
        if (ledFactory == null) {
            return 1;
        }
        ledFactory.setMicLed(i);
        return 1;
    }

    public void setDBX(int i) {
        setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_AUD_DBX_FOR_FAC, i);
    }

    public int adjustGammaPattenr(int i, int i2, int i3, int i4) {
        return HisVideoApi.setRGBPattern(i, i2, i3, i4);
    }

    public int writeFile(String str, String str2, boolean z, boolean z2) {
        return HisensePlatformConfig.writeFile(str, str2, z, z2);
    }

    public int removeFile(String str) {
        return HisensePlatformConfig.removeFile(str);
    }

    public int getLocalDimmingState() {
        return HisVideoApi.getLocalDimming();
    }

    public int setLocalDimmingState(int i) {
        return HisVideoApi.setLocalDimming(i);
    }

    public int setLocalDimmingSwitch(boolean z) {
        return HisVideoApi.setLocalDimming(z ? 1 : 0);
    }

    public int getLVDSEnable() {
        return 1 == getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_ONOFF) ? 0 : 1;
    }

    public boolean setSSCVByOneEnable(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_ONOFF, i == 1 ? 0 : 1) == 0;
    }

    public int getSSCVByOneRange() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_STEP);
    }

    public boolean setSSCVByOneRange(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_STEP, i) == 0;
    }

    public int getSSCVByOneSpan() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_SPAN);
    }

    public boolean setLvdsDetailValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_LVDS_SPAN, i) == 0;
    }

    public boolean isDdrSscEnable() {
        return 1 != getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_ONOFF);
    }

    public int setDdrSscEnable(boolean z) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_ONOFF, z ? 1 : 0) == 0 ? 0 : -1;
    }

    public int getDdrSpan() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_STEP);
    }

    public int setDdrSpan(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_STEP, i) == 0 ? 0 : -1;
    }

    public int getDdrSscFreq() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_SPAN);
    }

    public int setSSCDDRModu(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DDR_SPAN, i) == 0 ? 0 : -1;
    }

    public int setMacAddressForFac(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MAC_ADDRESS, str);
    }

    public String getFacMacAddress() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_FAC_MAC_ADDRESS);
        Log.d("TV_HisenseTvAPI", "getFacMacAddress, macAddr = " + configString + "\n");
        if (configString == null || configString.isEmpty() || "00:0C:E7:06:00:00".equalsIgnoreCase(configString)) {
            return null;
        }
        return configString;
    }

    public String getTconVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_TCON_VERSION);
        Log.d("TV_HisenseTvAPI", "getTconVersion, tconVersion = " + configString + "\n");
        return configString;
    }

    public String getBluVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_BLU_VERSION);
        Log.d("TV_HisenseTvAPI", "getBluVersion, bluVersion = " + configString + "\n");
        return configString;
    }

    public String getSlaveBluVersion() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SLAVE_BLU_VERSION);
        Log.d("TV_HisenseTvAPI", "getSlaveBluVersion, SlaveBluVersion = " + configString + "\n");
        return configString;
    }

    public int set2PWBOffsetRedDelta(int i) {
        Log.d("TV_HisenseTvAPI", "set2PWBOffsetRedDelta val:" + i);
        return HisVideoApi.set2PWBOffsetRedDelta(i) == 0 ? 0 : -1;
    }

    public int get2PWBOffsetRedDelta() {
        Log.d("TV_HisenseTvAPI", "get2PWBOffsetRedDelta");
        return HisVideoApi.get2PWBOffsetRedDelta();
    }

    public int set2PWBOffsetGreenDelta(int i) {
        Log.d("TV_HisenseTvAPI", "set2PWBOffsetGreenDelta val:" + i);
        return HisVideoApi.set2PWBOffsetGreenDelta(i) == 0 ? 0 : -1;
    }

    public int get2PWBOffsetGreenDelta() {
        Log.d("TV_HisenseTvAPI", "get2PWBOffsetGreenDelta");
        return HisVideoApi.get2PWBOffsetGreenDelta();
    }

    public int set2PWBOffsetBlueDelta(int i) {
        Log.d("TV_HisenseTvAPI", "set2PWBOffsetBlueDelta val:" + i);
        return HisVideoApi.set2PWBOffsetBlueDelta(i) == 0 ? 0 : -1;
    }

    public int get2PWBOffsetBlueDelta() {
        Log.d("TV_HisenseTvAPI", "get2PWBOffsetBlueDelta");
        return HisVideoApi.get2PWBOffsetBlueDelta();
    }

    public int set2PWBGainRedDelta(int i) {
        Log.d("TV_HisenseTvAPI", "set2PWBGainRedDelta val:" + i);
        return HisVideoApi.set2PWBGainRedDelta(i) == 0 ? 0 : -1;
    }

    public int get2PWBGainRedDelta() {
        Log.d("TV_HisenseTvAPI", "get2PWBGainRedDelta");
        return HisVideoApi.get2PWBGainRedDelta();
    }

    public int set2PWBGainGreenDelta(int i) {
        Log.d("TV_HisenseTvAPI", "set2PWBGainGreenDelta val:" + i);
        return HisVideoApi.set2PWBGainGreenDelta(i) == 0 ? 0 : -1;
    }

    public int get2PWBGainGreenDelta() {
        Log.d("TV_HisenseTvAPI", "get2PWBGainGreenDelta");
        return HisVideoApi.get2PWBGainGreenDelta();
    }

    public int set2PWBGainBlueDelta(int i) {
        Log.d("TV_HisenseTvAPI", "set2PWBGainBlueDelta val:" + i);
        return HisVideoApi.set2PWBGainBlueDelta(i) == 0 ? 0 : -1;
    }

    public int get2PWBGainBlueDelta() {
        Log.d("TV_HisenseTvAPI", "get2PWBGainBlueDelta");
        return HisVideoApi.get2PWBGainBlueDelta();
    }

    public int getProjectId() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROJECT_ID);
    }

    public int setProjectId(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_PROJECT_ID, i);
    }

    public String getChipId() {
        String configString = getConfigString(MtkTvConfigTypeBase.CFG_SYSTEM_CHIP_ID);
        Log.d("TV_HisenseTvAPI", "getChipId, chipid = " + configString + "\n");
        return configString;
    }

    public boolean setBalance(int i) {
        return HisAudioApi.setBalance(i) == 0;
    }

    public int getBalance() {
        return HisAudioApi.getBalance();
    }

    public int getSoundVolCurveMin() {
        return HisAudioApi.getSoundVolCurveMin();
    }

    public int getSoundVolCurveMax() {
        return HisAudioApi.getSoundVolCurveMax();
    }

    public void setAutoGammaStartTunning(int i) {
        HisVideoApi.setAutoGammaTuning(i);
    }

    public int getAspectRatio() {
        return HisVideoApi.getAspectRatio();
    }

    public int setAspectRatio(int i) {
        return HisVideoApi.setAspectRatio(i);
    }

    public int setActiveContrast(int i) {
        return HisVideoApi.setActiveContrast(i);
    }

    public int getSensorValue() {
        return HisVideoApi.getLightSensorValue();
    }

    public int resetWhteBalance() {
        return HisVideoApi.resetAllFac2PWhiteBalance();
    }

    public int[] get5GChannel() {
        return getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_5G_CHANNEL_INFO, 100);
    }

    public int getTunnerGpioStatus() {
        Log.d("TV_HisenseTvAPI", "getTunnerGpioStatus");
        return getConfigValue(MtkTvConfigTypeBase.CFG_SYSTEM_TUNNER_GPIO_STATUS);
    }

    public int setEARCStatus(int i) {
        return HisHdmiApi.setEarcEnable(i);
    }

    public int getEARCStatus() {
        return HisHdmiApi.getEarcStatus();
    }

    public int setLocalDimmingCheck(int i) {
        return HisVideoApi.setLocalDimmingCheck(i);
    }

    public int getLuxValue() {
        return HisVideoApi.getDolbyIQLux();
    }

    public int getLightSensorCurve(int i, int i2) {
        return HisVideoApi.getLightSensorCurve(i, i2);
    }

    public int setLightSensorCurve(int i, int i2, int i3) {
        return HisVideoApi.setLightSensorCurve(i, i2, i3);
    }

    public int isFileExit(String str) {
        return HisensePlatformConfig.isFileOrDirExist(str);
    }

    public boolean setVcomArrayValue(int[] iArr, int i) {
        return setConfigValue(iArr[0] == 2 ? MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_2 : MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_1, iArr[1]) == 0;
    }

    public boolean updateVcomArrayValue(int[] iArr, int i) {
        return setConfigValue(iArr[0] == 2 ? MtkTvConfigTypeBase.CFG_FACTORY_VCOM_SAVE_2 : MtkTvConfigTypeBase.CFG_FACTORY_VCOM_SAVE_1, iArr[1]) == 0;
    }

    public int getUHDVcomValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_1);
    }

    public int getFHDVcomValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_VALUE_2);
    }

    public boolean setVcomPanelType(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VCOM_PANEL_TYPE, i) == 0;
    }

    public int getDolbyIQEnable() {
        return HisVideoApi.getDolbyIQEnable();
    }

    public int setDolbyIQUserBinEnable(int i) {
        return HisVideoApi.setDolbyIQUserBinEnable(i);
    }

    public int getDolbyIQUserBinEnable() {
        return HisVideoApi.getDolbyIQUserBinEnable();
    }

    public int setDolbyIQUserBinUpdate(String str) {
        return HisVideoApi.setDolbyIQUserBinUpdate(str);
    }

    public String getDolbyIQUserBinInfo() {
        return HisVideoApi.getDolbyIQUserBinInfo();
    }

    public int getDemuraSwitchStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_SWITCH_STATUS);
    }

    public int setDemuraSwitchStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_SWITCH_STATUS, i);
    }

    public int setDemuraSpiStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_SPI_STATUS, i);
    }

    public int setDemuraResetTcon(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_RESET_TCON, i);
    }

    public int setDemuraTconStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_TCON_STATUS, i);
    }

    public String getAutoGammaStatus() {
        return HisVideoApi.getAutoGammaStatus();
    }

    public int getMicDbDiffSelect() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_DB_DIFF_SELECT);
    }

    public int setMicDbDiffSelect(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_MIC_DB_DIFF_SELECT, i);
    }

    public int getIsSubwooferSupport() {
        return HisAudioApi.getIsSubwooferSupport();
    }

    public int setMultiDigitalAMPValueByDevId(int i, int i2, int i3) {
        return HisAudioApi.setMultiDigitalAMPValueByDevId(i, i2, i3);
    }

    public int getMultiDigitalAMPValueByDevId(int i, int i2) {
        return HisAudioApi.getMultiDigitalAMPValueByDevId(i, i2);
    }

    public int setMultiBalance(int i) {
        return HisAudioApi.setMultiBalance(i);
    }

    public int getMultiBalance() {
        return HisAudioApi.getMultiBalance();
    }

    public int getLaserMcuVersion() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MCU_VERSION);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserMcuVersion = " + configValue + "\n");
        return configValue;
    }

    public int getLaserDlpVersion() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_DLP_VERSION);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserDlpVersion = " + configValue + "\n");
        return configValue;
    }

    public int getLaserForeignBodyDetectSwitch() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FOREIGN_BODY_DETECT_SWITCH);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserForeignBodyDetectSwitch = " + configValue + "\n");
        return configValue;
    }

    public int setLaserForeignBodyDetectSwitch(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserForeignBodyDetectSwitch = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FOREIGN_BODY_DETECT_SWITCH, i);
    }

    public int getLaserSoftwareUpgradeMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SOFTWARE_UPGRADE_MODE);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserSoftwareUpgradeMode = " + configValue + "\n");
        return configValue;
    }

    public int setLaserSoftwareUpgradeMode(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserSoftwareUpgradeMode = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SOFTWARE_UPGRADE_MODE, i);
    }

    public int getLaserAlertLedOnOff() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_ALERT_LED_ON_OFF);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserAlertLedOnOff = " + configValue + "\n");
        return configValue;
    }

    public int setLaserAlertLedOnOff(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserAlertLedOnOff = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_ALERT_LED_ON_OFF, i);
    }

    public int getLaserTemperatureProtectSwitch() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_TEMPERATURE_PROTECT_SWITCH);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserTemperatureProtectSwitch = " + configValue + "\n");
        return configValue;
    }

    public int setLaserTemperatureProtectSwitch(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserTemperatureProtectSwitch = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_TEMPERATURE_PROTECT_SWITCH, i);
    }

    public int setLaserMirrorAdjustSwitch(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserMirrorAdjustSwitch = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MIRROR_ADJUST_SWITCH, i);
    }

    public int setLaserMirrorAdjustValue(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserMirrorAdjustValue = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MIRROR_ADJUST_VALUE, i);
    }

    public int getLaserMirrorGainValue() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MIRROR_GAIN_VALUE);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserMirrorGainValue = " + configValue + "\n");
        return configValue;
    }

    public int getLaserScreenType() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SCREENTYPE);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserScreenType = " + configValue + "\n");
        return configValue;
    }

    public int setLaserScreenType(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserScreenType = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SCREENTYPE, i);
    }

    public int getLaserEyeProtectSwitch() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_EYE_PROTECT_SWITCH);
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserEyeProtectSwitch = " + configValue + "\n");
        return configValue;
    }

    public int setLaserEyeProtectSwitch(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setLaserEyeProtectSwitch = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_EYE_PROTECT_SWITCH, i);
    }

    public int setCleanStartUpTime(int i) {
        Log.v("TV_HisenseTvAPI", "[Laser] setCleanStartUpTime = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLEAN_STARTUP_TIME, i);
    }

    public byte[] getRuningTime() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_STATUS, 3);
        if (configIntArrayValue == null || configIntArrayValue.length < 3) {
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255), (byte) ((configIntArrayValue[2] >> 0) & 255), (byte) ((configIntArrayValue[2] >> 8) & 255)};
        Log.v("TV_HisenseTvAPI", "[Laser] status[0] :  bytes[0] = " + ((int) bArr[0]) + ", bytes[1] = " + ((int) bArr[1]) + ", bytes[2] = " + ((int) bArr[2]) + ", bytes[3] = " + ((int) bArr[3]) + "\n");
        Log.v("TV_HisenseTvAPI", "[Laser] status[1] :  bytes[4] = " + ((int) bArr[4]) + ", bytes[5] = " + ((int) bArr[5]) + ", bytes[6] = " + ((int) bArr[6]) + ", bytes[7] = " + ((int) bArr[7]) + "\n");
        StringBuilder sb = new StringBuilder();
        sb.append("[Laser] status[2] :  bytes[8] = ");
        sb.append((int) bArr[8]);
        sb.append(", bytes[9] = ");
        sb.append((int) bArr[9]);
        sb.append("\n");
        Log.v("TV_HisenseTvAPI", sb.toString());
        return bArr;
    }

    public int getLaserRuningTime() {
        byte[] runingTime = getRuningTime();
        if (runingTime == null || runingTime.length < 2) {
            return 0;
        }
        int[] iArr = {runingTime[0] & 255, runingTime[1] & 255};
        int i = (iArr[0] << 8) | iArr[1];
        Log.v("TV_HisenseTvAPI", "[Laser] getLaserRuningTime = " + i + "\n");
        return i;
    }

    public boolean setLaserAutoGraphCorrection(int[] iArr, int i) {
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_AUTO_GRAPH_CORRECTION, iArr, i) == 0;
    }

    public boolean setLaserFocus(int i) {
        Log.v("TV_HisenseTvAPI", "[laser]setLaserFocus = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FOCUS, i) == 0;
    }

    public boolean setLaserCW1(int i) {
        Log.v("TV_HisenseTvAPI", "[laser]setLaserCW1 = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW1, i) == 0;
    }

    public int getLaserCW1() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW1);
        Log.v("TV_HisenseTvAPI", "[laser]getLaserCW1 = " + configValue + "\n");
        return configValue;
    }

    public boolean setLaserCW2(int i) {
        Log.v("TV_HisenseTvAPI", "[laser]setLaserCW2 = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW2, i) == 0;
    }

    public int getLaserCW2() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CW2);
        Log.v("TV_HisenseTvAPI", "[laser]getLaserCW2 = " + configValue + "\n");
        return configValue;
    }

    public byte[] getFanSpeed() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_FAN_SPEED, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.v("TV_HisenseTvAPI", "[Laser] getFanSpeed return data error!!");
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255)};
        Log.v("TV_HisenseTvAPI", "[laser] getFanSpeed:status[0] = " + configIntArrayValue[0] + ", status[1] = " + configIntArrayValue[1] + "\n");
        Log.v("TV_HisenseTvAPI", "[laser] getFanSpeed:bytes[0] = " + ((int) bArr[0]) + ", bytes[1] = " + ((int) bArr[1]) + ", bytes[2] = " + ((int) bArr[2]) + ", bytes[3] = " + ((int) bArr[3]) + ", bytes[4] = " + ((int) bArr[4]) + ", bytes[5] = " + ((int) bArr[5]) + "\n");
        return bArr;
    }

    public byte[] getLaserTemperature() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_TEMPERATURE, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.v("TV_HisenseTvAPI", "[Laser] getLaserTemperature return data error!!");
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
        Log.v("TV_HisenseTvAPI", "[laser] temperature: temperature[0] = " + configIntArrayValue[0] + ", temperature[1] = " + configIntArrayValue[1] + "\n");
        Log.v("TV_HisenseTvAPI", "[laser] getLaserTemperature: bytes[0] = " + ((int) bArr[0]) + ", bytes[1] = " + ((int) bArr[1]) + ", bytes[2] = " + ((int) bArr[2]) + ", bytes[3] = " + ((int) bArr[3]) + ", bytes[4] = " + ((int) bArr[4]) + ", bytes[5] = " + ((int) bArr[5]) + ", bytes[6] = " + ((int) bArr[6]) + ", bytes[7] = " + ((int) bArr[7]) + "\n");
        return bArr;
    }

    public boolean resetGraphCorrection() {
        Log.v("TV_HisenseTvAPI", "[laser]resetGraphCorrection = 61680\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_RESET_GRAPH_CORRECTION, 61680) == 0;
    }

    public int getDistenceSensorStrength() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_DISTENCE_SENSOR_STRENGTH, 1);
        if (configIntArrayValue == null || configIntArrayValue.length != 1) {
            Log.v("TV_HisenseTvAPI", "[Laser] getDistenceSensorStrength return data error!!");
            return -1;
        }
        int i = configIntArrayValue[0] & 65535;
        Log.v("TV_HisenseTvAPI", "[laser]getDistenceSensorStrength, value is " + i + "\n");
        return i;
    }

    public int getBackgroundNoiseValue() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_BACKGROUND_NOISE_VALUE);
        Log.v("TV_HisenseTvAPI", "[laser]getBackgroundNoiseValue, value is " + configValue + "\n");
        return configValue;
    }

    public int setBackgroundNoiseValue(int i) {
        Log.v("TV_HisenseTvAPI", "[laser]setBackgroundNoiseValue, value is " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_BACKGROUND_NOISE_VALUE, i);
    }

    public String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public String getLaserLedVersion() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_LED_VERSION, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.v("TV_HisenseTvAPI", "[Laser] getLaserLedVersion return data error!!");
            return null;
        }
        byte[] bArr = {(byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255)};
        Log.v("TV_HisenseTvAPI", "[laser] getFanSpeed:status[0] = " + configIntArrayValue[0] + "status[1] = " + configIntArrayValue[1] + "\n");
        Log.v("TV_HisenseTvAPI", "[laser] getFanSpeed:bytes[0] = " + ((int) bArr[0]) + "bytes[1] = " + ((int) bArr[1]) + "bytes[2] = " + ((int) bArr[2]) + "bytes[3] = " + ((int) bArr[3]) + "\n");
        return bytesToHexString(bArr);
    }

    public int setLaserCloOverlapSelect(int i) {
        Log.v("TV_HisenseTvAPI", "[laser]setLaserCloOverlapSelect, mode = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_OVERLAP_SELECT, i);
    }

    public int getLaserCloOverlapSelect() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_OVERLAP_SELECT);
        Log.v("TV_HisenseTvAPI", "[laser]getLaserCloOverlapSelect, mode = " + configValue + "\n");
        return configValue;
    }

    public int setLaserCloMode(int i) {
        Log.v("TV_HisenseTvAPI", "[laser]setLaserCloMode, mode = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_MODE, i);
    }

    public int getLaserCloMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_CLO_MODE);
        Log.v("TV_HisenseTvAPI", "[laser]getLaserCloMode, mode = " + configValue + "\n");
        return configValue;
    }

    public int setLaserOverlapMode(int i) {
        Log.v("TV_HisenseTvAPI", "[laser]setLaserOverlapMode, mode = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_OVERLAP_MODE, i);
    }

    public int getLaserOverlapMode() {
        int configValue = getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_OVERLAP_MODE);
        Log.v("TV_HisenseTvAPI", "[laser]getLaserOverlapMode, mode = " + configValue + "\n");
        return configValue;
    }

    public int setMiscInputInfo(int i) {
        Log.v("TV_HisenseTvAPI", "setMiscInputInfo, value = " + i + "\n");
        return setConfigValue(MtkTvConfigTypeBase.CFG_MISC_INPUT_INFO_UPDATE, i);
    }

    public int getEmmcType() {
        return HisensePlatformConfig.getEmmcType();
    }

    public int setMuteColor(int i) {
        Log.d("TV_HisenseTvAPI", "setMuteColor" + i);
        int blueMuteEnable = HisVideoApi.setBlueMuteEnable(i);
        Log.d("TV_HisenseTvAPI", "setMuteColor ret = " + blueMuteEnable);
        return blueMuteEnable;
    }

    public String getServiceTag() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SERVICE_TAG);
    }

    public boolean setServiceTag(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_SERVICE_TAG, str) == 0;
    }

    public boolean generateEepKeyFile() {
        int iMountPerm_native = HisenseTVNativeWrapper.mountPerm_native();
        if (iMountPerm_native == 0) {
            iMountPerm_native = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_EEP_KEY, 0);
            Log.d("TV_HisenseTvAPI", "generateEepKeyFile, return value = " + iMountPerm_native + "\n");
            HisenseTVNativeWrapper.unmountPerm_native();
        } else {
            Log.e("TV_HisenseTvAPI", "mount perm error, return value = " + iMountPerm_native + "\n");
        }
        return iMountPerm_native == 0;
    }

    public boolean cleanEepKeyFile() {
        int iMountPerm_native = HisenseTVNativeWrapper.mountPerm_native();
        if (iMountPerm_native == 0) {
            iMountPerm_native = setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_CLEAN_EEP_KEY, 0);
            Log.d("TV_HisenseTvAPI", "cleanEepKeyFile, return value = " + iMountPerm_native + "\n");
            HisenseTVNativeWrapper.unmountPerm_native();
        } else {
            Log.e("TV_HisenseTvAPI", "mount perm error, return value = " + iMountPerm_native + "\n");
        }
        return iMountPerm_native == 0;
    }

    public int setCurrentVirtualSource(int i) {
        return AvMiddleWareManager.setCurrentVirtualSource(i);
    }

    public boolean setFactoryCheckStatus(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_CHECK_SATUS, i) == 0;
    }

    public int getFactoryCheckStatus() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_CHECK_SATUS);
    }

    public boolean setOledCpcValue(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_CPC_ONOFF, i) == 0;
    }

    public int getOledCpcValue() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_CPC_ONOFF);
    }

    public boolean setOledLbc2Value(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_LBC2_ONOFF, i) == 0;
    }

    public int getOledLbc2Value() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_VID_OLED_LBC2_ONOFF);
    }

    public boolean setDemuraFlashErase() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_FLASH_ERASE, 0) == 0;
    }

    public boolean setDemuraFlashUpgrade(String str) {
        return setConfigString(MtkTvConfigTypeBase.CFG_FACTORY_DEMURA_FLASH_UPGRADE, str) == 0;
    }

    public int getOffsExecuteCount() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_OFFRS_EXE_COUNT, 1);
        if (configIntArrayValue == null || configIntArrayValue.length < 1) {
            return -1;
        }
        int i = configIntArrayValue[0] & 65535;
        Log.d("TV_HisenseTvAPI", "[laser]getOffsExecuteCount, value is " + i + "\n");
        return i;
    }

    public int getJBExecuteCount() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_JB_EXE_COUNT, 1);
        if (configIntArrayValue == null || configIntArrayValue.length < 1) {
            return -1;
        }
        int i = configIntArrayValue[0] & 65535;
        Log.d("TV_HisenseTvAPI", "[laser]getJBExecuteCount, value is " + i + "\n");
        return i;
    }

    public byte[] getOffsExecuteTime() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_OFFRS_EXE_TIME, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            return null;
        }
        return new byte[]{(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
    }

    public byte[] getJBExecuteTime() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_JB_EXE_TIME, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            return null;
        }
        return new byte[]{(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
    }

    public int getErrDetCount() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_ERR_DETCOUNT);
    }

    public int getOledtempLutVersion() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_OLED_TEMP_LUTVERSION);
    }

    public String getOledtcontempLutVersion() {
        return getConfigString(MtkTvConfigTypeBase.CFG_FACTORY_OLED_TCON_TEMP_LUTVERSION);
    }

    public byte[] getVcomsValueByte() {
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_ALL_VCOM_VALUE, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            return null;
        }
        return new byte[]{(byte) ((configIntArrayValue[0] >> 0) & 255), (byte) ((configIntArrayValue[0] >> 8) & 255), (byte) ((configIntArrayValue[0] >> 16) & 255), (byte) ((configIntArrayValue[0] >> 24) & 255), (byte) ((configIntArrayValue[1] >> 0) & 255), (byte) ((configIntArrayValue[1] >> 8) & 255), (byte) ((configIntArrayValue[1] >> 16) & 255), (byte) ((configIntArrayValue[1] >> 24) & 255)};
    }

    public boolean setLaserSlidingCoverMode(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SLIDING_COVER_MODE, i) == 0;
    }

    public boolean setLaserMovableCoverStep(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MOVABLE_COVER_STEP, i) == 0;
    }

    public int getLaserMovableCoverStep() {
        return getConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_MOVABLE_COVER_STEP);
    }

    public boolean SaveLaserMovableCoverStep() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_SAVE_MOVABLE_COVER_STEP, 0) == 0;
    }

    public boolean ResetLaserMovableCoverStep() {
        return setConfigValue(MtkTvConfigTypeBase.CFG_LASER_SYSTEM_RESET_MOVABLE_COVER_STEP, 0) == 0;
    }

    public boolean setFirstPowerOnTime(long j) {
        int[] iArr = {(int) ((-1) & j), (int) (j >> 32)};
        Log.e("TV_HisenseTvAPI", "dataHex:" + Long.toHexString(j) + ";highHex:" + Integer.toHexString(iArr[1]) + ";lowHex:" + Integer.toHexString(iArr[0]));
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_FIRST_POWER_ON_TIME, iArr, 2) == 0;
    }

    public long getFirstPowerOnTime() {
        long j;
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_FIRST_POWER_ON_TIME, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.e("TV_HisenseTvAPI", "[laser]getFirstPowerOnTime, error \n");
            return -1L;
        }
        long j2 = configIntArrayValue[1];
        if (configIntArrayValue[0] < 0) {
            j = Long.parseLong(Integer.toHexString(configIntArrayValue[0]), 16);
        } else {
            j = configIntArrayValue[0];
        }
        long j3 = j + (j2 << 32);
        Log.e("TV_HisenseTvAPI", "dataHex:" + Long.toHexString(j3) + ";highHex:" + Integer.toHexString(configIntArrayValue[1]) + ";lowHex:" + Integer.toHexString(configIntArrayValue[0]));
        return j3;
    }

    public boolean setStartingOnCount(long j) {
        int[] iArr = {(int) ((-1) & j), (int) (j >> 32)};
        Log.e("TV_HisenseTvAPI", "dataHex:" + Long.toHexString(j) + ";highHex:" + Integer.toHexString(iArr[1]) + ";lowHex:" + Integer.toHexString(iArr[0]));
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STARTING_ON_COUNT, iArr, 2) == 0;
    }

    public long getStartingOnCount() {
        long j;
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STARTING_ON_COUNT, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.e("TV_HisenseTvAPI", "[laser]getStartingOnCount, error \n");
            return -1L;
        }
        long j2 = configIntArrayValue[1];
        if (configIntArrayValue[0] < 0) {
            j = Long.parseLong(Integer.toHexString(configIntArrayValue[0]), 16);
        } else {
            j = configIntArrayValue[0];
        }
        long j3 = j + (j2 << 32);
        Log.e("TV_HisenseTvAPI", "dataHex:" + Long.toHexString(j3) + ";highHex:" + Integer.toHexString(configIntArrayValue[1]) + ";lowHex:" + Integer.toHexString(configIntArrayValue[0]));
        return j3;
    }

    public boolean setStrStartOnCount(long j) {
        int[] iArr = {(int) ((-1) & j), (int) (j >> 32)};
        Log.e("TV_HisenseTvAPI", "dataHex:" + Long.toHexString(j) + ";highHex:" + Integer.toHexString(iArr[1]) + ";lowHex:" + Integer.toHexString(iArr[0]));
        return setConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STR_STARTING_ON_COUNT, iArr, 2) == 0;
    }

    public long getStrStartOnCount() {
        long j;
        int[] configIntArrayValue = getConfigIntArrayValue(MtkTvConfigTypeBase.CFG_FACTORY_STR_STARTING_ON_COUNT, 2);
        if (configIntArrayValue == null || configIntArrayValue.length < 2) {
            Log.e("TV_HisenseTvAPI", "[laser]getStrStartOnCount, error \n");
            return -1L;
        }
        long j2 = configIntArrayValue[1];
        if (configIntArrayValue[0] < 0) {
            j = Long.parseLong(Integer.toHexString(configIntArrayValue[0]), 16);
        } else {
            j = configIntArrayValue[0];
        }
        long j3 = j + (j2 << 32);
        Log.e("TV_HisenseTvAPI", "dataHex:" + Long.toHexString(j3) + ";highHex:" + Integer.toHexString(configIntArrayValue[1]) + ";lowHex:" + Integer.toHexString(configIntArrayValue[0]));
        return j3;
    }

    public boolean setFirstBootState(int i) {
        return setConfigValue(MtkTvConfigTypeBase.CFG_FACTORY_FIRSTBOOT_STATE, i) == 0;
    }
}
