package com.mediatek.twoworlds.tv;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvHBBTVBase {
    private static final int AUDIO_PRELOAD_HEAD_LEN = 2;
    private static final int AUDIO_PRELOAD_INFO_ACTIVE = 1;
    private static final int AUDIO_PRELOAD_INFO_AD = 2;
    private static final int AUDIO_PRELOAD_INFO_HI = 9;
    private static final int AUDIO_PRELOAD_INFO_INDEX = 0;
    private static final int AUDIO_PRELOAD_INFO_LANG = 3;
    private static final int AUDIO_PRELOAD_INFO_LANG_LEN = 4;
    private static final int AUDIO_PRELOAD_INFO_LEN = 10;
    private static final int AUDIO_PRELOAD_INFO_TAG = 7;
    private static final int AUDIO_PRELOAD_INFO_TAG_LEN = 2;
    private static final int AUDIO_PRELOAD_NMEMB = 0;
    private static final int AUDIO_PRELOAD_TOTAL = 1;
    private static final int DECRYPTION_FILE_MAX_LEN = 65536;
    private static final int DECRYPTION_USERKEYLEN = 16;
    private static final int EXCHANGE_FUNC_AUDIO_GET_LIST = 13;
    private static final int EXCHANGE_FUNC_AUDIO_SET_ACTIVE = 14;
    private static final int EXCHANGE_FUNC_DIALOG_FB = 10;
    private static final int EXCHANGE_FUNC_ENABLE = 1;
    private static final int EXCHANGE_FUNC_GET_COOKIE = 22;
    private static final int EXCHANGE_FUNC_GET_DECRYPTION_DATA = 25;
    private static final int EXCHANGE_FUNC_GET_FVP_SUPPORT = 18;
    private static final int EXCHANGE_FUNC_GET_HBBTV_SUPPORT = 17;
    private static final int EXCHANGE_FUNC_GET_HBBTV_VERSION = 19;
    private static final int EXCHANGE_FUNC_GET_MDS_APP_LIST = 27;
    private static final int EXCHANGE_FUNC_GET_MDS_AUTH_URL = 23;
    private static final int EXCHANGE_FUNC_GET_USER_AGENT = 21;
    private static final int EXCHANGE_FUNC_KILL_APP = 9;
    private static final int EXCHANGE_FUNC_LAUNCH_APP = 8;
    private static final int EXCHANGE_FUNC_RESET_APR_MNGR_DB = 11;
    private static final int EXCHANGE_FUNC_SET_EXTERNAL_DATA = 24;
    private static final int EXCHANGE_FUNC_SET_HBBTV_VERSION = 20;
    private static final int EXCHANGE_FUNC_START = 2;
    private static final int EXCHANGE_FUNC_STOP = 3;
    private static final int EXCHANGE_FUNC_SUBTITLE_GET_LIST = 15;
    private static final int EXCHANGE_FUNC_SUBTITLE_SET_ACTIVE = 16;
    private static final int EXCHANGE_FUNC_TYPE_UNKNOWN = 0;
    private static final int EXCHANGE_FUNC_UPDATE_MDS_APP_LIST = 26;
    private static final int EXCHANGE_HEADER_TYPE_IDX = 1;
    private static final int EXCHANGE_HEAD_LEN = 2;
    private static final int EXCHANGE_MAX_HEAD_LEN = 128;
    private static final int EXCHANGE_MAX_LEN = 1408;
    private static final int EXCHANGE_PAYLOAD_1ST_IDX = 2;
    private static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    private static final int HBBTV_COOKIE_MAX_LEN = 1028;
    private static final int HBBTV_USER_AGENT_MAX_LEN = 1028;
    private static final int MDS_AUTH_URL_MAX_LEN = 1028;
    private static final int MDS_FVP_APP_LIST_MAX_LEN = 5124;
    private static final int MTKTVAPI_HBBTV_APR_MNGR_DB_ERASE_MANUFACTURE_DATA = 1;
    private static final int MTKTVAPI_HBBTV_APR_MNGR_DB_ERASE_USER_DATA = 2;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DIALOG_VALUE_ALWAYS = 2;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DIALOG_VALUE_CANCEL = 0;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DIALOG_VALUE_ONCE = 1;
    private static final int SBTL_PRELOAD_HEAD_LEN = 2;
    private static final int SBTL_PRELOAD_INFO_ACTIVE = 1;
    private static final int SBTL_PRELOAD_INFO_ATTR = 3;
    private static final int SBTL_PRELOAD_INFO_INDEX = 0;
    private static final int SBTL_PRELOAD_INFO_LANG = 4;
    private static final int SBTL_PRELOAD_INFO_LANG_LEN = 4;
    private static final int SBTL_PRELOAD_INFO_LEN = 10;
    private static final int SBTL_PRELOAD_INFO_TAG = 8;
    private static final int SBTL_PRELOAD_INFO_TAG_LEN = 2;
    private static final int SBTL_PRELOAD_INFO_TYPE = 2;
    private static final int SBTL_PRELOAD_NMEMB = 0;
    private static final int SBTL_PRELOAD_TOTAL = 1;
    public static final String TAG = "MtkTvHBBTVBase";

    public enum ExternalDataType {
        EXTERNAL_DATA_TYPE_LIVE_TV,
        EXTERNAL_DATA_TYPE_3RD_APP_LAUNCHER,
        EXTERNAL_DATA_TYPE_EPG,
        EXTERNAL_DATA_TYPE_RECOMMEND_PAGE
    }

    public enum HbbtvAppSrcType {
        HBBTV_APP_SRC_TYPE_NONE,
        HBBTV_APP_SRC_TYPE_HBBTV_NORMAL,
        HBBTV_APP_SRC_TYPE_BBC_MHEG,
        HBBTV_APP_SRC_TYPE_BBC_NETTV,
        HBBTV_APP_SRC_TYPE_BBC_DIAL,
        HBBTV_APP_SRC_TYPE_FVP_DEEPLINK_URL,
        HBBTV_APP_SRC_TYPE_FVP_PORTAL,
        HBBTV_APP_SRC_TYPE_JS_CREATE_APP,
        HBBTV_APP_SRC_TYPE_END
    }

    public enum HbbtvCmdType {
        MTKTVAPI_HBBTV_FUNC_UNKNOWN,
        MTKTVAPI_HBBTV_FUNC_ENABLE,
        MTKTVAPI_HBBTV_FUNC_START,
        MTKTVAPI_HBBTV_FUNC_STOP,
        MTKTVAPI_HBBTV_FUNC_ACCEPT,
        MTKTVAPI_HBBTV_FUNC_CANCEL,
        MTKTVAPI_HBBTV_SET_NETWORK_STATE,
        MTKTVAPI_HBBTV_SET_NETWORK_CONDITION_STATE
    }

    public enum HbbtvRet {
        HBBTV_RET_OK,
        HBBTV_RET_INTERNAL_ERROR
    }

    public static class MtkTvHbbTVStreamAudio {
        public boolean active;
        public boolean audio_description;
        public boolean hearing_impaired;
        public int index;
        public char[] lang;
        public long tag;

        public MtkTvHbbTVStreamAudio() {
            this.index = 0;
            this.active = false;
            this.audio_description = false;
            this.hearing_impaired = false;
            char[] cArr = {0, 0, 0, 0};
            this.lang = cArr;
            this.tag = 0L;
            this.index = 0;
            this.active = false;
            this.audio_description = false;
            this.hearing_impaired = false;
            cArr[0] = 0;
            this.tag = 0L;
        }

        public MtkTvHbbTVStreamAudio(MtkTvHbbTVStreamAudio mtkTvHbbTVStreamAudio) {
            this.index = 0;
            this.active = false;
            this.audio_description = false;
            this.hearing_impaired = false;
            this.lang = new char[]{0, 0, 0, 0};
            this.tag = 0L;
            this.index = mtkTvHbbTVStreamAudio.index;
            this.active = mtkTvHbbTVStreamAudio.active;
            this.audio_description = mtkTvHbbTVStreamAudio.audio_description;
            this.hearing_impaired = mtkTvHbbTVStreamAudio.hearing_impaired;
            for (int i = 0; i < 4; i++) {
                this.lang[i] = mtkTvHbbTVStreamAudio.lang[i];
            }
            this.tag = mtkTvHbbTVStreamAudio.tag;
        }
    }

    public enum HBBTV_SUBTITLE_ATTR_T {
        HBBTV_SUBTITLE_ATTR_NORMAL(0),
        HBBTV_ATTR_HEARING_IMPAIRED(1),
        HBBTV_SUBTITLE_ATTR_UNKNOWN(2);

        private int index;

        HBBTV_SUBTITLE_ATTR_T(int i) {
            this.index = 0;
            this.index = i;
        }

        public int idOf() {
            return this.index;
        }
    }

    public enum HBBTV_STREAM_SUBTITLE_TYPE_T {
        HBBTV_STREAM_SUBTITLE_UNKNOWN(0),
        HBBTV_STREAM_SUBTITLE_TTML_TYPE(1),
        HBBTV_STREAM_SUBTITLE_DIVX_TYPE(2),
        HBBTV_STREAM_SUBTITLE_TEXT_ASS(3),
        HBBTV_STREAM_SUBTITLE_TEXT_SRT(4),
        HBBTV_STREAM_SUBTITLE_RLE(5),
        HBBTV_STREAM_SUBTITLE_CC_TYPE(6),
        HBBTV_STREAM_SUBTITLE_DVB_TYPE(7),
        HBBTV_STREAM_SUBTITLE_TELTEXT_TYPE(8);

        private int index;

        HBBTV_STREAM_SUBTITLE_TYPE_T(int i) {
            this.index = 0;
            this.index = i;
        }

        public int idOf() {
            return this.index;
        }
    }

    public static class MtkTvHbbTVStreamSubtitle {
        public boolean active;
        public int attr;
        public int index;
        public char[] lang;
        public long tag;
        public int type;

        public MtkTvHbbTVStreamSubtitle() {
            this.index = 0;
            this.active = false;
            this.type = 0;
            this.attr = 2;
            char[] cArr = {0, 0, 0, 0};
            this.lang = cArr;
            this.tag = 0L;
            this.index = 0;
            this.active = false;
            this.type = 0;
            this.attr = 2;
            cArr[0] = 0;
            this.tag = 0L;
        }

        public MtkTvHbbTVStreamSubtitle(MtkTvHbbTVStreamSubtitle mtkTvHbbTVStreamSubtitle) {
            this.index = 0;
            this.active = false;
            this.type = 0;
            this.attr = 2;
            this.lang = new char[]{0, 0, 0, 0};
            this.tag = 0L;
            this.index = mtkTvHbbTVStreamSubtitle.index;
            this.active = mtkTvHbbTVStreamSubtitle.active;
            this.type = mtkTvHbbTVStreamSubtitle.type;
            this.attr = mtkTvHbbTVStreamSubtitle.attr;
            for (int i = 0; i < 4; i++) {
                this.lang[i] = mtkTvHbbTVStreamSubtitle.lang[i];
            }
            this.tag = mtkTvHbbTVStreamSubtitle.tag;
        }
    }

    public MtkTvHBBTVBase() {
        Log.d(TAG, "MtkTvHBBTVBase object created");
    }

    public HbbtvRet enable(boolean z) {
        Log.d(TAG, "hbbtv enable entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr = {z ? 1 : 0};
        Log.i(TAG, "hbbtv enable:" + z);
        HbbtvRet hbbtvRetExchangeData = exchangeData(1, iArr);
        Log.d(TAG, "hbbtv enable exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet setExternalData(int i, int[] iArr) {
        Log.d(TAG, "setExternalData entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr2 = new int[iArr.length + 1];
        Arrays.fill(iArr2, 0);
        iArr2[0] = i;
        System.arraycopy(iArr, 0, iArr2, 1, iArr.length);
        HbbtvRet hbbtvRetExchangeData = exchangeData(24, iArr2);
        Log.d(TAG, "setExternalData exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet enableHbbtvByEPG() {
        Log.d(TAG, "enableHbbtvByEPG entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_EPG.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvByEPG exit");
        return externalData;
    }

    public HbbtvRet disableHbbtvByEPG() {
        Log.d(TAG, "disableHbbtvByEPG entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_EPG.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvByEPG exit");
        return externalData;
    }

    public HbbtvRet enableHbbtvBy3rdApp() {
        Log.d(TAG, "enableHbbtvBy3rdApp entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_3RD_APP_LAUNCHER.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvBy3rdApp exit");
        return externalData;
    }

    public HbbtvRet disableHbbtvBy3rdApp() {
        Log.d(TAG, "disableHbbtvBy3rdApp entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_3RD_APP_LAUNCHER.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvBy3rdApp exit");
        return externalData;
    }

    public HbbtvRet enableHbbtvByLiveTV() {
        Log.d(TAG, "enableHbbtvByLiveTV entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_LIVE_TV.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvByLiveTV exit");
        return externalData;
    }

    public HbbtvRet disableHbbtvByLiveTV() {
        Log.d(TAG, "disableHbbtvByLiveTV entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_LIVE_TV.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvByLiveTV exit");
        return externalData;
    }

    public HbbtvRet enableHbbtvByRecommendPage() {
        Log.d(TAG, "enableHbbtvByRecommendPage entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_RECOMMEND_PAGE.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvByRecommendPage exit");
        return externalData;
    }

    public HbbtvRet disableHbbtvByRecommendPage() {
        Log.d(TAG, "disableHbbtvByRecommendPage entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_RECOMMEND_PAGE.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvByRecommendPage exit");
        return externalData;
    }

    public HbbtvRet start() {
        Log.d(TAG, "start hbbtv entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(2, null);
        Log.d(TAG, "start hbbtv exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet stop() {
        Log.d(TAG, "stop hbbtv entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(3, null);
        Log.d(TAG, "stop hbbtv exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet launchHBBTVApp(int i, String str) {
        Log.d(TAG, "launchHBBTVApp entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int length = str.length();
        int i2 = (length / 4) + 1;
        int[] iArr = new int[i2 + 1];
        int i3 = 0;
        iArr[0] = i;
        byte[] bArr = new byte[i2 * 4];
        Arrays.fill(bArr, (byte) 0);
        System.arraycopy(str.getBytes(), 0, bArr, 0, length);
        int i4 = 1;
        while (i4 <= i2) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            iArr[i4] = bArr[i3] + (bArr[i5] << 8) + (bArr[i6] << 16) + (bArr[i7] << 24);
            i4++;
            i3 = i7 + 1;
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(8, iArr);
        Log.d(TAG, "launchHBBTVApp type(" + i + "), url[0...3](" + iArr[1] + "), url(" + str + ").\n");
        Log.d(TAG, "launchHBBTVApp exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet killHBBTVApp() {
        Log.d(TAG, "killHBBTVApp entered");
        byte[] bArr = {0, 0, 1, 0};
        int[] iArr = {0, 6};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        iArr[0] = bArr[0] + (bArr[1] << 8) + (bArr[2] << 16) + (bArr[3] << 24);
        HbbtvRet hbbtvRetExchangeData = exchangeData(9, iArr);
        Log.d(TAG, "killHBBTVApp exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet dialogFeedBack(int i) {
        Log.d(TAG, "hbbtv enable entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        Log.i(TAG, "hbbtv dialog enable:" + i);
        HbbtvRet hbbtvRetExchangeData = exchangeData(10, new int[]{i});
        Log.d(TAG, "hbbtv dialog enable exit");
        return hbbtvRetExchangeData;
    }

    public int setAudioDescription(int i) {
        Log.d(TAG, "setAudioDescription hbbtv entered");
        int iHbbtvSetAudioDescription_native = TVNativeWrapper.hbbtvSetAudioDescription_native(i);
        Log.d(TAG, "setAudioDescription hbbtv exit");
        return iHbbtvSetAudioDescription_native;
    }

    public HbbtvRet eraseApvMngrDBData(int i) {
        Log.d(TAG, "eraseApvMngrDBData");
        int[] iArr = {i};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(11, iArr);
        Log.d(TAG, "eraseApvMngrDBData exit");
        return hbbtvRetExchangeData;
    }

    public int setDefaultAudioLang(String str) {
        Log.d(TAG, "setDefaultAudioLang hbbtv entered");
        int iHbbtvSetDefaultAudioLang_native = TVNativeWrapper.hbbtvSetDefaultAudioLang_native(str);
        Log.d(TAG, "setDefaultAudioLang hbbtv exit");
        return iHbbtvSetDefaultAudioLang_native;
    }

    public int setDefaultSubtitleLang(String str, int i) {
        Log.d(TAG, "setDefaultSubtitleLang hbbtv entered");
        int iHbbtvSetDefaultSubtitleLang_native = TVNativeWrapper.hbbtvSetDefaultSubtitleLang_native(str, i);
        Log.d(TAG, "setDefaultSubtitleLang hbbtv exit");
        return iHbbtvSetDefaultSubtitleLang_native;
    }

    public int setDefaultSubtitleLang(String str) {
        return setDefaultSubtitleLang(str, 1);
    }

    public HbbtvRet setHBBTVVersion(byte[] bArr, int i) {
        Log.d(TAG, "setHBBTVVersion entered");
        int[] iArr = {0, 0, 0};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i < 3) {
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        iArr[0] = bArr[0];
        iArr[1] = bArr[1];
        iArr[2] = bArr[2];
        HbbtvRet hbbtvRetExchangeData = exchangeData(20, iArr);
        Log.d(TAG, "setHBBTVVersion(size:" + i + "):" + iArr[0] + "." + iArr[1] + "." + iArr[2] + "\n");
        Log.d(TAG, "setHBBTVVersion exit");
        return hbbtvRetExchangeData;
    }

    public int getStreamAudioCount() {
        Log.d(TAG, "getStreamAudioCount hbbtv entered");
        int iHbbtvStmGetAudioCount_native = TVNativeWrapper.hbbtvStmGetAudioCount_native();
        Log.d(TAG, "getStreamAudioCount hbbtv exit");
        return iHbbtvStmGetAudioCount_native;
    }

    public String getStreamAudioLang(int i) {
        Log.d(TAG, "getStreamAudioLang hbbtv entered");
        String strHbbtvStmGetAudioLang_native = TVNativeWrapper.hbbtvStmGetAudioLang_native(i);
        Log.d(TAG, "getStreamAudioLang hbbtv exit");
        return strHbbtvStmGetAudioLang_native;
    }

    public HbbtvRet getHBBTVSupport(boolean[] zArr) {
        Log.d(TAG, "getHBBTVSupport entered");
        int[] iArr = {0};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(17, iArr);
        if (iArr[0] != 0) {
            zArr[0] = true;
        } else {
            zArr[0] = false;
        }
        Log.d(TAG, "getHBBTVSupport:" + zArr[0]);
        Log.d(TAG, "getHBBTVSupport exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet getFVPSupport(boolean[] zArr) {
        Log.d(TAG, "getFVPSupport entered");
        int[] iArr = {0};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(18, iArr);
        if (iArr[0] != 0) {
            zArr[0] = true;
        } else {
            zArr[0] = false;
        }
        Log.d(TAG, "getFVPSupport:" + zArr[0]);
        Log.d(TAG, "getFVPSupport exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet getHBBTVVersion(byte[] bArr, int i) {
        Log.d(TAG, "getHBBTVVersion entered");
        int[] iArr = {0, 0, 0};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i < 3) {
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(19, iArr);
        bArr[0] = (byte) iArr[0];
        bArr[1] = (byte) iArr[1];
        bArr[2] = (byte) iArr[2];
        Log.d(TAG, "getHBBTVVersion(size:" + i + "):" + ((int) bArr[0]) + "." + ((int) bArr[1]) + "." + ((int) bArr[2]) + "\n");
        Log.d(TAG, "getHBBTVVersion exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet getHBBTVUserAgent(StringBuffer stringBuffer) {
        Log.d(TAG, "getHBBTVUserAgent entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr = new int[257];
        Arrays.fill(iArr, 0);
        HbbtvRet hbbtvRetExchangeData = exchangeData(21, iArr);
        for (int i = 0; i < 257 && ((byte) iArr[i]) != 0; i++) {
            stringBuffer.append((char) ((byte) iArr[i]));
            if (((byte) (iArr[i] >> 8)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 8)));
            if (((byte) (iArr[i] >> 16)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 16)));
            if (((byte) (iArr[i] >> 24)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 24)));
        }
        Log.d(TAG, "getHBBTVUserAgent(size:" + stringBuffer.length() + "): UserAgent(" + stringBuffer.substring(0) + ")\n");
        Log.d(TAG, "getHBBTVUserAgent exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet getHBBTVCookie(StringBuffer stringBuffer) {
        Log.d(TAG, "getHBBTVCookie entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr = new int[257];
        Arrays.fill(iArr, 0);
        HbbtvRet hbbtvRetExchangeData = exchangeData(22, iArr);
        for (int i = 0; i < 257 && ((byte) iArr[i]) != 0; i++) {
            stringBuffer.append((char) ((byte) iArr[i]));
            if (((byte) (iArr[i] >> 8)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 8)));
            if (((byte) (iArr[i] >> 16)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 16)));
            if (((byte) (iArr[i] >> 24)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 24)));
        }
        Log.d(TAG, "getHBBTVCookie(size:" + stringBuffer.length() + "): Cookie(" + stringBuffer.substring(0) + ")\n");
        Log.d(TAG, "getHBBTVCookie exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet getFVPAuthUrl(StringBuffer stringBuffer) {
        Log.d(TAG, "getFVPAuthUrl entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr = new int[257];
        Arrays.fill(iArr, 0);
        HbbtvRet hbbtvRetExchangeData = exchangeData(23, iArr);
        for (int i = 0; i < 257 && ((byte) iArr[i]) != 0; i++) {
            stringBuffer.append((char) ((byte) iArr[i]));
            if (((byte) (iArr[i] >> 8)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 8)));
            if (((byte) (iArr[i] >> 16)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 16)));
            if (((byte) (iArr[i] >> 24)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 24)));
        }
        Log.d(TAG, "getFVPAuthUrl(size:" + stringBuffer.length() + "): AuthUrl(" + stringBuffer.toString() + ")\n");
        Log.d(TAG, "getFVPAuthUrl exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet updateFVPAppList() {
        Log.d(TAG, "update FVP App List entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(26, null);
        Log.d(TAG, "update FVP App List exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet getFVPAppList(StringBuffer stringBuffer) {
        Log.d(TAG, "getFVPAppList entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr = new int[1281];
        Arrays.fill(iArr, 0);
        HbbtvRet hbbtvRetExchangeData = exchangeData(27, iArr);
        for (int i = 0; i < 1281 && ((byte) iArr[i]) != 0; i++) {
            stringBuffer.append((char) ((byte) iArr[i]));
            if (((byte) (iArr[i] >> 8)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 8)));
            if (((byte) (iArr[i] >> 16)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 16)));
            if (((byte) (iArr[i] >> 24)) == 0) {
                break;
            }
            stringBuffer.append((char) ((byte) (iArr[i] >> 24)));
        }
        Log.d(TAG, "getFVPAppList(size:" + stringBuffer.length() + "): AppList(" + stringBuffer.toString() + ")\n");
        Log.d(TAG, "getFVPAppList exit");
        return hbbtvRetExchangeData;
    }

    public int hbbtvStmGetAduioIndex() {
        Log.d(TAG, "hbbtvStmGetAduioIndex hbbtv entered");
        int iHbbtvStmGetAudio_native = TVNativeWrapper.hbbtvStmGetAudio_native();
        Log.d(TAG, "hbbtvStmGetAduioIndex hbbtv exit");
        return iHbbtvStmGetAudio_native;
    }

    public int hbbtvStmSetAudioIndex(int i) {
        Log.d(TAG, "hbbtvStmSetAudioIndex hbbtv entered");
        int iHbbtvStmSetAudioIndex_native = TVNativeWrapper.hbbtvStmSetAudioIndex_native(i);
        Log.d(TAG, "hbbtvStmSetAudioIndex hbbtv exit");
        return iHbbtvStmSetAudioIndex_native;
    }

    public HbbtvRet hbbtvAudioGetList(MtkTvHbbTVStreamAudio[] mtkTvHbbTVStreamAudioArr, int i, int[] iArr) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            Log.d(TAG, "Invalid arg, nmemb:" + i);
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        int i2 = (i * 10) + 2;
        int[] iArr2 = new int[i2];
        iArr2[0] = i;
        iArr2[1] = iArr[0];
        Log.d(TAG, "before exchangeData");
        for (int i3 = 0; i3 < i2; i3++) {
            Log.d(TAG, "[" + i3 + "]->" + iArr2[i3] + "\t");
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(13, iArr2);
        if (hbbtvRetExchangeData != HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData, return failed");
            return hbbtvRetExchangeData;
        }
        Log.d(TAG, "after exchangeData");
        for (int i4 = 0; i4 < i2; i4++) {
            Log.d(TAG, "[" + i4 + "]->" + iArr2[i4] + "\t");
        }
        int i5 = iArr2[0];
        iArr[0] = iArr2[1];
        Log.d(TAG, "after exchangeData, nmemb:" + i5 + ", ntotal[0]:" + iArr[0]);
        if (i5 > iArr[0]) {
            i5 = iArr[0];
        }
        for (int i6 = 0; i6 < i5; i6++) {
            mtkTvHbbTVStreamAudioArr[i6] = new MtkTvHbbTVStreamAudio();
            int i7 = (i6 * 10) + 2;
            mtkTvHbbTVStreamAudioArr[i6].index = iArr2[i7 + 0];
            mtkTvHbbTVStreamAudioArr[i6].active = iArr2[i7 + 1] != 0;
            mtkTvHbbTVStreamAudioArr[i6].audio_description = iArr2[i7 + 2] != 0;
            for (int i8 = 0; i8 < 4; i8++) {
                mtkTvHbbTVStreamAudioArr[i6].lang[i8] = (char) iArr2[i7 + 3 + i8];
            }
            int i9 = i7 + 7;
            mtkTvHbbTVStreamAudioArr[i6].tag = ((long) iArr2[i9]) << ((int) (((long) iArr2[i9 + 1]) + 32));
            mtkTvHbbTVStreamAudioArr[i6].hearing_impaired = iArr2[i7 + 9] != 0;
            Log.d(TAG, "strmAudio[" + i6 + "]{idex:" + mtkTvHbbTVStreamAudioArr[i6].index + ", active:" + mtkTvHbbTVStreamAudioArr[i6].active + ", ad:" + mtkTvHbbTVStreamAudioArr[i6].audio_description + ", lang:" + ((Object) mtkTvHbbTVStreamAudioArr[i6].lang) + ", tag:" + mtkTvHbbTVStreamAudioArr[i6].tag + ", HI:" + mtkTvHbbTVStreamAudioArr[i6].hearing_impaired + "}");
        }
        return hbbtvRetExchangeData;
    }

    public HbbtvRet hbbtvAudioSetActive(MtkTvHbbTVStreamAudio[] mtkTvHbbTVStreamAudioArr, int i) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            Log.d(TAG, "Invalid arg, nmemb:" + i);
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        int i2 = (i * 10) + 2;
        int[] iArr = new int[i2];
        iArr[0] = i;
        iArr[1] = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = (i3 * 10) + 2;
            iArr[i4 + 0] = mtkTvHbbTVStreamAudioArr[i3].index;
            iArr[i4 + 1] = mtkTvHbbTVStreamAudioArr[i3].active ? 1 : 0;
            iArr[i4 + 2] = mtkTvHbbTVStreamAudioArr[i3].audio_description ? 1 : 0;
            for (int i5 = 0; i5 < 4; i5++) {
                iArr[i4 + 3 + i5] = mtkTvHbbTVStreamAudioArr[i3].lang[i5];
            }
            int i6 = i4 + 7;
            iArr[i6] = (int) (mtkTvHbbTVStreamAudioArr[i3].tag >> 32);
            iArr[i6 + 1] = (int) (mtkTvHbbTVStreamAudioArr[i3].tag & (-1));
            iArr[i4 + 9] = mtkTvHbbTVStreamAudioArr[i3].hearing_impaired ? 1 : 0;
        }
        Log.d(TAG, "before exchangeData");
        for (int i7 = 0; i7 < i2; i7++) {
            Log.d(TAG, "[" + i7 + "]->" + iArr[i7] + "\t");
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(14, iArr);
        if (hbbtvRetExchangeData == HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData");
        } else {
            Log.d(TAG, "after exchangeData, return failed");
        }
        return hbbtvRetExchangeData;
    }

    public HbbtvRet hbbtvSubtitleGetList(MtkTvHbbTVStreamSubtitle[] mtkTvHbbTVStreamSubtitleArr, int i, int[] iArr) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            Log.d(TAG, "Invalid arg, nmemb:" + i);
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        int i2 = (i * 10) + 2;
        int[] iArr2 = new int[i2];
        iArr2[0] = i;
        iArr2[1] = iArr[0];
        Log.d(TAG, "before exchangeData");
        for (int i3 = 0; i3 < i2; i3++) {
            Log.d(TAG, "[" + i3 + "]->" + iArr2[i3] + "\t");
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(15, iArr2);
        if (hbbtvRetExchangeData != HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData, return failed");
            return hbbtvRetExchangeData;
        }
        Log.d(TAG, "after exchangeData");
        for (int i4 = 0; i4 < i2; i4++) {
            Log.d(TAG, "[" + i4 + "]->" + iArr2[i4] + "\t");
        }
        int i5 = iArr2[0];
        iArr[0] = iArr2[1];
        Log.d(TAG, "after exchangeData, nmemb:" + i5 + ", ntotal[0]:" + iArr[0]);
        if (i5 > iArr[0]) {
            i5 = iArr[0];
        }
        for (int i6 = 0; i6 < i5; i6++) {
            mtkTvHbbTVStreamSubtitleArr[i6] = new MtkTvHbbTVStreamSubtitle();
            int i7 = (i6 * 10) + 2;
            mtkTvHbbTVStreamSubtitleArr[i6].index = iArr2[i7 + 0];
            mtkTvHbbTVStreamSubtitleArr[i6].active = iArr2[i7 + 1] != 0;
            mtkTvHbbTVStreamSubtitleArr[i6].type = iArr2[i7 + 2];
            mtkTvHbbTVStreamSubtitleArr[i6].attr = iArr2[i7 + 3];
            for (int i8 = 0; i8 < 4; i8++) {
                mtkTvHbbTVStreamSubtitleArr[i6].lang[i8] = (char) iArr2[i7 + 4 + i8];
            }
            int i9 = i7 + 8;
            mtkTvHbbTVStreamSubtitleArr[i6].tag = ((long) iArr2[i9]) << ((int) (((long) iArr2[i9 + 1]) + 32));
            Log.d(TAG, "strmSbtl[" + i6 + "]{idex:" + mtkTvHbbTVStreamSubtitleArr[i6].index + ", active:" + mtkTvHbbTVStreamSubtitleArr[i6].active + ", type:" + mtkTvHbbTVStreamSubtitleArr[i6].type + ", attr:" + mtkTvHbbTVStreamSubtitleArr[i6].attr + ", lang:" + ((Object) mtkTvHbbTVStreamSubtitleArr[i6].lang) + ", tag:" + mtkTvHbbTVStreamSubtitleArr[i6].tag + "}");
        }
        return hbbtvRetExchangeData;
    }

    public HbbtvRet hbbtvSubtitleSetActive(MtkTvHbbTVStreamSubtitle[] mtkTvHbbTVStreamSubtitleArr, int i) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            Log.d(TAG, "Invalid arg, nmemb:" + i);
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        int i2 = (i * 10) + 2;
        int[] iArr = new int[i2];
        iArr[0] = i;
        iArr[1] = 0;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = (i3 * 10) + 2;
            iArr[i4 + 0] = mtkTvHbbTVStreamSubtitleArr[i3].index;
            iArr[i4 + 1] = mtkTvHbbTVStreamSubtitleArr[i3].active ? 1 : 0;
            iArr[i4 + 2] = mtkTvHbbTVStreamSubtitleArr[i3].type;
            iArr[i4 + 3] = mtkTvHbbTVStreamSubtitleArr[i3].attr;
            for (int i5 = 0; i5 < 4; i5++) {
                iArr[i4 + 4 + i5] = mtkTvHbbTVStreamSubtitleArr[i3].lang[i5];
            }
            int i6 = i4 + 8;
            iArr[i6] = (int) (mtkTvHbbTVStreamSubtitleArr[i3].tag >> 32);
            iArr[i6 + 1] = (int) (mtkTvHbbTVStreamSubtitleArr[i3].tag & (-1));
        }
        Log.d(TAG, "before exchangeData");
        for (int i7 = 0; i7 < i2; i7++) {
            Log.d(TAG, "[" + i7 + "]->" + iArr[i7] + "\t");
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(16, iArr);
        if (hbbtvRetExchangeData == HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData");
        } else {
            Log.d(TAG, "after exchangeData, reture failed");
        }
        return hbbtvRetExchangeData;
    }

    public HbbtvRet getHBBTVDecryptionData(byte[] bArr, String str, int[] iArr, byte[] bArr2) {
        byte[] bArr3;
        int i;
        Log.d(TAG, "getHBBTVDecryptionData entered");
        byte[] bArr4 = {0};
        byte[] bArr5 = {0};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (iArr.length > 0 && str != null) {
            int i2 = iArr[0];
            if (i2 > 65536) {
                Log.d(TAG, "DecryptionData fileSize(" + i2 + ") is too bigger than 64KB.");
            } else if (i2 > 5120) {
                Log.d(TAG, "DecryptionData fileSize(" + i2 + ") is bigger than 7KB; Default will change to 7KB.");
                i2 = 5120;
            }
            int length = bArr == null ? 0 : bArr.length;
            int length2 = str.length();
            int i3 = (length2 / 4) + 1;
            int i4 = i3 * 4;
            int i5 = ((i2 / 4) + 1) * 4;
            StringBuilder sb = new StringBuilder();
            sb.append("DecryptionData(userKeyLen:");
            sb.append(length);
            sb.append("): occupy memory size (");
            byte[] bArr6 = bArr5;
            sb.append(16);
            sb.append(")\n");
            Log.d(TAG, sb.toString());
            Log.d(TAG, "DecryptionData(filePathLen:" + length2 + "): occupy memory size (" + i4 + ")\n");
            Log.d(TAG, "DecryptionData(decryptionDataLen:" + i2 + "): occupy memory size (" + i5 + ")\n");
            int[] iArr2 = new int[(((i4 + 24) + 4) + i5) / 4];
            Arrays.fill(iArr2, 0);
            iArr2[0] = length;
            if (length <= 0 || length > 16) {
                if (length == 0) {
                    Log.d(TAG, "DecryptionData userKey length is zero");
                } else {
                    Log.d(TAG, "DecryptionData userKey length is too bigger than DECRYPTION_USERKEYLEN(16), just set zero");
                }
                bArr3 = bArr4;
            } else {
                byte[] bArr7 = new byte[16];
                Arrays.fill(bArr7, (byte) 0);
                System.arraycopy(bArr, 0, bArr7, 0, length);
                bArr3 = bArr7;
            }
            int i6 = 0;
            int i7 = 1;
            while (i7 < 5 && length > 0) {
                int i8 = i6 + 1;
                iArr2[i7] = bArr3[i6] & 255;
                int i9 = i8 + 1;
                iArr2[i7] = iArr2[i7] | ((bArr3[i8] << 8) & 65280);
                int i10 = i9 + 1;
                iArr2[i7] = iArr2[i7] | ((bArr3[i9] << 16) & 16711680);
                iArr2[i7] = iArr2[i7] | ((bArr3[i10] << 24) & (-16777216));
                i7++;
                i6 = i10 + 1;
            }
            iArr2[5] = length2;
            if (length2 > 0) {
                byte[] bArr8 = new byte[i4];
                Arrays.fill(bArr8, (byte) 0);
                System.arraycopy(str.getBytes(), 0, bArr8, 0, length2);
                bArr6 = bArr8;
            } else {
                Log.d(TAG, "DecryptionData filePath is empty");
            }
            int i11 = 6;
            int i12 = 0;
            while (true) {
                i = 6 + i3;
                if (i11 >= i) {
                    break;
                }
                int i13 = i12 + 1;
                int i14 = i13 + 1;
                int i15 = bArr6[i12] + (bArr6[i13] << 8);
                int i16 = i14 + 1;
                iArr2[i11] = i15 + (bArr6[i14] << 16) + (bArr6[i16] << 24);
                i11++;
                i12 = i16 + 1;
            }
            iArr2[i] = i2;
            Log.d(TAG, "DecryptionData(index1:" + i + ")\n");
            HbbtvRet hbbtvRetExchangeData = exchangeData(25, iArr2);
            iArr[0] = iArr2[i];
            Arrays.fill(bArr2, (byte) 0);
            int i17 = i + 1;
            Log.d(TAG, "DecryptionData(index2:" + i17 + ")\n");
            if (iArr[0] <= i2) {
                int i18 = 0;
                int i19 = 0;
                for (char c = 0; i18 < (iArr[c] / 4) + 1 && i19 < iArr[c]; c = 0) {
                    int i20 = i19 + 1;
                    int i21 = i17 + i18;
                    bArr2[i19] = (byte) (iArr2[i21] & 255);
                    if (i20 >= iArr[c]) {
                        break;
                    }
                    int i22 = i20 + 1;
                    bArr2[i20] = (byte) ((iArr2[i21] >> 8) & 255);
                    if (i22 >= iArr[c]) {
                        break;
                    }
                    int i23 = i22 + 1;
                    bArr2[i22] = (byte) ((iArr2[i21] >> 16) & 255);
                    if (i23 >= iArr[c]) {
                        break;
                    }
                    i19 = i23 + 1;
                    bArr2[i23] = (byte) ((iArr2[i21] >> 24) & 255);
                    i18++;
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i24 = 0; i24 < 50 && i24 < iArr[0]; i24++) {
                stringBuffer.append((char) bArr2[i24]);
            }
            Log.d(TAG, "DecryptionData(src fileSize:" + i2 + "): dest fileSize (" + iArr[0] + ")\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("DecryptionData(testBuffer:");
            sb2.append(stringBuffer.substring(0));
            sb2.append(")\n");
            Log.d(TAG, sb2.toString());
            Log.d(TAG, "DecryptionData(userKey:" + bArr + ")\n");
            Log.d(TAG, "DecryptionData(filePath:" + str + ")\n");
            Log.d(TAG, "getHBBTVDecryptionData exit");
            return hbbtvRetExchangeData;
        }
        Log.d(TAG, "DecryptionData Error: fileSize is zero or filePath is null");
        Log.d(TAG, "getHBBTVDecryptionData exit");
        return hbbtvRet;
    }

    public HbbtvRet exchangeData(int i, int[] iArr) {
        Log.d(TAG, "exchangeData, exchangeType==" + i + ",data==" + iArr);
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (iArr == null) {
            iArr = new int[1];
        }
        int length = iArr.length + 2;
        if (length > EXCHANGE_MAX_LEN) {
            Log.d(TAG, "[Error]bigger than EXCHANGE_MAX_LEN\n");
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        int[] iArr2 = new int[length];
        int i2 = 0;
        iArr2[0] = length;
        iArr2[1] = i;
        int i3 = 0;
        for (int i4 = 2; i4 < length; i4++) {
            iArr2[i4] = iArr[i3];
            i3++;
        }
        int iHbbtvExchangeData_native = TVNativeWrapper.hbbtvExchangeData_native(iArr2);
        Log.d(TAG, "retTVNativeWrapper:" + iHbbtvExchangeData_native + "\n");
        for (int i5 = 2; i5 < length; i5++) {
            iArr[i2] = iArr2[i5];
            i2++;
        }
        if (iHbbtvExchangeData_native >= 0) {
            return HbbtvRet.HBBTV_RET_OK;
        }
        return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
    }
}
