package com.fleeksoft.ksoup.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: SourceReaderByteArray.kt */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "SourceReaderByteArray is deprecated, use com.fleeksoft.io.ByteArrayInputStream instead.", replaceWith = @ReplaceWith(expression = "com.fleeksoft.io.ByteArrayInputStream", imports = {}))
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\bH\u0016J \u0010\u0015\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u00020\u0003H\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016J\b\u0010\u001a\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/fleeksoft/ksoup/io/SourceReaderByteArray;", "Lcom/fleeksoft/ksoup/io/SourceReader;", "bytes", "", "<init>", "([B)V", "source", "currentPosition", "", "markedPosition", "Ljava/lang/Integer;", "isClosed", "", "mark", "", "readLimit", "", "reset", "readInt", "readBytes", PeertubeParsingHelper.COUNT_KEY, "read", TypedValues.CycleType.S_WAVE_OFFSET, "length", "readAllBytes", "exhausted", "close", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SourceReaderByteArray implements SourceReader {
    private int currentPosition;
    private boolean isClosed;
    private Integer markedPosition;
    private byte[] source;

    public SourceReaderByteArray(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.source = bytes;
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public void mark(long readLimit) {
        this.markedPosition = Integer.valueOf(this.currentPosition);
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public void reset() {
        this.isClosed = false;
        Integer num = this.markedPosition;
        if (num != null) {
            this.currentPosition = num.intValue();
            this.markedPosition = null;
        }
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public int readInt() {
        if (exhausted()) {
            return -1;
        }
        byte[] bArr = this.source;
        int i = this.currentPosition;
        this.currentPosition = i + 1;
        return bArr[i] & 255;
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public byte[] readBytes(int count) {
        byte[] bArr = new byte[count];
        int i = 0;
        while (!exhausted() && i < count) {
            byte[] bArr2 = this.source;
            int i2 = this.currentPosition;
            this.currentPosition = i2 + 1;
            bArr[i] = bArr2[i2];
            i++;
        }
        if (i == 0) {
            return new byte[0];
        }
        return i != count ? ArraysKt.copyOfRange(bArr, 0, i) : bArr;
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public int read(byte[] bytes, int offset, int length) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        int i = this.currentPosition;
        while (!exhausted() && offset < length) {
            byte[] bArr = this.source;
            int i2 = this.currentPosition;
            this.currentPosition = i2 + 1;
            bytes[offset] = bArr[i2];
            offset++;
        }
        int i3 = this.currentPosition - i;
        if (i3 == 0 && exhausted()) {
            return -1;
        }
        return i3;
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public byte[] readAllBytes() {
        return readBytes(this.source.length - this.currentPosition);
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public boolean exhausted() {
        return this.currentPosition >= this.source.length;
    }

    @Override // com.fleeksoft.ksoup.io.SourceReader
    public void close() {
        this.isClosed = true;
    }
}
