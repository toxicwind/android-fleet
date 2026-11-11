package j$.adapter;

import android.os.StrictMode;
import j$.desugar.sun.nio.fs.DesugarDefaultFileSystemProvider;
import j$.nio.file.FileSystem;
import j$.nio.file.spi.FileSystemProvider;
import java.net.URI;
import java.nio.file.FileSystems;

/* JADX INFO: loaded from: classes3.dex */
public abstract class HybridFileSystemProvider {
    private static final FileSystem FILE_SYSTEM_INSTANCE;
    private static final FileSystemProvider INSTANCE;

    static {
        FileSystemProvider fileSystemProvider = getFileSystemProvider();
        INSTANCE = fileSystemProvider;
        FILE_SYSTEM_INSTANCE = fileSystemProvider.getFileSystem(URI.create("file:///"));
    }

    private static FileSystemProvider getFileSystemProvider() {
        if (AndroidVersionTest.is26OrAbove) {
            return FileSystemProvider.VivifiedWrapper.convert(FileSystems.getDefault().provider());
        }
        if (AndroidVersionTest.isHeadfull) {
            setThreadPolicy();
        }
        return DesugarDefaultFileSystemProvider.instance();
    }

    private static void setThreadPolicy() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(StrictMode.getThreadPolicy()).permitDiskReads().build());
    }

    public static FileSystemProvider instance() {
        return INSTANCE;
    }
}
