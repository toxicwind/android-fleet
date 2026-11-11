package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public enum StandardCopyOption implements CopyOption {
    REPLACE_EXISTING,
    COPY_ATTRIBUTES,
    ATOMIC_MOVE;

    public abstract /* synthetic */ class EnumConversion {
        public static /* synthetic */ java.nio.file.StandardCopyOption convert(StandardCopyOption standardCopyOption) {
            if (standardCopyOption == null) {
                return null;
            }
            if (standardCopyOption == StandardCopyOption.REPLACE_EXISTING) {
                return java.nio.file.StandardCopyOption.REPLACE_EXISTING;
            }
            return standardCopyOption == StandardCopyOption.COPY_ATTRIBUTES ? java.nio.file.StandardCopyOption.COPY_ATTRIBUTES : java.nio.file.StandardCopyOption.ATOMIC_MOVE;
        }
    }
}
