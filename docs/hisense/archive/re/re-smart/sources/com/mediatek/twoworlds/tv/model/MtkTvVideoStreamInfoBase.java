package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvVideoStreamInfoBase {
    public static final String TAG = "TV_MtkTvVideoStreamInfoBase";
    public static final int VID_ENC_AV1 = 28;
    public static final int VID_ENC_AVS = 18;
    public static final int VID_ENC_AVS2 = 26;
    public static final int VID_ENC_DIVX_311 = 4;
    public static final int VID_ENC_DIVX_4 = 5;
    public static final int VID_ENC_DIVX_5 = 6;
    public static final int VID_ENC_DV_H264 = 24;
    public static final int VID_ENC_DV_H265 = 25;
    public static final int VID_ENC_H263 = 13;
    public static final int VID_ENC_H264 = 12;
    public static final int VID_ENC_H265 = 22;
    public static final int VID_ENC_MJPEG = 14;
    public static final int VID_ENC_MPEG_1 = 1;
    public static final int VID_ENC_MPEG_2 = 2;
    public static final int VID_ENC_MPEG_4 = 3;
    public static final int VID_ENC_NV12 = 19;
    public static final int VID_ENC_RV8 = 15;
    public static final int VID_ENC_RV9 = 16;
    public static final int VID_ENC_SHVC = 27;
    public static final int VID_ENC_SORENSON = 17;
    public static final int VID_ENC_UNKNOWN = 0;
    public static final int VID_ENC_VP6 = 21;
    public static final int VID_ENC_VP8 = 20;
    public static final int VID_ENC_VP9 = 23;
    public static final int VID_ENC_WMV1 = 8;
    public static final int VID_ENC_WMV2 = 9;
    public static final int VID_ENC_WMV3 = 10;
    public static final int VID_ENC_WVC1 = 11;
    public static final int VID_ENC_XVID = 7;
    public static final int VID_FMT_HD_16_9 = 2;
    public static final int VID_FMT_HD_4_3 = 1;
    public static final int VID_FMT_SD_16_9 = 4;
    public static final int VID_FMT_SD_4_3 = 3;
    public static final int VID_FMT_UNKNOWN = 0;
    public int mVideoEncode;
    public int mVideoFormat;
    public int mVideoPid;

    public int getVideoEncode() {
        return this.mVideoEncode;
    }

    public int getVideoFormat() {
        return this.mVideoFormat;
    }

    public int getVideoPid() {
        return this.mVideoPid;
    }

    public void setVideoEncode(int i) {
        this.mVideoEncode = i;
    }

    public void setVideoFormat(int i) {
        this.mVideoFormat = i;
    }

    public void setVideoPid(int i) {
        this.mVideoPid = i;
    }
}
