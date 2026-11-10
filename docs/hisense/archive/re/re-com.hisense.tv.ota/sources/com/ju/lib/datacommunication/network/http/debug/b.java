package com.ju.lib.datacommunication.network.http.debug;

import android.text.TextUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List f104a;
    Map b;
    List c;
    List d;

    private String b(Map map) {
        boolean z = false;
        if (map == null || this.f104a == null) {
            com.ju.lib.datacommunication.network.http.i.b.g("DebugHelper", " map is null. ");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.f104a) {
            if (TextUtils.isEmpty((CharSequence) map.get(str))) {
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

    private boolean c(String str) {
        if (str != null && this.b != null) {
            com.ju.lib.datacommunication.network.http.i.b.d("DebugHelper", " body: " + str);
            for (Map.Entry entry : this.b.entrySet()) {
                String str2 = "\"" + entry.getKey() + "\":" + entry.getValue();
                String str3 = "\"" + entry.getKey() + "\":\"" + entry.getValue();
                String str4 = "<" + entry.getKey() + ">" + entry.getValue() + "</";
                if (str.contains(str2) || str.contains(str3) || str.contains(str4)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean d(int i) {
        List list = this.c;
        return list != null && list.contains(Integer.valueOf(i));
    }

    private boolean e(String str) {
        List list = this.d;
        return list != null && list.contains(str);
    }

    String a(DebugCheckType debugCheckType, i iVar) {
        boolean zC;
        int i = a.f103a[debugCheckType.ordinal()];
        if (i == 1) {
            return b(iVar.e);
        }
        if (i == 2) {
            zC = c(iVar.f);
        } else if (i != 3) {
            zC = i != 4 ? false : e(iVar.c);
        } else {
            zC = d(iVar.d);
        }
        if (zC) {
            return "";
        }
        return null;
    }

    public String toString() {
        return "paramRules: " + this.f104a + "\nrespRules: " + this.b + "\nstatusCodeRules: " + this.c + "\nurlRules: " + this.d + "\n";
    }
}
