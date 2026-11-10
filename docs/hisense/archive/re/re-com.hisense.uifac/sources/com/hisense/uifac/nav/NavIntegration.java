package com.hisense.uifac.nav;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.tvcm.FactoryConstants;
import com.hisense.uiadapter.TVFunction;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.util.MenuConfigManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NavIntegration {
    private static NavIntegration instance;
    private int currentIdx = 0;
    private MenuConfigManager mMenuConfigManager;
    private List<String> mSourceList;
    private TVFunction mTVFunction;

    public interface iEffectChangeListener {
        void effectChanged(View view, int i);
    }

    public interface iSourceChangeListener {
        void sourceChanged(View view, int i);
    }

    public boolean iCurrentInputSourceHasSignal() {
        return true;
    }

    public static synchronized NavIntegration getInstance(Context context) {
        if (instance == null) {
            instance = new NavIntegration(context);
        }
        return instance;
    }

    private NavIntegration(Context context) {
        this.mSourceList = new ArrayList();
        this.mMenuConfigManager = MenuConfigManager.getInstance(context);
        TVFunction tVFunction = TVFunction.getInstance(context);
        this.mTVFunction = tVFunction;
        this.mSourceList = Arrays.asList(tVFunction.getInputSourceArray());
    }

    public void changeChannel(int i) {
        this.mTVFunction.selectChannel(i);
    }

    public void channelNext() {
        this.mTVFunction.channelUp();
    }

    public void channelPre() {
        this.mTVFunction.channelDown();
    }

    public String getCurrentChannelNumberString() {
        return this.mTVFunction.getCurrentChannelNumberString();
    }

    public String getCurrentInputSource() {
        return this.mTVFunction.getCurrentInputSourceName();
    }

    public String[] getInputSourceList() {
        return this.mTVFunction.getInputSourceArray();
    }

    public void iChangeNextSource(View view, iSourceChangeListener isourcechangelistener) {
        this.mTVFunction.changeNextInputSource();
        int iIndexOf = this.mSourceList.indexOf(getDisplayName(this.mTVFunction.getCurrentInputSource()));
        if (iIndexOf < 0 || isourcechangelistener == null) {
            return;
        }
        isourcechangelistener.sourceChanged(view, iIndexOf);
    }

    public void iExecWhenHisFacEnding() {
        this.mTVFunction.executeWhenHisFacEnding();
    }

    public int iGetEffect(String str) {
        if (str.equalsIgnoreCase("HIFAC_F1_VOLUME")) {
            return this.mMenuConfigManager.getDefault("HIFAC_VOLUME");
        }
        if (str.equalsIgnoreCase("HIFAC_BALANCE")) {
            return getBalanceMode();
        }
        if (str.equalsIgnoreCase("HIFAC_BALANCE_DOUBLE_AMP")) {
            return getDoubleAmplifierBalanceMode();
        }
        if (str.equalsIgnoreCase("HIFAC_BALANCE_MULTI_AMP")) {
            return getMultiAmplifierBalanceMode();
        }
        if (str.equalsIgnoreCase("HIFAC_BALANCE_UNIV_MULTI_AMP")) {
            return getUnivMultiAmplifierBalanceMode(this.currentIdx);
        }
        if (str.equalsIgnoreCase("HIFAC_TVS_BALANCE_LIKE_550")) {
            return getTvsTheeterBalanceMode();
        }
        if (str.equalsIgnoreCase("HIFAC_TVS_BALANCE_LIKE_650")) {
            return getTvsTheeterWooferBalanceMode();
        }
        if (str.equalsIgnoreCase("HIFAC_TVS_BALANCE_LIKE_850")) {
            return getTvsBalanceModeLike850();
        }
        if (str.equals("HIFAC_TUNERMODE")) {
            Log.i("Fac_Nav", "get tuner mode = " + this.mTVFunction.getTunerMode());
            return this.mTVFunction.getTunerMode();
        }
        if (str.equalsIgnoreCase("HIFAC_2DTO3D")) {
            return this.mTVFunction.get2Dto3D();
        }
        if (str.equalsIgnoreCase("HIFAC_RDRV_PLUS") || str.equalsIgnoreCase("HIFAC_RDRV_MINUS")) {
            str = "HIFAC_RDRV";
        }
        if (str.equalsIgnoreCase("HIFAC_GDRV_PLUS") || str.equalsIgnoreCase("HIFAC_GDRV_MINUS")) {
            str = "HIFAC_GDRV";
        }
        if (str.equalsIgnoreCase("HIFAC_BDRV_PLUS") || str.equalsIgnoreCase("HIFAC_BDRV_MINUS")) {
            str = "HIFAC_BDRV";
        }
        if (str.equalsIgnoreCase("HIFAC_RCUT_PLUS") || str.equalsIgnoreCase("HIFAC_RCUT_MINUS")) {
            str = "HIFAC_RCUT";
        }
        if (str.equalsIgnoreCase("HIFAC_GCUT_PLUS") || str.equalsIgnoreCase("HIFAC_GCUT_MINUS")) {
            str = "HIFAC_GCUT";
        }
        if (str.equals("HIFAC_BCUT_PLUS") || str.equalsIgnoreCase("HIFAC_BCUT_MINUS")) {
            str = "HIFAC_BCUT";
        }
        if (str.equalsIgnoreCase("HIFAC_PNL_SEL")) {
            str = "HIFAC_PNL_SEL";
        }
        return this.mMenuConfigManager.getDefault(str);
    }

    public boolean isCurrenSourceDTV() {
        return this.mTVFunction.isCurrentSourceDTV();
    }

    public void preSetBalanceToLeft() {
        this.mMenuConfigManager.setValue("HIFAC_BALANCE", FactoryConstants.BALANCE_AMP_LEFT_INDEX);
    }

    public void preSetBalanceToWoofer() {
        this.mMenuConfigManager.setValue("HIFAC_BALANCE", 3);
    }

    public void setBalanceToMiddle() {
        this.mMenuConfigManager.setValue("HIFAC_BALANCE", 0);
    }

    public void preSetDoubleAmplifierBalanceToML() {
        this.mMenuConfigManager.setValue("HIFAC_BALANCE_DOUBLE_AMP", FacPropertyDemo.HIFAC_ML_DIGITAL_AMP_BALANCE);
    }

    public void preSetDoubleAmplifierBalanceToCenter() {
        this.mMenuConfigManager.setValue("HIFAC_BALANCE_DOUBLE_AMP", 0);
    }

    public void preSetMultiAmplifierBalanceToML() {
        this.mMenuConfigManager.setValue("HIFAC_BALANCE_MULTI_AMP", FacPropertyDemo.HIFAC_ML_DIGITAL_AMP_BALANCE);
    }

    public void preSetUnivMultiAmplifierBalanceToML() {
        int max = this.mMenuConfigManager.getMax("HIFAC_BALANCE_UNIV_MULTI_AMP");
        this.currentIdx = 0;
        while (this.mTVFunction.getUnivMultiBalance(this.currentIdx) == -1) {
            this.currentIdx = (this.currentIdx + 1) % (max + 1);
            Log.i("Fac_NavIntegration", "preSetUnivMultiAmplifierBalanceToML, in while loop, currentIdx = " + this.currentIdx);
        }
        Log.i("Fac_NavIntegration", "preSetUnivMultiAmplifierBalanceToML, while loop ended, currentIdx = " + this.currentIdx);
        int univMultiBalance = this.mTVFunction.getUnivMultiBalance(this.currentIdx);
        Log.i("Fac_NavIntegration", "preSetUnivMultiAmplifierBalanceToML, current = " + univMultiBalance);
        this.mMenuConfigManager.setValue("HIFAC_BALANCE_UNIV_MULTI_AMP", univMultiBalance);
    }

    public void preSetMultiAmplifierBalanceToMIDDLE() {
        this.mMenuConfigManager.setValue("HIFAC_BALANCE_MULTI_AMP", 0);
    }

    public void preSetTvsTheeterBalanceToML() {
        this.mMenuConfigManager.setValue("HIFAC_TVS_BALANCE_LIKE_550", 1);
    }

    public void preSetTvsTheeterWooferBalanceToML() {
        this.mMenuConfigManager.setValue("HIFAC_TVS_BALANCE_LIKE_650", 1);
    }

    public void preSetTvsTheeterBalanceToCenter() {
        this.mMenuConfigManager.setValue("HIFAC_TVS_BALANCE_LIKE_550", 0);
    }

    public void preSetTvsTheeterWooferBalanceToCenter() {
        this.mMenuConfigManager.setValue("HIFAC_TVS_BALANCE_LIKE_650", 0);
    }

    public void preSetTvsBalanceToCenterLike850() {
        this.mMenuConfigManager.setValue("HIFAC_TVS_BALANCE_LIKE_850", 0);
    }

    public void preSetTvsBalanceToMLLike850() {
        this.mMenuConfigManager.setValue("HIFAC_TVS_BALANCE_LIKE_850", 1);
    }

    public void iSetNextEffect(View view, iEffectChangeListener ieffectchangelistener, String str) {
        if (str.equalsIgnoreCase("HIFAC_BALANCE")) {
            int i = this.mMenuConfigManager.getDefault("HIFAC_BALANCE");
            int max = this.mMenuConfigManager.getMax("HIFAC_BALANCE");
            int min = this.mMenuConfigManager.getMin("HIFAC_BALANCE");
            if (FacPropertyDemo.HIFAC_AMP_WOOFER_SUPPORT) {
                if (this.mMenuConfigManager.isWooferModeOn()) {
                    this.mMenuConfigManager.setValue("HIFAC_BALANCE", FactoryConstants.BALANCE_AMP_LEFT_INDEX);
                } else {
                    int i2 = (max + min) / 2;
                    if (i == i2) {
                        this.mMenuConfigManager.setValue("HIFAC_BALANCE", 3);
                    } else if (i == max) {
                        this.mMenuConfigManager.setValue("HIFAC_BALANCE", i2);
                    } else {
                        this.mMenuConfigManager.setValue("HIFAC_BALANCE", max);
                    }
                }
                if (this.mMenuConfigManager.isWooferModeOn()) {
                    i = 3;
                }
            } else {
                int i3 = (max + min) / 2;
                if (i == i3) {
                    this.mMenuConfigManager.setValue("HIFAC_BALANCE", FactoryConstants.BALANCE_AMP_LEFT_INDEX);
                } else if (i == max) {
                    this.mMenuConfigManager.setValue("HIFAC_BALANCE", i3);
                } else {
                    this.mMenuConfigManager.setValue("HIFAC_BALANCE", max);
                }
                i = this.mMenuConfigManager.getDefault(str);
            }
            if (i < min || i > max || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, getBalanceMode());
            return;
        }
        int i4 = 1;
        if (str.equalsIgnoreCase("HIFAC_BALANCE_DOUBLE_AMP")) {
            int i5 = this.mMenuConfigManager.getDefault("HIFAC_BALANCE_DOUBLE_AMP");
            int max2 = this.mMenuConfigManager.getMax("HIFAC_BALANCE_DOUBLE_AMP");
            int min2 = this.mMenuConfigManager.getMin("HIFAC_BALANCE_DOUBLE_AMP");
            if (i5 < max2) {
                this.mMenuConfigManager.setValue(str, i5 + 1);
            } else {
                this.mMenuConfigManager.setValue(str, min2);
            }
            int i6 = this.mMenuConfigManager.getDefault(str);
            if (i6 < min2 || i6 > max2 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, getDoubleAmplifierBalanceMode());
            return;
        }
        if (str.equalsIgnoreCase("HIFAC_BALANCE_MULTI_AMP")) {
            int i7 = this.mMenuConfigManager.getDefault("HIFAC_BALANCE_MULTI_AMP");
            int max3 = this.mMenuConfigManager.getMax("HIFAC_BALANCE_MULTI_AMP");
            int min3 = this.mMenuConfigManager.getMin("HIFAC_BALANCE_MULTI_AMP");
            if (i7 < max3) {
                this.mMenuConfigManager.setValue(str, i7 + 1);
            } else {
                this.mMenuConfigManager.setValue(str, min3);
            }
            int i8 = this.mMenuConfigManager.getDefault(str);
            if (i8 < min3 || i8 > max3 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, getMultiAmplifierBalanceMode());
            return;
        }
        if (str.equalsIgnoreCase("HIFAC_BALANCE_UNIV_MULTI_AMP")) {
            int max4 = this.mMenuConfigManager.getMax("HIFAC_BALANCE_UNIV_MULTI_AMP");
            int min4 = this.mMenuConfigManager.getMin("HIFAC_BALANCE_UNIV_MULTI_AMP");
            int i9 = max4 + 1;
            this.currentIdx = (this.currentIdx + 1) % i9;
            Log.i("Fac_NavIntegration", "currentIdx = " + this.currentIdx);
            while (this.mTVFunction.getUnivMultiBalance(this.currentIdx) == -1) {
                this.currentIdx = (this.currentIdx + 1) % i9;
                Log.i("Fac_NavIntegration", "in while loop, currentIdx = " + this.currentIdx);
            }
            Log.i("Fac_NavIntegration", "while loop ended, currentIdx = " + this.currentIdx);
            int univMultiBalance = this.mTVFunction.getUnivMultiBalance(this.currentIdx);
            Log.i("Fac_NavIntegration", "current = " + univMultiBalance);
            this.mMenuConfigManager.setValue(str, univMultiBalance);
            if (univMultiBalance < min4 || univMultiBalance > max4 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, getUnivMultiAmplifierBalanceMode(this.currentIdx));
            return;
        }
        if (str.equalsIgnoreCase("HIFAC_TVS_BALANCE_LIKE_550")) {
            int i10 = this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_550");
            int max5 = this.mMenuConfigManager.getMax("HIFAC_TVS_BALANCE_LIKE_550");
            int min5 = this.mMenuConfigManager.getMin("HIFAC_TVS_BALANCE_LIKE_550");
            if (i10 < max5) {
                this.mMenuConfigManager.setValue(str, i10 + 1);
            } else {
                this.mMenuConfigManager.setValue(str, min5);
            }
            int i11 = this.mMenuConfigManager.getDefault(str);
            if (i11 < min5 || i11 > max5 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, getTvsTheeterBalanceMode());
            return;
        }
        if (str.equalsIgnoreCase("HIFAC_TVS_BALANCE_LIKE_650")) {
            int i12 = this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_650");
            int max6 = this.mMenuConfigManager.getMax("HIFAC_TVS_BALANCE_LIKE_650");
            int min6 = this.mMenuConfigManager.getMin("HIFAC_TVS_BALANCE_LIKE_650");
            if (i12 < max6) {
                this.mMenuConfigManager.setValue(str, i12 + 1);
            } else {
                this.mMenuConfigManager.setValue(str, min6);
            }
            int i13 = this.mMenuConfigManager.getDefault(str);
            if (i13 < min6 || i13 > max6 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, getTvsTheeterWooferBalanceMode());
            return;
        }
        if (str.equalsIgnoreCase("HIFAC_TVS_BALANCE_LIKE_850")) {
            int i14 = this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_850");
            int max7 = this.mMenuConfigManager.getMax("HIFAC_TVS_BALANCE_LIKE_850");
            int min7 = this.mMenuConfigManager.getMin("HIFAC_TVS_BALANCE_LIKE_850");
            if (i14 < max7) {
                this.mMenuConfigManager.setValue(str, i14 + 1);
            } else {
                this.mMenuConfigManager.setValue(str, min7);
            }
            int i15 = this.mMenuConfigManager.getDefault(str);
            if (i15 < min7 || i15 > max7 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, getTvsBalanceModeLike850());
            return;
        }
        if (str.equals("HIFAC_TUNERMODE")) {
            int tunerMode = this.mTVFunction.getTunerMode();
            Log.i("Fac_NavIntegration", "NavIntegration set tuner mode1 = " + tunerMode);
            if (tunerMode == 0) {
                this.mTVFunction.setTunerMode(1);
            } else {
                this.mTVFunction.setTunerMode(0);
            }
            int tunerMode2 = this.mTVFunction.getTunerMode();
            if (tunerMode2 < 0 || tunerMode2 > 1) {
                return;
            }
            Log.i("Fac_NavIntegration", "NavIntegration set tuner mode2 = " + tunerMode2);
            if (ieffectchangelistener != null) {
                ieffectchangelistener.effectChanged(view, tunerMode2);
                return;
            }
            return;
        }
        if (str.equalsIgnoreCase("HIFAC_2DTO3D")) {
            if (this.mTVFunction.get2Dto3D() == 0) {
                this.mTVFunction.set2Dto3D(1);
            } else {
                this.mTVFunction.set2Dto3D(0);
            }
            int i16 = this.mTVFunction.get2Dto3D();
            if (i16 < 0 || i16 > 1 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, i16);
            return;
        }
        if (str.equalsIgnoreCase("HIFAC_RDRV_PLUS") || str.equalsIgnoreCase("HIFAC_RDRV_MINUS") || str.equalsIgnoreCase("HIFAC_GDRV_PLUS") || str.equalsIgnoreCase("HIFAC_GDRV_MINUS") || str.equalsIgnoreCase("HIFAC_BDRV_PLUS") || str.equalsIgnoreCase("HIFAC_BDRV_MINUS") || str.equalsIgnoreCase("HIFAC_RCUT_PLUS") || str.equalsIgnoreCase("HIFAC_RCUT_MINUS") || str.equalsIgnoreCase("HIFAC_GCUT_PLUS") || str.equalsIgnoreCase("HIFAC_GCUT_MINUS") || str.equalsIgnoreCase("HIFAC_BCUT_PLUS") || str.equalsIgnoreCase("HIFAC_BCUT_MINUS")) {
            if (!str.equalsIgnoreCase("HIFAC_RDRV_PLUS") && !str.equalsIgnoreCase("HIFAC_GDRV_PLUS") && !str.equalsIgnoreCase("HIFAC_BDRV_PLUS") && !str.equalsIgnoreCase("HIFAC_RCUT_PLUS") && !str.equalsIgnoreCase("HIFAC_GCUT_PLUS") && !str.equalsIgnoreCase("HIFAC_BCUT_PLUS")) {
                i4 = -1;
            }
            if (str.equalsIgnoreCase("HIFAC_RDRV_PLUS") || str.equalsIgnoreCase("HIFAC_RDRV_MINUS")) {
                str = "HIFAC_RDRV";
            }
            if (str.equalsIgnoreCase("HIFAC_GDRV_PLUS") || str.equalsIgnoreCase("HIFAC_GDRV_MINUS")) {
                str = "HIFAC_GDRV";
            }
            if (str.equalsIgnoreCase("HIFAC_BDRV_PLUS") || str.equalsIgnoreCase("HIFAC_BDRV_MINUS")) {
                str = "HIFAC_BDRV";
            }
            if (str.equalsIgnoreCase("HIFAC_RCUT_PLUS") || str.equalsIgnoreCase("HIFAC_RCUT_MINUS")) {
                str = "HIFAC_RCUT";
            }
            if (str.equalsIgnoreCase("HIFAC_GCUT_PLUS") || str.equalsIgnoreCase("HIFAC_GCUT_MINUS")) {
                str = "HIFAC_GCUT";
            }
            if (str.equalsIgnoreCase("HIFAC_BCUT_PLUS") || str.equalsIgnoreCase("HIFAC_BCUT_MINUS")) {
                str = "HIFAC_BCUT";
            }
            int i17 = this.mMenuConfigManager.getDefault(str);
            int max8 = this.mMenuConfigManager.getMax(str);
            int min8 = this.mMenuConfigManager.getMin(str);
            int i18 = i17 + i4;
            if (i18 > max8) {
                i18 = min8;
            } else if (i18 < min8) {
                i18 = max8;
            }
            this.mMenuConfigManager.setValue(str, i18);
            if (i18 < min8 || i18 > max8 || ieffectchangelistener == null) {
                return;
            }
            ieffectchangelistener.effectChanged(view, i18);
            return;
        }
        int i19 = this.mMenuConfigManager.getDefault(str);
        int max9 = this.mMenuConfigManager.getMax(str);
        int min9 = this.mMenuConfigManager.getMin(str);
        if (i19 < max9) {
            this.mMenuConfigManager.setValue(str, i19 + 1);
        } else {
            this.mMenuConfigManager.setValue(str, min9);
        }
        int i20 = this.mMenuConfigManager.getDefault(str);
        if (i20 < min9 || i20 > max9 || ieffectchangelistener == null) {
            return;
        }
        ieffectchangelistener.effectChanged(view, i20);
    }

    public void iSetSourcetoATV() {
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            this.mTVFunction.switchSource(FacPropertyDemo.SourceEnum.TV);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mTVFunction.setTunerModeForEM(0);
            try {
                Thread.sleep(400L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            this.mTVFunction.changeChannel(905, 0);
            return;
        }
        this.mTVFunction.switchSource(FacPropertyDemo.SourceEnum.TV);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        this.mTVFunction.setTunerMode(1);
    }

    public void iSetSourcetoAV1() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.AV)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.AV);
        } else if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.AV1)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.AV1);
        }
    }

    public void iSetSourcetoAV2() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.AV2)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.AV2);
        }
    }

    public void iSetSourcetoDTV() {
        this.mTVFunction.switchSource(FacPropertyDemo.SourceEnum.TV);
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            this.mTVFunction.setTunerModeForEM(1);
            try {
                Thread.sleep(400L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            this.mTVFunction.changeChannel(4, 1);
            return;
        }
        this.mTVFunction.setTunerMode(0);
    }

    public void iSetSourcetoHDMI1() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.HDMI1)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI1);
        }
    }

    public void iSetSourcetoHDMI2() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.HDMI2)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI2);
        }
    }

    public void iSetSourcetoHDMI3() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.HDMI3)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI3);
        }
    }

    public void iSetSourcetoHDMI4() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.HDMI4)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI4);
        }
    }

    public void iSetSourcetoNextAV() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.AV)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.AV);
            return;
        }
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.AV1)) {
            if (this.mTVFunction.isCurrentSourceAV1()) {
                if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.AV2)) {
                    this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.AV2);
                    return;
                }
                return;
            }
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.AV1);
        }
    }

    public void iSetSourcetoNextHDMI() {
        if (this.mTVFunction.isCurrentSourceHDMI1()) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI2);
            return;
        }
        if (this.mTVFunction.isCurrentSourceHDMI2()) {
            if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.HDMI3)) {
                this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI3);
                return;
            } else {
                this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI1);
                return;
            }
        }
        if (this.mTVFunction.isCurrentSourceHDMI3()) {
            if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.HDMI4)) {
                this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI4);
                return;
            } else {
                this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI1);
                return;
            }
        }
        this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.HDMI1);
    }

    public void iSetSourcetoNextTV() {
        this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.TV);
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (this.mTVFunction.getTunerMode() == 0) {
                this.mTVFunction.setTunerModeForEM(1);
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.mTVFunction.changeChannel(4, 1);
                return;
            }
            this.mTVFunction.setTunerModeForEM(0);
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            this.mTVFunction.changeChannel(905, 0);
            return;
        }
        this.mTVFunction.setTunerMode(0);
    }

    public void iSetSourcetoVGA() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.VGA)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.VGA);
        }
    }

    public void iSetSourcetoYPBPR1() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.COMPONENT1)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.COMPONENT1);
        } else if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.COMPONENT)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.COMPONENT);
        }
    }

    public void iSetSourcetoYPBPR2() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.COMPONENT2)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.COMPONENT2);
        }
    }

    public void iSetSourcetoYPBPR3() {
        if (FacPropertyDemo.SourceEnum.getShownSourceList().contains(FacPropertyDemo.SourceEnum.COMPONENT3)) {
            this.mTVFunction.changeSource(FacPropertyDemo.SourceEnum.COMPONENT3);
        }
    }

    public void iSetTheSource(final View view, final iSourceChangeListener isourcechangelistener, final String str) {
        new Thread(new Runnable() { // from class: com.hisense.uifac.nav.NavIntegration.1
            @Override // java.lang.Runnable
            public void run() {
                iSourceChangeListener isourcechangelistener2;
                NavIntegration.this.mTVFunction.changeSource((FacPropertyDemo.SourceEnum) Enum.valueOf(FacPropertyDemo.SourceEnum.class, NavIntegration.this.getOriSourceName(str).trim()));
                List list = NavIntegration.this.mSourceList;
                NavIntegration navIntegration = NavIntegration.this;
                int iIndexOf = list.indexOf(navIntegration.getDisplayName(navIntegration.mTVFunction.getCurrentInputSource()));
                if (iIndexOf < 0 || (isourcechangelistener2 = isourcechangelistener) == null) {
                    return;
                }
                isourcechangelistener2.sourceChanged(view, iIndexOf);
            }
        }).start();
    }

    String getOriSourceName(String str) {
        for (FacPropertyDemo.SourceEnum sourceEnum : FacPropertyDemo.SourceEnum.values()) {
            if (sourceEnum.getDisplayName().equalsIgnoreCase(str)) {
                return sourceEnum.name();
            }
        }
        return "ATV";
    }

    String getDisplayName(String str) {
        for (FacPropertyDemo.SourceEnum sourceEnum : FacPropertyDemo.SourceEnum.values()) {
            if (sourceEnum.name().equalsIgnoreCase(str)) {
                return sourceEnum.getDisplayName();
            }
        }
        return "ATV";
    }

    private int getBalanceMode() {
        if (FacPropertyDemo.HIFAC_AMP_WOOFER_SUPPORT && this.mMenuConfigManager.isWooferModeOn()) {
            return R.string.str_factorymenu_woofer;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE") == this.mMenuConfigManager.getMin("HIFAC_BALANCE")) {
            return R.string.str_factorymenu_left;
        }
        return this.mMenuConfigManager.getDefault("HIFAC_BALANCE") == this.mMenuConfigManager.getMax("HIFAC_BALANCE") ? R.string.str_factorymenu_right : R.string.str_factorymenu_middle;
    }

    private int getDoubleAmplifierBalanceMode() {
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_DOUBLE_AMP") == 1) {
            return R.string.str_factorymenu_digital_amp_ml;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_DOUBLE_AMP") == 2) {
            return R.string.str_factorymenu_digital_amp_mr;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_DOUBLE_AMP") == 3) {
            return R.string.str_factorymenu_digital_amp_s1l;
        }
        return this.mMenuConfigManager.getDefault("HIFAC_BALANCE_DOUBLE_AMP") == 4 ? R.string.str_factorymenu_digital_amp_s1r : R.string.str_factorymenu_digital_amp_center;
    }

    private int getMultiAmplifierBalanceMode() {
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_MULTI_AMP") == 1) {
            return R.string.str_factorymenu_digital_amp_ml;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_MULTI_AMP") == 2) {
            return R.string.str_factorymenu_digital_amp_mr;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_MULTI_AMP") == 3) {
            return R.string.str_factorymenu_digital_amp_s1l;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_MULTI_AMP") == 4) {
            return R.string.str_factorymenu_digital_amp_s1r;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_BALANCE_MULTI_AMP") == 5) {
            return R.string.str_factorymenu_digital_amp_s2l;
        }
        return this.mMenuConfigManager.getDefault("HIFAC_BALANCE_MULTI_AMP") == 6 ? R.string.str_factorymenu_digital_amp_s2r : R.string.str_factorymenu_digital_amp_center;
    }

    private int getUnivMultiAmplifierBalanceMode(int i) {
        if (i == 0) {
            return R.string.str_factorymenu_digital_univ_amp_main_left;
        }
        if (i == 1) {
            return R.string.str_factorymenu_digital_univ_amp_main_right;
        }
        if (i == 2) {
            return R.string.str_factorymenu_digital_univ_amp_main_left1;
        }
        if (i == 3) {
            return R.string.str_factorymenu_digital_univ_amp_main_right1;
        }
        if (i == 4) {
            return FacPropertyDemo.HIFAC_MARKET_SA ? R.string.str_factorymenu_digital_univ_amp_alt_left : R.string.str_factorymenu_digital_univ_amp_surround_left;
        }
        if (i == 5) {
            return FacPropertyDemo.HIFAC_MARKET_SA ? R.string.str_factorymenu_digital_univ_amp_alt_right : R.string.str_factorymenu_digital_univ_amp_surround_right;
        }
        if (i == 6) {
            return R.string.str_factorymenu_digital_univ_amp_sky_left;
        }
        if (i == 7) {
            return R.string.str_factorymenu_digital_univ_amp_sky_right;
        }
        if (i == 8) {
            return FacPropertyDemo.HIFAC_MARKET_SA ? R.string.str_factorymenu_digital_univ_amp_bass : R.string.str_factorymenu_digital_univ_amp_subwoofer;
        }
        return i == 9 ? R.string.str_factorymenu_digital_univ_amp_center : R.string.str_factorymenu_digital_univ_amp_mid;
    }

    private int getTvsTheeterBalanceMode() {
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_550") == 1) {
            return R.string.str_factorymenu_balance_ml;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_550") == 2) {
            return R.string.str_factorymenu_balance_mr;
        }
        return this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_550") == 3 ? R.string.str_factorymenu_balance_w : R.string.str_factorymenu_balance_center;
    }

    private int getTvsTheeterWooferBalanceMode() {
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_650") == 1) {
            return R.string.str_factorymenu_balance_ml;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_650") == 2) {
            return R.string.str_factorymenu_balance_mr;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_650") == 3) {
            return R.string.str_factorymenu_balance_w;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_650") == 4) {
            return R.string.str_factorymenu_balance_TTL;
        }
        return this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_650") == 5 ? R.string.str_factorymenu_balance_TTR : R.string.str_factorymenu_balance_center;
    }

    private int getTvsBalanceModeLike850() {
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_850") == 1) {
            return R.string.str_factorymenu_balance_ml;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_850") == 2) {
            return R.string.str_factorymenu_balance_mr;
        }
        if (this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_850") == 3) {
            return R.string.str_factorymenu_balance_tl;
        }
        return this.mMenuConfigManager.getDefault("HIFAC_TVS_BALANCE_LIKE_850") == 4 ? R.string.str_factorymenu_balance_tr : R.string.str_factorymenu_balance_center;
    }

    public String getCurrentSourceVideoInfo() {
        return this.mTVFunction.getVideoInfoOfCurrentSource();
    }

    public boolean isCurrenSourceATV() {
        return this.mTVFunction.isCurrentSourceATV();
    }

    public boolean isCurrenSourceTV() {
        return this.mTVFunction.isCurrentSourceTV();
    }

    public String getCurrentInputSourceDisplayName() {
        return this.mTVFunction.getCurrentInputSourceDisplayName();
    }

    public void setTunerMode(int i) {
        this.mTVFunction.setTunerMode(i);
    }
}
