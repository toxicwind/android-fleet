package com.google.common.io;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class Closer implements Closeable {
    public static final Suppressor SUPPRESSOR;
    public final Deque<Closeable> stack = new ArrayDeque(4);

    @VisibleForTesting
    public final Suppressor suppressor;
    public Throwable thrown;

    @VisibleForTesting
    static final class LoggingSuppressor implements Suppressor {
        public static final LoggingSuppressor INSTANCE = new LoggingSuppressor();

        @Override // com.google.common.io.Closer.Suppressor
        public void suppress(Closeable closeable, Throwable th, Throwable th2) {
            Logger logger = Closeables.logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(closeable);
            logger.log(level, a.a(strValueOf.length() + 42, "Suppressing exception thrown when closing ", strValueOf), th2);
        }
    }

    @VisibleForTesting
    static final class SuppressingSuppressor implements Suppressor {
        public static final SuppressingSuppressor INSTANCE = new SuppressingSuppressor();
        public static final Method addSuppressed = addSuppressedMethodOrNull();

        public static Method addSuppressedMethodOrNull() {
            try {
                return Throwable.class.getMethod("addSuppressed", Throwable.class);
            } catch (Throwable unused) {
                return null;
            }
        }

        public static boolean isAvailable() {
            return addSuppressed != null;
        }

        @Override // com.google.common.io.Closer.Suppressor
        public void suppress(Closeable closeable, Throwable th, Throwable th2) {
            if (th == th2) {
                return;
            }
            try {
                addSuppressed.invoke(th, th2);
            } catch (Throwable unused) {
                LoggingSuppressor.INSTANCE.suppress(closeable, th, th2);
            }
        }
    }

    @VisibleForTesting
    interface Suppressor {
        void suppress(Closeable closeable, Throwable th, Throwable th2);
    }

    static {
        SUPPRESSOR = SuppressingSuppressor.isAvailable() ? SuppressingSuppressor.INSTANCE : LoggingSuppressor.INSTANCE;
    }

    @VisibleForTesting
    public Closer(Suppressor suppressor) {
        if (suppressor == null) {
            throw new NullPointerException();
        }
        this.suppressor = suppressor;
    }

    public static Closer create() {
        return new Closer(SUPPRESSOR);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws X {
        Throwable th = this.thrown;
        while (!this.stack.isEmpty()) {
            Closeable closeableRemoveFirst = this.stack.removeFirst();
            try {
                closeableRemoveFirst.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    this.suppressor.suppress(closeableRemoveFirst, th, th2);
                }
            }
        }
        if (this.thrown != null || th == null) {
            return;
        }
        Throwables.propagateIfPossible(th, IOException.class);
        throw new AssertionError(th);
    }

    @CanIgnoreReturnValue
    public <C extends Closeable> C register(C c2) {
        if (c2 != null) {
            this.stack.addFirst(c2);
        }
        return c2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public RuntimeException rethrow(Throwable th) throws X {
        if (th == null) {
            throw new NullPointerException();
        }
        this.thrown = th;
        Throwables.propagateIfPossible(th, IOException.class);
        throw new RuntimeException(th);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public <X extends Exception> RuntimeException rethrow(Throwable th, Class<X> cls) throws X {
        if (th != null) {
            this.thrown = th;
            Throwables.propagateIfPossible(th, IOException.class);
            Throwables.propagateIfPossible(th, cls);
            throw new RuntimeException(th);
        }
        throw new NullPointerException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public <X1 extends Exception, X2 extends Exception> RuntimeException rethrow(Throwable th, Class<X1> cls, Class<X2> cls2) throws X {
        if (th != null) {
            this.thrown = th;
            Throwables.propagateIfPossible(th, IOException.class);
            Throwables.propagateIfPossible(th, cls, cls2);
            throw new RuntimeException(th);
        }
        throw new NullPointerException();
    }
}
