package j$.nio.file;

import j$.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface FileVisitor<T> {
    FileVisitResult postVisitDirectory(Object obj, IOException iOException);

    FileVisitResult preVisitDirectory(Object obj, BasicFileAttributes basicFileAttributes);

    FileVisitResult visitFile(Object obj, BasicFileAttributes basicFileAttributes);

    FileVisitResult visitFileFailed(Object obj, IOException iOException);
}
