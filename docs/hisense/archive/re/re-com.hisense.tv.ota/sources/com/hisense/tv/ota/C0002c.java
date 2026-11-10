package com.hisense.tv.ota;

import java.io.File;

/* JADX INFO: renamed from: com.hisense.tv.ota.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0002c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f33a;
    private boolean b;
    private VersionData c;
    private boolean d;
    private File e;
    private File f;
    private File g;
    private boolean h;
    private boolean i;
    private boolean j;

    public File a() {
        return this.g;
    }

    public File b() {
        return this.f;
    }

    public File c() {
        return this.e;
    }

    public int d() {
        return this.f33a;
    }

    public VersionData e() {
        return this.c;
    }

    public boolean f() {
        return this.b;
    }

    public boolean g() {
        return this.d;
    }

    public boolean h() {
        return this.j;
    }

    public boolean i() {
        return this.i;
    }

    public boolean j() {
        return this.h;
    }

    public void k() {
        this.c = null;
        this.e = null;
        this.f = null;
        this.d = false;
        this.h = false;
        this.j = false;
    }

    public synchronized void l(boolean z) {
        this.b = z;
    }

    public synchronized void m(boolean z) {
        this.d = z;
    }

    public void n(boolean z) {
        this.j = z;
    }

    public void o(File file) {
        this.g = file;
    }

    public void p(File file) {
        this.f = file;
    }

    public void q(boolean z) {
        this.i = z;
    }

    public void r(File file) {
        this.e = file;
    }

    public void s(boolean z) {
        this.h = z;
    }

    public synchronized void t(int i) {
        this.f33a = i;
    }

    public void u(VersionData versionData) {
        this.c = versionData;
    }
}
