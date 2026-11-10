package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.net.InetAddresses;
import com.google.common.reflect.ClassPath;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public class FinalizableReferenceQueue implements Closeable {
    public static final String FINALIZER_CLASS_NAME = "com.google.common.base.internal.Finalizer";
    public static final Logger logger = Logger.getLogger(FinalizableReferenceQueue.class.getName());
    public static final Method startFinalizer = getStartFinalizer(loadFinalizer(new SystemLoader(), new DecoupledLoader(), new DirectLoader()));
    public final boolean threadStarted;
    public final ReferenceQueue<Object> queue = new ReferenceQueue<>();
    public final PhantomReference<Object> frqRef = new PhantomReference<>(this, this.queue);

    static class DecoupledLoader implements FinalizerLoader {
        public static final String LOADING_ERROR = "Could not load Finalizer in its own class loader. Loading Finalizer in the current class loader instead. As a result, you will not be able to garbage collect this class loader. To support reclaiming this class loader, either resolve the underlying issue, or move Guava to your system class path.";

        public URL getBaseUrl() throws IOException {
            String strConcat = String.valueOf(FinalizableReferenceQueue.FINALIZER_CLASS_NAME.replace(InetAddresses.IPV4_DELIMITER, '/')).concat(ClassPath.CLASS_FILE_NAME_EXTENSION);
            URL resource = DecoupledLoader.class.getClassLoader().getResource(strConcat);
            if (resource == null) {
                throw new FileNotFoundException(strConcat);
            }
            String string = resource.toString();
            if (string.endsWith(strConcat)) {
                return new URL(resource, string.substring(0, string.length() - strConcat.length()));
            }
            throw new IOException(string.length() != 0 ? "Unsupported path style: ".concat(string) : new String("Unsupported path style: "));
        }

        @Override // com.google.common.base.FinalizableReferenceQueue.FinalizerLoader
        public Class<?> loadFinalizer() {
            try {
                return newLoader(getBaseUrl()).loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
            } catch (Exception e2) {
                FinalizableReferenceQueue.logger.log(Level.WARNING, LOADING_ERROR, (Throwable) e2);
                return null;
            }
        }

        public URLClassLoader newLoader(URL url) {
            return new URLClassLoader(new URL[]{url}, null);
        }
    }

    static class DirectLoader implements FinalizerLoader {
        @Override // com.google.common.base.FinalizableReferenceQueue.FinalizerLoader
        public Class<?> loadFinalizer() {
            try {
                return Class.forName(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
            } catch (ClassNotFoundException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    interface FinalizerLoader {
        Class<?> loadFinalizer();
    }

    static class SystemLoader implements FinalizerLoader {

        @VisibleForTesting
        public static boolean disabled;

        @Override // com.google.common.base.FinalizableReferenceQueue.FinalizerLoader
        public Class<?> loadFinalizer() {
            if (disabled) {
                return null;
            }
            try {
                ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                if (systemClassLoader != null) {
                    try {
                        return systemClassLoader.loadClass(FinalizableReferenceQueue.FINALIZER_CLASS_NAME);
                    } catch (ClassNotFoundException unused) {
                    }
                }
                return null;
            } catch (SecurityException unused2) {
                FinalizableReferenceQueue.logger.info("Not allowed to access system class loader.");
                return null;
            }
        }
    }

    public FinalizableReferenceQueue() {
        boolean z = true;
        try {
            startFinalizer.invoke(null, FinalizableReference.class, this.queue, this.frqRef);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        } catch (Throwable th) {
            logger.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", th);
            z = false;
        }
        this.threadStarted = z;
    }

    public static Method getStartFinalizer(Class<?> cls) {
        try {
            return cls.getMethod("startFinalizer", Class.class, ReferenceQueue.class, PhantomReference.class);
        } catch (NoSuchMethodException e2) {
            throw new AssertionError(e2);
        }
    }

    public static Class<?> loadFinalizer(FinalizerLoader... finalizerLoaderArr) {
        for (FinalizerLoader finalizerLoader : finalizerLoaderArr) {
            Class<?> clsLoadFinalizer = finalizerLoader.loadFinalizer();
            if (clsLoadFinalizer != null) {
                return clsLoadFinalizer;
            }
        }
        throw new AssertionError();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void cleanUp() {
        if (this.threadStarted) {
            return;
        }
        while (true) {
            Reference<? extends Object> referencePoll = this.queue.poll();
            if (referencePoll == 0) {
                return;
            }
            referencePoll.clear();
            try {
                ((FinalizableReference) referencePoll).finalizeReferent();
            } catch (Throwable th) {
                logger.log(Level.SEVERE, "Error cleaning up after reference.", th);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.frqRef.enqueue();
        cleanUp();
    }
}
