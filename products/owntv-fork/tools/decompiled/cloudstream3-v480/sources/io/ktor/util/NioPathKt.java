package io.ktor.util;

import j$.nio.file.Path;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NioPath.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0019\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0004\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0013\u0010\u0006\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u0019\u0010\u0002\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0002\u0010\b¨\u0006\t"}, d2 = {"j$/nio/file/Path", "relativePath", "combineSafe", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)Lj$/nio/file/Path;", "normalizeAndRelativize", "(Lj$/nio/file/Path;)Lj$/nio/file/Path;", "dropLeadingTopDirs", "Ljava/io/File;", "(Ljava/io/File;Lj$/nio/file/Path;)Ljava/io/File;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NioPathKt {
    public static final Path combineSafe(Path path, Path relativePath) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        Path pathNormalizeAndRelativize = normalizeAndRelativize(relativePath);
        if (pathNormalizeAndRelativize.startsWith("..")) {
            throw new InvalidPathException(relativePath.toString(), "Relative path " + relativePath + " beginning with .. is invalid");
        }
        if (pathNormalizeAndRelativize.isAbsolute()) {
            throw new IllegalStateException(("Bad relative path " + relativePath).toString());
        }
        if (path.getNameCount() == 0) {
            return pathNormalizeAndRelativize;
        }
        Path pathResolve = path.resolve(pathNormalizeAndRelativize);
        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
        return pathResolve;
    }

    public static final Path normalizeAndRelativize(Path path) {
        Path pathRelativize;
        Path pathNormalize;
        Path pathDropLeadingTopDirs;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Path root = path.getRoot();
        if (root != null && (pathRelativize = root.relativize(path)) != null && (pathNormalize = pathRelativize.normalize()) != null && (pathDropLeadingTopDirs = dropLeadingTopDirs(pathNormalize)) != null) {
            return pathDropLeadingTopDirs;
        }
        Path pathNormalize2 = path.normalize();
        Intrinsics.checkNotNullExpressionValue(pathNormalize2, "normalize(...)");
        return dropLeadingTopDirs(pathNormalize2);
    }

    private static final Path dropLeadingTopDirs(Path path) {
        Iterator<Path> it = path.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            Path next = it.next();
            if (i < 0) {
                kotlin.collections.CollectionsKt.throwIndexOverflow();
            }
            if (!Intrinsics.areEqual(next.toString(), "..")) {
                break;
            }
            i++;
        }
        if (i <= 0) {
            return path;
        }
        Path pathSubpath = path.subpath(i, path.getNameCount());
        Intrinsics.checkNotNullExpressionValue(pathSubpath, "subpath(...)");
        return pathSubpath;
    }

    public static final File combineSafe(File file, Path relativePath) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        Path pathNormalizeAndRelativize = normalizeAndRelativize(relativePath);
        if (pathNormalizeAndRelativize.startsWith("..")) {
            throw new InvalidPathException(relativePath.toString(), "Relative path " + relativePath + " beginning with .. is invalid");
        }
        if (pathNormalizeAndRelativize.isAbsolute()) {
            throw new IllegalStateException(("Bad relative path " + relativePath).toString());
        }
        return new File(file, pathNormalizeAndRelativize.toString());
    }
}
