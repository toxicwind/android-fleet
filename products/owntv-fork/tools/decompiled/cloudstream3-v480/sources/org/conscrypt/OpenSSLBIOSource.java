package org.conscrypt;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes8.dex */
final class OpenSSLBIOSource {
    private OpenSSLBIOInputStream source;

    static OpenSSLBIOSource wrap(ByteBuffer byteBuffer) {
        return new OpenSSLBIOSource(new OpenSSLBIOInputStream(new ByteBufferInputStream(byteBuffer), false));
    }

    private OpenSSLBIOSource(OpenSSLBIOInputStream openSSLBIOInputStream) {
        this.source = openSSLBIOInputStream;
    }

    long getContext() {
        return this.source.getBioContext();
    }

    private synchronized void release() {
        OpenSSLBIOInputStream openSSLBIOInputStream = this.source;
        if (openSSLBIOInputStream != null) {
            NativeCrypto.BIO_free_all(openSSLBIOInputStream.getBioContext());
            this.source = null;
        }
    }

    protected void finalize() throws Throwable {
        try {
            release();
        } finally {
            super.finalize();
        }
    }

    private static class ByteBufferInputStream extends InputStream implements InputStreamRetargetInterface {
        private final ByteBuffer source;

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }

        ByteBufferInputStream(ByteBuffer byteBuffer) {
            this.source = byteBuffer;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.source.remaining() > 0) {
                return this.source.get();
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.source.limit() - this.source.position();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            int iPosition = this.source.position();
            this.source.get(bArr);
            return this.source.position() - iPosition;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int iMin = Math.min(this.source.remaining(), i2);
            int iPosition = this.source.position();
            this.source.get(bArr, i, iMin);
            return this.source.position() - iPosition;
        }

        @Override // java.io.InputStream
        public void reset() throws IOException {
            this.source.reset();
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            long jPosition = this.source.position();
            this.source.position((int) (j + jPosition));
            return ((long) this.source.position()) - jPosition;
        }
    }
}
