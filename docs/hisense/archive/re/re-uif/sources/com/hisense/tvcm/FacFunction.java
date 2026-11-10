package com.hisense.tvcm;

import android.content.Context;
import android.os.Handler;
import com.hisense.uifac.util.IMtkFunCallback;
import com.hisense.uifac.util.ISourceStatusChangeCallBack;

/* JADX INFO: loaded from: classes.dex */
public abstract class FacFunction {
    public abstract boolean IsDolbyIQSupport();

    public abstract boolean adcCalibration();

    public abstract void addATVPresetChannel(ATVPresetChannel aTVPresetChannel);

    public abstract void addDTVPresetChannel(DTVPresetChannel dTVPresetChannel);

    public abstract void addSourceChangedStatus(ISourceStatusChangeCallBack iSourceStatusChangeCallBack);

    public abstract void addTVPresetChannelForSA(int i);

    public abstract void addTVPresetChannelForcityname(String str);

    public abstract void cancelCurrMenu();

    public abstract void changeChannel(int i);

    public abstract void changeChannel(int i, int i2);

    public abstract void changeChannelATSC(int i, int i2);

    public abstract void changeChannelForId(int i, int i2);

    public abstract void changePreciseChannelForATSC(int i, int i2);

    public abstract void channelDown();

    public abstract void channelUp();

    public abstract int checkTconTempVr();

    public abstract void cleanAgingTime();

    public abstract boolean cleanEepKeyFile();

    public abstract boolean clearHdcp14Key();

    public abstract boolean clearHdcp22Key();

    public abstract void clearKeys();

    public abstract boolean clearOMRunningTime();

    public abstract boolean clearPlayReadyKey();

    public abstract boolean clearWideVineKey();

    public abstract void closeCurrentMMI();

    public abstract void closeDBX();

    public abstract boolean closeGamma(int[] iArr);

    public abstract int closeVcomCardParam();

    public abstract void closeWifi();

    public abstract void disconnectWifi();

    public abstract void doUsbUpgrade(Context context);

    public abstract void enterCAMMainMenu();

    public abstract void execWhenHisFacEnding();

    public abstract void executeClearProtectly();

    public abstract void executeClearUnprotectly();

    public abstract void executeDataInit(Handler handler);

    public abstract void executeFactoryOk(IMtkFunCallback iMtkFunCallback);

    public abstract void exitTvSource();

    public abstract boolean factoryOk_checkAutoGamma();

    public abstract String[] geBrandIsolateList();

    public abstract void generateEepKeyFile();

    public abstract FacPropertyDemo.DimensionGroup get2Dor3D();

    public abstract int get2PWBGainBlueDelta();

    public abstract int get2PWBGainGreenDelta();

    public abstract int get2PWBGainRedDelta();

    public abstract int get2PWBOffsetBlueDelta();

    public abstract int get2PWBOffsetGreenDelta();

    public abstract int get2PWBOffsetRedDelta();

    public abstract FacPropertyDemo.ThreeDimensionMode get3DMode();

    public abstract int get3D_Sync_Delay();

    public abstract int get3Dto2D();

    public abstract String get5GChannel();

    public abstract String getACASId();

    public abstract int getACASState();

    public abstract int getADCBlueGain();

    public abstract int getADCBlueOffset();

    public abstract int getADCGreenGain();

    public abstract int getADCGreenOffset();

    public abstract int getADCRedGain();

    public abstract int getADCRedOffset();

    public abstract int getAEFC_43();

    public abstract int getAEFC_44();

    public abstract int getAEFC_66Bit76();

    public abstract int getAEFC_6EBit3210();

    public abstract int getAEFC_6EBit7654();

    public abstract int getAEFC_A0();

    public abstract int getAEFC_A1();

    public abstract int getAEFC_CB();

    public abstract int getAEFC_D4();

    public abstract int getAEFC_D5Bit2();

    public abstract int getAEFC_D7HighBoun();

    public abstract int getAEFC_D7LowBoun();

    public abstract int getAEFC_D8Bit3210();

    public abstract int getAEFC_D9Bit0();

    public abstract int getAgingFlag();

    public abstract int getAmbientLightValue();

    public abstract int getAreaControl();

    public abstract int getAreaControlFrequence();

    public abstract int getAttackTime();

    public abstract String getAttestationKey();

    public abstract boolean getAudHpfStatus();

    public abstract boolean getAudPeqEnableStatus();

    public abstract boolean getAudSocDrcEnableStatus();

    public abstract int getAudioDRC();

    public abstract int getAudioDspVersion();

    public abstract int getAudioHiDevMode();

    public abstract FacPropertyDemo.AudioMode getAudioMode();

    public abstract int getAudioNrThr();

    public abstract int getAudioSifThreshold();

    public abstract int getAuthMode();

    public abstract String getAutoGammaStatus();

    public abstract int getAutoVolumeCtrlEnable();

    public abstract int getAutoVolumeCtrlThreshold();

    public abstract int getBCUT();

    public abstract int getBCUTOffset();

    public abstract int getBDRV();

    public abstract int getBDRVOffset();

    public abstract String getBTMacAddress();

    public abstract float getBackgroundNoiseCorrectionFactorValue();

    public abstract int getBackgroundNoisePredeterminedValue();

    public abstract int getBackgroundNoiseValue();

    public abstract int getBacklight();

    public abstract String getBacklightDriverVersion();

    public abstract int getBacklight_20(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point);

    public abstract int getBacklight_80(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point);

    public abstract int getBacklight_Max(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point);

    public abstract int getBacklight_Mid(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point);

    public abstract int getBacklight_Min(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point);

    public abstract int getBalance();

    public abstract int getBalanceForLike850();

    public abstract int getBalanceForTweeter();

    public abstract int getBalanceForTweeterWoofer();

    public abstract int[] getBoardTemperature();

    public abstract String getBrand();

    public abstract int getBrightness();

    public abstract int getBypassGammaEnable();

    public abstract int getBypassPQEnable();

    public abstract int getBypassSecEnable();

    public abstract int getBypassXvyccEnable();

    public abstract String getCICardName();

    public abstract boolean getCICardStatus();

    public abstract String getCIPlusKey();

    public abstract int getCTBrightness();

    public abstract int getCTHue();

    public abstract int getCTSaturation();

    public abstract int getCWValue1();

    public abstract int getCWValue2();

    public abstract int getChinaDescramblerBox();

    public abstract String getChinaDrmKey();

    public abstract String getChipID();

    public abstract String getClientType();

    public abstract int getColor();

    public abstract int getColorBypassEnable();

    public abstract FacPropertyDemo.ColorTemperature getColorTemperature();

    public abstract FacPropertyDemo.ColorTuner getColorTuner();

    public abstract int getContrast();

    public abstract int getConvergence();

    public abstract int getCountry();

    public abstract String getCountryName();

    public abstract String getCurrentChannelNumberString();

    public abstract FacPropertyDemo.OVERSCAN_TIMING getCurrentOverScanTiming();

    public abstract FacPropertyDemo.SourceEnum getCurrentSource();

    public abstract String getCurrentSourceName();

    public abstract int getCurrentTvInch();

    public abstract long getDataTime();

    public abstract String getDataVersion();

    public abstract String getDefaultMacAddress();

    public abstract int getDelayReduce();

    public abstract int getDemuraSwitchStatus();

    public abstract String getDeviceID();

    public abstract int getDisplayBoardUpgradeMode();

    public abstract int getDistanceSensorStrengthValue();

    public abstract String getDlpMcuVersion();

    public abstract int getDmpMode();

    public abstract int getDolbyIQUserBinEnable();

    public abstract String getDolbyIQUserBinInfo();

    public abstract void getDolbyUpdateResultTip(int i);

    public abstract String getDtcpIpKey();

    public abstract FacPropertyDemo.DtvDemodulationType getDtvType();

    public abstract int getEarcStatus();

    public abstract boolean getEmmcType();

    public abstract int getEqualizer10KHz();

    public abstract int getEqualizer10KHz_New();

    public abstract int getEqualizer120Hz();

    public abstract int getEqualizer120Hz_New();

    public abstract int getEqualizer1500Hz();

    public abstract int getEqualizer1KHz();

    public abstract int getEqualizer20KHz();

    public abstract int getEqualizer2500Hz();

    public abstract int getEqualizer250Hz();

    public abstract int getEqualizer30KHz();

    public abstract int getEqualizer40KHz();

    public abstract int getEqualizer500Hz();

    public abstract int getEqualizer50KHz();

    public abstract int getEqualizer5KHz();

    public abstract int getEqualizer600Hz();

    public abstract int getEqualizer6KHz();

    public abstract int getErrDetCount();

    public abstract String getF1Result();

    public abstract String getFPGAVersion();

    public abstract String getFRCVersion();

    public abstract int getFREQ1();

    public abstract int getFREQ2();

    public abstract int getFREQ3();

    public abstract int getFREQ4();

    public abstract int getFREQ5();

    public abstract int getFREQ6();

    public abstract int getFREQ7();

    public abstract int getFREQ8();

    public abstract int getFacCheckLogStatus();

    public abstract int getFacLogStatus();

    public abstract int getFactoryProtectFlag();

    public abstract int getFactoryStatus();

    public abstract int getFactoryTconStatus();

    public abstract int[] getFansSpeed();

    public abstract int getFieldDepth();

    public abstract String getFirstPowerOnTime();

    public abstract String getFullLogoName();

    public abstract int getGAIN1();

    public abstract int getGAIN2();

    public abstract int getGAIN3();

    public abstract int getGAIN4();

    public abstract int getGAIN5();

    public abstract int getGAIN6();

    public abstract int getGAIN7();

    public abstract int getGAIN8();

    public abstract int getGCUT();

    public abstract int getGCUTOffset();

    public abstract int getGDRV();

    public abstract int getGDRVOffset();

    public abstract int getGainDistributionThr();

    public abstract int getGammaBypassEnable();

    public abstract String getHDDSize();

    public abstract int getHStart();

    public abstract String getHdcp14Key();

    public abstract String getHdcp2xKey();

    public abstract String getHdcpKey();

    public abstract FacPropertyDemo.HomeOrTVMode getHomeorTV();

    public abstract int getHpfFrequency();

    public abstract String getIPAddress();

    public abstract int getImageCompensationEnable();

    public abstract int getInputSourceType(int i);

    public abstract int getJBExecuteCount();

    public abstract String getJBExecuteTime();

    public abstract String getLCMRunningStatus();

    public abstract int getLNB();

    public abstract int getLanguage();

    public abstract int getLaserAlarmEnable();

    public abstract int getLaserEyeProtectSwitch();

    public abstract int getLaserForeignObjectDetectSwitch();

    public abstract int getLaserMovableCoverStep();

    public abstract String getLaserRuningState();

    public abstract String getLaserRuningTime();

    public abstract int getLastSource();

    public abstract String getLedEffectVersion();

    public abstract int getLeftRightSwap();

    public abstract int getLightSensorCurve(int i, int i2);

    public abstract int getLightSensorIndex();

    public abstract int getLightSensorOptionState();

    public abstract int getLightSensorValue();

    public int[] getLightSensorValues() {
        return null;
    }

    public abstract int getLocalDimmingState();

    public abstract int getLocaldimmingEnable();

    public abstract int getLogo();

    public abstract FacPropertyDemo.Logo getLogoEnum();

    public abstract FacPropertyDemo.LogoForEM_SA getLogoForEMSAEnum();

    public abstract String getLogoFullName();

    public abstract String getLogoFullNameForSA();

    public abstract String getLogoFullNameMon();

    public abstract String getLogoName();

    public abstract int getLogoValueForSA(String str);

    public abstract String getMGKKey();

    public abstract String getMacAddress();

    public abstract String getMacFileNameInUsbDisk();

    public abstract String getMarlinKey();

    public abstract int getMaxx3DOption();

    public abstract int getMaxxBassFrequency();

    public abstract int getMaxxTrebleFrequency();

    public abstract String getMcuVersionInfo();

    public abstract int getMicDb();

    public abstract int getMicDevice();

    public abstract int getMicRecordTime();

    public abstract int getMicSwitchStatus();

    public abstract int getMicTestResult();

    public abstract int getMirrorValue();

    public abstract int getModeValue();

    public abstract FacPropertyDemo.ModelPanelGroup getModelPanel();

    public abstract int getModelSelection();

    public abstract int getMultiBalance();

    public abstract int getMultiDigitalAMPValueByDevId(int i, int i2);

    public abstract String getNewMachineID();

    public abstract int getNonstandardDescramble();

    public abstract int getNonstandardDescrambleplus();

    public int getNvodProgramDisplay() {
        return 0;
    }

    public abstract FacPropertyDemo.OSDLanguage getOSDLanguage();

    public abstract int getOffrsExecuteCount();

    public abstract String getOffrsExecuteTime();

    public abstract int getOledCpcValue();

    public abstract int getOledHdrValue();

    public abstract int getOledLbaValue();

    public abstract int getOledLbc2Value();

    public abstract int getOledLeaValue();

    public abstract int getOledOrbitValue();

    public abstract int getOledPixelShiftShapeValue();

    public abstract int getOledPixelShiftTimerValue();

    public abstract int getOledPixelShiftTypeValue();

    public abstract int getOledTpcValue();

    public abstract String getOrignalMachineID();

    public abstract int getOverScanBottom();

    public abstract int getOverScanLeft();

    public abstract int getOverScanRight();

    public abstract int getOverScanTop();

    public abstract String getPVRKey();

    public abstract int getPWMFreq(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point);

    public abstract int getPiCurveBrigth20();

    public abstract int getPiCurveBrigth80();

    public abstract int getPiCurveBrigthMax();

    public abstract int getPiCurveBrigthMid();

    public abstract int getPiCurveBrigthMin();

    public abstract int getPiCurveContract20();

    public abstract int getPiCurveContract80();

    public abstract int getPiCurveContractMax();

    public abstract int getPiCurveContractMid();

    public abstract int getPiCurveContractMin();

    public abstract int getPiCurveHue20();

    public abstract int getPiCurveHue80();

    public abstract int getPiCurveHueMax();

    public abstract int getPiCurveHueMid();

    public abstract int getPiCurveHueMin();

    public abstract int getPiCurveSaturation20();

    public abstract int getPiCurveSaturation80();

    public abstract int getPiCurveSaturationMax();

    public abstract int getPiCurveSaturationMid();

    public abstract int getPiCurveSaturationMin();

    public abstract int getPiCurveSharpness20();

    public abstract int getPiCurveSharpness80();

    public abstract int getPiCurveSharpnessMax();

    public abstract int getPiCurveSharpnessMid();

    public abstract int getPiCurveSharpnessMin();

    public abstract int getPictureCurve0();

    public abstract int getPictureCurve100();

    public abstract int getPictureCurve25();

    public abstract int getPictureCurve50();

    public abstract int getPictureCurve75();

    public abstract int getPictureCurveMaximum();

    public abstract int getPictureCurveMinimum();

    public abstract FacPropertyDemo.PictureMode getPictureMode();

    public abstract FacPropertyDemo.PictureModeTW_Toshiba getPictureModeTW();

    public abstract String getPlayReady25Key();

    public abstract String getPlayReady40Key();

    public abstract String getPlayReadyKey();

    public abstract FacPropertyDemo.PowerModeEnum getPowerOnMode();

    public abstract int getPqBypassEnable();

    public abstract int getPrescale();

    public abstract String getProductType();

    public abstract boolean getProductionKey();

    public int getProgramNumDisplay() {
        return 0;
    }

    public abstract String getProjectId();

    public abstract String getProjectIdFileNameInUsbDisk();

    public abstract int getQ1();

    public abstract int getQ2();

    public abstract int getQ3();

    public abstract int getQ4();

    public abstract int getQ5();

    public abstract int getQ6();

    public abstract int getQ7();

    public abstract int getQ8();

    public abstract String getQDAKey();

    public abstract String getQDAUUID();

    public abstract int getRCUT();

    public abstract int getRCUTOffset();

    public abstract int getRDRV();

    public abstract int getRDRVOffset();

    public abstract int getRegion();

    public abstract int getReleaseTime();

    public abstract int getSSCDDREnable();

    public abstract int getSSCDDRModu();

    public abstract int getSSCDDRSpan();

    public abstract int getSSCVByOneEnable();

    public abstract int getSSCVByOneModu();

    public abstract int getSSCVByOneRange();

    public abstract int getSSCVByOneSpan();

    public abstract int getSTEP1();

    public abstract int getSTEP2();

    public abstract int getSTEP3();

    public abstract int getSTEP4();

    public abstract int getSTEP5();

    public abstract int getSTEP6();

    public abstract int getSTEP7();

    public abstract int getSTEP8();

    public abstract FacPropertyDemo.SavingMode getSavingMode();

    public abstract int getScreen();

    public abstract String getSeeQVaultKey();

    public abstract boolean getSensorEnableStatus();

    public abstract int getSensorLuxValue();

    public abstract String getServiceTag();

    public abstract int getSharpness();

    public abstract String getSlaveBacklightDriverVersion();

    public abstract String getSlaveLCMRunningStatus();

    public abstract int getSocDrc();

    public abstract String getSoundMcuVersion();

    public abstract int getSoundVolCurveMax();

    public abstract int getSoundVolCurveMin();

    public abstract boolean getSportMode();

    public abstract String getStartOnCount();

    public abstract int getSubWooferTotal();

    public abstract String getTVRunningTime();

    public abstract String getTconTempVersion();

    public abstract String getTconVersion();

    public abstract int getTemperatureProtection();

    public abstract int getThreshold();

    public abstract FacPropertyDemo.ToFacEnum getToFac();

    public abstract int getTunerMode();

    public abstract String getTvChipVersion();

    public abstract String getTvScreenVersion();

    public abstract int getTvsCurrentChannelNumber();

    public abstract String getTvsCurrentChannelTuner();

    public abstract String getTvsLightSensor();

    public abstract int getTvsTPC();

    public abstract String getTypeCVersion();

    public abstract int getUartSwitchStatus();

    public abstract int getUnivMultiBalance(int i);

    public abstract int getUpgradeFlag();

    public abstract int getUserAgingSwitchStatus();

    public int getUserOrderSet() {
        return 0;
    }

    public abstract int getVStart();

    public abstract int getVcomVoltage();

    public abstract int getVcomVoltage2(int i);

    public abstract int[] getVcomsValue();

    public abstract int getVdDspVersion();

    public abstract String getVersionInfo();

    public abstract FacPropertyDemo.VideoCurveModeGroup getVideoCurveModeGroup();

    public abstract String getVideoInfo();

    public abstract int getVifAgcRef();

    public abstract int getVifAsiaSignalOption();

    public abstract int getVifClampGainOvNegative();

    public abstract int getVifCrKi();

    public abstract int getVifCrKp();

    public abstract int getVifCrKpKiAdjust();

    public abstract int getVifCrThr();

    public abstract int getVifOverModulation();

    public abstract int getVifTop();

    public abstract int getVifVersion();

    public abstract int getVifVgaMaximum();

    public abstract int getVolume();

    public abstract int getVolumeCurve(int i);

    public abstract int getVolumeCurve0();

    public abstract int getVolumeCurve100();

    public abstract int getVolumeCurve25();

    public abstract int getVolumeCurve50();

    public abstract int getVolumeCurve75();

    public abstract int getWB20PointBlue();

    public abstract int getWB20PointGreen();

    public abstract int getWB20PointLevel();

    public abstract int getWB20PointRed();

    public abstract String getWideKey();

    public abstract String getWifiMacAddress();

    public abstract boolean getWifiStatus();

    public abstract int getWooferAudioDRC();

    public abstract int getWooferAudioOutputPort();

    public abstract int getWooferAudioPaGain();

    public abstract int getZDMicResult();

    public abstract FacPropertyDemo.ZoomMode getZoomMode();

    public abstract FacPropertyDemo.ZoomModeForEM getZoomModeForEM();

    public abstract String getlanguageName();

    public abstract int getnetworktype();

    public abstract void initCamDisplayHandler(Handler handler, CamDisplayManager camDisplayManager);

    public abstract int initInnertconBoard();

    public abstract void initMtkManagers(Context context);

    public abstract void initScreenType();

    public abstract boolean is3DSupported();

    public abstract boolean isBluSupport();

    public abstract boolean isBrandIsolate();

    public abstract boolean isDualBluSupport();

    public abstract boolean isFileExit(String str);

    public abstract boolean isFirstPowerOn();

    public abstract boolean isFrcSupport();

    public abstract boolean isHaveFte();

    public abstract boolean isHaveHDD();

    public abstract boolean isHaveTuner();

    public abstract boolean isKeyWriteProtecEnabled();

    public abstract int isNoSignalStandbyEnabled();

    public abstract int isRegionX(int i);

    public abstract boolean isShutdownBroadcastFromFactory();

    public abstract boolean isSlotActive();

    public abstract boolean isStoreMode();

    public abstract boolean isTconSupport();

    public abstract boolean isVolumeMute();

    public abstract boolean isWooferModeOn();

    public abstract void openWifi();

    public abstract void pauseTv(boolean z);

    public abstract void readDriverInfo();

    public abstract String readLog();

    public abstract void reconnectWifi();

    public abstract void registerCamListeners();

    public abstract void resetDBX();

    public abstract boolean resetGamma(int i);

    public abstract boolean resetGraphCorrection();

    public abstract void restoreAgingTime();

    public abstract boolean saveVcomVoltage(int i);

    public abstract boolean saveVcomVoltage2(int i, int i2);

    public abstract void selectMenuItem(int i);

    public abstract void sendCmd(String str);

    public abstract void set2Dor3D(FacPropertyDemo.DimensionGroup dimensionGroup);

    public abstract int set2PWBGainBlueDelta(int i);

    public abstract int set2PWBGainGreenDelta(int i);

    public abstract int set2PWBGainRedDelta(int i);

    public abstract int set2PWBOffsetBlueDelta(int i);

    public abstract int set2PWBOffsetGreenDelta(int i);

    public abstract int set2PWBOffsetRedDelta(int i);

    public abstract void set3DMode(FacPropertyDemo.ThreeDimensionMode threeDimensionMode);

    public abstract void set3D_Sync_Delay(int i);

    public abstract void set3Dto2D(int i);

    public abstract void setADCBlueGain(int i);

    public abstract void setADCBlueOffset(int i);

    public abstract void setADCGreenGain(int i);

    public abstract void setADCGreenOffset(int i);

    public abstract void setADCRedGain(int i);

    public abstract void setADCRedOffset(int i);

    public abstract void setAEFC_43(int i);

    public abstract void setAEFC_44(int i);

    public abstract void setAEFC_66Bit76(int i);

    public abstract void setAEFC_6EBit3210(int i);

    public abstract void setAEFC_6EBit7654(int i);

    public abstract void setAEFC_A0(int i);

    public abstract void setAEFC_A1(int i);

    public abstract void setAEFC_CB(int i);

    public abstract void setAEFC_D4(int i);

    public abstract void setAEFC_D5Bit2(int i);

    public abstract void setAEFC_D7HighBoun(int i);

    public abstract void setAEFC_D7LowBoun(int i);

    public abstract void setAEFC_D8Bit3210(int i);

    public abstract void setAEFC_D9Bit0(int i);

    public abstract void setATVPresetChannel();

    public abstract void setAgingFlag(int i);

    public abstract boolean setAgingMode(boolean z);

    public abstract void setAreaControl(int i);

    public abstract void setAreaControlFrequence(int i);

    public abstract void setAttackTimeAutoVolumeControl(int i);

    public abstract boolean setAttestationKey(String str);

    public abstract boolean setAudHpfStatus(boolean z);

    public abstract boolean setAudPeqEnableStatus(boolean z);

    public abstract boolean setAudSocDrcEnableStatus(boolean z);

    public abstract void setAudioCurveDetailValueNew(int i, int i2);

    public abstract void setAudioDRC(int i);

    public abstract void setAudioDspVersion(int i);

    public abstract void setAudioHiDevMode(int i);

    public abstract void setAudioMode(FacPropertyDemo.AudioMode audioMode);

    public abstract void setAudioNrThr(int i);

    public abstract void setAudioSifThreshold(int i);

    public abstract void setAuthMode(int i);

    public abstract void setAutoVolumeCtrlEnable(int i);

    public abstract boolean setAutoVolumeCtrlThreshold(int i);

    public abstract void setBCUT(int i);

    public abstract void setBCUTOffset(int i);

    public abstract void setBDRV(int i);

    public abstract void setBDRVOffset(int i);

    public abstract boolean setBackLightOn(boolean z);

    public abstract int setBackgroundNoiseValue(int i);

    public abstract void setBacklight(int i);

    public abstract void setBacklight_20(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i);

    public abstract void setBacklight_80(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i);

    public abstract void setBacklight_Max(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i);

    public abstract void setBacklight_Mid(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i);

    public abstract void setBacklight_Min(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i);

    public abstract void setBalance(int i);

    public abstract void setBalanceForLike850(int i);

    public abstract void setBalanceForTweeter(int i);

    public abstract void setBalanceForTweeterWoofer(int i);

    public abstract boolean setBrand(String str);

    public abstract void setBrightness(int i);

    public abstract void setBypassGammaEnable(int i);

    public abstract void setBypassPQEnable(int i);

    public abstract void setBypassSecEnable(int i);

    public abstract void setBypassXvyccEnable(int i);

    public abstract boolean setCIPlusKey(String str);

    public abstract void setCTBrightness(int i);

    public abstract void setCTHue(int i);

    public abstract void setCTSaturation(int i);

    public abstract boolean setCWValue1(int i);

    public abstract boolean setCWValue2(int i);

    public abstract boolean setChannelInitFileFromUdisk(String str);

    public abstract void setChinaDescramblerBox(int i);

    public abstract boolean setChinaDrmKey(String str);

    public abstract boolean setClientType(String str);

    public abstract void setColor(int i);

    public abstract void setColorBypassEnable(int i);

    public abstract void setColorTemperature(FacPropertyDemo.ColorTemperature colorTemperature);

    public abstract void setColorTuner(FacPropertyDemo.ColorTuner colorTuner);

    public abstract void setContrast(int i);

    public abstract void setConvergence(int i);

    public abstract void setCountry(int i);

    public abstract boolean setCountryIndex(int i);

    public abstract boolean setDMPMode(int i);

    public abstract void setDMPStatusToLog();

    public abstract boolean setDTCPIPKey(String str);

    public abstract boolean setDTSSwitch(int i);

    public abstract void setDTVPresetChannel();

    public abstract void setDelayReduce(int i);

    public abstract int setDemuraSwitchStatus(int i);

    public abstract void setDisplayBoardUpgrade(int i);

    public abstract int setDolbyIQUserBinEnable(int i);

    public abstract int setDolbyIQUserBinUpdate(String str);

    public abstract void setDtvType(FacPropertyDemo.DtvDemodulationType dtvDemodulationType);

    public abstract boolean setEarcStatus(int i);

    public abstract void setEnterVcomVideoParameter();

    public abstract void setEqualizer10KHz(int i);

    public abstract void setEqualizer10KHz_New(int i);

    public abstract void setEqualizer120Hz(int i);

    public abstract void setEqualizer120Hz_New(int i);

    public abstract void setEqualizer1500Hz(int i);

    public abstract void setEqualizer1KHz(int i);

    public abstract void setEqualizer20KHz(int i);

    public abstract void setEqualizer2500Hz(int i);

    public abstract void setEqualizer250Hz(int i);

    public abstract void setEqualizer30KHz(int i);

    public abstract void setEqualizer40KHz(int i);

    public abstract void setEqualizer500Hz(int i);

    public abstract void setEqualizer50KHz(int i);

    public abstract void setEqualizer5KHz(int i);

    public abstract void setEqualizer600Hz(int i);

    public abstract void setEqualizer6KHz(int i);

    public abstract void setExitVcomVideoParameter();

    public abstract void setFREQ1(int i);

    public abstract void setFREQ2(int i);

    public abstract void setFREQ3(int i);

    public abstract void setFREQ4(int i);

    public abstract void setFREQ5(int i);

    public abstract void setFREQ6(int i);

    public abstract void setFREQ7(int i);

    public abstract void setFREQ8(int i);

    public abstract void setFacCheckLogStatus(int i);

    public abstract void setFacLogStatus(int i);

    public abstract int setFactoryProtectFlag(int i);

    public abstract void setFactoryStatus(int i);

    public abstract void setFieldDepth(int i);

    public abstract boolean setFilterChangeTime();

    public abstract boolean setFirstTimePowerOn(int i);

    public abstract void setGAIN1(int i);

    public abstract void setGAIN2(int i);

    public abstract void setGAIN3(int i);

    public abstract void setGAIN4(int i);

    public abstract void setGAIN5(int i);

    public abstract void setGAIN6(int i);

    public abstract void setGAIN7(int i);

    public abstract void setGAIN8(int i);

    public abstract void setGCUT(int i);

    public abstract void setGCUTOffset(int i);

    public abstract void setGDRV(int i);

    public abstract void setGDRVOffset(int i);

    public abstract void setGainDistributionThr(int i);

    public abstract boolean setGamma(int[] iArr);

    public abstract void setGammaBypassEnable(int i);

    public abstract void setHStart(int i);

    public abstract boolean setHdcp14Key(String str);

    public abstract boolean setHdcp2xKey(String str);

    public abstract void setHomeorTV(FacPropertyDemo.HomeOrTVMode homeOrTVMode);

    public abstract boolean setHpfFrequency(int i);

    public abstract void setImageCompensationEnable(int i);

    public abstract void setLNB(int i);

    public abstract boolean setLangIndex(int i);

    public abstract void setLanguage(int i);

    public abstract boolean setLaserAutoGraphCorrection(int[] iArr);

    public abstract boolean setLaserEyeProtectSwitch(int i);

    public abstract void setLaserForeignObjectDetectSwitch(int i);

    public abstract boolean setLaserSlidingCoverMode(int i);

    public abstract void setLaserToAlarm(int i);

    public abstract int setLedControl(int i);

    public abstract void setLeftRightSwap(int i);

    public abstract int setLightSensorCurve(int i, int i2, int i3);

    public abstract void setLightSensorIndex(int i);

    public abstract boolean setLocalDimmingCheck(int i);

    public abstract int setLocalDimmingState(int i);

    public abstract int setLocalDimmingSwitch(boolean z);

    public abstract void setLocalKeypadLog();

    public abstract void setLocaldimmingEnable(int i);

    public abstract void setLogToFile(String str);

    public abstract void setLogo(int i);

    public abstract boolean setLogoIndex(int i);

    public abstract int setLvdsSscDisableOnce(int i);

    public abstract boolean setMGKKey(String str);

    public abstract boolean setMacAddress(String str);

    public abstract void setMacKeysStatusToLog();

    public abstract boolean setMajorMaintenance();

    public abstract boolean setMarlinKey(String str);

    public abstract void setMaxx3DOption(int i);

    public abstract void setMaxxBassFrequency(int i);

    public abstract void setMaxxTrebleFrequency(int i);

    public abstract void setMicDb(int i);

    public abstract void setMicDevice(int i);

    public abstract void setMicRecordTime(int i);

    public abstract void setMicTestResult(int i);

    public abstract boolean setMinorMaintenance();

    public abstract boolean setMirrorValue(int i);

    public abstract void setModeValue(int i);

    public abstract void setModelPanel(FacPropertyDemo.ModelPanelGroup modelPanelGroup);

    public abstract void setModelSelection(int i);

    public abstract void setMtkSourceStatus(boolean z);

    public abstract int setMultiBalance(int i);

    public abstract int setMultiDigitalAMPValueByDevId(int i, int i2, int i3);

    public abstract int setMuteColor(int i);

    public abstract void setNewMachineID(String str);

    public abstract void setNoSignalStandby(int i);

    public abstract void setNonstandardDescramble(int i);

    public abstract void setNonstandardDescrambleplus(int i);

    public abstract void setNvodProgramDisplay(int i);

    public abstract void setOSDCFlag(boolean z);

    public abstract void setOSDLanguage(FacPropertyDemo.OSDLanguage oSDLanguage);

    public abstract boolean setOledCpcValue(int i);

    public abstract boolean setOledHdrValue(int i);

    public abstract boolean setOledLbaValue(int i);

    public abstract boolean setOledLbc2Value(int i);

    public abstract boolean setOledLeaValue(int i);

    public abstract boolean setOledOrbitValue(int i);

    public abstract boolean setOledPixelShiftShapeValue(int i);

    public abstract boolean setOledPixelShiftTimerValue(int i);

    public abstract boolean setOledPixelShiftTypeValue(int i);

    public abstract boolean setOledTpcValue(int i);

    public abstract boolean setOpenMirror(boolean z);

    public abstract boolean setOttSrc(int i);

    public abstract void setOverScanBottom(int i);

    public abstract void setOverScanLeft(int i);

    public abstract void setOverScanRight(int i);

    public abstract void setOverScanTop(int i);

    public abstract boolean setPVRKey(String str);

    public abstract void setPWMFreq(FacPropertyDemo.BACKLIGHTCURVE_POINT backlightcurve_point, int i);

    public abstract void setPiCurveBrigth20(int i);

    public abstract void setPiCurveBrigth80(int i);

    public abstract void setPiCurveBrigthMax(int i);

    public abstract void setPiCurveBrigthMid(int i);

    public abstract void setPiCurveBrigthMin(int i);

    public abstract void setPiCurveContract20(int i);

    public abstract void setPiCurveContract80(int i);

    public abstract void setPiCurveContractMax(int i);

    public abstract void setPiCurveContractMid(int i);

    public abstract void setPiCurveContractMin(int i);

    public abstract void setPiCurveHue20(int i);

    public abstract void setPiCurveHue80(int i);

    public abstract void setPiCurveHueMax(int i);

    public abstract void setPiCurveHueMid(int i);

    public abstract void setPiCurveHueMin(int i);

    public abstract void setPiCurveSaturation20(int i);

    public abstract void setPiCurveSaturation80(int i);

    public abstract void setPiCurveSaturationMax(int i);

    public abstract void setPiCurveSaturationMid(int i);

    public abstract void setPiCurveSaturationMin(int i);

    public abstract void setPiCurveSharpness20(int i);

    public abstract void setPiCurveSharpness80(int i);

    public abstract void setPiCurveSharpnessMax(int i);

    public abstract void setPiCurveSharpnessMid(int i);

    public abstract void setPiCurveSharpnessMin(int i);

    public abstract void setPictureCurve0(int i);

    public abstract void setPictureCurve100(int i);

    public abstract void setPictureCurve25(int i);

    public abstract void setPictureCurve50(int i);

    public abstract void setPictureCurve75(int i);

    public abstract void setPictureMode(FacPropertyDemo.PictureMode pictureMode);

    public abstract void setPictureModeTW(FacPropertyDemo.PictureModeTW_Toshiba pictureModeTW_Toshiba);

    public abstract boolean setPlayready25Key(String str);

    public abstract boolean setPlayready30Key(String str);

    public abstract boolean setPlayready40Key(String str);

    public abstract void setPowerOnMode(FacPropertyDemo.PowerModeEnum powerModeEnum);

    public abstract void setPqBypassEnable(int i);

    public abstract void setPrescale(int i);

    public abstract boolean setProductionKey(String str);

    public void setProgramNumDisplay(int i) {
    }

    public abstract int setProjectid(String str);

    public abstract void setQ1(int i);

    public abstract void setQ2(int i);

    public abstract void setQ3(int i);

    public abstract void setQ4(int i);

    public abstract void setQ5(int i);

    public abstract void setQ6(int i);

    public abstract void setQ7(int i);

    public abstract void setQ8(int i);

    public abstract boolean setQDAKey(String str);

    public abstract void setRCUT(int i);

    public abstract void setRCUTOffset(int i);

    public abstract void setRDRV(int i);

    public abstract void setRDRVOffset(int i);

    public abstract void setRegion(int i);

    public abstract boolean setRegionTilValue(int i);

    public abstract void setReleaseTimeAutoVolumeControl(int i);

    public abstract void setSAMarketFacCheckFlag();

    public abstract boolean setSQVKey(String str);

    public abstract void setSSCDDREnable(int i);

    public abstract void setSSCDDRModu(int i);

    public abstract void setSSCDDRSpan(int i);

    public abstract void setSSCVByOneEnable(int i);

    public abstract void setSSCVByOneModu(int i);

    public abstract void setSSCVByOneRange(int i);

    public abstract void setSSCVByOneSpan(int i);

    public abstract void setSTEP1(int i);

    public abstract void setSTEP2(int i);

    public abstract void setSTEP3(int i);

    public abstract void setSTEP4(int i);

    public abstract void setSTEP5(int i);

    public abstract void setSTEP6(int i);

    public abstract void setSTEP7(int i);

    public abstract void setSTEP8(int i);

    public abstract void setSavingMode(FacPropertyDemo.SavingMode savingMode);

    public abstract void setScreen(int i);

    public abstract boolean setSensorEnable(boolean z);

    public abstract boolean setServiceTag(String str);

    public abstract void setSharpness(int i);

    public abstract boolean setSocDrc(int i);

    public abstract void setSoftwareVersionLog();

    public abstract boolean setSportMode(boolean z);

    public abstract void setTVSLog(String str);

    public abstract int setTVSSavingMode();

    public abstract int setTVSSavingMode(int i);

    public abstract void setTemperatureProtection(int i);

    public abstract int setTestPattern(int i, int i2, int i3, int i4);

    public abstract void setThresholdAutoVolumeControl(int i);

    public abstract void setToFac(FacPropertyDemo.ToFacEnum toFacEnum);

    public abstract void setTunerMode(int i);

    public abstract void setTunerModeForEM(int i);

    public abstract void setTunerModeforATSC(int i);

    public abstract void setTvsTvTuner(int i);

    public abstract int setTwelveVoltsStatus(int i);

    public abstract void setUSBOverCurrent();

    public abstract void setUartDebugEnable();

    public abstract void setUartSwitch(int i);

    public abstract int setUnivMultiBalance(int i);

    public abstract void setUpgradeFlag(int i);

    public abstract void setUserAgingSwitch(int i);

    public void setUserOrderSet(int i) {
    }

    public abstract void setVStart(int i);

    public abstract boolean setVcomCardParam(String str, int i);

    public abstract boolean setVcomPanelType(int i);

    public abstract boolean setVcomVoltage(int i);

    public abstract boolean setVcomVoltage2(int i, int i2);

    public abstract void setVdDspVersion(int i);

    public abstract void setVideoCurveModeGroup(FacPropertyDemo.VideoCurveModeGroup videoCurveModeGroup);

    public abstract void setVifAgcRef(int i);

    public abstract void setVifAsiaSignalOption(int i);

    public abstract void setVifClampGainOvNegative(int i);

    public abstract void setVifCrKi(int i);

    public abstract void setVifCrKp(int i);

    public abstract void setVifCrKpKiAdjust(int i);

    public abstract void setVifCrThr(int i);

    public abstract void setVifOverModulation(int i);

    public abstract void setVifTop(int i);

    public abstract void setVifVersion(int i);

    public abstract void setVifVgaMaximum(int i);

    public abstract void setVolume(int i);

    public abstract void setVolumeCurve0(int i);

    public abstract void setVolumeCurve100(int i);

    public abstract void setVolumeCurve25(int i);

    public abstract void setVolumeCurve50(int i);

    public abstract void setVolumeCurve75(int i);

    public abstract boolean setVolumeMute(boolean z);

    public abstract void setWB20PointBlue(int i);

    public abstract void setWB20PointGreen(int i);

    public abstract void setWB20PointLevel(int i);

    public abstract void setWB20PointRed(int i);

    public abstract boolean setWBPattern(int i);

    public abstract boolean setWideVineKey(String str);

    public abstract void setWooferAudioDRC(int i);

    public abstract void setWooferAudioOutputPort(int i);

    public abstract void setWooferAudioPaGain(int i);

    public abstract void setZDMicResult(int i);

    public abstract void setZoomMode(FacPropertyDemo.ZoomMode zoomMode);

    public abstract void setZoomModeForEM(FacPropertyDemo.ZoomModeForEM zoomModeForEM);

    public abstract void setnetworktype(int i);

    public abstract void startWifiScan();

    public abstract void switchDTVrouteToDtmb();

    public abstract void switchSource(FacPropertyDemo.SourceEnum sourceEnum);

    public abstract boolean switchSourceByName(String str);

    public abstract void syncFs();

    public abstract void updateFacLogStatus(int i);

    public abstract void updateSscDefaultValueForTemp();

    public abstract void upgradeBacklightDriver();

    public abstract void upgradeFRC();

    public abstract void writeBooleanSetting(String str, boolean z);

    public abstract int writeFile(String str, String str2, boolean z, boolean z2);

    public abstract void writeToExternalControl(int i, byte[] bArr, int i2);

    /* JADX INFO: renamed from: com.hisense.tvcm.FacFunction$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum;

        static {
            int[] iArr = new int[FacPropertyDemo.SourceEnum.values().length];
            $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum = iArr;
            try {
                iArr[FacPropertyDemo.SourceEnum.VGA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.COMPONENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.ATV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.DTV.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI1.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI3.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.HDMI4.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[FacPropertyDemo.SourceEnum.DMP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public FacPropertyDemo.ADCColorSpaceGroup getCurrentADCColorSpaceGroup() {
        int i = AnonymousClass1.$SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[getCurrentSource().ordinal()];
        if (i == 1) {
            return FacPropertyDemo.ADCColorSpaceGroup.VGA;
        }
        if (i == 2) {
            if (getCurrentOverScanTiming().ordinal() >= FacPropertyDemo.OVERSCAN_TIMING.EN_FACTORY_OverScan_YPBPR480I.ordinal() && getCurrentOverScanTiming().ordinal() <= FacPropertyDemo.OVERSCAN_TIMING.EN_FACTORY_OverScan_YPBPR576P.ordinal()) {
                return FacPropertyDemo.ADCColorSpaceGroup.YPBPR_SD;
            }
            return FacPropertyDemo.ADCColorSpaceGroup.YPBPR_HD;
        }
        return FacPropertyDemo.ADCColorSpaceGroup.YPBPR_SD;
    }

    public FacPropertyDemo.VideoSourceGroup getCurrentVideoSourceGroup() {
        switch (AnonymousClass1.$SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[getCurrentSource().ordinal()]) {
            case 1:
                return FacPropertyDemo.VideoSourceGroup.VGA;
            case 2:
                return FacPropertyDemo.VideoSourceGroup.YPBPR;
            case 3:
                return FacPropertyDemo.VideoSourceGroup.ATV;
            case 4:
                return FacPropertyDemo.VideoSourceGroup.DTV;
            case 5:
            case 6:
            case 7:
            case 8:
                return FacPropertyDemo.VideoSourceGroup.HDMI;
            case 9:
                return FacPropertyDemo.VideoSourceGroup.DMP;
            default:
                return FacPropertyDemo.VideoSourceGroup.AV;
        }
    }

    public FacPropertyDemo.WhiteBalanceSourceGroup getCurrentWhiteBalanceSourceGroup() {
        switch (AnonymousClass1.$SwitchMap$com$hisense$tvcm$FacPropertyDemo$SourceEnum[getCurrentSource().ordinal()]) {
            case 1:
                return FacPropertyDemo.WhiteBalanceSourceGroup.RGB;
            case 2:
                return FacPropertyDemo.WhiteBalanceSourceGroup.YUV;
            case 3:
            default:
                return FacPropertyDemo.WhiteBalanceSourceGroup.CVBS;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return FacPropertyDemo.WhiteBalanceSourceGroup.DTV;
            case 9:
                return FacPropertyDemo.WhiteBalanceSourceGroup.DMP;
        }
    }
}
