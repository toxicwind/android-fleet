package com.ju.lib.datacommunication.network.http.debug;

import android.text.TextUtils;
import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DebugConfig {
    public List<String> paramRules;
    public Map<String, String> respRules;
    public List<Integer> statusCodeRules;
    public List<String> urlRules;

    /* JADX INFO: renamed from: com.ju.lib.datacommunication.network.http.debug.DebugConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType = new int[DebugCheckType.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.Param.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.RespBody.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.RespCode.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$debug$DebugCheckType[DebugCheckType.Url.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private String getValidParamsPreInfo(Map<String, String> map) {
        boolean z = false;
        if (map == null || this.paramRules == null) {
            HttpLog.w("DebugHelper", " map is null. ");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.paramRules) {
            if (TextUtils.isEmpty(map.get(str))) {
                sb.append(str);
                sb.append(",");
            }
        }
        if (!TextUtils.isEmpty(sb.toString())) {
            sb.append(" are null; \n");
        }
        HashSet hashSet = new HashSet();
        for (String str2 : map.keySet()) {
            if (!TextUtils.isEmpty(str2)) {
                if (hashSet.contains(str2.toLowerCase())) {
                    sb.append(str2);
                    sb.append(",");
                    z = true;
                } else {
                    hashSet.add(str2.toLowerCase());
                }
            }
        }
        if (z) {
            sb.append(" are duplicate \n");
        }
        if (TextUtils.isEmpty(sb.toString())) {
            return null;
        }
        return sb.toString();
    }

    private boolean isValidBody(String str) {
        if (str != null && this.respRules != null) {
            HttpLog.i("DebugHelper", a.a(" body: ", str));
            for (Map.Entry<String, String> entry : this.respRules.entrySet()) {
                StringBuilder sbA = a.a("\"");
                sbA.append((Object) entry.getKey());
                sbA.append("\":");
                sbA.append((Object) entry.getValue());
                String string = sbA.toString();
                StringBuilder sbA2 = a.a("\"");
                sbA2.append((Object) entry.getKey());
                sbA2.append("\":\"");
                sbA2.append((Object) entry.getValue());
                String string2 = sbA2.toString();
                StringBuilder sbA3 = a.a("<");
                sbA3.append((Object) entry.getKey());
                sbA3.append(">");
                sbA3.append((Object) entry.getValue());
                sbA3.append("</");
                String string3 = sbA3.toString();
                if (str.contains(string) || str.contains(string2) || str.contains(string3)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidStatusCode(int i) {
        List<Integer> list = this.statusCodeRules;
        return list != null && list.contains(Integer.valueOf(i));
    }

    private boolean isValidUrl(String str) {
        List<String> list = this.urlRules;
        return list != null && list.contains(str);
    }

    public String getDebugTracePreInfo(DebugCheckType debugCheckType, DebugTrace debugTrace) {
        boolean zIsValidUrl;
        int iOrdinal = debugCheckType.ordinal();
        if (iOrdinal == 0) {
            return getValidParamsPreInfo(debugTrace.requestParams);
        }
        if (iOrdinal == 1) {
            zIsValidUrl = isValidUrl(debugTrace.url);
        } else if (iOrdinal != 2) {
            zIsValidUrl = iOrdinal != 3 ? false : isValidBody(debugTrace.respBody);
        } else {
            zIsValidUrl = isValidStatusCode(debugTrace.respCode);
        }
        if (zIsValidUrl) {
            return "";
        }
        return null;
    }

    public String toString() {
        StringBuilder sbA = a.a("paramRules: ");
        sbA.append(this.paramRules);
        sbA.append("\nrespRules: ");
        sbA.append(this.respRules);
        sbA.append("\nstatusCodeRules: ");
        sbA.append(this.statusCodeRules);
        sbA.append("\nurlRules: ");
        return a.a(sbA, this.urlRules, "\n");
    }
}
