package com.hisense.tvcm;

import android.content.Context;
import android.util.Log;
import com.hisense.uifac.R;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public class FacFunctionAdapterHelper {
    private static int[] mBrandIsolationArray = {R.array.factorymenu_logo_brand_isolation_gen, R.array.factorymenu_logo_brand_isolation_haam, R.array.factorymenu_logo_brand_isolation_has, R.array.factorymenu_logo_brand_isolation_dor, R.array.factorymenu_logo_brand_isolation_otw, R.array.factorymenu_logo_brand_isolation_sho, R.array.factorymenu_logo_brand_isolation_enf, R.array.factorymenu_logo_brand_isolation_dlc, R.array.factorymenu_logo_brand_isolation_oro, R.array.factorymenu_logo_brand_isolation_alh, R.array.factorymenu_logo_brand_isolation_une, R.array.factorymenu_logo_brand_isolation_tub};
    private String TAG = "Fac_AdapterHelper";
    Context mContext;
    FacFunction_Adapter mFacFunctin;

    public enum LogoForEM_NoBrandIsolation {
        his(0, "his", 1),
        vuz(1, "vuz", 23),
        tos(2, "tos", 95),
        akn(3, "akn", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_DETECT),
        gua(4, "gua", 55),
        wsa(5, "wsa", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED),
        liv(6, "liv", 75),
        szi(7, "szi", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT),
        sie(8, "sie", MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON),
        hif(9, "hif", 115),
        ltt(10, "ltt", 116);

        int enumIndex;
        int logoIndex;
        String logoName;

        LogoForEM_NoBrandIsolation(int i, String str, int i2) {
            this.enumIndex = i;
            this.logoName = str;
            this.logoIndex = i2;
        }

        public int getTilValue() {
            return this.logoIndex;
        }

        public int getUIIndex() {
            return this.enumIndex;
        }
    }

    public FacFunctionAdapterHelper(Context context, FacFunction_Adapter facFunction_Adapter) {
        this.mContext = context;
        this.mFacFunctin = facFunction_Adapter;
    }

    public int getCurrentSALogoType(int i) {
        int i2 = 3;
        if (1 == i) {
            return 3;
        }
        for (FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation : FacPropertyDemo.LogoForBGH_SABrandIsolation.values()) {
            if (i == logoForBGH_SABrandIsolation.getIndex()) {
                i2 = 0;
            }
        }
        for (FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation : FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()) {
            if (i == logoForDEVANT_SABrandIsolation.getIndex()) {
                i2 = 1;
            }
        }
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (i == logoForNEWSAN_SABrandIsolation.getIndex()) {
                i2 = 2;
            }
        }
        return i2;
    }

    public int getCurrentSALogoIndexInUi(int i) {
        Log.i(this.TAG, "getCurrentSALogoIndexInUi value is " + i);
        String brand = this.mFacFunctin.getBrand();
        Log.i(this.TAG, "getCurrentSALogoIndexInUi brand is " + brand);
        int newSALogoValue = getNewSALogoValue(brand);
        Log.i(this.TAG, "getCurrentSALogoIndexInUi brandValue is " + newSALogoValue);
        int currentSALogoType = getCurrentSALogoType(newSALogoValue);
        Log.i(this.TAG, "getCurrentSALogoIndexInUi type is " + currentSALogoType);
        int i2 = 0;
        if (3 == currentSALogoType) {
            FacPropertyDemo.LogoForDefault_SABrandIsolation[] logoForDefault_SABrandIsolationArrValues = FacPropertyDemo.LogoForDefault_SABrandIsolation.values();
            int length = logoForDefault_SABrandIsolationArrValues.length;
            int i3 = 0;
            while (i2 < length) {
                FacPropertyDemo.LogoForDefault_SABrandIsolation logoForDefault_SABrandIsolation = logoForDefault_SABrandIsolationArrValues[i2];
                if (i == logoForDefault_SABrandIsolation.getIndex()) {
                    i3 = logoForDefault_SABrandIsolation.enumIndex;
                }
                i2++;
            }
            return i3;
        }
        if (currentSALogoType == 0) {
            FacPropertyDemo.LogoForBGH_SABrandIsolation[] logoForBGH_SABrandIsolationArrValues = FacPropertyDemo.LogoForBGH_SABrandIsolation.values();
            int length2 = logoForBGH_SABrandIsolationArrValues.length;
            int i4 = 0;
            while (i2 < length2) {
                FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation = logoForBGH_SABrandIsolationArrValues[i2];
                if (i == logoForBGH_SABrandIsolation.getIndex()) {
                    i4 = logoForBGH_SABrandIsolation.enumIndex;
                }
                i2++;
            }
            return i4;
        }
        if (1 == currentSALogoType) {
            FacPropertyDemo.LogoForDEVANT_SABrandIsolation[] logoForDEVANT_SABrandIsolationArrValues = FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values();
            int length3 = logoForDEVANT_SABrandIsolationArrValues.length;
            int i5 = 0;
            while (i2 < length3) {
                FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation = logoForDEVANT_SABrandIsolationArrValues[i2];
                if (i == logoForDEVANT_SABrandIsolation.getIndex()) {
                    i5 = logoForDEVANT_SABrandIsolation.enumIndex;
                }
                i2++;
            }
            return i5;
        }
        if (2 == currentSALogoType) {
            FacPropertyDemo.LogoForNEWSAN_SABrandIsolation[] logoForNEWSAN_SABrandIsolationArrValues = FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values();
            int length4 = logoForNEWSAN_SABrandIsolationArrValues.length;
            int i6 = 0;
            while (i2 < length4) {
                FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation = logoForNEWSAN_SABrandIsolationArrValues[i2];
                if (i == logoForNEWSAN_SABrandIsolation.getIndex()) {
                    i6 = logoForNEWSAN_SABrandIsolation.enumIndex;
                }
                i2++;
            }
            return i6;
        }
        Log.i(this.TAG, "getCurrentSALogoIndexInUi index is 0");
        return 0;
    }

    public String getCurrentSALogoName(int i) {
        Log.i(this.TAG, "getCurrentSALogoName value is " + i);
        String name = "NG";
        for (FacPropertyDemo.LogoForDefault_SABrandIsolation logoForDefault_SABrandIsolation : FacPropertyDemo.LogoForDefault_SABrandIsolation.values()) {
            if (i == logoForDefault_SABrandIsolation.getIndex()) {
                name = logoForDefault_SABrandIsolation.getName();
            }
        }
        for (FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation : FacPropertyDemo.LogoForBGH_SABrandIsolation.values()) {
            if (i == logoForBGH_SABrandIsolation.getIndex()) {
                name = logoForBGH_SABrandIsolation.getName();
            }
        }
        for (FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation : FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()) {
            if (i == logoForDEVANT_SABrandIsolation.getIndex()) {
                name = logoForDEVANT_SABrandIsolation.getName();
            }
        }
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (i == logoForNEWSAN_SABrandIsolation.getIndex()) {
                name = logoForNEWSAN_SABrandIsolation.getName();
            }
        }
        Log.i(this.TAG, "getCurrentSALogoName name is " + name);
        return name;
    }

    public int getNewSALogoValue(int i, int i2) {
        int index = FacPropertyDemo.LogoForDefault_SABrandIsolation.values()[i].getIndex();
        Log.i(this.TAG, "getNewSALogoValue index is " + i);
        Log.i(this.TAG, "getNewSALogoValue lastLogoValue is " + i2);
        for (FacPropertyDemo.LogoForDefault_SABrandIsolation logoForDefault_SABrandIsolation : FacPropertyDemo.LogoForDefault_SABrandIsolation.values()) {
            if (i < FacPropertyDemo.LogoForDefault_SABrandIsolation.getSize() && i2 == logoForDefault_SABrandIsolation.getIndex()) {
                return FacPropertyDemo.LogoForDefault_SABrandIsolation.values()[i].getIndex();
            }
        }
        for (FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation : FacPropertyDemo.LogoForBGH_SABrandIsolation.values()) {
            if (i < FacPropertyDemo.LogoForBGH_SABrandIsolation.getSize() && i2 == logoForBGH_SABrandIsolation.getIndex()) {
                return FacPropertyDemo.LogoForBGH_SABrandIsolation.values()[i].getIndex();
            }
        }
        for (FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation : FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()) {
            if (i < FacPropertyDemo.LogoForDEVANT_SABrandIsolation.getSize() && i2 == logoForDEVANT_SABrandIsolation.getIndex()) {
                return FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()[i].getIndex();
            }
        }
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (i < FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.getSize() && i2 == logoForNEWSAN_SABrandIsolation.getIndex()) {
                return FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()[i].getIndex();
            }
        }
        Log.i(this.TAG, "getNewSALogoValue newValue is " + index);
        return index;
    }

    public int getNewSALogoValue(String str) {
        int index = FacPropertyDemo.LogoForDefault_SABrandIsolation.values()[0].getIndex();
        Log.i(this.TAG, "getNewSALogoValue logoName is " + str);
        FacPropertyDemo.LogoForDefault_SABrandIsolation[] logoForDefault_SABrandIsolationArrValues = FacPropertyDemo.LogoForDefault_SABrandIsolation.values();
        int length = logoForDefault_SABrandIsolationArrValues.length;
        for (int i = 0; i < length; i++) {
            FacPropertyDemo.LogoForDefault_SABrandIsolation logoForDefault_SABrandIsolation = logoForDefault_SABrandIsolationArrValues[i];
            if (str.equalsIgnoreCase(logoForDefault_SABrandIsolation.getName())) {
                return logoForDefault_SABrandIsolation.getIndex();
            }
        }
        for (FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation : FacPropertyDemo.LogoForBGH_SABrandIsolation.values()) {
            if (str.equalsIgnoreCase(logoForBGH_SABrandIsolation.getName())) {
                return logoForBGH_SABrandIsolation.getIndex();
            }
        }
        for (FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation : FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()) {
            if (str.equalsIgnoreCase(logoForDEVANT_SABrandIsolation.getName())) {
                return logoForDEVANT_SABrandIsolation.getIndex();
            }
        }
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (str.equalsIgnoreCase(logoForNEWSAN_SABrandIsolation.getName())) {
                return logoForNEWSAN_SABrandIsolation.getIndex();
            }
        }
        Log.i(this.TAG, "getNewSALogoValue newValue is " + index);
        return index;
    }

    public int getSALogoValueForMac(String str) {
        Log.i(this.TAG, "getNewSALogoValue logoName is " + str);
        for (FacPropertyDemo.LogoForDefault_SABrandIsolation logoForDefault_SABrandIsolation : FacPropertyDemo.LogoForDefault_SABrandIsolation.values()) {
            if (str.equalsIgnoreCase(logoForDefault_SABrandIsolation.getName())) {
                return logoForDefault_SABrandIsolation.getIndex();
            }
        }
        for (FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation : FacPropertyDemo.LogoForBGH_SABrandIsolation.values()) {
            if (str.equalsIgnoreCase(logoForBGH_SABrandIsolation.getName())) {
                return logoForBGH_SABrandIsolation.getIndex();
            }
        }
        for (FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation : FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()) {
            if (str.equalsIgnoreCase(logoForDEVANT_SABrandIsolation.getName())) {
                return logoForDEVANT_SABrandIsolation.getIndex();
            }
        }
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (str.equalsIgnoreCase(logoForNEWSAN_SABrandIsolation.getName())) {
                return logoForNEWSAN_SABrandIsolation.getIndex();
            }
        }
        Log.i(this.TAG, "getNewSALogoValue newValue is -1");
        return -1;
    }

    public String getSALogoFullName(int i) {
        Log.i(this.TAG, "getSALogoFullName value is " + i);
        String[] stringArray = null;
        int i2 = 0;
        for (FacPropertyDemo.LogoForDefault_SABrandIsolation logoForDefault_SABrandIsolation : FacPropertyDemo.LogoForDefault_SABrandIsolation.values()) {
            if (i == logoForDefault_SABrandIsolation.getIndex()) {
                i2 = logoForDefault_SABrandIsolation.enumIndex;
                stringArray = this.mContext.getResources().getStringArray(FacParamManager.SALOGO[3]);
            }
        }
        for (FacPropertyDemo.LogoForBGH_SABrandIsolation logoForBGH_SABrandIsolation : FacPropertyDemo.LogoForBGH_SABrandIsolation.values()) {
            if (i == logoForBGH_SABrandIsolation.getIndex()) {
                i2 = logoForBGH_SABrandIsolation.enumIndex;
                stringArray = this.mContext.getResources().getStringArray(FacParamManager.SALOGO[0]);
            }
        }
        for (FacPropertyDemo.LogoForDEVANT_SABrandIsolation logoForDEVANT_SABrandIsolation : FacPropertyDemo.LogoForDEVANT_SABrandIsolation.values()) {
            if (i == logoForDEVANT_SABrandIsolation.getIndex()) {
                i2 = logoForDEVANT_SABrandIsolation.enumIndex;
                stringArray = this.mContext.getResources().getStringArray(FacParamManager.SALOGO[1]);
            }
        }
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (i == logoForNEWSAN_SABrandIsolation.getIndex()) {
                i2 = logoForNEWSAN_SABrandIsolation.enumIndex;
                stringArray = this.mContext.getResources().getStringArray(FacParamManager.SALOGO[2]);
            }
        }
        if (stringArray == null) {
            return "NG";
        }
        String str = stringArray[i2];
        Log.i(this.TAG, "getSALogoFullName index is " + i2);
        Log.i(this.TAG, "getSALogoFullName name is " + str);
        return str;
    }

    public boolean isNewsanLogo(int i) {
        for (FacPropertyDemo.LogoForNEWSAN_SABrandIsolation logoForNEWSAN_SABrandIsolation : FacPropertyDemo.LogoForNEWSAN_SABrandIsolation.values()) {
            if (i == logoForNEWSAN_SABrandIsolation.getIndex()) {
                return true;
            }
        }
        return false;
    }

    public int getBrandIsolateLogoSourceIndex() {
        String lowerCase;
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.factorymenu_brand_isolation);
        if (stringArray != null && !stringArray.equals("") && (lowerCase = this.mFacFunctin.getBrand().toLowerCase()) != null && !lowerCase.isEmpty()) {
            Log.i(this.TAG, "getBrandIsolateLogoSourceIndex logoBrand  = " + lowerCase);
            for (int i = 0; i < stringArray.length; i++) {
                if (stringArray[i].equalsIgnoreCase(lowerCase)) {
                    Log.i(this.TAG, "getBrandIsolateLogoSourceIndex brand i = " + i);
                    return mBrandIsolationArray[i];
                }
            }
        }
        return -1;
    }

    public String[] geBrandIsolateList() {
        int brandIsolateLogoSourceIndex = getBrandIsolateLogoSourceIndex();
        if (brandIsolateLogoSourceIndex != -1) {
            return this.mContext.getResources().getStringArray(brandIsolateLogoSourceIndex);
        }
        return this.mContext.getResources().getStringArray(R.array.factorymenu_logo_for_em_non_brand_isolation);
    }

    public boolean isBrandIsolate() {
        return getBrandIsolateLogoSourceIndex() != -1;
    }

    public int getNoBrandIsolateUIIndex(int i) {
        for (LogoForEM_NoBrandIsolation logoForEM_NoBrandIsolation : LogoForEM_NoBrandIsolation.values()) {
            if (i == logoForEM_NoBrandIsolation.getTilValue()) {
                return logoForEM_NoBrandIsolation.getUIIndex();
            }
        }
        return 0;
    }

    public int getNoBrandIsolateTilValue(int i) {
        for (LogoForEM_NoBrandIsolation logoForEM_NoBrandIsolation : LogoForEM_NoBrandIsolation.values()) {
            if (i == logoForEM_NoBrandIsolation.getUIIndex()) {
                return logoForEM_NoBrandIsolation.getTilValue();
            }
        }
        return LogoForEM_NoBrandIsolation.his.getTilValue();
    }
}
