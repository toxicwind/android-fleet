package com.hippo.unifile;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes6.dex */
class ResourceFile extends UniFile {
    private final int mId;
    private final String mName;
    private final String mP;
    private final Resources mR;

    @Override // com.hippo.unifile.UniFile
    public boolean canRead() {
        return true;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean canWrite() {
        return false;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile createDirectory(String str) {
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile createFile(String str) {
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean delete() {
        return false;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean exists() {
        return true;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile findFile(String str) {
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public String getFilePath() {
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean isDirectory() {
        return false;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean isFile() {
        return true;
    }

    @Override // com.hippo.unifile.UniFile
    public long lastModified() {
        return -1L;
    }

    @Override // com.hippo.unifile.UniFile
    public long length() {
        return -1L;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile[] listFiles() {
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile[] listFiles(FilenameFilter filenameFilter) {
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean renameTo(String str) {
        return false;
    }

    ResourceFile(Resources resources, String str, int i, String str2) {
        super(null);
        this.mR = resources;
        this.mP = str;
        this.mId = i;
        this.mName = str2;
    }

    @Override // com.hippo.unifile.UniFile
    public Uri getUri() {
        return new Uri.Builder().scheme("android.resource").authority(this.mP).path(Integer.toString(this.mId)).build();
    }

    @Override // com.hippo.unifile.UniFile
    public String getName() {
        return this.mName;
    }

    @Override // com.hippo.unifile.UniFile
    public String getType() {
        return "application/octet-stream";
    }

    @Override // com.hippo.unifile.UniFile
    public OutputStream openOutputStream() throws IOException {
        throw new IOException("Can't open OutputStream from resource file.");
    }

    @Override // com.hippo.unifile.UniFile
    public OutputStream openOutputStream(boolean z) throws IOException {
        throw new IOException("Can't open OutputStream from resource file.");
    }

    @Override // com.hippo.unifile.UniFile
    public InputStream openInputStream() throws IOException {
        try {
            return this.mR.openRawResource(this.mId);
        } catch (Resources.NotFoundException unused) {
            throw new IOException("Can't open InputStream");
        }
    }

    @Override // com.hippo.unifile.UniFile
    public UniRandomAccessFile createRandomAccessFile(String str) throws IOException {
        if (!"r".equals(str)) {
            throw new IOException("Unsupported mode: " + str);
        }
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = this.mR.openRawResourceFd(this.mId);
            if (assetFileDescriptorOpenRawResourceFd == null) {
                throw new IOException("Can't open AssetFileDescriptor");
            }
            return new RawRandomAccessFile(TrickRandomAccessFile.create(assetFileDescriptorOpenRawResourceFd, str));
        } catch (Resources.NotFoundException unused) {
            throw new IOException("Can't open AssetFileDescriptor");
        }
    }
}
