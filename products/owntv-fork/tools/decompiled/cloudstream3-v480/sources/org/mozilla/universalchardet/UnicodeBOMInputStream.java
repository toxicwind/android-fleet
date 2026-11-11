package org.mozilla.universalchardet;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;

/* JADX INFO: loaded from: classes8.dex */
public class UnicodeBOMInputStream extends InputStream implements InputStreamRetargetInterface {
    private final BOM bom;
    private final PushbackInputStream in;
    private boolean skipped;

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream outputStream) {
        return DesugarInputStream.transferTo(this, outputStream);
    }

    public static final class BOM {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final byte[] bytes;
        private final String description;
        public static final BOM NONE = new BOM(new byte[0], "NONE");
        public static final BOM UTF_8 = new BOM(new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_1, ByteSourceJsonBootstrapper.UTF8_BOM_2, ByteSourceJsonBootstrapper.UTF8_BOM_3}, "UTF-8");
        public static final BOM UTF_16_LE = new BOM(new byte[]{-1, -2}, "UTF-16 little-endian");
        public static final BOM UTF_16_BE = new BOM(new byte[]{-2, -1}, "UTF-16 big-endian");
        public static final BOM UTF_32_LE = new BOM(new byte[]{-1, -2, 0, 0}, "UTF-32 little-endian");
        public static final BOM UTF_32_BE = new BOM(new byte[]{0, 0, -2, -1}, "UTF-32 big-endian");

        public final String toString() {
            return this.description;
        }

        public final byte[] getBytes() {
            byte[] bArr = this.bytes;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            return bArr2;
        }

        private BOM(byte[] bArr, String str) {
            this.bytes = bArr;
            this.description = str;
        }
    }

    public UnicodeBOMInputStream(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x004a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0061  */
    /* JADX WARN: Code duplicated, block: B:36:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x0070  */
    /* JADX WARN: Code duplicated, block: B:43:0x0079  */
    public UnicodeBOMInputStream(InputStream inputStream, boolean z) throws IOException {
        byte b;
        this.skipped = false;
        if (inputStream == null) {
            throw new NullPointerException("invalid input stream: null is not allowed");
        }
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 4);
        this.in = pushbackInputStream;
        byte[] bArr = new byte[4];
        int i = pushbackInputStream.read(bArr);
        if (i == 2) {
            b = bArr[0];
            if (b != -1 && bArr[1] == -2) {
                this.bom = BOM.UTF_16_LE;
            } else if (b != -2 && bArr[1] == -1) {
                this.bom = BOM.UTF_16_BE;
            } else {
                this.bom = BOM.NONE;
            }
        } else if (i == 3) {
            if (bArr[0] != -17 && bArr[1] == -69 && bArr[2] == -65) {
                this.bom = BOM.UTF_8;
            } else {
                b = bArr[0];
                if (b != -1) {
                    if (b != -2) {
                        this.bom = BOM.NONE;
                    } else {
                        this.bom = BOM.NONE;
                    }
                } else if (b != -2) {
                    this.bom = BOM.NONE;
                } else {
                    this.bom = BOM.NONE;
                }
            }
        } else if (i == 4) {
            byte b2 = bArr[0];
            if (b2 == -1 && bArr[1] == -2 && bArr[2] == 0 && bArr[3] == 0) {
                this.bom = BOM.UTF_32_LE;
            } else if (b2 == 0 && bArr[1] == 0 && bArr[2] == -2 && bArr[3] == -1) {
                this.bom = BOM.UTF_32_BE;
            } else if (bArr[0] != -17) {
                b = bArr[0];
                if (b != -1) {
                    if (b != -2) {
                        this.bom = BOM.NONE;
                    } else {
                        this.bom = BOM.NONE;
                    }
                } else if (b != -2) {
                    this.bom = BOM.NONE;
                } else {
                    this.bom = BOM.NONE;
                }
            } else {
                b = bArr[0];
                if (b != -1) {
                    if (b != -2) {
                        this.bom = BOM.NONE;
                    } else {
                        this.bom = BOM.NONE;
                    }
                } else if (b != -2) {
                    this.bom = BOM.NONE;
                } else {
                    this.bom = BOM.NONE;
                }
            }
        } else {
            this.bom = BOM.NONE;
        }
        if (i > 0) {
            pushbackInputStream.unread(bArr, 0, i);
        }
        if (z) {
            skipBOM();
        }
    }

    public final BOM getBOM() {
        return this.bom;
    }

    public final synchronized UnicodeBOMInputStream skipBOM() throws IOException {
        if (!this.skipped) {
            long length = this.bom.bytes.length;
            for (long jSkip = this.in.skip(length); jSkip < length; jSkip++) {
                this.in.read();
            }
            this.skipped = true;
        }
        return this;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        this.skipped = true;
        return this.in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        this.skipped = true;
        return this.in.read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        this.skipped = true;
        return this.in.read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        this.skipped = true;
        return this.in.skip(j);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.in.mark(i);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.in.reset();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
