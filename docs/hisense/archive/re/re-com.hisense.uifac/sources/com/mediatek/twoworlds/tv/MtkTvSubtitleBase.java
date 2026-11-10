package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvSubtitleBase {
    public static final int SUBTITLE_TRACK_INVALID = 255;
    public static final int SUBTITLE_TRACK_LANGUAGE_LENGTH = 4;
    private static final int SUBTITLE_TRACK_LIST_MAX_LEN = 256;
    private static final int SUBTITLE_TRACK_MAX_NUM = 128;
    public static final String TAG = "MtkTvSubtitleBase";
    public int nfySubtitle_trackNum = 0;
    public OneSubtitleTrack[] nfySubtitle_trackList = new OneSubtitleTrack[128];

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
        private static final int I4C_ONE_SUBTITLE_TRACK_LEN = 10;
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

    public int dealStream(int i) {
        return 0;
    }

    public enum DealType {
        DEAL_TYPE_UNKNOWN(0),
        DEAL_TYPE_STOP_CURRENT(1);

        private int mDealType;

        DealType(int i) {
            this.mDealType = i;
        }

        public int getDealType() {
            return this.mDealType;
        }
    }

    public int nextStream() {
        return TVNativeWrapper.nextStream_native();
    }

    public int playStream(int i) {
        return TVNativeWrapper.playStream_native(i);
    }

    public synchronized int getTracks() {
        int i;
        int[] iArr = new int[256];
        int[] iArr2 = new int[4];
        int iSubtitleGetTracks_native = TVNativeWrapper.SubtitleGetTracks_native(iArr);
        if (iSubtitleGetTracks_native != 0) {
            return iSubtitleGetTracks_native;
        }
        int i2 = iArr[0];
        int i3 = 1;
        if (i2 > 0) {
            this.nfySubtitle_trackNum = iArr[1];
            Log.d(TAG, "nfySubtitle_trackNum:" + this.nfySubtitle_trackNum + "\n");
            i2 += -1;
            i3 = 2;
        }
        if (this.nfySubtitle_trackNum > 0 && this.nfySubtitle_trackNum < 128) {
            for (int i4 = 0; i4 < this.nfySubtitle_trackNum && i2 >= 10; i4++) {
                this.nfySubtitle_trackList[i4] = new OneSubtitleTrack();
                int i5 = i3 + 1;
                this.nfySubtitle_trackList[i4].trackId = iArr[i3];
                System.arraycopy(iArr, i5, iArr2, 0, 4);
                this.nfySubtitle_trackList[i4].trackLanguage = convertAsciiArrayToString(iArr2);
                Log.d(TAG, "trackLanguage:" + this.nfySubtitle_trackList[i4].trackLanguage + "\n");
                int i6 = i5 + 4;
                int i7 = i6 + 1;
                this.nfySubtitle_trackList[i4].trackLangIdx = iArr[i6];
                if (iArr[i7] == 0) {
                    this.nfySubtitle_trackList[i4].trackHearingImpaired = Boolean.FALSE;
                } else {
                    this.nfySubtitle_trackList[i4].trackHearingImpaired = Boolean.TRUE;
                }
                int i8 = i7 + 1;
                Log.d(TAG, "trackHearingImpaired:" + this.nfySubtitle_trackList[i4].trackHearingImpaired + "\n");
                int i9 = i8 + 1;
                this.nfySubtitle_trackList[i4].trackType = SubtitleStreamType.values()[iArr[i8]];
                Log.d(TAG, "trackType:" + this.nfySubtitle_trackList[i4].trackType + "\n");
                int i10 = i9 + 1;
                this.nfySubtitle_trackList[i4].trackTTXMagNum = iArr[i9];
                int i11 = i10 + 1;
                this.nfySubtitle_trackList[i4].trackTTXPageNum = iArr[i10];
                Log.d(TAG, "trackTTXPageNum:" + this.nfySubtitle_trackList[i4].trackTTXPageNum + "\n");
                if (this.nfySubtitle_trackList[i4].trackType == SubtitleStreamType.SUBTITLE_STREAM_TYPE_SUBTITLE) {
                    i = i11 + 1;
                    this.nfySubtitle_trackList[i4].trackdvbdetailType = DvbSubtitleDetailType.values()[iArr[i11]];
                    Log.d(TAG, "trackdvbdetailType:" + this.nfySubtitle_trackList[i4].trackdvbdetailType + "\n");
                } else {
                    if (this.nfySubtitle_trackList[i4].trackType == SubtitleStreamType.SUBTITLE_STREAM_TYPE_TELTEXT) {
                        i = i11 + 1;
                        this.nfySubtitle_trackList[i4].trackttxdetailType = TtxSubtitleDetailType.values()[iArr[i11]];
                        Log.d(TAG, "trackttxdetailType:" + this.nfySubtitle_trackList[i4].trackttxdetailType + "\n");
                    } else {
                        i3 = i11;
                    }
                    i2 -= 10;
                }
                i3 = i;
                i2 -= 10;
            }
        }
        return iSubtitleGetTracks_native;
    }

    public synchronized OneSubtitleTrack[] getSbtlTracks() {
        int[] iArr = new int[256];
        int[] iArr2 = new int[4];
        if (TVNativeWrapper.SubtitleGetTracks_native(iArr) != 0) {
            Log.d(TAG, "Error getTracks\n");
            return null;
        }
        int i = iArr[0];
        int i2 = 1;
        if (i > 0) {
            this.nfySubtitle_trackNum = iArr[1];
            Log.d(TAG, "nfySubtitle_trackNum:" + this.nfySubtitle_trackNum + "\n");
            i += -1;
            i2 = 2;
        }
        if (this.nfySubtitle_trackNum <= 0) {
            Log.d(TAG, "Subtitle_trackList is null\n");
            return null;
        }
        OneSubtitleTrack[] oneSubtitleTrackArr = new OneSubtitleTrack[this.nfySubtitle_trackNum];
        if (this.nfySubtitle_trackNum > 0 && this.nfySubtitle_trackNum < 128) {
            int i3 = 0;
            while (i3 < this.nfySubtitle_trackNum && i >= 10) {
                oneSubtitleTrackArr[i3] = new OneSubtitleTrack();
                int i4 = i2 + 1;
                oneSubtitleTrackArr[i3].trackId = iArr[i2];
                Log.d(TAG, "trackId:" + oneSubtitleTrackArr[i3].trackId + "\n");
                System.arraycopy(iArr, i4, iArr2, 0, 4);
                oneSubtitleTrackArr[i3].trackLanguage = convertAsciiArrayToString(iArr2);
                Log.d(TAG, "trackLanguage:" + oneSubtitleTrackArr[i3].trackLanguage + "\n");
                int i5 = i4 + 4;
                int i6 = i5 + 1;
                oneSubtitleTrackArr[i3].trackLangIdx = iArr[i5];
                Log.d(TAG, "trackLanguageIdx:" + oneSubtitleTrackArr[i3].trackLangIdx + "\n");
                if (iArr[i6] == 0) {
                    oneSubtitleTrackArr[i3].trackHearingImpaired = Boolean.FALSE;
                } else {
                    oneSubtitleTrackArr[i3].trackHearingImpaired = Boolean.TRUE;
                }
                int i7 = i6 + 1;
                Log.d(TAG, "trackHearingImpaired:" + oneSubtitleTrackArr[i3].trackHearingImpaired + "\n");
                int i8 = i7 + 1;
                oneSubtitleTrackArr[i3].trackType = SubtitleStreamType.values()[iArr[i7]];
                Log.d(TAG, "trackType:" + oneSubtitleTrackArr[i3].trackType + "\n");
                int i9 = i8 + 1;
                oneSubtitleTrackArr[i3].trackTTXMagNum = iArr[i8];
                Log.d(TAG, "trackTTXMagNum:" + oneSubtitleTrackArr[i3].trackTTXMagNum + "\n");
                int i10 = i9 + 1;
                oneSubtitleTrackArr[i3].trackTTXPageNum = iArr[i9];
                Log.d(TAG, "trackTTXPageNum:" + oneSubtitleTrackArr[i3].trackTTXPageNum + "\n");
                i -= 10;
                i3++;
                i2 = i10;
            }
        }
        return oneSubtitleTrackArr;
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
}
