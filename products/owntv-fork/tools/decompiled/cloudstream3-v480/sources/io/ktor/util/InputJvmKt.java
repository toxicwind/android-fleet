package io.ktor.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.utils.io.core.ByteReadPacketKt;
import io.ktor.utils.io.core.InputKt;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Source;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: InputJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0003\u001a\u00020\u0002*\u00060\u0000j\u0002`\u0001¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/io/Source;", "Lio/ktor/utils/io/core/Input;", "Ljava/io/InputStream;", "asStream", "(Lkotlinx/io/Source;)Ljava/io/InputStream;", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class InputJvmKt {

    /* JADX INFO: renamed from: io.ktor.util.InputJvmKt$asStream$1, reason: invalid class name */
    /* JADX INFO: compiled from: InputJvm.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J'\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\tJ\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"io/ktor/util/InputJvmKt$asStream$1", "Ljava/io/InputStream;", "", "read", "()I", "", "buffer", TypedValues.CycleType.S_WAVE_OFFSET, "length", "([BII)I", "", PeertubeParsingHelper.COUNT_KEY, "skip", "(J)J", "", "close", "()V", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnonymousClass1 extends InputStream implements InputStreamRetargetInterface {
        final /* synthetic */ Source $this_asStream;

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }

        AnonymousClass1(Source source) {
            this.$this_asStream = source;
        }

        @Override // java.io.InputStream
        public int read() {
            if (InputKt.getEndOfInput(this.$this_asStream)) {
                return -1;
            }
            return this.$this_asStream.readByte();
        }

        @Override // java.io.InputStream
        public int read(byte[] buffer, int offset, int length) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            if (InputKt.getEndOfInput(this.$this_asStream)) {
                return -1;
            }
            return InputKt.readAvailable(this.$this_asStream, buffer, offset, length);
        }

        @Override // java.io.InputStream
        public long skip(long count) {
            return ByteReadPacketKt.discard(this.$this_asStream, count);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.$this_asStream.close();
        }
    }

    public static final InputStream asStream(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return new AnonymousClass1(source);
    }
}
