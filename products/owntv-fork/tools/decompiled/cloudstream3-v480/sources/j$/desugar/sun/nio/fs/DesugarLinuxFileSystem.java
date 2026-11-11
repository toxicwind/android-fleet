package j$.desugar.sun.nio.fs;

import j$.nio.file.FileSystem;
import j$.nio.file.Path;
import j$.nio.file.PathMatcher;
import j$.nio.file.WatchService;
import j$.nio.file.attribute.UserPrincipalLookupService;
import j$.nio.file.spi.FileSystemProvider;
import j$.time.ZoneId;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class DesugarLinuxFileSystem extends FileSystem {
    private final DesugarLinuxFileSystemProvider provider;
    private final String rootDir;
    private final String userDir;

    /* JADX INFO: renamed from: j$.desugar.sun.nio.fs.DesugarLinuxFileSystem$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ Set m(Object obj) {
            return AnonymousClass3.m(new Object[]{obj});
        }
    }

    /* JADX INFO: renamed from: j$.desugar.sun.nio.fs.DesugarLinuxFileSystem$1, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass1 {
        public static /* synthetic */ List m(Object[] objArr) {
            ArrayList arrayList = new ArrayList(objArr.length);
            for (Object obj : objArr) {
                arrayList.add(Objects.requireNonNull(obj));
            }
            return Collections.unmodifiableList(arrayList);
        }
    }

    /* JADX INFO: renamed from: j$.desugar.sun.nio.fs.DesugarLinuxFileSystem$2, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass2 {
        public static /* synthetic */ List m(Object obj) {
            return AnonymousClass1.m(new Object[]{obj});
        }
    }

    /* JADX INFO: renamed from: j$.desugar.sun.nio.fs.DesugarLinuxFileSystem$3, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass3 {
        public static /* synthetic */ Set m(Object[] objArr) {
            HashSet hashSet = new HashSet(objArr.length);
            for (Object obj : objArr) {
                if (!hashSet.add(Objects.requireNonNull(obj))) {
                    ZoneId.AnonymousClass2.AnonymousClass0.m("duplicate element: ", obj);
                    return null;
                }
            }
            return Collections.unmodifiableSet(hashSet);
        }
    }

    @Override // j$.nio.file.FileSystem
    public boolean isOpen() {
        return true;
    }

    @Override // j$.nio.file.FileSystem
    public boolean isReadOnly() {
        return false;
    }

    public DesugarLinuxFileSystem(DesugarLinuxFileSystemProvider desugarLinuxFileSystemProvider, String str, String str2) {
        this.provider = desugarLinuxFileSystemProvider;
        this.userDir = str;
        this.rootDir = str2;
    }

    @Override // j$.nio.file.FileSystem
    public FileSystemProvider provider() {
        return this.provider;
    }

    @Override // j$.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.FileSystem
    public String getSeparator() {
        return "/";
    }

    @Override // j$.nio.file.FileSystem
    public Iterable getRootDirectories() {
        return AnonymousClass2.m(new DesugarUnixPath(this, "/", this.userDir, this.rootDir));
    }

    @Override // j$.nio.file.FileSystem
    public Iterable getFileStores() {
        throw new UnsupportedOperationException("");
    }

    @Override // j$.nio.file.FileSystem
    public Set supportedFileAttributeViews() {
        return AnonymousClass0.m("basic");
    }

    @Override // j$.nio.file.FileSystem
    public DesugarUnixPath getPath(String str, String... strArr) {
        if (strArr.length != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            for (String str2 : strArr) {
                if (!str2.isEmpty()) {
                    if (sb.length() > 0) {
                        sb.append('/');
                    }
                    sb.append(str2);
                }
            }
            str = sb.toString();
        }
        return new DesugarUnixPath(this, str, this.userDir, this.rootDir);
    }

    @Override // j$.nio.file.FileSystem
    public PathMatcher getPathMatcher(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf <= 0 || iIndexOf == str.length()) {
            throw new IllegalArgumentException(String.format("Requested <syntax>:<pattern> spliterator(':') position(%d) is out of bound in %s", Integer.valueOf(iIndexOf), str));
        }
        String strSubstring = str.substring(0, iIndexOf);
        String strSubstring2 = str.substring(iIndexOf + 1);
        if (strSubstring.equalsIgnoreCase("glob")) {
            strSubstring2 = DesugarGlobs.toUnixRegexPattern(strSubstring2);
        } else if (!strSubstring.equalsIgnoreCase("regex")) {
            throw new UnsupportedOperationException("Syntax '" + strSubstring + "' not recognized");
        }
        final Pattern patternCompile = Pattern.compile(strSubstring2);
        return new PathMatcher() { // from class: j$.desugar.sun.nio.fs.DesugarLinuxFileSystem.4
            @Override // j$.nio.file.PathMatcher
            public final boolean matches(Path path) {
                return DesugarLinuxFileSystem.lambda$getPathMatcher$0(patternCompile, path);
            }
        };
    }

    static /* synthetic */ boolean lambda$getPathMatcher$0(Pattern pattern, Path path) {
        return pattern.matcher(path.toString()).matches();
    }

    @Override // j$.nio.file.FileSystem
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        throw new UnsupportedOperationException();
    }

    @Override // j$.nio.file.FileSystem
    public WatchService newWatchService() {
        throw new UnsupportedOperationException();
    }
}
