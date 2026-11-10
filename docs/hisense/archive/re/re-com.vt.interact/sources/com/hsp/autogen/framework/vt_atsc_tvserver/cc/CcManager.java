package com.hsp.autogen.framework.vt_atsc_tvserver.cc;

import android.util.Log;
import com.hsp.rod.Archive;
import com.hsp.rod.HSPBuffer;
import com.hsp.rod.Rod;

/* JADX INFO: loaded from: classes.dex */
public class CcManager {
    public static final String TAG = "CcManager";

    public static int isCCExist() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "isCCExist", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int setCCState(int i, int i2) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.put(i2);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setCCState", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i3 = archive2.getInt();
        archive2.release();
        return i3;
    }

    public static int getCCState(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "getCCState", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int[] getTotalCCLanguage() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getTotalCCLanguage", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int[] intArray = archive.getIntArray();
        archive.release();
        return intArray;
    }
}
