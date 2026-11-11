package com.lagradost.nicehttp;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NiceFile.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\u000bB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/lagradost/nicehttp/NiceFile;", "", "name", "", "fileName", coil3.util.UtilsKt.SCHEME_FILE, "Ljava/io/File;", "fileType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;Ljava/lang/String;)V", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/io/File;)V", "(Ljava/io/File;)V", "getName", "()Ljava/lang/String;", "getFileName", "getFile", "()Ljava/io/File;", "getFileType", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NiceFile {
    private final File file;
    private final String fileName;
    private final String fileType;
    private final String name;

    public NiceFile(String name, String fileName, File file, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.name = name;
        this.fileName = fileName;
        this.file = file;
        this.fileType = str;
    }

    public final File getFile() {
        return this.file;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final String getFileType() {
        return this.fileType;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NiceFile(String name, String value) {
        this(name, value, null, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NiceFile(String name, File file) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(file, "file");
        String name2 = file.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        this(name, name2, file, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NiceFile(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        this(name, file);
    }
}
