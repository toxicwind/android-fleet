package j$.nio.file;

import j$.nio.file.attribute.UserPrincipalLookupService;
import j$.nio.file.spi.FileSystemProvider;
import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FileSystem implements Closeable {

    public final /* synthetic */ class VivifiedWrapper extends FileSystem {
        public final /* synthetic */ java.nio.file.FileSystem wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.FileSystem fileSystem) {
            this.wrappedValue = fileSystem;
        }

        public static /* synthetic */ FileSystem convert(java.nio.file.FileSystem fileSystem) {
            if (fileSystem == null) {
                return null;
            }
            return fileSystem instanceof Wrapper ? FileSystem.this : new VivifiedWrapper(fileSystem);
        }

        @Override // j$.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
        public /* synthetic */ void close() throws IOException {
            this.wrappedValue.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.FileSystem fileSystem = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return fileSystem.equals(obj);
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ Iterable getFileStores() {
            return this.wrappedValue.getFileStores();
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ Path getPath(String str, String[] strArr) {
            return Path.VivifiedWrapper.convert(this.wrappedValue.getPath(str, strArr));
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ PathMatcher getPathMatcher(String str) {
            return PathMatcher.VivifiedWrapper.convert(this.wrappedValue.getPathMatcher(str));
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ Iterable getRootDirectories() {
            return PathApiFlips.flipIterablePath(this.wrappedValue.getRootDirectories());
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ String getSeparator() {
            return this.wrappedValue.getSeparator();
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ UserPrincipalLookupService getUserPrincipalLookupService() {
            return UserPrincipalLookupService.VivifiedWrapper.convert(this.wrappedValue.getUserPrincipalLookupService());
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ boolean isOpen() {
            return this.wrappedValue.isOpen();
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ boolean isReadOnly() {
            return this.wrappedValue.isReadOnly();
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ WatchService newWatchService() {
            return WatchService.VivifiedWrapper.convert(this.wrappedValue.newWatchService());
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ FileSystemProvider provider() {
            return FileSystemProvider.VivifiedWrapper.convert(this.wrappedValue.provider());
        }

        @Override // j$.nio.file.FileSystem
        public /* synthetic */ Set supportedFileAttributeViews() {
            return this.wrappedValue.supportedFileAttributeViews();
        }
    }

    public final /* synthetic */ class Wrapper extends java.nio.file.FileSystem {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.FileSystem convert(FileSystem fileSystem) {
            if (fileSystem == null) {
                return null;
            }
            return fileSystem instanceof VivifiedWrapper ? ((VivifiedWrapper) fileSystem).wrappedValue : fileSystem.new Wrapper();
        }

        @Override // java.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
        public /* synthetic */ void close() {
            FileSystem.this.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            FileSystem fileSystem = FileSystem.this;
            if (obj instanceof Wrapper) {
                obj = FileSystem.this;
            }
            return fileSystem.equals(obj);
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ Iterable getFileStores() {
            return FileSystem.this.getFileStores();
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ java.nio.file.Path getPath(String str, String[] strArr) {
            return Path.Wrapper.convert(FileSystem.this.getPath(str, strArr));
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ java.nio.file.PathMatcher getPathMatcher(String str) {
            return PathMatcher.Wrapper.convert(FileSystem.this.getPathMatcher(str));
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ Iterable getRootDirectories() {
            return PathApiFlips.flipIterablePath(FileSystem.this.getRootDirectories());
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ String getSeparator() {
            return FileSystem.this.getSeparator();
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ java.nio.file.attribute.UserPrincipalLookupService getUserPrincipalLookupService() {
            return UserPrincipalLookupService.Wrapper.convert(FileSystem.this.getUserPrincipalLookupService());
        }

        public /* synthetic */ int hashCode() {
            return FileSystem.this.hashCode();
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ boolean isOpen() {
            return FileSystem.this.isOpen();
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ boolean isReadOnly() {
            return FileSystem.this.isReadOnly();
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ java.nio.file.WatchService newWatchService() {
            return WatchService.Wrapper.convert(FileSystem.this.newWatchService());
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ java.nio.file.spi.FileSystemProvider provider() {
            return FileSystemProvider.Wrapper.convert(FileSystem.this.provider());
        }

        @Override // java.nio.file.FileSystem
        public /* synthetic */ Set supportedFileAttributeViews() {
            return FileSystem.this.supportedFileAttributeViews();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract Iterable getFileStores();

    public abstract Path getPath(String str, String... strArr);

    public abstract PathMatcher getPathMatcher(String str);

    public abstract Iterable getRootDirectories();

    public abstract String getSeparator();

    public abstract UserPrincipalLookupService getUserPrincipalLookupService();

    public abstract boolean isOpen();

    public abstract boolean isReadOnly();

    public abstract WatchService newWatchService();

    public abstract FileSystemProvider provider();

    public abstract Set<String> supportedFileAttributeViews();

    protected FileSystem() {
    }
}
