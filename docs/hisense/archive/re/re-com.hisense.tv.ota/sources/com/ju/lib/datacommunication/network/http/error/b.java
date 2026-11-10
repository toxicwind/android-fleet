package com.ju.lib.datacommunication.network.http.error;

import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f123a;
    private HashMap b = new HashMap();
    private NetworkErrorEnum c;
    private String d;
    private String e;

    public b(NetworkErrorEnum networkErrorEnum, String str) {
        if (TextUtils.isEmpty(str) || networkErrorEnum == null) {
            com.ju.lib.datacommunication.network.http.i.b.c("RequestFailedBean", " url or code cannot be null ");
        } else if (str.contains("?")) {
            String[] strArrSplit = str.split("\\?");
            this.f123a = strArrSplit[0];
            e(strArrSplit[1]);
        } else {
            this.f123a = str;
        }
        this.c = networkErrorEnum;
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split("&")) {
            String[] strArrSplit = str2.split("=");
            if (strArrSplit.length == 2 && !TextUtils.isEmpty(strArrSplit[0])) {
                this.b.put(strArrSplit[0], strArrSplit[1]);
            }
        }
        com.ju.lib.datacommunication.network.http.i.b.d("RequestFailedBean", " parseUrlQuery: ", this.b);
    }

    public b a(NetworkErrorEnum networkErrorEnum) {
        this.c = networkErrorEnum;
        return this;
    }

    public b b(String str) {
        this.e = str;
        return this;
    }

    public HashMap c() {
        return this.b;
    }

    public b d(String str) {
        return this;
    }

    public b f(HashMap map) {
        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
            com.ju.lib.datacommunication.network.http.i.b.d("RequestFailedBean", " requestParams: ", map);
        }
        if (map != null) {
            this.b.putAll(map);
        }
        return this;
    }

    public b g(String str) {
        this.d = str;
        return this;
    }

    public String h() {
        return this.f123a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RequestFailedBean{url=");
        sb.append(this.f123a);
        sb.append(", code=");
        sb.append(this.c);
        sb.append(", subCode=");
        sb.append(this.d);
        sb.append(", failMsg=");
        sb.append(this.e);
        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
            sb.append(", requestParams=");
            sb.append(this.b);
        }
        sb.append("}");
        return sb.toString();
    }
}
