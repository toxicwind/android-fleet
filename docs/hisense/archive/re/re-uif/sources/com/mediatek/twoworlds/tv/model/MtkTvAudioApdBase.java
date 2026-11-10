package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvAudioApdBase {
    public static final int AUDIO_APD_STATUS_READY = 0;
    public static final int AUDIO_APD_STATUS_TRANSMIT = 1;
    public static final int AUDIO_APD_STATUS_UNKNOWN = 2;
    public static final String TAG = "TV_MtkTvAudioApdBase";
    private int mAduioApdFound;
    private String mAudioApdLang;
    private int mAudioApdStatus;
    private String mAudioApdText;
    private int mAudioApdTextLen;

    public int getAudioApdfound() {
        return this.mAduioApdFound;
    }

    public void setAudioApdfound(int i) {
        this.mAduioApdFound = i;
    }

    public String getAudioApdLang() {
        return this.mAudioApdLang;
    }

    public void setAudioApdLang(String str) {
        this.mAudioApdLang = str;
    }

    public int getAudioApdTextLen() {
        return this.mAudioApdTextLen;
    }

    public void setAudioApdTextLen(int i) {
        this.mAudioApdTextLen = i;
    }

    public String getAudioApdText() {
        return this.mAudioApdText;
    }

    public void setAudioApdText(String str) {
        this.mAudioApdText = str;
    }

    public int getAudioApdStatus() {
        return this.mAudioApdStatus;
    }

    public void setAudioApdStatus(int i) {
        this.mAudioApdStatus = i;
    }
}
