package com.hsp.autogen.framework.vt_atsc_tvserver.play;

import android.util.Log;
import android.view.Surface;
import com.hsp.rod.Archive;
import com.hsp.rod.HSPBuffer;
import com.hsp.rod.Rod;

/* JADX INFO: loaded from: classes.dex */
public class PlayManager {
    public static final String TAG = "PlayManager";

    public static int setSurfaceToSupply(Surface surface) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setSurfaceToSupply", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i = archive2.getInt();
        archive2.release();
        return i;
    }

    public static int startPlay(String str, int i, int i2) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(str);
        archive.put(i);
        archive.put(i2);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "startPlay", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i3 = archive2.getInt();
        archive2.release();
        return i3;
    }

    public static int stopPlay(String str) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(str);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "stopPlay", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i = archive2.getInt();
        archive2.release();
        return i;
    }

    public static String getCurrentPlayChannel() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getCurrentPlayChannel", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        String string = archive.getString();
        archive.release();
        return string;
    }

    public static int setCurrentChannel(String str) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(str);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setCurrentChannel", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i = archive2.getInt();
        archive2.release();
        return i;
    }

    public static String getCurrentChannelWithTunerMode(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "getCurrentChannelWithTunerMode", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        String string = archive2.getString();
        archive2.release();
        return string;
    }
}
