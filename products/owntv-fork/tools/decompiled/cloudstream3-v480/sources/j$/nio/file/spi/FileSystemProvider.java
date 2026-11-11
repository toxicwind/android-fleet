package j$.nio.file.spi;

import coil3.util.UtilsKt;
import j$.desugar.sun.nio.fs.DesugarLinuxFileSystem;
import j$.nio.channels.AsynchronousFileChannel;
import j$.nio.channels.DesugarChannels;
import j$.nio.file.AccessMode;
import j$.nio.file.CopyOption;
import j$.nio.file.FileApiFlips;
import j$.nio.file.FileStore;
import j$.nio.file.FileSystem;
import j$.nio.file.FileSystems;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.PathApiFlips;
import j$.nio.file.StandardOpenOption;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeConversions;
import j$.nio.file.attribute.FileAttributeView;
import j$.time.ZoneId;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.NoSuchFileException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FileSystemProvider {
    private static volatile List installedProviders = null;
    private static boolean loadingProviders = false;
    private static final Object lock = new Object();
    private static final Set DEFAULT_OPEN_OPTIONS = AnonymousClass8.m(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ FileAttribute[] m(java.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            if (fileAttributeArr == null) {
                return null;
            }
            int length = fileAttributeArr.length;
            FileAttribute[] fileAttributeArr2 = new FileAttribute[length];
            for (int i = 0; i < length; i++) {
                fileAttributeArr2[i] = FileAttributeConversions.convert(fileAttributeArr[i]);
            }
            return fileAttributeArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$2, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass2 {
        public static /* synthetic */ OpenOption[] m(java.nio.file.OpenOption[] openOptionArr) {
            if (openOptionArr == null) {
                return null;
            }
            int length = openOptionArr.length;
            OpenOption[] openOptionArr2 = new OpenOption[length];
            for (int i = 0; i < length; i++) {
                openOptionArr2[i] = OpenOption.VivifiedWrapper.convert(openOptionArr[i]);
            }
            return openOptionArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$3, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass3 {
        public static /* synthetic */ AccessMode[] m(java.nio.file.AccessMode[] accessModeArr) {
            if (accessModeArr == null) {
                return null;
            }
            int length = accessModeArr.length;
            AccessMode[] accessModeArr2 = new AccessMode[length];
            for (int i = 0; i < length; i++) {
                accessModeArr2[i] = AccessMode.EnumConversion.convert(accessModeArr[i]);
            }
            return accessModeArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$4, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass4 {
        public static /* synthetic */ CopyOption[] m(java.nio.file.CopyOption[] copyOptionArr) {
            if (copyOptionArr == null) {
                return null;
            }
            int length = copyOptionArr.length;
            CopyOption[] copyOptionArr2 = new CopyOption[length];
            for (int i = 0; i < length; i++) {
                copyOptionArr2[i] = CopyOption.VivifiedWrapper.convert(copyOptionArr[i]);
            }
            return copyOptionArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$5, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass5 {
        public static /* synthetic */ java.nio.file.OpenOption[] m(OpenOption[] openOptionArr) {
            if (openOptionArr == null) {
                return null;
            }
            int length = openOptionArr.length;
            java.nio.file.OpenOption[] openOptionArr2 = new java.nio.file.OpenOption[length];
            for (int i = 0; i < length; i++) {
                openOptionArr2[i] = OpenOption.Wrapper.convert(openOptionArr[i]);
            }
            return openOptionArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$6, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass6 {
        public static /* synthetic */ java.nio.file.AccessMode[] m(AccessMode[] accessModeArr) {
            if (accessModeArr == null) {
                return null;
            }
            int length = accessModeArr.length;
            java.nio.file.AccessMode[] accessModeArr2 = new java.nio.file.AccessMode[length];
            for (int i = 0; i < length; i++) {
                accessModeArr2[i] = AccessMode.EnumConversion.convert(accessModeArr[i]);
            }
            return accessModeArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$7, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass7 {
        public static /* synthetic */ java.nio.file.CopyOption[] m(CopyOption[] copyOptionArr) {
            if (copyOptionArr == null) {
                return null;
            }
            int length = copyOptionArr.length;
            java.nio.file.CopyOption[] copyOptionArr2 = new java.nio.file.CopyOption[length];
            for (int i = 0; i < length; i++) {
                copyOptionArr2[i] = CopyOption.Wrapper.convert(copyOptionArr[i]);
            }
            return copyOptionArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.spi.FileSystemProvider$8, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass8 {
        public static /* synthetic */ Set m(Object obj, Object obj2, Object obj3) {
            return DesugarLinuxFileSystem.AnonymousClass3.m(new Object[]{obj, obj2, obj3});
        }
    }

    public final /* synthetic */ class VivifiedWrapper extends FileSystemProvider {
        public final /* synthetic */ java.nio.file.spi.FileSystemProvider wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.spi.FileSystemProvider fileSystemProvider) {
            this.wrappedValue = fileSystemProvider;
        }

        public static /* synthetic */ FileSystemProvider convert(java.nio.file.spi.FileSystemProvider fileSystemProvider) {
            if (fileSystemProvider == null) {
                return null;
            }
            return fileSystemProvider instanceof Wrapper ? FileSystemProvider.this : new VivifiedWrapper(fileSystemProvider);
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void checkAccess(Path path, AccessMode[] accessModeArr) throws IOException {
            this.wrappedValue.checkAccess(Path.Wrapper.convert(path), AnonymousClass6.m(accessModeArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void copy(Path path, Path path2, CopyOption[] copyOptionArr) throws IOException {
            this.wrappedValue.copy(Path.Wrapper.convert(path), Path.Wrapper.convert(path2), AnonymousClass7.m(copyOptionArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void createDirectory(Path path, FileAttribute[] fileAttributeArr) throws IOException {
            this.wrappedValue.createDirectory(Path.Wrapper.convert(path), DesugarChannels.AnonymousClass0.m(fileAttributeArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void createLink(Path path, Path path2) throws IOException {
            this.wrappedValue.createLink(Path.Wrapper.convert(path), Path.Wrapper.convert(path2));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void createSymbolicLink(Path path, Path path2, FileAttribute[] fileAttributeArr) throws IOException {
            this.wrappedValue.createSymbolicLink(Path.Wrapper.convert(path), Path.Wrapper.convert(path2), DesugarChannels.AnonymousClass0.m(fileAttributeArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void delete(Path path) throws IOException {
            this.wrappedValue.delete(Path.Wrapper.convert(path));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ boolean deleteIfExists(Path path) {
            return this.wrappedValue.deleteIfExists(Path.Wrapper.convert(path));
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.spi.FileSystemProvider fileSystemProvider = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return fileSystemProvider.equals(obj);
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ FileAttributeView getFileAttributeView(Path path, Class cls, LinkOption[] linkOptionArr) {
            return FileAttributeView.VivifiedWrapper.convert(this.wrappedValue.getFileAttributeView(Path.Wrapper.convert(path), FileApiFlips.flipFileAttributeView(cls), Path.AnonymousClass4.m(linkOptionArr)));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ FileStore getFileStore(Path path) {
            return FileStore.VivifiedWrapper.convert(this.wrappedValue.getFileStore(Path.Wrapper.convert(path)));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ FileSystem getFileSystem(URI uri) {
            return FileSystem.VivifiedWrapper.convert(this.wrappedValue.getFileSystem(uri));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ Path getPath(URI uri) {
            return Path.VivifiedWrapper.convert(this.wrappedValue.getPath(uri));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ String getScheme() {
            return this.wrappedValue.getScheme();
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ boolean isHidden(Path path) {
            return this.wrappedValue.isHidden(Path.Wrapper.convert(path));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ boolean isSameFile(Path path, Path path2) {
            return this.wrappedValue.isSameFile(Path.Wrapper.convert(path), Path.Wrapper.convert(path2));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void move(Path path, Path path2, CopyOption[] copyOptionArr) throws IOException {
            this.wrappedValue.move(Path.Wrapper.convert(path), Path.Wrapper.convert(path2), AnonymousClass7.m(copyOptionArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ AsynchronousFileChannel newAsynchronousFileChannel(Path path, Set set, ExecutorService executorService, FileAttribute[] fileAttributeArr) {
            return AsynchronousFileChannel.VivifiedWrapper.convert(this.wrappedValue.newAsynchronousFileChannel(Path.Wrapper.convert(path), FileApiFlips.flipOpenOptionSet(set), executorService, DesugarChannels.AnonymousClass0.m(fileAttributeArr)));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ SeekableByteChannel newByteChannel(Path path, Set set, FileAttribute[] fileAttributeArr) {
            return this.wrappedValue.newByteChannel(Path.Wrapper.convert(path), FileApiFlips.flipOpenOptionSet(set), DesugarChannels.AnonymousClass0.m(fileAttributeArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ DirectoryStream newDirectoryStream(Path path, DirectoryStream.Filter filter) {
            return PathApiFlips.flipDirectoryStreamPath(this.wrappedValue.newDirectoryStream(Path.Wrapper.convert(path), PathApiFlips.flipDirectoryStreamFilterPath(filter)));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ FileChannel newFileChannel(Path path, Set set, FileAttribute[] fileAttributeArr) {
            return this.wrappedValue.newFileChannel(Path.Wrapper.convert(path), FileApiFlips.flipOpenOptionSet(set), DesugarChannels.AnonymousClass0.m(fileAttributeArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ FileSystem newFileSystem(Path path, Map map) {
            return FileSystem.VivifiedWrapper.convert(this.wrappedValue.newFileSystem(Path.Wrapper.convert(path), (Map<String, ?>) map));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ FileSystem newFileSystem(URI uri, Map map) {
            return FileSystem.VivifiedWrapper.convert(this.wrappedValue.newFileSystem(uri, (Map<String, ?>) map));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ InputStream newInputStream(Path path, OpenOption[] openOptionArr) {
            return this.wrappedValue.newInputStream(Path.Wrapper.convert(path), AnonymousClass5.m(openOptionArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ OutputStream newOutputStream(Path path, OpenOption[] openOptionArr) {
            return this.wrappedValue.newOutputStream(Path.Wrapper.convert(path), AnonymousClass5.m(openOptionArr));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ BasicFileAttributes readAttributes(Path path, Class cls, LinkOption[] linkOptionArr) {
            return BasicFileAttributes.VivifiedWrapper.convert(this.wrappedValue.readAttributes(Path.Wrapper.convert(path), FileApiFlips.flipFileAttributes(cls), Path.AnonymousClass4.m(linkOptionArr)));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ Map readAttributes(Path path, String str, LinkOption[] linkOptionArr) {
            return FileApiFlips.flipMapWithMaybeFileTimeValues(this.wrappedValue.readAttributes(Path.Wrapper.convert(path), str, Path.AnonymousClass4.m(linkOptionArr)));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ Path readSymbolicLink(Path path) {
            return Path.VivifiedWrapper.convert(this.wrappedValue.readSymbolicLink(Path.Wrapper.convert(path)));
        }

        @Override // j$.nio.file.spi.FileSystemProvider
        public /* synthetic */ void setAttribute(Path path, String str, Object obj, LinkOption[] linkOptionArr) throws IOException {
            this.wrappedValue.setAttribute(Path.Wrapper.convert(path), str, FileApiFlips.flipMaybeFileTime(obj), Path.AnonymousClass4.m(linkOptionArr));
        }
    }

    public final /* synthetic */ class Wrapper extends java.nio.file.spi.FileSystemProvider {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.spi.FileSystemProvider convert(FileSystemProvider fileSystemProvider) {
            if (fileSystemProvider == null) {
                return null;
            }
            return fileSystemProvider instanceof VivifiedWrapper ? ((VivifiedWrapper) fileSystemProvider).wrappedValue : fileSystemProvider.new Wrapper();
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void checkAccess(java.nio.file.Path path, java.nio.file.AccessMode[] accessModeArr) {
            FileSystemProvider.this.checkAccess(Path.VivifiedWrapper.convert(path), AnonymousClass3.m(accessModeArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void copy(java.nio.file.Path path, java.nio.file.Path path2, java.nio.file.CopyOption[] copyOptionArr) {
            FileSystemProvider.this.copy(Path.VivifiedWrapper.convert(path), Path.VivifiedWrapper.convert(path2), AnonymousClass4.m(copyOptionArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void createDirectory(java.nio.file.Path path, java.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            FileSystemProvider.this.createDirectory(Path.VivifiedWrapper.convert(path), AnonymousClass0.m(fileAttributeArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void createLink(java.nio.file.Path path, java.nio.file.Path path2) {
            FileSystemProvider.this.createLink(Path.VivifiedWrapper.convert(path), Path.VivifiedWrapper.convert(path2));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void createSymbolicLink(java.nio.file.Path path, java.nio.file.Path path2, java.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            FileSystemProvider.this.createSymbolicLink(Path.VivifiedWrapper.convert(path), Path.VivifiedWrapper.convert(path2), AnonymousClass0.m(fileAttributeArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void delete(java.nio.file.Path path) {
            FileSystemProvider.this.delete(Path.VivifiedWrapper.convert(path));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ boolean deleteIfExists(java.nio.file.Path path) {
            return FileSystemProvider.this.deleteIfExists(Path.VivifiedWrapper.convert(path));
        }

        public /* synthetic */ boolean equals(Object obj) {
            FileSystemProvider fileSystemProvider = FileSystemProvider.this;
            if (obj instanceof Wrapper) {
                obj = FileSystemProvider.this;
            }
            return fileSystemProvider.equals(obj);
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.attribute.FileAttributeView getFileAttributeView(java.nio.file.Path path, Class cls, java.nio.file.LinkOption[] linkOptionArr) {
            return FileAttributeView.Wrapper.convert(FileSystemProvider.this.getFileAttributeView(Path.VivifiedWrapper.convert(path), FileApiFlips.flipFileAttributeView(cls), Path.AnonymousClass0.m(linkOptionArr)));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.FileStore getFileStore(java.nio.file.Path path) {
            return FileStore.Wrapper.convert(FileSystemProvider.this.getFileStore(Path.VivifiedWrapper.convert(path)));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.FileSystem getFileSystem(URI uri) {
            return FileSystem.Wrapper.convert(FileSystemProvider.this.getFileSystem(uri));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.Path getPath(URI uri) {
            return Path.Wrapper.convert(FileSystemProvider.this.getPath(uri));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ String getScheme() {
            return FileSystemProvider.this.getScheme();
        }

        public /* synthetic */ int hashCode() {
            return FileSystemProvider.this.hashCode();
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ boolean isHidden(java.nio.file.Path path) {
            return FileSystemProvider.this.isHidden(Path.VivifiedWrapper.convert(path));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ boolean isSameFile(java.nio.file.Path path, java.nio.file.Path path2) {
            return FileSystemProvider.this.isSameFile(Path.VivifiedWrapper.convert(path), Path.VivifiedWrapper.convert(path2));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void move(java.nio.file.Path path, java.nio.file.Path path2, java.nio.file.CopyOption[] copyOptionArr) {
            FileSystemProvider.this.move(Path.VivifiedWrapper.convert(path), Path.VivifiedWrapper.convert(path2), AnonymousClass4.m(copyOptionArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.channels.AsynchronousFileChannel newAsynchronousFileChannel(java.nio.file.Path path, Set set, ExecutorService executorService, java.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            return AsynchronousFileChannel.Wrapper.convert(FileSystemProvider.this.newAsynchronousFileChannel(Path.VivifiedWrapper.convert(path), FileApiFlips.flipOpenOptionSet(set), executorService, AnonymousClass0.m(fileAttributeArr)));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ SeekableByteChannel newByteChannel(java.nio.file.Path path, Set set, java.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            return FileSystemProvider.this.newByteChannel(Path.VivifiedWrapper.convert(path), FileApiFlips.flipOpenOptionSet(set), AnonymousClass0.m(fileAttributeArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ DirectoryStream newDirectoryStream(java.nio.file.Path path, DirectoryStream.Filter filter) {
            return PathApiFlips.flipDirectoryStreamPath(FileSystemProvider.this.newDirectoryStream(Path.VivifiedWrapper.convert(path), PathApiFlips.flipDirectoryStreamFilterPath(filter)));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ FileChannel newFileChannel(java.nio.file.Path path, Set set, java.nio.file.attribute.FileAttribute[] fileAttributeArr) {
            return FileSystemProvider.this.newFileChannel(Path.VivifiedWrapper.convert(path), FileApiFlips.flipOpenOptionSet(set), AnonymousClass0.m(fileAttributeArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.FileSystem newFileSystem(URI uri, Map map) {
            return FileSystem.Wrapper.convert(FileSystemProvider.this.newFileSystem(uri, map));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.FileSystem newFileSystem(java.nio.file.Path path, Map map) {
            return FileSystem.Wrapper.convert(FileSystemProvider.this.newFileSystem(Path.VivifiedWrapper.convert(path), map));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ InputStream newInputStream(java.nio.file.Path path, java.nio.file.OpenOption[] openOptionArr) {
            return FileSystemProvider.this.newInputStream(Path.VivifiedWrapper.convert(path), AnonymousClass2.m(openOptionArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ OutputStream newOutputStream(java.nio.file.Path path, java.nio.file.OpenOption[] openOptionArr) {
            return FileSystemProvider.this.newOutputStream(Path.VivifiedWrapper.convert(path), AnonymousClass2.m(openOptionArr));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.attribute.BasicFileAttributes readAttributes(java.nio.file.Path path, Class cls, java.nio.file.LinkOption[] linkOptionArr) {
            return BasicFileAttributes.Wrapper.convert(FileSystemProvider.this.readAttributes(Path.VivifiedWrapper.convert(path), FileApiFlips.flipFileAttributes(cls), Path.AnonymousClass0.m(linkOptionArr)));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ Map readAttributes(java.nio.file.Path path, String str, java.nio.file.LinkOption[] linkOptionArr) {
            return FileApiFlips.flipMapWithMaybeFileTimeValues(FileSystemProvider.this.readAttributes(Path.VivifiedWrapper.convert(path), str, Path.AnonymousClass0.m(linkOptionArr)));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ java.nio.file.Path readSymbolicLink(java.nio.file.Path path) {
            return Path.Wrapper.convert(FileSystemProvider.this.readSymbolicLink(Path.VivifiedWrapper.convert(path)));
        }

        @Override // java.nio.file.spi.FileSystemProvider
        public /* synthetic */ void setAttribute(java.nio.file.Path path, String str, Object obj, java.nio.file.LinkOption[] linkOptionArr) {
            FileSystemProvider.this.setAttribute(Path.VivifiedWrapper.convert(path), str, FileApiFlips.flipMaybeFileTime(obj), Path.AnonymousClass0.m(linkOptionArr));
        }
    }

    public abstract void checkAccess(Path path, AccessMode... accessModeArr);

    public abstract void copy(Path path, Path path2, CopyOption... copyOptionArr);

    public abstract void createDirectory(Path path, FileAttribute... fileAttributeArr);

    public abstract void delete(Path path);

    public abstract FileAttributeView getFileAttributeView(Path path, Class cls, LinkOption... linkOptionArr);

    public abstract FileStore getFileStore(Path path);

    public abstract FileSystem getFileSystem(URI uri);

    public abstract Path getPath(URI uri);

    public abstract String getScheme();

    public abstract boolean isHidden(Path path);

    public abstract boolean isSameFile(Path path, Path path2);

    public abstract void move(Path path, Path path2, CopyOption... copyOptionArr);

    public abstract SeekableByteChannel newByteChannel(Path path, Set set, FileAttribute... fileAttributeArr);

    public abstract DirectoryStream newDirectoryStream(Path path, DirectoryStream.Filter filter);

    public abstract FileSystem newFileSystem(URI uri, Map map);

    public abstract BasicFileAttributes readAttributes(Path path, Class cls, LinkOption... linkOptionArr);

    public abstract Map readAttributes(Path path, String str, LinkOption... linkOptionArr);

    public abstract void setAttribute(Path path, String str, Object obj, LinkOption... linkOptionArr);

    private static Void checkPermission() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager == null) {
            return null;
        }
        securityManager.checkPermission(new RuntimePermission("fileSystemProvider"));
        return null;
    }

    private FileSystemProvider(Void r1) {
    }

    protected FileSystemProvider() {
        this(checkPermission());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List loadInstalledProviders() {
        Object obj;
        ArrayList arrayList = new ArrayList();
        for (FileSystemProvider fileSystemProvider : ServiceLoader.load(FileSystemProvider.class, ClassLoader.getSystemClassLoader())) {
            String scheme = fileSystemProvider.getScheme();
            if (!scheme.equalsIgnoreCase(UtilsKt.SCHEME_FILE)) {
                int size = arrayList.size();
                int i = 0;
                do {
                    if (i >= size) {
                        arrayList.add(fileSystemProvider);
                        break;
                    }
                    obj = arrayList.get(i);
                    i++;
                } while (!((FileSystemProvider) obj).getScheme().equalsIgnoreCase(scheme));
            }
        }
        return arrayList;
    }

    public static List installedProviders() {
        if (installedProviders == null) {
            FileSystemProvider fileSystemProviderProvider = FileSystems.getDefault().provider();
            synchronized (lock) {
                try {
                    if (installedProviders == null) {
                        if (loadingProviders) {
                            throw new Error("Circular loading of installed providers detected");
                        }
                        loadingProviders = true;
                        List list = (List) AccessController.doPrivileged(new PrivilegedAction() { // from class: j$.nio.file.spi.FileSystemProvider.1
                            @Override // java.security.PrivilegedAction
                            public List run() {
                                return FileSystemProvider.loadInstalledProviders();
                            }
                        });
                        list.add(0, fileSystemProviderProvider);
                        installedProviders = Collections.unmodifiableList(list);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return installedProviders;
    }

    public FileSystem newFileSystem(Path path, Map map) {
        throw new UnsupportedOperationException();
    }

    public InputStream newInputStream(Path path, OpenOption... openOptionArr) {
        if (openOptionArr.length > 0) {
            for (OpenOption openOption : openOptionArr) {
                if (openOption == StandardOpenOption.APPEND || openOption == StandardOpenOption.WRITE) {
                    throw new UnsupportedOperationException("'" + openOption + "' not allowed");
                }
            }
        }
        return Channels.newInputStream(Files.newByteChannel(path, openOptionArr));
    }

    public OutputStream newOutputStream(Path path, OpenOption... openOptionArr) {
        Set set;
        if (openOptionArr.length == 0) {
            set = DEFAULT_OPEN_OPTIONS;
        } else {
            HashSet hashSet = new HashSet();
            for (OpenOption openOption : openOptionArr) {
                if (openOption == StandardOpenOption.READ) {
                    ZoneId.AnonymousClass4.m("READ not allowed");
                    return null;
                }
                hashSet.add(openOption);
            }
            hashSet.add(StandardOpenOption.WRITE);
            set = hashSet;
        }
        return Channels.newOutputStream(newByteChannel(path, set, new FileAttribute[0]));
    }

    public FileChannel newFileChannel(Path path, Set set, FileAttribute... fileAttributeArr) {
        throw new UnsupportedOperationException();
    }

    public AsynchronousFileChannel newAsynchronousFileChannel(Path path, Set set, ExecutorService executorService, FileAttribute... fileAttributeArr) {
        throw new UnsupportedOperationException();
    }

    public void createSymbolicLink(Path path, Path path2, FileAttribute... fileAttributeArr) {
        throw new UnsupportedOperationException();
    }

    public void createLink(Path path, Path path2) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteIfExists(Path path) {
        try {
            delete(path);
            return true;
        } catch (NoSuchFileException unused) {
            return false;
        }
    }

    public Path readSymbolicLink(Path path) {
        throw new UnsupportedOperationException();
    }
}
