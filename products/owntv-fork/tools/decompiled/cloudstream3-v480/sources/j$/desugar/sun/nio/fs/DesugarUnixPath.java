package j$.desugar.sun.nio.fs;

import androidx.media3.extractor.text.ttml.TtmlNode;
import j$.nio.file.AccessMode;
import j$.nio.file.FileSystem;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.WatchEvent;
import j$.nio.file.WatchKey;
import j$.nio.file.WatchService;
import j$.time.ZoneId;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.io.File;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class DesugarUnixPath implements Path {
    private static final Pattern PATH_COMPONENT_SPLITERATOR = Pattern.compile("/+");
    private volatile byte[] byteArrayValue;
    private final List fileNames;
    private final FileSystem fileSystem;
    private final boolean isAbsolutePath;
    private final String pathText;
    private final String rootDir;
    private final String userDir;

    /* JADX INFO: renamed from: j$.desugar.sun.nio.fs.DesugarUnixPath$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ String m(CharSequence charSequence, Iterable iterable) {
            if (charSequence == null) {
                throw new NullPointerException(TtmlNode.RUBY_DELIMITER);
            }
            StringBuilder sb = new StringBuilder();
            Iterator it = iterable.iterator();
            if (it.hasNext()) {
                while (true) {
                    sb.append((CharSequence) it.next());
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(charSequence);
                }
            }
            return sb.toString();
        }
    }

    @Override // j$.nio.file.Path, j$.nio.file.Watchable
    public /* synthetic */ WatchKey register(WatchService watchService, WatchEvent.Kind... kindArr) {
        return register(watchService, kindArr, new WatchEvent.Modifier[0]);
    }

    @Override // j$.nio.file.Path
    public /* synthetic */ Path resolve(String str) {
        return resolve(getFileSystem().getPath(str, new String[0]));
    }

    public DesugarUnixPath(FileSystem fileSystem, String str, String str2, String str3) {
        this(fileSystem, str.startsWith("/"), getFileNames(str), str2, str3);
    }

    private DesugarUnixPath(FileSystem fileSystem, boolean z, List list, String str, String str2) {
        this.fileSystem = fileSystem;
        this.isAbsolutePath = z;
        this.fileNames = list;
        this.pathText = getPathText(z, list);
        this.userDir = str;
        this.rootDir = str2;
    }

    private static List getFileNames(String str) {
        if (str.isEmpty()) {
            return Collections.singletonList("");
        }
        return (List) DesugarArrays.stream(PATH_COMPONENT_SPLITERATOR.split(str)).filter(new Predicate() { // from class: j$.desugar.sun.nio.fs.DesugarUnixPath.1
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DesugarUnixPath.lambda$getFileNames$0((String) obj);
            }
        }).collect(Collectors.toUnmodifiableList());
    }

    static /* synthetic */ boolean lambda$getFileNames$0(String str) {
        return !str.isEmpty();
    }

    private static String getPathText(boolean z, Collection collection) {
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "/" : "");
        sb.append(AnonymousClass0.m("/", collection));
        return sb.toString();
    }

    @Override // j$.nio.file.Path
    public FileSystem getFileSystem() {
        return this.fileSystem;
    }

    @Override // j$.nio.file.Path
    public boolean isAbsolute() {
        return this.isAbsolutePath;
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath getRoot() {
        if (!isAbsolute()) {
            return null;
        }
        FileSystem fileSystem = getFileSystem();
        String str = this.rootDir;
        return new DesugarUnixPath(fileSystem, str, this.userDir, str);
    }

    DesugarUnixPath getUserDir() {
        FileSystem fileSystem = getFileSystem();
        String str = this.userDir;
        return new DesugarUnixPath(fileSystem, str, str, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath getFileName() {
        if (this.fileNames.isEmpty()) {
            if (this.isAbsolutePath) {
                return null;
            }
            return this;
        }
        return new DesugarUnixPath(this.fileSystem, (String) this.fileNames.get(getNameCount() - 1), this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath getParent() {
        int nameCount = getNameCount();
        if (nameCount == 0) {
            return null;
        }
        if (nameCount == 1 && !this.isAbsolutePath) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (this.isAbsolutePath) {
            sb.append("/");
        }
        sb.append(subPathName(0, nameCount - 1));
        return new DesugarUnixPath(this.fileSystem, sb.toString(), this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public int getNameCount() {
        return this.fileNames.size();
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath getName(int i) {
        if (i < 0 || i >= getNameCount()) {
            throw new IllegalArgumentException(String.format("Requested name for index (%d) is out of bound in \n%s.", Integer.valueOf(i), this));
        }
        return new DesugarUnixPath(this.fileSystem, (String) this.fileNames.get(i), this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath subpath(int i, int i2) {
        return new DesugarUnixPath(this.fileSystem, subPathName(i, i2), this.userDir, this.rootDir);
    }

    private String subPathName(int i, int i2) {
        return AnonymousClass0.m("/", this.fileNames.subList(i, i2));
    }

    @Override // j$.nio.file.Path
    public boolean startsWith(String str) {
        return startsWith(new DesugarUnixPath(this.fileSystem, str, this.userDir, this.rootDir));
    }

    @Override // j$.nio.file.Path
    public boolean startsWith(Path path) {
        int nameCount;
        Objects.requireNonNull(path);
        if (!(path instanceof DesugarUnixPath) || isAbsolute() != path.isAbsolute() || getNameCount() < (nameCount = path.getNameCount())) {
            return false;
        }
        for (int i = 0; i < nameCount; i++) {
            if (!getName(i).equals(path.getName(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // j$.nio.file.Path
    public boolean endsWith(String str) {
        return endsWith(new DesugarUnixPath(this.fileSystem, str, this.userDir, this.rootDir));
    }

    @Override // j$.nio.file.Path
    public boolean endsWith(Path path) {
        Objects.requireNonNull(path);
        if (!(path instanceof DesugarUnixPath)) {
            return false;
        }
        if (path.isAbsolute()) {
            return equals(path);
        }
        int nameCount = path.getNameCount();
        if (getNameCount() < nameCount) {
            return false;
        }
        int nameCount2 = getNameCount();
        for (int i = nameCount - 1; i >= 0; i--) {
            if (!getName((i - nameCount) + nameCount2).equals(path.getName(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // j$.nio.file.Path
    public boolean equals(Object obj) {
        return (obj instanceof DesugarUnixPath) && compareTo((Path) obj) == 0;
    }

    public int hashCode() {
        return this.pathText.hashCode();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(Path path) {
        return this.pathText.compareTo(((DesugarUnixPath) path).pathText);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath normalize() {
        ArrayDeque arrayDeque = new ArrayDeque();
        for (String str : this.fileNames) {
            str.getClass();
            if (!str.equals(".")) {
                if (str.equals("..")) {
                    arrayDeque.removeLast();
                } else {
                    arrayDeque.add(str);
                }
            }
        }
        return new DesugarUnixPath(this.fileSystem, getPathText(this.isAbsolutePath, arrayDeque), this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath resolve(Path path) {
        if (!(path instanceof DesugarUnixPath)) {
            throw new IllegalArgumentException(String.format("Expected to resolve paths on the same file system as DesugarUnixPath, but gets %s (%s).", path, path.getFileSystem()));
        }
        if (path.isAbsolute()) {
            return (DesugarUnixPath) path;
        }
        return new DesugarUnixPath(this.fileSystem, this.pathText + "/" + path, this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath resolveSibling(Path path) {
        if (!(Objects.requireNonNull(path) instanceof DesugarUnixPath)) {
            throw new IllegalArgumentException(String.format("Expected to resolve paths on the same file system as DesugarUnixPath, but gets %s (%s).", path, path.getFileSystem()));
        }
        DesugarUnixPath parent = getParent();
        return parent == null ? (DesugarUnixPath) path : parent.resolve(path);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath resolveSibling(String str) {
        return resolveSibling((Path) new DesugarUnixPath(this.fileSystem, str, this.userDir, this.rootDir));
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath relativize(Path path) {
        int i = 0;
        if (!(path instanceof DesugarUnixPath)) {
            throw new IllegalArgumentException(String.format("Expected to resolve paths on the same file system as DesugarUnixPath, but gets %s (%s).", path, path.getFileSystem()));
        }
        if (isAbsolute() != path.isAbsolute()) {
            ZoneId.AnonymousClass4.m("'other' is different type of Path in absolute property.");
            return null;
        }
        List list = ((DesugarUnixPath) path).fileNames;
        int size = this.fileNames.size();
        int size2 = list.size();
        while (i < size && i < size2 && ((String) this.fileNames.get(i)).equals(list.get(i))) {
            i++;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = i; i2 < size; i2++) {
            arrayList.add("..");
        }
        while (i < size2) {
            arrayList.add((String) list.get(i));
            i++;
        }
        return new DesugarUnixPath(this.fileSystem, false, arrayList, this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public File toFile() {
        return new File(toString());
    }

    @Override // j$.nio.file.Path
    public URI toUri() {
        return DesugarUnixUriUtils.toUri(this);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath toAbsolutePath() {
        return isAbsolute() ? this : getUserDir().resolve((Path) this);
    }

    @Override // j$.nio.file.Path
    public DesugarUnixPath toRealPath(LinkOption... linkOptionArr) {
        getFileSystem().provider().checkAccess(this, AccessMode.READ);
        if (Arrays.asList(linkOptionArr).contains(LinkOption.NOFOLLOW_LINKS)) {
            return toAbsolutePath();
        }
        return new DesugarUnixPath(this.fileSystem, toFile().getCanonicalPath(), this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.Path
    public String toString() {
        return this.pathText;
    }

    byte[] asByteArray() {
        if (this.byteArrayValue == null) {
            this.byteArrayValue = this.pathText.getBytes(DesugarUtil.jnuEncoding());
        }
        return this.byteArrayValue;
    }

    @Override // j$.nio.file.Path, java.lang.Iterable
    public Iterator iterator() {
        return Path.CC.$default$iterator(this);
    }

    @Override // j$.nio.file.Path, j$.nio.file.Watchable
    public WatchKey register(WatchService watchService, WatchEvent.Kind[] kindArr, WatchEvent.Modifier... modifierArr) {
        throw new UnsupportedOperationException("Watch Service is not supported");
    }
}
