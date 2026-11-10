package com.ju.lib.datacommunication.network.http.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f87a = new ArrayList();

    private boolean d(String str, String str2) {
        if (str == null) {
            com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.Stack", "checkNameAndValue name == null");
            return false;
        }
        if (str.isEmpty()) {
            com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.Stack", "checkNameAndValue name is empty");
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= ' ' || cCharAt >= 127) {
                com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.Stack", "checkNameAndValue " + String.format("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i), str));
                return false;
            }
        }
        if (str2 == null) {
            com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.Stack", "checkNameAndValue value == null");
            return false;
        }
        int length2 = str2.length();
        for (int i2 = 0; i2 < length2; i2++) {
            char cCharAt2 = str2.charAt(i2);
            if ((cCharAt2 <= 31 && cCharAt2 != '\t') || cCharAt2 >= 127) {
                com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.Stack", "checkNameAndValue " + String.format("Unexpected char %#04x at %d in %s value: %s", Integer.valueOf(cCharAt2), Integer.valueOf(i2), str, str2));
                return false;
            }
        }
        return true;
    }

    public g b(String str, String str2) {
        if (d(str, str2)) {
            this.f87a.add(new h(str, str2));
        }
        return this;
    }

    public i c() {
        return new i(this);
    }

    public g e(String str) {
        Iterator it = this.f87a.iterator();
        while (it.hasNext()) {
            if (((h) it.next()).f88a.equalsIgnoreCase(str)) {
                it.remove();
            }
        }
        return this;
    }

    public g f(String str, String str2) {
        if (d(str, str2)) {
            e(str);
            this.f87a.add(new h(str, str2));
        }
        return this;
    }
}
