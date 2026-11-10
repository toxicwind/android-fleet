package okhttp3.internal.io;

import b.a.a.a.a;
import d.B;
import d.C;
import d.t;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() { // from class: okhttp3.internal.io.FileSystem.1
        @Override // okhttp3.internal.io.FileSystem
        public B appendingSink(File file) {
            try {
                return t.a(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return t.a(file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException(a.a("failed to delete ", file));
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public void deleteContents(File file) throws IOException {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                throw new IOException(a.a("not a readable directory: ", file));
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    deleteContents(file2);
                }
                if (!file2.delete()) {
                    throw new IOException(a.a("failed to delete ", file2));
                }
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public boolean exists(File file) {
            return file.exists();
        }

        @Override // okhttp3.internal.io.FileSystem
        public void rename(File file, File file2) throws IOException {
            delete(file2);
            if (file.renameTo(file2)) {
                return;
            }
            throw new IOException("failed to rename " + file + " to " + file2);
        }

        @Override // okhttp3.internal.io.FileSystem
        public B sink(File file) {
            try {
                return t.b(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return t.b(file);
            }
        }

        @Override // okhttp3.internal.io.FileSystem
        public long size(File file) {
            return file.length();
        }

        @Override // okhttp3.internal.io.FileSystem
        public C source(File file) {
            return t.c(file);
        }
    };

    B appendingSink(File file);

    void delete(File file);

    void deleteContents(File file);

    boolean exists(File file);

    void rename(File file, File file2);

    B sink(File file);

    long size(File file);

    C source(File file);
}
