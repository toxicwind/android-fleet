package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.google.common.base.Ascii;
import com.hisense.avmiddleware.HisHdmiApi;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvHBBTVBase {
    public static final int AUDIO_PRELOAD_HEAD_LEN = 2;
    public static final int AUDIO_PRELOAD_INFO_ACTIVE = 1;
    public static final int AUDIO_PRELOAD_INFO_AD = 2;
    public static final int AUDIO_PRELOAD_INFO_HI = 9;
    public static final int AUDIO_PRELOAD_INFO_INDEX = 0;
    public static final int AUDIO_PRELOAD_INFO_LANG = 3;
    public static final int AUDIO_PRELOAD_INFO_LANG_LEN = 4;
    public static final int AUDIO_PRELOAD_INFO_LEN = 10;
    public static final int AUDIO_PRELOAD_INFO_TAG = 7;
    public static final int AUDIO_PRELOAD_INFO_TAG_LEN = 2;
    public static final int AUDIO_PRELOAD_NMEMB = 0;
    public static final int AUDIO_PRELOAD_TOTAL = 1;
    public static final int DECRYPTION_FILE_MAX_LEN = 65536;
    public static final int DECRYPTION_USERKEYLEN = 16;
    public static final int EXCHANGE_FUNC_AUDIO_GET_LIST = 13;
    public static final int EXCHANGE_FUNC_AUDIO_SET_ACTIVE = 14;
    public static final int EXCHANGE_FUNC_DIALOG_FB = 10;
    public static final int EXCHANGE_FUNC_ENABLE = 1;
    public static final int EXCHANGE_FUNC_GET_COOKIE = 22;
    public static final int EXCHANGE_FUNC_GET_DECRYPTION_DATA = 25;
    public static final int EXCHANGE_FUNC_GET_FVP_SUPPORT = 18;
    public static final int EXCHANGE_FUNC_GET_HBBTV_SUPPORT = 17;
    public static final int EXCHANGE_FUNC_GET_HBBTV_VERSION = 19;
    public static final int EXCHANGE_FUNC_GET_MDS_APP_LIST = 27;
    public static final int EXCHANGE_FUNC_GET_MDS_AUTH_URL = 23;
    public static final int EXCHANGE_FUNC_GET_USER_AGENT = 21;
    public static final int EXCHANGE_FUNC_KILL_APP = 9;
    public static final int EXCHANGE_FUNC_LAUNCH_APP = 8;
    public static final int EXCHANGE_FUNC_RESET_APR_MNGR_DB = 11;
    public static final int EXCHANGE_FUNC_SET_EXTERNAL_DATA = 24;
    public static final int EXCHANGE_FUNC_SET_HBBTV_VERSION = 20;
    public static final int EXCHANGE_FUNC_START = 2;
    public static final int EXCHANGE_FUNC_STOP = 3;
    public static final int EXCHANGE_FUNC_SUBTITLE_GET_LIST = 15;
    public static final int EXCHANGE_FUNC_SUBTITLE_SET_ACTIVE = 16;
    public static final int EXCHANGE_FUNC_TYPE_UNKNOWN = 0;
    public static final int EXCHANGE_FUNC_UPDATE_MDS_APP_LIST = 26;
    public static final int EXCHANGE_HEADER_TYPE_IDX = 1;
    public static final int EXCHANGE_HEAD_LEN = 2;
    public static final int EXCHANGE_MAX_HEAD_LEN = 128;
    public static final int EXCHANGE_MAX_LEN = 1408;
    public static final int EXCHANGE_PAYLOAD_1ST_IDX = 2;
    public static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    public static final int HBBTV_COOKIE_MAX_LEN = 1028;
    public static final int HBBTV_USER_AGENT_MAX_LEN = 1028;
    public static final int MDS_AUTH_URL_MAX_LEN = 1028;
    public static final int MDS_FVP_APP_LIST_MAX_LEN = 5124;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DB_ERASE_MANUFACTURE_DATA = 1;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DB_ERASE_USER_DATA = 2;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DIALOG_VALUE_ALWAYS = 2;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DIALOG_VALUE_CANCEL = 0;
    public static final int MTKTVAPI_HBBTV_APR_MNGR_DIALOG_VALUE_ONCE = 1;
    public static final int SBTL_PRELOAD_HEAD_LEN = 2;
    public static final int SBTL_PRELOAD_INFO_ACTIVE = 1;
    public static final int SBTL_PRELOAD_INFO_ATTR = 3;
    public static final int SBTL_PRELOAD_INFO_INDEX = 0;
    public static final int SBTL_PRELOAD_INFO_LANG = 4;
    public static final int SBTL_PRELOAD_INFO_LANG_LEN = 4;
    public static final int SBTL_PRELOAD_INFO_LEN = 10;
    public static final int SBTL_PRELOAD_INFO_TAG = 8;
    public static final int SBTL_PRELOAD_INFO_TAG_LEN = 2;
    public static final int SBTL_PRELOAD_INFO_TYPE = 2;
    public static final int SBTL_PRELOAD_NMEMB = 0;
    public static final int SBTL_PRELOAD_TOTAL = 1;
    public static final String TAG = "MtkTvHBBTVBase";

    public enum ExternalDataType {
        EXTERNAL_DATA_TYPE_LIVE_TV,
        EXTERNAL_DATA_TYPE_3RD_APP_LAUNCHER,
        EXTERNAL_DATA_TYPE_EPG,
        EXTERNAL_DATA_TYPE_RECOMMEND_PAGE
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

        public int index;

        HBBTV_STREAM_SUBTITLE_TYPE_T(int i) {
            this.index = 0;
            this.index = i;
        }

        public int idOf() {
            return this.index;
        }
    }

    public enum HBBTV_SUBTITLE_ATTR_T {
        HBBTV_SUBTITLE_ATTR_NORMAL(0),
        HBBTV_ATTR_HEARING_IMPAIRED(1),
        HBBTV_SUBTITLE_ATTR_UNKNOWN(2);

        public int index;

        HBBTV_SUBTITLE_ATTR_T(int i) {
            this.index = 0;
            this.index = i;
        }

        public int idOf() {
            return this.index;
        }
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

    public MtkTvHBBTVBase() {
        Log.d(TAG, "MtkTvHBBTVBase object created");
    }

    public HbbtvRet dialogFeedBack(int i) {
        Log.d(TAG, "hbbtv enable entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        Log.i(TAG, "hbbtv dialog enable:" + i);
        HbbtvRet hbbtvRetExchangeData = exchangeData(10, new int[]{i});
        Log.d(TAG, "hbbtv dialog enable exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet disableHbbtvBy3rdApp() {
        Log.d(TAG, "disableHbbtvBy3rdApp entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_3RD_APP_LAUNCHER.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvBy3rdApp exit");
        return externalData;
    }

    public HbbtvRet disableHbbtvByEPG() {
        Log.d(TAG, "disableHbbtvByEPG entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_EPG.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvByEPG exit");
        return externalData;
    }

    public HbbtvRet disableHbbtvByLiveTV() {
        Log.d(TAG, "disableHbbtvByLiveTV entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_LIVE_TV.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvByLiveTV exit");
        return externalData;
    }

    public HbbtvRet disableHbbtvByRecommendPage() {
        Log.d(TAG, "disableHbbtvByRecommendPage entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_RECOMMEND_PAGE.ordinal(), new int[]{0});
        Log.d(TAG, "disableHbbtvByRecommendPage exit");
        return externalData;
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

    public HbbtvRet enableHbbtvBy3rdApp() {
        Log.d(TAG, "enableHbbtvBy3rdApp entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_3RD_APP_LAUNCHER.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvBy3rdApp exit");
        return externalData;
    }

    public HbbtvRet enableHbbtvByEPG() {
        Log.d(TAG, "enableHbbtvByEPG entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_EPG.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvByEPG exit");
        return externalData;
    }

    public HbbtvRet enableHbbtvByLiveTV() {
        Log.d(TAG, "enableHbbtvByLiveTV entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_LIVE_TV.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvByLiveTV exit");
        return externalData;
    }

    public HbbtvRet enableHbbtvByRecommendPage() {
        Log.d(TAG, "enableHbbtvByRecommendPage entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet externalData = setExternalData(ExternalDataType.EXTERNAL_DATA_TYPE_RECOMMEND_PAGE.ordinal(), new int[]{1});
        Log.d(TAG, "enableHbbtvByRecommendPage exit");
        return externalData;
    }

    public HbbtvRet eraseApvMngrDBData(int i) {
        Log.d(TAG, "eraseApvMngrDBData");
        int[] iArr = {i};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(11, iArr);
        Log.d(TAG, "eraseApvMngrDBData exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet exchangeData(int i, int[] iArr) {
        Log.d(TAG, "exchangeData, exchangeType==" + i + ",data==" + iArr);
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (iArr == null) {
            iArr = new int[1];
        }
        int length = iArr.length + 2;
        if (length > 1408) {
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
        a.a("retTVNativeWrapper:", iHbbtvExchangeData_native, "\n", TAG);
        for (int i5 = 2; i5 < length; i5++) {
            iArr[i2] = iArr2[i5];
            i2++;
        }
        return iHbbtvExchangeData_native >= 0 ? HbbtvRet.HBBTV_RET_OK : HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
    }

    public HbbtvRet getFVPAppList(StringBuffer stringBuffer) {
        Log.d(TAG, "getFVPAppList entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr = new int[HisHdmiApi.HD_HDMI_LIST_HDMI_INFO];
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
        StringBuilder sbA = a.a("getFVPAppList(size:");
        sbA.append(stringBuffer.length());
        sbA.append("): AppList(");
        sbA.append(stringBuffer.toString());
        sbA.append(")\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "getFVPAppList exit");
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
        StringBuilder sbA = a.a("getFVPAuthUrl(size:");
        sbA.append(stringBuffer.length());
        sbA.append("): AuthUrl(");
        sbA.append(stringBuffer.toString());
        sbA.append(")\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "getFVPAuthUrl exit");
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
        StringBuilder sbA = a.a("getFVPSupport:");
        sbA.append(zArr[0]);
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "getFVPSupport exit");
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
        StringBuilder sbA = a.a("getHBBTVCookie(size:");
        sbA.append(stringBuffer.length());
        sbA.append("): Cookie(");
        sbA.append(stringBuffer.substring(0));
        sbA.append(")\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "getHBBTVCookie exit");
        return hbbtvRetExchangeData;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0042  */
    /* JADX WARN: Code duplicated, block: B:19:0x00bf A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:20:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:21:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:30:0x0116  */
    /* JADX WARN: Code duplicated, block: B:31:0x0126  */
    /* JADX WARN: Code duplicated, block: B:35:0x0132 A[LOOP:1: B:33:0x012e->B:35:0x0132, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:38:0x0173  */
    /* JADX WARN: Code duplicated, block: B:66:0x0151 A[EDGE_INSN: B:66:0x0151->B:36:0x0151 BREAK  A[LOOP:1: B:33:0x012e->B:35:0x0132], SYNTHETIC] */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:40:0x017b
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public com.mediatek.twoworlds.tv.MtkTvHBBTVBase.HbbtvRet getHBBTVDecryptionData(byte[] r21, java.lang.String r22, int[] r23, byte[] r24) {
        /*
            Method dump skipped, instruction units count: 589
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvHBBTVBase.getHBBTVDecryptionData(byte[], java.lang.String, int[], byte[]):com.mediatek.twoworlds.tv.MtkTvHBBTVBase$HbbtvRet");
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
        StringBuilder sbA = a.a("getHBBTVSupport:");
        sbA.append(zArr[0]);
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "getHBBTVSupport exit");
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
        StringBuilder sbA = a.a("getHBBTVUserAgent(size:");
        sbA.append(stringBuffer.length());
        sbA.append("): UserAgent(");
        sbA.append(stringBuffer.substring(0));
        sbA.append(")\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "getHBBTVUserAgent exit");
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
        StringBuilder sbA = a.a("getHBBTVVersion(size:", i, "):");
        sbA.append((int) bArr[0]);
        sbA.append(".");
        sbA.append((int) bArr[1]);
        sbA.append(".");
        sbA.append((int) bArr[2]);
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "getHBBTVVersion exit");
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

    /* JADX WARN: Multi-variable type inference failed */
    public HbbtvRet hbbtvAudioGetList(MtkTvHbbTVStreamAudio[] mtkTvHbbTVStreamAudioArr, int i, int[] iArr) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            a.b("Invalid arg, nmemb:", i, TAG);
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        int i2 = (i * 10) + 2;
        int[] iArr2 = new int[i2];
        int i3 = 0;
        iArr2[0] = i;
        int i4 = 1;
        iArr2[1] = iArr[0];
        Log.d(TAG, "before exchangeData");
        for (int iA = 0; iA < i2; iA = a.a(a.a("[", iA, "]->"), iArr2[iA], "\t", TAG, iA, 1)) {
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(13, iArr2);
        if (hbbtvRetExchangeData != HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData, return failed");
            return hbbtvRetExchangeData;
        }
        Log.d(TAG, "after exchangeData");
        for (int iA2 = 0; iA2 < i2; iA2 = a.a(a.a("[", iA2, "]->"), iArr2[iA2], "\t", TAG, iA2, 1)) {
        }
        int i5 = iArr2[0];
        iArr[0] = iArr2[1];
        a.b(a.a("after exchangeData, nmemb:", i5, ", ntotal[0]:"), iArr[0], TAG);
        if (i5 > iArr[0]) {
            i5 = iArr[0];
        }
        int i6 = 0;
        while (i3 < i5) {
            mtkTvHbbTVStreamAudioArr[i3] = new MtkTvHbbTVStreamAudio();
            int i7 = (i3 * 10) + 2;
            mtkTvHbbTVStreamAudioArr[i3].index = iArr2[i7 + 0];
            mtkTvHbbTVStreamAudioArr[i3].active = iArr2[i7 + 1] == 0 ? i6 : i4;
            mtkTvHbbTVStreamAudioArr[i3].audio_description = iArr2[i7 + 2] == 0 ? i6 : i4;
            while (i6 < 4) {
                mtkTvHbbTVStreamAudioArr[i3].lang[i6] = (char) iArr2[i7 + 3 + i6];
                i6++;
            }
            int i8 = i7 + 7;
            mtkTvHbbTVStreamAudioArr[i3].tag = ((long) iArr2[i8]) << ((int) (((long) iArr2[i8 + i4]) + 32));
            mtkTvHbbTVStreamAudioArr[i3].hearing_impaired = iArr2[i7 + 9] != 0;
            StringBuilder sbA = a.a("strmAudio[", i3, "]{idex:");
            sbA.append(mtkTvHbbTVStreamAudioArr[i3].index);
            sbA.append(", active:");
            sbA.append(mtkTvHbbTVStreamAudioArr[i3].active);
            sbA.append(", ad:");
            sbA.append(mtkTvHbbTVStreamAudioArr[i3].audio_description);
            sbA.append(", lang:");
            sbA.append((Object) mtkTvHbbTVStreamAudioArr[i3].lang);
            sbA.append(", tag:");
            sbA.append(mtkTvHbbTVStreamAudioArr[i3].tag);
            sbA.append(", HI:");
            sbA.append(mtkTvHbbTVStreamAudioArr[i3].hearing_impaired);
            sbA.append("}");
            Log.d(TAG, sbA.toString());
            i3++;
            i6 = 0;
            i4 = 1;
        }
        return hbbtvRetExchangeData;
    }

    public HbbtvRet hbbtvAudioSetActive(MtkTvHbbTVStreamAudio[] mtkTvHbbTVStreamAudioArr, int i) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            a.b("Invalid arg, nmemb:", i, TAG);
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
        for (int iA = 0; iA < i2; iA = a.a(a.a("[", iA, "]->"), iArr[iA], "\t", TAG, iA, 1)) {
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(14, iArr);
        if (hbbtvRetExchangeData == HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData");
        } else {
            Log.d(TAG, "after exchangeData, return failed");
        }
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

    public HbbtvRet hbbtvSubtitleGetList(MtkTvHbbTVStreamSubtitle[] mtkTvHbbTVStreamSubtitleArr, int i, int[] iArr) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            a.b("Invalid arg, nmemb:", i, TAG);
            return HbbtvRet.HBBTV_RET_INTERNAL_ERROR;
        }
        int i2 = (i * 10) + 2;
        int[] iArr2 = new int[i2];
        iArr2[0] = i;
        iArr2[1] = iArr[0];
        Log.d(TAG, "before exchangeData");
        for (int iA = 0; iA < i2; iA = a.a(a.a("[", iA, "]->"), iArr2[iA], "\t", TAG, iA, 1)) {
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(15, iArr2);
        if (hbbtvRetExchangeData != HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData, return failed");
            return hbbtvRetExchangeData;
        }
        Log.d(TAG, "after exchangeData");
        for (int iA2 = 0; iA2 < i2; iA2 = a.a(a.a("[", iA2, "]->"), iArr2[iA2], "\t", TAG, iA2, 1)) {
        }
        int i3 = iArr2[0];
        iArr[0] = iArr2[1];
        a.b(a.a("after exchangeData, nmemb:", i3, ", ntotal[0]:"), iArr[0], TAG);
        if (i3 > iArr[0]) {
            i3 = iArr[0];
        }
        for (int i4 = 0; i4 < i3; i4++) {
            mtkTvHbbTVStreamSubtitleArr[i4] = new MtkTvHbbTVStreamSubtitle();
            int i5 = (i4 * 10) + 2;
            mtkTvHbbTVStreamSubtitleArr[i4].index = iArr2[i5 + 0];
            mtkTvHbbTVStreamSubtitleArr[i4].active = iArr2[i5 + 1] != 0;
            mtkTvHbbTVStreamSubtitleArr[i4].type = iArr2[i5 + 2];
            mtkTvHbbTVStreamSubtitleArr[i4].attr = iArr2[i5 + 3];
            for (int i6 = 0; i6 < 4; i6++) {
                mtkTvHbbTVStreamSubtitleArr[i4].lang[i6] = (char) iArr2[i5 + 4 + i6];
            }
            int i7 = i5 + 8;
            mtkTvHbbTVStreamSubtitleArr[i4].tag = ((long) iArr2[i7]) << ((int) (((long) iArr2[i7 + 1]) + 32));
            StringBuilder sbA = a.a("strmSbtl[", i4, "]{idex:");
            sbA.append(mtkTvHbbTVStreamSubtitleArr[i4].index);
            sbA.append(", active:");
            sbA.append(mtkTvHbbTVStreamSubtitleArr[i4].active);
            sbA.append(", type:");
            sbA.append(mtkTvHbbTVStreamSubtitleArr[i4].type);
            sbA.append(", attr:");
            sbA.append(mtkTvHbbTVStreamSubtitleArr[i4].attr);
            sbA.append(", lang:");
            sbA.append((Object) mtkTvHbbTVStreamSubtitleArr[i4].lang);
            sbA.append(", tag:");
            sbA.append(mtkTvHbbTVStreamSubtitleArr[i4].tag);
            sbA.append("}");
            Log.d(TAG, sbA.toString());
        }
        return hbbtvRetExchangeData;
    }

    public HbbtvRet hbbtvSubtitleSetActive(MtkTvHbbTVStreamSubtitle[] mtkTvHbbTVStreamSubtitleArr, int i) {
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        if (i <= 0) {
            a.b("Invalid arg, nmemb:", i, TAG);
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
        for (int iA = 0; iA < i2; iA = a.a(a.a("[", iA, "]->"), iArr[iA], "\t", TAG, iA, 1)) {
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(16, iArr);
        if (hbbtvRetExchangeData == HbbtvRet.HBBTV_RET_OK) {
            Log.d(TAG, "after exchangeData");
        } else {
            Log.d(TAG, "after exchangeData, reture failed");
        }
        return hbbtvRetExchangeData;
    }

    public HbbtvRet killHBBTVApp() {
        Log.d(TAG, "killHBBTVApp entered");
        byte[] bArr = {0, 0, 1, 0};
        int[] iArr = {0, 6};
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        iArr[0] = bArr[0] + (bArr[1] << 8) + (bArr[2] << Ascii.DLE) + (bArr[3] << Ascii.CAN);
        HbbtvRet hbbtvRetExchangeData = exchangeData(9, iArr);
        Log.d(TAG, "killHBBTVApp exit");
        return hbbtvRetExchangeData;
    }

    public HbbtvRet launchHBBTVApp(int i, String str) {
        Log.d(TAG, "launchHBBTVApp entered");
        int i2 = 0;
        new int[1][0] = 0;
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int length = str.length();
        int i3 = (length / 4) + 1;
        int[] iArr = new int[i3 + 1];
        iArr[0] = i;
        byte[] bArr = new byte[i3 * 4];
        Arrays.fill(bArr, (byte) 0);
        System.arraycopy(str.getBytes(), 0, bArr, 0, length);
        int i4 = 1;
        while (i4 <= i3) {
            int i5 = i2 + 1;
            int i6 = i5 + 1;
            int i7 = bArr[i2] + (bArr[i5] << 8);
            int i8 = i6 + 1;
            iArr[i4] = i7 + (bArr[i6] << Ascii.DLE) + (bArr[i8] << Ascii.CAN);
            i4++;
            i2 = i8 + 1;
        }
        HbbtvRet hbbtvRetExchangeData = exchangeData(8, iArr);
        StringBuilder sbA = a.a("launchHBBTVApp type(", i, "), url[0...3](");
        sbA.append(iArr[1]);
        sbA.append("), url(");
        sbA.append(str);
        sbA.append(").\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "launchHBBTVApp exit");
        return hbbtvRetExchangeData;
    }

    public int setAudioDescription(int i) {
        Log.d(TAG, "setAudioDescription hbbtv entered");
        int iHbbtvSetAudioDescription_native = TVNativeWrapper.hbbtvSetAudioDescription_native(i);
        Log.d(TAG, "setAudioDescription hbbtv exit");
        return iHbbtvSetAudioDescription_native;
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

    public HbbtvRet setExternalData(int i, int[] iArr) {
        Log.d(TAG, "setExternalData entered");
        new int[1][0] = 0;
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        int[] iArr2 = new int[iArr.length + 1];
        Arrays.fill(iArr2, 0);
        iArr2[0] = i;
        System.arraycopy(iArr, 0, iArr2, 1, iArr.length);
        HbbtvRet hbbtvRetExchangeData = exchangeData(24, iArr2);
        Log.d(TAG, "setExternalData exit");
        return hbbtvRetExchangeData;
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
        StringBuilder sbA = a.a("setHBBTVVersion(size:", i, "):");
        sbA.append(iArr[0]);
        sbA.append(".");
        sbA.append(iArr[1]);
        sbA.append(".");
        sbA.append(iArr[2]);
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "setHBBTVVersion exit");
        return hbbtvRetExchangeData;
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

    public HbbtvRet updateFVPAppList() {
        Log.d(TAG, "update FVP App List entered");
        HbbtvRet hbbtvRet = HbbtvRet.HBBTV_RET_OK;
        HbbtvRet hbbtvRetExchangeData = exchangeData(26, null);
        Log.d(TAG, "update FVP App List exit");
        return hbbtvRetExchangeData;
    }

    public int setDefaultSubtitleLang(String str) {
        return setDefaultSubtitleLang(str, 1);
    }

    public static class MtkTvHbbTVStreamAudio {
        public boolean active;
        public boolean audio_description;
        public boolean hearing_impaired;
        public int index;
        public char[] lang = {0, 0, 0, 0};
        public long tag;

        public MtkTvHbbTVStreamAudio() {
            this.index = 0;
            this.active = false;
            this.audio_description = false;
            this.hearing_impaired = false;
            this.tag = 0L;
            this.index = 0;
            this.active = false;
            this.audio_description = false;
            this.hearing_impaired = false;
            this.lang[0] = 0;
            this.tag = 0L;
        }

        public MtkTvHbbTVStreamAudio(MtkTvHbbTVStreamAudio mtkTvHbbTVStreamAudio) {
            this.index = 0;
            this.active = false;
            this.audio_description = false;
            this.hearing_impaired = false;
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

    public static class MtkTvHbbTVStreamSubtitle {
        public boolean active;
        public int attr;
        public int index;
        public char[] lang = {0, 0, 0, 0};
        public long tag;
        public int type;

        public MtkTvHbbTVStreamSubtitle() {
            this.index = 0;
            this.active = false;
            this.type = 0;
            this.attr = 2;
            this.tag = 0L;
            this.index = 0;
            this.active = false;
            this.type = 0;
            this.attr = 2;
            this.lang[0] = 0;
            this.tag = 0L;
        }

        public MtkTvHbbTVStreamSubtitle(MtkTvHbbTVStreamSubtitle mtkTvHbbTVStreamSubtitle) {
            this.index = 0;
            this.active = false;
            this.type = 0;
            this.attr = 2;
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
}
