package com.hisense.tvmanager.atsc;

import android.util.Log;
import java.util.ArrayList;
import vendor.hisense.tv.hs_tv_service.V1_0.IHisTvService;
import vendor.hisense.tv.hs_tv_service.V1_0.IHisTvServiceChannelInfoBase;

/* JADX INFO: loaded from: classes.dex */
public class ChannelList {
    private static ChannelList mChannelList;
    private static IHisTvService mService;

    public static boolean getHidlService() {
        try {
            IHisTvService service = IHisTvService.getService();
            mService = service;
            return service != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private ChannelList() {
    }

    public static ChannelList getInstance() {
        if (mChannelList == null) {
            synchronized (ChannelList.class) {
                if (mChannelList == null) {
                    mChannelList = new ChannelList();
                }
            }
        }
        return mChannelList;
    }

    public ChannelInfoBase getChannelInfoByUUID(String str) {
        IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = new IHisTvServiceChannelInfoBase();
        ChannelInfoBase channelInfoBase = new ChannelInfoBase();
        if (getHidlService()) {
            try {
                iHisTvServiceChannelInfoBase = mService.getChannelInfoByUUID(str);
            } catch (Exception e) {
                Log.e("ChannelList", "getChannelInfoByUUID exception: " + e);
                return null;
            }
        }
        channelInfoBase.setServiceUUID(iHisTvServiceChannelInfoBase.uuid);
        channelInfoBase.setServiceId(iHisTvServiceChannelInfoBase.serviceId);
        channelInfoBase.setBroadcastType(iHisTvServiceChannelInfoBase.broadcastType);
        channelInfoBase.setServiceType(iHisTvServiceChannelInfoBase.serviceType);
        channelInfoBase.setChannelNumber(iHisTvServiceChannelInfoBase.channelNum);
        channelInfoBase.setServiceName(iHisTvServiceChannelInfoBase.serviceName);
        channelInfoBase.setFrequencyHz(iHisTvServiceChannelInfoBase.frequency);
        channelInfoBase.setPhysicalChannelIndex(iHisTvServiceChannelInfoBase.physicalChannelIndex);
        channelInfoBase.setModulation(iHisTvServiceChannelInfoBase.modulation);
        channelInfoBase.setAttrValue(iHisTvServiceChannelInfoBase.attrValue);
        channelInfoBase.setChannelInfoAvailable(iHisTvServiceChannelInfoBase.isChannelInfoAvailable != 0);
        return channelInfoBase;
    }

    public ArrayList<ChannelInfoBase> getChannelListByTunerMode(int i) {
        ArrayList<IHisTvServiceChannelInfoBase> arrayList = new ArrayList<>();
        ArrayList<ChannelInfoBase> arrayList2 = new ArrayList<>();
        if (getHidlService()) {
            try {
                arrayList = mService.getChannelListByTunerMode(i);
            } catch (Exception e) {
                Log.e("ChannelList", "getChannelListByTunerMode exception: " + e);
                return null;
            }
        }
        Log.i("ChannelList", " infoBaseOut.size()= " + arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ChannelInfoBase channelInfoBase = new ChannelInfoBase();
            channelInfoBase.setServiceUUID(arrayList.get(i2).uuid);
            channelInfoBase.setServiceId(arrayList.get(i2).serviceId);
            channelInfoBase.setBroadcastType(arrayList.get(i2).broadcastType);
            channelInfoBase.setServiceType(arrayList.get(i2).serviceType);
            channelInfoBase.setChannelNumber(arrayList.get(i2).channelNum);
            channelInfoBase.setServiceName(arrayList.get(i2).serviceName);
            channelInfoBase.setFrequencyHz(arrayList.get(i2).frequency);
            channelInfoBase.setPhysicalChannelIndex(arrayList.get(i2).physicalChannelIndex);
            channelInfoBase.setModulation(arrayList.get(i2).modulation);
            channelInfoBase.setAttrValue(arrayList.get(i2).attrValue);
            channelInfoBase.setChannelInfoAvailable(arrayList.get(i2).isChannelInfoAvailable != 0);
            channelInfoBase.setChannelEditMask(0);
            arrayList2.add(i2, channelInfoBase);
        }
        return arrayList2;
    }

    public void cleanChannelListByTunerMode(int i) {
        if (getHidlService()) {
            try {
                mService.cleanChannelListByTunerMode(i);
            } catch (Exception e) {
                Log.e("ChannelList", "cleanChannelListByTunerMode exception: " + e);
            }
        }
    }
}
