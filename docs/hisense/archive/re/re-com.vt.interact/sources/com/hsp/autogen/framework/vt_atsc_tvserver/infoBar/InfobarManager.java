package com.hsp.autogen.framework.vt_atsc_tvserver.infoBar;

import android.util.Log;
import com.hsp.autogen.include.HisInfoBarChannelInfoBase;
import com.hsp.autogen.include.HisInfoBarProgramInfoBase;
import com.hsp.autogen.include.HisInfoBarTunerInfoBase;
import com.hsp.rod.Archive;
import com.hsp.rod.HSPBuffer;
import com.hsp.rod.Rod;

/* JADX INFO: loaded from: classes.dex */
public class InfobarManager {
    public static final String TAG = "InfobarManager";

    public static HisInfoBarChannelInfoBase getInfoBarChannelInfo() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getInfoBarChannelInfo", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        HisInfoBarChannelInfoBase hisInfoBarChannelInfoBase = new HisInfoBarChannelInfoBase();
        hisInfoBarChannelInfoBase.loadFrom(hSPBufferCall);
        archive.release();
        return hisInfoBarChannelInfoBase;
    }

    public static HisInfoBarProgramInfoBase getInfoBarProgramEvent() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getInfoBarProgramEvent", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        HisInfoBarProgramInfoBase hisInfoBarProgramInfoBase = new HisInfoBarProgramInfoBase();
        hisInfoBarProgramInfoBase.loadFrom(hSPBufferCall);
        archive.release();
        return hisInfoBarProgramInfoBase;
    }

    public static HisInfoBarTunerInfoBase getInfoBarTunerInfo() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getInfoBarTunerInfo", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        HisInfoBarTunerInfoBase hisInfoBarTunerInfoBase = new HisInfoBarTunerInfoBase();
        hisInfoBarTunerInfoBase.loadFrom(hSPBufferCall);
        archive.release();
        return hisInfoBarTunerInfoBase;
    }

    public static int getInfoBarAVStatus() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getInfoBarAVStatus", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }
}
