package j$.desugar.sun.nio.fs;

import coil3.util.UtilsKt;
import j$.nio.channels.AsynchronousFileChannel;
import j$.nio.file.AccessMode;
import j$.nio.file.CopyOption;
import j$.nio.file.FileStore;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import j$.nio.file.spi.FileSystemProvider;
import j$.time.ZoneId;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public class DesugarLinuxFileSystemProvider extends FileSystemProvider {
    private final String rootDir;
    private volatile DesugarLinuxFileSystem theFileSystem;
    private final String userDir;

    public static DesugarLinuxFileSystemProvider create() {
        return new DesugarLinuxFileSystemProvider(System.getProperty("user.dir"), "/");
    }

    DesugarLinuxFileSystemProvider(String str, String str2) {
        this.userDir = str;
        this.rootDir = str2;
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public String getScheme() {
        return UtilsKt.SCHEME_FILE;
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public DesugarLinuxFileSystem newFileSystem(URI uri, Map map) {
        checkFileUri(uri);
        throw new FileSystemAlreadyExistsException();
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public final DesugarLinuxFileSystem getFileSystem(URI uri) {
        DesugarLinuxFileSystem desugarLinuxFileSystem;
        checkFileUri(uri);
        DesugarLinuxFileSystem desugarLinuxFileSystem2 = this.theFileSystem;
        if (desugarLinuxFileSystem2 != null) {
            return desugarLinuxFileSystem2;
        }
        synchronized (this) {
            try {
                desugarLinuxFileSystem = this.theFileSystem;
                if (desugarLinuxFileSystem == null) {
                    desugarLinuxFileSystem = new DesugarLinuxFileSystem(this, this.userDir, this.rootDir);
                    this.theFileSystem = desugarLinuxFileSystem;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return desugarLinuxFileSystem;
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public Path getPath(URI uri) {
        return DesugarUnixUriUtils.fromUri(this.theFileSystem, uri, this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public DirectoryStream newDirectoryStream(Path path, DirectoryStream.Filter filter) {
        filter.getClass();
        return new DesugarDirectoryStream(path, filter);
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void createDirectory(Path path, FileAttribute... fileAttributeArr) throws NoSuchFileException, FileAlreadyExistsException {
        if (path.getParent() != null && !Files.exists(path.getParent(), new LinkOption[0])) {
            throw new NoSuchFileException(path.toString());
        }
        if (!path.toFile().mkdirs()) {
            throw new FileAlreadyExistsException(path.toString());
        }
    }

    private boolean exists(Path path) {
        try {
            checkAccess(path, new AccessMode[0]);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void delete(Path path) throws NoSuchFileException {
        if (exists(path)) {
            deleteIfExists(path);
            return;
        }
        throw new NoSuchFileException(path.toString());
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public boolean deleteIfExists(Path path) {
        return path.toFile().delete();
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public SeekableByteChannel newByteChannel(Path path, Set set, FileAttribute... fileAttributeArr) {
        return newFileChannel(path, set, fileAttributeArr);
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public FileChannel newFileChannel(Path path, Set set, FileAttribute... fileAttributeArr) {
        if (path.toFile().isDirectory()) {
            throw new UnsupportedOperationException("The desugar library does not support creating a file channel on a directory: " + path);
        }
        return DesugarFileChannel.openEmulatedFileChannel(path, set, fileAttributeArr);
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public AsynchronousFileChannel newAsynchronousFileChannel(Path path, Set set, ExecutorService executorService, FileAttribute... fileAttributeArr) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void createLink(Path path, Path path2) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void createSymbolicLink(Path path, Path path2, FileAttribute... fileAttributeArr) {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public Path readSymbolicLink(Path path) {
        return new DesugarUnixPath(this.theFileSystem, path.toFile().getCanonicalPath(), this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void copy(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        if (!containsCopyOption(copyOptionArr, StandardCopyOption.REPLACE_EXISTING) && Files.exists(path2, new LinkOption[0])) {
            throw new FileAlreadyExistsException(path2.toString());
        }
        if (containsCopyOption(copyOptionArr, StandardCopyOption.ATOMIC_MOVE)) {
            throw new UnsupportedOperationException("Unsupported copy option");
        }
        FileInputStream fileInputStream = new FileInputStream(path.toFile());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path2.toFile());
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = fileInputStream.read(bArr, 0, 8192);
                    if (i >= 0) {
                        fileOutputStream.write(bArr, 0, i);
                    } else {
                        fileOutputStream.close();
                        fileInputStream.close();
                        return;
                    }
                    try {
                        fileInputStream.close();
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
                throw th2;
            }
        } catch (Throwable th4) {
            fileInputStream.close();
            throw th4;
        }
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void move(Path path, Path path2, CopyOption... copyOptionArr) throws FileAlreadyExistsException {
        if (!containsCopyOption(copyOptionArr, StandardCopyOption.REPLACE_EXISTING) && Files.exists(path2, new LinkOption[0])) {
            throw new FileAlreadyExistsException(path2.toString());
        }
        if (containsCopyOption(copyOptionArr, StandardCopyOption.COPY_ATTRIBUTES)) {
            throw new UnsupportedOperationException("Unsupported copy option");
        }
        path.toFile().renameTo(path2.toFile());
    }

    private boolean containsCopyOption(CopyOption[] copyOptionArr, CopyOption copyOption) {
        for (CopyOption copyOption2 : copyOptionArr) {
            if (copyOption2 == copyOption) {
                return true;
            }
        }
        return false;
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public boolean isSameFile(Path path, Path path2) throws IOException {
        if (path.equals(path2)) {
            return true;
        }
        checkAccess(path, new AccessMode[0]);
        checkAccess(path2, new AccessMode[0]);
        return path.toFile().equals(path2.toFile());
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public boolean isHidden(Path path) {
        return path.toFile().isHidden();
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public FileStore getFileStore(Path path) {
        throw new SecurityException("getFileStore");
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public FileAttributeView getFileAttributeView(Path path, Class cls, LinkOption... linkOptionArr) {
        cls.getClass();
        if (cls == BasicFileAttributeView.class) {
            return (FileAttributeView) cls.cast(new DesugarBasicFileAttributeView(path));
        }
        return null;
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void checkAccess(Path path, AccessMode... accessModeArr) throws IOException {
        boolean zCanRead;
        File file = path.toFile();
        if (!file.exists()) {
            throw new NoSuchFileException(path.toString());
        }
        boolean z = true;
        for (AccessMode accessMode : accessModeArr) {
            int i = AnonymousClass1.$SwitchMap$java$nio$file$AccessMode[accessMode.ordinal()];
            if (i == 1) {
                zCanRead = file.canRead();
            } else if (i != 2) {
                if (i == 3) {
                    zCanRead = file.canExecute();
                }
            } else {
                zCanRead = file.canWrite();
            }
            z &= zCanRead;
        }
        if (!z) {
            throw new IOException(String.format("Unable to access file %s", path));
        }
    }

    /* JADX INFO: renamed from: j$.desugar.sun.nio.fs.DesugarLinuxFileSystemProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$nio$file$AccessMode;

        static {
            int[] iArr = new int[AccessMode.values().length];
            $SwitchMap$java$nio$file$AccessMode = iArr;
            try {
                iArr[AccessMode.READ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$nio$file$AccessMode[AccessMode.WRITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$nio$file$AccessMode[AccessMode.EXECUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public BasicFileAttributes readAttributes(Path path, Class cls, LinkOption... linkOptionArr) {
        if (cls != BasicFileAttributes.class) {
            throw new UnsupportedOperationException();
        }
        return (BasicFileAttributes) cls.cast(((BasicFileAttributeView) getFileAttributeView(path, BasicFileAttributeView.class, linkOptionArr)).readAttributes());
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public Map readAttributes(Path path, String str, LinkOption... linkOptionArr) {
        String[] strArrSplit;
        int iIndexOf = str.indexOf(":");
        if (iIndexOf == -1) {
            strArrSplit = str.split(",");
        } else {
            String strSubstring = str.substring(0, iIndexOf);
            if ("basic".equals(strSubstring)) {
                strArrSplit = str.substring(iIndexOf + 1).split(",");
            } else {
                throw new UnsupportedOperationException(String.format("Requested attribute type for: %s is not available.", strSubstring));
            }
        }
        return new DesugarBasicFileAttributeView(path).readAttributes(strArrSplit);
    }

    @Override // j$.nio.file.spi.FileSystemProvider
    public void setAttribute(Path path, String str, Object obj, LinkOption... linkOptionArr) {
        int iIndexOf = str.indexOf(":");
        if (iIndexOf != -1) {
            String strSubstring = str.substring(0, iIndexOf);
            if (!"basic".equals(strSubstring)) {
                throw new UnsupportedOperationException(String.format("Requested attribute type for: %s is not available.", strSubstring));
            }
            str = str.substring(iIndexOf + 1);
        }
        new DesugarBasicFileAttributeView(path).setAttribute(str, obj);
    }

    private void checkFileUri(URI uri) {
        if (!uri.getScheme().equalsIgnoreCase(getScheme())) {
            ZoneId.AnonymousClass4.m("URI does not match this provider");
            return;
        }
        if (uri.getRawAuthority() != null) {
            ZoneId.AnonymousClass4.m("Authority component present");
            return;
        }
        String path = uri.getPath();
        if (path == null) {
            ZoneId.AnonymousClass4.m("Path component is undefined");
            return;
        }
        if (!path.equals("/")) {
            ZoneId.AnonymousClass4.m("Path component should be '/'");
        } else if (uri.getRawQuery() != null) {
            ZoneId.AnonymousClass4.m("Query component present");
        } else {
            if (uri.getRawFragment() == null) {
                return;
            }
            ZoneId.AnonymousClass4.m("Fragment component present");
        }
    }

    class DesugarPathIterator implements Iterator {
        private final File[] candidates;
        private final DirectoryStream.Filter filter;
        private int index = 0;

        DesugarPathIterator(Path path, DirectoryStream.Filter filter) {
            File[] fileArrListFiles = path.toFile().listFiles();
            this.candidates = fileArrListFiles == null ? new File[0] : fileArrListFiles;
            this.filter = filter;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (next() == null) {
                return false;
            }
            this.index--;
            return true;
        }

        @Override // java.util.Iterator
        public Path next() {
            DesugarUnixPath desugarUnixPath;
            do {
                int i = this.index;
                File[] fileArr = this.candidates;
                if (i >= fileArr.length) {
                    return null;
                }
                this.index = i + 1;
                desugarUnixPath = new DesugarUnixPath(DesugarLinuxFileSystemProvider.this.theFileSystem, fileArr[i].getPath(), DesugarLinuxFileSystemProvider.this.userDir, DesugarLinuxFileSystemProvider.this.rootDir);
                try {
                } catch (IOException e) {
                    throw new DirectoryIteratorException(e);
                }
            } while (!this.filter.accept(desugarUnixPath));
            return desugarUnixPath;
        }
    }

    class DesugarDirectoryStream implements DirectoryStream {
        DesugarPathIterator iterator;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        DesugarDirectoryStream(Path path, DirectoryStream.Filter filter) {
            this.iterator = DesugarLinuxFileSystemProvider.this.new DesugarPathIterator(path, filter);
        }

        @Override // java.nio.file.DirectoryStream, java.lang.Iterable
        public Iterator iterator() {
            return this.iterator;
        }
    }
}
