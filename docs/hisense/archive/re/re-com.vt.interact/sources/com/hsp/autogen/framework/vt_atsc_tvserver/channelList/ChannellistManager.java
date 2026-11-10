package com.hsp.autogen.framework.vt_atsc_tvserver.channelList;

import android.util.Log;
import com.hsp.autogen.include.IHisTvServiceChannelInfoBase;
import com.hsp.rod.Archive;
import com.hsp.rod.HSPBuffer;
import com.hsp.rod.Rod;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ChannellistManager {
    public static final String TAG = "ChannellistManager";

    public static IHisTvServiceChannelInfoBase getChannelInfoByUUID(String str) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(str);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "getChannelInfoByUUID", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = new IHisTvServiceChannelInfoBase();
        iHisTvServiceChannelInfoBase.loadFrom(hSPBufferCall);
        archive2.release();
        return iHisTvServiceChannelInfoBase;
    }

    public static ArrayList<IHisTvServiceChannelInfoBase> getChannelListByFreIndex(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "getChannelListByFreIndex", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        ArrayList<IHisTvServiceChannelInfoBase> arrayList = new ArrayList<>();
        int i2 = archive2.getInt();
        for (int i3 = 0; i3 < i2; i3++) {
            IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = new IHisTvServiceChannelInfoBase();
            iHisTvServiceChannelInfoBase.Deserialize(archive2);
            arrayList.add(iHisTvServiceChannelInfoBase);
        }
        archive2.release();
        return arrayList;
    }

    public static ArrayList<IHisTvServiceChannelInfoBase> getChannelListByTunerMode(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "getChannelListByTunerMode", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        ArrayList<IHisTvServiceChannelInfoBase> arrayList = new ArrayList<>();
        int i2 = archive2.getInt();
        for (int i3 = 0; i3 < i2; i3++) {
            IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = new IHisTvServiceChannelInfoBase();
            iHisTvServiceChannelInfoBase.Deserialize(archive2);
            arrayList.add(iHisTvServiceChannelInfoBase);
        }
        archive2.release();
        return arrayList;
    }

    public static int cleanChannelListByTunerMode(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "cleanChannelListByTunerMode", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int setChannelList(ArrayList<IHisTvServiceChannelInfoBase> arrayList) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            IHisTvServiceChannelInfoBase iHisTvServiceChannelInfoBase = arrayList.get(i);
            HSPBuffer hSPBuffer2 = new HSPBuffer();
            iHisTvServiceChannelInfoBase.saveTo(hSPBuffer2);
            archive.put(hSPBuffer2);
        }
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setChannelList", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }
}
