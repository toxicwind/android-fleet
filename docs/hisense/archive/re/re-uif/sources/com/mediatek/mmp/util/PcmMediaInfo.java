package com.mediatek.mmp.util;

/* JADX INFO: loaded from: classes.dex */
public class PcmMediaInfo {
    public long size;

    public class AudioPcmInfo {
        public int bitsPerSample;
        public short blockAlign;
        public int channelNumber;
        public int sampleRate;
    }

    public abstract AudioPcmInfo getAudioPcmInfo();
}
