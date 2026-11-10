package androidx.core.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class AtomicFile {
    public static final String LOG_TAG = "AtomicFile";
    public final File mBaseName;
    public final File mLegacyBackupName;
    public final File mNewName;

    public AtomicFile(@NonNull File baseName) {
        this.mBaseName = baseName;
        this.mNewName = new File(baseName.getPath() + ".new");
        this.mLegacyBackupName = new File(baseName.getPath() + ".bak");
    }

    public static void rename(@NonNull File source, @NonNull File target) {
        if (target.isDirectory() && !target.delete()) {
            a.c("Failed to delete file which is a directory ", target, LOG_TAG);
        }
        if (source.renameTo(target)) {
            return;
        }
        Log.e(LOG_TAG, "Failed to rename " + source + " to " + target);
    }

    public static boolean sync(@NonNull FileOutputStream stream) {
        try {
            stream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void delete() {
        this.mBaseName.delete();
        this.mNewName.delete();
        this.mLegacyBackupName.delete();
    }

    public void failWrite(@Nullable FileOutputStream str) {
        if (str == null) {
            return;
        }
        if (!sync(str)) {
            Log.e(LOG_TAG, "Failed to sync file output stream");
        }
        try {
            str.close();
        } catch (IOException e2) {
            Log.e(LOG_TAG, "Failed to close file output stream", e2);
        }
        if (this.mNewName.delete()) {
            return;
        }
        StringBuilder sbA = a.a("Failed to delete new file ");
        sbA.append(this.mNewName);
        Log.e(LOG_TAG, sbA.toString());
    }

    public void finishWrite(@Nullable FileOutputStream str) {
        if (str == null) {
            return;
        }
        if (!sync(str)) {
            Log.e(LOG_TAG, "Failed to sync file output stream");
        }
        try {
            str.close();
        } catch (IOException e2) {
            Log.e(LOG_TAG, "Failed to close file output stream", e2);
        }
        rename(this.mNewName, this.mBaseName);
    }

    @NonNull
    public File getBaseFile() {
        return this.mBaseName;
    }

    @NonNull
    public FileInputStream openRead() {
        if (this.mLegacyBackupName.exists()) {
            rename(this.mLegacyBackupName, this.mBaseName);
        }
        if (this.mNewName.exists() && this.mBaseName.exists() && !this.mNewName.delete()) {
            StringBuilder sbA = a.a("Failed to delete outdated new file ");
            sbA.append(this.mNewName);
            Log.e(LOG_TAG, sbA.toString());
        }
        return new FileInputStream(this.mBaseName);
    }

    @NonNull
    public byte[] readFully() throws IOException {
        FileInputStream fileInputStreamOpenRead = openRead();
        try {
            byte[] bArr = new byte[fileInputStreamOpenRead.available()];
            int i = 0;
            while (true) {
                int i2 = fileInputStreamOpenRead.read(bArr, i, bArr.length - i);
                if (i2 <= 0) {
                    fileInputStreamOpenRead.close();
                    return bArr;
                }
                i += i2;
                int iAvailable = fileInputStreamOpenRead.available();
                if (iAvailable > bArr.length - i) {
                    byte[] bArr2 = new byte[iAvailable + i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            }
        } catch (Throwable th) {
            fileInputStreamOpenRead.close();
            throw th;
        }
    }

    @NonNull
    public FileOutputStream startWrite() throws IOException {
        if (this.mLegacyBackupName.exists()) {
            rename(this.mLegacyBackupName, this.mBaseName);
        }
        try {
            return new FileOutputStream(this.mNewName);
        } catch (FileNotFoundException unused) {
            if (!this.mNewName.getParentFile().mkdirs()) {
                StringBuilder sbA = a.a("Failed to create directory for ");
                sbA.append(this.mNewName);
                throw new IOException(sbA.toString());
            }
            try {
                return new FileOutputStream(this.mNewName);
            } catch (FileNotFoundException e2) {
                StringBuilder sbA2 = a.a("Failed to create new file ");
                sbA2.append(this.mNewName);
                throw new IOException(sbA2.toString(), e2);
            }
        }
    }
}
