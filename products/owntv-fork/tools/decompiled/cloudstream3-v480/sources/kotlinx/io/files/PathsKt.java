package kotlinx.io.files;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.io.CoreKt;
import kotlinx.io.Sink;
import kotlinx.io.Source;
import kotlinx.io._JvmPlatformKt;

/* JADX INFO: compiled from: Paths.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0007\u001a\u0013\u0010\b\u001a\u00020\t*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\b\n\u001a\u0013\u0010\u000b\u001a\u00020\f*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0080\u0080\u0004\u001a\u0012\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u0003H\u0082\u0080\u0004\u001a\u0012\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0082\u0080\u0004\u001a\u001a\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0003H\u0082\u0080\u0004¨\u0006\u0017"}, d2 = {"Path", "Lkotlinx/io/files/Path;", TtmlNode.RUBY_BASE, "", "parts", "", "(Ljava/lang/String;[Ljava/lang/String;)Lkotlinx/io/files/Path;", "(Lkotlinx/io/files/Path;[Ljava/lang/String;)Lkotlinx/io/files/Path;", "source", "Lkotlinx/io/Source;", "sourceDeprecated", "sink", "Lkotlinx/io/Sink;", "sinkDeprecated", "removeTrailingSeparators", "path", "isWindows_", "", "isUnc", "removeTrailingSeparatorsUnix", "removeTrailingSeparatorsWindows", "suffixLength", "", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PathsKt {
    public static final Path Path(String base, String... parts) {
        Intrinsics.checkNotNullParameter(base, "base");
        Intrinsics.checkNotNullParameter(parts, "parts");
        StringBuilder sb = new StringBuilder();
        sb.append(base);
        for (String str : parts) {
            StringBuilder sb2 = sb;
            if (sb2.length() > 0 && !StringsKt.endsWith$default((CharSequence) sb2, PathsJvmKt.SystemPathSeparator, false, 2, (Object) null)) {
                sb.append(PathsJvmKt.SystemPathSeparator);
            }
            sb.append(str);
        }
        return PathsJvmKt.Path(sb.toString());
    }

    public static final Path Path(Path base, String... parts) {
        Intrinsics.checkNotNullParameter(base, "base");
        Intrinsics.checkNotNullParameter(parts, "parts");
        return Path(base.toString(), (String[]) Arrays.copyOf(parts, parts.length));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use FileSystem.source instead", replaceWith = @ReplaceWith(expression = "SystemFileSystem.source(this).buffered()", imports = {"kotlinx.io.files.FileSystem"}))
    public static final Source sourceDeprecated(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return CoreKt.buffered(FileSystemJvmKt.SystemFileSystem.source(path));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use FileSystem.sink instead", replaceWith = @ReplaceWith(expression = "SystemFileSystem.sink(this).buffered()", imports = {"kotlinx.io.files.FileSystem"}))
    public static final Sink sinkDeprecated(Path path) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        return CoreKt.buffered(FileSystem.CC.sink$default(FileSystemJvmKt.SystemFileSystem, path, false, 2, null));
    }

    public static /* synthetic */ String removeTrailingSeparators$default(String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = _JvmPlatformKt.isWindows();
        }
        return removeTrailingSeparators(str, z);
    }

    public static final String removeTrailingSeparators(String path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "path");
        if (z) {
            int i = 1;
            if (path.length() > 1) {
                if (path.charAt(1) == ':') {
                    i = 3;
                } else if (isUnc(path)) {
                    i = 2;
                }
            }
            return removeTrailingSeparatorsWindows(i, path);
        }
        return removeTrailingSeparatorsUnix(path);
    }

    private static final boolean isUnc(String str) {
        if (str.length() < 2) {
            return false;
        }
        return StringsKt.startsWith$default(str, "\\\\", false, 2, (Object) null) || StringsKt.startsWith$default(str, "//", false, 2, (Object) null);
    }

    private static final String removeTrailingSeparatorsUnix(String str) {
        int length = str.length();
        while (length > 1 && str.charAt(length - 1) == '/') {
            length--;
        }
        String strSubstring = str.substring(0, length);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    private static final String removeTrailingSeparatorsWindows(int i, String str) {
        if (i < 1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int length = str.length();
        while (length > i) {
            char cCharAt = str.charAt(length - 1);
            if (cCharAt != '\\' && cCharAt != '/') {
                break;
            }
            length--;
        }
        String strSubstring = str.substring(0, length);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }
}
