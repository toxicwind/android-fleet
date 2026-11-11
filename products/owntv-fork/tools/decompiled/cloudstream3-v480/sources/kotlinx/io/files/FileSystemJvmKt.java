package kotlinx.io.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.JvmCoreKt;
import kotlinx.io.RawSink;
import kotlinx.io.RawSource;

/* JADX INFO: compiled from: FileSystemJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\n¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000\"\u0011\u0010\b\u001a\u00020\t8\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000*\n\u0010\n\"\u00020\u000b2\u00020\u000b¨\u0006\f"}, d2 = {"mover", "Lkotlinx/io/files/Mover;", "getMover", "()Lkotlinx/io/files/Mover;", "mover$delegate", "Lkotlin/Lazy;", "SystemFileSystem", "Lkotlinx/io/files/FileSystem;", "SystemTemporaryDirectory", "Lkotlinx/io/files/Path;", "FileNotFoundException", "Ljava/io/FileNotFoundException;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FileSystemJvmKt {
    public static final Path SystemTemporaryDirectory;
    private static final Lazy mover$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.io.files.FileSystemJvmKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return FileSystemJvmKt.mover_delegate$lambda$0();
        }
    });
    public static final FileSystem SystemFileSystem = new SystemFileSystemImpl() { // from class: kotlinx.io.files.FileSystemJvmKt$SystemFileSystem$1
        @Override // kotlinx.io.files.FileSystem
        public boolean exists(Path path) {
            Intrinsics.checkNotNullParameter(path, "path");
            return path.getFile().exists();
        }

        @Override // kotlinx.io.files.FileSystem
        public void delete(Path path, boolean mustExist) throws IOException {
            Intrinsics.checkNotNullParameter(path, "path");
            if (exists(path)) {
                if (!path.getFile().delete()) {
                    throw new IOException("Deletion failed");
                }
            } else if (mustExist) {
                throw new FileNotFoundException("File does not exist: " + path.getFile());
            }
        }

        @Override // kotlinx.io.files.FileSystem
        public void createDirectories(Path path, boolean mustCreate) throws IOException {
            Intrinsics.checkNotNullParameter(path, "path");
            if (path.getFile().mkdirs()) {
                return;
            }
            if (mustCreate) {
                throw new IOException("Path already exist: " + path);
            }
            if (path.getFile().isFile()) {
                throw new IOException("Path already exists and it's a file: " + path);
            }
        }

        @Override // kotlinx.io.files.FileSystem
        public void atomicMove(Path source, Path destination) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(destination, "destination");
            FileSystemJvmKt.getMover().move(source, destination);
        }

        @Override // kotlinx.io.files.FileSystem
        public FileMetadata metadataOrNull(Path path) {
            Intrinsics.checkNotNullParameter(path, "path");
            if (path.getFile().exists()) {
                return new FileMetadata(path.getFile().isFile(), path.getFile().isDirectory(), path.getFile().isFile() ? path.getFile().length() : -1L);
            }
            return null;
        }

        @Override // kotlinx.io.files.FileSystem
        public RawSource source(Path path) {
            Intrinsics.checkNotNullParameter(path, "path");
            return JvmCoreKt.asSource(new FileInputStream(path.getFile()));
        }

        @Override // kotlinx.io.files.FileSystem
        public RawSink sink(Path path, boolean append) {
            Intrinsics.checkNotNullParameter(path, "path");
            return JvmCoreKt.asSink(new FileOutputStream(path.getFile(), append));
        }

        @Override // kotlinx.io.files.FileSystem
        public Path resolve(Path path) throws IOException {
            Intrinsics.checkNotNullParameter(path, "path");
            if (!path.getFile().exists()) {
                throw new FileNotFoundException(path.getFile().getAbsolutePath());
            }
            File canonicalFile = path.getFile().getCanonicalFile();
            Intrinsics.checkNotNullExpressionValue(canonicalFile, "getCanonicalFile(...)");
            return new Path(canonicalFile);
        }

        @Override // kotlinx.io.files.FileSystem
        public Collection<Path> list(Path directory) throws IOException {
            Intrinsics.checkNotNullParameter(directory, "directory");
            File file = directory.getFile();
            if (!file.exists()) {
                throw new FileNotFoundException(file.getAbsolutePath());
            }
            if (!file.isDirectory()) {
                throw new IOException("Not a directory: " + file.getAbsolutePath());
            }
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            String[] list = file.list();
            if (list != null) {
                for (String str : list) {
                    Intrinsics.checkNotNull(str);
                    listCreateListBuilder.add(PathsKt.Path(directory, str));
                }
            }
            return CollectionsKt.build(listCreateListBuilder);
        }
    };

    static {
        String property = System.getProperty("java.io.tmpdir");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        SystemTemporaryDirectory = PathsJvmKt.Path(property);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Mover getMover() {
        return (Mover) mover$delegate.getValue();
    }

    static final Mover mover_delegate$lambda$0() {
        try {
            Class.forName("java.nio.file.Files");
            return new NioMover();
        } catch (ClassNotFoundException unused) {
            return new Mover() { // from class: kotlinx.io.files.FileSystemJvmKt$mover$2$1
                @Override // kotlinx.io.files.Mover
                public void move(Path source, Path destination) {
                    Intrinsics.checkNotNullParameter(source, "source");
                    Intrinsics.checkNotNullParameter(destination, "destination");
                    throw new UnsupportedOperationException("Atomic move not supported");
                }
            };
        }
    }
}
