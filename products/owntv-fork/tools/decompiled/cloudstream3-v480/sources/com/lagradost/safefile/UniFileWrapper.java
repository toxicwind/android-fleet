package com.lagradost.safefile;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import coil3.util.UtilsKt;
import com.hippo.unifile.UniFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UniFileWrapper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0012\u0010\f\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u001a\u0010\u0014\u001a\u00020\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\nH\u0016J\b\u0010\u0016\u001a\u00020\nH\u0016J\b\u0010\u0017\u001a\u00020\nH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0016J\u0012\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010%\u001a\u00020\u000eH\u0016J\b\u0010&\u001a\u00020'H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/lagradost/safefile/UniFileWrapper;", "Lcom/lagradost/safefile/SafeFile;", "context", "Landroid/content/Context;", UtilsKt.SCHEME_FILE, "Lcom/hippo/unifile/UniFile;", "(Landroid/content/Context;Lcom/hippo/unifile/UniFile;)V", "contentResolver", "Landroid/content/ContentResolver;", "canReadOrThrow", "", "canWriteOrThrow", "createDirectoryOrThrow", "directoryName", "", "createFileOrThrow", "displayName", "deleteOrThrow", "existsOrThrow", "filePathOrThrow", "findFileOrThrow", "ignoreCase", "isDirectoryOrThrow", "isFileOrThrow", "lastModifiedOrThrow", "", "lengthOrThrow", "listFilesOrThrow", "", "nameOrThrow", "openInputStreamOrThrow", "Ljava/io/InputStream;", "openOutputStreamOrThrow", "Ljava/io/OutputStream;", "append", "renameToOrThrow", "name", "typeOrThrow", "uriOrThrow", "Landroid/net/Uri;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UniFileWrapper implements SafeFile {
    private final ContentResolver contentResolver;
    private final Context context;
    private final UniFile file;

    public UniFileWrapper(Context context, UniFile file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        this.context = context;
        this.file = file;
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "context.contentResolver");
        this.contentResolver = contentResolver;
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean canRead() {
        return SafeFile.DefaultImpls.canRead(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean canWrite() {
        return SafeFile.DefaultImpls.canWrite(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createDirectory(String str) {
        return SafeFile.DefaultImpls.createDirectory(this, str);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createFile(String str) {
        return SafeFile.DefaultImpls.createFile(this, str);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean delete() {
        return SafeFile.DefaultImpls.delete(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean exists() {
        return SafeFile.DefaultImpls.exists(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public String filePath() {
        return SafeFile.DefaultImpls.filePath(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile findFile(String str, boolean z) {
        return SafeFile.DefaultImpls.findFile(this, str, z);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile gotoDirectory(String str, boolean z) {
        return SafeFile.DefaultImpls.gotoDirectory(this, str, z);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean isDirectory() {
        return SafeFile.DefaultImpls.isDirectory(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean isFile() {
        return SafeFile.DefaultImpls.isFile(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Long lastModified() {
        return SafeFile.DefaultImpls.lastModified(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Long length() {
        return SafeFile.DefaultImpls.length(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public List<SafeFile> listFiles() {
        return SafeFile.DefaultImpls.listFiles(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public String name() {
        return SafeFile.DefaultImpls.name(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public InputStream openInputStream() {
        return SafeFile.DefaultImpls.openInputStream(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public OutputStream openOutputStream(boolean z) {
        return SafeFile.DefaultImpls.openOutputStream(this, z);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Boolean renameTo(String str) {
        return SafeFile.DefaultImpls.renameTo(this, str);
    }

    @Override // com.lagradost.safefile.SafeFile
    public String type() {
        return SafeFile.DefaultImpls.type(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public Uri uri() {
        return SafeFile.DefaultImpls.uri(this);
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createFileOrThrow(String displayName) throws IOException {
        UniFile uniFileCreateFile = this.file.createFile(displayName);
        if (uniFileCreateFile != null) {
            return UniFileWrapperKt.toFile(uniFileCreateFile, this.context);
        }
        throw new IOException("Unable to create file");
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile createDirectoryOrThrow(String directoryName) throws IOException {
        UniFile uniFileCreateDirectory = this.file.createDirectory(directoryName);
        if (uniFileCreateDirectory != null) {
            return UniFileWrapperKt.toFile(uniFileCreateDirectory, this.context);
        }
        throw new IOException("Unable to create directory");
    }

    @Override // com.lagradost.safefile.SafeFile
    public Uri uriOrThrow() {
        Uri uri = this.file.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "file.uri");
        return uri;
    }

    @Override // com.lagradost.safefile.SafeFile
    public String nameOrThrow() throws IOException {
        String name = this.file.getName();
        if (name != null) {
            return name;
        }
        throw new IOException("Null name");
    }

    @Override // com.lagradost.safefile.SafeFile
    public String typeOrThrow() throws IOException {
        String type = this.file.getType();
        if (type != null) {
            return type;
        }
        throw new IOException("Null type");
    }

    @Override // com.lagradost.safefile.SafeFile
    public String filePathOrThrow() throws IOException {
        String filePath = this.file.getFilePath();
        if (filePath != null) {
            return filePath;
        }
        throw new IOException("Null filepath");
    }

    @Override // com.lagradost.safefile.SafeFile
    /* JADX INFO: renamed from: isDirectoryOrThrow */
    public boolean getIsDir() {
        return this.file.isDirectory();
    }

    @Override // com.lagradost.safefile.SafeFile
    /* JADX INFO: renamed from: isFileOrThrow */
    public boolean getIsFile() {
        return this.file.isFile();
    }

    @Override // com.lagradost.safefile.SafeFile
    public long lastModifiedOrThrow() {
        return this.file.lastModified();
    }

    @Override // com.lagradost.safefile.SafeFile
    public long lengthOrThrow() {
        try {
            long length = this.file.length();
            if (length == -1) {
                throw new IOException("-1 Length");
            }
            if (length > 1) {
                return length;
            }
            InputStream inputStreamOpenInputStreamOrThrow = openInputStreamOrThrow();
            try {
                return inputStreamOpenInputStreamOrThrow.available();
            } finally {
                SafeFileKt.closeQuietly(inputStreamOpenInputStreamOrThrow);
            }
        } catch (Throwable th) {
            Long l = (Long) UniFileWrapperKt.safe(new Function0<Long>() { // from class: com.lagradost.safefile.UniFileWrapper.lengthOrThrow.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Long invoke() throws FileNotFoundException {
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = UniFileWrapper.this.contentResolver.openFileDescriptor(UniFileWrapper.this.uriOrThrow(), "r");
                    try {
                        ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorOpenFileDescriptor;
                        Long lValueOf = parcelFileDescriptor != null ? Long.valueOf(parcelFileDescriptor.getStatSize()) : null;
                        CloseableKt.closeFinally(parcelFileDescriptorOpenFileDescriptor, null);
                        return lValueOf;
                    } catch (Throwable th2) {
                        try {
                            throw th2;
                        } catch (Throwable th3) {
                            CloseableKt.closeFinally(parcelFileDescriptorOpenFileDescriptor, th2);
                            throw th3;
                        }
                    }
                }
            });
            if (l != null) {
                return l.longValue();
            }
            throw th;
        }
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean canReadOrThrow() {
        return this.file.canRead();
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean canWriteOrThrow() {
        return this.file.canWrite();
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean deleteOrThrow() {
        try {
            return this.file.delete();
        } catch (Throwable th) {
            Uri uri = uri();
            if (uri != null) {
                return this.contentResolver.delete(uri, null, null) > 0;
            }
            throw th;
        }
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean existsOrThrow() {
        return this.file.exists();
    }

    @Override // com.lagradost.safefile.SafeFile
    public List<SafeFile> listFilesOrThrow() throws FileNotFoundException {
        SafeFile file;
        UniFile[] uniFileArrListFiles = this.file.listFiles();
        if (uniFileArrListFiles == null) {
            throw new FileNotFoundException();
        }
        ArrayList arrayList = new ArrayList();
        for (UniFile it : uniFileArrListFiles) {
            if (it != null) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                file = UniFileWrapperKt.toFile(it, this.context);
            } else {
                file = null;
            }
            if (file != null) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    @Override // com.lagradost.safefile.SafeFile
    public SafeFile findFileOrThrow(String displayName, boolean ignoreCase) throws FileNotFoundException {
        UniFile uniFileFindFile = this.file.findFile(displayName);
        if (uniFileFindFile != null) {
            return UniFileWrapperKt.toFile(uniFileFindFile, this.context);
        }
        throw new FileNotFoundException();
    }

    @Override // com.lagradost.safefile.SafeFile
    public boolean renameToOrThrow(String name) {
        return this.file.renameTo(name);
    }

    @Override // com.lagradost.safefile.SafeFile
    public OutputStream openOutputStreamOrThrow(boolean append) {
        try {
            OutputStream outputStreamOpenOutputStream = this.file.openOutputStream(append);
            Intrinsics.checkNotNullExpressionValue(outputStreamOpenOutputStream, "file.openOutputStream(append)");
            return outputStreamOpenOutputStream;
        } catch (Throwable th) {
            Uri uri = uri();
            if (uri != null) {
                try {
                    OutputStream outputStreamOpenOutputStream2 = this.contentResolver.openOutputStream(uri, append ? "wa" : "wt");
                    if (outputStreamOpenOutputStream2 != null) {
                        return outputStreamOpenOutputStream2;
                    }
                } catch (Throwable unused) {
                    Unit unit = Unit.INSTANCE;
                }
            }
            throw th;
        }
    }

    @Override // com.lagradost.safefile.SafeFile
    public InputStream openInputStreamOrThrow() {
        try {
            InputStream inputStreamOpenInputStream = this.file.openInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStreamOpenInputStream, "{\n            file.openInputStream()\n        }");
            return inputStreamOpenInputStream;
        } catch (Throwable th) {
            InputStream inputStream = (InputStream) UniFileWrapperKt.safe(new Function0<InputStream>() { // from class: com.lagradost.safefile.UniFileWrapper.openInputStreamOrThrow.1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final InputStream invoke() {
                    return UniFileWrapper.this.contentResolver.openInputStream(UniFileWrapper.this.uriOrThrow());
                }
            });
            if (inputStream != null) {
                return inputStream;
            }
            throw th;
        }
    }
}
