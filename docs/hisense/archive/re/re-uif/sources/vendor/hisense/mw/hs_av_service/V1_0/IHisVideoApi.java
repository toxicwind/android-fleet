package vendor.hisense.mw.hs_av_service.V1_0;

import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.RemoteException;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IHisVideoApi extends IHwInterface {

    @FunctionalInterface
    public interface get2PWBGainBlueDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBGainGreenDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBGainRedDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBOffsetBlueDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBOffsetGreenDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface get2PWBOffsetRedDeltaCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getAspectRatioCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklight20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklight80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightPWMFreqCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBacklightPercentByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightness20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightness80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getBrightnessMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrast20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrast80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getContrastMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyIQEnableFlagCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getDolbyIQUserBinEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFRCVersionCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTemperatureCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerBrightnessCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerColorCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerHueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryColorTunerSaturationCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceLevelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryMultiPointWhiteBalanceRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceGainBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceGainGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceGainRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceOffsetBlueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceOffsetGreenCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getFactoryWhiteBalanceOffsetRedCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getGammaEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHue20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHue80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHueMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHueMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getHueMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getItemDataCallback {
        void onValues(int i, ArrayList<Integer> arrayList);
    }

    @FunctionalInterface
    public interface getItemStringCallback {
        void onValues(int i, String str);
    }

    @FunctionalInterface
    public interface getItemValueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorCurveCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorOptionStateCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLightSensorValueCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getLocalDimmingCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDHDREnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDLEAEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDLocalFrozenChkEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOLEDTPCEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanBottomCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanLeftCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanRightCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getOverscanTopCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPanelCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPictureModeByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPixelShiftEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getPqEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturation20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturation80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSaturationMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSceEnableCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpness20Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpness80Callback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessByFactoryCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessMaxCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessMidCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSharpnessMinCallback {
        void onValues(int i, int i2);
    }

    @FunctionalInterface
    public interface getSignalVideoFormatCallback {
        void onValues(int i, int i2, int i3, int i4, int i5);
    }

    @FunctionalInterface
    public interface getXvyccEnableCallback {
        void onValues(int i, int i2);
    }

    int SetAutoGammaEnable(int i) throws RemoteException;

    void get2PWBGainBlueDelta(get2PWBGainBlueDeltaCallback get2pwbgainbluedeltacallback) throws RemoteException;

    void get2PWBGainGreenDelta(get2PWBGainGreenDeltaCallback get2pwbgaingreendeltacallback) throws RemoteException;

    void get2PWBGainRedDelta(get2PWBGainRedDeltaCallback get2pwbgainreddeltacallback) throws RemoteException;

    void get2PWBOffsetBlueDelta(get2PWBOffsetBlueDeltaCallback get2pwboffsetbluedeltacallback) throws RemoteException;

    void get2PWBOffsetGreenDelta(get2PWBOffsetGreenDeltaCallback get2pwboffsetgreendeltacallback) throws RemoteException;

    void get2PWBOffsetRedDelta(get2PWBOffsetRedDeltaCallback get2pwboffsetreddeltacallback) throws RemoteException;

    void getAspectRatio(getAspectRatioCallback getaspectratiocallback) throws RemoteException;

    void getBacklight20(getBacklight20Callback getbacklight20callback) throws RemoteException;

    void getBacklight80(getBacklight80Callback getbacklight80callback) throws RemoteException;

    void getBacklightMax(getBacklightMaxCallback getbacklightmaxcallback) throws RemoteException;

    void getBacklightMid(getBacklightMidCallback getbacklightmidcallback) throws RemoteException;

    void getBacklightMin(getBacklightMinCallback getbacklightmincallback) throws RemoteException;

    void getBacklightPWMFreq(getBacklightPWMFreqCallback getbacklightpwmfreqcallback) throws RemoteException;

    void getBacklightPercentByFactory(getBacklightPercentByFactoryCallback getbacklightpercentbyfactorycallback) throws RemoteException;

    void getBrightness20(getBrightness20Callback getbrightness20callback) throws RemoteException;

    void getBrightness80(getBrightness80Callback getbrightness80callback) throws RemoteException;

    void getBrightnessByFactory(getBrightnessByFactoryCallback getbrightnessbyfactorycallback) throws RemoteException;

    void getBrightnessMax(getBrightnessMaxCallback getbrightnessmaxcallback) throws RemoteException;

    void getBrightnessMid(getBrightnessMidCallback getbrightnessmidcallback) throws RemoteException;

    void getBrightnessMin(getBrightnessMinCallback getbrightnessmincallback) throws RemoteException;

    void getContrast20(getContrast20Callback getcontrast20callback) throws RemoteException;

    void getContrast80(getContrast80Callback getcontrast80callback) throws RemoteException;

    void getContrastByFactory(getContrastByFactoryCallback getcontrastbyfactorycallback) throws RemoteException;

    void getContrastMax(getContrastMaxCallback getcontrastmaxcallback) throws RemoteException;

    void getContrastMid(getContrastMidCallback getcontrastmidcallback) throws RemoteException;

    void getContrastMin(getContrastMinCallback getcontrastmincallback) throws RemoteException;

    void getDolbyIQEnableFlag(getDolbyIQEnableFlagCallback getdolbyiqenableflagcallback) throws RemoteException;

    void getDolbyIQUserBinEnable(getDolbyIQUserBinEnableCallback getdolbyiquserbinenablecallback) throws RemoteException;

    void getFRCVersion(getFRCVersionCallback getfrcversioncallback) throws RemoteException;

    void getFactoryColorTemperature(getFactoryColorTemperatureCallback getfactorycolortemperaturecallback) throws RemoteException;

    void getFactoryColorTunerBrightness(getFactoryColorTunerBrightnessCallback getfactorycolortunerbrightnesscallback) throws RemoteException;

    void getFactoryColorTunerColor(getFactoryColorTunerColorCallback getfactorycolortunercolorcallback) throws RemoteException;

    void getFactoryColorTunerHue(getFactoryColorTunerHueCallback getfactorycolortunerhuecallback) throws RemoteException;

    void getFactoryColorTunerSaturation(getFactoryColorTunerSaturationCallback getfactorycolortunersaturationcallback) throws RemoteException;

    void getFactoryMultiPointWhiteBalanceBlue(getFactoryMultiPointWhiteBalanceBlueCallback getfactorymultipointwhitebalancebluecallback) throws RemoteException;

    void getFactoryMultiPointWhiteBalanceGreen(getFactoryMultiPointWhiteBalanceGreenCallback getfactorymultipointwhitebalancegreencallback) throws RemoteException;

    void getFactoryMultiPointWhiteBalanceLevel(getFactoryMultiPointWhiteBalanceLevelCallback getfactorymultipointwhitebalancelevelcallback) throws RemoteException;

    void getFactoryMultiPointWhiteBalanceRed(getFactoryMultiPointWhiteBalanceRedCallback getfactorymultipointwhitebalanceredcallback) throws RemoteException;

    void getFactoryWhiteBalanceGainBlue(getFactoryWhiteBalanceGainBlueCallback getfactorywhitebalancegainbluecallback) throws RemoteException;

    void getFactoryWhiteBalanceGainGreen(getFactoryWhiteBalanceGainGreenCallback getfactorywhitebalancegaingreencallback) throws RemoteException;

    void getFactoryWhiteBalanceGainRed(getFactoryWhiteBalanceGainRedCallback getfactorywhitebalancegainredcallback) throws RemoteException;

    void getFactoryWhiteBalanceOffsetBlue(getFactoryWhiteBalanceOffsetBlueCallback getfactorywhitebalanceoffsetbluecallback) throws RemoteException;

    void getFactoryWhiteBalanceOffsetGreen(getFactoryWhiteBalanceOffsetGreenCallback getfactorywhitebalanceoffsetgreencallback) throws RemoteException;

    void getFactoryWhiteBalanceOffsetRed(getFactoryWhiteBalanceOffsetRedCallback getfactorywhitebalanceoffsetredcallback) throws RemoteException;

    void getGammaEnable(getGammaEnableCallback getgammaenablecallback) throws RemoteException;

    void getHue20(getHue20Callback gethue20callback) throws RemoteException;

    void getHue80(getHue80Callback gethue80callback) throws RemoteException;

    void getHueMax(getHueMaxCallback gethuemaxcallback) throws RemoteException;

    void getHueMid(getHueMidCallback gethuemidcallback) throws RemoteException;

    void getHueMin(getHueMinCallback gethuemincallback) throws RemoteException;

    void getItemData(int i, getItemDataCallback getitemdatacallback) throws RemoteException;

    void getItemString(int i, getItemStringCallback getitemstringcallback) throws RemoteException;

    void getItemValue(int i, getItemValueCallback getitemvaluecallback) throws RemoteException;

    void getLightSensorCurve(int i, int i2, getLightSensorCurveCallback getlightsensorcurvecallback) throws RemoteException;

    void getLightSensorOptionState(getLightSensorOptionStateCallback getlightsensoroptionstatecallback) throws RemoteException;

    void getLightSensorValue(getLightSensorValueCallback getlightsensorvaluecallback) throws RemoteException;

    void getLocalDimming(getLocalDimmingCallback getlocaldimmingcallback) throws RemoteException;

    void getOLEDHDREnable(getOLEDHDREnableCallback getoledhdrenablecallback) throws RemoteException;

    void getOLEDLEAEnable(getOLEDLEAEnableCallback getoledleaenablecallback) throws RemoteException;

    void getOLEDLocalFrozenChkEnable(getOLEDLocalFrozenChkEnableCallback getoledlocalfrozenchkenablecallback) throws RemoteException;

    void getOLEDTPCEnable(getOLEDTPCEnableCallback getoledtpcenablecallback) throws RemoteException;

    void getOverscanBottom(getOverscanBottomCallback getoverscanbottomcallback) throws RemoteException;

    void getOverscanLeft(getOverscanLeftCallback getoverscanleftcallback) throws RemoteException;

    void getOverscanRight(getOverscanRightCallback getoverscanrightcallback) throws RemoteException;

    void getOverscanTop(getOverscanTopCallback getoverscantopcallback) throws RemoteException;

    void getPanel(getPanelCallback getpanelcallback) throws RemoteException;

    void getPictureModeByFactory(getPictureModeByFactoryCallback getpicturemodebyfactorycallback) throws RemoteException;

    void getPixelShiftEnable(getPixelShiftEnableCallback getpixelshiftenablecallback) throws RemoteException;

    void getPqEnable(getPqEnableCallback getpqenablecallback) throws RemoteException;

    void getSaturation20(getSaturation20Callback getsaturation20callback) throws RemoteException;

    void getSaturation80(getSaturation80Callback getsaturation80callback) throws RemoteException;

    void getSaturationByFactory(getSaturationByFactoryCallback getsaturationbyfactorycallback) throws RemoteException;

    void getSaturationMax(getSaturationMaxCallback getsaturationmaxcallback) throws RemoteException;

    void getSaturationMid(getSaturationMidCallback getsaturationmidcallback) throws RemoteException;

    void getSaturationMin(getSaturationMinCallback getsaturationmincallback) throws RemoteException;

    void getSceEnable(getSceEnableCallback getsceenablecallback) throws RemoteException;

    void getSharpness20(getSharpness20Callback getsharpness20callback) throws RemoteException;

    void getSharpness80(getSharpness80Callback getsharpness80callback) throws RemoteException;

    void getSharpnessByFactory(getSharpnessByFactoryCallback getsharpnessbyfactorycallback) throws RemoteException;

    void getSharpnessMax(getSharpnessMaxCallback getsharpnessmaxcallback) throws RemoteException;

    void getSharpnessMid(getSharpnessMidCallback getsharpnessmidcallback) throws RemoteException;

    void getSharpnessMin(getSharpnessMinCallback getsharpnessmincallback) throws RemoteException;

    void getSignalVideoFormat(getSignalVideoFormatCallback getsignalvideoformatcallback) throws RemoteException;

    void getXvyccEnable(getXvyccEnableCallback getxvyccenablecallback) throws RemoteException;

    ArrayList<String> interfaceChain() throws RemoteException;

    int resetAllFac2PWhiteBalance(int i) throws RemoteException;

    int set2PWBGainBlueDelta(int i) throws RemoteException;

    int set2PWBGainGreenDelta(int i) throws RemoteException;

    int set2PWBGainRedDelta(int i) throws RemoteException;

    int set2PWBOffsetBlueDelta(int i) throws RemoteException;

    int set2PWBOffsetGreenDelta(int i) throws RemoteException;

    int set2PWBOffsetRedDelta(int i) throws RemoteException;

    int setActiveContrast(int i) throws RemoteException;

    int setAgingModeEnable(int i) throws RemoteException;

    int setAspectRatio(int i) throws RemoteException;

    int setBacklight20(int i) throws RemoteException;

    int setBacklight80(int i) throws RemoteException;

    int setBacklightMax(int i) throws RemoteException;

    int setBacklightMid(int i) throws RemoteException;

    int setBacklightMin(int i) throws RemoteException;

    int setBacklightPWMFreq(int i) throws RemoteException;

    int setBacklightPercentByFactory(int i) throws RemoteException;

    int setBrightness20(int i) throws RemoteException;

    int setBrightness80(int i) throws RemoteException;

    int setBrightnessByFactory(int i) throws RemoteException;

    int setBrightnessMax(int i) throws RemoteException;

    int setBrightnessMid(int i) throws RemoteException;

    int setBrightnessMin(int i) throws RemoteException;

    int setContrast20(int i) throws RemoteException;

    int setContrast80(int i) throws RemoteException;

    int setContrastByFactory(int i) throws RemoteException;

    int setContrastMax(int i) throws RemoteException;

    int setContrastMid(int i) throws RemoteException;

    int setContrastMin(int i) throws RemoteException;

    int setDolbyIQUserBinEnable(int i) throws RemoteException;

    int setFactoryColorTemperature(int i) throws RemoteException;

    int setFactoryColorTunerBrightness(int i) throws RemoteException;

    int setFactoryColorTunerColor(int i) throws RemoteException;

    int setFactoryColorTunerHue(int i) throws RemoteException;

    int setFactoryColorTunerSaturation(int i) throws RemoteException;

    int setFactoryMultiPointWhiteBalanceBlue(int i) throws RemoteException;

    int setFactoryMultiPointWhiteBalanceGreen(int i) throws RemoteException;

    int setFactoryMultiPointWhiteBalanceLevel(int i) throws RemoteException;

    int setFactoryMultiPointWhiteBalanceRed(int i) throws RemoteException;

    int setFactoryWhiteBalanceGainBlue(int i) throws RemoteException;

    int setFactoryWhiteBalanceGainGreen(int i) throws RemoteException;

    int setFactoryWhiteBalanceGainRed(int i) throws RemoteException;

    int setFactoryWhiteBalanceOffsetBlue(int i) throws RemoteException;

    int setFactoryWhiteBalanceOffsetGreen(int i) throws RemoteException;

    int setFactoryWhiteBalanceOffsetRed(int i) throws RemoteException;

    int setGammaEnable(int i) throws RemoteException;

    int setHue20(int i) throws RemoteException;

    int setHue80(int i) throws RemoteException;

    int setHueMax(int i) throws RemoteException;

    int setHueMid(int i) throws RemoteException;

    int setHueMin(int i) throws RemoteException;

    int setInsidePattern(int i) throws RemoteException;

    int setItemString(int i, String str) throws RemoteException;

    int setItemValue(int i, int i2) throws RemoteException;

    int setLDTestEnable(int i) throws RemoteException;

    int setLightSensorCurve(int i, int i2, int i3) throws RemoteException;

    int setLocalDimming(int i) throws RemoteException;

    int setOLEDHDREnable(int i) throws RemoteException;

    int setOLEDLEAEnable(int i) throws RemoteException;

    int setOLEDLocalFrozenChkEnable(int i) throws RemoteException;

    int setOLEDTPCEnable(int i) throws RemoteException;

    int setOverscanBottom(int i) throws RemoteException;

    int setOverscanLeft(int i) throws RemoteException;

    int setOverscanRight(int i) throws RemoteException;

    int setOverscanTop(int i) throws RemoteException;

    int setPanel(int i) throws RemoteException;

    int setPictureModeByFactory(int i) throws RemoteException;

    int setPixelShiftEnable(int i) throws RemoteException;

    int setPqEnable(int i) throws RemoteException;

    int setRGBPattern(int i, int i2, int i3, int i4) throws RemoteException;

    int setSaturation20(int i) throws RemoteException;

    int setSaturation80(int i) throws RemoteException;

    int setSaturationByFactory(int i) throws RemoteException;

    int setSaturationMax(int i) throws RemoteException;

    int setSaturationMid(int i) throws RemoteException;

    int setSaturationMin(int i) throws RemoteException;

    int setSceEnable(int i) throws RemoteException;

    int setSharpness20(int i) throws RemoteException;

    int setSharpness80(int i) throws RemoteException;

    int setSharpnessByFactory(int i) throws RemoteException;

    int setSharpnessMax(int i) throws RemoteException;

    int setSharpnessMid(int i) throws RemoteException;

    int setSharpnessMin(int i) throws RemoteException;

    int setTestPattern(int i) throws RemoteException;

    int setVcomTestEnable(int i) throws RemoteException;

    int setXvyccEnable(int i) throws RemoteException;

    static IHisVideoApi asInterface(IHwBinder iHwBinder) {
        if (iHwBinder == null) {
            return null;
        }
        IHwInterface iHwInterfaceQueryLocalInterface = iHwBinder.queryLocalInterface("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
        if (iHwInterfaceQueryLocalInterface != null && (iHwInterfaceQueryLocalInterface instanceof IHisVideoApi)) {
            return (IHisVideoApi) iHwInterfaceQueryLocalInterface;
        }
        Proxy proxy = new Proxy(iHwBinder);
        try {
            Iterator<String> it = proxy.interfaceChain().iterator();
            while (it.hasNext()) {
                if (it.next().equals("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi")) {
                    return proxy;
                }
            }
        } catch (RemoteException unused) {
        }
        return null;
    }

    static IHisVideoApi getService(String str) throws RemoteException {
        return asInterface(HwBinder.getService("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi", str));
    }

    static IHisVideoApi getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IHisVideoApi {
        private IHwBinder mRemote;

        public Proxy(IHwBinder iHwBinder) {
            Objects.requireNonNull(iHwBinder);
            this.mRemote = iHwBinder;
        }

        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException unused) {
                return "[class or subclass of vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi]@Proxy";
            }
        }

        public final boolean equals(Object obj) {
            return HidlSupport.interfacesEqual(this, obj);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAspectRatio(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(2, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getAspectRatio(getAspectRatioCallback getaspectratiocallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(3, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getaspectratiocallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLocalDimming(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(6, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLocalDimming(getLocalDimmingCallback getlocaldimmingcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(7, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlocaldimmingcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDTPCEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(28, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDTPCEnable(getOLEDTPCEnableCallback getoledtpcenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(29, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledtpcenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setActiveContrast(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(51, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPictureModeByFactory(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_URI_FILE_OPENED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPictureModeByFactory(getPictureModeByFactoryCallback getpicturemodebyfactorycallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DESC_ALLOCED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpicturemodebyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightPercentByFactory(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_BLOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightPercentByFactory(getBacklightPercentByFactoryCallback getbacklightpercentbyfactorycallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_RATING_UNBLOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightpercentbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastByFactory(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(128, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastByFactory(getContrastByFactoryCallback getcontrastbyfactorycallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PLAYING_PARENT_RATING, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessByFactory(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PVR_PROG_BLOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessByFactory(getBrightnessByFactoryCallback getbrightnessbyfactorycallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_DUR_THUMB, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationByFactory(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GUIDANCE_BLOCKED_AFT_THUMB, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationByFactory(getSaturationByFactoryCallback getsaturationbyfactorycallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_PAUSE_STATUS, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessByFactory(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_INP_SIGNAL_UNKNOWN, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessByFactory(getSharpnessByFactoryCallback getsharpnessbyfactorycallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_HDCP_DETECT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessbyfactorycallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessMin(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MACROVISION_NOT_DETECT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightness20(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_ON, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessMid(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_EWS_OFF, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightness80(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BRDCSTING_SVC, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBrightnessMax(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_NON_BRDCSTING_SVC, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessMin(getBrightnessMinCallback getbrightnessmincallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_ON, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightness20(getBrightness20Callback getbrightness20callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DVBS_SHORT_OFF, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightness20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessMid(getBrightnessMidCallback getbrightnessmidcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STARTED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightness80(getBrightness80Callback getbrightness80callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STOPPED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightness80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBrightnessMax(getBrightnessMaxCallback getbrightnessmaxcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PAUSED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbrightnessmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastMin(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_SOURCE_READY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrast20(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_ES_LIST, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastMid(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REQ_STOP, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrast80(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PIN_CAP_CHANGE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setContrastMax(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_ES_AUTH_UPDATED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastMin(getContrastMinCallback getcontrastmincallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_BOUND, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrast20(getContrast20Callback getcontrast20callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_REACH_TIME_BOUND, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrast20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastMid(getContrastMidCallback getcontrastmidcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BUF_FULL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrast80(getContrast80Callback getcontrast80callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_UNAUTHORIZED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrast80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getContrastMax(getContrastMaxCallback getcontrastmaxcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BLOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getcontrastmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationMin(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_FULL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturation20(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BITRATE_TOO_HIGH, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationMid(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_NOT_SUPPORTED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturation80(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_STRG_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSaturationMax(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INTERNAL_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationMin(getSaturationMinCallback getsaturationmincallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_BAD_TICK, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturation20(getSaturation20Callback getsaturation20callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_CHANNEL_LOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturation20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationMid(getSaturationMidCallback getsaturationmidcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_PIN_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturation80(getSaturation80Callback getsaturation80callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_18_PLUS_LOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturation80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSaturationMax(getSaturationMaxCallback getsaturationmaxcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CHAPTER_CHANGE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsaturationmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHueMin(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONNECTION_ANA_FREQ, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHue20(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_ANA_AUTO_DETECTED_AUD_SYS, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHueMid(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_BUF_READY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHue80(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_TIME_UPDATE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setHueMax(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_UNDERFLOW, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHueMin(getHueMinCallback gethuemincallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_EOF, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethuemincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHue20(getHue20Callback gethue20callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_CLEAR_EOF, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethue20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHueMid(getHueMidCallback gethuemidcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_AUDIO_SWITCH_DONE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethuemidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHue80(getHue80Callback gethue80callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_GET_IFRAME, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethue80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getHueMax(getHueMaxCallback gethuemaxcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_STREAM_OPENED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                gethuemaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessMin(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_NUM_READY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpness20(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_FULL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessMid(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_EMPTY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpness80(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(181, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSharpnessMax(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOWN_ERR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessMin(getSharpnessMinCallback getsharpnessmincallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_EXPIRATION_ERR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpness20(getSharpness20Callback getsharpness20callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRVDATA_ACCESS_FAIL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpness20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessMid(getSharpnessMidCallback getsharpnessmidcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_BUSINESS_TKN, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpness80(getSharpness80Callback getsharpness80callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CERT_EXPIRED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpness80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSharpnessMax(getSharpnessMaxCallback getsharpnessmaxcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LIC_NOT_ALLOW, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsharpnessmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightMin(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_END, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklight20(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_UNAVAIL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightMid(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_SRV_BUSY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklight80(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_DEV_NOT_REG, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightMax(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CONTENT_EXHAUSTED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setBacklightPWMFreq(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOWN_SRV_ERR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightMin(getBacklightMinCallback getbacklightmincallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_USR_AGENT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightmincallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklight20(getBacklight20Callback getbacklight20callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNSUPPORTED_VER, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklight20callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightMid(getBacklightMidCallback getbacklightmidcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_CONTENT_TYPE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightmidcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklight80(getBacklight80Callback getbacklight80callback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_SRV_TKN, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklight80callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightMax(getBacklightMaxCallback getbacklightmaxcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INV_ASSET_ID, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightmaxcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getBacklightPWMFreq(getBacklightPWMFreqCallback getbacklightpwmfreqcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INIT_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getbacklightpwmfreqcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanTop(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_EXPIRED_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanBottom(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_ACQUISITION_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanLeft(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_SERVER_CONNECTION_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOverscanRight(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TOKEN_ACQUISITION_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanTop(getOverscanTopCallback getoverscantopcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_ACQUISITION_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscantopcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanBottom(getOverscanBottomCallback getoverscanbottomcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CMPB_QUERY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanbottomcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanLeft(getOverscanLeftCallback getoverscanleftcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_IDX_CHANGED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanleftcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOverscanRight(getOverscanRightCallback getoverscanrightcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CC_ATTR_CHANGED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoverscanrightcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPanel(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_STREAM_CTR_NOT_SUPPORT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPanel(getPanelCallback getpanelcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_AUDIO_STREAM_CTR_NOT_SUPPORT, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpanelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTemperature(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_VIDEO_WFD_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTemperature(getFactoryColorTemperatureCallback getfactorycolortemperaturecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CONN_PMT_UPDATED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortemperaturecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceOffsetRed(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_CAM_CARD_REMOVE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceOffsetRed(getFactoryWhiteBalanceOffsetRedCallback getfactorywhitebalanceoffsetredcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FILE_OPER_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalanceoffsetredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceOffsetGreen(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_REC_INPUT_LOCKED, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceOffsetGreen(getFactoryWhiteBalanceOffsetGreenCallback getfactorywhitebalanceoffsetgreencallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_PSI_TABLE_UPDATE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalanceoffsetgreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceOffsetBlue(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_INVALID_ARG, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceOffsetBlue(getFactoryWhiteBalanceOffsetBlueCallback getfactorywhitebalanceoffsetbluecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_UNKNOW_DRMTYPE, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalanceoffsetbluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceGainRed(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_LICENSE_INVALID, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceGainRed(getFactoryWhiteBalanceGainRedCallback getfactorywhitebalancegainredcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_GET_KEY_ERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalancegainredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceGainGreen(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_CAD_INVALID, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceGainGreen(getFactoryWhiteBalanceGainGreenCallback getfactorywhitebalancegaingreencallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_NO_MARLIN_KEY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalancegaingreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryWhiteBalanceGainBlue(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_NOT_MARLIN_MS3_URL, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryWhiteBalanceGainBlue(getFactoryWhiteBalanceGainBlueCallback getfactorywhitebalancegainbluecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_MARLIN_KEY_FORMATERROR, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorywhitebalancegainbluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceLevel(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_MARLIN, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceLevel(getFactoryMultiPointWhiteBalanceLevelCallback getfactorymultipointwhitebalancelevelcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_NAUTILUS_MARLIN, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalancelevelcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceRed(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TYPE_PLAYREADY, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceRed(getFactoryMultiPointWhiteBalanceRedCallback getfactorymultipointwhitebalanceredcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(227, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalanceredcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceGreen(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(228, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceGreen(getFactoryMultiPointWhiteBalanceGreenCallback getfactorymultipointwhitebalancegreencallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(229, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalancegreencallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryMultiPointWhiteBalanceBlue(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(230, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryMultiPointWhiteBalanceBlue(getFactoryMultiPointWhiteBalanceBlueCallback getfactorymultipointwhitebalancebluecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(231, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorymultipointwhitebalancebluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setTestPattern(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(232, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setInsidePattern(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(233, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerColor(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(234, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerColor(getFactoryColorTunerColorCallback getfactorycolortunercolorcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(235, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunercolorcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerBrightness(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(236, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerBrightness(getFactoryColorTunerBrightnessCallback getfactorycolortunerbrightnesscallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(237, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunerbrightnesscallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerSaturation(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(238, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerSaturation(getFactoryColorTunerSaturationCallback getfactorycolortunersaturationcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(239, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunersaturationcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setFactoryColorTunerHue(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(240, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFactoryColorTunerHue(getFactoryColorTunerHueCallback getfactorycolortunerhuecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(241, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfactorycolortunerhuecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setVcomTestEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(242, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setAgingModeEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(244, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPqEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(246, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPqEnable(getPqEnableCallback getpqenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(247, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpqenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setGammaEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(248, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getGammaEnable(getGammaEnableCallback getgammaenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(249, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getgammaenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setSceEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(250, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSceEnable(getSceEnableCallback getsceenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(251, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsceenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setXvyccEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(252, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getXvyccEnable(getXvyccEnableCallback getxvyccenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(253, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getxvyccenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int SetAutoGammaEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(254, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getSignalVideoFormat(getSignalVideoFormatCallback getsignalvideoformatcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(259, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getsignalvideoformatcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorOptionState(getLightSensorOptionStateCallback getlightsensoroptionstatecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(302, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensoroptionstatecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorValue(getLightSensorValueCallback getlightsensorvaluecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(319, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBOffsetRedDelta(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(393, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBOffsetRedDelta(get2PWBOffsetRedDeltaCallback get2pwboffsetreddeltacallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(394, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwboffsetreddeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBOffsetGreenDelta(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(395, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBOffsetGreenDelta(get2PWBOffsetGreenDeltaCallback get2pwboffsetgreendeltacallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(396, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwboffsetgreendeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBOffsetBlueDelta(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(397, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBOffsetBlueDelta(get2PWBOffsetBlueDeltaCallback get2pwboffsetbluedeltacallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(398, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwboffsetbluedeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBGainRedDelta(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(399, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBGainRedDelta(get2PWBGainRedDeltaCallback get2pwbgainreddeltacallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(400, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwbgainreddeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBGainGreenDelta(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(401, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBGainGreenDelta(get2PWBGainGreenDeltaCallback get2pwbgaingreendeltacallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(402, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwbgaingreendeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int set2PWBGainBlueDelta(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(403, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void get2PWBGainBlueDelta(get2PWBGainBlueDeltaCallback get2pwbgainbluedeltacallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(404, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                get2pwbgainbluedeltacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemData(int i, getItemDataCallback getitemdatacallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(409, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemdatacallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32Vector());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int resetAllFac2PWhiteBalance(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(410, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setItemString(int i, String str) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            hwParcel.writeString(str);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(413, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemString(int i, getItemStringCallback getitemstringcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(414, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemstringcallback.onValues(hwParcel2.readInt32(), hwParcel2.readString());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setItemValue(int i, int i2) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(415, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getItemValue(int i, getItemValueCallback getitemvaluecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(416, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getitemvaluecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setRGBPattern(int i, int i2, int i3, int i4) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            hwParcel.writeInt32(i4);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(421, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDolbyIQEnableFlag(getDolbyIQEnableFlagCallback getdolbyiqenableflagcallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(441, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyiqenableflagcallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setDolbyIQUserBinEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(447, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLightSensorCurve(int i, int i2, int i3) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            hwParcel.writeInt32(i3);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(452, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getLightSensorCurve(int i, int i2, getLightSensorCurveCallback getlightsensorcurvecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            hwParcel.writeInt32(i2);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(453, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getlightsensorcurvecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getDolbyIQUserBinEnable(getDolbyIQUserBinEnableCallback getdolbyiquserbinenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(454, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getdolbyiquserbinenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setPixelShiftEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(519, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getPixelShiftEnable(getPixelShiftEnableCallback getpixelshiftenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(520, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getpixelshiftenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDLocalFrozenChkEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(527, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDLocalFrozenChkEnable(getOLEDLocalFrozenChkEnableCallback getoledlocalfrozenchkenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(528, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledlocalfrozenchkenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDLEAEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(531, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDLEAEnable(getOLEDLEAEnableCallback getoledleaenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(532, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledleaenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setOLEDHDREnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(535, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getOLEDHDREnable(getOLEDHDREnableCallback getoledhdrenablecallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(536, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getoledhdrenablecallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public int setLDTestEnable(int i) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            hwParcel.writeInt32(i);
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(539, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readInt32();
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public void getFRCVersion(getFRCVersionCallback getfrcversioncallback) throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("vendor.hisense.mw.hs_av_service@1.0::IHisVideoApi");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(540, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                getfrcversioncallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt32());
            } finally {
                hwParcel2.release();
            }
        }

        @Override // vendor.hisense.mw.hs_av_service.V1_0.IHisVideoApi
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256067662, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readStringVector();
            } finally {
                hwParcel2.release();
            }
        }

        public String interfaceDescriptor() throws RemoteException {
            HwParcel hwParcel = new HwParcel();
            hwParcel.writeInterfaceToken("android.hidl.base@1.0::IBase");
            HwParcel hwParcel2 = new HwParcel();
            try {
                this.mRemote.transact(256136003, hwParcel, hwParcel2, 0);
                hwParcel2.verifySuccess();
                hwParcel.releaseTemporaryStorage();
                return hwParcel2.readString();
            } finally {
                hwParcel2.release();
            }
        }
    }
}
