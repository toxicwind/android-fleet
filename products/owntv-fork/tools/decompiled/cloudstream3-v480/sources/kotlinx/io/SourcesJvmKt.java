package kotlinx.io;

import androidx.collection.SieveCacheKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.auth.HttpAuthHeader;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: SourcesJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\u0080\u0004\u001a\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\u0080\u0004\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\u0080\u0004\u001a\u000e\u0010\t\u001a\u00020\n*\u00020\bH\u0086\u0080\u0004\u001a\u0016\u0010\u000b\u001a\u00020\f*\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u0010*\u00020\bH\u0086\u0080\u0004¨\u0006\u0011"}, d2 = {"readStringImpl", "", "Lkotlinx/io/Buffer;", "byteCount", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "readString", "Lkotlinx/io/Source;", "asInputStream", "Ljava/io/InputStream;", "readAtMostTo", "", "sink", "Ljava/nio/ByteBuffer;", "asByteChannel", "Ljava/nio/channels/ReadableByteChannel;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SourcesJvmKt {
    static final boolean asByteChannel$lambda$0() {
        return false;
    }

    static final boolean asInputStream$lambda$0() {
        return false;
    }

    private static final String readStringImpl(Buffer buffer, long j, Charset charset) throws EOFException {
        String str;
        int i;
        if (j < 0 || j > SieveCacheKt.NodeLinkMask) {
            throw new IllegalArgumentException(("byteCount (" + j + ") is not within the range [0..2147483647)").toString());
        }
        if (buffer.getSizeMut() < j) {
            throw new EOFException("Buffer contains less bytes then required (byteCount: " + j + ", size: " + buffer.getSizeMut() + ')');
        }
        if (j == 0) {
            return "";
        }
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        if (buffer.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = buffer.getHead();
        Intrinsics.checkNotNull(head);
        byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
        int pos = head.getPos();
        if (head.getLimit() - pos >= j) {
            i = (int) j;
            str = new String(bArrDataAsByteArray, pos, i, charset);
        } else {
            str = null;
            i = 0;
        }
        if (i != 0) {
            if (i < 0) {
                throw new IllegalStateException("Returned negative read bytes count");
            }
            if (i > head.getSize()) {
                throw new IllegalStateException("Returned too many bytes");
            }
            buffer.skip(i);
        }
        return str == null ? new String(SourcesKt.readByteArray(buffer, (int) j), charset) : str;
    }

    public static final String readString(Source source, Charset charset) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        for (long j = 1; source.request(j); j *= 2) {
        }
        return readStringImpl(source.getBufferField(), source.getBufferField().getSizeMut(), charset);
    }

    public static final String readString(Source source, long j, Charset charset) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        source.require(j);
        return readStringImpl(source.getBufferField(), j, charset);
    }

    public static final InputStream asInputStream(final Source source) {
        MutablePropertyReference0Impl mutablePropertyReference0Impl;
        Intrinsics.checkNotNullParameter(source, "<this>");
        if (source instanceof RealSource) {
            mutablePropertyReference0Impl = new MutablePropertyReference0Impl(source) { // from class: kotlinx.io.SourcesJvmKt$asInputStream$isClosed$1
                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return Boolean.valueOf(((RealSource) this.receiver).closed);
                }

                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
                public void set(Object obj) {
                    ((RealSource) this.receiver).closed = ((Boolean) obj).booleanValue();
                }
            };
        } else {
            if (!(source instanceof Buffer)) {
                throw new NoWhenBranchMatchedException();
            }
            mutablePropertyReference0Impl = new Function0() { // from class: kotlinx.io.SourcesJvmKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(SourcesJvmKt.asInputStream$lambda$0());
                }
            };
        }
        return new C11451(mutablePropertyReference0Impl, source);
    }

    /* JADX INFO: renamed from: kotlinx.io.SourcesJvmKt$asInputStream$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SourcesJvm.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004¨\u0006\r"}, d2 = {"kotlinx/io/SourcesJvmKt$asInputStream$1", "Ljava/io/InputStream;", "read", "", "data", "", TypedValues.CycleType.S_WAVE_OFFSET, "byteCount", "available", "close", "", "toString", "", "kotlinx-io-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class C11451 extends InputStream implements InputStreamRetargetInterface {
        final /* synthetic */ Function0<Boolean> $isClosed;
        final /* synthetic */ Source $this_asInputStream;

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }

        C11451(Function0<Boolean> function0, Source source) {
            this.$isClosed = function0;
            this.$this_asInputStream = source;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.$isClosed.invoke().booleanValue()) {
                throw new IOException("Underlying source is closed.");
            }
            if (this.$this_asInputStream.exhausted()) {
                return -1;
            }
            return this.$this_asInputStream.readByte() & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] data, int offset, int byteCount) throws IOException {
            Intrinsics.checkNotNullParameter(data, "data");
            if (this.$isClosed.invoke().booleanValue()) {
                throw new IOException("Underlying source is closed.");
            }
            _UtilKt.checkOffsetAndCount(data.length, offset, byteCount);
            return this.$this_asInputStream.readAtMostTo(data, offset, byteCount + offset);
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            if (this.$isClosed.invoke().booleanValue()) {
                throw new IOException("Underlying source is closed.");
            }
            return (int) Math.min(this.$this_asInputStream.getBufferField().getSizeMut(), SieveCacheKt.NodeLinkMask);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.$this_asInputStream.close();
        }

        public String toString() {
            return this.$this_asInputStream + ".asInputStream()";
        }
    }

    public static final int readAtMostTo(Source source, ByteBuffer sink) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (source.getBufferField().getSizeMut() == 0) {
            source.request(8192L);
            if (source.getBufferField().getSizeMut() == 0) {
                return -1;
            }
        }
        return BuffersJvmKt.readAtMostTo(source.getBufferField(), sink);
    }

    public static final ReadableByteChannel asByteChannel(final Source source) {
        final MutablePropertyReference0Impl mutablePropertyReference0Impl;
        Intrinsics.checkNotNullParameter(source, "<this>");
        if (source instanceof RealSource) {
            mutablePropertyReference0Impl = new MutablePropertyReference0Impl(source) { // from class: kotlinx.io.SourcesJvmKt$asByteChannel$isClosed$1
                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return Boolean.valueOf(((RealSource) this.receiver).closed);
                }

                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
                public void set(Object obj) {
                    ((RealSource) this.receiver).closed = ((Boolean) obj).booleanValue();
                }
            };
        } else {
            if (!(source instanceof Buffer)) {
                throw new NoWhenBranchMatchedException();
            }
            mutablePropertyReference0Impl = new Function0() { // from class: kotlinx.io.SourcesJvmKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(SourcesJvmKt.asByteChannel$lambda$0());
                }
            };
        }
        return new ReadableByteChannel() { // from class: kotlinx.io.SourcesJvmKt.asByteChannel.1
            @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                source.close();
            }

            @Override // java.nio.channels.Channel
            public boolean isOpen() {
                return !mutablePropertyReference0Impl.invoke().booleanValue();
            }

            @Override // java.nio.channels.ReadableByteChannel
            public int read(ByteBuffer sink) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                return SourcesJvmKt.readAtMostTo(source, sink);
            }
        };
    }
}
