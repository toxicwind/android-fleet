package j$.nio.file;

import coil3.util.UtilsKt;
import j$.lang.Iterable;
import j$.nio.file.spi.FileSystemProvider;
import j$.time.ZoneId;
import j$.util.Spliterator;
import java.io.File;
import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public interface Path extends Comparable<Path>, Iterable<Path>, Watchable {

    /* JADX INFO: renamed from: j$.nio.file.Path$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ LinkOption[] m(java.nio.file.LinkOption[] linkOptionArr) {
            if (linkOptionArr == null) {
                return null;
            }
            int length = linkOptionArr.length;
            LinkOption[] linkOptionArr2 = new LinkOption[length];
            for (int i = 0; i < length; i++) {
                linkOptionArr2[i] = LinkOption.EnumConversion.convert(linkOptionArr[i]);
            }
            return linkOptionArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.Path$2, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass2 {
        public static /* synthetic */ WatchEvent.Kind[] m(java.nio.file.WatchEvent.Kind[] kindArr) {
            if (kindArr == null) {
                return null;
            }
            int length = kindArr.length;
            WatchEvent.Kind[] kindArr2 = new WatchEvent.Kind[length];
            for (int i = 0; i < length; i++) {
                kindArr2[i] = WatchEventKindConversions.convert(kindArr[i]);
            }
            return kindArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.Path$3, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass3 {
        public static /* synthetic */ WatchEvent.Modifier[] m(java.nio.file.WatchEvent.Modifier[] modifierArr) {
            if (modifierArr == null) {
                return null;
            }
            int length = modifierArr.length;
            WatchEvent.Modifier[] modifierArr2 = new WatchEvent.Modifier[length];
            for (int i = 0; i < length; i++) {
                modifierArr2[i] = WatchEvent.Modifier.VivifiedWrapper.convert(modifierArr[i]);
            }
            return modifierArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.Path$4, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass4 {
        public static /* synthetic */ java.nio.file.LinkOption[] m(LinkOption[] linkOptionArr) {
            if (linkOptionArr == null) {
                return null;
            }
            int length = linkOptionArr.length;
            java.nio.file.LinkOption[] linkOptionArr2 = new java.nio.file.LinkOption[length];
            for (int i = 0; i < length; i++) {
                linkOptionArr2[i] = LinkOption.EnumConversion.convert(linkOptionArr[i]);
            }
            return linkOptionArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.Path$5, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass5 {
        public static /* synthetic */ java.nio.file.WatchEvent.Kind[] m(WatchEvent.Kind[] kindArr) {
            if (kindArr == null) {
                return null;
            }
            int length = kindArr.length;
            java.nio.file.WatchEvent.Kind[] kindArr2 = new java.nio.file.WatchEvent.Kind[length];
            for (int i = 0; i < length; i++) {
                kindArr2[i] = WatchEventKindConversions.convert(kindArr[i]);
            }
            return kindArr2;
        }
    }

    /* JADX INFO: renamed from: j$.nio.file.Path$6, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass6 {
        public static /* synthetic */ java.nio.file.WatchEvent.Modifier[] m(WatchEvent.Modifier[] modifierArr) {
            if (modifierArr == null) {
                return null;
            }
            int length = modifierArr.length;
            java.nio.file.WatchEvent.Modifier[] modifierArr2 = new java.nio.file.WatchEvent.Modifier[length];
            for (int i = 0; i < length; i++) {
                modifierArr2[i] = WatchEvent.Modifier.Wrapper.convert(modifierArr[i]);
            }
            return modifierArr2;
        }
    }

    public final /* synthetic */ class VivifiedWrapper implements Path, Iterable {
        public final /* synthetic */ java.nio.file.Path wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.Path path) {
            this.wrappedValue = path;
        }

        public static /* synthetic */ Path convert(java.nio.file.Path path) {
            if (path == null) {
                return null;
            }
            return path instanceof Wrapper ? Path.this : new VivifiedWrapper(path);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // j$.nio.file.Path
        public /* synthetic */ int compareTo(Path path) {
            return this.wrappedValue.compareTo(Wrapper.convert(path));
        }

        @Override // java.lang.Comparable
        public /* synthetic */ int compareTo(Path path) {
            return this.wrappedValue.compareTo(PathApiFlips.convertPath(path));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ boolean endsWith(Path path) {
            return this.wrappedValue.endsWith(Wrapper.convert(path));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ boolean endsWith(String str) {
            return this.wrappedValue.endsWith(str);
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.Path path = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return path.equals(obj);
        }

        @Override // java.lang.Iterable, j$.lang.Iterable
        public /* synthetic */ void forEach(Consumer consumer) {
            Iterable.EL.forEach(this.wrappedValue, consumer);
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path getFileName() {
            return convert(this.wrappedValue.getFileName());
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ FileSystem getFileSystem() {
            return FileSystem.VivifiedWrapper.convert(this.wrappedValue.getFileSystem());
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path getName(int i) {
            return convert(this.wrappedValue.getName(i));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ int getNameCount() {
            return this.wrappedValue.getNameCount();
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path getParent() {
            return convert(this.wrappedValue.getParent());
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path getRoot() {
            return convert(this.wrappedValue.getRoot());
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ boolean isAbsolute() {
            return this.wrappedValue.isAbsolute();
        }

        @Override // j$.nio.file.Path, java.lang.Iterable
        public /* synthetic */ Iterator iterator() {
            return PathApiFlips.flipIteratorPath(this.wrappedValue.iterator());
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path normalize() {
            return convert(this.wrappedValue.normalize());
        }

        @Override // j$.nio.file.Path, j$.nio.file.Watchable
        public /* synthetic */ WatchKey register(WatchService watchService, WatchEvent.Kind[] kindArr) {
            return WatchKey.VivifiedWrapper.convert(this.wrappedValue.register(WatchService.Wrapper.convert(watchService), AnonymousClass5.m(kindArr)));
        }

        @Override // j$.nio.file.Path, j$.nio.file.Watchable
        public /* synthetic */ WatchKey register(WatchService watchService, WatchEvent.Kind[] kindArr, WatchEvent.Modifier[] modifierArr) {
            return WatchKey.VivifiedWrapper.convert(this.wrappedValue.register(WatchService.Wrapper.convert(watchService), AnonymousClass5.m(kindArr), AnonymousClass6.m(modifierArr)));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path relativize(Path path) {
            return convert(this.wrappedValue.relativize(Wrapper.convert(path)));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path resolve(Path path) {
            return convert(this.wrappedValue.resolve(Wrapper.convert(path)));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path resolve(String str) {
            return convert(this.wrappedValue.resolve(str));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path resolveSibling(Path path) {
            return convert(this.wrappedValue.resolveSibling(Wrapper.convert(path)));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path resolveSibling(String str) {
            return convert(this.wrappedValue.resolveSibling(str));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.Iterable, j$.lang.Iterable, j$.util.Collection
        public /* synthetic */ Spliterator spliterator() {
            return Spliterator.VivifiedWrapper.convert(this.wrappedValue.spliterator());
        }

        @Override // java.lang.Iterable
        public /* synthetic */ java.util.Spliterator<Path> spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ boolean startsWith(Path path) {
            return this.wrappedValue.startsWith(Wrapper.convert(path));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ boolean startsWith(String str) {
            return this.wrappedValue.startsWith(str);
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path subpath(int i, int i2) {
            return convert(this.wrappedValue.subpath(i, i2));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path toAbsolutePath() {
            return convert(this.wrappedValue.toAbsolutePath());
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ File toFile() {
            return this.wrappedValue.toFile();
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ Path toRealPath(LinkOption[] linkOptionArr) {
            return convert(this.wrappedValue.toRealPath(AnonymousClass4.m(linkOptionArr)));
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ String toString() {
            return this.wrappedValue.toString();
        }

        @Override // j$.nio.file.Path
        public /* synthetic */ URI toUri() {
            return this.wrappedValue.toUri();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.Path {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.Path convert(Path path) {
            if (path == null) {
                return null;
            }
            return path instanceof VivifiedWrapper ? ((VivifiedWrapper) path).wrappedValue : path.new Wrapper();
        }

        @Override // java.lang.Comparable
        public /* synthetic */ int compareTo(java.nio.file.Path path) {
            return Path.this.compareTo(PathApiFlips.convertPath(path));
        }

        @Override // java.nio.file.Path
        /* JADX INFO: renamed from: compareTo, reason: avoid collision after fix types in other method */
        public /* synthetic */ int compareTo2(java.nio.file.Path path) {
            return Path.this.compareTo(VivifiedWrapper.convert(path));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ boolean endsWith(String str) {
            return Path.this.endsWith(str);
        }

        @Override // java.nio.file.Path
        public /* synthetic */ boolean endsWith(java.nio.file.Path path) {
            return Path.this.endsWith(VivifiedWrapper.convert(path));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ boolean equals(Object obj) {
            Path path = Path.this;
            if (obj instanceof Wrapper) {
                obj = Path.this;
            }
            return path.equals(obj);
        }

        @Override // java.lang.Iterable
        public /* synthetic */ void forEach(Consumer<? super java.nio.file.Path> consumer) {
            Iterable.EL.forEach(Path.this, consumer);
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path getFileName() {
            return convert(Path.this.getFileName());
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.FileSystem getFileSystem() {
            return FileSystem.Wrapper.convert(Path.this.getFileSystem());
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path getName(int i) {
            return convert(Path.this.getName(i));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ int getNameCount() {
            return Path.this.getNameCount();
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path getParent() {
            return convert(Path.this.getParent());
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path getRoot() {
            return convert(Path.this.getRoot());
        }

        @Override // java.nio.file.Path
        public /* synthetic */ int hashCode() {
            return Path.this.hashCode();
        }

        @Override // java.nio.file.Path
        public /* synthetic */ boolean isAbsolute() {
            return Path.this.isAbsolute();
        }

        @Override // java.nio.file.Path, java.lang.Iterable
        public /* synthetic */ Iterator iterator() {
            return PathApiFlips.flipIteratorPath(Path.this.iterator());
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path normalize() {
            return convert(Path.this.normalize());
        }

        @Override // java.nio.file.Path, java.nio.file.Watchable
        public /* synthetic */ java.nio.file.WatchKey register(java.nio.file.WatchService watchService, java.nio.file.WatchEvent.Kind[] kindArr) {
            return WatchKey.Wrapper.convert(Path.this.register(WatchService.VivifiedWrapper.convert(watchService), AnonymousClass2.m(kindArr)));
        }

        @Override // java.nio.file.Path, java.nio.file.Watchable
        public /* synthetic */ java.nio.file.WatchKey register(java.nio.file.WatchService watchService, java.nio.file.WatchEvent.Kind[] kindArr, java.nio.file.WatchEvent.Modifier[] modifierArr) {
            return WatchKey.Wrapper.convert(Path.this.register(WatchService.VivifiedWrapper.convert(watchService), AnonymousClass2.m(kindArr), AnonymousClass3.m(modifierArr)));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path relativize(java.nio.file.Path path) {
            return convert(Path.this.relativize(VivifiedWrapper.convert(path)));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path resolve(String str) {
            return convert(Path.this.resolve(str));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path resolve(java.nio.file.Path path) {
            return convert(Path.this.resolve(VivifiedWrapper.convert(path)));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path resolveSibling(String str) {
            return convert(Path.this.resolveSibling(str));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path resolveSibling(java.nio.file.Path path) {
            return convert(Path.this.resolveSibling(VivifiedWrapper.convert(path)));
        }

        @Override // java.lang.Iterable
        public /* synthetic */ java.util.Spliterator<java.nio.file.Path> spliterator() {
            return Spliterator.Wrapper.convert(Iterable.EL.spliterator(Path.this));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ boolean startsWith(String str) {
            return Path.this.startsWith(str);
        }

        @Override // java.nio.file.Path
        public /* synthetic */ boolean startsWith(java.nio.file.Path path) {
            return Path.this.startsWith(VivifiedWrapper.convert(path));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path subpath(int i, int i2) {
            return convert(Path.this.subpath(i, i2));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path toAbsolutePath() {
            return convert(Path.this.toAbsolutePath());
        }

        @Override // java.nio.file.Path
        public /* synthetic */ File toFile() {
            return Path.this.toFile();
        }

        @Override // java.nio.file.Path
        public /* synthetic */ java.nio.file.Path toRealPath(java.nio.file.LinkOption[] linkOptionArr) {
            return convert(Path.this.toRealPath(AnonymousClass0.m(linkOptionArr)));
        }

        @Override // java.nio.file.Path
        public /* synthetic */ String toString() {
            return Path.this.toString();
        }

        @Override // java.nio.file.Path
        public /* synthetic */ URI toUri() {
            return Path.this.toUri();
        }
    }

    int compareTo(Path path);

    boolean endsWith(Path path);

    boolean endsWith(String str);

    boolean equals(Object obj);

    Path getFileName();

    FileSystem getFileSystem();

    Path getName(int i);

    int getNameCount();

    Path getParent();

    Path getRoot();

    boolean isAbsolute();

    Iterator iterator();

    Path normalize();

    WatchKey register(WatchService watchService, WatchEvent.Kind... kindArr);

    WatchKey register(WatchService watchService, WatchEvent.Kind[] kindArr, WatchEvent.Modifier... modifierArr);

    Path relativize(Path path);

    Path resolve(Path path);

    Path resolve(String str);

    Path resolveSibling(Path path);

    Path resolveSibling(String str);

    boolean startsWith(Path path);

    boolean startsWith(String str);

    Path subpath(int i, int i2);

    Path toAbsolutePath();

    File toFile();

    Path toRealPath(LinkOption... linkOptionArr);

    String toString();

    URI toUri();

    /* JADX INFO: renamed from: j$.nio.file.Path$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static Path of(String str, String... strArr) {
            return FileSystems.getDefault().getPath(str, strArr);
        }

        public static Path of(URI uri) {
            String scheme = uri.getScheme();
            if (scheme == null) {
                ZoneId.AnonymousClass4.m("Missing scheme");
                return null;
            }
            if (scheme.equalsIgnoreCase(UtilsKt.SCHEME_FILE)) {
                return FileSystems.getDefault().provider().getPath(uri);
            }
            for (FileSystemProvider fileSystemProvider : FileSystemProvider.installedProviders()) {
                if (fileSystemProvider.getScheme().equalsIgnoreCase(scheme)) {
                    return fileSystemProvider.getPath(uri);
                }
            }
            throw new FileSystemNotFoundException("Provider \"" + scheme + "\" not installed");
        }

        public static Iterator $default$iterator(final Path path) {
            return new Iterator() { // from class: j$.nio.file.Path.1
                private int i = 0;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.i < Path.this.getNameCount();
                }

                @Override // java.util.Iterator
                public Path next() {
                    if (this.i < Path.this.getNameCount()) {
                        Path name = Path.this.getName(this.i);
                        this.i++;
                        return name;
                    }
                    throw new NoSuchElementException();
                }
            };
        }
    }
}
