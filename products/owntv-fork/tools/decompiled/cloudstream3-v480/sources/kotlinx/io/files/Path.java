package kotlinx.io.files;

import coil3.util.UtilsKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PathsJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0011\b@\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\u0004\u0018\u00010\u00008FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0012\u001a\u00020\u000e8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00020\f8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lkotlinx/io/files/Path;", "", UtilsKt.SCHEME_FILE, "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "getFile$kotlinx_io_core", "()Ljava/io/File;", "parent", "getParent", "()Lkotlinx/io/files/Path;", "toString", "", "equals", "", "other", "hashCode", "", "isAbsolute", "()Z", "name", "getName", "()Ljava/lang/String;", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Path {
    private final File file;

    public Path(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = file;
    }

    /* JADX INFO: renamed from: getFile$kotlinx_io_core, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    public final Path getParent() {
        File parentFile = this.file.getParentFile();
        if (parentFile == null) {
            return null;
        }
        return new Path(parentFile);
    }

    public String toString() {
        String string = this.file.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Path) {
            return Intrinsics.areEqual(toString(), ((Path) other).toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public final boolean isAbsolute() {
        return this.file.isAbsolute();
    }

    public final String getName() {
        String name = this.file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }
}
