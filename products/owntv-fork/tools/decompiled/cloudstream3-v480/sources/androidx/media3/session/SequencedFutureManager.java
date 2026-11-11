package androidx.media3.session;

import android.os.Handler;
import androidx.collection.ArrayMap;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
final class SequencedFutureManager {
    private static final String TAG = "SequencedFutureManager";
    private boolean isReleased;
    private int nextSequenceNumber;
    private Runnable pendingLazyReleaseCallback;
    private Handler releaseCallbackHandler;
    private final Object lock = new Object();
    private final ArrayMap<Integer, SequencedFuture<?>> seqToFutureMap = new ArrayMap<>();

    public int obtainNextSequenceNumber() {
        int i;
        synchronized (this.lock) {
            i = this.nextSequenceNumber;
            this.nextSequenceNumber = i + 1;
        }
        return i;
    }

    public <T> SequencedFuture<T> createSequencedFuture(T t) {
        SequencedFuture<T> sequencedFutureCreate;
        synchronized (this.lock) {
            int iObtainNextSequenceNumber = obtainNextSequenceNumber();
            sequencedFutureCreate = SequencedFuture.create(iObtainNextSequenceNumber, t);
            if (this.isReleased) {
                sequencedFutureCreate.setWithTheValueOfResultWhenClosed();
            } else {
                this.seqToFutureMap.put(Integer.valueOf(iObtainNextSequenceNumber), sequencedFutureCreate);
            }
        }
        return sequencedFutureCreate;
    }

    public <T> void setFutureResult(int i, T t) {
        synchronized (this.lock) {
            SequencedFuture<?> sequencedFutureRemove = this.seqToFutureMap.remove(Integer.valueOf(i));
            if (sequencedFutureRemove != null) {
                if (sequencedFutureRemove.getResultWhenClosed().getClass() == t.getClass()) {
                    sequencedFutureRemove.set(t);
                } else {
                    Log.w(TAG, "Type mismatch, expected " + sequencedFutureRemove.getResultWhenClosed().getClass() + ", but was " + t.getClass());
                }
            }
            if (this.pendingLazyReleaseCallback != null && this.seqToFutureMap.isEmpty()) {
                release();
            }
        }
    }

    public void release() {
        ArrayList arrayList;
        synchronized (this.lock) {
            this.isReleased = true;
            arrayList = new ArrayList(this.seqToFutureMap.values());
            this.seqToFutureMap.clear();
            if (this.pendingLazyReleaseCallback != null) {
                ((Handler) Preconditions.checkNotNull(this.releaseCallbackHandler)).post(this.pendingLazyReleaseCallback);
                this.pendingLazyReleaseCallback = null;
                this.releaseCallbackHandler = null;
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((SequencedFuture) it.next()).setWithTheValueOfResultWhenClosed();
        }
    }

    public void lazyRelease(long j, Runnable runnable) {
        synchronized (this.lock) {
            Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
            this.releaseCallbackHandler = handlerCreateHandlerForCurrentLooper;
            this.pendingLazyReleaseCallback = runnable;
            if (this.seqToFutureMap.isEmpty()) {
                release();
            } else {
                handlerCreateHandlerForCurrentLooper.postDelayed(new Runnable() { // from class: androidx.media3.session.SequencedFutureManager$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.release();
                    }
                }, j);
            }
        }
    }

    public static final class SequencedFuture<T> extends AbstractFuture<T> {
        private final T resultWhenClosed;
        private final int sequenceNumber;

        private SequencedFuture(int i, T t) {
            this.sequenceNumber = i;
            this.resultWhenClosed = t;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean set(T t) {
            return super.set(t);
        }

        public void setWithTheValueOfResultWhenClosed() {
            set(this.resultWhenClosed);
        }

        public int getSequenceNumber() {
            return this.sequenceNumber;
        }

        public T getResultWhenClosed() {
            return this.resultWhenClosed;
        }

        public static <T> SequencedFuture<T> create(int i, T t) {
            return new SequencedFuture<>(i, t);
        }
    }
}
