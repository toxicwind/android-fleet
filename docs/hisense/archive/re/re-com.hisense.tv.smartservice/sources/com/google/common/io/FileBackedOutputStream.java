package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class FileBackedOutputStream extends OutputStream {

    @GuardedBy("this")
    public File file;
    public final int fileThreshold;

    @GuardedBy("this")
    public MemoryOutput memory;

    @GuardedBy("this")
    public OutputStream out;
    public final File parentDirectory;
    public final boolean resetOnFinalize;
    public final ByteSource source;

    private static class MemoryOutput extends ByteArrayOutputStream {
        public MemoryOutput() {
        }

        public byte[] getBuffer() {
            return ((ByteArrayOutputStream) this).buf;
        }

        public int getCount() {
            return ((ByteArrayOutputStream) this).count;
        }

        public /* synthetic */ MemoryOutput(AnonymousClass1 anonymousClass1) {
        }
    }

    public FileBackedOutputStream(int i) {
        this(i, false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized InputStream openInputStream() {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    @GuardedBy("this")
    private void update(int i) throws IOException {
        if (this.file != null || this.memory.getCount() + i <= this.fileThreshold) {
            return;
        }
        File fileCreateTempFile = File.createTempFile("FileBackedOutputStream", null, this.parentDirectory);
        if (this.resetOnFinalize) {
            fileCreateTempFile.deleteOnExit();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
        fileOutputStream.write(this.memory.getBuffer(), 0, this.memory.getCount());
        fileOutputStream.flush();
        this.out = fileOutputStream;
        this.file = fileCreateTempFile;
        this.memory = null;
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() {
        this.out.flush();
    }

    @VisibleForTesting
    public synchronized File getFile() {
        return this.file;
    }

    public synchronized void reset() {
        AnonymousClass1 anonymousClass1 = null;
        try {
            close();
            if (this.memory == null) {
                this.memory = new MemoryOutput(anonymousClass1);
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            if (this.file != null) {
                File file = this.file;
                this.file = null;
                if (!file.delete()) {
                    String strValueOf = String.valueOf(file);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 18);
                    sb.append("Could not delete: ");
                    sb.append(strValueOf);
                    throw new IOException(sb.toString());
                }
            }
        } catch (Throwable th) {
            if (this.memory == null) {
                this.memory = new MemoryOutput(anonymousClass1);
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            if (this.file != null) {
                File file2 = this.file;
                this.file = null;
                if (!file2.delete()) {
                    String strValueOf2 = String.valueOf(file2);
                    StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 18);
                    sb2.append("Could not delete: ");
                    sb2.append(strValueOf2);
                    throw new IOException(sb2.toString());
                }
            }
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int i) {
        update(1);
        this.out.write(i);
    }

    public FileBackedOutputStream(int i, boolean z) {
        this(i, z, null);
    }

    public FileBackedOutputStream(int i, boolean z, File file) {
        this.fileThreshold = i;
        this.resetOnFinalize = z;
        this.parentDirectory = file;
        this.memory = new MemoryOutput(null);
        this.out = this.memory;
        if (z) {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.1
                public void finalize() {
                    try {
                        FileBackedOutputStream.this.reset();
                    } catch (Throwable th) {
                        th.printStackTrace(System.err);
                    }
                }

                @Override // com.google.common.io.ByteSource
                public InputStream openStream() {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        } else {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.2
                @Override // com.google.common.io.ByteSource
                public InputStream openStream() {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int i, int i2) {
        update(i2);
        this.out.write(bArr, i, i2);
    }
}
