package com.hmct.cloud.sdk.utils;

import android.text.TextUtils;
import android.util.Log;
import com.ju.lib.datacommunication.network.http.dns.GslbHostName;
import com.ju.lib.datacommunication.network.http.impl.AbsHostReplaceInterceptor;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HostReplaceUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2082a = DeviceConfig.getProp("persist.his.domain");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static List<String> f2083b;

    static {
        ArrayList arrayList = new ArrayList();
        f2083b = arrayList;
        arrayList.add(GslbHostName.DOMAIN_GSLB_SERVER);
        f2083b.add(GslbHostName.DOMAIN_GSLB_SERVER.toUpperCase());
    }

    public static String a(String str, String str2) {
        String[] strArrSplit = str.split("[.]");
        if (strArrSplit.length < 3) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(AbsHostReplaceInterceptor.DES_HOST_PREFIX);
        stringBuffer.append(strArrSplit[0]);
        for (int i = 1; i < strArrSplit.length - 2; i++) {
            stringBuffer.append(".");
            stringBuffer.append(strArrSplit[i]);
        }
        stringBuffer.append(".");
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }

    public static String b(String str, String str2) {
        if (str.equals(str2) || str.indexOf("://") == -1) {
            return str;
        }
        String[] strArrSplit = str.split("://");
        String strA = b.a.a.a.a.a((str2.contains("http") || str2.contains("https")) ? "" : b.a.a.a.a.a(new StringBuilder(), strArrSplit[0], "://"), str2);
        if (strArrSplit.length > 0 && strArrSplit[1].indexOf("/") != -1) {
            String[] strArrSplit2 = strArrSplit[1].split("/");
            if (strArrSplit2.length > 1) {
                String string = strA;
                for (int i = 1; i < strArrSplit2.length; i++) {
                    StringBuilder sbB = b.a.a.a.a.b(string, "/");
                    sbB.append(strArrSplit2[i]);
                    string = sbB.toString();
                }
                strA = string;
            }
        }
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i("HTTP2.AbsInterceptor", "replace url success url:", str, ",\n newUrl is;", strA);
        }
        return strA;
    }

    public static String hostReplace(String str) {
        if (!TextUtils.isEmpty(f2082a) && !TextUtils.isEmpty(str)) {
            try {
                String host = new URL(str).getHost();
                if (f2083b.contains(host)) {
                    return str;
                }
                if (!host.endsWith(AbsHostReplaceInterceptor.SOURCE_HOST_SUFFIX) && !host.endsWith(AbsHostReplaceInterceptor.SOURCE_HOST_SUFFIX.toUpperCase())) {
                    return str;
                }
                String strB = b(str, a(host, f2082a));
                Log.i("HTTP2.HostReplace", "intercept(), newUrl = " + strB);
                return strB;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str;
    }
}
