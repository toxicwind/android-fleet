package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class TvProviderAudioTrackBase {
    public static final int AUD_DECODE_TYPE_AAC = 4;
    public static final int AUD_DECODE_TYPE_AC3 = 1;
    public static final int AUD_DECODE_TYPE_AC4 = 18;
    public static final int AUD_DECODE_TYPE_DTS_EXPRESS = 16;
    public static final int AUD_DECODE_TYPE_DTS_HS = 17;
    public static final int AUD_DECODE_TYPE_DTS_NORMAL = 15;
    public static final int AUD_DECODE_TYPE_EAC3 = 2;
    public static final int AUD_DECODE_TYPE_FLAC = 3;
    public static final int AUD_DECODE_TYPE_HEAAC = 5;
    public static final int AUD_DECODE_TYPE_HEAAC_V2 = 6;
    public static final int AUD_DECODE_TYPE_LPCM_ALAW = 7;
    public static final int AUD_DECODE_TYPE_LPCM_ULAW = 8;
    public static final int AUD_DECODE_TYPE_MPEG1_LAYER1 = 11;
    public static final int AUD_DECODE_TYPE_MPEG1_LAYER3 = 9;
    public static final int AUD_DECODE_TYPE_MPEG1_LAYR2 = 10;
    public static final int AUD_DECODE_TYPE_MPEG2_LAYER1 = 14;
    public static final int AUD_DECODE_TYPE_MPEG2_LAYER2 = 13;
    public static final int AUD_DECODE_TYPE_MPEG2_LAYER3 = 12;
    public static final int AUD_DECODE_TYPE_PCM = 19;
    public static final int AUD_DECODE_TYPE_UNKNOWN = 0;
    public static final int AUD_EDITORIAL_CLASS_HEARING_IMPAIRED_CLEAN = 3;
    public static final int AUD_EDITORIAL_CLASS_MAIN = 1;
    public static final int AUD_EDITORIAL_CLASS_RESERVED = 0;
    public static final int AUD_EDITORIAL_CLASS_VISUAL_IMPAIRED_AD = 2;
    public static final int AUD_EDITORIAL_CLASS_VISUAL_IMPAIRED_AD_AND_SPOKEN_SUBTITLE = 5;
    public static final int AUD_EDITORIAL_CLASS_VISUAL_IMPAIRED_SPOKEN_SUBTITLE = 4;
    public static final int AUD_ENC_AAC = 7;
    public static final int AUD_ENC_AC3 = 1;
    public static final int AUD_ENC_AC4 = 26;
    public static final int AUD_ENC_ALAC = 24;
    public static final int AUD_ENC_AMR = 21;
    public static final int AUD_ENC_APE = 23;
    public static final int AUD_ENC_AWB = 20;
    public static final int AUD_ENC_COOK = 15;
    public static final int AUD_ENC_DRA = 16;
    public static final int AUD_ENC_DTS = 6;
    public static final int AUD_ENC_EU_CANAL_PLUS = 8;
    public static final int AUD_ENC_E_AC3 = 12;
    public static final int AUD_ENC_FLAC = 22;
    public static final int AUD_ENC_FM_RADIO = 14;
    public static final int AUD_ENC_LPCM = 13;
    public static final int AUD_ENC_MPEG_1 = 2;
    public static final int AUD_ENC_MPEG_2 = 3;
    public static final int AUD_ENC_OPUS = 25;
    public static final int AUD_ENC_PCM = 4;
    public static final int AUD_ENC_TV_SYS = 5;
    public static final int AUD_ENC_UNKNOWN = 0;
    public static final int AUD_ENC_VORBIS = 17;
    public static final int AUD_ENC_WMA_LOSSLESS = 19;
    public static final int AUD_ENC_WMA_PRO = 18;
    public static final int AUD_ENC_WMA_V1 = 9;
    public static final int AUD_ENC_WMA_V2 = 10;
    public static final int AUD_ENC_WMA_V3 = 11;
    public static final int AUD_FMT_DUAL_MONO = 2;
    public static final int AUD_FMT_MONO = 1;
    public static final int AUD_FMT_STEREO = 3;
    public static final int AUD_FMT_SUBSTREAM = 5;
    public static final int AUD_FMT_TYPE_5_1 = 4;
    public static final int AUD_FMT_UNKNOWN = 0;
    public static final int AUD_MIX_TYPE_INDEPENDENT = 2;
    public static final int AUD_MIX_TYPE_SUPPLEMENTARY = 1;
    public static final int AUD_MIX_TYPE_UNKNOWN = 0;
    public static final int AUD_TYPE_CLEAN = 1;
    public static final int AUD_TYPE_COMMENTARY = 8;
    public static final int AUD_TYPE_COMPLETE_MAIN = 5;
    public static final int AUD_TYPE_DIALOGUE = 7;
    public static final int AUD_TYPE_EMERGENCY = 9;
    public static final int AUD_TYPE_HEARING_IMPAIRED = 2;
    public static final int AUD_TYPE_KARAOKE = 9;
    public static final int AUD_TYPE_MUSIC_AND_EFFECT = 6;
    public static final int AUD_TYPE_RESERVED = 4;
    public static final int AUD_TYPE_UNKNOWN = 0;
    public static final int AUD_TYPE_VISUAL_IMPAIRED = 3;
    public static final int AUD_TYPE_VOICE_OVER = 8;
    private static final String TAG = "TvProviderAudioTrack";
    private int mAudioAtmos;
    private int mAudioChannelCount;
    private int mAudioCompTag;
    private int mAudioDecodeType;
    private int mAudioDriverAutoSelect;
    private int mAudioEditorialClass;
    private int mAudioEncodeType;
    private int mAudioFormat;
    private int mAudioId;
    private int mAudioIndex;
    private int mAudioIsMixedWithAD;
    private String mAudioLanguage;
    private int mAudioMixType;
    private int mAudioMsgId;
    private int mAudioOptMask;
    private int mAudioPid;
    private int mAudioPreId;
    private int mAudioPreseIdx;
    private int mAudioSampleRate;
    private int mAudioTextLabel;
    private int mAudioType;

    public int getAudioChannelCount() {
        return this.mAudioChannelCount;
    }

    public void setAudioChannelCount(int i) {
        this.mAudioChannelCount = i;
    }

    public int getAudioSampleRate() {
        return this.mAudioSampleRate;
    }

    protected void setAudioSampleRate(int i) {
        this.mAudioSampleRate = i;
    }

    public int getAudioId() {
        return this.mAudioId;
    }

    public void setAudioId(int i) {
        this.mAudioId = i;
    }

    public int getAudioType() {
        return this.mAudioType;
    }

    public void setAudioType(int i) {
        this.mAudioType = i;
    }

    public int getAudioMixType() {
        return this.mAudioMixType;
    }

    public void setAudioMixType(int i) {
        this.mAudioMixType = i;
    }

    public int getAudioEditorialClass() {
        return this.mAudioEditorialClass;
    }

    public void setAudioEditorialClass(int i) {
        this.mAudioEditorialClass = i;
    }

    public int getAudioEncodeType() {
        return this.mAudioEncodeType;
    }

    public void setAudioEncodeType(int i) {
        this.mAudioEncodeType = i;
    }

    public int getAudioIsMixedWithAD() {
        return this.mAudioIsMixedWithAD;
    }

    public void setAudioIsMixedWithAD(int i) {
        this.mAudioIsMixedWithAD = i;
    }

    public int getAudioFormat() {
        return this.mAudioFormat;
    }

    public void setAudioFormat(int i) {
        this.mAudioFormat = i;
    }

    public int getAudioIndex() {
        return this.mAudioIndex;
    }

    public void setAudioIndex(int i) {
        this.mAudioIndex = i;
    }

    public int getAudioPid() {
        return this.mAudioPid;
    }

    public void setAudioPid(int i) {
        this.mAudioPid = i;
    }

    public int getAudioDecodeType() {
        return this.mAudioDecodeType;
    }

    public void setAudioDecodeType(int i) {
        this.mAudioDecodeType = i;
    }

    public String getAudioLanguage() {
        return this.mAudioLanguage;
    }

    public void setAudioLanguage(String str) {
        this.mAudioLanguage = str;
    }

    public int getAudioAtmos() {
        return this.mAudioAtmos;
    }

    public void setAudioAtmos(int i) {
        this.mAudioAtmos = i;
    }

    public int getAudioOptMask() {
        return this.mAudioOptMask;
    }

    public void setAudioOptMask(int i) {
        this.mAudioOptMask = i;
    }

    public int getAudioTextLabel() {
        return this.mAudioTextLabel;
    }

    public void setAudioTextLabel(int i) {
        this.mAudioTextLabel = i;
    }

    public int getAudioMsgId() {
        return this.mAudioMsgId;
    }

    public void setAudioMsgId(int i) {
        this.mAudioMsgId = i;
    }

    public int getAudioPreId() {
        return this.mAudioPreId;
    }

    public void setAudioPreId(int i) {
        this.mAudioPreId = i;
    }

    public int getAudioCompTag() {
        return this.mAudioCompTag;
    }

    public void setAudioCompTag(int i) {
        this.mAudioCompTag = i;
    }

    public int getAudioDriverAutoSelect() {
        return this.mAudioDriverAutoSelect;
    }

    public void setAudioDriverAutoSelect(int i) {
        this.mAudioDriverAutoSelect = i;
    }

    public int getAudioPreseIdx() {
        return this.mAudioPreseIdx;
    }

    public void setAudioPreseIdx(int i) {
        this.mAudioPreseIdx = i;
    }

    public String toString() {
        return "TvProviderAudioTrack info mAudioId=" + this.mAudioId + ", mAudioLanguage=" + this.mAudioLanguage + ", mAudioChannelCount=" + this.mAudioChannelCount + ", mAudioSampleRate=" + this.mAudioSampleRate + ", mAudioType=" + this.mAudioType + ", mAudioMixType=" + this.mAudioMixType + ", mAudioEditorialClass=" + this.mAudioEditorialClass + ", mAudioEncodeType=" + this.mAudioEncodeType + ", mAudioDecodeType=" + this.mAudioDecodeType + ", mAudioIsMixedWithAD=" + this.mAudioIsMixedWithAD + ", mAudioPid=" + this.mAudioPid + ", mAudioFormat=" + this.mAudioFormat + ", mAudioIndex=" + this.mAudioIndex + ", mAudioAtmos=" + this.mAudioAtmos + ", mAudioOptMask=" + this.mAudioOptMask + ", mAudioPreId=" + this.mAudioPreId + ", mAudioTextLabel=" + this.mAudioTextLabel + ", mAudioMsgId=" + this.mAudioMsgId + ", mAudioCompTag=" + this.mAudioCompTag + ", mAudioDriverAutoSelect=" + this.mAudioDriverAutoSelect + ", mAudioPreseId=" + this.mAudioPreseIdx + "\n";
    }
}
