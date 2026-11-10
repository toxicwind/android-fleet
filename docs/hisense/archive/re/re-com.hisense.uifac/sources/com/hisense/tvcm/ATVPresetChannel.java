package com.hisense.tvcm;

/* JADX INFO: loaded from: classes.dex */
public class ATVPresetChannel {
    private String channelName;
    private int channelNum;
    private int frequency;
    private AudioSys mAudioSys;
    private ColorSys mColorSys;

    public enum AudioSys {
        AUTO,
        DK,
        I,
        BG,
        M
    }

    public enum ColorSys {
        AUTO,
        PAL,
        SECAM,
        NTSC,
        PAL_M,
        PAL_N
    }

    public ATVPresetChannel(int i, String str, int i2, ColorSys colorSys, AudioSys audioSys) {
        this.channelNum = i;
        this.channelName = str;
        this.frequency = i2;
        this.mColorSys = colorSys;
        this.mAudioSys = audioSys;
    }

    public AudioSys getAudioSys() {
        return this.mAudioSys;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public int getChannelNum() {
        return this.channelNum;
    }

    public ColorSys getColorSys() {
        return this.mColorSys;
    }

    public int getFrequency() {
        return this.frequency;
    }
}
