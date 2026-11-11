package j$.nio.file.attribute;

import j$.nio.file.FileApiFlips;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FileAttributeConversions {
    public static FileTime convert(java.nio.file.attribute.FileTime fileTime) {
        if (fileTime == null) {
            return null;
        }
        return FileTime.fromMillis(fileTime.toMillis());
    }

    public static java.nio.file.attribute.FileTime convert(FileTime fileTime) {
        if (fileTime == null) {
            return null;
        }
        return java.nio.file.attribute.FileTime.fromMillis(fileTime.toMillis());
    }

    public static FileAttribute convert(final java.nio.file.attribute.FileAttribute fileAttribute) {
        if (fileAttribute == null) {
            return null;
        }
        if (isPosixPermissionAttributes(fileAttribute.value())) {
            return new FileAttribute() { // from class: j$.nio.file.attribute.FileAttributeConversions.1
                @Override // j$.nio.file.attribute.FileAttribute
                public String name() {
                    return "posix:permissions";
                }

                @Override // j$.nio.file.attribute.FileAttribute
                public Object value() {
                    return Collections.unmodifiableSet(FileApiFlips.flipPosixPermissionSet((Set) fileAttribute.value()));
                }
            };
        }
        return FileAttribute.VivifiedWrapper.convert(fileAttribute);
    }

    public static java.nio.file.attribute.FileAttribute convert(final FileAttribute fileAttribute) {
        if (fileAttribute == null) {
            return null;
        }
        if (isPosixPermissionAttributes(fileAttribute.value())) {
            return new java.nio.file.attribute.FileAttribute() { // from class: j$.nio.file.attribute.FileAttributeConversions.2
                @Override // java.nio.file.attribute.FileAttribute
                public String name() {
                    return "posix:permissions";
                }

                @Override // java.nio.file.attribute.FileAttribute
                public Object value() {
                    return Collections.unmodifiableSet(FileApiFlips.flipPosixPermissionSet((Set) fileAttribute.value()));
                }
            };
        }
        return FileAttribute.Wrapper.convert(fileAttribute);
    }

    private static boolean isPosixPermissionAttributes(Object obj) {
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (set.isEmpty()) {
            return false;
        }
        Object next = set.iterator().next();
        return (next instanceof PosixFilePermission) || (next instanceof java.nio.file.attribute.PosixFilePermission);
    }
}
