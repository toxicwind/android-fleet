package j$.nio.file;

import coil3.util.UtilsKt;
import j$.adapter.HybridFileSystemProvider;
import j$.nio.file.spi.FileSystemProvider;
import java.net.URI;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes3.dex */
public final class FileSystems {

    private static class DefaultFileSystemHolder {
        static final FileSystem defaultFileSystem = defaultFileSystem();

        private static FileSystem defaultFileSystem() {
            return ((FileSystemProvider) AccessController.doPrivileged(new PrivilegedAction() { // from class: j$.nio.file.FileSystems.DefaultFileSystemHolder.1
                @Override // java.security.PrivilegedAction
                public FileSystemProvider run() {
                    return DefaultFileSystemHolder.getDefaultProvider();
                }
            })).getFileSystem(URI.create("file:///"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static FileSystemProvider getDefaultProvider() {
            FileSystemProvider fileSystemProviderInstance = HybridFileSystemProvider.instance();
            String property = System.getProperty("java.nio.file.spi.DefaultFileSystemProvider");
            if (property != null) {
                for (String str : property.split(",")) {
                    try {
                        fileSystemProviderInstance = (FileSystemProvider) Class.forName(str, true, ClassLoader.getSystemClassLoader()).getDeclaredConstructor(FileSystemProvider.class).newInstance(fileSystemProviderInstance);
                        if (!fileSystemProviderInstance.getScheme().equals(UtilsKt.SCHEME_FILE)) {
                            throw new Error("Default provider must use scheme 'file'");
                        }
                    } catch (Exception e) {
                        throw new Error(e);
                    }
                }
            }
            return fileSystemProviderInstance;
        }
    }

    public static FileSystem getDefault() {
        return DefaultFileSystemHolder.defaultFileSystem;
    }
}
