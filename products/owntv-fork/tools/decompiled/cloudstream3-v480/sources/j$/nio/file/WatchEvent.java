package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public interface WatchEvent {

    public interface Kind {

        public final /* synthetic */ class VivifiedWrapper implements Kind {
            public final /* synthetic */ java.nio.file.WatchEvent.Kind wrappedValue;

            private /* synthetic */ VivifiedWrapper(java.nio.file.WatchEvent.Kind kind) {
                this.wrappedValue = kind;
            }

            public static /* synthetic */ Kind convert(java.nio.file.WatchEvent.Kind kind) {
                if (kind == null) {
                    return null;
                }
                return kind instanceof Wrapper ? Kind.this : new VivifiedWrapper(kind);
            }

            public /* synthetic */ boolean equals(Object obj) {
                java.nio.file.WatchEvent.Kind kind = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return kind.equals(obj);
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.nio.file.WatchEvent.Kind
            public /* synthetic */ String name() {
                return this.wrappedValue.name();
            }

            @Override // j$.nio.file.WatchEvent.Kind
            public /* synthetic */ Class type() {
                return this.wrappedValue.type();
            }
        }

        public final /* synthetic */ class Wrapper implements java.nio.file.WatchEvent.Kind {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ java.nio.file.WatchEvent.Kind convert(Kind kind) {
                if (kind == null) {
                    return null;
                }
                return kind instanceof VivifiedWrapper ? ((VivifiedWrapper) kind).wrappedValue : kind.new Wrapper();
            }

            public /* synthetic */ boolean equals(Object obj) {
                Kind kind = Kind.this;
                if (obj instanceof Wrapper) {
                    obj = Kind.this;
                }
                return kind.equals(obj);
            }

            public /* synthetic */ int hashCode() {
                return Kind.this.hashCode();
            }

            @Override // java.nio.file.WatchEvent.Kind
            public /* synthetic */ String name() {
                return Kind.this.name();
            }

            @Override // java.nio.file.WatchEvent.Kind
            public /* synthetic */ Class type() {
                return Kind.this.type();
            }
        }

        String name();

        Class type();
    }

    public interface Modifier {

        public final /* synthetic */ class VivifiedWrapper implements Modifier {
            public final /* synthetic */ java.nio.file.WatchEvent.Modifier wrappedValue;

            private /* synthetic */ VivifiedWrapper(java.nio.file.WatchEvent.Modifier modifier) {
                this.wrappedValue = modifier;
            }

            public static /* synthetic */ Modifier convert(java.nio.file.WatchEvent.Modifier modifier) {
                if (modifier == null) {
                    return null;
                }
                return modifier instanceof Wrapper ? Modifier.this : new VivifiedWrapper(modifier);
            }

            public /* synthetic */ boolean equals(Object obj) {
                java.nio.file.WatchEvent.Modifier modifier = this.wrappedValue;
                if (obj instanceof VivifiedWrapper) {
                    obj = ((VivifiedWrapper) obj).wrappedValue;
                }
                return modifier.equals(obj);
            }

            public /* synthetic */ int hashCode() {
                return this.wrappedValue.hashCode();
            }

            @Override // j$.nio.file.WatchEvent.Modifier
            public /* synthetic */ String name() {
                return this.wrappedValue.name();
            }
        }

        public final /* synthetic */ class Wrapper implements java.nio.file.WatchEvent.Modifier {
            private /* synthetic */ Wrapper() {
            }

            public static /* synthetic */ java.nio.file.WatchEvent.Modifier convert(Modifier modifier) {
                if (modifier == null) {
                    return null;
                }
                return modifier instanceof VivifiedWrapper ? ((VivifiedWrapper) modifier).wrappedValue : modifier.new Wrapper();
            }

            public /* synthetic */ boolean equals(Object obj) {
                Modifier modifier = Modifier.this;
                if (obj instanceof Wrapper) {
                    obj = Modifier.this;
                }
                return modifier.equals(obj);
            }

            public /* synthetic */ int hashCode() {
                return Modifier.this.hashCode();
            }

            @Override // java.nio.file.WatchEvent.Modifier
            public /* synthetic */ String name() {
                return Modifier.this.name();
            }
        }

        String name();
    }

    public final /* synthetic */ class VivifiedWrapper implements WatchEvent {
        public final /* synthetic */ java.nio.file.WatchEvent wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.WatchEvent watchEvent) {
            this.wrappedValue = watchEvent;
        }

        public static /* synthetic */ WatchEvent convert(java.nio.file.WatchEvent watchEvent) {
            if (watchEvent == null) {
                return null;
            }
            return watchEvent instanceof Wrapper ? WatchEvent.this : new VivifiedWrapper(watchEvent);
        }

        @Override // j$.nio.file.WatchEvent
        public /* synthetic */ Object context() {
            return PathApiFlips.convertPath(this.wrappedValue.context());
        }

        @Override // j$.nio.file.WatchEvent
        public /* synthetic */ int count() {
            return this.wrappedValue.count();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.WatchEvent watchEvent = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return watchEvent.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.WatchEvent
        public /* synthetic */ Kind kind() {
            return WatchEventKindConversions.convert(this.wrappedValue.kind());
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.WatchEvent {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.WatchEvent convert(WatchEvent watchEvent) {
            if (watchEvent == null) {
                return null;
            }
            return watchEvent instanceof VivifiedWrapper ? ((VivifiedWrapper) watchEvent).wrappedValue : watchEvent.new Wrapper();
        }

        @Override // java.nio.file.WatchEvent
        public /* synthetic */ Object context() {
            return PathApiFlips.convertPath(WatchEvent.this.context());
        }

        @Override // java.nio.file.WatchEvent
        public /* synthetic */ int count() {
            return WatchEvent.this.count();
        }

        public /* synthetic */ boolean equals(Object obj) {
            WatchEvent watchEvent = WatchEvent.this;
            if (obj instanceof Wrapper) {
                obj = WatchEvent.this;
            }
            return watchEvent.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return WatchEvent.this.hashCode();
        }

        @Override // java.nio.file.WatchEvent
        public /* synthetic */ java.nio.file.WatchEvent.Kind kind() {
            return WatchEventKindConversions.convert(WatchEvent.this.kind());
        }
    }

    Object context();

    int count();

    Kind kind();
}
