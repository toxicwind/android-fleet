package kotlinx.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: SinksJvm.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0086\u0080\u0004\u001a\u000e\u0010\n\u001a\u00020\u000b*\u00020\u0002H\u0086\u0080\u0004\u001a\u0016\u0010\f\u001a\u00020\b*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0086\u0080\u0004¨\u0006\u0011"}, d2 = {"writeString", "", "Lkotlinx/io/Sink;", TypedValues.Custom.S_STRING, "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "startIndex", "", "endIndex", "asOutputStream", "Ljava/io/OutputStream;", "write", "source", "Ljava/nio/ByteBuffer;", "asByteChannel", "Ljava/nio/channels/WritableByteChannel;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SinksJvmKt {
    static final boolean asByteChannel$lambda$0() {
        return false;
    }

    static final boolean asOutputStream$lambda$0() {
        return false;
    }

    public static /* synthetic */ void writeString$default(Sink sink, String str, Charset charset, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = str.length();
        }
        writeString(sink, str, charset, i, i2);
    }

    public static final void writeString(Sink sink, String string, Charset charset, int i, int i2) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        _UtilKt.checkBounds(string.length(), i, i2);
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            Utf8Kt.writeString(sink, string, i, i2);
            return;
        }
        String strSubstring = string.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        byte[] bytes = strSubstring.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        sink.write(bytes, 0, bytes.length);
    }

    public static final OutputStream asOutputStream(final Sink sink) {
        final MutablePropertyReference0Impl mutablePropertyReference0Impl;
        Intrinsics.checkNotNullParameter(sink, "<this>");
        if (sink instanceof RealSink) {
            mutablePropertyReference0Impl = new MutablePropertyReference0Impl(sink) { // from class: kotlinx.io.SinksJvmKt$asOutputStream$isClosed$1
                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return Boolean.valueOf(((RealSink) this.receiver).closed);
                }

                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
                public void set(Object obj) {
                    ((RealSink) this.receiver).closed = ((Boolean) obj).booleanValue();
                }
            };
        } else {
            if (!(sink instanceof Buffer)) {
                throw new NoWhenBranchMatchedException();
            }
            mutablePropertyReference0Impl = new Function0() { // from class: kotlinx.io.SinksJvmKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(SinksJvmKt.asOutputStream$lambda$0());
                }
            };
        }
        return new OutputStream() { // from class: kotlinx.io.SinksJvmKt.asOutputStream.1
            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                if (mutablePropertyReference0Impl.invoke().booleanValue()) {
                    throw new IOException("Underlying sink is closed.");
                }
                Sink sink2 = sink;
                sink2.getBufferField().writeByte((byte) i);
                sink2.hintEmit();
            }

            @Override // java.io.OutputStream
            public void write(byte[] data, int offset, int byteCount) throws IOException {
                Intrinsics.checkNotNullParameter(data, "data");
                if (mutablePropertyReference0Impl.invoke().booleanValue()) {
                    throw new IOException("Underlying sink is closed.");
                }
                Sink sink2 = sink;
                sink2.getBufferField().write(data, offset, byteCount + offset);
                sink2.hintEmit();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
                if (mutablePropertyReference0Impl.invoke().booleanValue()) {
                    return;
                }
                sink.flush();
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                sink.close();
            }

            public String toString() {
                return sink + ".asOutputStream()";
            }
        };
    }

    public static final int write(Sink sink, ByteBuffer source) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        long sizeMut = sink.getBufferField().getSizeMut();
        BuffersJvmKt.transferFrom(sink.getBufferField(), source);
        long sizeMut2 = sink.getBufferField().getSizeMut() - sizeMut;
        sink.hintEmit();
        return (int) sizeMut2;
    }

    public static final WritableByteChannel asByteChannel(final Sink sink) {
        final MutablePropertyReference0Impl mutablePropertyReference0Impl;
        Intrinsics.checkNotNullParameter(sink, "<this>");
        if (sink instanceof RealSink) {
            mutablePropertyReference0Impl = new MutablePropertyReference0Impl(sink) { // from class: kotlinx.io.SinksJvmKt$asByteChannel$isClosed$1
                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return Boolean.valueOf(((RealSink) this.receiver).closed);
                }

                @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
                public void set(Object obj) {
                    ((RealSink) this.receiver).closed = ((Boolean) obj).booleanValue();
                }
            };
        } else {
            if (!(sink instanceof Buffer)) {
                throw new NoWhenBranchMatchedException();
            }
            mutablePropertyReference0Impl = new Function0() { // from class: kotlinx.io.SinksJvmKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(SinksJvmKt.asByteChannel$lambda$0());
                }
            };
        }
        return new WritableByteChannel() { // from class: kotlinx.io.SinksJvmKt.asByteChannel.1
            @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                sink.close();
            }

            @Override // java.nio.channels.Channel
            public boolean isOpen() {
                return !mutablePropertyReference0Impl.invoke().booleanValue();
            }

            @Override // java.nio.channels.WritableByteChannel
            public int write(ByteBuffer source) {
                Intrinsics.checkNotNullParameter(source, "source");
                if (mutablePropertyReference0Impl.invoke().booleanValue()) {
                    throw new IllegalStateException("Underlying sink is closed.".toString());
                }
                return SinksJvmKt.write(sink, source);
            }
        };
    }
}
