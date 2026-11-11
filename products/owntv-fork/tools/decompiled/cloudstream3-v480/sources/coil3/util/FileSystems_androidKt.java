package coil3.util;

import android.os.StatFs;
import java.io.File;
import kotlin.Metadata;
import okio.FileSystem;
import okio.Path;

/* JADX INFO: compiled from: fileSystems.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"remainingFreeSpaceBytes", "", "Lokio/FileSystem;", "directory", "Lokio/Path;", "coil-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class FileSystems_androidKt {
    public static final long remainingFreeSpaceBytes(FileSystem fileSystem, Path path) {
        File file = path.toFile();
        file.mkdir();
        StatFs statFs = new StatFs(file.getAbsolutePath());
        return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
    }
}
