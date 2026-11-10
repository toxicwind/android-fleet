package com.ju.lib.datacommunication.network.http.core;

import android.net.Uri;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f90a;
    private HiRequest$Method b = HiRequest$Method.GET;
    private g c = new g();
    private k d;
    private int e;
    private Object f;
    private m g;
    private int h;
    private com.ju.lib.datacommunication.network.http.core.signature.b i;

    private boolean o(HiRequest$Method hiRequest$Method) {
        return q(hiRequest$Method) || hiRequest$Method.equals(HiRequest$Method.DELETE);
    }

    private boolean q(HiRequest$Method hiRequest$Method) {
        return hiRequest$Method.equals(HiRequest$Method.POST) || hiRequest$Method.equals(HiRequest$Method.PUT) || hiRequest$Method.equals(HiRequest$Method.PATCH);
    }

    public l j(String str, String str2) {
        g gVar = this.c;
        if (str2 == null) {
            str2 = "";
        }
        gVar.b(str, str2);
        return this;
    }

    public n k() {
        if (this.f90a != null) {
            return new n(this, null);
        }
        throw new IllegalStateException("url == null");
    }

    public l l() {
        n(HiRequest$Method.GET, null);
        return this;
    }

    public l m(String str, String str2) {
        g gVar = this.c;
        if (str2 == null) {
            str2 = "";
        }
        gVar.f(str, str2);
        return this;
    }

    public l n(HiRequest$Method hiRequest$Method, k kVar) {
        if (hiRequest$Method == null) {
            throw new NullPointerException("method == null");
        }
        if (kVar != null && !o(hiRequest$Method)) {
            throw new IllegalArgumentException("method " + hiRequest$Method + " must not have a request body.");
        }
        if (kVar != null || !q(hiRequest$Method)) {
            this.b = hiRequest$Method;
            this.d = kVar;
            return this;
        }
        throw new IllegalArgumentException("method " + hiRequest$Method + " must have a request body.");
    }

    public l p(k kVar) {
        n(HiRequest$Method.POST, kVar);
        return this;
    }

    public l r(com.ju.lib.datacommunication.network.http.core.signature.b bVar) {
        this.i = bVar;
        return this;
    }

    public l s(Uri uri) {
        if (uri == null) {
            return this;
        }
        this.f90a = uri.toString();
        return this;
    }

    public l t(String str) {
        if (str == null) {
            return this;
        }
        s(Uri.parse(str));
        return this;
    }

    public l u(String str, Map map) {
        if (str == null) {
            return this;
        }
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                builderBuildUpon.appendQueryParameter((String) entry.getKey(), entry.getValue() == null ? "" : (String) entry.getValue());
            }
        }
        s(builderBuildUpon.build());
        return this;
    }
}
