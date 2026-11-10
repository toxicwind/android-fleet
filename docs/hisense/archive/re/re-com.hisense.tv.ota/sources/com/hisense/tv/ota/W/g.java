package com.hisense.tv.ota.W;

import java.io.File;
import java.io.FileFilter;

/* JADX INFO: loaded from: classes.dex */
class g implements FileFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f28a;
    final /* synthetic */ String b;

    g(String str, String str2) {
        this.f28a = str;
        this.b = str2;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        String name = file.getName();
        if (file.isFile()) {
            return name.matches(this.f28a) || name.matches(this.b);
        }
        return false;
    }
}
