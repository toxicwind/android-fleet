package com.google.firebase.firestore.util;

import android.annotation.TargetApi;
import android.os.Build;
import b.a.a.a.a;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(26)
    public static class DefaultFileDeleter {
        public static void delete(File file) throws IOException {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException e2) {
                throw new IOException("Failed to delete file " + file + ": " + e2);
            }
        }
    }

    private static class LegacyFileDeleter {
        public static void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException(a.a("Failed to delete file ", file));
            }
        }
    }

    public static void delete(File file) throws IOException {
        int i = Build.VERSION.SDK_INT;
        DefaultFileDeleter.delete(file);
    }
}
