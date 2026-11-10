package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class RemovalListeners {
    public static <K, V> RemovalListener<K, V> asynchronous(final RemovalListener<K, V> removalListener, final Executor executor) {
        if (removalListener == null) {
            throw new NullPointerException();
        }
        if (executor != null) {
            return new RemovalListener<K, V>() { // from class: com.google.common.cache.RemovalListeners.1
                @Override // com.google.common.cache.RemovalListener
                public void onRemoval(final RemovalNotification<K, V> removalNotification) {
                    executor.execute(new Runnable() { // from class: com.google.common.cache.RemovalListeners.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            removalListener.onRemoval(removalNotification);
                        }
                    });
                }
            };
        }
        throw new NullPointerException();
    }
}
