package j$.nio.file;

import j$.nio.file.attribute.FileAttributeView;
import java.nio.file.DirectoryStream;

/* JADX INFO: loaded from: classes3.dex */
public interface SecureDirectoryStream<T> extends DirectoryStream<T> {
    void deleteDirectory(T t);

    void deleteFile(T t);

    <V extends FileAttributeView> V getFileAttributeView(T t, Class<V> cls, LinkOption... linkOptionArr);

    SecureDirectoryStream<T> newDirectoryStream(T t, LinkOption... linkOptionArr);
}
