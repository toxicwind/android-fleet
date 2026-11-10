package com.google.common.io;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class Resources {
    public static ByteSource asByteSource(URL url) {
        return new UrlByteSource(url, null);
    }

    public static CharSource asCharSource(URL url, Charset charset) {
        return asByteSource(url).asCharSource(charset);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X */
    public static void copy(URL url, OutputStream outputStream) throws X {
        asByteSource(url).copyTo(outputStream);
    }

    @CanIgnoreReturnValue
    public static URL getResource(String str) {
        URL resource = ((ClassLoader) MoreObjects.firstNonNull(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader())).getResource(str);
        Preconditions.checkArgument(resource != null, "resource %s not found.", str);
        return resource;
    }

    @CanIgnoreReturnValue
    public static <T> T readLines(URL url, Charset charset, LineProcessor<T> lineProcessor) {
        return (T) asCharSource(url, charset).readLines(lineProcessor);
    }

    public static byte[] toByteArray(URL url) {
        return asByteSource(url).read();
    }

    public static String toString(URL url, Charset charset) {
        return asCharSource(url, charset).read();
    }

    public static List<String> readLines(URL url, Charset charset) {
        return (List) readLines(url, charset, new LineProcessor<List<String>>() { // from class: com.google.common.io.Resources.1
            public final List<String> result = new ArrayList();

            @Override // com.google.common.io.LineProcessor
            public boolean processLine(String str) {
                this.result.add(str);
                return true;
            }

            @Override // com.google.common.io.LineProcessor
            public List<String> getResult() {
                return this.result;
            }
        });
    }

    private static final class UrlByteSource extends ByteSource {
        public final URL url;

        public UrlByteSource(URL url) {
            if (url == null) {
                throw new NullPointerException();
            }
            this.url = url;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return this.url.openStream();
        }

        public String toString() {
            String strValueOf = String.valueOf(this.url);
            return a.a(strValueOf.length() + 24, "Resources.asByteSource(", strValueOf, ")");
        }

        public /* synthetic */ UrlByteSource(URL url, AnonymousClass1 anonymousClass1) {
            if (url != null) {
                this.url = url;
                return;
            }
            throw new NullPointerException();
        }
    }

    @CanIgnoreReturnValue
    public static URL getResource(Class<?> cls, String str) {
        URL resource = cls.getResource(str);
        Preconditions.checkArgument(resource != null, "resource %s relative to %s not found.", str, cls.getName());
        return resource;
    }
}
