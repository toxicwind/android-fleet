package j$.desugar.sun.nio.fs;

import j$.nio.file.AccessMode;
import j$.nio.file.Path;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileTime;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
class DesugarBasicFileAttributeView extends DesugarAbstractBasicFileAttributeView {
    private final Path path;

    public DesugarBasicFileAttributeView(Path path) {
        this.path = path;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public BasicFileAttributes readAttributes() {
        this.path.getFileSystem().provider().checkAccess(this.path, new AccessMode[0]);
        return DesugarBasicFileAttributes.create(this.path.toFile());
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    public void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
        if (fileTime == null && fileTime2 == null) {
            return;
        }
        this.path.getFileSystem().provider().checkAccess(this.path, AccessMode.WRITE);
        if (this.path.toFile().setLastModified(fileTime.to(TimeUnit.MILLISECONDS))) {
            return;
        }
        throw new IOException("File.setLastModified did not succeed on " + this.path);
    }
}
