package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.upstream.DataSource;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultExtractorInput implements ExtractorInput {
    private static final byte[] SCRATCH_SPACE = new byte[4096];
    private final DataSource dataSource;
    private byte[] peekBuffer = new byte[8192];
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final long streamLength;

    public DefaultExtractorInput(DataSource dataSource, long j, long j2) {
        this.dataSource = dataSource;
        this.position = j;
        this.streamLength = j2;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public int read(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i, i2);
        if (fromPeekBuffer == 0) {
            fromPeekBuffer = readFromDataSource(bArr, i, i2, 0, true);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public boolean readFully(byte[] bArr, int i, int i2, boolean z) throws InterruptedException, IOException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i, i2);
        while (fromPeekBuffer < i2 && fromPeekBuffer != -1) {
            fromPeekBuffer = readFromDataSource(bArr, i, i2, fromPeekBuffer, z);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public void readFully(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        readFully(bArr, i, i2, false);
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public int skip(int i) throws InterruptedException, IOException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i);
        if (iSkipFromPeekBuffer == 0) {
            byte[] bArr = SCRATCH_SPACE;
            iSkipFromPeekBuffer = readFromDataSource(bArr, 0, Math.min(i, bArr.length), 0, true);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer;
    }

    public boolean skipFully(int i, boolean z) throws InterruptedException, IOException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i);
        while (iSkipFromPeekBuffer < i && iSkipFromPeekBuffer != -1) {
            byte[] bArr = SCRATCH_SPACE;
            iSkipFromPeekBuffer = readFromDataSource(bArr, -iSkipFromPeekBuffer, Math.min(i, bArr.length + iSkipFromPeekBuffer), iSkipFromPeekBuffer, z);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public void skipFully(int i) throws InterruptedException, IOException {
        skipFully(i, false);
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public boolean peekFully(byte[] bArr, int i, int i2, boolean z) throws InterruptedException, IOException {
        if (!advancePeekPosition(i2, z)) {
            return false;
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - i2, bArr, i, i2);
        return true;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public void peekFully(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        peekFully(bArr, i, i2, false);
    }

    public boolean advancePeekPosition(int i, boolean z) throws InterruptedException, IOException {
        ensureSpaceForPeek(i);
        int iMin = Math.min(this.peekBufferLength - this.peekBufferPosition, i);
        while (iMin < i) {
            iMin = readFromDataSource(this.peekBuffer, this.peekBufferPosition, i, iMin, z);
            if (iMin == -1) {
                return false;
            }
        }
        int i2 = this.peekBufferPosition + i;
        this.peekBufferPosition = i2;
        this.peekBufferLength = Math.max(this.peekBufferLength, i2);
        return true;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public void advancePeekPosition(int i) throws InterruptedException, IOException {
        advancePeekPosition(i, false);
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.position + ((long) this.peekBufferPosition);
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public long getPosition() {
        return this.position;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorInput
    public long getLength() {
        return this.streamLength;
    }

    private void ensureSpaceForPeek(int i) {
        int i2 = this.peekBufferPosition + i;
        byte[] bArr = this.peekBuffer;
        if (i2 > bArr.length) {
            this.peekBuffer = Arrays.copyOf(bArr, Math.max(bArr.length * 2, i2));
        }
    }

    private int skipFromPeekBuffer(int i) {
        int iMin = Math.min(this.peekBufferLength, i);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private int readFromPeekBuffer(byte[] bArr, int i, int i2) {
        int i3 = this.peekBufferLength;
        if (i3 == 0) {
            return 0;
        }
        int iMin = Math.min(i3, i2);
        System.arraycopy(this.peekBuffer, 0, bArr, i, iMin);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private void updatePeekBuffer(int i) {
        int i2 = this.peekBufferLength - i;
        this.peekBufferLength = i2;
        this.peekBufferPosition = 0;
        byte[] bArr = this.peekBuffer;
        System.arraycopy(bArr, i, bArr, 0, i2);
    }

    private int readFromDataSource(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int i4 = this.dataSource.read(bArr, i + i3, i2 - i3);
        if (i4 != -1) {
            return i3 + i4;
        }
        if (i3 == 0 && z) {
            return -1;
        }
        throw new EOFException();
    }

    private void commitBytesRead(int i) {
        if (i != -1) {
            this.position += (long) i;
        }
    }
}
