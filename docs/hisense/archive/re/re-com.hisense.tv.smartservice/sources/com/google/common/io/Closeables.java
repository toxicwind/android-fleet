package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class Closeables {

    @VisibleForTesting
    public static final Logger logger = Logger.getLogger(Closeables.class.getName());

    public static void close(Closeable closeable, boolean z) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e2) {
            if (!z) {
                throw e2;
            }
            logger.log(Level.WARNING, "IOException thrown while closing Closeable.", (Throwable) e2);
        }
    }

    public static void closeQuietly(InputStream inputStream) {
        try {
            close(inputStream, true);
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public static void closeQuietly(Reader reader) {
        try {
            close(reader, true);
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
