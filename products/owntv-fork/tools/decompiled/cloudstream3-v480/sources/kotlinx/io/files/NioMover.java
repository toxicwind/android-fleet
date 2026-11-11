package kotlinx.io.files;

import j$.io.FileRetargetClass;
import j$.nio.file.Files;
import j$.nio.file.StandardCopyOption;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileSystemJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0097\u0080\u0004¨\u0006\t"}, d2 = {"Lkotlinx/io/files/NioMover;", "Lkotlinx/io/files/Mover;", "<init>", "()V", "move", "", "source", "Lkotlinx/io/files/Path;", "destination", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class NioMover implements Mover {
    @Override // kotlinx.io.files.Mover
    @AnimalSnifferIgnore
    public void move(Path source, Path destination) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (!source.getFile().exists()) {
            throw new FileNotFoundException("Source file does not exist: " + source.getFile());
        }
        try {
            Files.move(FileRetargetClass.toPath(source.getFile()), FileRetargetClass.toPath(destination.getFile()), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (Throwable th) {
            if (!(th instanceof IOException)) {
                throw new IOException("Move failed", th);
            }
            throw th;
        }
    }
}
