package com.hsp.autogen.framework.vt_atsc_tvserver.scan;

import android.util.Log;
import com.hsp.rod.Archive;
import com.hsp.rod.HSPBuffer;
import com.hsp.rod.Rod;

/* JADX INFO: loaded from: classes.dex */
public class ScanManager {
    public static final String TAG = "ScanManager";

    public static int setScanSource(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setScanSource", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int startManualScan(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "startManualScan", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int startAutoScan() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "startAutoScan", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int cancelScan() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "cancelScan", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int isScanRunning() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "isScanRunning", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }
}
