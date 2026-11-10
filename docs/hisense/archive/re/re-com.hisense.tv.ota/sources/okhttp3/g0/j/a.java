package okhttp3.g0.j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okio.A;
import okio.B;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
class a implements b {
    a() {
    }

    @Override // okhttp3.g0.j.b
    public boolean a(File file) {
        return file.exists();
    }

    @Override // okhttp3.g0.j.b
    public void b(File file) throws IOException {
        if (file.delete() || !file.exists()) {
            return;
        }
        throw new IOException("failed to delete " + file);
    }

    @Override // okhttp3.g0.j.b
    public B c(File file) {
        return t.j(file);
    }

    @Override // okhttp3.g0.j.b
    public long d(File file) {
        return file.length();
    }

    @Override // okhttp3.g0.j.b
    public void e(File file, File file2) throws IOException {
        b(file2);
        if (file.renameTo(file2)) {
            return;
        }
        throw new IOException("failed to rename " + file + " to " + file2);
    }

    @Override // okhttp3.g0.j.b
    public A f(File file) {
        try {
            return t.f(file);
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            return t.f(file);
        }
    }

    @Override // okhttp3.g0.j.b
    public void g(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                g(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete " + file2);
            }
        }
    }

    @Override // okhttp3.g0.j.b
    public A h(File file) {
        try {
            return t.a(file);
        } catch (FileNotFoundException unused) {
            file.getParentFile().mkdirs();
            return t.a(file);
        }
    }
}
