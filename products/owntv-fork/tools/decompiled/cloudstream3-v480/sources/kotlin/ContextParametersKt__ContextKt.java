package kotlin;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aV\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0003\u001a\u0002H\u00022!\u0010\u0004\u001a\u001d\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\u0002H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\b\u001aj\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u00012\u0006\u0010\u000b\u001a\u0002H\t2\u0006\u0010\f\u001a\u0002H\n2'\u0010\u0004\u001a#\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u00010\r¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\u0004H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0002\u0010\u000e\u001a~\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u00012\u0006\u0010\u000b\u001a\u0002H\t2\u0006\u0010\f\u001a\u0002H\n2\u0006\u0010\u0010\u001a\u0002H\u000f2-\u0010\u0004\u001a)\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00010\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\u0006H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0004 \u0001¢\u0006\u0002\u0010\u0012\u001a\u0092\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0013\"\u0004\b\u0004\u0010\u00012\u0006\u0010\u000b\u001a\u0002H\t2\u0006\u0010\f\u001a\u0002H\n2\u0006\u0010\u0010\u001a\u0002H\u000f2\u0006\u0010\u0014\u001a\u0002H\u001323\u0010\u0004\u001a/\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00010\u0015¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\bH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0005 \u0001¢\u0006\u0002\u0010\u0016\u001a¦\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0013\"\u0004\b\u0004\u0010\u0017\"\u0004\b\u0005\u0010\u00012\u0006\u0010\u000b\u001a\u0002H\t2\u0006\u0010\f\u001a\u0002H\n2\u0006\u0010\u0010\u001a\u0002H\u000f2\u0006\u0010\u0014\u001a\u0002H\u00132\u0006\u0010\u0018\u001a\u0002H\u001729\u0010\u0004\u001a5\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u00010\u0019¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\nH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0006 \u0001¢\u0006\u0002\u0010\u001a\u001aº\u0001\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\n\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0013\"\u0004\b\u0004\u0010\u0017\"\u0004\b\u0005\u0010\u001b\"\u0004\b\u0006\u0010\u00012\u0006\u0010\u000b\u001a\u0002H\t2\u0006\u0010\f\u001a\u0002H\n2\u0006\u0010\u0010\u001a\u0002H\u000f2\u0006\u0010\u0014\u001a\u0002H\u00132\u0006\u0010\u0018\u001a\u0002H\u00172\u0006\u0010\u001c\u001a\u0002H\u001b2?\u0010\u0004\u001a;\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u00010\u001d¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\u0003\u0010\fH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0007 \u0001¢\u0006\u0002\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"}, d2 = {"context", "R", ExifInterface.GPS_DIRECTION_TRUE, JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "block", "Lkotlin/Function1;", "Lkotlin/ContextFunctionTypeParams;", PeertubeParsingHelper.COUNT_KEY, "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "Lkotlin/Function2;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "C", "c", "Lkotlin/Function3;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "D", "d", "Lkotlin/Function4;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", ExifInterface.LONGITUDE_EAST, "e", "Lkotlin/Function5;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "F", "f", "Lkotlin/Function6;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function6;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/ContextParametersKt")
class ContextParametersKt__ContextKt {
    private static final <T, R> R context(T t, Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(t);
    }

    private static final <A, B, R> R context(A a, B b, Function2<? super A, ? super B, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(a, b);
    }

    private static final <A, B, C, R> R context(A a, B b, C c, Function3<? super A, ? super B, ? super C, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(a, b, c);
    }

    private static final <A, B, C, D, R> R context(A a, B b, C c, D d, Function4<? super A, ? super B, ? super C, ? super D, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(a, b, c, d);
    }

    private static final <A, B, C, D, E, R> R context(A a, B b, C c, D d, E e, Function5<? super A, ? super B, ? super C, ? super D, ? super E, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(a, b, c, d, e);
    }

    private static final <A, B, C, D, E, F, R> R context(A a, B b, C c, D d, E e, F f, Function6<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(a, b, c, d, e, f);
    }
}
