package e.a.a.a;

import java.io.File;
import java.io.FileFilter;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements FileFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ File f2147a;

    public b(File file) {
        this.f2147a = file;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return file.equals(this.f2147a);
    }
}
