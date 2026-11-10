package com.hisense.dtvservice;

import android.util.Log;
import java.util.ArrayList;
import vendor.hisense.dtv.dtvservice.V1_0.IChannelInfo;
import vendor.hisense.dtv.dtvservice.V1_0.IHisChannelListApi;

/* JADX INFO: loaded from: classes.dex */
public class HisChannelListApiImpl {
    private static HisChannelListApiImpl mChannelListApiImpl;
    private static IHisChannelListApi mChannelListServer;

    private HisChannelListApiImpl() {
    }

    public static HisChannelListApiImpl getInstance() {
        if (mChannelListApiImpl == null) {
            synchronized (HisChannelListApiImpl.class) {
                if (mChannelListApiImpl == null) {
                    mChannelListApiImpl = new HisChannelListApiImpl();
                }
            }
        }
        return mChannelListApiImpl;
    }

    public static boolean getHidlService() {
        try {
            IHisChannelListApi service = IHisChannelListApi.getService();
            mChannelListServer = service;
            return service != null;
        } catch (Exception e) {
            Log.e("HISDTV@ChannelListApiImpl_java", "get channel list HIDL Service exception" + e);
            return false;
        }
    }

    public int addPresetChannel(int i) {
        int iAddPresetChannel = -1;
        if (getHidlService()) {
            try {
                iAddPresetChannel = mChannelListServer.addPresetChannel(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@ChannelListApiImpl_java", "call startPlay ret = " + iAddPresetChannel);
        } else {
            Log.d("HISDTV@ChannelListApiImpl_java", "getHidlService is false, call startPlay failed");
        }
        return iAddPresetChannel;
    }

    public int importChannelFromUSB(String str, boolean z) {
        int iImportChannelFromUSB = -1;
        if (getHidlService()) {
            try {
                iImportChannelFromUSB = mChannelListServer.importChannelFromUSB(str, z);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@ChannelListApiImpl_java", "call importChannelFromUSB ret = " + iImportChannelFromUSB);
        } else {
            Log.d("HISDTV@ChannelListApiImpl_java", "getHidlService is false, call importChannelFromUSB failed");
        }
        return iImportChannelFromUSB;
    }

    public int cleanChannelsByTunerMode(int i) {
        int iCleanChannelsByTunerMode = -1;
        if (getHidlService()) {
            try {
                Log.d("HISDTV@ChannelListApiImpl_java", "before call cleanChannelsByTunerMode ret = -1");
                iCleanChannelsByTunerMode = mChannelListServer.cleanChannelsByTunerMode(i);
                Log.d("HISDTV@ChannelListApiImpl_java", "after call cleanChannelsByTunerMode ret = " + iCleanChannelsByTunerMode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@ChannelListApiImpl_java", "call cleanChannelsByTunerMode ret = " + iCleanChannelsByTunerMode);
        } else {
            Log.d("HISDTV@ChannelListApiImpl_java", "getHidlService is false, call cleanChannelsByTunerMode failed");
        }
        return iCleanChannelsByTunerMode;
    }

    public int flushChannelList() {
        int iFlushChannelList = -1;
        if (getHidlService()) {
            try {
                iFlushChannelList = mChannelListServer.flushChannelList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@ChannelListApiImpl_java", "call flushChannelList ret = " + iFlushChannelList);
        } else {
            Log.d("HISDTV@ChannelListApiImpl_java", "getHidlService is false, call flushChannelList failed");
        }
        return iFlushChannelList;
    }

    public ChannelInfoBase getChannelInfoByChannelKey(String str) {
        IChannelInfo iChannelInfo = new IChannelInfo();
        ChannelInfoBase channelInfoBase = new ChannelInfoBase();
        if (getHidlService()) {
            try {
                iChannelInfo = mChannelListServer.getChannelInfoByChannelKey(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("HISDTV@ChannelListApiImpl_java", "call getChannelInfoByChannelKey ret = " + iChannelInfo);
        } else {
            Log.d("HISDTV@ChannelListApiImpl_java", "getHidlService is false, call getChannelInfoByChannelKey failed");
        }
        if (iChannelInfo != null) {
            channelInfoBase.setAudioSys(iChannelInfo.audioSys);
            channelInfoBase.setBrdcstMedium(iChannelInfo.brdcstMedium);
            channelInfoBase.setBrdcstType(iChannelInfo.brdcstType);
            channelInfoBase.setCentralFrequency(iChannelInfo.centralFrequency);
            channelInfoBase.setChannelId(iChannelInfo.channelId);
            channelInfoBase.setChannelKey(iChannelInfo.channelKey);
            channelInfoBase.setChannelNum(iChannelInfo.channelNum);
            channelInfoBase.setColorSys(iChannelInfo.colorSys);
            channelInfoBase.setEditMask(iChannelInfo.editMask);
            channelInfoBase.setFrequency(iChannelInfo.frequency);
            channelInfoBase.setModulationType(iChannelInfo.modulationType);
            channelInfoBase.setOriginalNetworkId(iChannelInfo.originalNetworkId);
            channelInfoBase.setServiceMask(iChannelInfo.serviceMask);
            channelInfoBase.setServiceName(iChannelInfo.serviceName);
            channelInfoBase.setServiceType(iChannelInfo.serviceType);
            channelInfoBase.setTransportStreamId(iChannelInfo.transportStreamId);
            channelInfoBase.setTunerMode(iChannelInfo.tunerMode);
            channelInfoBase.setTvSys(iChannelInfo.tvSys);
        }
        return channelInfoBase;
    }

    public ArrayList<ChannelInfoBase> getChannelListByTunerMode(int i, boolean z) {
        ArrayList<IChannelInfo> arrayList = new ArrayList<>();
        ArrayList<ChannelInfoBase> arrayList2 = new ArrayList<>();
        if (getHidlService()) {
            try {
                Log.d("HISDTV@ChannelListApiImpl_java", "call getChannelListByTunerMode,isALL = " + z);
                arrayList = mChannelListServer.getChannelListByTunerMode(i, z);
            } catch (Exception e) {
                Log.d("HISDTV@ChannelListApiImpl_java", " getChannelListByTunerMode failed Exception");
                e.printStackTrace();
                return null;
            }
        } else {
            Log.d("HISDTV@ChannelListApiImpl_java", "getHidlService is false, call getChannelListByTunerMode failed");
        }
        int size = arrayList.size();
        Log.d("HISDTV@ChannelListApiImpl_java", "call getChannelListByTunerMode size = " + size);
        for (int i2 = 0; i2 < size; i2++) {
            ChannelInfoBase channelInfoBase = new ChannelInfoBase();
            channelInfoBase.setBrdcstMedium(arrayList.get(i2).brdcstMedium);
            channelInfoBase.setBrdcstType(arrayList.get(i2).brdcstType);
            channelInfoBase.setCentralFrequency(arrayList.get(i2).centralFrequency);
            channelInfoBase.setChannelId(arrayList.get(i2).channelId);
            channelInfoBase.setChannelKey(arrayList.get(i2).channelKey);
            channelInfoBase.setChannelNum(arrayList.get(i2).channelNum);
            channelInfoBase.setColorSys(arrayList.get(i2).colorSys);
            channelInfoBase.setFrequency(arrayList.get(i2).frequency);
            channelInfoBase.setEditMask(arrayList.get(i2).editMask);
            channelInfoBase.setModulationType(arrayList.get(i2).modulationType);
            channelInfoBase.setOriginalNetworkId(arrayList.get(i2).originalNetworkId);
            channelInfoBase.setServiceName(arrayList.get(i2).serviceName);
            channelInfoBase.setServiceType(arrayList.get(i2).serviceType);
            channelInfoBase.setTunerMode(arrayList.get(i2).tunerMode);
            channelInfoBase.setTransportStreamId(arrayList.get(i2).transportStreamId);
            channelInfoBase.setServiceMask(arrayList.get(i2).serviceMask);
            channelInfoBase.setTvSys(arrayList.get(i2).tvSys);
            channelInfoBase.setAudioSys(arrayList.get(i2).audioSys);
            arrayList2.add(i2, channelInfoBase);
        }
        Log.d("HISDTV@ChannelListApiImpl_java", "call getChannelListByTunerMode end ");
        return arrayList2;
    }
}
