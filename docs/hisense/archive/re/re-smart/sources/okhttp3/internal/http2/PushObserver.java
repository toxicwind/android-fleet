package okhttp3.internal.http2;

import d.i;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() { // from class: okhttp3.internal.http2.PushObserver.1
        @Override // okhttp3.internal.http2.PushObserver
        public boolean onData(int i, i iVar, int i2, boolean z) {
            iVar.skip(i2);
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean onHeaders(int i, List<Header> list, boolean z) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public boolean onRequest(int i, List<Header> list) {
            return true;
        }

        @Override // okhttp3.internal.http2.PushObserver
        public void onReset(int i, ErrorCode errorCode) {
        }
    };

    boolean onData(int i, i iVar, int i2, boolean z);

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
