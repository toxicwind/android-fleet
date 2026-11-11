package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public abstract class StandardWatchEventKinds {
    public static final WatchEvent.Kind OVERFLOW = new StdWatchEventKind("OVERFLOW", Object.class);
    public static final WatchEvent.Kind ENTRY_CREATE = new StdWatchEventKind("ENTRY_CREATE", Path.class);
    public static final WatchEvent.Kind ENTRY_DELETE = new StdWatchEventKind("ENTRY_DELETE", Path.class);
    public static final WatchEvent.Kind ENTRY_MODIFY = new StdWatchEventKind("ENTRY_MODIFY", Path.class);

    private static class StdWatchEventKind implements WatchEvent.Kind {
        private final String name;
        private final Class type;

        StdWatchEventKind(String str, Class cls) {
            this.name = str;
            this.type = cls;
        }

        @Override // j$.nio.file.WatchEvent.Kind
        public String name() {
            return this.name;
        }

        @Override // j$.nio.file.WatchEvent.Kind
        public Class type() {
            return this.type;
        }

        public String toString() {
            return this.name;
        }
    }
}
