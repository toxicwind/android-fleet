package j$.nio.file;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface WatchKey {

    public final /* synthetic */ class VivifiedWrapper implements WatchKey {
        public final /* synthetic */ java.nio.file.WatchKey wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.WatchKey watchKey) {
            this.wrappedValue = watchKey;
        }

        public static /* synthetic */ WatchKey convert(java.nio.file.WatchKey watchKey) {
            if (watchKey == null) {
                return null;
            }
            return watchKey instanceof Wrapper ? WatchKey.this : new VivifiedWrapper(watchKey);
        }

        @Override // j$.nio.file.WatchKey
        public /* synthetic */ void cancel() {
            this.wrappedValue.cancel();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.WatchKey watchKey = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return watchKey.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.WatchKey
        public /* synthetic */ boolean isValid() {
            return this.wrappedValue.isValid();
        }

        @Override // j$.nio.file.WatchKey
        public /* synthetic */ List pollEvents() {
            return FileApiFlips.flipWatchEventList(this.wrappedValue.pollEvents());
        }

        @Override // j$.nio.file.WatchKey
        public /* synthetic */ boolean reset() {
            return this.wrappedValue.reset();
        }

        @Override // j$.nio.file.WatchKey
        public /* synthetic */ Watchable watchable() {
            return Watchable.VivifiedWrapper.convert(this.wrappedValue.watchable());
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.WatchKey {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.WatchKey convert(WatchKey watchKey) {
            if (watchKey == null) {
                return null;
            }
            return watchKey instanceof VivifiedWrapper ? ((VivifiedWrapper) watchKey).wrappedValue : watchKey.new Wrapper();
        }

        @Override // java.nio.file.WatchKey
        public /* synthetic */ void cancel() {
            WatchKey.this.cancel();
        }

        public /* synthetic */ boolean equals(Object obj) {
            WatchKey watchKey = WatchKey.this;
            if (obj instanceof Wrapper) {
                obj = WatchKey.this;
            }
            return watchKey.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return WatchKey.this.hashCode();
        }

        @Override // java.nio.file.WatchKey
        public /* synthetic */ boolean isValid() {
            return WatchKey.this.isValid();
        }

        @Override // java.nio.file.WatchKey
        public /* synthetic */ List pollEvents() {
            return FileApiFlips.flipWatchEventList(WatchKey.this.pollEvents());
        }

        @Override // java.nio.file.WatchKey
        public /* synthetic */ boolean reset() {
            return WatchKey.this.reset();
        }

        @Override // java.nio.file.WatchKey
        public /* synthetic */ java.nio.file.Watchable watchable() {
            return Watchable.Wrapper.convert(WatchKey.this.watchable());
        }
    }

    void cancel();

    boolean isValid();

    List pollEvents();

    boolean reset();

    Watchable watchable();
}
