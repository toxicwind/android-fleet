package com.vt.tv.usbota;

import java.io.File;

/* JADX INFO: renamed from: com.vt.tv.usbota.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0000a {
    private boolean a;
    private File b;
    private boolean c;
    private String d;
    private String e;
    private String f;

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public File c() {
        return this.b;
    }

    public String d() {
        return this.f;
    }

    public boolean e() {
        return this.a;
    }

    public boolean f() {
        return this.c;
    }

    public void g() {
        this.b = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = false;
        this.c = false;
    }

    public synchronized void h(boolean z) {
    }

    public synchronized void i(boolean z) {
        this.a = z;
    }

    public void j(boolean z) {
        this.c = z;
    }

    public void k(String str) {
        this.d = str;
    }

    public void l(String str) {
        this.e = str;
    }

    public void m(File file) {
        this.b = file;
    }

    public void n(String str) {
        this.f = str;
    }
}
