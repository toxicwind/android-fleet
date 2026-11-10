package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.io.Flushable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class Flushables {
    public static final Logger logger = Logger.getLogger(Flushables.class.getName());

    public static void flush(Flushable flushable, boolean z) throws IOException {
        try {
            flushable.flush();
        } catch (IOException e2) {
            if (!z) {
                throw e2;
            }
            logger.log(Level.WARNING, "IOException thrown while flushing Flushable.", (Throwable) e2);
        }
    }

    public static void flushQuietly(Flushable flushable) {
        try {
            flush(flushable, true);
        } catch (IOException e2) {
            logger.log(Level.SEVERE, "IOException should not have been thrown.", (Throwable) e2);
        }
    }
}
