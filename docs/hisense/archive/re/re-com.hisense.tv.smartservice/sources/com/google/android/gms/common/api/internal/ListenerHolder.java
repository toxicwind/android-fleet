package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public final class ListenerHolder<L> {
    public final zaa zajm;
    public volatile L zajn;
    public final ListenerKey<L> zajo;

    @KeepForSdk
    public static final class ListenerKey<L> {
        public final L zajn;
        public final String zajp;

        @KeepForSdk
        public ListenerKey(L l, String str) {
            this.zajn = l;
            this.zajp = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zajn == listenerKey.zajn && this.zajp.equals(listenerKey.zajp);
        }

        public final int hashCode() {
            return this.zajp.hashCode() + (System.identityHashCode(this.zajn) * 31);
        }
    }

    @KeepForSdk
    public interface Notifier<L> {
        @KeepForSdk
        void notifyListener(L l);

        @KeepForSdk
        void onNotifyListenerFailed();
    }

    private final class zaa extends com.google.android.gms.internal.base.zar {
        public zaa(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Preconditions.checkArgument(message.what == 1);
            ListenerHolder.this.notifyListenerInternal((Notifier) message.obj);
        }
    }

    @KeepForSdk
    public ListenerHolder(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.zajm = new zaa(looper);
        Preconditions.checkNotNull(l, "Listener must not be null");
        this.zajn = l;
        Preconditions.checkNotEmpty(str);
        this.zajo = new ListenerKey<>(l, str);
    }

    @KeepForSdk
    public final void clear() {
        this.zajn = null;
    }

    @NonNull
    @KeepForSdk
    public final ListenerKey<L> getListenerKey() {
        return this.zajo;
    }

    @KeepForSdk
    public final boolean hasListener() {
        return this.zajn != null;
    }

    @KeepForSdk
    public final void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zajm.sendMessage(this.zajm.obtainMessage(1, notifier));
    }

    @KeepForSdk
    public final void notifyListenerInternal(Notifier<? super L> notifier) {
        L l = this.zajn;
        if (l == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(l);
        } catch (RuntimeException e2) {
            notifier.onNotifyListenerFailed();
            throw e2;
        }
    }
}
