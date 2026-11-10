package com.google.firebase.components;

import androidx.annotation.VisibleForTesting;
import com.google.firebase.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public class Lazy<T> implements Provider<T> {
    public static final Object UNINITIALIZED = new Object();
    public volatile Object instance;
    public volatile Provider<T> provider;

    public Lazy(T t) {
        this.instance = UNINITIALIZED;
        this.instance = t;
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        T t = (T) this.instance;
        if (t == UNINITIALIZED) {
            synchronized (this) {
                t = (T) this.instance;
                if (t == UNINITIALIZED) {
                    t = this.provider.get();
                    this.instance = t;
                    this.provider = null;
                }
            }
        }
        return t;
    }

    @VisibleForTesting
    public boolean isInitialized() {
        return this.instance != UNINITIALIZED;
    }

    public Lazy(Provider<T> provider) {
        this.instance = UNINITIALIZED;
        this.provider = provider;
    }
}
