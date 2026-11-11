package go;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Universe {
    public static native void _init();

    public static void touch() {
    }

    private Universe() {
    }

    private static final class proxyerror extends Exception implements Seq.Proxy, error {
        private final int refnum;

        @Override // go.error
        public native String error();

        @Override // go.Seq.GoObject
        public final int incRefnum() {
            Seq.incGoRef(this.refnum, this);
            return this.refnum;
        }

        proxyerror(int i) {
            this.refnum = i;
            Seq.trackGoRef(i, this);
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return error();
        }
    }
}
