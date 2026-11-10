package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvSubtitleBase {
    public static final int SUBTITLE_TRACK_INVALID = 255;
    public static final int SUBTITLE_TRACK_LANGUAGE_LENGTH = 4;
    public static final int SUBTITLE_TRACK_LIST_MAX_LEN = 256;
    public static final int SUBTITLE_TRACK_MAX_NUM = 128;
    public static final String TAG = "MtkTvSubtitleBase";
    public int nfySubtitle_trackNum = 0;
    public OneSubtitleTrack[] nfySubtitle_trackList = new OneSubtitleTrack[128];

    public enum DealType {
        DEAL_TYPE_UNKNOWN(0),
        DEAL_TYPE_STOP_CURRENT(1);

        public int mDealType;

        DealType(int i) {
            this.mDealType = i;
        }

        public int getDealType() {
            return this.mDealType;
        }
    }

    public enum DvbSubtitleDetailType {
        DVB_SBTL_UNKNOWN,
        DVB_SBTL_NO_ASP_RATIO,
        DVB_SBTL_4_3,
        DVB_SBTL_16_9,
        DVB_SBTL_221_1,
        DVB_SBTL_HD,
        DVB_SBTL_NO_ASP_RATIO_HOH,
        DVB_SBTL_4_3_HOH,
        DVB_SBTL_16_9_HOH,
        DVB_SBTL_221_1_HOH,
        DVB_SBTL_HD_HOH
    }

    public static class OneSubtitleTrack {
        public static final int I4C_ONE_SUBTITLE_TRACK_LEN = 10;
        public Boolean trackHearingImpaired;
        public int trackLangIdx;
        public int trackTTXMagNum;
        public int trackTTXPageNum;
        public SubtitleStreamType trackType;
        public DvbSubtitleDetailType trackdvbdetailType;
        public TtxSubtitleDetailType trackttxdetailType;
        public int trackId = 255;
        public String trackLanguage = "";
    }

    public enum SubtitleCallBackType {
        SUBTITLE_CALL_BACK_TYPE_TRACKS_UPDATE,
        SUBTITLE_CALL_BACK_TYPE_TRACK_INFO_SELECTED,
        SUBTITLE_CALL_BACK_TYPE_NUM
    }

    public enum SubtitleStreamType {
        SUBTITLE_STREAM_TYPE_UNKNOWN,
        SUBTITLE_STREAM_TYPE_SUBTITLE,
        SUBTITLE_STREAM_TYPE_TELTEXT
    }

    public enum TtxSubtitleDetailType {
        DVB_TLTXT_UNKNOWN,
        DVB_TLTXT_INITIAL_PAGE,
        DVB_TLTXT_SBTL_PAGE,
        DVB_TLTXT_ADDL_PAGE,
        DVB_TLTXT_PRG_SCHEDULE_PAGE,
        DVB_TLTXT_HEARING_IMPAIR_PAGE
    }

    private String convertAsciiArrayToString(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        if (iArr == null) {
            return null;
        }
        for (int i : iArr) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    public int dealStream(int i) {
        return 0;
    }

    public synchronized OneSubtitleTrack[] getSbtlTracks() {
        int i;
        int[] iArr = new int[256];
        int[] iArr2 = new int[4];
        if (TVNativeWrapper.SubtitleGetTracks_native(iArr) != 0) {
            Log.d(TAG, "Error getTracks\n");
            return null;
        }
        int i2 = iArr[0];
        if (i2 > 0) {
            i = 2;
            this.nfySubtitle_trackNum = iArr[1];
            Log.d(TAG, "nfySubtitle_trackNum:" + this.nfySubtitle_trackNum + "\n");
            i2 += -1;
        } else {
            i = 1;
        }
        if (this.nfySubtitle_trackNum <= 0) {
            Log.d(TAG, "Subtitle_trackList is null\n");
            return null;
        }
        OneSubtitleTrack[] oneSubtitleTrackArr = new OneSubtitleTrack[this.nfySubtitle_trackNum];
        if (this.nfySubtitle_trackNum > 0 && this.nfySubtitle_trackNum < 128) {
            int i3 = i;
            int i4 = i2;
            int i5 = 0;
            while (i5 < this.nfySubtitle_trackNum && i4 >= 10) {
                oneSubtitleTrackArr[i5] = new OneSubtitleTrack();
                int i6 = i3 + 1;
                oneSubtitleTrackArr[i5].trackId = iArr[i3];
                Log.d(TAG, "trackId:" + oneSubtitleTrackArr[i5].trackId + "\n");
                System.arraycopy(iArr, i6, iArr2, 0, 4);
                oneSubtitleTrackArr[i5].trackLanguage = convertAsciiArrayToString(iArr2);
                Log.d(TAG, "trackLanguage:" + oneSubtitleTrackArr[i5].trackLanguage + "\n");
                int i7 = i6 + 4;
                int i8 = i7 + 1;
                oneSubtitleTrackArr[i5].trackLangIdx = iArr[i7];
                Log.d(TAG, "trackLanguageIdx:" + oneSubtitleTrackArr[i5].trackLangIdx + "\n");
                if (iArr[i8] == 0) {
                    oneSubtitleTrackArr[i5].trackHearingImpaired = false;
                } else {
                    oneSubtitleTrackArr[i5].trackHearingImpaired = true;
                }
                int i9 = i8 + 1;
                Log.d(TAG, "trackHearingImpaired:" + oneSubtitleTrackArr[i5].trackHearingImpaired + "\n");
                int i10 = i9 + 1;
                oneSubtitleTrackArr[i5].trackType = SubtitleStreamType.values()[iArr[i9]];
                Log.d(TAG, "trackType:" + oneSubtitleTrackArr[i5].trackType + "\n");
                int i11 = i10 + 1;
                oneSubtitleTrackArr[i5].trackTTXMagNum = iArr[i10];
                Log.d(TAG, "trackTTXMagNum:" + oneSubtitleTrackArr[i5].trackTTXMagNum + "\n");
                int i12 = i11 + 1;
                oneSubtitleTrackArr[i5].trackTTXPageNum = iArr[i11];
                Log.d(TAG, "trackTTXPageNum:" + oneSubtitleTrackArr[i5].trackTTXPageNum + "\n");
                i4 -= 10;
                i5++;
                i3 = i12;
            }
        }
        return oneSubtitleTrackArr;
    }

    public synchronized int getTracks() {
        int i;
        int i2;
        int[] iArr = new int[256];
        int[] iArr2 = new int[4];
        int iSubtitleGetTracks_native = TVNativeWrapper.SubtitleGetTracks_native(iArr);
        if (iSubtitleGetTracks_native != 0) {
            return iSubtitleGetTracks_native;
        }
        int i3 = iArr[0];
        if (i3 > 0) {
            i = 2;
            this.nfySubtitle_trackNum = iArr[1];
            Log.d(TAG, "nfySubtitle_trackNum:" + this.nfySubtitle_trackNum + "\n");
            i3 += -1;
        } else {
            i = 1;
        }
        if (this.nfySubtitle_trackNum > 0 && this.nfySubtitle_trackNum < 128) {
            int i4 = i;
            int i5 = i3;
            for (int i6 = 0; i6 < this.nfySubtitle_trackNum && i5 >= 10; i6++) {
                this.nfySubtitle_trackList[i6] = new OneSubtitleTrack();
                int i7 = i4 + 1;
                this.nfySubtitle_trackList[i6].trackId = iArr[i4];
                System.arraycopy(iArr, i7, iArr2, 0, 4);
                this.nfySubtitle_trackList[i6].trackLanguage = convertAsciiArrayToString(iArr2);
                Log.d(TAG, "trackLanguage:" + this.nfySubtitle_trackList[i6].trackLanguage + "\n");
                int i8 = i7 + 4;
                int i9 = i8 + 1;
                this.nfySubtitle_trackList[i6].trackLangIdx = iArr[i8];
                if (iArr[i9] == 0) {
                    this.nfySubtitle_trackList[i6].trackHearingImpaired = false;
                } else {
                    this.nfySubtitle_trackList[i6].trackHearingImpaired = true;
                }
                int i10 = i9 + 1;
                Log.d(TAG, "trackHearingImpaired:" + this.nfySubtitle_trackList[i6].trackHearingImpaired + "\n");
                int i11 = i10 + 1;
                this.nfySubtitle_trackList[i6].trackType = SubtitleStreamType.values()[iArr[i10]];
                Log.d(TAG, "trackType:" + this.nfySubtitle_trackList[i6].trackType + "\n");
                int i12 = i11 + 1;
                this.nfySubtitle_trackList[i6].trackTTXMagNum = iArr[i11];
                int i13 = i12 + 1;
                this.nfySubtitle_trackList[i6].trackTTXPageNum = iArr[i12];
                Log.d(TAG, "trackTTXPageNum:" + this.nfySubtitle_trackList[i6].trackTTXPageNum + "\n");
                if (this.nfySubtitle_trackList[i6].trackType == SubtitleStreamType.SUBTITLE_STREAM_TYPE_SUBTITLE) {
                    i2 = i13 + 1;
                    this.nfySubtitle_trackList[i6].trackdvbdetailType = DvbSubtitleDetailType.values()[iArr[i13]];
                    Log.d(TAG, "trackdvbdetailType:" + this.nfySubtitle_trackList[i6].trackdvbdetailType + "\n");
                } else {
                    if (this.nfySubtitle_trackList[i6].trackType == SubtitleStreamType.SUBTITLE_STREAM_TYPE_TELTEXT) {
                        i2 = i13 + 1;
                        this.nfySubtitle_trackList[i6].trackttxdetailType = TtxSubtitleDetailType.values()[iArr[i13]];
                        Log.d(TAG, "trackttxdetailType:" + this.nfySubtitle_trackList[i6].trackttxdetailType + "\n");
                    } else {
                        i4 = i13;
                    }
                    i5 -= 10;
                }
                i4 = i2;
                i5 -= 10;
            }
        }
        return iSubtitleGetTracks_native;
    }

    public int nextStream() {
        return TVNativeWrapper.nextStream_native();
    }

    public int playStream(int i) {
        return TVNativeWrapper.playStream_native(i);
    }
}
