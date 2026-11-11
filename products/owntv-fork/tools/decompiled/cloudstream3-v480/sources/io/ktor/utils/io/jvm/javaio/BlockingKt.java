package io.ktor.utils.io.jvm.javaio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.ByteReadChannelOperationsKt;
import io.ktor.utils.io.ByteWriteChannel;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_concurrentKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Blocking.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0011\u0010\b\u001a\u00020\u0007*\u00020\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "Lkotlinx/coroutines/Job;", "parent", "Ljava/io/InputStream;", "toInputStream", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlinx/coroutines/Job;)Ljava/io/InputStream;", "Lio/ktor/utils/io/ByteWriteChannel;", "Ljava/io/OutputStream;", "toOutputStream", "(Lio/ktor/utils/io/ByteWriteChannel;)Ljava/io/OutputStream;", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BlockingKt {

    /* JADX INFO: renamed from: io.ktor.utils.io.jvm.javaio.BlockingKt$toInputStream$1, reason: invalid class name */
    /* JADX INFO: compiled from: Blocking.kt */
    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J'\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"io/ktor/utils/io/jvm/javaio/BlockingKt$toInputStream$1", "Ljava/io/InputStream;", "", "read", "()I", "", "b", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "len", "([BII)I", "", "blockingWait", "()V", "close", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnonymousClass1 extends InputStream implements InputStreamRetargetInterface {
        final /* synthetic */ ByteReadChannel $this_toInputStream;

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }

        AnonymousClass1(ByteReadChannel byteReadChannel) {
            this.$this_toInputStream = byteReadChannel;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.$this_toInputStream.isClosedForRead()) {
                return -1;
            }
            if (this.$this_toInputStream.getReadBuffer().exhausted()) {
                blockingWait();
            }
            if (this.$this_toInputStream.isClosedForRead()) {
                return -1;
            }
            return this.$this_toInputStream.getReadBuffer().readByte() & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] b, int off, int len) {
            Intrinsics.checkNotNullParameter(b, "b");
            if (this.$this_toInputStream.isClosedForRead()) {
                return -1;
            }
            if (this.$this_toInputStream.getReadBuffer().exhausted()) {
                blockingWait();
            }
            int atMostTo = this.$this_toInputStream.getReadBuffer().readAtMostTo(b, off, Math.min(ByteReadChannelOperationsKt.getAvailableForRead(this.$this_toInputStream), len) + off);
            if (atMostTo >= 0) {
                return atMostTo;
            }
            return this.$this_toInputStream.isClosedForRead() ? -1 : 0;
        }

        private final void blockingWait() {
            BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new BlockingKt$toInputStream$1$blockingWait$1(this.$this_toInputStream, null), 1, null);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ByteReadChannelKt.cancel(this.$this_toInputStream);
        }
    }

    public static final InputStream toInputStream(ByteReadChannel byteReadChannel, Job job) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        return new AnonymousClass1(byteReadChannel);
    }

    public static /* synthetic */ InputStream toInputStream$default(ByteReadChannel byteReadChannel, Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return toInputStream(byteReadChannel, job);
    }

    public static final OutputStream toOutputStream(final ByteWriteChannel byteWriteChannel) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        return new OutputStream() { // from class: io.ktor.utils.io.jvm.javaio.BlockingKt.toOutputStream.1
            @Override // java.io.OutputStream
            public void write(int b) {
                BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new BlockingKt$toOutputStream$1$write$1(byteWriteChannel, b, null), 1, null);
            }

            @Override // java.io.OutputStream
            public void write(byte[] b, int off, int len) {
                Intrinsics.checkNotNullParameter(b, "b");
                BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new BlockingKt$toOutputStream$1$write$2(byteWriteChannel, b, off, len, null), 1, null);
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
                BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new BlockingKt$toOutputStream$1$flush$1(byteWriteChannel, null), 1, null);
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new BlockingKt$toOutputStream$1$close$1(byteWriteChannel, null), 1, null);
            }
        };
    }
}
