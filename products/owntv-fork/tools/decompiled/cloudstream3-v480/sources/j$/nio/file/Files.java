package j$.nio.file;

import com.fleeksoft.io.Constants;
import j$.desugar.sun.nio.fs.DesugarLinuxFileSystem;
import j$.io.InputStreamRetargetClass;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import j$.nio.file.attribute.FileOwnerAttributeView;
import j$.nio.file.attribute.FileTime;
import j$.nio.file.attribute.PosixFileAttributeView;
import j$.nio.file.attribute.PosixFileAttributes;
import j$.nio.file.attribute.PosixFilePermission;
import j$.nio.file.attribute.UserPrincipal;
import j$.nio.file.spi.FileSystemProvider;
import j$.time.ZoneId;
import j$.util.Objects;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes3.dex */
public final class Files {
    private static final Set DEFAULT_CREATE_OPTIONS = AnonymousClass0.m(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);

    /* JADX INFO: renamed from: j$.nio.file.Files$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ Set m(Object obj, Object obj2) {
            return DesugarLinuxFileSystem.AnonymousClass3.m(new Object[]{obj, obj2});
        }
    }

    private static FileSystemProvider provider(Path path) {
        return path.getFileSystem().provider();
    }

    public static InputStream newInputStream(Path path, OpenOption... openOptionArr) {
        return provider(path).newInputStream(path, openOptionArr);
    }

    public static OutputStream newOutputStream(Path path, OpenOption... openOptionArr) {
        return provider(path).newOutputStream(path, openOptionArr);
    }

    public static SeekableByteChannel newByteChannel(Path path, Set set, FileAttribute... fileAttributeArr) {
        return provider(path).newByteChannel(path, set, fileAttributeArr);
    }

    public static SeekableByteChannel newByteChannel(Path path, OpenOption... openOptionArr) {
        Set set;
        if (openOptionArr.length == 0) {
            set = Collections.EMPTY_SET;
        } else {
            HashSet hashSet = new HashSet();
            Collections.addAll(hashSet, openOptionArr);
            set = hashSet;
        }
        return newByteChannel(path, set, new FileAttribute[0]);
    }

    private static class AcceptAllFilter implements DirectoryStream.Filter {
        static final AcceptAllFilter FILTER = new AcceptAllFilter();

        @Override // java.nio.file.DirectoryStream.Filter
        public boolean accept(Path path) {
            return true;
        }

        private AcceptAllFilter() {
        }
    }

    public static DirectoryStream<Path> newDirectoryStream(Path path) {
        return provider(path).newDirectoryStream(path, AcceptAllFilter.FILTER);
    }

    public static DirectoryStream<Path> newDirectoryStream(Path path, String str) {
        if (str.equals(Marker.ANY_MARKER)) {
            return newDirectoryStream(path);
        }
        FileSystem fileSystem = path.getFileSystem();
        final PathMatcher pathMatcher = fileSystem.getPathMatcher("glob:".concat(str));
        return fileSystem.provider().newDirectoryStream(path, new DirectoryStream.Filter() { // from class: j$.nio.file.Files.1
            @Override // java.nio.file.DirectoryStream.Filter
            public boolean accept(Path path2) {
                return pathMatcher.matches(path2.getFileName());
            }
        });
    }

    public static Path createFile(Path path, FileAttribute<?>... fileAttributeArr) {
        newByteChannel(path, DEFAULT_CREATE_OPTIONS, fileAttributeArr).close();
        return path;
    }

    public static Path createDirectory(Path path, FileAttribute<?>... fileAttributeArr) {
        provider(path).createDirectory(path, fileAttributeArr);
        return path;
    }

    public static Path createDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws FileSystemException {
        try {
            createAndCheckIsDirectory(path, fileAttributeArr);
            return path;
        } catch (FileAlreadyExistsException e) {
            throw e;
        } catch (IOException unused) {
            try {
                path = path.toAbsolutePath();
                e = null;
            } catch (SecurityException e2) {
                e = e2;
            }
            Path parent = path.getParent();
            while (parent != null) {
                try {
                    provider(parent).checkAccess(parent, new AccessMode[0]);
                    break;
                } catch (NoSuchFileException unused2) {
                    parent = parent.getParent();
                }
            }
            if (parent == null) {
                if (e == null) {
                    throw new FileSystemException(path.toString(), null, "Unable to determine if root directory exists");
                }
                throw e;
            }
            Iterator it = parent.relativize(path).iterator();
            while (it.hasNext()) {
                parent = parent.resolve((Path) it.next());
                createAndCheckIsDirectory(parent, fileAttributeArr);
            }
            return path;
        }
    }

    private static void createAndCheckIsDirectory(Path path, FileAttribute... fileAttributeArr) throws FileAlreadyExistsException {
        try {
            createDirectory(path, fileAttributeArr);
        } catch (FileAlreadyExistsException e) {
            if (!isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                throw e;
            }
        }
    }

    public static Path createTempFile(Path path, String str, String str2, FileAttribute<?>... fileAttributeArr) {
        return TempFileHelper.createTempFile((Path) Objects.requireNonNull(path), str, str2, fileAttributeArr);
    }

    public static Path createTempFile(String str, String str2, FileAttribute<?>... fileAttributeArr) {
        return TempFileHelper.createTempFile(null, str, str2, fileAttributeArr);
    }

    public static Path createTempDirectory(Path path, String str, FileAttribute<?>... fileAttributeArr) {
        return TempFileHelper.createTempDirectory((Path) Objects.requireNonNull(path), str, fileAttributeArr);
    }

    public static Path createTempDirectory(String str, FileAttribute<?>... fileAttributeArr) {
        return TempFileHelper.createTempDirectory(null, str, fileAttributeArr);
    }

    public static Path createSymbolicLink(Path path, Path path2, FileAttribute<?>... fileAttributeArr) {
        provider(path).createSymbolicLink(path, path2, fileAttributeArr);
        return path;
    }

    public static Path createLink(Path path, Path path2) {
        provider(path).createLink(path, path2);
        return path;
    }

    public static void delete(Path path) {
        provider(path).delete(path);
    }

    public static boolean deleteIfExists(Path path) {
        return provider(path).deleteIfExists(path);
    }

    public static Path copy(Path path, Path path2, CopyOption... copyOptionArr) {
        FileSystemProvider fileSystemProviderProvider = provider(path);
        if (provider(path2).equals(fileSystemProviderProvider)) {
            fileSystemProviderProvider.copy(path, path2, copyOptionArr);
            return path2;
        }
        CopyMoveHelper.copyToForeignTarget(path, path2, copyOptionArr);
        return path2;
    }

    public static Path move(Path path, Path path2, CopyOption... copyOptionArr) {
        FileSystemProvider fileSystemProviderProvider = provider(path);
        if (provider(path2).equals(fileSystemProviderProvider)) {
            fileSystemProviderProvider.move(path, path2, copyOptionArr);
            return path2;
        }
        CopyMoveHelper.moveToForeignTarget(path, path2, copyOptionArr);
        return path2;
    }

    public static Path readSymbolicLink(Path path) {
        return provider(path).readSymbolicLink(path);
    }

    public static FileStore getFileStore(Path path) {
        return provider(path).getFileStore(path);
    }

    public static boolean isSameFile(Path path, Path path2) {
        return provider(path).isSameFile(path, path2);
    }

    public static boolean isHidden(Path path) {
        return provider(path).isHidden(path);
    }

    public static <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> cls, LinkOption... linkOptionArr) {
        return (V) provider(path).getFileAttributeView(path, cls, linkOptionArr);
    }

    public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> cls, LinkOption... linkOptionArr) {
        return (A) provider(path).readAttributes(path, cls, linkOptionArr);
    }

    public static Path setAttribute(Path path, String str, Object obj, LinkOption... linkOptionArr) {
        provider(path).setAttribute(path, str, obj, linkOptionArr);
        return path;
    }

    public static Object getAttribute(Path path, String str, LinkOption... linkOptionArr) {
        if (str.indexOf(42) >= 0 || str.indexOf(44) >= 0) {
            ZoneId.AnonymousClass4.m(str);
            return null;
        }
        Map<String, Object> attributes = readAttributes(path, str, linkOptionArr);
        int iIndexOf = str.indexOf(58);
        if (iIndexOf != -1) {
            str = iIndexOf == str.length() ? "" : str.substring(iIndexOf + 1);
        }
        return attributes.get(str);
    }

    public static Map<String, Object> readAttributes(Path path, String str, LinkOption... linkOptionArr) {
        return provider(path).readAttributes(path, str, linkOptionArr);
    }

    public static Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... linkOptionArr) {
        return ((PosixFileAttributes) readAttributes(path, PosixFileAttributes.class, linkOptionArr)).permissions();
    }

    public static Path setPosixFilePermissions(Path path, Set<PosixFilePermission> set) {
        PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) getFileAttributeView(path, PosixFileAttributeView.class, new LinkOption[0]);
        if (posixFileAttributeView == null) {
            throw new UnsupportedOperationException();
        }
        posixFileAttributeView.setPermissions(set);
        return path;
    }

    public static UserPrincipal getOwner(Path path, LinkOption... linkOptionArr) {
        FileOwnerAttributeView fileOwnerAttributeView = (FileOwnerAttributeView) getFileAttributeView(path, FileOwnerAttributeView.class, linkOptionArr);
        if (fileOwnerAttributeView == null) {
            throw new UnsupportedOperationException();
        }
        return fileOwnerAttributeView.getOwner();
    }

    public static Path setOwner(Path path, UserPrincipal userPrincipal) {
        FileOwnerAttributeView fileOwnerAttributeView = (FileOwnerAttributeView) getFileAttributeView(path, FileOwnerAttributeView.class, new LinkOption[0]);
        if (fileOwnerAttributeView == null) {
            throw new UnsupportedOperationException();
        }
        fileOwnerAttributeView.setOwner(userPrincipal);
        return path;
    }

    public static boolean isSymbolicLink(Path path) {
        try {
            return readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS).isSymbolicLink();
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isDirectory(Path path, LinkOption... linkOptionArr) {
        if (linkOptionArr.length == 0) {
            provider(path);
        }
        try {
            return readAttributes(path, BasicFileAttributes.class, linkOptionArr).isDirectory();
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isRegularFile(Path path, LinkOption... linkOptionArr) {
        if (linkOptionArr.length == 0) {
            provider(path);
        }
        try {
            return readAttributes(path, BasicFileAttributes.class, linkOptionArr).isRegularFile();
        } catch (IOException unused) {
            return false;
        }
    }

    public static FileTime getLastModifiedTime(Path path, LinkOption... linkOptionArr) {
        return readAttributes(path, BasicFileAttributes.class, linkOptionArr).lastModifiedTime();
    }

    public static Path setLastModifiedTime(Path path, FileTime fileTime) {
        ((BasicFileAttributeView) getFileAttributeView(path, BasicFileAttributeView.class, new LinkOption[0])).setTimes((FileTime) Objects.requireNonNull(fileTime), null, null);
        return path;
    }

    public static long size(Path path) {
        return readAttributes(path, BasicFileAttributes.class, new LinkOption[0]).size();
    }

    private static boolean followLinks(LinkOption... linkOptionArr) {
        int length = linkOptionArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            LinkOption linkOption = linkOptionArr[i];
            if (linkOption != LinkOption.NOFOLLOW_LINKS) {
                linkOption.getClass();
                throw new AssertionError("Should not get here");
            }
            i++;
            z = false;
        }
        return z;
    }

    public static boolean exists(Path path, LinkOption... linkOptionArr) {
        if (linkOptionArr.length == 0) {
            provider(path);
        }
        try {
            if (followLinks(linkOptionArr)) {
                provider(path).checkAccess(path, new AccessMode[0]);
            } else {
                readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            }
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean notExists(Path path, LinkOption... linkOptionArr) {
        try {
            if (followLinks(linkOptionArr)) {
                provider(path).checkAccess(path, new AccessMode[0]);
            } else {
                readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            }
        } catch (NoSuchFileException unused) {
            return true;
        } catch (IOException unused2) {
        }
        return false;
    }

    private static boolean isAccessible(Path path, AccessMode... accessModeArr) {
        try {
            provider(path).checkAccess(path, accessModeArr);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isReadable(Path path) {
        return isAccessible(path, AccessMode.READ);
    }

    public static boolean isWritable(Path path) {
        return isAccessible(path, AccessMode.WRITE);
    }

    public static boolean isExecutable(Path path) {
        return isAccessible(path, AccessMode.EXECUTE);
    }

    public static Path walkFileTree(Path path, Set<FileVisitOption> set, int i, FileVisitor<? super Path> fileVisitor) {
        FileVisitResult fileVisitResultVisitFile;
        FileTreeWalker fileTreeWalker = new FileTreeWalker(set, i);
        try {
            FileTreeWalker.Event eventWalk = fileTreeWalker.walk(path);
            do {
                int i2 = AnonymousClass3.$SwitchMap$java$nio$file$FileTreeWalker$EventType[eventWalk.type().ordinal()];
                if (i2 == 1) {
                    IOException iOExceptionIoeException = eventWalk.ioeException();
                    fileVisitResultVisitFile = iOExceptionIoeException == null ? fileVisitor.visitFile(eventWalk.file(), eventWalk.attributes()) : fileVisitor.visitFileFailed(eventWalk.file(), iOExceptionIoeException);
                } else if (i2 == 2) {
                    fileVisitResultVisitFile = fileVisitor.preVisitDirectory(eventWalk.file(), eventWalk.attributes());
                    if (fileVisitResultVisitFile == FileVisitResult.SKIP_SUBTREE || fileVisitResultVisitFile == FileVisitResult.SKIP_SIBLINGS) {
                        fileTreeWalker.pop();
                    }
                } else if (i2 == 3) {
                    fileVisitResultVisitFile = fileVisitor.postVisitDirectory(eventWalk.file(), eventWalk.ioeException());
                    if (fileVisitResultVisitFile == FileVisitResult.SKIP_SIBLINGS) {
                        fileVisitResultVisitFile = FileVisitResult.CONTINUE;
                    }
                } else {
                    throw new AssertionError("Should not get here");
                }
                if (Objects.requireNonNull(fileVisitResultVisitFile) != FileVisitResult.CONTINUE) {
                    if (fileVisitResultVisitFile == FileVisitResult.TERMINATE) {
                        break;
                    }
                    if (fileVisitResultVisitFile == FileVisitResult.SKIP_SIBLINGS) {
                        fileTreeWalker.skipRemainingSiblings();
                    }
                    eventWalk = fileTreeWalker.next();
                } else {
                    eventWalk = fileTreeWalker.next();
                }
            } while (eventWalk != null);
            fileTreeWalker.close();
            return path;
        } catch (Throwable th) {
            try {
                fileTreeWalker.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.Files$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$java$nio$file$FileTreeWalker$EventType;

        static {
            int[] iArr = new int[FileTreeWalker.EventType.values().length];
            $SwitchMap$java$nio$file$FileTreeWalker$EventType = iArr;
            try {
                iArr[FileTreeWalker.EventType.ENTRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$nio$file$FileTreeWalker$EventType[FileTreeWalker.EventType.START_DIRECTORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$nio$file$FileTreeWalker$EventType[FileTreeWalker.EventType.END_DIRECTORY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static BufferedReader newBufferedReader(Path path, Charset charset) {
        return new BufferedReader(new InputStreamReader(newInputStream(path, new OpenOption[0]), charset.newDecoder()));
    }

    public static long copy(InputStream inputStream, Path path, CopyOption... copyOptionArr) throws IOException {
        Objects.requireNonNull(inputStream);
        int length = copyOptionArr.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            CopyOption copyOption = copyOptionArr[i];
            if (copyOption != StandardCopyOption.REPLACE_EXISTING) {
                if (copyOption == null) {
                    throw new NullPointerException("options contains 'null'");
                }
                throw new UnsupportedOperationException(copyOption + " not supported");
            }
            i++;
            z = true;
        }
        if (z) {
            try {
                deleteIfExists(path);
            } catch (SecurityException e) {
                e = e;
            }
        }
        e = null;
        try {
            OutputStream outputStreamNewOutputStream = newOutputStream(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
            try {
                long jTransferTo = InputStreamRetargetClass.transferTo(inputStream, outputStreamNewOutputStream);
                if (outputStreamNewOutputStream != null) {
                    outputStreamNewOutputStream.close();
                }
                return jTransferTo;
            } catch (Throwable th) {
                if (outputStreamNewOutputStream != null) {
                    try {
                        outputStreamNewOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (FileAlreadyExistsException e2) {
            if (e != null) {
                throw e;
            }
            throw e2;
        }
    }

    private static byte[] read(InputStream inputStream, int i) throws IOException {
        int i2;
        byte[] bArrCopyOf = new byte[i];
        int i3 = 0;
        while (true) {
            int i4 = inputStream.read(bArrCopyOf, i3, i - i3);
            if (i4 > 0) {
                i3 += i4;
            } else {
                if (i4 < 0 || (i2 = inputStream.read()) < 0) {
                    break;
                }
                if (i <= Constants.MAX_BUFFER_SIZE - i) {
                    i = Math.max(i << 1, 8192);
                } else {
                    if (i == 2147483639) {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                    i = 2147483639;
                }
                bArrCopyOf = Arrays.copyOf(bArrCopyOf, i);
                bArrCopyOf[i3] = (byte) i2;
                i3++;
            }
        }
        return i == i3 ? bArrCopyOf : Arrays.copyOf(bArrCopyOf, i3);
    }

    public static byte[] readAllBytes(Path path) {
        SeekableByteChannel seekableByteChannelNewByteChannel = newByteChannel(path, new OpenOption[0]);
        try {
            InputStream inputStreamNewInputStream = Channels.newInputStream(seekableByteChannelNewByteChannel);
            try {
                long size = seekableByteChannelNewByteChannel.size();
                if (size > 2147483639) {
                    throw new OutOfMemoryError("Required array size too large");
                }
                byte[] bArr = read(inputStreamNewInputStream, (int) size);
                if (inputStreamNewInputStream != null) {
                    inputStreamNewInputStream.close();
                }
                seekableByteChannelNewByteChannel.close();
                return bArr;
            } catch (Throwable th) {
                if (inputStreamNewInputStream != null) {
                    try {
                        inputStreamNewInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            if (seekableByteChannelNewByteChannel != null) {
                try {
                    seekableByteChannelNewByteChannel.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
            }
            throw th3;
        }
    }

    public static List<String> readAllLines(Path path, Charset charset) throws IOException {
        BufferedReader bufferedReaderNewBufferedReader = newBufferedReader(path, charset);
        try {
            ArrayList arrayList = new ArrayList();
            while (true) {
                String line = bufferedReaderNewBufferedReader.readLine();
                if (line != null) {
                    arrayList.add(line);
                } else {
                    bufferedReaderNewBufferedReader.close();
                    return arrayList;
                }
            }
        } catch (Throwable th) {
            if (bufferedReaderNewBufferedReader != null) {
                try {
                    bufferedReaderNewBufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static Path write(Path path, byte[] bArr, OpenOption... openOptionArr) throws IOException {
        Objects.requireNonNull(bArr);
        OutputStream outputStreamNewOutputStream = newOutputStream(path, openOptionArr);
        try {
            int length = bArr.length;
            int i = length;
            while (i > 0) {
                int iMin = Math.min(i, 8192);
                outputStreamNewOutputStream.write(bArr, length - i, iMin);
                i -= iMin;
            }
            if (outputStreamNewOutputStream != null) {
                outputStreamNewOutputStream.close();
            }
            return path;
        } catch (Throwable th) {
            if (outputStreamNewOutputStream != null) {
                try {
                    outputStreamNewOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static Path write(Path path, Iterable<? extends CharSequence> iterable, Charset charset, OpenOption... openOptionArr) throws IOException {
        Objects.requireNonNull(iterable);
        CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
        OutputStream outputStreamNewOutputStream = newOutputStream(path, openOptionArr);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStreamNewOutputStream, charsetEncoderNewEncoder));
            try {
                Iterator<? extends CharSequence> it = iterable.iterator();
                while (it.hasNext()) {
                    bufferedWriter.append(it.next());
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
                if (outputStreamNewOutputStream != null) {
                    outputStreamNewOutputStream.close();
                }
                return path;
            } catch (Throwable th) {
                try {
                    bufferedWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            if (outputStreamNewOutputStream != null) {
                try {
                    outputStreamNewOutputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
            }
            throw th3;
        }
    }
}
