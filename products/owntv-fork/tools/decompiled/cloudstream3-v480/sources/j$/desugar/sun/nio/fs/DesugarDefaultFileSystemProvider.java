package j$.desugar.sun.nio.fs;

import j$.nio.file.spi.FileSystemProvider;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DesugarDefaultFileSystemProvider {
    private static final FileSystemProvider INSTANCE = DesugarLinuxFileSystemProvider.create();

    public static FileSystemProvider instance() {
        return INSTANCE;
    }
}
