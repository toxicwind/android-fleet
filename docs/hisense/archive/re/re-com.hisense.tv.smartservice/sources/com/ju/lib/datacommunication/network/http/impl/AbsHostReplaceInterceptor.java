package com.ju.lib.datacommunication.network.http.impl;

import android.text.TextUtils;
import com.ju.lib.datacommunication.network.http.dns.GslbHostName;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsHostReplaceInterceptor extends AbsInterceptor {
    public static final String DES_HOST_PREFIX = "hisense-";
    public static final int MIN_VALID_HOST_LEVEL = 3;
    public static final String SOURCE_HOST_SUFFIX = ".hismarttv.com";
    public static List<String> sWhitList = new ArrayList();
    public final String mSystemSettingsHostSuffix;

    static {
        sWhitList.add(GslbHostName.DOMAIN_GSLB_SERVER);
        sWhitList.add(GslbHostName.DOMAIN_GSLB_SERVER.toUpperCase());
    }

    public AbsHostReplaceInterceptor(String str) {
        this.mSystemSettingsHostSuffix = str;
        HttpLog.i(getTag(), "HostReplaceInterceptor(), systemSettingsHost = ", str);
    }

    public String createNewHost(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("[.]");
        if (strArrSplit.length < 3) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DES_HOST_PREFIX);
        stringBuffer.append(strArrSplit[0]);
        for (int i = 1; i < strArrSplit.length - 2; i++) {
            stringBuffer.append(".");
            stringBuffer.append(strArrSplit[i]);
        }
        stringBuffer.append(".");
        stringBuffer.append(this.mSystemSettingsHostSuffix);
        return stringBuffer.toString();
    }

    public abstract String getTag();

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        return TextUtils.isEmpty(this.mSystemSettingsHostSuffix) ? chain.proceed(chain.request()) : interceptCustomImp(chain);
    }

    public abstract Response interceptCustomImp(Interceptor.Chain chain);

    public boolean isWhiteListHost(String str) {
        return sWhitList.contains(str);
    }

    public boolean needChangedHost(String str) {
        return str.endsWith(SOURCE_HOST_SUFFIX) || str.endsWith(SOURCE_HOST_SUFFIX.toUpperCase());
    }
}
