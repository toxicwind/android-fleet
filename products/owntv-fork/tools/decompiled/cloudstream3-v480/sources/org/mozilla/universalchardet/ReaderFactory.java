package org.mozilla.universalchardet;

import j$.io.FileRetargetClass;
import j$.nio.file.Files;
import j$.nio.file.OpenOption;
import j$.util.Objects;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes8.dex */
public final class ReaderFactory {
    private ReaderFactory() {
        throw new AssertionError("No instances allowed");
    }

    public static BufferedReader createBufferedReader(File file, Charset charset) throws IOException {
        Charset charsetForName = (Charset) Objects.requireNonNull(charset, "defaultCharset must be not null");
        String strDetectCharset = UniversalDetector.detectCharset(file);
        if (strDetectCharset != null) {
            charsetForName = Charset.forName(strDetectCharset);
        }
        if (!charsetForName.name().contains("UTF")) {
            return Files.newBufferedReader(FileRetargetClass.toPath(file), charsetForName);
        }
        return new BufferedReader(new InputStreamReader(new UnicodeBOMInputStream(new BufferedInputStream(Files.newInputStream(FileRetargetClass.toPath(file), new OpenOption[0]))), charsetForName));
    }

    public static BufferedReader createBufferedReader(File file) throws IOException {
        return createBufferedReader(file, Charset.defaultCharset());
    }

    public static BufferedReader createBufferedReader(byte[] bArr, Charset charset) throws IOException {
        Charset charsetForName = (Charset) Objects.requireNonNull(charset, "defaultCharset must be not null");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            String strDetectCharset = UniversalDetector.detectCharset(byteArrayInputStream);
            byteArrayInputStream.close();
            if (strDetectCharset != null) {
                charsetForName = Charset.forName(strDetectCharset);
            }
            if (!charsetForName.name().contains("UTF")) {
                return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr), charsetForName));
            }
            return new BufferedReader(new InputStreamReader(new UnicodeBOMInputStream(new ByteArrayInputStream(bArr)), charsetForName));
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static BufferedReader createBufferedReader(byte[] bArr) throws IOException {
        return createBufferedReader(bArr, Charset.defaultCharset());
    }

    @Deprecated
    public static Reader createReaderFromFile(File file, Charset charset) throws IOException {
        return createBufferedReader(file, charset);
    }

    @Deprecated
    public static Reader createReaderFromFile(File file) throws IOException {
        return createReaderFromFile(file, Charset.defaultCharset());
    }
}
