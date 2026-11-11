package kotlinx.io.files;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.CoreKt;
import kotlinx.io.Sink;
import kotlinx.io.Source;

/* JADX INFO: compiled from: PathsJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\u0080\u0004\u001a\u0013\u0010\u0006\u001a\u00020\u0007*\u00020\u0003H\u0081\u0080\u0004¢\u0006\u0002\b\b\u001a\u0013\u0010\t\u001a\u00020\n*\u00020\u0003H\u0081\u0080\u0004¢\u0006\u0002\b\u000b\"\u0011\u0010\u0000\u001a\u00020\u00018\u0006X\u0087Ä\b¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"SystemPathSeparator", "", "Path", "Lkotlinx/io/files/Path;", "path", "", "sourceHack", "Lkotlinx/io/Source;", "source", "sinkHack", "Lkotlinx/io/Sink;", "sink", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PathsJvmKt {
    public static final char SystemPathSeparator = File.separatorChar;

    public static final Path Path(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new Path(new File(path));
    }

    public static final Source source(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return CoreKt.buffered(FileSystemJvmKt.SystemFileSystem.source(path));
    }

    public static final Sink sink(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return CoreKt.buffered(FileSystem.CC.sink$default(FileSystemJvmKt.SystemFileSystem, path, false, 2, null));
    }
}
