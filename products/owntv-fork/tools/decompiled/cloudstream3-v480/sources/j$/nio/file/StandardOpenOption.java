package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public enum StandardOpenOption implements OpenOption {
    READ,
    WRITE,
    APPEND,
    TRUNCATE_EXISTING,
    CREATE,
    CREATE_NEW,
    DELETE_ON_CLOSE,
    SPARSE,
    SYNC,
    DSYNC;

    public abstract /* synthetic */ class EnumConversion {
        public static /* synthetic */ java.nio.file.StandardOpenOption convert(StandardOpenOption standardOpenOption) {
            if (standardOpenOption == null) {
                return null;
            }
            if (standardOpenOption == StandardOpenOption.READ) {
                return java.nio.file.StandardOpenOption.READ;
            }
            if (standardOpenOption == StandardOpenOption.WRITE) {
                return java.nio.file.StandardOpenOption.WRITE;
            }
            if (standardOpenOption == StandardOpenOption.APPEND) {
                return java.nio.file.StandardOpenOption.APPEND;
            }
            if (standardOpenOption == StandardOpenOption.TRUNCATE_EXISTING) {
                return java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
            }
            if (standardOpenOption == StandardOpenOption.CREATE) {
                return java.nio.file.StandardOpenOption.CREATE;
            }
            if (standardOpenOption == StandardOpenOption.CREATE_NEW) {
                return java.nio.file.StandardOpenOption.CREATE_NEW;
            }
            if (standardOpenOption == StandardOpenOption.DELETE_ON_CLOSE) {
                return java.nio.file.StandardOpenOption.DELETE_ON_CLOSE;
            }
            if (standardOpenOption == StandardOpenOption.SPARSE) {
                return java.nio.file.StandardOpenOption.SPARSE;
            }
            return standardOpenOption == StandardOpenOption.SYNC ? java.nio.file.StandardOpenOption.SYNC : java.nio.file.StandardOpenOption.DSYNC;
        }
    }
}
