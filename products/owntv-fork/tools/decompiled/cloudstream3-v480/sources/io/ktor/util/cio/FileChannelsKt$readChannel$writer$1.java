package io.ktor.util.cio;

import coil3.util.UtilsKt;
import io.ktor.utils.io.WriterScope;
import j$.nio.channels.DesugarChannels;
import java.io.Closeable;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileChannels.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.cio.FileChannelsKt$readChannel$writer$1", f = "FileChannels.kt", i = {0, 0, 0, 0}, l = {42}, m = "invokeSuspend", n = {"$this$writer", UtilsKt.SCHEME_FILE, "fileChannel", "$i$a$-use-FileChannelsKt$readChannel$writer$1$3"}, s = {"L$0", "L$2", "L$3", "I$0"}, v = 1)
final class FileChannelsKt$readChannel$writer$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $endInclusive;
    final /* synthetic */ long $fileLength;
    final /* synthetic */ Lazy<RandomAccessFile> $randomAccessFile$delegate;
    final /* synthetic */ long $start;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FileChannelsKt$readChannel$writer$1(long j, long j2, long j3, Lazy<? extends RandomAccessFile> lazy, Continuation<? super FileChannelsKt$readChannel$writer$1> continuation) {
        super(2, continuation);
        this.$start = j;
        this.$endInclusive = j2;
        this.$fileLength = j3;
        this.$randomAccessFile$delegate = lazy;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FileChannelsKt$readChannel$writer$1 fileChannelsKt$readChannel$writer$1 = new FileChannelsKt$readChannel$writer$1(this.$start, this.$endInclusive, this.$fileLength, this.$randomAccessFile$delegate, continuation);
        fileChannelsKt$readChannel$writer$1.L$0 = obj;
        return fileChannelsKt$readChannel$writer$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((FileChannelsKt$readChannel$writer$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Throwable th;
        Closeable closeable;
        WriterScope writerScope = (WriterScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long j = this.$start;
            if (!(j >= 0)) {
                throw new IllegalArgumentException(("start position shouldn't be negative but it is " + j).toString());
            }
            long j2 = this.$endInclusive;
            long j3 = this.$fileLength;
            if (j2 <= j3 - 1) {
                RandomAccessFile channel$lambda$1 = FileChannelsKt.readChannel$lambda$1(this.$randomAccessFile$delegate);
                long j4 = this.$start;
                long j5 = this.$endInclusive;
                try {
                    RandomAccessFile randomAccessFile = channel$lambda$1;
                    FileChannel fileChannelConvertMaybeLegacyFileChannelFromLibrary = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(randomAccessFile.getChannel());
                    Intrinsics.checkNotNullExpressionValue(fileChannelConvertMaybeLegacyFileChannelFromLibrary, "getChannel(...)");
                    this.L$0 = SpillingKt.nullOutSpilledVariable(writerScope);
                    this.L$1 = channel$lambda$1;
                    this.L$2 = SpillingKt.nullOutSpilledVariable(randomAccessFile);
                    this.L$3 = SpillingKt.nullOutSpilledVariable(fileChannelConvertMaybeLegacyFileChannelFromLibrary);
                    this.I$0 = 0;
                    this.label = 1;
                    if (FileChannelsKt.writeToScope(fileChannelConvertMaybeLegacyFileChannelFromLibrary, writerScope, j4, j5, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    closeable = channel$lambda$1;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = channel$lambda$1;
                    throw th;
                }
            } else {
                throw new IllegalArgumentException(("endInclusive points to the position out of the file: file size = " + j3 + ", endInclusive = " + j2).toString());
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            closeable = (Closeable) this.L$1;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(closeable, th);
                    throw th4;
                }
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(closeable, null);
        return Unit.INSTANCE;
    }
}
