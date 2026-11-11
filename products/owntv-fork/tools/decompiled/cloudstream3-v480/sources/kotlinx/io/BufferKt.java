package kotlinx.io;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aM\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u001a\u0010\u0005\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0006H\u0081\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"seek", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/io/Buffer;", "fromIndex", "", "lambda", "Lkotlin/Function2;", "Lkotlinx/io/Segment;", "(Lkotlinx/io/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "kotlinx-io-core"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BufferKt {
    public static final /* synthetic */ <T> T seek(Buffer buffer, long j, Function2<? super Segment, ? super Long, ? extends T> lambda) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        if (buffer.getHead() == null) {
            return lambda.invoke(null, -1L);
        }
        if (buffer.getSizeMut() - j < j) {
            Segment tail = buffer.getTail();
            long sizeMut = buffer.getSizeMut();
            while (tail != null && sizeMut > j) {
                sizeMut -= (long) (tail.getLimit() - tail.getPos());
                if (sizeMut <= j) {
                    break;
                }
                tail = tail.getPrev();
            }
            return lambda.invoke(tail, Long.valueOf(sizeMut));
        }
        Segment head = buffer.getHead();
        long j2 = 0;
        while (head != null) {
            long limit = ((long) (head.getLimit() - head.getPos())) + j2;
            if (limit > j) {
                break;
            }
            head = head.getNext();
            j2 = limit;
        }
        return lambda.invoke(head, Long.valueOf(j2));
    }
}
