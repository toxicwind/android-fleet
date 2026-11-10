package com.ju.lib.datacommunication.network.http.impl;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import okhttp3.N;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends b {
    private static List b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f144a;

    static {
        ArrayList arrayList = new ArrayList();
        b = arrayList;
        arrayList.add("lbgs.hismarttv.com");
        b.add("lbgs.hismarttv.com".toUpperCase());
    }

    public a(String str) {
        this.f144a = str;
        com.ju.lib.datacommunication.network.http.i.b.d(e(), "HostReplaceInterceptor(), systemSettingsHost = ", str);
    }

    @Override // okhttp3.O
    public c0 a(N n) {
        return TextUtils.isEmpty(this.f144a) ? n.f(n.a()) : f(n);
    }

    protected String d(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("[.]");
        if (strArrSplit.length < 3) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("hisense-");
        stringBuffer.append(strArrSplit[0]);
        int i = 1;
        while (true) {
            int length = strArrSplit.length - 2;
            stringBuffer.append(".");
            if (i >= length) {
                stringBuffer.append(this.f144a);
                return stringBuffer.toString();
            }
            stringBuffer.append(strArrSplit[i]);
            i++;
        }
    }

    protected abstract String e();

    protected abstract c0 f(N n);

    protected boolean g(String str) {
        return b.contains(str);
    }

    protected boolean h(String str) {
        return str.endsWith(".hismarttv.com") || str.endsWith(".hismarttv.com".toUpperCase());
    }
}
