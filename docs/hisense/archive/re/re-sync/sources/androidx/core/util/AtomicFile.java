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
    public final File mBackupName;
    public final File mBaseName;

    public AtomicFile(@NonNull File file) {
        this.mBaseName = file;
        this.mBackupName = new File(file.getPath() + ".bak");
    }

    public static boolean sync(@NonNull FileOutputStream fileOutputStream) {
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void delete() {
        this.mBaseName.delete();
        this.mBackupName.delete();
    }

    public void failWrite(@Nullable FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            sync(fileOutputStream);
            try {
                fileOutputStream.close();
                this.mBaseName.delete();
                this.mBackupName.renameTo(this.mBaseName);
            } catch (IOException e) {
                Log.w("AtomicFile", "failWrite: Got exception:", e);
            }
        }
    }

    public void finishWrite(@Nullable FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            sync(fileOutputStream);
            try {
                fileOutputStream.close();
                this.mBackupName.delete();
            } catch (IOException e) {
                Log.w("AtomicFile", "finishWrite: Got exception:", e);
            }
        }
    }

    @NonNull
    public File getBaseFile() {
        return this.mBaseName;
    }

    @NonNull
    public FileInputStream openRead() {
        if (this.mBackupName.exists()) {
            this.mBaseName.delete();
            this.mBackupName.renameTo(this.mBaseName);
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
        if (this.mBaseName.exists()) {
            if (this.mBackupName.exists()) {
                this.mBaseName.delete();
            } else if (!this.mBaseName.renameTo(this.mBackupName)) {
                StringBuilder sbC = a.c("Couldn't rename file ");
                sbC.append(this.mBaseName);
                sbC.append(" to backup file ");
                sbC.append(this.mBackupName);
                Log.w("AtomicFile", sbC.toString());
            }
        }
        try {
            return new FileOutputStream(this.mBaseName);
        } catch (FileNotFoundException unused) {
            if (!this.mBaseName.getParentFile().mkdirs()) {
                StringBuilder sbC2 = a.c("Couldn't create directory ");
                sbC2.append(this.mBaseName);
                throw new IOException(sbC2.toString());
            }
            try {
                return new FileOutputStream(this.mBaseName);
            } catch (FileNotFoundException unused2) {
                StringBuilder sbC3 = a.c("Couldn't create ");
                sbC3.append(this.mBaseName);
                throw new IOException(sbC3.toString());
            }
        }
    }
}
