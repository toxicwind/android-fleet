package com.hippo.unifile;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes6.dex */
class RawFile extends UniFile {
    private static final String TAG = "RawFile";
    private File mFile;

    RawFile(UniFile uniFile, File file) {
        super(uniFile);
        this.mFile = file;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean] */
    @Override // com.hippo.unifile.UniFile
    public UniFile createFile(String str) throws Throwable {
        FileOutputStream fileOutputStream;
        ?? r2 = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(this.mFile, str);
        ?? Exists = file.exists();
        try {
            if (Exists != 0) {
                if (file.isFile()) {
                    return new RawFile(this, file);
                }
                return null;
            }
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    RawFile rawFile = new RawFile(this, file);
                    IOUtils.closeQuietly(fileOutputStream);
                    return rawFile;
                } catch (IOException e) {
                    e = e;
                    Log.w(TAG, "Failed to createFile " + str + ": " + e);
                    IOUtils.closeQuietly(fileOutputStream);
                    return null;
                }
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(r2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r2 = Exists;
        }
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile createDirectory(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(this.mFile, str);
        if (file.isDirectory() || file.mkdirs()) {
            return new RawFile(this, file);
        }
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public Uri getUri() {
        return Uri.fromFile(this.mFile);
    }

    @Override // com.hippo.unifile.UniFile
    public String getName() {
        return this.mFile.getName();
    }

    @Override // com.hippo.unifile.UniFile
    public String getType() {
        if (this.mFile.isDirectory()) {
            return null;
        }
        return Utils.getTypeForName(this.mFile.getName());
    }

    @Override // com.hippo.unifile.UniFile
    public String getFilePath() {
        return this.mFile.getPath();
    }

    @Override // com.hippo.unifile.UniFile
    public boolean isDirectory() {
        return this.mFile.isDirectory();
    }

    @Override // com.hippo.unifile.UniFile
    public boolean isFile() {
        return this.mFile.isFile();
    }

    @Override // com.hippo.unifile.UniFile
    public long lastModified() {
        return this.mFile.lastModified();
    }

    @Override // com.hippo.unifile.UniFile
    public long length() {
        return this.mFile.length();
    }

    @Override // com.hippo.unifile.UniFile
    public boolean canRead() {
        return this.mFile.canRead();
    }

    @Override // com.hippo.unifile.UniFile
    public boolean canWrite() {
        return this.mFile.canWrite();
    }

    @Override // com.hippo.unifile.UniFile
    public boolean delete() {
        deleteContents(this.mFile);
        return this.mFile.delete();
    }

    @Override // com.hippo.unifile.UniFile
    public boolean exists() {
        return this.mFile.exists();
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile[] listFiles() {
        File[] fileArrListFiles = this.mFile.listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        int length = fileArrListFiles.length;
        UniFile[] uniFileArr = new UniFile[length];
        for (int i = 0; i < length; i++) {
            uniFileArr[i] = new RawFile(this, fileArrListFiles[i]);
        }
        return uniFileArr;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile[] listFiles(FilenameFilter filenameFilter) {
        if (filenameFilter == null) {
            return listFiles();
        }
        File[] fileArrListFiles = this.mFile.listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if (filenameFilter.accept(this, file.getName())) {
                arrayList.add(new RawFile(this, file));
            }
        }
        return (UniFile[]) arrayList.toArray(new UniFile[arrayList.size()]);
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile findFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(this.mFile, str);
        if (file.exists()) {
            return new RawFile(this, file);
        }
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean renameTo(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(this.mFile.getParentFile(), str);
        if (!this.mFile.renameTo(file)) {
            return false;
        }
        this.mFile = file;
        return true;
    }

    @Override // com.hippo.unifile.UniFile
    public OutputStream openOutputStream() throws IOException {
        return new FileOutputStream(this.mFile);
    }

    @Override // com.hippo.unifile.UniFile
    public OutputStream openOutputStream(boolean z) throws IOException {
        return new FileOutputStream(this.mFile, z);
    }

    @Override // com.hippo.unifile.UniFile
    public InputStream openInputStream() throws IOException {
        return new FileInputStream(this.mFile);
    }

    @Override // com.hippo.unifile.UniFile
    public UniRandomAccessFile createRandomAccessFile(String str) throws FileNotFoundException {
        return new RawRandomAccessFile(new RandomAccessFile(this.mFile, str));
    }

    private static boolean deleteContents(File file) {
        File[] fileArrListFiles = file.listFiles();
        boolean zDeleteContents = true;
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    zDeleteContents &= deleteContents(file2);
                }
                if (!file2.delete()) {
                    Log.w(TAG, "Failed to delete " + file2);
                    zDeleteContents = false;
                }
            }
        }
        return zDeleteContents;
    }
}
