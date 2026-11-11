package kotlinx.io.files;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FileSystem.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B'\bF\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlinx/io/files/FileMetadata;", "", "isRegularFile", "", "isDirectory", ContentDisposition.Parameters.Size, "", "<init>", "(ZZJ)V", "()Z", "getSize", "()J", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FileMetadata {
    private final boolean isDirectory;
    private final boolean isRegularFile;
    private final long size;

    public FileMetadata() {
        this(false, false, 0L, 7, null);
    }

    public FileMetadata(boolean z, boolean z2, long j) {
        this.isRegularFile = z;
        this.isDirectory = z2;
        this.size = j;
    }

    public /* synthetic */ FileMetadata(boolean z, boolean z2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? 0L : j);
    }

    /* JADX INFO: renamed from: isRegularFile, reason: from getter */
    public final boolean getIsRegularFile() {
        return this.isRegularFile;
    }

    /* JADX INFO: renamed from: isDirectory, reason: from getter */
    public final boolean getIsDirectory() {
        return this.isDirectory;
    }

    public final long getSize() {
        return this.size;
    }
}
