package com.google.android.exoplayer.util;

/* JADX INFO: loaded from: classes.dex */
public class MpeghUtil {
    public static int getMpeghSyncframeAudioSampleCount(int i) {
        if (i == 16000) {
            return 3072;
        }
        if (i != 24000) {
            return i != 32000 ? 1024 : 1536;
        }
        return 2048;
    }
}
