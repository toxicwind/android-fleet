package j$.nio.file;

/* JADX INFO: loaded from: classes3.dex */
public interface PathMatcher {

    public final /* synthetic */ class VivifiedWrapper implements PathMatcher {
        public final /* synthetic */ java.nio.file.PathMatcher wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.PathMatcher pathMatcher) {
            this.wrappedValue = pathMatcher;
        }

        public static /* synthetic */ PathMatcher convert(java.nio.file.PathMatcher pathMatcher) {
            if (pathMatcher == null) {
                return null;
            }
            return pathMatcher instanceof Wrapper ? PathMatcher.this : new VivifiedWrapper(pathMatcher);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.PathMatcher pathMatcher = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return pathMatcher.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.PathMatcher
        public /* synthetic */ boolean matches(Path path) {
            return this.wrappedValue.matches(Path.Wrapper.convert(path));
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.PathMatcher {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.PathMatcher convert(PathMatcher pathMatcher) {
            if (pathMatcher == null) {
                return null;
            }
            return pathMatcher instanceof VivifiedWrapper ? ((VivifiedWrapper) pathMatcher).wrappedValue : pathMatcher.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            PathMatcher pathMatcher = PathMatcher.this;
            if (obj instanceof Wrapper) {
                obj = PathMatcher.this;
            }
            return pathMatcher.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return PathMatcher.this.hashCode();
        }

        @Override // java.nio.file.PathMatcher
        public /* synthetic */ boolean matches(java.nio.file.Path path) {
            return PathMatcher.this.matches(Path.VivifiedWrapper.convert(path));
        }
    }

    boolean matches(Path path);
}
