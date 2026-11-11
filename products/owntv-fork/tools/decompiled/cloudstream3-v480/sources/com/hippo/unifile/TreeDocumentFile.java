package com.hippo.unifile;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes6.dex */
class TreeDocumentFile extends UniFile {
    private static final String TAG = "TreeDocumentFile";
    private final Context mContext;
    private String mName;
    private Uri mUri;

    TreeDocumentFile(UniFile uniFile, Context context, Uri uri) {
        this(uniFile, context, uri, null);
    }

    TreeDocumentFile(UniFile uniFile, Context context, Uri uri, String str) {
        super(uniFile);
        this.mContext = context.getApplicationContext();
        this.mUri = uri;
        this.mName = str;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile createFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        UniFile uniFileFindFile = findFile(str);
        if (uniFileFindFile != null) {
            if (uniFileFindFile.isFile()) {
                return uniFileFindFile;
            }
            Log.w(TAG, "Try to create file " + str + ", but it is not file");
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf > 0) {
            String strSubstring = str.substring(0, iLastIndexOf);
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(iLastIndexOf + 1));
            if (!TextUtils.isEmpty(mimeTypeFromExtension)) {
                Uri uriCreateFile = DocumentsContractApi21.createFile(this.mContext, this.mUri, mimeTypeFromExtension, strSubstring);
                if (uriCreateFile != null) {
                    return new TreeDocumentFile(this, this.mContext, uriCreateFile);
                }
                return null;
            }
        }
        Uri uriCreateFile2 = DocumentsContractApi21.createFile(this.mContext, this.mUri, "application/octet-stream", str);
        if (uriCreateFile2 != null) {
            return new TreeDocumentFile(this, this.mContext, uriCreateFile2);
        }
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile createDirectory(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        UniFile uniFileFindFile = findFile(str);
        if (uniFileFindFile != null) {
            if (uniFileFindFile.isDirectory()) {
                return uniFileFindFile;
            }
            return null;
        }
        Uri uriCreateDirectory = DocumentsContractApi21.createDirectory(this.mContext, this.mUri, str);
        if (uriCreateDirectory != null) {
            return new TreeDocumentFile(this, this.mContext, uriCreateDirectory);
        }
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public Uri getUri() {
        return this.mUri;
    }

    @Override // com.hippo.unifile.UniFile
    public String getName() {
        if (this.mName == null) {
            this.mName = DocumentsContractApi19.getName(this.mContext, this.mUri);
        }
        return this.mName;
    }

    @Override // com.hippo.unifile.UniFile
    public String getType() {
        String type = DocumentsContractApi19.getType(this.mContext, this.mUri);
        return !TextUtils.isEmpty(type) ? type : Utils.getTypeForName(getName());
    }

    @Override // com.hippo.unifile.UniFile
    public String getFilePath() {
        return DocumentsContractApi19.getFilePath(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public long length() {
        if (isDirectory()) {
            return -1L;
        }
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public boolean delete() {
        invalidateName();
        return DocumentsContractApi19.delete(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile[] listFiles() {
        if (!isDirectory()) {
            return null;
        }
        NamedUri[] namedUriArrListFilesNamed = DocumentsContractApi21.listFilesNamed(this.mContext, this.mUri);
        UniFile[] uniFileArr = new UniFile[namedUriArrListFilesNamed.length];
        int length = namedUriArrListFilesNamed.length;
        for (int i = 0; i < length; i++) {
            NamedUri namedUri = namedUriArrListFilesNamed[i];
            uniFileArr[i] = new TreeDocumentFile(this, this.mContext, namedUri.uri, namedUri.name);
        }
        return uniFileArr;
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile[] listFiles(FilenameFilter filenameFilter) {
        if (filenameFilter == null) {
            return listFiles();
        }
        if (!isDirectory()) {
            return null;
        }
        NamedUri[] namedUriArrListFilesNamed = DocumentsContractApi21.listFilesNamed(this.mContext, this.mUri);
        ArrayList arrayList = new ArrayList();
        for (NamedUri namedUri : namedUriArrListFilesNamed) {
            if (namedUri.name != null && filenameFilter.accept(this, namedUri.name)) {
                arrayList.add(new TreeDocumentFile(this, this.mContext, namedUri.uri, namedUri.name));
            }
        }
        return (UniFile[]) arrayList.toArray(new UniFile[arrayList.size()]);
    }

    @Override // com.hippo.unifile.UniFile
    public UniFile findFile(String str) {
        if (TextUtils.isEmpty(str) || !isDirectory()) {
            return null;
        }
        TreeDocumentFile treeDocumentFile = new TreeDocumentFile(this, this.mContext, DocumentsContract.buildDocumentUriUsingTree(this.mUri, DocumentsContract.getDocumentId(this.mUri) + "/" + str), str);
        if (treeDocumentFile.exists()) {
            return treeDocumentFile;
        }
        return null;
    }

    @Override // com.hippo.unifile.UniFile
    public boolean renameTo(String str) {
        invalidateName();
        Uri uriRenameTo = DocumentsContractApi21.renameTo(this.mContext, this.mUri, str);
        if (uriRenameTo == null) {
            return false;
        }
        this.mUri = uriRenameTo;
        return true;
    }

    @Override // com.hippo.unifile.UniFile
    public OutputStream openOutputStream() throws IOException {
        if (isDirectory()) {
            throw new IOException("Can't open OutputStream from a directory");
        }
        try {
            OutputStream outputStreamOpenOutputStream = this.mContext.getContentResolver().openOutputStream(this.mUri);
            if (outputStreamOpenOutputStream != null) {
                return outputStreamOpenOutputStream;
            }
            throw new IOException("Can't open OutputStream");
        } catch (Exception unused) {
            throw new IOException("Can't open OutputStream");
        }
    }

    @Override // com.hippo.unifile.UniFile
    public OutputStream openOutputStream(boolean z) throws IOException {
        if (isDirectory()) {
            throw new IOException("Can't open OutputStream from a directory");
        }
        try {
            OutputStream outputStreamOpenOutputStream = this.mContext.getContentResolver().openOutputStream(this.mUri, z ? "wa" : "w");
            if (outputStreamOpenOutputStream != null) {
                return outputStreamOpenOutputStream;
            }
            throw new IOException("Can't open OutputStream");
        } catch (Exception unused) {
            throw new IOException("Can't open OutputStream");
        }
    }

    @Override // com.hippo.unifile.UniFile
    public InputStream openInputStream() throws IOException {
        if (isDirectory()) {
            throw new IOException("Can't open InputStream from a directory");
        }
        try {
            InputStream inputStreamOpenInputStream = this.mContext.getContentResolver().openInputStream(this.mUri);
            if (inputStreamOpenInputStream != null) {
                return inputStreamOpenInputStream;
            }
            throw new IOException("Can't open InputStream");
        } catch (Exception unused) {
            throw new IOException("Can't open InputStream");
        }
    }

    @Override // com.hippo.unifile.UniFile
    public UniRandomAccessFile createRandomAccessFile(String str) throws IOException {
        if (!isFile()) {
            throw new IOException("Can't make sure it is file");
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(this.mUri, str);
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                throw new IOException("Can't open ParcelFileDescriptor");
            }
            return new RawRandomAccessFile(TrickRandomAccessFile.create(parcelFileDescriptorOpenFileDescriptor, str));
        } catch (Exception unused) {
            throw new IOException("Can't open ParcelFileDescriptor");
        }
    }

    private void invalidateName() {
        this.mName = null;
    }
}
