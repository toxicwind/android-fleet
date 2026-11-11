package j$.nio.file;

import j$.nio.file.attribute.BasicFileAttributes;
import j$.util.Objects;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleFileVisitor<T> implements FileVisitor<T> {
    protected SimpleFileVisitor() {
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(T t, BasicFileAttributes basicFileAttributes) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(basicFileAttributes);
        return FileVisitResult.CONTINUE;
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult visitFile(T t, BasicFileAttributes basicFileAttributes) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(basicFileAttributes);
        return FileVisitResult.CONTINUE;
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult visitFileFailed(T t, IOException iOException) throws IOException {
        Objects.requireNonNull(t);
        throw iOException;
    }

    @Override // j$.nio.file.FileVisitor
    public FileVisitResult postVisitDirectory(T t, IOException iOException) throws IOException {
        Objects.requireNonNull(t);
        if (iOException != null) {
            throw iOException;
        }
        return FileVisitResult.CONTINUE;
    }
}
