package io.ktor.http;

import coil3.util.UtilsKt;
import j$.nio.file.Path;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileContentTypeJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/http/ContentType$Companion;", "Ljava/io/File;", UtilsKt.SCHEME_FILE, "Lio/ktor/http/ContentType;", "defaultForFile", "(Lio/ktor/http/ContentType$Companion;Ljava/io/File;)Lio/ktor/http/ContentType;", "j$/nio/file/Path", "path", "defaultForPath", "(Lio/ktor/http/ContentType$Companion;Lj$/nio/file/Path;)Lio/ktor/http/ContentType;", "ktor-http"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FileContentTypeJvmKt {
    public static final ContentType defaultForFile(ContentType.Companion companion, File file) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(file, "file");
        return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(ContentType.INSTANCE, FilesKt.getExtension(file)));
    }

    public static final ContentType defaultForPath(ContentType.Companion companion, Path path) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(ContentType.INSTANCE, PathsKt.getExtension(path)));
    }
}
