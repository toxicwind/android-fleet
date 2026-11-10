package com.hisense.tv.ota.W;

import java.io.File;
import java.io.FileFilter;

/* JADX INFO: loaded from: classes.dex */
class h implements FileFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f29a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;

    h(String str, String str2, boolean z, String str3, String str4) {
        this.f29a = str;
        this.b = str2;
        this.c = z;
        this.d = str3;
        this.e = str4;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        if (!file.isFile() || (!name.matches(this.f29a) && !name.matches(this.b))) {
            return false;
        }
        if (this.c) {
            return (name.equalsIgnoreCase(this.d) || name.equalsIgnoreCase(this.e)) ? false : true;
        }
        return true;
    }
}
