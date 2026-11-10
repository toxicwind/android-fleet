package com.hisense.uiadapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.hisense.tvcm.ATVPresetChannel;
import com.hisense.tvcm.DTVPresetChannel;
import com.hisense.tvcm.FacFunction;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.util.IMtkFunCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class TVFunction {
    private static FacFunction mFacFunction;
    private static TVFunction mTVFunction;
    private final HashMap<String, List<ATVPresetChannel>> mATVHashMap = new HashMap<>();
    private final HashMap<String, List<DTVPresetChannel>> mDTVHashMap = new HashMap<>();

    public static boolean isMute() {
        return mFacFunction.isVolumeMute();
    }

    public static TVFunction getInstance(Context context) {
        if (mTVFunction == null) {
            mTVFunction = new TVFunction(FacPropertyDemo.HIFAC_VENDOR, context);
        }
        return mTVFunction;
    }

    public static void setMute(boolean z) {
        mFacFunction.setVolumeMute(z);
    }

    public TVFunction(String str, Context context) {
        context.getApplicationContext();
        mFacFunction = TVComponent.createFacFunction(str);
        initHashMap();
    }

    public boolean autoADCCalibration() {
        return mFacFunction.adcCalibration();
    }

    public void changeNextInputSource() {
        String currentInputSourceName = getCurrentInputSourceName();
        FacPropertyDemo.SourceEnum sourceEnum = FacPropertyDemo.SourceEnum.ATV;
        List<FacPropertyDemo.SourceEnum> shownSourceList = FacPropertyDemo.SourceEnum.getShownSourceList();
        for (int i = 0; i < shownSourceList.size(); i++) {
            if (shownSourceList.get(i).name().equals(currentInputSourceName)) {
                if (i < shownSourceList.size() - 1) {
                    sourceEnum = shownSourceList.get(i + 1);
                } else {
                    sourceEnum = shownSourceList.get(0);
                }
            }
        }
        mFacFunction.switchSource(sourceEnum);
    }

    public void changeSource(FacPropertyDemo.SourceEnum sourceEnum) {
        try {
            mFacFunction.switchSource(sourceEnum);
        } catch (IllegalArgumentException unused) {
            Log.i("Fac_TVFunction", "source " + sourceEnum.toString() + "not exist!!");
        }
    }

    public void channelDown() {
        mFacFunction.channelDown();
    }

    public void channelUp() {
        mFacFunction.channelUp();
    }

    public void clearProtectlyInFactory() {
        mFacFunction.executeClearProtectly();
    }

    public void clearUnprotectlyInFactory() {
        mFacFunction.executeClearUnprotectly();
    }

    public void dataInitInFactory(Handler handler) {
        mFacFunction.executeDataInit(handler);
    }

    public void executeWhenHisFacEnding() {
        System.out.print("HisFac end!!!!!!!!!!");
        mFacFunction.execWhenHisFacEnding();
    }

    public void factoryOk(IMtkFunCallback iMtkFunCallback) {
        FacFunction facFunction = mFacFunction;
        if (facFunction instanceof FacFunction_Adapter) {
            facFunction.executeFactoryOk(iMtkFunCallback);
        }
    }

    public void doUsbUpgrade(Context context) {
        mFacFunction.doUsbUpgrade(context);
    }

    public boolean is3DSupported() {
        return mFacFunction.is3DSupported();
    }

    public int get2Dto3D() {
        return mFacFunction.get3DMode() == FacPropertyDemo.ThreeDimensionMode.MODE_2DTO3D ? 1 : 0;
    }

    public String getAppointedMacFileName() {
        return mFacFunction.getMacFileNameInUsbDisk();
    }

    public String getCurrentChannelNumberString() {
        return mFacFunction.getCurrentChannelNumberString();
    }

    public String getCurrentInputSourceName() {
        return mFacFunction.getCurrentSource().toString();
    }

    public String getCurrentInputSource() {
        return mFacFunction.getCurrentSource().toString();
    }

    public String getDefaultMacAddr() {
        return mFacFunction.getDefaultMacAddress();
    }

    public String getChipID() {
        return mFacFunction.getChipID();
    }

    public String getDeviceID() {
        return mFacFunction.getDeviceID();
    }

    public String getChinaDrmKey() {
        return mFacFunction.getChinaDrmKey();
    }

    public String getHdcpKey() {
        return mFacFunction.getHdcpKey();
    }

    public String getHdcp14Key() {
        return mFacFunction.getHdcp14Key();
    }

    public String getHdcp2xKey() {
        return mFacFunction.getHdcp2xKey();
    }

    public String getCIPlusKey() {
        return mFacFunction.getCIPlusKey();
    }

    public boolean isKeyWriteProtecEnabled() {
        return mFacFunction.isKeyWriteProtecEnabled();
    }

    public String[] getInputSourceArray() {
        Vector vector = new Vector();
        for (FacPropertyDemo.SourceEnum sourceEnum : FacPropertyDemo.SourceEnum.values()) {
            Log.i("Fac_TVFunction", "getInputSourceArray aSourceEnum = " + sourceEnum.toString());
            if (sourceEnum.isEnabled() && sourceEnum.isShown()) {
                vector.add(sourceEnum.getDisplayName());
            }
        }
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    public String getIPAddr() {
        return mFacFunction.getIPAddress();
    }

    public String getMacAddress() {
        return mFacFunction.getMacAddress();
    }

    public String getBTMacAddress() {
        return mFacFunction.getBTMacAddress();
    }

    public String getNewMachineIDentity() {
        return mFacFunction.getNewMachineID();
    }

    public String getOrignalMachineIDentity() {
        return mFacFunction.getOrignalMachineID();
    }

    public String getProductTypeName() {
        return mFacFunction.getProductType();
    }

    public String getVersionInfomation() {
        return mFacFunction.getVersionInfo();
    }

    public void initHashMap() {
        initATVChannelPreset();
        initDTVChannelPreset();
    }

    public boolean isCurrentSourceATV() {
        return mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.ATV);
    }

    public boolean isCurrentSourceTV() {
        return mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.TV);
    }

    public boolean isCurrentSourceAV1() {
        return mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.AV) || mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.AV1);
    }

    public boolean isCurrentSourceDTV() {
        return mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.DTV);
    }

    public boolean isCurrentSourceHDMI1() {
        return mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.HDMI1);
    }

    public boolean isCurrentSourceHDMI2() {
        return mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.HDMI2);
    }

    public boolean isCurrentSourceHDMI3() {
        return mFacFunction.getCurrentSource().equals(FacPropertyDemo.SourceEnum.HDMI3);
    }

    public boolean isCurrentSourceComponent() {
        return mFacFunction.getCurrentSource() == FacPropertyDemo.SourceEnum.COMPONENT;
    }

    public boolean isCurrentSourceVGA() {
        return mFacFunction.getCurrentSource() == FacPropertyDemo.SourceEnum.VGA;
    }

    public void presetATVChannel(String str) {
        FacFunction facFunction = mFacFunction;
        if (facFunction instanceof FacFunction_Adapter) {
            ((FacFunction_Adapter) facFunction).addTVPresetChannelForcityname(str);
            return;
        }
        Iterator<ATVPresetChannel> it = this.mATVHashMap.get(str).iterator();
        while (it.hasNext()) {
            mFacFunction.addATVPresetChannel(it.next());
        }
        mFacFunction.setATVPresetChannel();
    }

    public void presetDTVChannel(String str, Handler handler) {
        FacFunction facFunction = mFacFunction;
        if (facFunction instanceof FacFunction_Adapter) {
            ((FacFunction_Adapter) facFunction).addTVPresetChannelForcityname(str, handler);
            return;
        }
        Iterator<DTVPresetChannel> it = this.mDTVHashMap.get(str).iterator();
        while (it.hasNext()) {
            mFacFunction.addDTVPresetChannel(it.next());
        }
        mFacFunction.setDTVPresetChannel();
    }

    public void presetATVChannel(String str, Handler handler) {
        FacFunction facFunction = mFacFunction;
        if (facFunction instanceof FacFunction_Adapter) {
            ((FacFunction_Adapter) facFunction).addTVPresetChannelForcityname(str, handler);
            return;
        }
        Iterator<ATVPresetChannel> it = this.mATVHashMap.get(str).iterator();
        while (it.hasNext()) {
            mFacFunction.addATVPresetChannel(it.next());
        }
        mFacFunction.setATVPresetChannel();
    }

    public void presetDTVChannel(String str) {
        FacFunction facFunction = mFacFunction;
        if (facFunction instanceof FacFunction_Adapter) {
            ((FacFunction_Adapter) facFunction).addTVPresetChannelForcityname(str);
            return;
        }
        Iterator<DTVPresetChannel> it = this.mDTVHashMap.get(str).iterator();
        while (it.hasNext()) {
            mFacFunction.addDTVPresetChannel(it.next());
        }
        mFacFunction.setDTVPresetChannel();
    }

    public void selectChannel(int i) {
        mFacFunction.changeChannel(i);
    }

    public void set2Dto3D(int i) {
        if (i == 1) {
            mFacFunction.set3DMode(FacPropertyDemo.ThreeDimensionMode.MODE_2DTO3D);
        } else {
            mFacFunction.set3DMode(FacPropertyDemo.ThreeDimensionMode.OFF);
        }
    }

    public boolean setMacAddress(String str) {
        return mFacFunction.setMacAddress(str);
    }

    public boolean setCountryIndex(int i) {
        return mFacFunction.setCountryIndex(i);
    }

    public boolean setLogoIndex(int i) {
        return mFacFunction.setLogoIndex(i);
    }

    public boolean setLangIndex(int i) {
        return mFacFunction.setLangIndex(i);
    }

    public boolean setWideVineKey(String str) {
        return mFacFunction.setWideVineKey(str);
    }

    public boolean setProductionKey(String str) {
        return mFacFunction.setProductionKey(str);
    }

    public boolean setChinaDrmKey(String str) {
        return mFacFunction.setChinaDrmKey(str);
    }

    public boolean setHdcp14Key(String str) {
        return mFacFunction.setHdcp14Key(str);
    }

    public boolean setHdcp2xKey(String str) {
        return mFacFunction.setHdcp2xKey(str);
    }

    public boolean setCIPlusKey(String str) {
        return mFacFunction.setCIPlusKey(str);
    }

    public boolean setHdcpKey(String str) {
        return mFacFunction.setHdcp14Key(str);
    }

    public String getPlayReadyKey() {
        return mFacFunction.getPlayReadyKey();
    }

    public boolean getProductionKey() {
        return mFacFunction.getProductionKey();
    }

    public boolean setMGKKey(String str) {
        return mFacFunction.setMGKKey(str);
    }

    public String getMGKKey() {
        return mFacFunction.getMGKKey();
    }

    public String getQDAKey() {
        return mFacFunction.getQDAKey();
    }

    public int checkTconTempVr() {
        return mFacFunction.checkTconTempVr();
    }

    public String getTconTempVersion() {
        return mFacFunction.getTconTempVersion();
    }

    public boolean setQDAKey(String str) {
        return mFacFunction.setQDAKey(str);
    }

    public void setNewMachineIDentity(String str) {
        mFacFunction.setNewMachineID(str);
    }

    private void initATVChannelPreset() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ATVPresetChannel(1, "C02", 57750000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList.add(new ATVPresetChannel(2, "C05", 85250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList.add(new ATVPresetChannel(3, "C08", 184250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList.add(new ATVPresetChannel(4, "C12", 215250000, ATVPresetChannel.ColorSys.SECAM, ATVPresetChannel.AudioSys.DK));
        arrayList.add(new ATVPresetChannel(5, "E08", 196250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.BG));
        arrayList.add(new ATVPresetChannel(6, "A-CC", 313250000, ATVPresetChannel.ColorSys.NTSC, ATVPresetChannel.AudioSys.M));
        arrayList.add(new ATVPresetChannel(7, "PZ35", 440250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList.add(new ATVPresetChannel(8, "UK49", 695250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.I));
        arrayList.add(new ATVPresetChannel(9, "C48", 791250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList.add(new ATVPresetChannel(10, "C56", 855250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        this.mATVHashMap.put("qingdao", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new ATVPresetChannel(1, "PC01", 49750000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList2.add(new ATVPresetChannel(2, "PZ01", 112250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList2.add(new ATVPresetChannel(3, "PC08", 184250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList2.add(new ATVPresetChannel(4, "SD11", 215250000, ATVPresetChannel.ColorSys.SECAM, ATVPresetChannel.AudioSys.DK));
        arrayList2.add(new ATVPresetChannel(5, "PB12", 224250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.BG));
        arrayList2.add(new ATVPresetChannel(6, "A-CC", 313250000, ATVPresetChannel.ColorSys.NTSC, ATVPresetChannel.AudioSys.M));
        arrayList2.add(new ATVPresetChannel(7, "PZ35", 440250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList2.add(new ATVPresetChannel(8, "PC25", 607250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList2.add(new ATVPresetChannel(9, "PI49", 695250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.I));
        arrayList2.add(new ATVPresetChannel(10, "PC56", 855250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        this.mATVHashMap.put("huangdao", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new ATVPresetChannel(1, "C01", 49750000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList3.add(new ATVPresetChannel(2, "C05", 85250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList3.add(new ATVPresetChannel(3, "Z07", 160250000, ATVPresetChannel.ColorSys.NTSC, ATVPresetChannel.AudioSys.M));
        arrayList3.add(new ATVPresetChannel(4, "C08", 184250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList3.add(new ATVPresetChannel(5, "C10", 200250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList3.add(new ATVPresetChannel(6, "C12", 216250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList3.add(new ATVPresetChannel(7, "Z16", 288250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.I));
        arrayList3.add(new ATVPresetChannel(8, "C25", 607250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList3.add(new ATVPresetChannel(9, "C57", 863250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        this.mATVHashMap.put("guiyang", arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new ATVPresetChannel(1, "PC01", 49750000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList4.add(new ATVPresetChannel(2, "NM06", 83250000, ATVPresetChannel.ColorSys.NTSC, ATVPresetChannel.AudioSys.M));
        arrayList4.add(new ATVPresetChannel(3, "PC08", 184250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList4.add(new ATVPresetChannel(4, "PB08", 196250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.BG));
        arrayList4.add(new ATVPresetChannel(5, "NM12", 205250000, ATVPresetChannel.ColorSys.NTSC, ATVPresetChannel.AudioSys.M));
        arrayList4.add(new ATVPresetChannel(6, "SD11", 215250000, ATVPresetChannel.ColorSys.SECAM, ATVPresetChannel.AudioSys.DK));
        arrayList4.add(new ATVPresetChannel(7, "PB12", 224250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList4.add(new ATVPresetChannel(8, "PZ35", 440250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList4.add(new ATVPresetChannel(9, "PI34", 575250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.I));
        arrayList4.add(new ATVPresetChannel(10, "PI49", 711250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.I));
        arrayList4.add(new ATVPresetChannel(11, "PG57", 759250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        arrayList4.add(new ATVPresetChannel(12, "NM65", 777250000, ATVPresetChannel.ColorSys.NTSC, ATVPresetChannel.AudioSys.M));
        arrayList4.add(new ATVPresetChannel(13, "PC56", 855250000, ATVPresetChannel.ColorSys.PAL, ATVPresetChannel.AudioSys.DK));
        this.mATVHashMap.put("shunde", arrayList4);
    }

    private void initDTVChannelPreset() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DTVPresetChannel(1, "DC04 1080i", 129000000, 3, 12309, 9020, 45116, 45180, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList.add(new DTVPresetChannel(2, "DC04 480p", 129000000, 3, 12309, 9020, 45116, 45244, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList.add(new DTVPresetChannel(3, "DC04 576i", 129000000, 3, 12309, 9020, 45116, 45308, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList.add(new DTVPresetChannel(4, "DC04 576p", 129000000, 3, 12309, 9020, 45116, 45372, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList.add(new DTVPresetChannel(5, "DC04 480i", 129000000, 3, 12309, 9020, 45116, 47164, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList.add(new DTVPresetChannel(6, "DC40", 458000000, 3, 2000, 2000, 1999, 1, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        this.mDTVHashMap.put("qingdao", arrayList);
        this.mDTVHashMap.put("huangdao", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new DTVPresetChannel(1, "DC04 1080i", 129000000, 3, 12309, 9020, 45116, 45180, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList2.add(new DTVPresetChannel(2, "DC04 480p", 129000000, 3, 12309, 9020, 45116, 45244, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList2.add(new DTVPresetChannel(3, "DC04 576i", 129000000, 3, 12309, 9020, 45116, 45308, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList2.add(new DTVPresetChannel(4, "DC04 576p", 129000000, 3, 12309, 9020, 45116, 45372, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList2.add(new DTVPresetChannel(5, "DC04 480i", 129000000, 3, 12309, 9020, 45116, 47164, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList2.add(new DTVPresetChannel(6, "DC40", 458000000, 3, 2000, 2000, 1999, 1, 6900, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        this.mDTVHashMap.put("shunde", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new DTVPresetChannel(1, "DC04 1080i", 129000000, 3, 12309, 9020, 45116, 45180, 6875, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList3.add(new DTVPresetChannel(2, "DC04 480p", 129000000, 3, 12309, 9020, 45116, 45244, 6875, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList3.add(new DTVPresetChannel(3, "DC04 576i", 129000000, 3, 12309, 9020, 45116, 45308, 6875, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList3.add(new DTVPresetChannel(4, "DC04 576p", 129000000, 3, 12309, 9020, 45116, 45372, 6875, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        arrayList3.add(new DTVPresetChannel(5, "DC04 480i", 129000000, 3, 12309, 9020, 45116, 47164, 6875, DTVPresetChannel.ModulationMode.MOD_QAM_64));
        this.mDTVHashMap.put("guiyang", arrayList3);
        this.mDTVHashMap.put("HIFAC_LINYI", arrayList);
    }

    public String getVideoInfoOfCurrentSource() {
        return mFacFunction.getVideoInfo();
    }

    public boolean setVcomVoltage(int i) {
        return mFacFunction.setVcomVoltage(i);
    }

    public boolean saveVcomVoltage(int i) {
        return mFacFunction.saveVcomVoltage(i);
    }

    public int getVcomVoltage() {
        return mFacFunction.getVcomVoltage();
    }

    public void upgradeFRC() {
        mFacFunction.upgradeFRC();
    }

    public String getFRCVersion() {
        return mFacFunction.getFRCVersion();
    }

    public String getTconVersion() {
        return mFacFunction.getTconVersion();
    }

    public String getTypeCVersion() {
        return mFacFunction.getTypeCVersion();
    }

    public String getWideKey() {
        return mFacFunction.getWideKey();
    }

    public void upgradeBacklightDriver() {
        mFacFunction.upgradeBacklightDriver();
    }

    public String getBacklightDriverVersion() {
        return mFacFunction.getBacklightDriverVersion();
    }

    public String getLCMRunningStatus() {
        return mFacFunction.getLCMRunningStatus();
    }

    public void switchSource(FacPropertyDemo.SourceEnum sourceEnum) {
        mFacFunction.switchSource(sourceEnum);
    }

    public void setWBPattern(int i) {
        mFacFunction.setWBPattern(i);
    }

    public void pauseTv(boolean z) {
        mFacFunction.pauseTv(z);
    }

    public boolean setFirstTimePowerOn(int i) {
        return mFacFunction.setFirstTimePowerOn(i);
    }

    public FacPropertyDemo.SourceEnum getCurrentInputSourceEnum() {
        return mFacFunction.getCurrentSource();
    }

    public int getMicDevice() {
        return mFacFunction.getMicDevice();
    }

    public void setMicDevice(int i) {
        mFacFunction.setMicDevice(i);
    }

    public void setUartDebugEnable() {
        mFacFunction.setUartDebugEnable();
    }

    public long getDataTime() {
        return mFacFunction.getDataTime();
    }

    public String getDataVersion() {
        return mFacFunction.getDataVersion();
    }

    public boolean isShutdownBroadcastFromFactory() {
        return mFacFunction.isShutdownBroadcastFromFactory();
    }

    public String getTvScreenVersion() {
        return mFacFunction.getTvScreenVersion();
    }

    public String getTvChipVersion() {
        return mFacFunction.getTvChipVersion();
    }

    public boolean setVcomCardParam(String str, int i) {
        return mFacFunction.setVcomCardParam(str, i);
    }

    public int closeVcomCardParam() {
        return mFacFunction.closeVcomCardParam();
    }

    public boolean setBackLightOn(boolean z) {
        return mFacFunction.setBackLightOn(z);
    }

    public int setLocalDimmingSwitch(boolean z) {
        return mFacFunction.setLocalDimmingSwitch(z);
    }

    public int setLocalDimmingState(int i) {
        return mFacFunction.setLocalDimmingState(i);
    }

    public int getLocalDimmingState() {
        return mFacFunction.getLocalDimmingState();
    }

    public void clearKeys() {
        mFacFunction.clearKeys();
    }

    public boolean clearHdcp14Key() {
        return mFacFunction.clearHdcp14Key();
    }

    public boolean clearHdcp22Key() {
        return mFacFunction.clearHdcp22Key();
    }

    public boolean clearWideVineKey() {
        return mFacFunction.clearWideVineKey();
    }

    public boolean clearPlayReadyKey() {
        return mFacFunction.clearPlayReadyKey();
    }

    public boolean isWooferModeOn() {
        return mFacFunction.isWooferModeOn();
    }

    public int[] getFansSpeed() {
        return mFacFunction.getFansSpeed();
    }

    public int[] getBoardTemperature() {
        return mFacFunction.getBoardTemperature();
    }

    public int[] getLightSensorValues() {
        return mFacFunction.getLightSensorValues();
    }

    public int getAmbientLightValue() {
        return mFacFunction.getAmbientLightValue();
    }

    public int getLightSensorOptionState() {
        return mFacFunction.getLightSensorOptionState();
    }

    public int getCWValue1() {
        return mFacFunction.getCWValue1();
    }

    public int getCWValue2() {
        return mFacFunction.getCWValue2();
    }

    public boolean setCWValue1(int i) {
        return mFacFunction.setCWValue1(i);
    }

    public boolean setCWValue2(int i) {
        return mFacFunction.setCWValue2(i);
    }

    public int getMirrorValue() {
        return mFacFunction.getMirrorValue();
    }

    public String getTVRunningTime() {
        return mFacFunction.getTVRunningTime();
    }

    public boolean setOpenMirror(boolean z) {
        return mFacFunction.setOpenMirror(z);
    }

    public boolean setFilterChangeTime() {
        return mFacFunction.setFilterChangeTime();
    }

    public boolean setMirrorValue(int i) {
        return mFacFunction.setMirrorValue(i);
    }

    public int getSensorLuxValue() {
        return mFacFunction.getSensorLuxValue();
    }

    public String getMcuVersionInfo() {
        return mFacFunction.getMcuVersionInfo();
    }

    public String getDlpMcuVersion() {
        return mFacFunction.getDlpMcuVersion();
    }

    public String getSoundMcuVersion() {
        return mFacFunction.getSoundMcuVersion();
    }

    public String getLedEffectVersion() {
        return mFacFunction.getLedEffectVersion();
    }

    public String getFPGAVersion() {
        return mFacFunction.getFPGAVersion();
    }

    public int getTunerMode() {
        return mFacFunction.getTunerMode();
    }

    public void setTunerMode(int i) {
        mFacFunction.setTunerMode(i);
    }

    public String getCurrentInputSourceDisplayName() {
        return mFacFunction.getCurrentSource().getDisplayName();
    }

    public FacPropertyDemo.Logo getLogoEnum() {
        return mFacFunction.getLogoEnum();
    }

    public String getLogoForEMSAEnum() {
        return mFacFunction.getLogoForEMSAEnum().getName();
    }

    public String getFullLogoName() {
        return mFacFunction.getFullLogoName();
    }

    public String getCountryName() {
        return mFacFunction.getCountryName();
    }

    public String getLogoName() {
        return mFacFunction.getLogoName();
    }

    public String getlanguageName() {
        return mFacFunction.getlanguageName();
    }

    public boolean setGamma(int[] iArr) {
        return mFacFunction.setGamma(iArr);
    }

    public boolean closeGamma(int[] iArr) {
        return mFacFunction.closeGamma(iArr);
    }

    public boolean resetGamma(int i) {
        return mFacFunction.resetGamma(i);
    }

    public int setFactoryProtectFlag(int i) {
        return mFacFunction.setFactoryProtectFlag(i);
    }

    public int getFactoryProtectFlag() {
        return mFacFunction.getFactoryProtectFlag();
    }

    public int getUpgradeFlag() {
        return mFacFunction.getUpgradeFlag();
    }

    public void setUpgradeFlag(int i) {
        mFacFunction.setUpgradeFlag(i);
    }

    public void setTunerModeForEM(int i) {
        mFacFunction.setTunerModeForEM(i);
    }

    public void changeChannel(int i, int i2) {
        mFacFunction.changeChannel(i, i2);
    }

    public boolean setRegionTilValue(int i) {
        return mFacFunction.setRegionTilValue(i);
    }

    public int getRegion() {
        return mFacFunction.getRegion();
    }

    public int isRegionX(int i) {
        return mFacFunction.isRegionX(i);
    }

    public String getBrand() {
        return mFacFunction.getBrand();
    }

    public boolean setBrand(String str) {
        return mFacFunction.setBrand(str);
    }

    public boolean setPlayready30Key(String str) {
        return mFacFunction.setPlayready30Key(str);
    }

    public boolean setPlayready25Key(String str) {
        return mFacFunction.setPlayready25Key(str);
    }

    public boolean setPlayready40Key(String str) {
        return mFacFunction.setPlayready40Key(str);
    }

    public String getPlayready25Key() {
        return mFacFunction.getPlayReady25Key();
    }

    public String getPlayready40Key() {
        return mFacFunction.getPlayReady40Key();
    }

    public boolean setAttestationKey(String str) {
        return mFacFunction.setAttestationKey(str);
    }

    public String getAttestationKey() {
        return mFacFunction.getAttestationKey();
    }

    public String getDtcpIpKey() {
        return mFacFunction.getDtcpIpKey();
    }

    public String getMarlinKey() {
        return mFacFunction.getMarlinKey();
    }

    public String getSeeQVaultKey() {
        return mFacFunction.getSeeQVaultKey();
    }

    public String getPVRKey() {
        return mFacFunction.getPVRKey();
    }

    public int removeFile(String str) {
        File file = new File(str);
        Log.d("Fac_tvfunction", "removeFile path = " + file.toString());
        return (file.exists() && !file.delete()) ? 1 : 0;
    }

    public int writeFile(String str, String str2, boolean z, boolean z2) {
        return mFacFunction.writeFile(str, str2, z, z2);
    }

    public int setProjectid(String str) {
        return mFacFunction.setProjectid(str);
    }

    public String getProjectId() {
        return mFacFunction.getProjectId();
    }

    public int[] getVcomsValue() {
        return mFacFunction.getVcomsValue();
    }

    public boolean setDTSSwitch(int i) {
        return mFacFunction.setDTSSwitch(i);
    }

    public boolean setOttSrc(int i) {
        return mFacFunction.setOttSrc(i);
    }

    public void syncFs() {
        mFacFunction.syncFs();
    }

    public void setLogToFile(String str) {
        mFacFunction.setLogToFile(str);
    }

    public String readLog() {
        return mFacFunction.readLog();
    }

    public String getCurrentSourceName() {
        return mFacFunction.getCurrentSourceName();
    }

    public int setTestPattern(int i, int i2, int i3, int i4) {
        return mFacFunction.setTestPattern(i, i2, i3, i4);
    }

    public String getWifiMacAddress() {
        return mFacFunction.getWifiMacAddress();
    }

    public boolean isFrcSupport() {
        return mFacFunction.isFrcSupport();
    }

    public boolean isTconSupport() {
        return mFacFunction.isTconSupport();
    }

    public boolean isBluSupport() {
        return mFacFunction.isBluSupport();
    }

    public int getMicSwitchStatus() {
        return mFacFunction.getMicSwitchStatus();
    }

    public int getMicTestResult() {
        return mFacFunction.getMicTestResult();
    }

    public void readDriverInfo() {
        mFacFunction.readDriverInfo();
    }

    public String getProjectIdFileName() {
        return mFacFunction.getProjectIdFileNameInUsbDisk();
    }

    public void setOSDCFlag(boolean z) {
        mFacFunction.setOSDCFlag(z);
    }

    public int getZDMicTestResult() {
        return mFacFunction.getZDMicResult();
    }

    public int getLogoValueForSA(String str) {
        return mFacFunction.getLogoValueForSA(str);
    }

    public String getLogoFullNameForSA() {
        return mFacFunction.getLogoFullNameForSA();
    }

    public String getLogoFullNameMon() {
        return mFacFunction.getLogoFullNameMon();
    }

    public String getClientType() {
        return mFacFunction.getClientType();
    }

    public boolean setClientType(String str) {
        return mFacFunction.setClientType(str);
    }

    public void addTVPresetChannelForSA(int i) {
        mFacFunction.addTVPresetChannelForSA(i);
    }

    public int getFacLogStatus() {
        return mFacFunction.getFacLogStatus();
    }

    public void setFacLogStatus(int i) {
        mFacFunction.setFacLogStatus(i);
    }

    public void generateEepKeyFile() {
        mFacFunction.generateEepKeyFile();
    }

    public void updateFacLogStatus(int i) {
        mFacFunction.updateFacLogStatus(i);
    }

    public boolean isBrandIsolate() {
        return mFacFunction.isBrandIsolate();
    }

    public String[] geBrandIsolateList() {
        return mFacFunction.geBrandIsolateList();
    }

    public boolean setChannelInitFileFromUdisk(String str) {
        return mFacFunction.setChannelInitFileFromUdisk(str);
    }

    public void updateSscDefaultValueForTemp() {
        mFacFunction.updateSscDefaultValueForTemp();
    }

    public String get5GChannel() {
        return mFacFunction.get5GChannel();
    }

    public int getVcomVoltage2(int i) {
        return mFacFunction.getVcomVoltage2(i);
    }

    public boolean saveVcomVoltage2(int i, int i2) {
        return mFacFunction.saveVcomVoltage2(i, i2);
    }

    public boolean setVcomVoltage2(int i, int i2) {
        return mFacFunction.setVcomVoltage2(i, i2);
    }

    public boolean setVcomPanelType(int i) {
        return mFacFunction.setVcomPanelType(i);
    }

    public boolean isHaveTuner() {
        return mFacFunction.isHaveTuner();
    }

    public String getSlaveBacklightDriverVersion() {
        return mFacFunction.getSlaveBacklightDriverVersion();
    }

    public String getSlaveLCMRunningStatus() {
        return mFacFunction.getSlaveLCMRunningStatus();
    }

    public boolean isDualBluSupport() {
        return mFacFunction.isDualBluSupport();
    }

    public String getDolbyIQUserBinInfo() {
        return mFacFunction.getDolbyIQUserBinInfo();
    }

    public boolean setDTCPIPKey(String str) {
        return mFacFunction.setDTCPIPKey(str);
    }

    public boolean setMarlinKey(String str) {
        return mFacFunction.setMarlinKey(str);
    }

    public boolean setSQVKey(String str) {
        return mFacFunction.setSQVKey(str);
    }

    public boolean setPVRKey(String str) {
        return mFacFunction.setPVRKey(str);
    }

    public String getAutoGammaStatus() {
        return mFacFunction.getAutoGammaStatus();
    }

    public void setTvsTvTuner(int i) {
        mFacFunction.setTvsTvTuner(i);
    }

    public String getF1Result() {
        return mFacFunction.getF1Result();
    }

    public void setUSBOverCurrent() {
        mFacFunction.setUSBOverCurrent();
    }

    public String getLogoFullNameForJPN() {
        return "tos".equalsIgnoreCase(mFacFunction.getLogoName()) ? "Toshiba" : "Hisense";
    }

    public String getACASId() {
        return mFacFunction.getACASId();
    }

    public String getHDDSize() {
        return mFacFunction.getHDDSize();
    }

    public int getACASState() {
        return mFacFunction.getACASState();
    }

    public int getSubWooferTotal() {
        return mFacFunction.getSubWooferTotal();
    }

    public void setTVSLog(String str) {
        mFacFunction.setTVSLog(str);
    }

    public boolean clearOMRunningTime() {
        return mFacFunction.clearOMRunningTime();
    }

    public boolean setLaserAutoGraphCorrection(int[] iArr) {
        return mFacFunction.setLaserAutoGraphCorrection(iArr);
    }

    public boolean resetGraphCorrection() {
        return mFacFunction.resetGraphCorrection();
    }

    public int getDistanceSensorStrengthValue() {
        return mFacFunction.getDistanceSensorStrengthValue();
    }

    public int getBackgroundNoiseValue() {
        return mFacFunction.getBackgroundNoiseValue();
    }

    public String getLaserRuningState() {
        return mFacFunction.getLaserRuningState();
    }

    public String getLaserRuningTime() {
        return mFacFunction.getLaserRuningTime();
    }

    public String getFirstPowerOnTime() {
        return mFacFunction.getFirstPowerOnTime();
    }

    public String getStartOnCount() {
        return mFacFunction.getStartOnCount();
    }

    public String getTvsLightSensor() {
        return mFacFunction.getTvsLightSensor();
    }

    public int getLastSource() {
        return mFacFunction.getLastSource();
    }

    public int setBackgroundNoiseValue(int i) {
        return mFacFunction.setBackgroundNoiseValue(i);
    }

    public int getBackgroundNoisePredeterminedValue() {
        return mFacFunction.getBackgroundNoisePredeterminedValue();
    }

    public float getBackgroundNoiseCorrectionFactorValue() {
        return mFacFunction.getBackgroundNoiseCorrectionFactorValue();
    }

    public int setTVSSavingMode() {
        return mFacFunction.setTVSSavingMode();
    }

    public void setMtkSourceStatus(boolean z) {
        mFacFunction.setMtkSourceStatus(z);
    }

    public int getTvsCurrentChannelNumber() {
        return mFacFunction.getTvsCurrentChannelNumber();
    }

    public String getTvsCurrentChannelTuner() {
        return mFacFunction.getTvsCurrentChannelTuner();
    }

    public boolean isHaveHDD() {
        return mFacFunction.isHaveHDD();
    }

    public boolean setMajorMaintenance() {
        return mFacFunction.setMajorMaintenance();
    }

    public boolean setMinorMaintenance() {
        return mFacFunction.setMinorMaintenance();
    }

    public int getJBExecuteCount() {
        return mFacFunction.getJBExecuteCount();
    }

    public int getOffrsExecuteCount() {
        return mFacFunction.getOffrsExecuteCount();
    }

    public String getJBExecuteTime() {
        return mFacFunction.getJBExecuteTime();
    }

    public String getOffrsExecuteTime() {
        return mFacFunction.getOffrsExecuteTime();
    }

    public int getErrDetCount() {
        return mFacFunction.getErrDetCount();
    }

    public void setModelSelection(int i) {
        mFacFunction.setModelSelection(i);
    }

    public int setMuteColor(int i) {
        return mFacFunction.setMuteColor(i);
    }

    public boolean isFileExit(String str) {
        return mFacFunction.isFileExit(str);
    }

    public int setTwelveVoltsStatus(int i) {
        return mFacFunction.setTwelveVoltsStatus(i);
    }

    public int initInnertconBoard() {
        return mFacFunction.initInnertconBoard();
    }

    public String getQDAUUID() {
        return mFacFunction.getQDAUUID();
    }

    public String getLogoFullName() {
        return mFacFunction.getLogoFullName();
    }

    public boolean cleanEepKeyFile() {
        return mFacFunction.cleanEepKeyFile();
    }

    public int getTvsTPC() {
        return mFacFunction.getTvsTPC();
    }

    public boolean getEmmcType() {
        return mFacFunction.getEmmcType();
    }

    public boolean setServiceTag(String str) {
        return mFacFunction.setServiceTag(str);
    }

    public String getServiceTag() {
        return mFacFunction.getServiceTag();
    }

    public int getFacCheckLogStatus() {
        return mFacFunction.getFacCheckLogStatus();
    }

    public int getUnivMultiBalance(int i) {
        return mFacFunction.getUnivMultiBalance(i);
    }
}
