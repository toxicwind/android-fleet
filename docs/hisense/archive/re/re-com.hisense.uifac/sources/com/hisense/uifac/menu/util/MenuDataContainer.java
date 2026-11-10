package com.hisense.uifac.menu.util;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.hisense.tvcm.FacParamManager;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MenuDataContainer {
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_5G;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_Bypass;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_ColorTuner;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_DRC_AVC;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_Dolby;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_LightSensor;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_OLED;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_OverScan;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_Peq;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_PictureCurve;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_PictureMode;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_SSC;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_SavingMode;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_SoundCurve;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_SoundCurveNew;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_SoundMode;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_SoundMode_More_Equalizers;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_WB20_Point;
    private static List<SetConfigListViewAdapter.DataItem> mHisDesDataGroup_Waves;
    private static String[] mHisDesignDataGroup;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_3DSetting;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_BlueToothSetting;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_DetectMic;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_Function;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_Init;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_Laser;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_NetworkSetting;
    private static List<SetConfigListViewAdapter.DataItem> mHisFacDataGroup_WhiteBalance;
    private static String[] mHisFactoryDataGroup;
    private static MenuDataContainer mMenuDataContainer;
    private String ItemName;
    private final MenuConfigManager mConfigManager;
    private final Context mContext;
    private MenuLogoHelper mMenuLogoHelper;
    private static String[] mRegionCountryPropertyArray = {"HIFAC_SETCOUNTRY_FOR_REGION1", "HIFAC_SETCOUNTRY_FOR_REGION2", "HIFAC_SETCOUNTRY_FOR_REGION3", "HIFAC_SETCOUNTRY_FOR_REGION4", "HIFAC_SETCOUNTRY_FOR_REGION5"};
    private static String[] mRegionLanguagePropertyArray = {"HIFAC_SETLANGUAGE_FOR_REGION1", "HIFAC_SETLANGUAGE_FOR_REGION2", "HIFAC_SETLANGUAGE_FOR_REGION3", "HIFAC_SETLANGUAGE_FOR_REGION4", "HIFAC_SETLANGUAGE_FOR_REGION5"};
    private static int[] mSARegionLanguageArray = {R.array.factorymenu_language_for_sa_regiona, R.array.factorymenu_language_for_sa_regionb};
    private static String[] mSARegionCountryPropertyArray = {"HIFAC_SETCOUNTRY_FOR_SA_REGION1", "HIFAC_SETCOUNTRY_FOR_SA_REGION2"};
    private static int[] mSARegionCountryArray = {R.array.factorymenu_country_for_sa_region_a, R.array.factorymenu_country_for_sa_region_b};
    private static String[] mSARegionLanguagePropertyArray = {"HIFAC_SETLANGUAGE_FOR_SA_REGION1", "HIFAC_SETLANGUAGE_FOR_SA_REGION2"};
    private static int[] mRegionLanguageArray = {R.array.factorymenu_language_for_region0, R.array.factorymenu_language_for_region1, R.array.factorymenu_language_for_region2, R.array.factorymenu_language_for_region3, R.array.factorymenu_language_for_region4};

    public static MenuDataContainer getInstance(Context context) {
        if (mMenuDataContainer == null) {
            mMenuDataContainer = new MenuDataContainer(context);
        }
        return mMenuDataContainer;
    }

    private MenuDataContainer(Context context) {
        new ArrayList();
        this.mContext = context;
        this.mConfigManager = MenuConfigManager.getInstance(context);
        this.mMenuLogoHelper = new MenuLogoHelper(context);
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_ColorTuner() {
        return mHisDesDataGroup_ColorTuner;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_DRC_AVC() {
        return mHisDesDataGroup_DRC_AVC;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_WAVES() {
        return mHisDesDataGroup_Waves;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_LIGHT_SENSOR() {
        return mHisDesDataGroup_LightSensor;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_OverScan() {
        return mHisDesDataGroup_OverScan;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_PictureCurve() {
        return mHisDesDataGroup_PictureCurve;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_PictureMode() {
        return mHisDesDataGroup_PictureMode;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_SavingMode() {
        return mHisDesDataGroup_SavingMode;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_SoundCurve() {
        return mHisDesDataGroup_SoundCurve;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_SoundCurveNew() {
        return mHisDesDataGroup_SoundCurveNew;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_SoundMode() {
        return mHisDesDataGroup_SoundMode;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_SoundMode_More_Equalizers() {
        return mHisDesDataGroup_SoundMode_More_Equalizers;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_WB20Point() {
        return mHisDesDataGroup_WB20_Point;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_SSC() {
        return mHisDesDataGroup_SSC;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_OLED() {
        return mHisDesDataGroup_OLED;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_Bypass() {
        return mHisDesDataGroup_Bypass;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_Peq() {
        return mHisDesDataGroup_Peq;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_5G_Info() {
        return mHisDesDataGroup_5G;
    }

    public List<SetConfigListViewAdapter.DataItem> getDesDataGroup_Dolby_IQ() {
        return mHisDesDataGroup_Dolby;
    }

    public int[] getEffectGroupInitValues(SetConfigListViewAdapter.DataItem dataItem) {
        if (dataItem != null && dataItem.getDataType() == SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW) {
            int[] iArr = new int[dataItem.getmEffectGroup().size()];
            int i = 0;
            Iterator<SetConfigListViewAdapter.DataItem> it = dataItem.getmEffectGroup().iterator();
            while (it.hasNext()) {
                iArr[i] = this.mConfigManager.getDefault(it.next().getmItemID());
                i++;
            }
            return iArr;
        }
        throw new IllegalArgumentException("type of mEffectParentDataItem is not DataType.EFFECTOPTIONVIEW or mEffectParentDataItem is null");
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_3DSetting() {
        return mHisFacDataGroup_3DSetting;
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_ChannelSet() {
        return mHisFacDataGroup_Init;
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_Function() {
        return mHisFacDataGroup_Function;
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_NetworkSetting() {
        return mHisFacDataGroup_NetworkSetting;
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_TestMic() {
        return mHisFacDataGroup_DetectMic;
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_WhiteBalance() {
        return mHisFacDataGroup_WhiteBalance;
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_Laser() {
        return mHisFacDataGroup_Laser;
    }

    public String[] getHisDesignDataGroup() {
        return mHisDesignDataGroup;
    }

    public String[] getHisFactoryDataGroup() {
        return mHisFactoryDataGroup;
    }

    public int getValueById(String str) {
        if (str != null && !"".equals(str.trim())) {
            return this.mConfigManager.getDefault(str) - this.mConfigManager.getMin(str);
        }
        throw new IllegalArgumentException("mId must be an contain elements of a character string");
    }

    public boolean load3DSetting() {
        mHisFacDataGroup_3DSetting = new ArrayList();
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.facmenu_3dmode_std);
        String[] stringArray2 = this.mContext.getResources().getStringArray(R.array.menu_video_3d_lrswitch_array);
        String[] stringArray3 = this.mContext.getResources().getStringArray(R.array.menu_video_3d_3t2switch_array);
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("HIFAC_3DMODE", this.mContext.getString(R.string.menu_video_3d_mode), 10004, 10004, this.mConfigManager.getDefault("HIFAC_3DMODE") - this.mConfigManager.getMin("HIFAC_3DMODE"), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW);
        mHisFacDataGroup_3DSetting.add(dataItem);
        dataItem.setEnable(true);
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_LR_SWAP", this.mContext.getString(R.string.menu_video_3d_leftright), 10004, 10004, this.mConfigManager.getDefault("HIFAC_LR_SWAP") - this.mConfigManager.getMin("HIFAC_LR_SWAP"), stringArray2, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW);
        mHisFacDataGroup_3DSetting.add(dataItem2);
        dataItem2.setEnable(true);
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("HIFAC_3DTO2D", this.mContext.getString(R.string.menu_video_3d_3t2), 10004, 10004, this.mConfigManager.getDefault("HIFAC_3DTO2D") - this.mConfigManager.getMin("HIFAC_3DTO2D"), stringArray3, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW);
        dataItem3.setEnable(true);
        ArrayList arrayList = new ArrayList();
        dataItem.mEffectGroup = arrayList;
        arrayList.add(dataItem2);
        dataItem.mEffectGroup.add(dataItem3);
        new HashMap();
        mHisFacDataGroup_3DSetting.add(dataItem3);
        return true;
    }

    public void loadHisDesData_AudioCurve() {
        mHisDesDataGroup_SoundCurve = new ArrayList();
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE1";
        mHisDesDataGroup_SoundCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE1", this.mContext.getString(R.string.str_factorymenu_curve1), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE25";
        mHisDesDataGroup_SoundCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE25", this.mContext.getString(R.string.str_factorymenu_curve25), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE50";
        mHisDesDataGroup_SoundCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE50", this.mContext.getString(R.string.str_factorymenu_curve50), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE75";
        mHisDesDataGroup_SoundCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE75", this.mContext.getString(R.string.str_factorymenu_curve75), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE100";
        mHisDesDataGroup_SoundCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE100", this.mContext.getString(R.string.str_factorymenu_curve100), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PRESCALE";
        mHisDesDataGroup_SoundCurve.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_prescale), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_soundcurve_prescale), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    public void loadHisDesData_AudioCurveNew() {
        mHisDesDataGroup_SoundCurveNew = new ArrayList();
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE1_NEW";
        mHisDesDataGroup_SoundCurveNew.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE1_NEW", this.mContext.getString(R.string.str_factorymenu_curve1), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE10_NEW";
        mHisDesDataGroup_SoundCurveNew.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE10_NEW", this.mContext.getString(R.string.str_factorymenu_curve10), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE25_NEW";
        mHisDesDataGroup_SoundCurveNew.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE25_NEW", this.mContext.getString(R.string.str_factorymenu_curve25), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE50_NEW";
        mHisDesDataGroup_SoundCurveNew.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE50_NEW", this.mContext.getString(R.string.str_factorymenu_curve50), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE75_NEW";
        mHisDesDataGroup_SoundCurveNew.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE75_NEW", this.mContext.getString(R.string.str_factorymenu_curve75), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_AUDIOCURVE_CURVE100_NEW";
        mHisDesDataGroup_SoundCurveNew.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIOCURVE_CURVE100_NEW", this.mContext.getString(R.string.str_factorymenu_curve100), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PRESCALE";
        mHisDesDataGroup_SoundCurveNew.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_prescale), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_soundcurve_prescale), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    public void loadHisDesData_AudioMode() {
        mHisDesDataGroup_SoundMode = new ArrayList();
        this.ItemName = "HIFAC_SOUNDMODE";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_mode), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_audiomode), 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        dataItem.mEffectGroup = new ArrayList();
        dataItem.setUserDefined(0);
        mHisDesDataGroup_SoundMode.add(dataItem);
        this.ItemName = "HIFAC_EQUALIZE_120HZ";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_120HZ", this.mContext.getString(R.string.str_factorymenu_120hz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem2.mParent = dataItem;
        mHisDesDataGroup_SoundMode.add(dataItem2);
        dataItem.mEffectGroup.add(dataItem2);
        this.ItemName = "HIFAC_EQUALIZE_500HZ";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_500HZ", this.mContext.getString(R.string.str_factorymenu_500hz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem3.mParent = dataItem;
        mHisDesDataGroup_SoundMode.add(dataItem3);
        dataItem.mEffectGroup.add(dataItem3);
        this.ItemName = "HIFAC_EQUALIZE_1500HZ";
        SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_1500HZ", this.mContext.getString(R.string.str_factorymenu_1dot5khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem4.mParent = dataItem;
        mHisDesDataGroup_SoundMode.add(dataItem4);
        dataItem.mEffectGroup.add(dataItem4);
        this.ItemName = "HIFAC_EQUALIZE_5KHZ";
        SetConfigListViewAdapter.DataItem dataItem5 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_5KHZ", this.mContext.getString(R.string.str_factorymenu_5khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem5.mParent = dataItem;
        mHisDesDataGroup_SoundMode.add(dataItem5);
        dataItem.mEffectGroup.add(dataItem5);
        this.ItemName = "HIFAC_EQUALIZE_10KHZ";
        SetConfigListViewAdapter.DataItem dataItem6 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_10KHZ", this.mContext.getString(R.string.str_factorymenu_10khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem6.mParent = dataItem;
        mHisDesDataGroup_SoundMode.add(dataItem6);
        dataItem.mEffectGroup.add(dataItem6);
        if (FacPropertyDemo.HIFAC_WAVES_SUPPORT) {
            this.ItemName = "HIFAC_EQUALIZE_20KHZ";
            SetConfigListViewAdapter.DataItem dataItem7 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_20KHZ", this.mContext.getString(R.string.str_factorymenu_10khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
            dataItem6.mParent = dataItem;
            mHisDesDataGroup_SoundMode.add(dataItem6);
            dataItem.mEffectGroup.add(dataItem7);
            this.ItemName = "HIFAC_EQUALIZE_30KHZ";
            SetConfigListViewAdapter.DataItem dataItem8 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_30KHZ", this.mContext.getString(R.string.str_factorymenu_10khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
            dataItem6.mParent = dataItem;
            mHisDesDataGroup_SoundMode.add(dataItem6);
            dataItem.mEffectGroup.add(dataItem8);
            this.ItemName = "HIFAC_EQUALIZE_40KHZ";
            SetConfigListViewAdapter.DataItem dataItem9 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_40KHZ", this.mContext.getString(R.string.str_factorymenu_10khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
            dataItem6.mParent = dataItem;
            mHisDesDataGroup_SoundMode.add(dataItem6);
            dataItem.mEffectGroup.add(dataItem9);
            this.ItemName = "HIFAC_EQUALIZE_50KHZ";
            SetConfigListViewAdapter.DataItem dataItem10 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_50KHZ", this.mContext.getString(R.string.str_factorymenu_10khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
            dataItem6.mParent = dataItem;
            mHisDesDataGroup_SoundMode.add(dataItem10);
            dataItem.mEffectGroup.add(dataItem6);
        }
    }

    public void loadHisDesData_AudioMode_More_Equalizers() {
        mHisDesDataGroup_SoundMode_More_Equalizers = new ArrayList();
        this.ItemName = "HIFAC_SOUNDMODE";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_mode), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_audiomode), 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        dataItem.mEffectGroup = new ArrayList();
        dataItem.setUserDefined(0);
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem);
        this.ItemName = "HIFAC_EQUALIZE_120HZ_NEW";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_120HZ_NEW", this.mContext.getString(R.string.str_factorymenu_120hz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem2.mParent = dataItem;
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem2);
        dataItem.mEffectGroup.add(dataItem2);
        this.ItemName = "HIFAC_EQUALIZE_250HZ";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_250HZ", this.mContext.getString(R.string.str_factorymenu_250hz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem3.mParent = dataItem;
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem3);
        dataItem.mEffectGroup.add(dataItem3);
        this.ItemName = "HIFAC_EQUALIZE_600HZ";
        SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_600HZ", this.mContext.getString(R.string.str_factorymenu_600hz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem4.mParent = dataItem;
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem4);
        dataItem.mEffectGroup.add(dataItem4);
        this.ItemName = "HIFAC_EQUALIZE_1KHZ";
        SetConfigListViewAdapter.DataItem dataItem5 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_1KHZ", this.mContext.getString(R.string.str_factorymenu_1Khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem5.mParent = dataItem;
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem5);
        dataItem.mEffectGroup.add(dataItem5);
        this.ItemName = "HIFAC_EQUALIZE_2.5KHZ";
        SetConfigListViewAdapter.DataItem dataItem6 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_2.5KHZ", this.mContext.getString(R.string.str_factorymenu_2500hz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem6.mParent = dataItem;
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem6);
        dataItem.mEffectGroup.add(dataItem6);
        this.ItemName = "HIFAC_EQUALIZE_6KHZ";
        SetConfigListViewAdapter.DataItem dataItem7 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_6KHZ", this.mContext.getString(R.string.str_factorymenu_6Khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem7.mParent = dataItem;
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem7);
        dataItem.mEffectGroup.add(dataItem7);
        this.ItemName = "HIFAC_EQUALIZE_10KHZ_NEW";
        SetConfigListViewAdapter.DataItem dataItem8 = new SetConfigListViewAdapter.DataItem("HIFAC_EQUALIZE_10KHZ_NEW", this.mContext.getString(R.string.str_factorymenu_10khz), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem8.mParent = dataItem;
        mHisDesDataGroup_SoundMode_More_Equalizers.add(dataItem8);
        dataItem.mEffectGroup.add(dataItem8);
    }

    public void loadHisDesData_ColorTuner() {
        mHisDesDataGroup_ColorTuner = new ArrayList();
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.factorymenu_colortuner);
        this.ItemName = "FACTORY_COLORTUNER";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("FACTORY_COLORTUNER", this.mContext.getString(R.string.str_factorymenu_colortuner), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisDesDataGroup_ColorTuner.add(dataItem);
        dataItem.setUserDefined(0);
        dataItem.mEffectGroup = new ArrayList();
        this.ItemName = "FACTORY_CT_HUE";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("FACTORY_CT_HUE", this.mContext.getString(R.string.str_factorymenu_ct_hue), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisDesDataGroup_ColorTuner.add(dataItem2);
        this.ItemName = "FACTORY_CT_SATURATION";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("FACTORY_CT_SATURATION", this.mContext.getString(R.string.str_factorymenu_ct_saturation), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisDesDataGroup_ColorTuner.add(dataItem3);
        this.ItemName = "FACTORY_CT_BRIGHTNESS";
        SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("FACTORY_CT_BRIGHTNESS", this.mContext.getString(R.string.str_factorymenu_ct_brightness), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisDesDataGroup_ColorTuner.add(dataItem4);
        dataItem.mEffectGroup.add(dataItem2);
        dataItem.mEffectGroup.add(dataItem3);
        dataItem.mEffectGroup.add(dataItem4);
        dataItem2.mParent = dataItem;
        dataItem3.mParent = dataItem;
        dataItem4.mParent = dataItem;
    }

    public void loadHisDesData_DRC_AVC() {
        mHisDesDataGroup_DRC_AVC = new ArrayList();
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_ntp8824);
        this.ItemName = "HIFAC_AUDIO_DRC_L";
        mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_DRC_L", this.mContext.getString(R.string.str_factory_audio_drc_low_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_AUDIO_DRC_H";
        mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_DRC_H", this.mContext.getString(R.string.str_factory_audio_drc_high_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_AUDIO_DRC_S";
        mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_DRC_S", this.mContext.getString(R.string.str_factory_audio_drc_medium_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_AUDIO_DRC_P";
        mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_DRC_P", this.mContext.getString(R.string.str_factory_audio_drc_post_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_AUDIO_PA_GAIN";
        mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_pa_gain), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_gain_ntp8824), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_AUDIO_OUTPUT_PORT";
        mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_output_port), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_output_port_ntp8824_8928), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        if (this.mConfigManager.getSubWooferTotal() >= 1) {
            this.ItemName = "HIFAC_AUDIO_SUB1_DRC_L";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB1_DRC_L", this.mContext.getString(R.string.str_factory_audio_sub1drc_low_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB1_DRC_H";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB1_DRC_H", this.mContext.getString(R.string.str_factory_audio_sub1drc_high_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB1_DRC_S";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB1_DRC_S", this.mContext.getString(R.string.str_factory_audio_sub1drc_medium_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB1_DRC_P";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB1_DRC_P", this.mContext.getString(R.string.str_factory_audio_sub1drc_post_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB1_AUDIO_PA_GAIN";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub1pa_gain), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_gain_ntp8824), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB1_AUDIO_OUTPUT_PORT";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub1output_port), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_output_port_ntp8824_8928), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        if (this.mConfigManager.getSubWooferTotal() >= 2) {
            this.ItemName = "HIFAC_AUDIO_SUB2_DRC_L";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB2_DRC_L", this.mContext.getString(R.string.str_factory_audio_sub2drc_low_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB2_DRC_H";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB2_DRC_H", this.mContext.getString(R.string.str_factory_audio_sub2drc_high_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB2_DRC_S";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB2_DRC_S", this.mContext.getString(R.string.str_factory_audio_sub2drc_medium_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB2_DRC_P";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB2_DRC_P", this.mContext.getString(R.string.str_factory_audio_sub2drc_post_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB2_AUDIO_PA_GAIN";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub2pa_gain), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_gain_ntp8824), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB2_AUDIO_OUTPUT_PORT";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub2output_port), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_output_port_ntp8824_8928), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        if (this.mConfigManager.getSubWooferTotal() >= 3) {
            this.ItemName = "HIFAC_AUDIO_SUB3_DRC_L";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB3_DRC_L", this.mContext.getString(R.string.str_factory_audio_sub3drc_low_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB3_DRC_H";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB3_DRC_H", this.mContext.getString(R.string.str_factory_audio_sub3drc_high_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB3_DRC_S";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB3_DRC_S", this.mContext.getString(R.string.str_factory_audio_sub3drc_medium_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB3_DRC_P";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB3_DRC_P", this.mContext.getString(R.string.str_factory_audio_sub3drc_post_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB3_AUDIO_PA_GAIN";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub3pa_gain), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_gain_ntp8824), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB3_AUDIO_OUTPUT_PORT";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub3output_port), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_output_port_ntp8824_8928), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        if (this.mConfigManager.getSubWooferTotal() >= 4) {
            this.ItemName = "HIFAC_AUDIO_SUB4_DRC_L";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB4_DRC_L", this.mContext.getString(R.string.str_factory_audio_sub4drc_low_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB4_DRC_H";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB4_DRC_H", this.mContext.getString(R.string.str_factory_audio_sub4drc_high_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB4_DRC_S";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB4_DRC_S", this.mContext.getString(R.string.str_factory_audio_sub4drc_medium_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB4_DRC_P";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB4_DRC_P", this.mContext.getString(R.string.str_factory_audio_sub4drc_post_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB4_AUDIO_PA_GAIN";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub4pa_gain), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_gain_ntp8824), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB4_AUDIO_OUTPUT_PORT";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub4output_port), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_output_port_ntp8824_8928), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        if (this.mConfigManager.getSubWooferTotal() >= 5) {
            this.ItemName = "HIFAC_AUDIO_SUB5_DRC_L";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB5_DRC_L", this.mContext.getString(R.string.str_factory_audio_sub5drc_low_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB5_DRC_H";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB5_DRC_H", this.mContext.getString(R.string.str_factory_audio_sub5drc_high_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB5_DRC_S";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB5_DRC_S", this.mContext.getString(R.string.str_factory_audio_sub5drc_medium_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_AUDIO_SUB5_DRC_P";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUDIO_SUB5_DRC_P", this.mContext.getString(R.string.str_factory_audio_sub5drc_post_band), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB5_AUDIO_PA_GAIN";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub5pa_gain), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_gain_ntp8824), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SUB5_AUDIO_OUTPUT_PORT";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_audio_sub5output_port), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_audio_drc_output_port_ntp8824_8928), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        if (FacPropertyDemo.HIFAC_AVC_SUPPORT) {
            this.ItemName = "HIFAC_AVC_THRESHOLD";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_avc_threshold), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_tothreshold), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "FACTORY_AVC_ATTACK_TIME";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_avc_attack_time), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_toattacktime), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "FACTORY_AVC_RELEASE_TIME";
            mHisDesDataGroup_DRC_AVC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_avc_release_time), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_toreleasetime), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
    }

    public void loadHisDesData_Waves() {
        mHisDesDataGroup_Waves = new ArrayList();
        this.ItemName = "HIFAC_MAXXBASS_FREQUENCY";
        mHisDesDataGroup_Waves.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_maxxbass_frequency), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_waves_maxxbass_frequency), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_MAXXTREBLE_FREQUENCY";
        mHisDesDataGroup_Waves.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_maxxtreble_frequency), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_waves_maxxtreble_frequency), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_MAXX3D_OPTION";
        mHisDesDataGroup_Waves.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factory_maxx3d_option), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_waves_maxx_3d_option), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    public void loadHisDesData_LightSensor() {
        mHisDesDataGroup_LightSensor = new ArrayList();
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX0";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX0", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index0)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE0";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE0", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE0";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE0", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM0";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM0", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX1";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX1", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index1)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE1";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE1", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE1";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE1", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM1";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM1", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX2";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX2", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index2)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE2";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE2", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE2";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE2", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM2";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM2", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX3";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX3", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index3)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE3";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE3", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE3";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE3", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM3";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM3", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX4";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX4", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index4)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE4";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE4", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE4";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE4", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM4";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM4", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX5";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX5", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index5)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE5";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE5", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE5";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE5", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM5";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM5", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX6";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX6", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index6)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE6";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE6", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE6";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE6", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM6";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM6", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LIGHT_SENSOR_INDEX7";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_INDEX7", this.mContext.getString(R.string.str_factory_light_sensor_index), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factory_light_sensor_index7)));
        this.ItemName = "HIFAC_LIGHT_SENSOR_VALUE7";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_LIGHT_SENSOR_VALUE7", this.mContext.getString(R.string.str_factory_light_sensor_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_LUX_VALUE7";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("FACTORY_LUX_VALUE7", this.mContext.getString(R.string.str_factory_LUX_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PWM7";
        mHisDesDataGroup_LightSensor.add(new SetConfigListViewAdapter.DataItem("HIFAC_PWM7", this.mContext.getString(R.string.str_factory_light_sensor_PWM), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_LUX";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("HIFAC_LUX", this.mContext.getString(R.string.str_factory_light_sensor_LUX), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisDesDataGroup_LightSensor.add(dataItem);
        dataItem.mSubChildGroup = new ArrayList();
    }

    public void loadHisDesData_OverScan() {
        mHisDesDataGroup_OverScan = new ArrayList();
        this.ItemName = "HIFAC_OVERSCAN_TOP";
        mHisDesDataGroup_OverScan.add(new SetConfigListViewAdapter.DataItem("HIFAC_OVERSCAN_TOP", this.mContext.getString(R.string.str_factory_overscan_top_margin), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_OVERSCAN_BUTTOM";
        mHisDesDataGroup_OverScan.add(new SetConfigListViewAdapter.DataItem("HIFAC_OVERSCAN_BUTTOM", this.mContext.getString(R.string.str_factory_overscan_bottom_margin), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_OVERSCAN_LEFT";
        mHisDesDataGroup_OverScan.add(new SetConfigListViewAdapter.DataItem("HIFAC_OVERSCAN_LEFT", this.mContext.getString(R.string.str_factory_overscan_left_margin), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_OVERSCAN_RIGHT";
        mHisDesDataGroup_OverScan.add(new SetConfigListViewAdapter.DataItem("HIFAC_OVERSCAN_RIGHT", this.mContext.getString(R.string.str_factory_overscan_right_margin), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
    }

    public void loadHisDesData_PictureCurve() {
        mHisDesDataGroup_PictureCurve = new ArrayList();
        this.ItemName = "HIFAC_PICTURECURVE_BRIGHT_MIN";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_BRIGHT_MIN", this.mContext.getString(R.string.str_factorymenu_videocurve0), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_BRIGHT_20";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_BRIGHT_20", this.mContext.getString(R.string.str_factorymenu_videocurve_brightness_20), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_BRIGHT_MID";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_BRIGHT_MID", this.mContext.getString(R.string.str_factorymenu_videocurve1), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_BRIGHT_80";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_BRIGHT_80", this.mContext.getString(R.string.str_factorymenu_videocurve_brightness_80), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_BRIGHT_MAX";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_BRIGHT_MAX", this.mContext.getString(R.string.str_factorymenu_videocurve2), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_CONTRACT_MIN";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_CONTRACT_MIN", this.mContext.getString(R.string.str_factorymenu_videocurve3), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_CONTRACT_20";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_CONTRACT_20", this.mContext.getString(R.string.str_factorymenu_videocurve_contrast_20), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_CONTRACT_MID";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_CONTRACT_MID", this.mContext.getString(R.string.str_factorymenu_videocurve4), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_CONTRACT_80";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_CONTRACT_80", this.mContext.getString(R.string.str_factorymenu_videocurve_contrast_80), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_CONTRACT_MAX";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_CONTRACT_MAX", this.mContext.getString(R.string.str_factorymenu_videocurve5), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SATURATION_MIN";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SATURATION_MIN", this.mContext.getString(R.string.str_factorymenu_videocurve6), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_SATURATION_20";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_SATURATION_20", this.mContext.getString(R.string.str_factorymenu_videocurve_saturation_20), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SATURATION_MID";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SATURATION_MID", this.mContext.getString(R.string.str_factorymenu_videocurve7), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_SATURATION_80";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_SATURATION_80", this.mContext.getString(R.string.str_factorymenu_videocurve_saturation_80), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SATURATION_MAX";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SATURATION_MAX", this.mContext.getString(R.string.str_factorymenu_videocurve8), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_HUE_MIN";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_HUE_MIN", this.mContext.getString(R.string.str_factorymenu_videocurve9), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_HUE_20";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_HUE_20", this.mContext.getString(R.string.str_factorymenu_videocurve_hue_20), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_HUE_MID";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_HUE_MID", this.mContext.getString(R.string.str_factorymenu_videocurve10), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_HUE_80";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_HUE_80", this.mContext.getString(R.string.str_factorymenu_videocurve_hue_80), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_HUE_MAX";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_HUE_MAX", this.mContext.getString(R.string.str_factorymenu_videocurve11), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SHARPNESS_MIN";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SHARPNESS_MIN", this.mContext.getString(R.string.str_factorymenu_videocurve_sharpness_min), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SHARPNESS_20";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SHARPNESS_20", this.mContext.getString(R.string.str_factorymenu_videocurve_sharpness_20), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SHARPNESS_MID";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SHARPNESS_MID", this.mContext.getString(R.string.str_factorymenu_videocurve_sharpness_mid), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SHARPNESS_80";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SHARPNESS_80", this.mContext.getString(R.string.str_factorymenu_videocurve_sharpness_80), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PICTURECURVE_SHARPNESS_MAX";
        mHisDesDataGroup_PictureCurve.add(new SetConfigListViewAdapter.DataItem("HIFAC_PICTURECURVE_SHARPNESS_MAX", this.mContext.getString(R.string.str_factorymenu_videocurve_sharpness_max), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
    }

    public void loadHisDesData_PictureMode() {
        String[] stringArray;
        mHisDesDataGroup_PictureMode = new ArrayList();
        this.ItemName = "HIFAC_PICTUREMODE";
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_MARKET_TW) {
                this.ItemName = "HIFAC_PICTUREMODE_TW";
                stringArray = this.mContext.getResources().getStringArray(R.array.designmenu_picturemode_for_TW_Toshiba);
            } else {
                stringArray = this.mContext.getResources().getStringArray(R.array.designmenu_picturemode_for_em);
            }
        } else {
            stringArray = this.mContext.getResources().getStringArray(R.array.designmenu_picturemode);
        }
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_mode), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisDesDataGroup_PictureMode.add(dataItem);
        dataItem.setUserDefined(0);
        dataItem.mEffectGroup = new ArrayList();
        this.ItemName = "HIFAC_PICTUREMODE_BRIGHTNESS";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_PICTUREMODE_BRIGHTNESS", this.mContext.getString(R.string.str_factorymenu_brightness), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem2.mParent = dataItem;
        mHisDesDataGroup_PictureMode.add(dataItem2);
        dataItem.mEffectGroup.add(dataItem2);
        this.ItemName = "HIFAC_PICTUREMODE_CONTRAST";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("HIFAC_PICTUREMODE_CONTRAST", this.mContext.getString(R.string.str_factorymenu_contrast), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem3.mParent = dataItem;
        mHisDesDataGroup_PictureMode.add(dataItem3);
        dataItem.mEffectGroup.add(dataItem3);
        this.ItemName = "HIFAC_PICTUREMODE_COLOR";
        SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("HIFAC_PICTUREMODE_COLOR", this.mContext.getString(R.string.str_factorymenu_color), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem4.mParent = dataItem;
        mHisDesDataGroup_PictureMode.add(dataItem4);
        dataItem.mEffectGroup.add(dataItem4);
        this.ItemName = "HIFAC_PICTUREMODE_SHARPNESS";
        SetConfigListViewAdapter.DataItem dataItem5 = new SetConfigListViewAdapter.DataItem("HIFAC_PICTUREMODE_SHARPNESS", this.mContext.getString(R.string.str_factorymenu_sharpness), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem5.mParent = dataItem;
        mHisDesDataGroup_PictureMode.add(dataItem5);
        dataItem.mEffectGroup.add(dataItem5);
        this.ItemName = "HIFAC_PICTUREMODE_BACKLIGHT";
        SetConfigListViewAdapter.DataItem dataItem6 = new SetConfigListViewAdapter.DataItem("HIFAC_PICTUREMODE_BACKLIGHT", this.mContext.getString(R.string.str_factorymenu_backlight), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        dataItem6.mParent = dataItem;
        mHisDesDataGroup_PictureMode.add(dataItem6);
        dataItem.mEffectGroup.add(dataItem6);
    }

    public void loadHisDesData_SavingMode() {
        mHisDesDataGroup_SavingMode = new ArrayList();
        this.ItemName = "FACTORY_BL_MIN_BACKLIGHT";
        mHisDesDataGroup_SavingMode.add(new SetConfigListViewAdapter.DataItem("FACTORY_BL_MIN_BACKLIGHT", this.mContext.getString(R.string.str_factorymenu_min_backlight), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_BL_20_BACKLIGHT";
        mHisDesDataGroup_SavingMode.add(new SetConfigListViewAdapter.DataItem("FACTORY_BL_20_BACKLIGHT", this.mContext.getString(R.string.str_factorymenu_20_backlight), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_BL_MID_BACKLIGHT";
        mHisDesDataGroup_SavingMode.add(new SetConfigListViewAdapter.DataItem("FACTORY_BL_MID_BACKLIGHT", this.mContext.getString(R.string.str_factorymenu_mid_backlight), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_BL_80_BACKLIGHT";
        mHisDesDataGroup_SavingMode.add(new SetConfigListViewAdapter.DataItem("FACTORY_BL_80_BACKLIGHT", this.mContext.getString(R.string.str_factorymenu_80_backlight), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_BL_MAX_BACKLIGHT";
        mHisDesDataGroup_SavingMode.add(new SetConfigListViewAdapter.DataItem("FACTORY_BL_MAX_BACKLIGHT", this.mContext.getString(R.string.str_factorymenu_max_backlight), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "FACTORY_BL_PWM_FREQ";
        mHisDesDataGroup_SavingMode.add(new SetConfigListViewAdapter.DataItem("FACTORY_BL_PWM_FREQ", this.mContext.getString(R.string.str_factorymenu_pwm_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
    }

    public void loadHisDesData_WB20_Point() {
        mHisDesDataGroup_WB20_Point = new ArrayList();
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.factorymenu_wb20_level);
        this.ItemName = "FACTORY_20POINT_LEVEL";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("FACTORY_20POINT_LEVEL", this.mContext.getString(R.string.str_factorymenu_20point_level), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisDesDataGroup_WB20_Point.add(dataItem);
        dataItem.setUserDefined(0);
        dataItem.mEffectGroup = new ArrayList();
        this.ItemName = "FACTORY_20POINT_RED";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("FACTORY_20POINT_RED", this.mContext.getString(R.string.str_factorymenu_20point_red), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisDesDataGroup_WB20_Point.add(dataItem2);
        this.ItemName = "FACTORY_20POINT_GREEN";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("FACTORY_20POINT_GREEN", this.mContext.getString(R.string.str_factorymenu_20point_green), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisDesDataGroup_WB20_Point.add(dataItem3);
        this.ItemName = "FACTORY_20POINT_BLUE";
        SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("FACTORY_20POINT_BLUE", this.mContext.getString(R.string.str_factorymenu_20point_blue), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisDesDataGroup_WB20_Point.add(dataItem4);
        dataItem.mEffectGroup.add(dataItem2);
        dataItem.mEffectGroup.add(dataItem3);
        dataItem.mEffectGroup.add(dataItem4);
        dataItem2.mParent = dataItem;
        dataItem3.mParent = dataItem;
        dataItem4.mParent = dataItem;
    }

    public void loadHisDesData_Bypass() {
        mHisDesDataGroup_Bypass = new ArrayList();
        this.ItemName = "HIFAC_BYPASS_PQ";
        mHisDesDataGroup_Bypass.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_bypass_pq), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_bypass), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_BYPASS_GAMMA";
        mHisDesDataGroup_Bypass.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_bypass_gamma), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_bypass), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_BYPASS_SEC";
        mHisDesDataGroup_Bypass.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_bypass_sec), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_bypass), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_BYPASS_XVYCC";
        mHisDesDataGroup_Bypass.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_bypass_xvycc), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_bypass), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    public void loadHisDesData_5G_Info() {
        mHisDesDataGroup_5G = new ArrayList();
        this.ItemName = "CHANNEL_5G";
        mHisDesDataGroup_5G.add(new SetConfigListViewAdapter.DataItem("CHANNEL_5G", this.mContext.getString(R.string.str_factorymenu_5g_channel), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.get5GChannel()));
    }

    public void loadHisDesData_Dolby_IQ() {
        mHisDesDataGroup_Dolby = new ArrayList();
        this.ItemName = "FACTORY_Dolby_IQ";
        mHisDesDataGroup_Dolby.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_dolby_iq), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.fac_dolby_iq), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_Dolby_IQ_Uprade";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("HIFAC_Dolby_IQ_Uprade", this.mContext.getString(R.string.str_factory_Dolby_IQ), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisDesDataGroup_Dolby.add(dataItem);
        dataItem.mSubChildGroup = new ArrayList();
        this.ItemName = "HIFAC_Dolby_Upgrade_Infor";
        mHisDesDataGroup_Dolby.add(new SetConfigListViewAdapter.DataItem("HIFAC_Dolby_Upgrade_Infor", null, 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factorymenu_dolby_iq_store_information)));
        this.ItemName = "HIFAC_Dolby_User_Bin_Time_Infor";
        mHisDesDataGroup_Dolby.add(new SetConfigListViewAdapter.DataItem("HIFAC_Dolby_User_Bin_Time_Infor", this.mContext.getString(R.string.str_factory_Dolby_IQ_User_Bin_File_Time), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getDolbyIQUserBinTimeInfo()));
        this.ItemName = "HIFAC_Dolby_User_Bin_Size_Infor";
        mHisDesDataGroup_Dolby.add(new SetConfigListViewAdapter.DataItem("HIFAC_Dolby_User_Bin_Size_Infor", this.mContext.getString(R.string.str_factory_Dolby_IQ_User_Bin_File_Size), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getDolbyIQUserBinSizeInfo()));
    }

    public void loadHisDesData_PEQ() {
        mHisDesDataGroup_Peq = new ArrayList();
        this.ItemName = "HIFAC_PEQ_1_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_1_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_1_preq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_1_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_1_STEP", this.mContext.getString(R.string.str_factorymenu_peq_1_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_1_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_1_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_1_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_1_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_1_Q", this.mContext.getString(R.string.str_factorymenu_peq_1_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_2_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_2_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_2_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_2_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_2_STEP", this.mContext.getString(R.string.str_factorymenu_peq_2_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_2_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_2_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_2_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_2_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_2_Q", this.mContext.getString(R.string.str_factorymenu_peq_2_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_3_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_3_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_3_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_3_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_3_STEP", this.mContext.getString(R.string.str_factorymenu_peq_3_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_3_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_3_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_3_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_3_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_3_Q", this.mContext.getString(R.string.str_factorymenu_peq_3_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_4_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_4_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_4_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_4_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_4_STEP", this.mContext.getString(R.string.str_factorymenu_peq_4_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_4_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_4_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_4_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_4_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_4_Q", this.mContext.getString(R.string.str_factorymenu_peq_4_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_5_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_5_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_5_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_5_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_5_STEP", this.mContext.getString(R.string.str_factorymenu_peq_5_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_5_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_5_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_5_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_5_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_5_Q", this.mContext.getString(R.string.str_factorymenu_peq_5_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_6_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_6_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_6_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_6_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_6_STEP", this.mContext.getString(R.string.str_factorymenu_peq_6_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_6_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_6_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_6_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_6_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_6_Q", this.mContext.getString(R.string.str_factorymenu_peq_6_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_7_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_7_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_7_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_7_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_7_STEP", this.mContext.getString(R.string.str_factorymenu_peq_7_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_7_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_7_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_7_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_7_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_7_Q", this.mContext.getString(R.string.str_factorymenu_peq_7_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_8_FREQ";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_8_FREQ", this.mContext.getString(R.string.str_factorymenu_peq_8_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_8_STEP";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_8_STEP", this.mContext.getString(R.string.str_factorymenu_peq_8_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_8_GAIN";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_8_GAIN", this.mContext.getString(R.string.str_factorymenu_peq_8_gain), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_PEQ_8_Q";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_PEQ_8_Q", this.mContext.getString(R.string.str_factorymenu_peq_8_q), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        new ArrayList();
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.facmenu_aud_peq_enable);
        this.ItemName = "HIFAC_AUD_PEQ_ENABLE";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("HIFAC_AUD_PEQ_ENABLE", this.mContext.getString(R.string.str_factorymenu_aud_peq_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisDesDataGroup_Peq.add(dataItem);
        dataItem.setUserDefined(0);
        dataItem.mEffectGroup = new ArrayList();
        new ArrayList();
        String[] stringArray2 = this.mContext.getResources().getStringArray(R.array.facmenu_aud_hpf);
        this.ItemName = "HIFAC_AUD_HPF";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_AUD_HPF", this.mContext.getString(R.string.str_factorymenu_aud_hpf), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray2, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisDesDataGroup_Peq.add(dataItem2);
        dataItem2.setUserDefined(0);
        dataItem2.mEffectGroup = new ArrayList();
        this.ItemName = "HIFAC_HPF_FREQUENCY";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_HPF_FREQUENCY", this.mContext.getString(R.string.str_factorymenu_hpf_frequency), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_SOC_DRC";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_SOC_DRC", this.mContext.getString(R.string.str_factorymenu_soc_drc), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        new ArrayList();
        String[] stringArray3 = this.mContext.getResources().getStringArray(R.array.facmenu_aud_soc_drc_enable);
        this.ItemName = "HIFAC_AUD_SOC_DRC_ENABLE";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("HIFAC_AUD_SOC_DRC_ENABLE", this.mContext.getString(R.string.str_factorymenu_aud_soc_drc_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray3, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisDesDataGroup_Peq.add(dataItem3);
        dataItem3.setUserDefined(0);
        dataItem3.mEffectGroup = new ArrayList();
        this.ItemName = "HIFAC_AUD_AUTO_VOLUEM_CTRL_ENABLE";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_aud_auto_volume_ctrl_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_aud_auto_volume_ctrl_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_AUD_AUTO_VOLUEM_CTRL_THRESHOLD";
        mHisDesDataGroup_Peq.add(new SetConfigListViewAdapter.DataItem("HIFAC_AUD_AUTO_VOLUEM_CTRL_THRESHOLD", this.mContext.getString(R.string.str_factorymenu_aud_auto_volume_ctrl_threshold), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
    }

    public void loadHisDesData_OLED() {
        mHisDesDataGroup_OLED = new ArrayList();
        this.ItemName = "HIFAC_OLED_TPC_ENABLE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_tpc_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_tpc_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.oled_move_type);
        this.ItemName = "HIFAC_OLED_PIXEL_MOVE_TYPE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_OLED_PIXEL_MOVE_TYPE", this.mContext.getString(R.string.str_pixel_shift_type), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_OLED_PIXEL_MOVE_TIMER";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_OLED_PIXEL_MOVE_TIMER", this.mContext.getString(R.string.str_pixel_shift_timer), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        this.ItemName = "HIFAC_OLED_PIXEL_MOVE_SHAPE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_pixel_shift_shape), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_move_shape), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_OLED_LEA_ENABLE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_lea_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_lea_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_OLED_CPC_ENABLE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_cpc_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_cpc_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_OLED_LBC2_ENABLE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_lbc2_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_lbc2_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_OLED_ORBIT_ENABLE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_orbit_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_orbit_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_OLED_LBA_ENABLE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_lba_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_lba_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_OLED_HDR_ENABLE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_hdr_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.oled_hdr_enable), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_FUN_MAJOR_MAINTENANCE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_FUN_MAJOR_MAINTENANCE", this.mContext.getString(R.string.str_factorymenu_major_maintenance), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        this.ItemName = "HIFAC_FUN_MINOR_MAINTENANCE";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_FUN_MINOR_MAINTENANCE", this.mContext.getString(R.string.str_factorymenu_minor_maintenance), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        this.ItemName = "HISFAC_RUNNING_TIME";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HISFAC_RUNNING_TIME", this.mContext.getString(R.string.str_tv_running_time), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getTVRunningTime()));
        this.ItemName = "HIFAC_OLED_JB_EXEC_TIMES";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_OLED_JB_EXEC_TIMES", this.mContext.getString(R.string.str_jb_exec_times), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getJBExecuteCount() + ""));
        this.ItemName = "HIFAC_OLED_OFFRS_EXEC_TIMES";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_OLED_OFFRS_EXEC_TIMES", this.mContext.getString(R.string.str_offrs_exec_times), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getOffrsExecuteCount() + ""));
        this.ItemName = "HIFAC_OLED_JB_LAST_EXEC";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_OLED_JB_LAST_EXEC", this.mContext.getString(R.string.str_jb_last_exec), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getJBExecuteTime()));
        this.ItemName = "HIFAC_OLED_OFFRS_LAST_EXEC";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_OLED_OFFRS_LAST_EXEC", this.mContext.getString(R.string.str_offrs_last_exec), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getOffrsExecuteTime()));
        this.ItemName = "HIFAC_OLED_ERROR_DET_TIMES";
        mHisDesDataGroup_OLED.add(new SetConfigListViewAdapter.DataItem("HIFAC_OLED_ERROR_DET_TIMES", this.mContext.getString(R.string.str_error_det_times), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getErrDetCount() + ""));
    }

    public void loadHisDesData_SSC() {
        if (this.mConfigManager.isSSCSupported()) {
            mHisDesDataGroup_SSC = new ArrayList();
            if (this.mConfigManager.isSSCVByOneEnable()) {
                this.ItemName = "HIFAC_SSC_VBYONE_ENABLE";
                mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_lvds_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_ssc), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            }
            this.ItemName = "HIFAC_SSC_VBYONE_RANGE";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_SSC_VBYONE_RANGE", this.mContext.getString(R.string.str_factorymenu_lvds_depth), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
            this.ItemName = "HIFAC_LVDS_PEPTH_UNITS";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_LVDS_PEPTH_UNITS", this.mContext.getString(R.string.str_factorymenu_lvds_depth_units), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, "30(3%)"));
            this.ItemName = "HIFAC_SSC_VBYONE_SPAN";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_SSC_VBYONE_SPAN", this.mContext.getString(R.string.str_factorymenu_lvds_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, FacPropertyDemo.HIFAC_VBYONE_SPAN_STEP, SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
            this.ItemName = "HIFAC_LVDS_FREQ_UNITS";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_LVDS_FREQ_UNITS", this.mContext.getString(R.string.str_factorymenu_lvds_freq_units), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, "300(30KHZ)"));
            if (this.mConfigManager.isSSCDdrEnable()) {
                this.ItemName = "HIFAC_SSC_DDR_ENABLE";
                mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_ddr_enable), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.designmenu_ssc), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            }
            this.ItemName = "HIFAC_SSC_DDR_SPAN";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_SSC_DDR_SPAN", this.mContext.getString(R.string.str_factorymenu_ddr_depth), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
            this.ItemName = "HIFAC_DDR_DEPTH_UNITS";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_DDR_DEPTH_UNITS", this.mContext.getString(R.string.str_factorymenu_ddr_depth_units), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, "30(0.01%)"));
            this.ItemName = "HIFAC_SSC_DDR_MODULATION";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_SSC_DDR_MODULATION", this.mContext.getString(R.string.str_factorymenu_ddr_freq), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
            this.ItemName = "HIFAC_DDR_FREQ_UNITS";
            mHisDesDataGroup_SSC.add(new SetConfigListViewAdapter.DataItem("HIFAC_DDR_FREQ_UNITS", this.mContext.getString(R.string.str_factorymenu_ddr_freq_units), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, "30(KHZ)"));
        }
    }

    public void loadHisDesignData() {
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            mHisDesignDataGroup = this.mContext.getResources().getStringArray(R.array.desmenu_mainitem_jp);
        } else {
            mHisDesignDataGroup = this.mContext.getResources().getStringArray(R.array.desmenu_mainitem);
        }
    }

    public void loadHisFacData_ChannelSet() {
        if (this.mConfigManager.isHaveTuner()) {
            mHisFacDataGroup_Init = new ArrayList();
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                this.ItemName = "huangdao_em";
                SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("huangdao_em", this.mContext.getString(R.string.str_factorymenu_huangdao), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem);
                dataItem.mSubChildGroup = new ArrayList();
                this.ItemName = "qingdao";
                SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("qingdao", this.mContext.getString(R.string.str_factorymenu_qingdao), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem2);
                dataItem2.mSubChildGroup = new ArrayList();
                this.ItemName = "guiyang";
                SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("guiyang", this.mContext.getString(R.string.str_factorymenu_guiyang), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem3);
                dataItem3.mSubChildGroup = new ArrayList();
                this.ItemName = "jiangmen";
                SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("jiangmen", this.mContext.getString(R.string.str_factorymenu_jiangmen), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem4);
                dataItem4.mSubChildGroup = new ArrayList();
                this.ItemName = "south africa";
                SetConfigListViewAdapter.DataItem dataItem5 = new SetConfigListViewAdapter.DataItem("south africa", this.mContext.getString(R.string.str_factorymenu_southafrica), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem5);
                dataItem5.mSubChildGroup = new ArrayList();
                this.ItemName = "algeria";
                SetConfigListViewAdapter.DataItem dataItem6 = new SetConfigListViewAdapter.DataItem("algeria", this.mContext.getString(R.string.str_factorymenu_algeria), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem6);
                dataItem6.mSubChildGroup = new ArrayList();
                this.ItemName = "malaysia";
                SetConfigListViewAdapter.DataItem dataItem7 = new SetConfigListViewAdapter.DataItem("malaysia", this.mContext.getString(R.string.str_factorymenu_malaysia), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem7);
                dataItem7.mSubChildGroup = new ArrayList();
                this.ItemName = "indonesia";
                SetConfigListViewAdapter.DataItem dataItem8 = new SetConfigListViewAdapter.DataItem("indonesia", this.mContext.getString(R.string.str_factorymenu_indonesia), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem8);
                dataItem8.mSubChildGroup = new ArrayList();
                this.ItemName = "iran";
                SetConfigListViewAdapter.DataItem dataItem9 = new SetConfigListViewAdapter.DataItem("iran", this.mContext.getString(R.string.str_factorymenu_iran), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem9);
                dataItem9.mSubChildGroup = new ArrayList();
                this.ItemName = "ruassia";
                SetConfigListViewAdapter.DataItem dataItem10 = new SetConfigListViewAdapter.DataItem("ruassia", this.mContext.getString(R.string.str_factorymenu_ruassia), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem10);
                dataItem10.mSubChildGroup = new ArrayList();
                this.ItemName = "nigeria";
                SetConfigListViewAdapter.DataItem dataItem11 = new SetConfigListViewAdapter.DataItem("nigeria", this.mContext.getString(R.string.str_factorymenu_nigeria), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem11);
                dataItem11.mSubChildGroup = new ArrayList();
                this.ItemName = "pakistan";
                SetConfigListViewAdapter.DataItem dataItem12 = new SetConfigListViewAdapter.DataItem("pakistan", this.mContext.getString(R.string.str_factorymenu_pakistan), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem12);
                dataItem12.mSubChildGroup = new ArrayList();
                this.ItemName = "egypt tsb";
                SetConfigListViewAdapter.DataItem dataItem13 = new SetConfigListViewAdapter.DataItem("egypt tsb", this.mContext.getString(R.string.str_factorymenu_egypt_tsb), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem13);
                dataItem13.mSubChildGroup = new ArrayList();
                this.ItemName = "angola";
                SetConfigListViewAdapter.DataItem dataItem14 = new SetConfigListViewAdapter.DataItem("angola", this.mContext.getString(R.string.str_factorymenu_angola), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem14);
                dataItem14.mSubChildGroup = new ArrayList();
                this.ItemName = "brandt";
                SetConfigListViewAdapter.DataItem dataItem15 = new SetConfigListViewAdapter.DataItem("brandt", this.mContext.getString(R.string.str_factorymenu_brandt), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem15);
                dataItem15.mSubChildGroup = new ArrayList();
                this.ItemName = "polytron";
                SetConfigListViewAdapter.DataItem dataItem16 = new SetConfigListViewAdapter.DataItem("polytron", this.mContext.getString(R.string.str_factorymenu_polytron), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem16);
                dataItem16.mSubChildGroup = new ArrayList();
                return;
            }
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.ItemName = "huangdao_sa";
                SetConfigListViewAdapter.DataItem dataItem17 = new SetConfigListViewAdapter.DataItem("huangdao_sa", this.mContext.getString(R.string.str_factorymenu_huangdao), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem17);
                dataItem17.mSubChildGroup = new ArrayList();
                this.ItemName = "qingdao_sa";
                SetConfigListViewAdapter.DataItem dataItem18 = new SetConfigListViewAdapter.DataItem("qingdao_sa", this.mContext.getString(R.string.str_factorymenu_qingdao), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem18);
                dataItem18.mSubChildGroup = new ArrayList();
                this.ItemName = "jiangmen_sa";
                SetConfigListViewAdapter.DataItem dataItem19 = new SetConfigListViewAdapter.DataItem("jiangmen_sa", this.mContext.getString(R.string.str_factorymenu_jiangmen), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
                mHisFacDataGroup_Init.add(dataItem19);
                dataItem19.mSubChildGroup = new ArrayList();
                return;
            }
            this.ItemName = "huangdao";
            SetConfigListViewAdapter.DataItem dataItem20 = new SetConfigListViewAdapter.DataItem("huangdao", this.mContext.getString(R.string.str_factorymenu_huangdao), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
            mHisFacDataGroup_Init.add(dataItem20);
            dataItem20.mSubChildGroup = new ArrayList();
        }
    }

    public void loadHisFacData_Function(boolean z) {
        String[] stringArray;
        String[] stringArray2;
        String[] strArrGeBrandIsolateList;
        mHisFacDataGroup_Function = new ArrayList();
        this.ItemName = "HIFAC_TOFAC";
        mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_tofac), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_tofac), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_UNPROTECTEDCLEAR";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("HIFAC_UNPROTECTEDCLEAR", this.mContext.getString(R.string.str_factorymenu_clearunprotectly), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Function.add(dataItem);
        dataItem.mSubChildGroup = new ArrayList();
        if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
            this.ItemName = "FACTORY_OPTION_VCOM_VOLTAGE";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_OPTION_VCOM_VOLTAGE", this.mContext.getString(R.string.str_factorymenu_option_vcom_voltage), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        }
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            this.ItemName = "FACTORY_OPTION_VCOM2_VOLTAGE";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_OPTION_VCOM2_VOLTAGE", this.mContext.getString(R.string.str_factorymenu_option_vcom2_voltage), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        }
        if (FacPropertyDemo.HIFAC_UART_SWITCH_SUPPORT) {
            this.ItemName = "HIFAC_UART_SWITCH";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_uart_switch), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_uart_switch), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        this.ItemName = "HIFAC_SETMAC";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_SETMAC", this.mContext.getString(R.string.str_factorymenu_setmacaddr), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Function.add(dataItem2);
        dataItem2.mSubChildGroup = new ArrayList();
        if (FacPropertyDemo.HIFAC_MARKET_HK) {
            this.ItemName = "HIFAC_SETLOCAL_HK";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SETLOCAL_HK", this.mContext.getString(R.string.str_factorymenu_local), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), new String[]{MtkTvConfigTypeBase.S3166_CFG_COUNT_HKG}, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SETLANGUAGE_FOR_HK";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_hk_language), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            this.ItemName = "HIFAC_SETCOUNTRY_MONITOR";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_local), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_local_monitor), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SETLANGUAGE_FOR_MONITOR";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_monitor_language), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_TW) {
            this.ItemName = "HIFAC_SETLOCAL_TWN";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_local), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_local_twn), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SETLANGUAGE_FOR_TWN";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_tw_language), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.ItemName = "HIFAC_SETLOCAL_JPN";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_local), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_local_jpn), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SETLANGUAGE_FOR_JPN";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_jpn_language), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_KO) {
            this.ItemName = "HIFAC_SETLOCAL_KOR";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_local), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_local_kor), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_SETLANGUAGE_FOR_KOR";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_kor_language), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else {
            int i = 0;
            if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                this.ItemName = "HIFAC_SETREGION";
                if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
                    stringArray = this.mContext.getResources().getStringArray(R.array.factorymenu_region);
                } else {
                    stringArray = this.mContext.getResources().getStringArray(R.array.factorymenu_region_no_region5);
                }
                SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_region), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
                mHisFacDataGroup_Function.add(dataItem3);
                dataItem3.setUserDefined(0);
                dataItem3.mEffectGroup = new ArrayList();
                int region = this.mConfigManager.getRegion();
                if (stringArray.length > region && region >= 0) {
                    i = region;
                }
                setRegionMenu(i, dataItem3, z);
            } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                this.ItemName = "HIFAC_SETREGION_SA";
                int i2 = this.mConfigManager.getDefault("HIFAC_SETREGION_SA");
                Log.i("Fac_MenuDataContainer", "currentDefaultValueForSA = " + i2);
                i = (i2 == 5 || i2 == 6) ? i2 - 5 : 0;
                Log.i("Fac_MenuDataContainer", "saUIDefaultReiong = " + i);
                SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_region), 10004, 10004, i, this.mContext.getResources().getStringArray(R.array.factorymenu_region_for_sa), 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
                mHisFacDataGroup_Function.add(dataItem4);
                dataItem4.setUserDefined(i);
                dataItem4.mEffectGroup = new ArrayList();
                setSARegionMenu(this.mConfigManager.getRegion(), dataItem4);
            } else {
                this.ItemName = "HIFAC_SETCOUNTRY";
                mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_country), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_country), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
                this.ItemName = "HIFAC_SETLANGUAGE";
                mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_language), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            }
        }
        if (FacPropertyDemo.HIFAC_MARKET_HK) {
            String[] stringArray3 = this.mContext.getResources().getStringArray(R.array.facmenu_array_logo_for_HK);
            this.ItemName = "HIFAC_SETLOGO_FOR_HK";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SETLOGO_FOR_HK", this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray3, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_MONITOR) {
            String[] stringArray4 = this.mContext.getResources().getStringArray(R.array.factorymenu_global_logo);
            this.ItemName = "FACTORY_FUN_SETLOGO_FOR_MONITOR";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_FUN_SETLOGO_FOR_MONITOR", this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray4, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_TW) {
            String[] stringArray5 = this.mContext.getResources().getStringArray(R.array.facmenu_array_for_isolate_brand_benq);
            this.ItemName = "HIFAC_SETLOGO_FOR_TWN";
            String brand = this.mConfigManager.getBrand();
            if (brand != null && brand.equalsIgnoreCase("tos")) {
                stringArray5 = this.mContext.getResources().getStringArray(R.array.facmenu_array_for_isolate_brand_tos);
            }
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray5, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_KO) {
            String[] stringArray6 = this.mContext.getResources().getStringArray(R.array.facmenu_array_for_kor);
            this.ItemName = "HIFAC_SETLOGO_FOR_KOR";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SETLOGO_FOR_KOR", this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray6, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.ItemName = "HIFAC_SETLOGO_FOR_JPN";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_jp_logo), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
                this.ItemName = "HIFAC_SETLOGO_FOR_EM_SA";
                strArrGeBrandIsolateList = this.mContext.getResources().getStringArray(R.array.factorymenu_em_sa_logo);
            } else if (!this.mConfigManager.isBrandIsolate()) {
                this.ItemName = "HIFAC_SETLOGO_FOR_EM_NON_BRAND_ISOLATION";
                strArrGeBrandIsolateList = this.mContext.getResources().getStringArray(R.array.factorymenu_logo_for_em_non_brand_isolation);
            } else {
                this.ItemName = "HIFAC_SETLOGO_FOR_EM_BRAND_ISOLATION";
                strArrGeBrandIsolateList = this.mConfigManager.geBrandIsolateList();
                if (strArrGeBrandIsolateList == null) {
                    Log.i("Fac_MenuDataContainer", "logoStrForEM is null");
                }
            }
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), strArrGeBrandIsolateList, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            String[] stringArray7 = this.mContext.getResources().getStringArray(FacParamManager.SALOGO[this.mMenuLogoHelper.currentLogoTypeInSAMarket()]);
            this.ItemName = "HIFAC_SETLOGO_FOR_SA";
            if (stringArray7 == null) {
                Log.i("Fac_MenuDataContainer", "logoStrForSA is null");
            }
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray7, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        } else {
            this.ItemName = "HIFAC_SETLOGO";
            if (FacPropertyDemo.HIFAC_PRODUCT_OEM) {
                stringArray2 = this.mContext.getResources().getStringArray(R.array.factorymenu_us_oem_logo);
            } else if (FacPropertyDemo.HIFAC_SHARP_LOGO_SUPPORT) {
                stringArray2 = this.mContext.getResources().getStringArray(R.array.factorymenu_multiple_logo);
            } else {
                stringArray2 = this.mContext.getResources().getStringArray(R.array.factorymenu_logo);
            }
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_logo), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray2, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        this.ItemName = "HIFAC_TESTPATTERN";
        SetConfigListViewAdapter.DataItem dataItem5 = new SetConfigListViewAdapter.DataItem("HIFAC_TESTPATTERN", this.mContext.getString(R.string.str_factorymenu_test_pattern), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Function.add(dataItem5);
        dataItem5.mSubChildGroup = new ArrayList();
        this.ItemName = "HIFAC_INSIDEPATTERN";
        SetConfigListViewAdapter.DataItem dataItem6 = new SetConfigListViewAdapter.DataItem("HIFAC_INSIDEPATTERN", this.mContext.getString(R.string.str_factorymenu_inside_pattern), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Function.add(dataItem6);
        dataItem6.mSubChildGroup = new ArrayList();
        if (FacPropertyDemo.HIFAC_UART_DEBUG_SUPPORT) {
            this.ItemName = "HIFAC_UART_DEBUG";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_UART_DEBUG", this.mContext.getString(R.string.str_uart_debug), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
            this.ItemName = "HIFAC_SETCHINADRM";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SETCHINADRM", this.mContext.getString(R.string.str_factorymenu_setchinadrm), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
            dataItem2.mSubChildGroup = new ArrayList();
        }
        if (FacPropertyDemo.HIFAC_HDCP_KEY_SUPPORT) {
            this.ItemName = "HIFAC_SETHDCP";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SETHDCP", this.mContext.getString(R.string.str_factorymenu_sethdcp), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
            dataItem2.mSubChildGroup = new ArrayList();
        }
        if (FacPropertyDemo.HIFAC_PLAY_READY_KEY_SUPPORT) {
            this.ItemName = "HIFAC_SET_PLAY_READY";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SET_PLAY_READY", this.mContext.getString(R.string.str_factorymenu_setplayready), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if (FacPropertyDemo.HIFAC_WIDE_VINE_KEY_SUPPORT) {
            this.ItemName = "HIFAC_SET_WIDEVINE";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SET_WIDEVINE", this.mContext.getString(R.string.str_factorymenu_setwidevine), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if (FacPropertyDemo.HIFAC_HDCP_1_4_KEY_SUPPORT) {
            this.ItemName = "HIFAC_SET_HDCP_1_4";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SET_HDCP_1_4", this.mContext.getString(R.string.str_factorymenu_sethdcp14), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if (FacPropertyDemo.HIFAC_HDCP_2_X_KEY_SUPPORT) {
            this.ItemName = "HIFAC_SET_HDCP_2_X";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SET_HDCP_2_X", this.mContext.getString(R.string.str_factorymenu_sethdcp2x), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if (FacPropertyDemo.HIFAC_MGK_KEY_SUPPORT) {
            this.ItemName = "HIFAC_SET_NETFLIX_KEY";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SET_NETFLIX_KEY", this.mContext.getString(R.string.str_factorymenu_setmgk), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        this.ItemName = "HIFAC_SET_ATTEST_KEY";
        mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_SET_ATTEST_KEY", this.mContext.getString(R.string.str_factorymenu_setattest), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        if (this.mConfigManager.isSupportQDAKey()) {
            this.ItemName = "FACTORY_FUN_SETQDA";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_FUN_SETQDA", this.mContext.getString(R.string.str_factorymenu_setqda), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.ItemName = "FACTORY_FUN_SETDTCPIP";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_FUN_SETDTCPIP", this.mContext.getString(R.string.str_factorymenu_setdtcpip), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
            this.ItemName = "FACTORY_FUN_SETMARLIN";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_FUN_SETMARLIN", this.mContext.getString(R.string.str_factorymenu_setmarlin), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
            this.ItemName = "FACTORY_FUN_SETPVR";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_FUN_SETPVR", this.mContext.getString(R.string.str_factorymenu_setpvr), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        this.ItemName = "HISFAC_RUNNING_TIME";
        mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HISFAC_RUNNING_TIME", this.mContext.getString(R.string.str_tv_running_time), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getTVRunningTime()));
        this.ItemName = "HIFAC_RESET_GAMMA";
        SetConfigListViewAdapter.DataItem dataItem7 = new SetConfigListViewAdapter.DataItem("HIFAC_RESET_GAMMA", this.mContext.getString(R.string.str_factorymenu_reset_gamma), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Function.add(dataItem7);
        dataItem7.mSubChildGroup = new ArrayList();
        this.ItemName = "FACTORY_AUTO_GAMMA_STATUS";
        mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_AUTO_GAMMA_STATUS", this.mContext.getString(R.string.str_factorymenu_auto_gamma_status), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getAutoGammaStatus()));
        if (FacPropertyDemo.HIFAC_DEMURA_SWITCH_SUPPORT) {
            this.ItemName = "HIFAC_DEMURA_SWITCH";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_demura_switch), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_demura_switch), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
            this.ItemName = "HIFAC_DEMURA_PATTERN";
            SetConfigListViewAdapter.DataItem dataItem8 = new SetConfigListViewAdapter.DataItem("HIFAC_DEMURA_PATTERN", this.mContext.getString(R.string.str_demura_pattern), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
            mHisFacDataGroup_Function.add(dataItem8);
            dataItem8.mSubChildGroup = new ArrayList();
        }
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            this.ItemName = "FACTORY_OVER_CURRENT";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_OVER_CURRENT", this.mContext.getString(R.string.str_factorymenu_reset_usb_over_current), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
            dataItem7.mSubChildGroup = new ArrayList();
            this.ItemName = "HIFAC_TVS_LNB";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_lnb), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_array_for_tvs_lnb), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS || FacPropertyDemo.HIFAC_IS_OLED) {
            this.ItemName = "HIFAC_FUN_MAJOR_MAINTENANCE";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_FUN_MAJOR_MAINTENANCE", this.mContext.getString(R.string.str_factorymenu_major_maintenance), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
            this.ItemName = "HIFAC_FUN_MINOR_MAINTENANCE";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("HIFAC_FUN_MINOR_MAINTENANCE", this.mContext.getString(R.string.str_factorymenu_minor_maintenance), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if (this.mConfigManager.getLogoName().equalsIgnoreCase("Sunbrite") || this.mConfigManager.getLogoName().equalsIgnoreCase("snb")) {
            this.ItemName = "FACTORY_FUN_SET_SERVICE_TAG";
            mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem("FACTORY_FUN_SET_SERVICE_TAG", this.mContext.getString(R.string.str_factorymenu_set_service_tag), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
    }

    public void loadHisFacData_DetectMic() {
        mHisFacDataGroup_DetectMic = new ArrayList();
        this.ItemName = "HIFAC_MIC_YIZHIXING_DB";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_tv_mic_db_select), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_mic_db), 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisFacDataGroup_DetectMic.add(dataItem);
        dataItem.setUserDefined(0);
        dataItem.mEffectGroup = new ArrayList();
        this.ItemName = "HIFAC_MIC_RECORD_TIME";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_tv_mic_record_time), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_mic_record_time), 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisFacDataGroup_DetectMic.add(dataItem2);
        dataItem2.setUserDefined(0);
        dataItem2.mEffectGroup = new ArrayList();
        this.ItemName = "FACTORY_MIC_ONE_STAUTS";
        mHisFacDataGroup_DetectMic.add(new SetConfigListViewAdapter.DataItem("FACTORY_MIC_ONE_STAUTS", this.mContext.getString(R.string.str_tv_mic_0_status), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getMicDb(0)));
        this.ItemName = "FACTORY_MIC_TWO_STATUS";
        mHisFacDataGroup_DetectMic.add(new SetConfigListViewAdapter.DataItem("FACTORY_MIC_TWO_STATUS", this.mContext.getString(R.string.str_tv_mic_1_status), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getMicDb(1)));
        this.ItemName = "FACTORY_MIC_SWITCH_STATUS";
        mHisFacDataGroup_DetectMic.add(new SetConfigListViewAdapter.DataItem("FACTORY_MIC_SWITCH_STATUS", this.mContext.getString(R.string.str_tv_mic_switch_status), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getMicSwitchStatus()));
    }

    public void loadHisFacData_WhiteBalance() {
        mHisFacDataGroup_WhiteBalance = new ArrayList();
        this.ItemName = "HIFAC_NO_PANEL";
        mHisFacDataGroup_WhiteBalance.add(new SetConfigListViewAdapter.DataItem("HIFAC_NO_PANEL", this.mContext.getString(R.string.str_factorymenu_modulepanel), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mContext.getString(R.string.str_factorymenu_default_panel)));
        this.ItemName = "HIFAC_RDRV";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("HIFAC_RDRV", this.mContext.getString(R.string.str_factorymenu_rdrv), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem);
        this.ItemName = "HIFAC_GDRV";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_GDRV", this.mContext.getString(R.string.str_factorymenu_gdrv), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem2);
        this.ItemName = "HIFAC_BDRV";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("HIFAC_BDRV", this.mContext.getString(R.string.str_factorymenu_bdrv), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem3);
        this.ItemName = "HIFAC_RCUT";
        SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("HIFAC_RCUT", this.mContext.getString(R.string.str_factorymenu_rcut), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem4);
        this.ItemName = "HIFAC_GCUT";
        SetConfigListViewAdapter.DataItem dataItem5 = new SetConfigListViewAdapter.DataItem("HIFAC_GCUT", this.mContext.getString(R.string.str_factorymenu_gcut), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem5);
        this.ItemName = "HIFAC_BCUT";
        SetConfigListViewAdapter.DataItem dataItem6 = new SetConfigListViewAdapter.DataItem("HIFAC_BCUT", this.mContext.getString(R.string.str_factorymenu_bcut), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem6);
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.facmenu_white_balance_option);
        this.ItemName = "HIFAC_COLOR_TEMP";
        SetConfigListViewAdapter.DataItem dataItem7 = new SetConfigListViewAdapter.DataItem("HIFAC_COLOR_TEMP", this.mContext.getString(R.string.str_factorymenu_colortemp), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
        mHisFacDataGroup_WhiteBalance.add(dataItem7);
        this.ItemName = "HIFAC_RGAIN_DELTA";
        SetConfigListViewAdapter.DataItem dataItem8 = new SetConfigListViewAdapter.DataItem("HIFAC_RGAIN_DELTA", this.mContext.getString(R.string.str_factorymenu_rdrv_delta), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem8);
        this.ItemName = "HIFAC_GGAIN_DELTA";
        SetConfigListViewAdapter.DataItem dataItem9 = new SetConfigListViewAdapter.DataItem("HIFAC_GGAIN_DELTA", this.mContext.getString(R.string.str_factorymenu_gdrv_delta), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem9);
        this.ItemName = "HIFAC_BGAIN_DELTA";
        SetConfigListViewAdapter.DataItem dataItem10 = new SetConfigListViewAdapter.DataItem("HIFAC_BGAIN_DELTA", this.mContext.getString(R.string.str_factorymenu_bdrv_delta), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem10);
        this.ItemName = "HIFAC_ROFFSET_DELTA";
        SetConfigListViewAdapter.DataItem dataItem11 = new SetConfigListViewAdapter.DataItem("HIFAC_ROFFSET_DELTA", this.mContext.getString(R.string.str_factorymenu_rcut_delta), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem11);
        this.ItemName = "HIFAC_GOFFSET_DELTA";
        SetConfigListViewAdapter.DataItem dataItem12 = new SetConfigListViewAdapter.DataItem("HIFAC_GOFFSET_DELTA", this.mContext.getString(R.string.str_factorymenu_gcut_delta), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem12);
        this.ItemName = "HIFAC_BOFFSET_DELTA";
        SetConfigListViewAdapter.DataItem dataItem13 = new SetConfigListViewAdapter.DataItem("HIFAC_BOFFSET_DELTA", this.mContext.getString(R.string.str_factorymenu_bcut_delta), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
        mHisFacDataGroup_WhiteBalance.add(dataItem13);
        dataItem7.setUserDefined(0);
        ArrayList arrayList = new ArrayList();
        dataItem7.mEffectGroup = arrayList;
        arrayList.add(dataItem);
        dataItem7.mEffectGroup.add(dataItem2);
        dataItem7.mEffectGroup.add(dataItem3);
        dataItem7.mEffectGroup.add(dataItem4);
        dataItem7.mEffectGroup.add(dataItem5);
        dataItem7.mEffectGroup.add(dataItem6);
        dataItem7.mEffectGroup.add(dataItem8);
        dataItem7.mEffectGroup.add(dataItem9);
        dataItem7.mEffectGroup.add(dataItem10);
        dataItem7.mEffectGroup.add(dataItem11);
        dataItem7.mEffectGroup.add(dataItem12);
        dataItem7.mEffectGroup.add(dataItem13);
        dataItem.mParent = dataItem7;
        dataItem2.mParent = dataItem7;
        dataItem3.mParent = dataItem7;
        dataItem4.mParent = dataItem7;
        dataItem5.mParent = dataItem7;
        dataItem6.mParent = dataItem7;
        dataItem8.mParent = dataItem7;
        dataItem9.mParent = dataItem7;
        dataItem10.mParent = dataItem7;
        dataItem11.mParent = dataItem7;
        dataItem12.mParent = dataItem7;
        dataItem13.mParent = dataItem7;
    }

    public void loadHisFactoryData() {
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            mHisFactoryDataGroup = this.mContext.getResources().getStringArray(R.array.facmenu_mainitem_whit_mic);
        } else if (FacPropertyDemo.HIFAC_IS_LASER4K) {
            mHisFactoryDataGroup = this.mContext.getResources().getStringArray(R.array.facmenu_mainitem_laser_whit_mic);
        } else {
            mHisFactoryDataGroup = this.mContext.getResources().getStringArray(R.array.facmenu_mainitem);
        }
    }

    public void loadNetworkSetting() {
        mHisFacDataGroup_NetworkSetting = new ArrayList();
        this.ItemName = "HIFAC_F7_NETWORK";
        mHisFacDataGroup_NetworkSetting.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.Network_TYPE), 10004, 10004, this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_networkinterface), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    public List<SetConfigListViewAdapter.DataItem> getFacDataGroup_BluetoothSetting() {
        return mHisFacDataGroup_BlueToothSetting;
    }

    public void loadBlueToothSetting() {
        mHisFacDataGroup_BlueToothSetting = new ArrayList();
        this.ItemName = "FACTORY_F7_BLUETOOTH";
        mHisFacDataGroup_BlueToothSetting.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.BlueTooth_TYPE), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_bluetoothinterface), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    public void refreshAudioCurveData(String str) {
        int i;
        if (str.equals("HIFAC_PEQ_1_STEP")) {
            i = 0;
        } else if (str.equals("HIFAC_PEQ_2_STEP")) {
            i = 4;
        } else if (str.equals("HIFAC_PEQ_3_STEP")) {
            i = 8;
        } else if (str.equals("HIFAC_PEQ_4_STEP")) {
            i = 12;
        } else if (str.equals("HIFAC_PEQ_5_STEP")) {
            i = 16;
        } else if (str.equals("HIFAC_PEQ_6_STEP")) {
            i = 20;
        } else if (str.equals("HIFAC_PEQ_7_STEP")) {
            i = 24;
        } else {
            if (!str.equals("HIFAC_PEQ_8_STEP")) {
                Log.i("Fac_MenuDataContainer", "refreshAudioCurveData not audio curve name = " + str);
                return;
            }
            i = 28;
        }
        Log.i("Fac_MenuDataContainer", "refreshAudioCurveData index = " + i);
        if (i < mHisDesDataGroup_Peq.size()) {
            mHisDesDataGroup_Peq.get(i).mStepValue = this.mConfigManager.getStep(str);
        }
    }

    private void setRegionMenu(int i, SetConfigListViewAdapter.DataItem dataItem, boolean z) {
        Log.i("Fac_MenuDataContainer", "setRegionMenu = " + i);
        Log.i("Fac_MenuDataContainer", "setRegionMenu isRegionChanged = " + z);
        int index = 0;
        if (i >= (FacPropertyDemo.HIFAC_REGION5_SUPPORT ? 5 : 4) || i < 0) {
            i = 0;
        }
        if (z) {
            refreshCountryAndLanguageForRegion(i);
        }
        this.ItemName = mRegionCountryPropertyArray[i];
        String[] stringArray = this.mContext.getResources().getStringArray(FacParamManager.REGION_ARRAY[i]);
        if (FacPropertyDemo.HIFAC_REGION5_SUPPORT) {
            stringArray = this.mContext.getResources().getStringArray(FacParamManager.REGION_ARRAY_WITH_SA[i]);
        }
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_country), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW);
        mHisFacDataGroup_Function.add(dataItem2);
        dataItem2.mParent = dataItem;
        dataItem.mEffectGroup.add(dataItem2);
        if (this.mConfigManager.getDefault(this.ItemName) == 0 && stringArray != null && stringArray.length > 0) {
            if (i == 0) {
                index = FacPropertyDemo.CountryForRegion0.UZB.getIndex();
            } else if (i == 1) {
                index = FacPropertyDemo.CountryForRegion1.SAU.getIndex();
            } else if (i == 2) {
                index = FacPropertyDemo.CountryForRegion2.ZAF.getIndex();
            } else if (i == 3) {
                index = FacPropertyDemo.CountryForRegion3.AUS.getIndex();
            } else if (i == 4) {
                index = FacPropertyDemo.CountryForRegion4.PAN.getIndex();
            }
            this.mConfigManager.setCountryIndex(index);
        }
        this.ItemName = mRegionLanguagePropertyArray[i];
        mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(mRegionLanguageArray[i]), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    private void refreshCountryAndLanguageForRegion(int i) {
        int i2;
        int index;
        int index2;
        int i3 = 0;
        if (i == 0) {
            index = FacPropertyDemo.CountryForRegion0.UZB.getIndex();
            index2 = FacPropertyDemo.LanguageForRegion0.hbr.getIndex();
        } else if (i == 1) {
            index = FacPropertyDemo.CountryForRegion1.SAU.getIndex();
            index2 = FacPropertyDemo.LanguageForRegion1.eng.getIndex();
        } else if (i == 2) {
            index = FacPropertyDemo.CountryForRegion2.ZAF.getIndex();
            index2 = FacPropertyDemo.LanguageForRegion2.eng.getIndex();
        } else if (i == 3) {
            index = FacPropertyDemo.CountryForRegion3.AUS.getIndex();
            index2 = FacPropertyDemo.LanguageForRegion3.eng.getIndex();
        } else {
            if (i == 4) {
                index = FacPropertyDemo.CountryForRegion4.PAN.getIndex();
                index2 = FacPropertyDemo.LanguageForRegion4.eng.getIndex();
            } else {
                i2 = 0;
            }
            this.mConfigManager.setLangIndex(i3);
            this.mConfigManager.setCountryIndex(i2);
        }
        int i4 = index;
        i3 = index2;
        i2 = i4;
        this.mConfigManager.setLangIndex(i3);
        this.mConfigManager.setCountryIndex(i2);
    }

    public void refreshMicData() {
        mHisFacDataGroup_DetectMic.get(6).mShowValue = this.mConfigManager.getMicDb(0);
        mHisFacDataGroup_DetectMic.get(7).mShowValue = this.mConfigManager.getMicDb(1);
        mHisFacDataGroup_DetectMic.get(8).mShowValue = this.mConfigManager.getMicSwitchStatus();
    }

    private void setSARegionMenu(int i, SetConfigListViewAdapter.DataItem dataItem) {
        Log.i("Fac_MenuDataContainer", "setSARegionMenu = " + i);
        int index = 0;
        int i2 = (i == 5 || i == 6) ? i - 5 : 0;
        Log.i("Fac_MenuDataContainer", "setSARegionMenu saUiIndex = " + i2);
        this.ItemName = mSARegionCountryPropertyArray[i2];
        String[] stringArray = this.mContext.getResources().getStringArray(mSARegionCountryArray[i2]);
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_country), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW);
        mHisFacDataGroup_Function.add(dataItem2);
        dataItem2.mParent = dataItem;
        dataItem.mEffectGroup.add(dataItem2);
        if (this.mConfigManager.getDefault(this.ItemName) == 0 && stringArray != null && stringArray.length > 0) {
            if (i2 == 0) {
                index = FacPropertyDemo.CountryForRegion5.BRA.getIndex();
            } else if (i2 == 1) {
                index = FacPropertyDemo.CountryForRegion6.VEN.getIndex();
            }
            this.mConfigManager.setCountryIndex(index);
        }
        this.ItemName = mSARegionLanguagePropertyArray[i2];
        mHisFacDataGroup_Function.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_language), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(mSARegionLanguageArray[i2]), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
    }

    public void loadHisFacData_Laser() {
        Log.i("Fac_MenuDataContainer", "loadHisFacData_Laser");
        mHisFacDataGroup_Laser = new ArrayList();
        if (FacPropertyDemo.HIFAC_LASER_IS_L9) {
            String[] stringArray = this.mContext.getResources().getStringArray(R.array.factorymenu_mode_selection);
            this.ItemName = "HIFAC_MODE_SELECTION";
            SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("HIFAC_MODE_SELECTION", this.mContext.getString(R.string.str_factorymenu_mode_selection), 10004, 10004, this.mConfigManager.getDefault(this.ItemName) - this.mConfigManager.getMin(this.ItemName), stringArray, 1, SetConfigListViewAdapter.DataItem.DataType.EFFECTOPTIONVIEW);
            mHisFacDataGroup_Laser.add(dataItem);
            this.ItemName = "HIFAC_MODE_SELECTION_VALUE";
            SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("HIFAC_MODE_SELECTION_VALUE", this.mContext.getString(R.string.str_factorymenu_mode_selection_value), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR);
            mHisFacDataGroup_Laser.add(dataItem2);
            dataItem.setUserDefined(0);
            ArrayList arrayList = new ArrayList();
            dataItem.mEffectGroup = arrayList;
            arrayList.add(dataItem2);
            dataItem2.mParent = dataItem;
        }
        this.ItemName = "FACTORY_EYE_PROTECT";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_eye_protect), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_eye_protect_mode), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "HIFAC_ALARMENABLE";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_toalarm), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_toalarm), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "FACTORY_TEMP_PROTECT";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_temp_protect), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorymenu_temp_protect), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "DISPLAYBOARD_SOFTWARE_UPGRADE_PREPARE";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_displayboard_upgrade), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_networkconnection), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "FACTORY_ZHENJING";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("FACTORY_ZHENJING", this.mContext.getString(R.string.str_factorymenu_zhenjing), 10004, 10004, String.valueOf(MenuConfigManager.getMirrorValue()), (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Laser.add(dataItem3);
        dataItem3.mSubChildGroup = new ArrayList();
        this.ItemName = "FACTORY_SCREEN_SELECT";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_screen_select), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.facmenu_screen), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        this.ItemName = "FACTORY_FUN_CLEAR_OM_RUNNING_TIME";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("FACTORY_FUN_CLEAR_OM_RUNNING_TIME", this.mContext.getString(R.string.str_factorymenu_clear_omrunningtime), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        this.ItemName = "HIFAC_SET_AGC";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("HIFAC_SET_AGC", this.mContext.getString(R.string.str_factorymenu_set_agc), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        if (FacPropertyDemo.HIFAC_LASER_IS_L9 && !FacPropertyDemo.HIFAC_LASER_PROJECTOR) {
            this.ItemName = "HIFAC_CALIBRATION_OF_BACKGROUND_NOISE";
            mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("HIFAC_CALIBRATION_OF_BACKGROUND_NOISE", this.mContext.getString(R.string.str_factorymenu_calibration_of_background_noise), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        this.ItemName = "HIFAC_FUN_FANS_SPEED";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("HIFAC_FUN_FANS_SPEED", this.mContext.getString(R.string.str_factorymenu_fans_speed), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        this.ItemName = "FACTORY_READ_LASER_RUNNING_TIME";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("FACTORY_READ_LASER_RUNNING_TIME", this.mContext.getString(R.string.str_factory_read_laser_running_time), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getLaserRuningTime()));
        if (FacPropertyDemo.HIFAC_LASER_IS_L9 && !FacPropertyDemo.HIFAC_LASER_PROJECTOR) {
            this.ItemName = "FACTORY_FOREIGN_OBJECT_DETECT_SWITCH";
            mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem(this.ItemName, this.mContext.getString(R.string.str_factorymenu_foreign_object_detect), 10004, 10004, this.mConfigManager.getDefault(this.ItemName), this.mContext.getResources().getStringArray(R.array.factorylaser_foreign_object_detect_switch), 1, SetConfigListViewAdapter.DataItem.DataType.OPTIONVIEW));
        }
        if (!FacPropertyDemo.HIFAC_LASER_IS_L9) {
            this.ItemName = "FACTORY_COLOR_WHEEL_ADJUST";
            mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("FACTORY_COLOR_WHEEL_ADJUST", this.mContext.getString(R.string.str_factorymenu_color_wheel_adjust), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD));
        }
        if ("leica".equalsIgnoreCase(SystemProperties.get("ro.product.brand", ""))) {
            this.ItemName = "FACTORY_SLIDING_COVER_GAP_ADJUST";
            mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("FACTORY_SLIDING_COVER_GAP_ADJUST", this.mContext.getString(R.string.str_factorymenu_movable_cover_step), this.mConfigManager.getMin(this.ItemName), this.mConfigManager.getMax(this.ItemName), this.mConfigManager.getDefault(this.ItemName), (String[]) null, this.mConfigManager.getStep(this.ItemName), SetConfigListViewAdapter.DataItem.DataType.PROGRESSBAR));
        }
        this.ItemName = "FACTORY_FIRST_POWER_ON_TIME";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("FACTORY_FIRST_POWER_ON_TIME", this.mContext.getString(R.string.str_factorymenu_first_power_on_time), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getFirstPowerOnTime()));
        this.ItemName = "FACTORY_START_ON_COUNT";
        mHisFacDataGroup_Laser.add(new SetConfigListViewAdapter.DataItem("FACTORY_START_ON_COUNT", this.mContext.getString(R.string.str_factorymenu_start_on_count), 10004, 10004, 10004, null, 1, SetConfigListViewAdapter.DataItem.DataType.TEXTCOMMVIEW, this.mConfigManager.getStartOnCount()));
    }

    public void loadHisFacData_ChannelSetForHK() {
        mHisFacDataGroup_Init = new ArrayList();
        this.ItemName = "huangdao_hK";
        SetConfigListViewAdapter.DataItem dataItem = new SetConfigListViewAdapter.DataItem("huangdao_hK", this.mContext.getString(R.string.str_factorymenu_huangdao), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Init.add(dataItem);
        dataItem.mSubChildGroup = new ArrayList();
        this.ItemName = "qingdao_hk";
        SetConfigListViewAdapter.DataItem dataItem2 = new SetConfigListViewAdapter.DataItem("qingdao_hk", this.mContext.getString(R.string.str_factorymenu_qingdao), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Init.add(dataItem2);
        dataItem2.mSubChildGroup = new ArrayList();
        this.ItemName = "guiyang_hk";
        SetConfigListViewAdapter.DataItem dataItem3 = new SetConfigListViewAdapter.DataItem("guiyang_hk", this.mContext.getString(R.string.str_factorymenu_guiyang), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Init.add(dataItem3);
        dataItem3.mSubChildGroup = new ArrayList();
        this.ItemName = "jiangmen_hk";
        SetConfigListViewAdapter.DataItem dataItem4 = new SetConfigListViewAdapter.DataItem("jiangmen_hk", this.mContext.getString(R.string.str_factorymenu_jiangmen), 10004, 10004, 10004, (String[]) null, 1, SetConfigListViewAdapter.DataItem.DataType.HAVESUBCHILD);
        mHisFacDataGroup_Init.add(dataItem4);
        dataItem4.mSubChildGroup = new ArrayList();
    }
}
