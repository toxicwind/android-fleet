package com.google.firebase.components;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.firebase.components.OptionalProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* JADX INFO: loaded from: classes.dex */
public class OptionalProvider<T> implements Provider<T>, Deferred<T> {
    public volatile Provider<T> delegate;

    @GuardedBy("this")
    public Deferred.DeferredHandler<T> handler;
    public static final Deferred.DeferredHandler<Object> NOOP_HANDLER = new Deferred.DeferredHandler() { // from class: b.e.b.b.m
        @Override // com.google.firebase.inject.Deferred.DeferredHandler
        public final void handle(Provider provider) {
            OptionalProvider.a(provider);
        }
    };
    public static final Provider<Object> EMPTY_PROVIDER = new Provider() { // from class: b.e.b.b.l
        @Override // com.google.firebase.inject.Provider
        public final Object get() {
            OptionalProvider.a();
            return null;
        }
    };

    public OptionalProvider(Deferred.DeferredHandler<T> deferredHandler, Provider<T> provider) {
        this.handler = deferredHandler;
        this.delegate = provider;
    }

    public static /* synthetic */ Object a() {
        return null;
    }

    public static /* synthetic */ void a(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2, Provider provider) {
        deferredHandler.handle(provider);
        deferredHandler2.handle(provider);
    }

    public static /* synthetic */ void a(Provider provider) {
    }

    public static <T> OptionalProvider<T> empty() {
        return new OptionalProvider<>(NOOP_HANDLER, EMPTY_PROVIDER);
    }

    public static <T> OptionalProvider<T> of(Provider<T> provider) {
        return new OptionalProvider<>(null, provider);
    }

    @Override // com.google.firebase.inject.Provider
    public T get() {
        return this.delegate.get();
    }

    public void set(Provider<T> provider) {
        Deferred.DeferredHandler<T> deferredHandler;
        if (this.delegate != EMPTY_PROVIDER) {
            throw new IllegalStateException("provide() can be called only once.");
        }
        synchronized (this) {
            deferredHandler = this.handler;
            this.handler = null;
            this.delegate = provider;
        }
        deferredHandler.handle(provider);
    }

    @Override // com.google.firebase.inject.Deferred
    public void whenAvailable(@NonNull final Deferred.DeferredHandler<T> deferredHandler) {
        Provider<T> provider;
        Provider<T> provider2 = this.delegate;
        if (provider2 != EMPTY_PROVIDER) {
            deferredHandler.handle(provider2);
            return;
        }
        Provider<T> provider3 = null;
        synchronized (this) {
            provider = this.delegate;
            if (provider != EMPTY_PROVIDER) {
                provider3 = provider;
            } else {
                final Deferred.DeferredHandler<T> deferredHandler2 = this.handler;
                this.handler = new Deferred.DeferredHandler() { // from class: b.e.b.b.k
                    @Override // com.google.firebase.inject.Deferred.DeferredHandler
                    public final void handle(Provider provider4) {
                        OptionalProvider.a(deferredHandler2, deferredHandler, provider4);
                    }
                };
            }
        }
        if (provider3 != null) {
            deferredHandler.handle(provider);
        }
    }
}
