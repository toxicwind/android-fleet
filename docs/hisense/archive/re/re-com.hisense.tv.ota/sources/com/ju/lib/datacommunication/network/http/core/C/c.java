package com.ju.lib.datacommunication.network.http.core.C;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f80a;
    final String b;

    c(String str, String str2) {
        try {
            this.f80a = URLEncoder.encode(str, Charset.defaultCharset().name());
            this.b = URLEncoder.encode(str2 == null ? "" : str2, Charset.defaultCharset().name());
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalArgumentException("name  = " + str + ", value = " + str2);
        }
    }

    String a() {
        return this.f80a;
    }

    String b() {
        return this.b;
    }
}
