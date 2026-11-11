package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public interface Watchable {

    public final /* synthetic */ class VivifiedWrapper implements Watchable {
        public final /* synthetic */ java.nio.file.Watchable wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.Watchable watchable) {
            this.wrappedValue = watchable;
        }

        public static /* synthetic */ Watchable convert(java.nio.file.Watchable watchable) {
            if (watchable == null) {
                return null;
            }
            if (watchable instanceof Wrapper) {
                return Watchable.this;
            }
            return watchable instanceof java.nio.file.Path ? Path.VivifiedWrapper.convert((java.nio.file.Path) watchable) : new VivifiedWrapper(watchable);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.Watchable watchable = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return watchable.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.Watchable
        public /* synthetic */ WatchKey register(WatchService watchService, WatchEvent.Kind[] kindArr) {
            return WatchKey.VivifiedWrapper.convert(this.wrappedValue.register(WatchService.Wrapper.convert(watchService), Path.AnonymousClass5.m(kindArr)));
        }

        @Override // j$.nio.file.Watchable
        public /* synthetic */ WatchKey register(WatchService watchService, WatchEvent.Kind[] kindArr, WatchEvent.Modifier[] modifierArr) {
            return WatchKey.VivifiedWrapper.convert(this.wrappedValue.register(WatchService.Wrapper.convert(watchService), Path.AnonymousClass5.m(kindArr), Path.AnonymousClass6.m(modifierArr)));
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.Watchable {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.Watchable convert(Watchable watchable) {
            if (watchable == null) {
                return null;
            }
            if (watchable instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) watchable).wrappedValue;
            }
            return watchable instanceof Path ? Path.Wrapper.convert((Path) watchable) : watchable.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            Watchable watchable = Watchable.this;
            if (obj instanceof Wrapper) {
                obj = Watchable.this;
            }
            return watchable.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return Watchable.this.hashCode();
        }

        @Override // java.nio.file.Watchable
        public /* synthetic */ java.nio.file.WatchKey register(java.nio.file.WatchService watchService, java.nio.file.WatchEvent.Kind[] kindArr) {
            return WatchKey.Wrapper.convert(Watchable.this.register(WatchService.VivifiedWrapper.convert(watchService), Path.AnonymousClass2.m(kindArr)));
        }

        @Override // java.nio.file.Watchable
        public /* synthetic */ java.nio.file.WatchKey register(java.nio.file.WatchService watchService, java.nio.file.WatchEvent.Kind[] kindArr, java.nio.file.WatchEvent.Modifier[] modifierArr) {
            return WatchKey.Wrapper.convert(Watchable.this.register(WatchService.VivifiedWrapper.convert(watchService), Path.AnonymousClass2.m(kindArr), Path.AnonymousClass3.m(modifierArr)));
        }
    }

    WatchKey register(WatchService watchService, WatchEvent.Kind... kindArr);

    WatchKey register(WatchService watchService, WatchEvent.Kind[] kindArr, WatchEvent.Modifier... modifierArr);
}
