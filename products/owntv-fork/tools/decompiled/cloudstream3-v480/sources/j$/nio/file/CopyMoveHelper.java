package j$.nio.file;

import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.FileAlreadyExistsException;

/* JADX INFO: loaded from: classes3.dex */
abstract class CopyMoveHelper {

    private static class CopyOptions {
        boolean replaceExisting = false;
        boolean copyAttributes = false;
        boolean followLinks = true;

        private CopyOptions() {
        }

        static CopyOptions parse(CopyOption... copyOptionArr) {
            CopyOptions copyOptions = new CopyOptions();
            for (CopyOption copyOption : copyOptionArr) {
                if (copyOption == StandardCopyOption.REPLACE_EXISTING) {
                    copyOptions.replaceExisting = true;
                } else if (copyOption == LinkOption.NOFOLLOW_LINKS) {
                    copyOptions.followLinks = false;
                } else if (copyOption == StandardCopyOption.COPY_ATTRIBUTES) {
                    copyOptions.copyAttributes = true;
                } else {
                    copyOption.getClass();
                    throw new UnsupportedOperationException("'" + copyOption + "' is not a recognized copy option");
                }
            }
            return copyOptions;
        }
    }

    private static CopyOption[] convertMoveToCopyOptions(CopyOption... copyOptionArr) throws AtomicMoveNotSupportedException {
        int length = copyOptionArr.length;
        CopyOption[] copyOptionArr2 = new CopyOption[length + 2];
        for (int i = 0; i < length; i++) {
            CopyOption copyOption = copyOptionArr[i];
            if (copyOption == StandardCopyOption.ATOMIC_MOVE) {
                throw new AtomicMoveNotSupportedException(null, null, "Atomic move between providers is not supported");
            }
            copyOptionArr2[i] = copyOption;
        }
        copyOptionArr2[length] = LinkOption.NOFOLLOW_LINKS;
        copyOptionArr2[length + 1] = StandardCopyOption.COPY_ATTRIBUTES;
        return copyOptionArr2;
    }

    static void copyToForeignTarget(Path path, Path path2, CopyOption... copyOptionArr) {
        CopyOptions copyOptions = CopyOptions.parse(copyOptionArr);
        BasicFileAttributes attributes = Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, copyOptions.followLinks ? new LinkOption[0] : new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        if (attributes.isSymbolicLink()) {
            throw new IOException("Copying of symbolic links not supported");
        }
        if (copyOptions.replaceExisting) {
            Files.deleteIfExists(path2);
        } else if (Files.exists(path2, new LinkOption[0])) {
            throw new FileAlreadyExistsException(path2.toString());
        }
        if (attributes.isDirectory()) {
            Files.createDirectory(path2, new FileAttribute[0]);
        } else {
            InputStream inputStreamNewInputStream = Files.newInputStream(path, new OpenOption[0]);
            try {
                Files.copy(inputStreamNewInputStream, path2, new CopyOption[0]);
                if (inputStreamNewInputStream != null) {
                    inputStreamNewInputStream.close();
                }
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
        }
        if (copyOptions.copyAttributes) {
            try {
                ((BasicFileAttributeView) Files.getFileAttributeView(path2, BasicFileAttributeView.class, new LinkOption[0])).setTimes(attributes.lastModifiedTime(), attributes.lastAccessTime(), attributes.creationTime());
            } catch (Throwable th3) {
                try {
                    Files.delete(path2);
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        }
    }

    static void moveToForeignTarget(Path path, Path path2, CopyOption... copyOptionArr) {
        copyToForeignTarget(path, path2, convertMoveToCopyOptions(copyOptionArr));
        Files.delete(path);
    }
}
