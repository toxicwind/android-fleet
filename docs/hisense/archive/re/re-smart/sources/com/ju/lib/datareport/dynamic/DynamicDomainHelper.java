package com.ju.lib.datareport.dynamic;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import b.a.a.a.a;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.HttpManager;
import com.ju.lib.datacommunication.network.http.address.ThirdAddressResponse;
import com.ju.lib.datacommunication.network.http.builder.PostStringBuilder;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datareport.StrategyBean;
import com.ju.lib.utils.base.JuPlatException;
import com.ju.lib.utils.file.FileProcess;
import com.ju.lib.utils.log.LogUtil;
import e.b.b;
import e.b.d;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicDomainHelper {
    public static final String DYNAMIC_ANDROID_DOMAIN = "https://domain.intsmarthub.com/";
    public static final String DYNAMIC_ERROR = "error";
    public static final String DYNAMIC_SUCCESS = "SUCCESS";
    public static final String DYNAMIC_URL_SUFFIX = "1.0/bas/abroad/api/domainDetail/retrieve";
    public static final String DYNAMIC_VIDAA_DOMAIN = "https://domain.vidaahub.com/";
    public static final String DynamicPolicyUrlDomain = "https://policy-jrnl-ea.vidaahub.com/";
    public static final String DynamicPolicyUrlSuffix = "log/get_dotexception_strategy";
    public static final String FAILURE = "FAILURE";
    public static final String MD5KEY = "AOSj8JBr0IpUzv5w+hURR6W4oINsI4o5";
    public static final String NO_CHANGES = "no changes";
    public static final String TAG = "DynamicDomainHelper";
    public static DynamicUrl dynamicUrl = null;
    public static final String floorsPolicyUrlAndroidOver = "https://policy-jrnl.intsmarthub.com/";
    public static final String floorsPolicyUrlVidaaOver = "https://policy-jrnl.vidaahub.com/";
    public static final boolean isDebug = false;

    public static String createParams(DynamicRequestInfo dynamicRequestInfo) {
        d dVar = new d();
        try {
            dVar.a(Params.ACCESSTOKEN, (Object) dynamicRequestInfo.getAccessToken());
            dVar.a("deviceid", (Object) dynamicRequestInfo.getDeviceid());
            dVar.a("devicemsg", (Object) dynamicRequestInfo.getDevicemsg());
            dVar.a("tvversion", (Object) dynamicRequestInfo.getTvversion());
            dVar.a("brand", (Object) dynamicRequestInfo.getBrand());
            dVar.a("os", (Object) dynamicRequestInfo.getOs());
            dVar.a("capabilitycode", (Object) dynamicRequestInfo.getCapabilitycode());
            dVar.a("chipplatform", (Object) dynamicRequestInfo.getChipplatform());
            dVar.a("zone", (Object) dynamicRequestInfo.getZone());
            dVar.a("areacode", (Object) dynamicRequestInfo.getAreacode());
            dVar.a("md5", (Object) dynamicRequestInfo.getMd5());
            String string = dVar.toString();
            LogUtil.i(TAG, "createParams result:" + string);
            return string;
        } catch (b e2) {
            e2.printStackTrace();
            return DYNAMIC_ERROR;
        }
    }

    public static DynamicUrl getCacheDynamicUrl(Context context) {
        return parseDynamicResponse(context, readDynamicDomainCache(context));
    }

    public static String getDynamicDomainDistribution(Context context, DynamicConfigure dynamicConfigure) {
        String strCreateParams = createParams(dynamicConfigure.getDynamicRequestInfo());
        if (dynamicConfigure.getPlatformInfo() == 1) {
            return getDynamicDomainDistributionInfo(context, "https://domain.vidaahub.com/1.0/bas/abroad/api/domainDetail/retrieve", strCreateParams);
        }
        return dynamicConfigure.getPlatformInfo() == 2 ? getDynamicDomainDistributionInfo(context, "https://domain.intsmarthub.com/1.0/bas/abroad/api/domainDetail/retrieve", strCreateParams) : "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String getDynamicDomainDistributionInfo(Context context, String str, String str2) {
        LogUtil.i(TAG, a.a("getDynamicDomainDistributionInfo urlTem:", str));
        try {
            HiResponse hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) ((PostStringBuilder) HttpManager.getHttpApi().postString().addHeader("Accept", "application/json")).addHeader("X-Sign-For", HttpUtils.getApiGatewaySign(str2, MD5KEY).trim())).contentType("application/json").url(str)).content(str2).execute();
            int code = hiResponseExecute.getCode();
            if (!hiResponseExecute.isSuccessful()) {
                LogUtil.e("response fail,responseCode=" + code, new Object[0]);
                return DYNAMIC_ERROR;
            }
            String strString = hiResponseExecute.getBody().string();
            LogUtil.i(TAG, "getDynamicDomainDistributionInfo result:" + strString);
            return strString;
        } catch (HttpException e2) {
            LogUtil.e(e2.toString(), new Object[0]);
        }
    }

    public static DynamicUrl getDynamicUrl() {
        return dynamicUrl;
    }

    public static String getMd5FromCache(String str) {
        String str2;
        if (str != null && str.length() <= 1) {
            return "";
        }
        try {
            str2 = (String) ((d) new d(str).a(ThirdAddressResponse.PAYLOAD)).a("md5");
            try {
                LogUtil.i(TAG, "getMd5FromCache md5:" + str2);
            } catch (b e2) {
                e = e2;
                e.printStackTrace();
            } catch (ClassCastException e3) {
                e = e3;
                e.printStackTrace();
            }
        } catch (b e4) {
            e = e4;
            str2 = "";
        } catch (ClassCastException e5) {
            e = e5;
            str2 = "";
        }
        return str2;
    }

    public static DynamicUrl parseDynamicResponse(Context context, String str) {
        DynamicUrl cacheDynamicUrl = null;
        try {
            d dVar = new d(str);
            String str2 = (String) dVar.a(NotificationCompat.CATEGORY_STATUS);
            LogUtil.i(TAG, "parseJson status:" + str2);
            if (str2 != null && DYNAMIC_SUCCESS.equals(str2)) {
                String str3 = (String) dVar.a(NotificationCompat.CATEGORY_MESSAGE);
                if (str3 == null || !NO_CHANGES.equals(str3)) {
                    d dVar2 = (d) ((d) dVar.a(ThirdAddressResponse.PAYLOAD)).a("jrnl");
                    DynamicUrl dynamicUrl2 = new DynamicUrl(dVar2.h("ter"), dVar2.h("exc"), dVar2.h("excfile"), dVar2.h("hea"), dVar2.h("dot"), dVar2.h(StrategyBean.Columns.POLICY));
                    try {
                        LogUtil.i(TAG, "parseJson dynamicUrl:" + dynamicUrl2);
                        cacheDynamicUrl = dynamicUrl2;
                    } catch (b e2) {
                        e = e2;
                        cacheDynamicUrl = dynamicUrl2;
                        e.printStackTrace();
                    }
                } else {
                    cacheDynamicUrl = getCacheDynamicUrl(context);
                }
            }
        } catch (b e3) {
            e = e3;
        }
        return cacheDynamicUrl;
    }

    public static DynamicResponseResultType parseDynamicResponseType(Context context, String str) {
        DynamicResponseResultType dynamicResponseResultType = new DynamicResponseResultType();
        try {
            d dVar = new d(str);
            String str2 = (String) dVar.a(NotificationCompat.CATEGORY_STATUS);
            LogUtil.i(TAG, "parseDynamicResponseType status:" + str2);
            if (str2 == null || !DYNAMIC_SUCCESS.equals(str2)) {
                dynamicResponseResultType.setType(1);
            } else {
                String str3 = (String) dVar.a(NotificationCompat.CATEGORY_MESSAGE);
                if (str3 == null || !NO_CHANGES.equals(str3)) {
                    String str4 = (String) ((d) dVar.a(ThirdAddressResponse.PAYLOAD)).a("DomainArea");
                    LogUtil.i(TAG, "parseDynamicResponseType domainArea:" + str4);
                    if (str4 == null || str4.isEmpty()) {
                        dynamicResponseResultType.setType(3);
                    } else {
                        dynamicResponseResultType.setType(0);
                    }
                } else {
                    dynamicResponseResultType.setType(2);
                }
            }
        } catch (b e2) {
            e2.printStackTrace();
        }
        LogUtil.i(TAG, a.a("parseDynamicResponseType type:", dynamicResponseResultType));
        return dynamicResponseResultType;
    }

    public static String readDynamicDomainCache(Context context) {
        LogUtil.i(TAG, "readDynamicDomainCache");
        String str = context.getFilesDir().getPath() + "/json_dynamic_domain.xml";
        try {
            return !new File(str).exists() ? "" : FileProcess.readFromFile(str);
        } catch (JuPlatException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void saveJsonToCache(Context context, String str) {
        LogUtil.i(TAG, "saveJsonToCache");
        try {
            FileProcess.saveObjectToFile(str, context.getFilesDir().getPath() + "/json_dynamic_domain.xml");
        } catch (JuPlatException e2) {
            e2.printStackTrace();
        }
    }

    public static void setDynamicUrl(DynamicUrl dynamicUrl2) {
        LogUtil.i(TAG, a.a("setDynamicUrl dynamicUrl:", dynamicUrl2));
        dynamicUrl = dynamicUrl2;
    }
}
