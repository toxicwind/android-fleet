package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public enum AccessMode {
    READ,
    WRITE,
    EXECUTE;

    public abstract /* synthetic */ class EnumConversion {
        public static /* synthetic */ AccessMode convert(java.nio.file.AccessMode accessMode) {
            if (accessMode == null) {
                return null;
            }
            if (accessMode == java.nio.file.AccessMode.READ) {
                return AccessMode.READ;
            }
            return accessMode == java.nio.file.AccessMode.WRITE ? AccessMode.WRITE : AccessMode.EXECUTE;
        }

        public static /* synthetic */ java.nio.file.AccessMode convert(AccessMode accessMode) {
            if (accessMode == null) {
                return null;
            }
            if (accessMode == AccessMode.READ) {
                return java.nio.file.AccessMode.READ;
            }
            return accessMode == AccessMode.WRITE ? java.nio.file.AccessMode.WRITE : java.nio.file.AccessMode.EXECUTE;
        }
    }
}
