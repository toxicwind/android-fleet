package com.google.android.exoplayer.extractor;

import android.util.Log;
import com.google.android.exoplayer.CryptoInfo;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.upstream.Allocation;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class RollingSampleBuffer {
    private final int allocationLength;
    private final Allocator allocator;
    private Allocation lastAllocation;
    private int lastAllocationOffset;
    private long totalBytesDropped;
    private long totalBytesWritten;
    private final InfoQueue infoQueue = new InfoQueue();
    private final LinkedBlockingDeque<Allocation> dataQueue = new LinkedBlockingDeque<>();
    private final SampleExtrasHolder extrasHolder = new SampleExtrasHolder();
    private final ParsableByteArray scratch = new ParsableByteArray(32);

    public RollingSampleBuffer(Allocator allocator) {
        this.allocator = allocator;
        this.allocationLength = allocator.getIndividualAllocationLength();
        this.lastAllocationOffset = this.allocationLength;
    }

    public void clear() {
        this.infoQueue.clear();
        Allocator allocator = this.allocator;
        LinkedBlockingDeque<Allocation> linkedBlockingDeque = this.dataQueue;
        allocator.release((Allocation[]) linkedBlockingDeque.toArray(new Allocation[linkedBlockingDeque.size()]));
        this.dataQueue.clear();
        this.totalBytesDropped = 0L;
        this.totalBytesWritten = 0L;
        this.lastAllocation = null;
        this.lastAllocationOffset = this.allocationLength;
    }

    public int getWriteIndex() {
        return this.infoQueue.getWriteIndex();
    }

    public void discardUpstreamSamples(int i) {
        long jDiscardUpstreamSamples = this.infoQueue.discardUpstreamSamples(i);
        this.totalBytesWritten = jDiscardUpstreamSamples;
        dropUpstreamFrom(jDiscardUpstreamSamples);
    }

    private void dropUpstreamFrom(long j) {
        int i = (int) (j - this.totalBytesDropped);
        int i2 = this.allocationLength;
        int i3 = i / i2;
        int i4 = i % i2;
        int size = (this.dataQueue.size() - i3) - 1;
        if (i4 == 0) {
            size++;
        }
        for (int i5 = 0; i5 < size; i5++) {
            this.allocator.release(this.dataQueue.removeLast());
        }
        this.lastAllocation = this.dataQueue.peekLast();
        if (i4 == 0) {
            i4 = this.allocationLength;
        }
        this.lastAllocationOffset = i4;
    }

    public int getReadIndex() {
        return this.infoQueue.getReadIndex();
    }

    public boolean peekSample(SampleHolder sampleHolder) {
        return this.infoQueue.peekSample(sampleHolder, this.extrasHolder);
    }

    public void skipSample() {
        dropDownstreamTo(this.infoQueue.moveToNextSample());
    }

    public boolean skipToKeyframeBefore(long j) {
        long jSkipToKeyframeBefore = this.infoQueue.skipToKeyframeBefore(j);
        if (jSkipToKeyframeBefore == -1) {
            return false;
        }
        dropDownstreamTo(jSkipToKeyframeBefore);
        return true;
    }

    public boolean readSample(SampleHolder sampleHolder) {
        if (!this.infoQueue.peekSample(sampleHolder, this.extrasHolder)) {
            return false;
        }
        if (sampleHolder.isEncrypted()) {
            readEncryptionData(sampleHolder, this.extrasHolder);
        }
        sampleHolder.ensureSpaceForWrite(sampleHolder.size);
        readData(this.extrasHolder.offset, sampleHolder.data, sampleHolder.size);
        dropDownstreamTo(this.infoQueue.moveToNextSample());
        return true;
    }

    private void readEncryptionData(SampleHolder sampleHolder, SampleExtrasHolder sampleExtrasHolder) {
        long j = sampleExtrasHolder.offset;
        int unsignedShort = 1;
        readData(j, this.scratch.data, 1);
        long j2 = j + 1;
        byte b = this.scratch.data[0];
        boolean z = (b & ByteCompanionObject.MIN_VALUE) != 0;
        int i = b & ByteCompanionObject.MAX_VALUE;
        CryptoInfo cryptoInfo = sampleHolder.cryptoInfo;
        if (cryptoInfo.iv == null) {
            cryptoInfo.iv = new byte[16];
        }
        readData(j2, sampleHolder.cryptoInfo.iv, i);
        long j3 = j2 + ((long) i);
        if (z) {
            readData(j3, this.scratch.data, 2);
            j3 += 2;
            this.scratch.setPosition(0);
            unsignedShort = this.scratch.readUnsignedShort();
        }
        int i2 = unsignedShort;
        int[] iArr = sampleHolder.cryptoInfo.numBytesOfClearData;
        if (iArr == null || iArr.length < i2) {
            iArr = new int[i2];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = sampleHolder.cryptoInfo.numBytesOfEncryptedData;
        if (iArr3 == null || iArr3.length < i2) {
            iArr3 = new int[i2];
        }
        int[] iArr4 = iArr3;
        if (z) {
            int i3 = i2 * 6;
            ensureCapacity(this.scratch, i3);
            readData(j3, this.scratch.data, i3);
            j3 += (long) i3;
            this.scratch.setPosition(0);
            for (int i4 = 0; i4 < i2; i4++) {
                iArr2[i4] = this.scratch.readUnsignedShort();
                iArr4[i4] = this.scratch.readUnsignedIntToInt();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = sampleHolder.size - ((int) (j3 - sampleExtrasHolder.offset));
        }
        CryptoInfo cryptoInfo2 = sampleHolder.cryptoInfo;
        cryptoInfo2.set(i2, iArr2, iArr4, sampleExtrasHolder.encryptionKeyId, cryptoInfo2.iv, 1);
        long j4 = sampleExtrasHolder.offset;
        int i5 = (int) (j3 - j4);
        sampleExtrasHolder.offset = j4 + ((long) i5);
        sampleHolder.size -= i5;
    }

    private void readData(long j, ByteBuffer byteBuffer, int i) {
        while (i > 0) {
            dropDownstreamTo(j);
            int i2 = (int) (j - this.totalBytesDropped);
            int iMin = Math.min(i, this.allocationLength - i2);
            Allocation allocationPeek = this.dataQueue.peek();
            if (allocationPeek != null) {
                byteBuffer.put(allocationPeek.data, allocationPeek.translateOffset(i2), iMin);
                j += (long) iMin;
                i -= iMin;
            } else {
                Log.e("RollingSampleBuffer", "readData: dataQueue is null!");
            }
        }
    }

    private void readData(long j, byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            dropDownstreamTo(j);
            int i3 = (int) (j - this.totalBytesDropped);
            int iMin = Math.min(i - i2, this.allocationLength - i3);
            Allocation allocationPeek = this.dataQueue.peek();
            if (allocationPeek != null) {
                System.arraycopy(allocationPeek.data, allocationPeek.translateOffset(i3), bArr, i2, iMin);
                j += (long) iMin;
                i2 += iMin;
            } else {
                Log.e("RollingSampleBuffer", "readData: dataQueue is null!");
            }
        }
    }

    private void dropDownstreamTo(long j) {
        int i = ((int) (j - this.totalBytesDropped)) / this.allocationLength;
        for (int i2 = 0; i2 < i; i2++) {
            this.allocator.release(this.dataQueue.remove());
            this.totalBytesDropped += (long) this.allocationLength;
        }
    }

    private static void ensureCapacity(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit() < i) {
            parsableByteArray.reset(new byte[i], i);
        }
    }

    public long getWritePosition() {
        return this.totalBytesWritten;
    }

    public int appendData(DataSource dataSource, int i, boolean z) throws IOException {
        int iPrepareForAppend = prepareForAppend(i);
        Allocation allocation = this.lastAllocation;
        int i2 = dataSource.read(allocation.data, allocation.translateOffset(this.lastAllocationOffset), iPrepareForAppend);
        if (i2 == -1) {
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
        this.lastAllocationOffset += i2;
        this.totalBytesWritten += (long) i2;
        return i2;
    }

    public int appendData(ExtractorInput extractorInput, int i, boolean z) throws InterruptedException, IOException {
        int iPrepareForAppend = prepareForAppend(i);
        Allocation allocation = this.lastAllocation;
        int i2 = extractorInput.read(allocation.data, allocation.translateOffset(this.lastAllocationOffset), iPrepareForAppend);
        if (i2 == -1) {
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
        this.lastAllocationOffset += i2;
        this.totalBytesWritten += (long) i2;
        return i2;
    }

    public void appendData(ParsableByteArray parsableByteArray, int i) {
        while (i > 0) {
            int iPrepareForAppend = prepareForAppend(i);
            Allocation allocation = this.lastAllocation;
            parsableByteArray.readBytes(allocation.data, allocation.translateOffset(this.lastAllocationOffset), iPrepareForAppend);
            this.lastAllocationOffset += iPrepareForAppend;
            this.totalBytesWritten += (long) iPrepareForAppend;
            i -= iPrepareForAppend;
        }
    }

    public void commitSample(long j, int i, long j2, int i2, byte[] bArr) {
        this.infoQueue.commitSample(j, i, j2, i2, bArr);
    }

    private int prepareForAppend(int i) {
        if (this.lastAllocationOffset == this.allocationLength) {
            this.lastAllocationOffset = 0;
            Allocation allocationAllocate = this.allocator.allocate();
            this.lastAllocation = allocationAllocate;
            this.dataQueue.add(allocationAllocate);
        }
        return Math.min(i, this.allocationLength - this.lastAllocationOffset);
    }

    private static final class InfoQueue {
        private int absoluteReadIndex;
        private int queueSize;
        private int relativeReadIndex;
        private int relativeWriteIndex;
        private int capacity = 1000;
        private long[] offsets = new long[1000];
        private long[] timesUs = new long[1000];
        private int[] flags = new int[1000];
        private int[] sizes = new int[1000];
        private byte[][] encryptionKeys = new byte[1000][];

        public synchronized void clear() {
            this.absoluteReadIndex = 0;
            this.relativeReadIndex = 0;
            this.relativeWriteIndex = 0;
            this.queueSize = 0;
        }

        public synchronized int getWriteIndex() {
            return this.absoluteReadIndex + this.queueSize;
        }

        public synchronized long discardUpstreamSamples(int i) {
            int writeIndex = getWriteIndex() - i;
            Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.queueSize);
            if (writeIndex == 0) {
                if (this.absoluteReadIndex == 0) {
                    return 0L;
                }
                int i2 = (this.relativeWriteIndex == 0 ? this.capacity : this.relativeWriteIndex) - 1;
                return this.offsets[i2] + ((long) this.sizes[i2]);
            }
            this.queueSize -= writeIndex;
            int i3 = ((this.relativeWriteIndex + this.capacity) - writeIndex) % this.capacity;
            this.relativeWriteIndex = i3;
            return this.offsets[i3];
        }

        public synchronized int getReadIndex() {
            return this.absoluteReadIndex;
        }

        public synchronized boolean peekSample(SampleHolder sampleHolder, SampleExtrasHolder sampleExtrasHolder) {
            if (this.queueSize == 0) {
                return false;
            }
            sampleHolder.timeUs = this.timesUs[this.relativeReadIndex];
            sampleHolder.size = this.sizes[this.relativeReadIndex];
            sampleHolder.flags = this.flags[this.relativeReadIndex];
            sampleExtrasHolder.offset = this.offsets[this.relativeReadIndex];
            sampleExtrasHolder.encryptionKeyId = this.encryptionKeys[this.relativeReadIndex];
            return true;
        }

        public synchronized long moveToNextSample() {
            int i;
            this.queueSize--;
            i = this.relativeReadIndex;
            int i2 = i + 1;
            this.relativeReadIndex = i2;
            this.absoluteReadIndex++;
            if (i2 == this.capacity) {
                this.relativeReadIndex = 0;
            }
            return this.queueSize > 0 ? this.offsets[this.relativeReadIndex] : ((long) this.sizes[i]) + this.offsets[i];
        }

        public synchronized long skipToKeyframeBefore(long j) {
            if (this.queueSize != 0 && j >= this.timesUs[this.relativeReadIndex]) {
                if (j > this.timesUs[(this.relativeWriteIndex == 0 ? this.capacity : this.relativeWriteIndex) - 1]) {
                    return -1L;
                }
                int i = 0;
                int i2 = this.relativeReadIndex;
                int i3 = -1;
                while (i2 != this.relativeWriteIndex && this.timesUs[i2] <= j) {
                    if ((this.flags[i2] & 1) != 0) {
                        i3 = i;
                    }
                    i2 = (i2 + 1) % this.capacity;
                    i++;
                }
                if (i3 == -1) {
                    return -1L;
                }
                this.queueSize -= i3;
                int i4 = (this.relativeReadIndex + i3) % this.capacity;
                this.relativeReadIndex = i4;
                this.absoluteReadIndex += i3;
                return this.offsets[i4];
            }
            return -1L;
        }

        public synchronized void commitSample(long j, int i, long j2, int i2, byte[] bArr) {
            this.timesUs[this.relativeWriteIndex] = j;
            this.offsets[this.relativeWriteIndex] = j2;
            this.sizes[this.relativeWriteIndex] = i2;
            this.flags[this.relativeWriteIndex] = i;
            this.encryptionKeys[this.relativeWriteIndex] = bArr;
            int i3 = this.queueSize + 1;
            this.queueSize = i3;
            if (i3 == this.capacity) {
                int i4 = this.capacity + 1000;
                long[] jArr = new long[i4];
                long[] jArr2 = new long[i4];
                int[] iArr = new int[i4];
                int[] iArr2 = new int[i4];
                byte[][] bArr2 = new byte[i4][];
                int i5 = this.capacity - this.relativeReadIndex;
                System.arraycopy(this.offsets, this.relativeReadIndex, jArr, 0, i5);
                System.arraycopy(this.timesUs, this.relativeReadIndex, jArr2, 0, i5);
                System.arraycopy(this.flags, this.relativeReadIndex, iArr, 0, i5);
                System.arraycopy(this.sizes, this.relativeReadIndex, iArr2, 0, i5);
                System.arraycopy(this.encryptionKeys, this.relativeReadIndex, bArr2, 0, i5);
                int i6 = this.relativeReadIndex;
                System.arraycopy(this.offsets, 0, jArr, i5, i6);
                System.arraycopy(this.timesUs, 0, jArr2, i5, i6);
                System.arraycopy(this.flags, 0, iArr, i5, i6);
                System.arraycopy(this.sizes, 0, iArr2, i5, i6);
                System.arraycopy(this.encryptionKeys, 0, bArr2, i5, i6);
                this.offsets = jArr;
                this.timesUs = jArr2;
                this.flags = iArr;
                this.sizes = iArr2;
                this.encryptionKeys = bArr2;
                this.relativeReadIndex = 0;
                this.relativeWriteIndex = this.capacity;
                this.queueSize = this.capacity;
                this.capacity = i4;
            } else {
                int i7 = this.relativeWriteIndex + 1;
                this.relativeWriteIndex = i7;
                if (i7 == this.capacity) {
                    this.relativeWriteIndex = 0;
                }
            }
        }
    }

    private static final class SampleExtrasHolder {
        public byte[] encryptionKeyId;
        public long offset;

        private SampleExtrasHolder() {
        }
    }
}
