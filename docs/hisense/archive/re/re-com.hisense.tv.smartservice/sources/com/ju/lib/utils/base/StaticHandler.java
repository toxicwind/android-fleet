package com.ju.lib.utils.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StaticHandler<T> extends Handler {
    public WeakReference<T> mWeakReference;

    public StaticHandler(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.mWeakReference.get() == null) {
            return;
        }
        handleMessage(this.mWeakReference.get(), message);
    }

    public abstract void handleMessage(T t, Message message);

    public StaticHandler(T t, Looper looper) {
        super(looper);
        this.mWeakReference = new WeakReference<>(t);
    }
}
