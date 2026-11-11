package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WatchEventKindConversions {
    public static java.nio.file.WatchEvent.Kind convert(WatchEvent.Kind kind) {
        if (kind == null) {
            return null;
        }
        if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
            return java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
        }
        if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
            return java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
        }
        if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
            return java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
        }
        if (kind == StandardWatchEventKinds.OVERFLOW) {
            return java.nio.file.StandardWatchEventKinds.OVERFLOW;
        }
        return WatchEvent.Kind.Wrapper.convert(kind);
    }

    public static WatchEvent.Kind convert(java.nio.file.WatchEvent.Kind kind) {
        if (kind == null) {
            return null;
        }
        if (kind == java.nio.file.StandardWatchEventKinds.ENTRY_CREATE) {
            return StandardWatchEventKinds.ENTRY_CREATE;
        }
        if (kind == java.nio.file.StandardWatchEventKinds.ENTRY_DELETE) {
            return StandardWatchEventKinds.ENTRY_DELETE;
        }
        if (kind == java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY) {
            return StandardWatchEventKinds.ENTRY_MODIFY;
        }
        if (kind == java.nio.file.StandardWatchEventKinds.OVERFLOW) {
            return StandardWatchEventKinds.OVERFLOW;
        }
        return WatchEvent.Kind.VivifiedWrapper.convert(kind);
    }
}
