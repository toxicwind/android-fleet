package j$.nio.file.attribute;

/* JADX INFO: loaded from: classes3.dex */
public enum PosixFilePermission {
    OWNER_READ,
    OWNER_WRITE,
    OWNER_EXECUTE,
    GROUP_READ,
    GROUP_WRITE,
    GROUP_EXECUTE,
    OTHERS_READ,
    OTHERS_WRITE,
    OTHERS_EXECUTE;

    public abstract /* synthetic */ class EnumConversion {
        public static /* synthetic */ PosixFilePermission convert(java.nio.file.attribute.PosixFilePermission posixFilePermission) {
            if (posixFilePermission == null) {
                return null;
            }
            if (posixFilePermission == java.nio.file.attribute.PosixFilePermission.OWNER_READ) {
                return PosixFilePermission.OWNER_READ;
            }
            if (posixFilePermission == java.nio.file.attribute.PosixFilePermission.OWNER_WRITE) {
                return PosixFilePermission.OWNER_WRITE;
            }
            if (posixFilePermission == java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE) {
                return PosixFilePermission.OWNER_EXECUTE;
            }
            if (posixFilePermission == java.nio.file.attribute.PosixFilePermission.GROUP_READ) {
                return PosixFilePermission.GROUP_READ;
            }
            if (posixFilePermission == java.nio.file.attribute.PosixFilePermission.GROUP_WRITE) {
                return PosixFilePermission.GROUP_WRITE;
            }
            if (posixFilePermission == java.nio.file.attribute.PosixFilePermission.GROUP_EXECUTE) {
                return PosixFilePermission.GROUP_EXECUTE;
            }
            if (posixFilePermission == java.nio.file.attribute.PosixFilePermission.OTHERS_READ) {
                return PosixFilePermission.OTHERS_READ;
            }
            return posixFilePermission == java.nio.file.attribute.PosixFilePermission.OTHERS_WRITE ? PosixFilePermission.OTHERS_WRITE : PosixFilePermission.OTHERS_EXECUTE;
        }

        public static /* synthetic */ java.nio.file.attribute.PosixFilePermission convert(PosixFilePermission posixFilePermission) {
            if (posixFilePermission == null) {
                return null;
            }
            if (posixFilePermission == PosixFilePermission.OWNER_READ) {
                return java.nio.file.attribute.PosixFilePermission.OWNER_READ;
            }
            if (posixFilePermission == PosixFilePermission.OWNER_WRITE) {
                return java.nio.file.attribute.PosixFilePermission.OWNER_WRITE;
            }
            if (posixFilePermission == PosixFilePermission.OWNER_EXECUTE) {
                return java.nio.file.attribute.PosixFilePermission.OWNER_EXECUTE;
            }
            if (posixFilePermission == PosixFilePermission.GROUP_READ) {
                return java.nio.file.attribute.PosixFilePermission.GROUP_READ;
            }
            if (posixFilePermission == PosixFilePermission.GROUP_WRITE) {
                return java.nio.file.attribute.PosixFilePermission.GROUP_WRITE;
            }
            if (posixFilePermission == PosixFilePermission.GROUP_EXECUTE) {
                return java.nio.file.attribute.PosixFilePermission.GROUP_EXECUTE;
            }
            if (posixFilePermission == PosixFilePermission.OTHERS_READ) {
                return java.nio.file.attribute.PosixFilePermission.OTHERS_READ;
            }
            return posixFilePermission == PosixFilePermission.OTHERS_WRITE ? java.nio.file.attribute.PosixFilePermission.OTHERS_WRITE : java.nio.file.attribute.PosixFilePermission.OTHERS_EXECUTE;
        }
    }
}
