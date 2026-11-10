package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAudioApdBase {
    public static final int AUDIO_APD_STATUS_READY = 0;
    public static final int AUDIO_APD_STATUS_TRANSMIT = 1;
    public static final int AUDIO_APD_STATUS_UNKNOWN = 2;
    public static final String TAG = "TV_MtkTvAudioApdBase";
    public int mAduioApdFound;
    public String mAudioApdLang;
    public int mAudioApdStatus;
    public String mAudioApdText;
    public int mAudioApdTextLen;

    public String getAudioApdLang() {
        return this.mAudioApdLang;
    }

    public int getAudioApdStatus() {
        return this.mAudioApdStatus;
    }

    public String getAudioApdText() {
        return this.mAudioApdText;
    }

    public int getAudioApdTextLen() {
        return this.mAudioApdTextLen;
    }

    public int getAudioApdfound() {
        return this.mAduioApdFound;
    }

    public void setAudioApdLang(String str) {
        this.mAudioApdLang = str;
    }

    public void setAudioApdStatus(int i) {
        this.mAudioApdStatus = i;
    }

    public void setAudioApdText(String str) {
        this.mAudioApdText = str;
    }

    public void setAudioApdTextLen(int i) {
        this.mAudioApdTextLen = i;
    }

    public void setAudioApdfound(int i) {
        this.mAduioApdFound = i;
    }
}
