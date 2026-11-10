package com.ju.lib.datacommunication.network.http.core;

/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final String f88a;
    final String b;

    h(String str, String str2) {
        this.f88a = str;
        this.b = str2;
    }

    private boolean a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return a(hVar.f88a, this.f88a) && a(hVar.b, this.b);
    }
}
