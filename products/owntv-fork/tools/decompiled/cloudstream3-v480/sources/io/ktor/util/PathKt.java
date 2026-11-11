package io.ktor.util;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Path.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0019\u0010\u0003\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\b\u001a\u0013\u0010\t\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b\t\u0010\u0006\u001a\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u0010\u001a\u00020\u000f*\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0013\u0010\u0012\u001a\u00020\u000f*\u00020\u000eH\u0002¢\u0006\u0004\b\u0012\u0010\u0011\u001a\u0013\u0010\f\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b\f\u0010\u0006¨\u0006\u0013"}, d2 = {"Ljava/io/File;", "", "relativePath", "combineSafe", "(Ljava/io/File;Ljava/lang/String;)Ljava/io/File;", "normalizeAndRelativize", "(Ljava/io/File;)Ljava/io/File;", "dir", "(Ljava/io/File;Ljava/io/File;)Ljava/io/File;", "notRooted", "path", "", "dropLeadingTopDirs", "(Ljava/lang/String;)I", "", "", "isPathSeparator", "(C)Z", "isPathSeparatorOrDot", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PathKt {
    private static final boolean isPathSeparator(char c) {
        return c == '\\' || c == '/';
    }

    public static final File combineSafe(File file, String relativePath) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        return combineSafe(file, new File(relativePath));
    }

    public static final File normalizeAndRelativize(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return dropLeadingTopDirs(notRooted(FilesKt.normalize(file)));
    }

    private static final File combineSafe(File file, File file2) {
        File fileNormalizeAndRelativize = normalizeAndRelativize(file2);
        if (FilesKt.startsWith(fileNormalizeAndRelativize, "..")) {
            throw new IllegalArgumentException("Bad relative path " + file2);
        }
        if (fileNormalizeAndRelativize.isAbsolute()) {
            throw new IllegalStateException(("Bad relative path " + file2).toString());
        }
        return new File(file, fileNormalizeAndRelativize.getPath());
    }

    private static final File notRooted(File file) {
        String strSubstring;
        if (!FilesKt.isRooted(file)) {
            return file;
        }
        File file2 = file;
        while (true) {
            File parentFile = file2.getParentFile();
            if (parentFile == null) {
                break;
            }
            file2 = parentFile;
        }
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        String strDrop = StringsKt.drop(path, file2.getName().length());
        int length = strDrop.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = strDrop.charAt(i);
            if (cCharAt != '\\' && cCharAt != '/') {
                strSubstring = strDrop.substring(i);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return new File(strSubstring);
            }
        }
        strSubstring = "";
        return new File(strSubstring);
    }

    public static final int dropLeadingTopDirs(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        int length = path.length() - 1;
        int i = 0;
        while (i <= length) {
            char cCharAt = path.charAt(i);
            if (!isPathSeparator(cCharAt)) {
                if (cCharAt != '.') {
                    break;
                }
                if (i == length) {
                    return i + 1;
                }
                char cCharAt2 = path.charAt(i + 1);
                int i2 = 2;
                if (!isPathSeparator(cCharAt2)) {
                    if (cCharAt2 == '.') {
                        int i3 = i + 2;
                        if (i3 != path.length()) {
                            if (!isPathSeparator(path.charAt(i3))) {
                                break;
                            }
                            i2 = 3;
                        }
                    } else {
                        break;
                    }
                }
                i += i2;
            } else {
                i++;
            }
        }
        return i;
    }

    private static final boolean isPathSeparatorOrDot(char c) {
        return c == '.' || isPathSeparator(c);
    }

    private static final File dropLeadingTopDirs(File file) {
        String path = file.getPath();
        if (path == null) {
            path = "";
        }
        int iDropLeadingTopDirs = dropLeadingTopDirs(path);
        if (iDropLeadingTopDirs == 0) {
            return file;
        }
        if (iDropLeadingTopDirs >= file.getPath().length()) {
            return new File(".");
        }
        String path2 = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
        String strSubstring = path2.substring(iDropLeadingTopDirs);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return new File(strSubstring);
    }
}
