package com.google.android.exoplayer.util;

/* JADX INFO: loaded from: classes.dex */
public class AacUtil {
    public static int getAacSyncframeAudioSampleCount(int i) {
        return (i == 5 || i == 29) ? 2048 : 1024;
    }
}
