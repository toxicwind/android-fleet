package com.hsp.autogen.framework.vt_atsc_tvserver.audioTrack;

import android.util.Log;
import com.hsp.autogen.include.HisTvServiceAudioTrackBase;
import com.hsp.rod.Archive;
import com.hsp.rod.HSPBuffer;
import com.hsp.rod.Rod;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AudiotrackManager {
    public static final String TAG = "AudiotrackManager";

    public static ArrayList<HisTvServiceAudioTrackBase> getAudioTrackInfoList() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getAudioTrackInfoList", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        ArrayList<HisTvServiceAudioTrackBase> arrayList = new ArrayList<>();
        int i = archive.getInt();
        for (int i2 = 0; i2 < i; i2++) {
            HisTvServiceAudioTrackBase hisTvServiceAudioTrackBase = new HisTvServiceAudioTrackBase();
            hisTvServiceAudioTrackBase.Deserialize(archive);
            arrayList.add(hisTvServiceAudioTrackBase);
        }
        archive.release();
        return arrayList;
    }

    public static int getCurrentAudioTrackId() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getCurrentAudioTrackId", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int selectAudioByTrackId(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "selectAudioByTrackId", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int setPrimaryAudioLanguage(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setPrimaryAudioLanguage", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int getPrimaryAudioLanguage() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getPrimaryAudioLanguage", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int setSecondaryAudioLanguage(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setSecondaryAudioLanguage", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int getSecondaryAudioLanguage() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getSecondaryAudioLanguage", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int setAudioDescriptionEnable(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setAudioDescriptionEnable", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int getAudioDescriptionEnable() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getAudioDescriptionEnable", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int setAudioDescriptionVolume(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setAudioDescriptionVolume", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int getAudioDescriptionVolume() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getAudioDescriptionVolume", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }

    public static int setAtvMtsOutputMode(int i) {
        HSPBuffer hSPBuffer = new HSPBuffer();
        Archive archive = new Archive();
        archive.put(i);
        archive.copyTo(hSPBuffer);
        HSPBuffer hSPBufferCall = Rod.call(3, "setAtvMtsOutputMode", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        archive.release();
        Archive archive2 = new Archive(hSPBufferCall);
        int i2 = archive2.getInt();
        archive2.release();
        return i2;
    }

    public static int getAtvMtsOutputMode() {
        HSPBuffer hSPBuffer = new HSPBuffer();
        new Archive();
        HSPBuffer hSPBufferCall = Rod.call(3, "getAtvMtsOutputMode", hSPBuffer);
        Log.d(TAG, "hsp retcode:" + hSPBufferCall.getRetCode());
        Archive archive = new Archive(hSPBufferCall);
        int i = archive.getInt();
        archive.release();
        return i;
    }
}
