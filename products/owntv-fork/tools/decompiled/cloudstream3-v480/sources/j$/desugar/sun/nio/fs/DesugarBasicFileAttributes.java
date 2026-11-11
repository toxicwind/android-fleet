package j$.desugar.sun.nio.fs;

import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileTime;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
class DesugarBasicFileAttributes implements BasicFileAttributes {
    private final FileTime creationTime;
    private final Object fileKey;
    private final boolean isDirectory;
    private final boolean isOther;
    private final boolean isRegularFile;
    private final boolean isSymbolicLink;
    private final FileTime lastAccessTime;
    private final FileTime lastModifiedTime;
    private final long size;

    public static DesugarBasicFileAttributes create(File file) {
        FileTime fileTimeFrom = FileTime.from(file.lastModified(), TimeUnit.MILLISECONDS);
        boolean zIsFile = file.isFile();
        boolean zIsDirectory = file.isDirectory();
        boolean zIsSymlink = isSymlink(file);
        return new DesugarBasicFileAttributes(fileTimeFrom, fileTimeFrom, fileTimeFrom, zIsFile, zIsDirectory, zIsSymlink, (zIsFile || zIsDirectory || zIsSymlink) ? false : true, file.length(), Integer.valueOf(file.hashCode()));
    }

    public DesugarBasicFileAttributes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3, boolean z, boolean z2, boolean z3, boolean z4, long j, Object obj) {
        this.lastModifiedTime = fileTime;
        this.lastAccessTime = fileTime2;
        this.creationTime = fileTime3;
        this.isRegularFile = z;
        this.isDirectory = z2;
        this.isSymbolicLink = z3;
        this.isOther = z4;
        this.size = j;
        this.fileKey = obj;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public FileTime lastModifiedTime() {
        return this.lastModifiedTime;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public FileTime lastAccessTime() {
        return this.lastAccessTime;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public FileTime creationTime() {
        return this.creationTime;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public boolean isRegularFile() {
        return this.isRegularFile;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public boolean isDirectory() {
        return this.isDirectory;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public boolean isSymbolicLink() {
        return this.isSymbolicLink;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public boolean isOther() {
        return this.isOther;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public long size() {
        return this.size;
    }

    @Override // j$.nio.file.attribute.BasicFileAttributes
    public Object fileKey() {
        return this.fileKey;
    }

    public static boolean isSymlink(File file) {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        try {
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            return !file.getCanonicalFile().equals(file.getAbsoluteFile());
        } catch (IOException unused) {
            return false;
        }
    }
}
