package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: Collection.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\t\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0006\u001a4\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086@¢\u0006\u0002\u0010\n\u001a8\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\u0002\"\u0010\b\u0001\u0010\f*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\fH\u0086@¢\u0006\u0002\u0010\u000e\u001ah\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00020\u000320\b\u0004\u0010\u0013\u001a*\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010\u0018\u001aV\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00020\u00032$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010\u0018\u001a\u0082\u0001\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00020\u00032$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00142$\b\u0004\u0010\u001b\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010\u001c\u001al\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0018\b\u0002\u0010\u001e*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0011\u0012\u0006\b\u0000\u0012\u0002H\u00020\u001f*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u001e2$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010 \u001a\u0098\u0001\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012\"\u0018\b\u0003\u0010\u001e*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0011\u0012\u0006\b\u0000\u0012\u0002H\u00120\u001f*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u001e2$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00142$\b\u0004\u0010\u001b\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010!\u001a~\u0010\"\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012\"\u0018\b\u0003\u0010\u001e*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0011\u0012\u0006\b\u0000\u0012\u0002H\u00120\u001f*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u001e20\b\u0004\u0010\u0013\u001a*\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010 \u001aV\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u0010\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00110\u00032$\b\u0004\u0010$\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010\u0018\u001al\u0010%\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0012\"\u0018\b\u0002\u0010\u001e*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0011\u0012\u0006\b\u0000\u0012\u0002H\u00120\u001f*\b\u0012\u0004\u0012\u0002H\u00110\u00032\u0006\u0010\u0004\u001a\u0002H\u001e2$\b\u0004\u0010$\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010 \u001a\\\u0010&\u001a\u0014\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00020\u00032$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010\u0018\u001a\u0088\u0001\u0010&\u001a\u0014\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u00010\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00020\u00032$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00142$\b\u0004\u0010\u001b\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010\u001c\u001ap\u0010'\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u001c\b\u0002\u0010\u001e*\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u001f*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u001e2$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010 \u001a\u009c\u0001\u0010'\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012\"\u001c\b\u0003\u0010\u001e*\u0016\u0012\u0006\b\u0000\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u00050\u001f*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u001e2$\b\u0004\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00142$\b\u0004\u0010\u001b\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0014H\u0086H¢\u0006\u0002\u0010!¨\u0006("}, d2 = {"toList", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/Flow;", "destination", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSet", "", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toCollection", "C", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associate", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "transform", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlin/Pair;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateBy", "keySelector", "valueTransform", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateByTo", "M", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "associateTo", "associateWith", "valueSelector", "associateWithTo", "groupBy", "groupByTo", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
final /* synthetic */ class FlowKt__CollectionKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associate$1, reason: invalid class name */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0, 0}, l = {365}, m = "associate", n = {"$this$associate", "transform", "$this$associateTo$iv", "destination$iv", "$i$f$associate", "$i$f$associateTo"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1<T, K, V> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associate(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateBy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0, 0}, l = {365}, m = "associateBy", n = {"$this$associateBy", "keySelector", "$this$associateByTo$iv", "destination$iv", "$i$f$associateBy", "$i$f$associateByTo"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"}, v = 1)
    static final class C11081<T, K> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C11081(Continuation<? super C11081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associateBy(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateBy$2, reason: invalid class name */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {365}, m = "associateBy", n = {"$this$associateBy", "keySelector", "valueTransform", "$this$associateByTo$iv", "destination$iv", "$i$f$associateBy", "$i$f$associateByTo"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass2<T, K, V> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associateBy(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateByTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0}, l = {129}, m = "associateByTo", n = {"$this$associateByTo", "destination", "keySelector", "$i$f$associateByTo"}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 1)
    static final class C11091<T, K, M extends Map<? super K, ? super T>> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C11091(Continuation<? super C11091> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associateByTo(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateByTo$3, reason: invalid class name */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0}, l = {162}, m = "associateByTo", n = {"$this$associateByTo", "destination", "keySelector", "valueTransform", "$i$f$associateByTo"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    static final class AnonymousClass3<T, K, V, M extends Map<? super K, ? super V>> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associateByTo(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0}, l = {ByteCode.MONITOREXIT}, m = "associateTo", n = {"$this$associateTo", "destination", "transform", "$i$f$associateTo"}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 1)
    static final class C11111<T, K, V, M extends Map<? super K, ? super V>> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C11111(Continuation<? super C11111> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associateTo(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateWith$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0, 0}, l = {365}, m = "associateWith", n = {"$this$associateWith", "valueSelector", "$this$associateWithTo$iv", "destination$iv", "$i$f$associateWith", "$i$f$associateWithTo"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"}, v = 1)
    static final class C11131<K, V> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C11131(Continuation<? super C11131> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associateWith(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateWithTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0}, l = {249}, m = "associateWithTo", n = {"$this$associateWithTo", "destination", "valueSelector", "$i$f$associateWithTo"}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 1)
    static final class C11141<K, V, M extends Map<? super K, ? super V>> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C11141(Continuation<? super C11141> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.associateWithTo(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$groupBy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0, 0}, l = {365}, m = "groupBy", n = {"$this$groupBy", "keySelector", "$this$groupByTo$iv", "destination$iv", "$i$f$groupBy", "$i$f$groupByTo"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"}, v = 1)
    static final class C11161<T, K> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C11161(Continuation<? super C11161> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.groupBy(null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$groupBy$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {365}, m = "groupBy", n = {"$this$groupBy", "keySelector", "valueTransform", "$this$groupByTo$iv", "destination$iv", "$i$f$groupBy", "$i$f$groupByTo"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"}, v = 1)
    static final class C11172<T, K, V> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C11172(Continuation<? super C11172> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.groupBy(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$groupByTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0}, l = {327}, m = "groupByTo", n = {"$this$groupByTo", "destination", "keySelector", "$i$f$groupByTo"}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 1)
    static final class C11181<T, K, M extends Map<? super K, List<T>>> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C11181(Continuation<? super C11181> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.groupByTo(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$groupByTo$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0, 0, 0, 0}, l = {357}, m = "groupByTo", n = {"$this$groupByTo", "destination", "keySelector", "valueTransform", "$i$f$groupByTo"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    static final class C11203<T, K, V, M extends Map<? super K, List<V>>> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C11203(Continuation<? super C11203> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__CollectionKt.groupByTo(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$toCollection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CollectionKt", f = "Collection.kt", i = {0, 0}, l = {21}, m = "toCollection", n = {"$this$toCollection", "destination"}, s = {"L$0", "L$1"}, v = 1)
    static final class C11221<T, C extends Collection<? super T>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C11221(Continuation<? super C11221> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.toCollection(null, null, this);
        }
    }

    public static final <T> Object toList(Flow<? extends T> flow, List<T> list, Continuation<? super List<? extends T>> continuation) {
        return FlowKt.toCollection(flow, list, continuation);
    }

    public static /* synthetic */ Object toList$default(Flow flow, List list, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return FlowKt.toList(flow, list, continuation);
    }

    public static final <T> Object toSet(Flow<? extends T> flow, Set<T> set, Continuation<? super Set<? extends T>> continuation) {
        return FlowKt.toCollection(flow, set, continuation);
    }

    public static /* synthetic */ Object toSet$default(Flow flow, Set set, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            set = new LinkedHashSet();
        }
        return FlowKt.toSet(flow, set, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, C extends Collection<? super T>> Object toCollection(Flow<? extends T> flow, final C c, Continuation<? super C> continuation) {
        C11221 c11221;
        if (continuation instanceof C11221) {
            c11221 = (C11221) continuation;
            if ((c11221.label & Integer.MIN_VALUE) != 0) {
                c11221.label -= Integer.MIN_VALUE;
            } else {
                c11221 = new C11221(continuation);
            }
        } else {
            c11221 = new C11221(continuation);
        }
        Object obj = c11221.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11221.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Collection collection = (Collection) c11221.L$1;
            ResultKt.throwOnFailure(obj);
            return collection;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector<? super Object> flowCollector = new FlowCollector() { // from class: kotlinx.coroutines.flow.FlowKt__CollectionKt.toCollection.2
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(T t, Continuation<? super Unit> continuation2) {
                c.add(t);
                return Unit.INSTANCE;
            }
        };
        c11221.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11221.L$1 = c;
        c11221.label = 1;
        return flow.collect(flowCollector, c11221) == coroutine_suspended ? coroutine_suspended : c;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, V> Object associate(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Pair<? extends K, ? extends V>>, ? extends Object> function2, Continuation<? super Map<K, ? extends V>> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$1;
            int i3 = anonymousClass1.I$0;
            Map map = (Map) anonymousClass1.L$3;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FlowCollector<? super Object> c11122 = new C11122<>(linkedHashMap, function2);
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(function2);
        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(flow);
        anonymousClass1.L$3 = linkedHashMap;
        anonymousClass1.I$0 = 0;
        anonymousClass1.I$1 = 0;
        anonymousClass1.label = 1;
        return flow.collect(c11122, anonymousClass1) == coroutine_suspended ? coroutine_suspended : linkedHashMap;
    }

    private static final <T, K, V> Object associate$$forInline(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super Pair<? extends K, ? extends V>>, ? extends Object> function2, Continuation<? super Map<K, ? extends V>> continuation) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        flow.collect(new C11122(linkedHashMap, function2), continuation);
        return linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K> Object associateBy(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super Map<K, ? extends T>> continuation) {
        C11081 c11081;
        if (continuation instanceof C11081) {
            c11081 = (C11081) continuation;
            if ((c11081.label & Integer.MIN_VALUE) != 0) {
                c11081.label -= Integer.MIN_VALUE;
            } else {
                c11081 = new C11081(continuation);
            }
        } else {
            c11081 = new C11081(continuation);
        }
        Object obj = c11081.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11081.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11081.I$1;
            int i3 = c11081.I$0;
            Map map = (Map) c11081.L$3;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FlowCollector<? super Object> c11102 = new C11102<>(linkedHashMap, function2);
        c11081.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11081.L$1 = SpillingKt.nullOutSpilledVariable(function2);
        c11081.L$2 = SpillingKt.nullOutSpilledVariable(flow);
        c11081.L$3 = linkedHashMap;
        c11081.I$0 = 0;
        c11081.I$1 = 0;
        c11081.label = 1;
        return flow.collect(c11102, c11081) == coroutine_suspended ? coroutine_suspended : linkedHashMap;
    }

    private static final <T, K> Object associateBy$$forInline(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super Map<K, ? extends T>> continuation) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        flow.collect(new C11102(linkedHashMap, function2), continuation);
        return linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, V> Object associateBy(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super Map<K, ? extends V>> continuation) {
        AnonymousClass2 anonymousClass2;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        } else {
            anonymousClass2 = new AnonymousClass2(continuation);
        }
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass2.I$1;
            int i3 = anonymousClass2.I$0;
            Map map = (Map) anonymousClass2.L$4;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FlowCollector<? super Object> anonymousClass4 = new AnonymousClass4<>(linkedHashMap, function2, function3);
        anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(function2);
        anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(flow);
        anonymousClass2.L$4 = linkedHashMap;
        anonymousClass2.I$0 = 0;
        anonymousClass2.I$1 = 0;
        anonymousClass2.label = 1;
        return flow.collect(anonymousClass4, anonymousClass2) == coroutine_suspended ? coroutine_suspended : linkedHashMap;
    }

    private static final <T, K, V> Object associateBy$$forInline(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super Map<K, ? extends V>> continuation) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        flow.collect(new AnonymousClass4(linkedHashMap, function2, function3), continuation);
        return linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, M extends Map<? super K, ? super T>> Object associateByTo(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super M> continuation) {
        C11091 c11091;
        if (continuation instanceof C11091) {
            c11091 = (C11091) continuation;
            if ((c11091.label & Integer.MIN_VALUE) != 0) {
                c11091.label -= Integer.MIN_VALUE;
            } else {
                c11091 = new C11091(continuation);
            }
        } else {
            c11091 = new C11091(continuation);
        }
        Object obj = c11091.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11091.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11091.I$0;
            Map map = (Map) c11091.L$1;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector<? super Object> c11102 = new C11102<>(m, function2);
        c11091.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11091.L$1 = m;
        c11091.L$2 = SpillingKt.nullOutSpilledVariable(function2);
        c11091.I$0 = 0;
        c11091.label = 1;
        return flow.collect(c11102, c11091) == coroutine_suspended ? coroutine_suspended : m;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateByTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    public static final class C11102<T> implements FlowCollector {

        /* JADX INFO: Incorrect field signature: TM; */
        final /* synthetic */ Map $destination;
        final /* synthetic */ Function2<T, Continuation<? super K>, Object> $keySelector;

        /* JADX WARN: Incorrect types in method signature: (TM;Lkotlin/jvm/functions/Function2<-TT;-Lkotlin/coroutines/Continuation<-TK;>;+Ljava/lang/Object;>;)V */
        public C11102(Map map, Function2 function2) {
            this.$destination = map;
            this.$keySelector = function2;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T t, Continuation<? super Unit> continuation) {
            FlowKt__CollectionKt$associateByTo$2$emit$1 flowKt__CollectionKt$associateByTo$2$emit$1;
            Map map;
            if (continuation instanceof FlowKt__CollectionKt$associateByTo$2$emit$1) {
                flowKt__CollectionKt$associateByTo$2$emit$1 = (FlowKt__CollectionKt$associateByTo$2$emit$1) continuation;
                if ((flowKt__CollectionKt$associateByTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__CollectionKt$associateByTo$2$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    flowKt__CollectionKt$associateByTo$2$emit$1 = new FlowKt__CollectionKt$associateByTo$2$emit$1(this, continuation);
                }
            } else {
                flowKt__CollectionKt$associateByTo$2$emit$1 = new FlowKt__CollectionKt$associateByTo$2$emit$1(this, continuation);
            }
            Object obj = flowKt__CollectionKt$associateByTo$2$emit$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = flowKt__CollectionKt$associateByTo$2$emit$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Map map2 = this.$destination;
                Function2<T, Continuation<? super K>, Object> function2 = this.$keySelector;
                flowKt__CollectionKt$associateByTo$2$emit$1.L$0 = t;
                flowKt__CollectionKt$associateByTo$2$emit$1.L$1 = map2;
                flowKt__CollectionKt$associateByTo$2$emit$1.label = 1;
                Object objInvoke = function2.invoke(t, flowKt__CollectionKt$associateByTo$2$emit$1);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objInvoke;
                map = map2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                map = (Map) flowKt__CollectionKt$associateByTo$2$emit$1.L$1;
                t = (T) flowKt__CollectionKt$associateByTo$2$emit$1.L$0;
                ResultKt.throwOnFailure(obj);
            }
            map.put(obj, t);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object emit$$forInline(T t, Continuation<? super Unit> continuation) {
            new FlowKt__CollectionKt$associateByTo$2$emit$1(this, continuation);
            this.$destination.put(this.$keySelector.invoke(t, (Continuation<? super K>) continuation), t);
            return Unit.INSTANCE;
        }
    }

    private static final <T, K, M extends Map<? super K, ? super T>> Object associateByTo$$forInline(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super M> continuation) {
        flow.collect(new C11102(m, function2), continuation);
        return m;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, V, M extends Map<? super K, ? super V>> Object associateByTo(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super M> continuation) {
        AnonymousClass3 anonymousClass3;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        Object obj = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass3.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass3.I$0;
            Map map = (Map) anonymousClass3.L$1;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector<? super Object> anonymousClass4 = new AnonymousClass4<>(m, function2, function3);
        anonymousClass3.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        anonymousClass3.L$1 = m;
        anonymousClass3.L$2 = SpillingKt.nullOutSpilledVariable(function2);
        anonymousClass3.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        anonymousClass3.I$0 = 0;
        anonymousClass3.label = 1;
        return flow.collect(anonymousClass4, anonymousClass3) == coroutine_suspended ? coroutine_suspended : m;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateByTo$4, reason: invalid class name */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    public static final class AnonymousClass4<T> implements FlowCollector {

        /* JADX INFO: Incorrect field signature: TM; */
        final /* synthetic */ Map $destination;
        final /* synthetic */ Function2<T, Continuation<? super K>, Object> $keySelector;
        final /* synthetic */ Function2<T, Continuation<? super V>, Object> $valueTransform;

        /* JADX WARN: Incorrect types in method signature: (TM;Lkotlin/jvm/functions/Function2<-TT;-Lkotlin/coroutines/Continuation<-TK;>;+Ljava/lang/Object;>;Lkotlin/jvm/functions/Function2<-TT;-Lkotlin/coroutines/Continuation<-TV;>;+Ljava/lang/Object;>;)V */
        public AnonymousClass4(Map map, Function2 function2, Function2 function3) {
            this.$destination = map;
            this.$keySelector = function2;
            this.$valueTransform = function3;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T t, Continuation<? super Unit> continuation) {
            FlowKt__CollectionKt$associateByTo$4$emit$1 flowKt__CollectionKt$associateByTo$4$emit$1;
            Object obj;
            Map map;
            Object obj2;
            if (continuation instanceof FlowKt__CollectionKt$associateByTo$4$emit$1) {
                flowKt__CollectionKt$associateByTo$4$emit$1 = (FlowKt__CollectionKt$associateByTo$4$emit$1) continuation;
                if ((flowKt__CollectionKt$associateByTo$4$emit$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__CollectionKt$associateByTo$4$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    flowKt__CollectionKt$associateByTo$4$emit$1 = new FlowKt__CollectionKt$associateByTo$4$emit$1(this, continuation);
                }
            } else {
                flowKt__CollectionKt$associateByTo$4$emit$1 = new FlowKt__CollectionKt$associateByTo$4$emit$1(this, continuation);
            }
            Object obj3 = flowKt__CollectionKt$associateByTo$4$emit$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = flowKt__CollectionKt$associateByTo$4$emit$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj3);
                Map map2 = this.$destination;
                Function2<T, Continuation<? super K>, Object> function2 = this.$keySelector;
                flowKt__CollectionKt$associateByTo$4$emit$1.L$0 = t;
                flowKt__CollectionKt$associateByTo$4$emit$1.L$1 = map2;
                flowKt__CollectionKt$associateByTo$4$emit$1.label = 1;
                Object objInvoke = function2.invoke(t, flowKt__CollectionKt$associateByTo$4$emit$1);
                if (objInvoke != coroutine_suspended) {
                    obj = t;
                    map = map2;
                    obj3 = objInvoke;
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                map = (Map) flowKt__CollectionKt$associateByTo$4$emit$1.L$1;
                obj = flowKt__CollectionKt$associateByTo$4$emit$1.L$0;
                ResultKt.throwOnFailure(obj3);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj2 = flowKt__CollectionKt$associateByTo$4$emit$1.L$2;
                map = (Map) flowKt__CollectionKt$associateByTo$4$emit$1.L$1;
                Object obj4 = flowKt__CollectionKt$associateByTo$4$emit$1.L$0;
                ResultKt.throwOnFailure(obj3);
            }
            map.put(obj2, obj3);
            return Unit.INSTANCE;
            Function2<T, Continuation<? super V>, Object> function3 = this.$valueTransform;
            flowKt__CollectionKt$associateByTo$4$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
            flowKt__CollectionKt$associateByTo$4$emit$1.L$1 = map;
            flowKt__CollectionKt$associateByTo$4$emit$1.L$2 = obj3;
            flowKt__CollectionKt$associateByTo$4$emit$1.label = 2;
            Object objInvoke2 = function3.invoke((T) obj, flowKt__CollectionKt$associateByTo$4$emit$1);
            if (objInvoke2 != coroutine_suspended) {
                Object obj5 = obj3;
                obj3 = objInvoke2;
                obj2 = obj5;
                map.put(obj2, obj3);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object emit$$forInline(T t, Continuation<? super Unit> continuation) {
            new FlowKt__CollectionKt$associateByTo$4$emit$1(this, continuation);
            this.$destination.put(this.$keySelector.invoke(t, (Continuation<? super K>) continuation), this.$valueTransform.invoke(t, (Continuation<? super V>) continuation));
            return Unit.INSTANCE;
        }
    }

    private static final <T, K, V, M extends Map<? super K, ? super V>> Object associateByTo$$forInline(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super M> continuation) {
        flow.collect(new AnonymousClass4(m, function2, function3), continuation);
        return m;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, V, M extends Map<? super K, ? super V>> Object associateTo(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super Pair<? extends K, ? extends V>>, ? extends Object> function2, Continuation<? super M> continuation) {
        C11111 c11111;
        if (continuation instanceof C11111) {
            c11111 = (C11111) continuation;
            if ((c11111.label & Integer.MIN_VALUE) != 0) {
                c11111.label -= Integer.MIN_VALUE;
            } else {
                c11111 = new C11111(continuation);
            }
        } else {
            c11111 = new C11111(continuation);
        }
        Object obj = c11111.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11111.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11111.I$0;
            Map map = (Map) c11111.L$1;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector<? super Object> c11122 = new C11122<>(m, function2);
        c11111.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11111.L$1 = m;
        c11111.L$2 = SpillingKt.nullOutSpilledVariable(function2);
        c11111.I$0 = 0;
        c11111.label = 1;
        return flow.collect(c11122, c11111) == coroutine_suspended ? coroutine_suspended : m;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    public static final class C11122<T> implements FlowCollector {

        /* JADX INFO: Incorrect field signature: TM; */
        final /* synthetic */ Map $destination;
        final /* synthetic */ Function2<T, Continuation<? super Pair<? extends K, ? extends V>>, Object> $transform;

        /* JADX WARN: Incorrect types in method signature: (TM;Lkotlin/jvm/functions/Function2<-TT;-Lkotlin/coroutines/Continuation<-Lkotlin/Pair<+TK;+TV;>;>;+Ljava/lang/Object;>;)V */
        public C11122(Map map, Function2 function2) {
            this.$destination = map;
            this.$transform = function2;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T t, Continuation<? super Unit> continuation) {
            FlowKt__CollectionKt$associateTo$2$emit$1 flowKt__CollectionKt$associateTo$2$emit$1;
            Map map;
            if (continuation instanceof FlowKt__CollectionKt$associateTo$2$emit$1) {
                flowKt__CollectionKt$associateTo$2$emit$1 = (FlowKt__CollectionKt$associateTo$2$emit$1) continuation;
                if ((flowKt__CollectionKt$associateTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__CollectionKt$associateTo$2$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    flowKt__CollectionKt$associateTo$2$emit$1 = new FlowKt__CollectionKt$associateTo$2$emit$1(this, continuation);
                }
            } else {
                flowKt__CollectionKt$associateTo$2$emit$1 = new FlowKt__CollectionKt$associateTo$2$emit$1(this, continuation);
            }
            Object obj = flowKt__CollectionKt$associateTo$2$emit$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = flowKt__CollectionKt$associateTo$2$emit$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Map map2 = this.$destination;
                Function2<T, Continuation<? super Pair<? extends K, ? extends V>>, Object> function2 = this.$transform;
                flowKt__CollectionKt$associateTo$2$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(t);
                flowKt__CollectionKt$associateTo$2$emit$1.L$1 = map2;
                flowKt__CollectionKt$associateTo$2$emit$1.label = 1;
                Object objInvoke = function2.invoke(t, flowKt__CollectionKt$associateTo$2$emit$1);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = objInvoke;
                map = map2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                map = (Map) flowKt__CollectionKt$associateTo$2$emit$1.L$1;
                Object obj2 = flowKt__CollectionKt$associateTo$2$emit$1.L$0;
                ResultKt.throwOnFailure(obj);
            }
            Pair pair = (Pair) obj;
            map.put(pair.getFirst(), pair.getSecond());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object emit$$forInline(T t, Continuation<? super Unit> continuation) {
            new FlowKt__CollectionKt$associateTo$2$emit$1(this, continuation);
            Map map = this.$destination;
            Pair pair = (Pair) this.$transform.invoke(t, (Continuation<? super Pair<? extends K, ? extends V>>) continuation);
            map.put(pair.getFirst(), pair.getSecond());
            return Unit.INSTANCE;
        }
    }

    private static final <T, K, V, M extends Map<? super K, ? super V>> Object associateTo$$forInline(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super Pair<? extends K, ? extends V>>, ? extends Object> function2, Continuation<? super M> continuation) {
        flow.collect(new C11122(m, function2), continuation);
        return m;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <K, V> Object associateWith(Flow<? extends K> flow, Function2<? super K, ? super Continuation<? super V>, ? extends Object> function2, Continuation<? super Map<K, ? extends V>> continuation) {
        C11131 c11131;
        if (continuation instanceof C11131) {
            c11131 = (C11131) continuation;
            if ((c11131.label & Integer.MIN_VALUE) != 0) {
                c11131.label -= Integer.MIN_VALUE;
            } else {
                c11131 = new C11131(continuation);
            }
        } else {
            c11131 = new C11131(continuation);
        }
        Object obj = c11131.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11131.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11131.I$1;
            int i3 = c11131.I$0;
            Map map = (Map) c11131.L$3;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FlowCollector<? super Object> c11152 = new C11152<>(linkedHashMap, function2);
        c11131.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11131.L$1 = SpillingKt.nullOutSpilledVariable(function2);
        c11131.L$2 = SpillingKt.nullOutSpilledVariable(flow);
        c11131.L$3 = linkedHashMap;
        c11131.I$0 = 0;
        c11131.I$1 = 0;
        c11131.label = 1;
        return flow.collect(c11152, c11131) == coroutine_suspended ? coroutine_suspended : linkedHashMap;
    }

    private static final <K, V> Object associateWith$$forInline(Flow<? extends K> flow, Function2<? super K, ? super Continuation<? super V>, ? extends Object> function2, Continuation<? super Map<K, ? extends V>> continuation) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        flow.collect(new C11152(linkedHashMap, function2), continuation);
        return linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <K, V, M extends Map<? super K, ? super V>> Object associateWithTo(Flow<? extends K> flow, M m, Function2<? super K, ? super Continuation<? super V>, ? extends Object> function2, Continuation<? super M> continuation) {
        C11141 c11141;
        if (continuation instanceof C11141) {
            c11141 = (C11141) continuation;
            if ((c11141.label & Integer.MIN_VALUE) != 0) {
                c11141.label -= Integer.MIN_VALUE;
            } else {
                c11141 = new C11141(continuation);
            }
        } else {
            c11141 = new C11141(continuation);
        }
        Object obj = c11141.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11141.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11141.I$0;
            Map map = (Map) c11141.L$1;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector<? super Object> c11152 = new C11152<>(m, function2);
        c11141.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11141.L$1 = m;
        c11141.L$2 = SpillingKt.nullOutSpilledVariable(function2);
        c11141.I$0 = 0;
        c11141.label = 1;
        return flow.collect(c11152, c11141) == coroutine_suspended ? coroutine_suspended : m;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$associateWithTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    public static final class C11152<T> implements FlowCollector {

        /* JADX INFO: Incorrect field signature: TM; */
        final /* synthetic */ Map $destination;
        final /* synthetic */ Function2<K, Continuation<? super V>, Object> $valueSelector;

        /* JADX WARN: Incorrect types in method signature: (TM;Lkotlin/jvm/functions/Function2<-TK;-Lkotlin/coroutines/Continuation<-TV;>;+Ljava/lang/Object;>;)V */
        public C11152(Map map, Function2 function2) {
            this.$destination = map;
            this.$valueSelector = function2;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(K k, Continuation<? super Unit> continuation) {
            FlowKt__CollectionKt$associateWithTo$2$emit$1 flowKt__CollectionKt$associateWithTo$2$emit$1;
            Map map;
            Object obj;
            if (continuation instanceof FlowKt__CollectionKt$associateWithTo$2$emit$1) {
                flowKt__CollectionKt$associateWithTo$2$emit$1 = (FlowKt__CollectionKt$associateWithTo$2$emit$1) continuation;
                if ((flowKt__CollectionKt$associateWithTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__CollectionKt$associateWithTo$2$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    flowKt__CollectionKt$associateWithTo$2$emit$1 = new FlowKt__CollectionKt$associateWithTo$2$emit$1(this, continuation);
                }
            } else {
                flowKt__CollectionKt$associateWithTo$2$emit$1 = new FlowKt__CollectionKt$associateWithTo$2$emit$1(this, continuation);
            }
            Object obj2 = flowKt__CollectionKt$associateWithTo$2$emit$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = flowKt__CollectionKt$associateWithTo$2$emit$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj2);
                Map map2 = this.$destination;
                Function2<K, Continuation<? super V>, Object> function2 = this.$valueSelector;
                flowKt__CollectionKt$associateWithTo$2$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(k);
                flowKt__CollectionKt$associateWithTo$2$emit$1.L$1 = map2;
                flowKt__CollectionKt$associateWithTo$2$emit$1.L$2 = k;
                flowKt__CollectionKt$associateWithTo$2$emit$1.label = 1;
                Object objInvoke = function2.invoke(k, flowKt__CollectionKt$associateWithTo$2$emit$1);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = objInvoke;
                map = map2;
                obj = k;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj3 = flowKt__CollectionKt$associateWithTo$2$emit$1.L$2;
                map = (Map) flowKt__CollectionKt$associateWithTo$2$emit$1.L$1;
                Object obj4 = flowKt__CollectionKt$associateWithTo$2$emit$1.L$0;
                ResultKt.throwOnFailure(obj2);
                obj = obj3;
            }
            map.put(obj, obj2);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final Object emit$$forInline(K k, Continuation<? super Unit> continuation) {
            new FlowKt__CollectionKt$associateWithTo$2$emit$1(this, continuation);
            this.$destination.put(k, this.$valueSelector.invoke(k, continuation));
            return Unit.INSTANCE;
        }
    }

    private static final <K, V, M extends Map<? super K, ? super V>> Object associateWithTo$$forInline(Flow<? extends K> flow, M m, Function2<? super K, ? super Continuation<? super V>, ? extends Object> function2, Continuation<? super M> continuation) {
        flow.collect(new C11152(m, function2), continuation);
        return m;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K> Object groupBy(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super Map<K, ? extends List<? extends T>>> continuation) {
        C11161 c11161;
        if (continuation instanceof C11161) {
            c11161 = (C11161) continuation;
            if ((c11161.label & Integer.MIN_VALUE) != 0) {
                c11161.label -= Integer.MIN_VALUE;
            } else {
                c11161 = new C11161(continuation);
            }
        } else {
            c11161 = new C11161(continuation);
        }
        Object obj = c11161.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11161.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11161.I$1;
            int i3 = c11161.I$0;
            Map map = (Map) c11161.L$3;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FlowCollector<? super Object> c11192 = new C11192<>(function2, linkedHashMap);
        c11161.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11161.L$1 = SpillingKt.nullOutSpilledVariable(function2);
        c11161.L$2 = SpillingKt.nullOutSpilledVariable(flow);
        c11161.L$3 = linkedHashMap;
        c11161.I$0 = 0;
        c11161.I$1 = 0;
        c11161.label = 1;
        return flow.collect(c11192, c11161) == coroutine_suspended ? coroutine_suspended : linkedHashMap;
    }

    private static final <T, K> Object groupBy$$forInline(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super Map<K, ? extends List<? extends T>>> continuation) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        flow.collect(new C11192(function2, linkedHashMap), continuation);
        return linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, V> Object groupBy(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super Map<K, ? extends List<? extends V>>> continuation) {
        C11172 c11172;
        if (continuation instanceof C11172) {
            c11172 = (C11172) continuation;
            if ((c11172.label & Integer.MIN_VALUE) != 0) {
                c11172.label -= Integer.MIN_VALUE;
            } else {
                c11172 = new C11172(continuation);
            }
        } else {
            c11172 = new C11172(continuation);
        }
        Object obj = c11172.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11172.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11172.I$1;
            int i3 = c11172.I$0;
            Map map = (Map) c11172.L$4;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FlowCollector<? super Object> c11214 = new C11214<>(function2, linkedHashMap, function3);
        c11172.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11172.L$1 = SpillingKt.nullOutSpilledVariable(function2);
        c11172.L$2 = SpillingKt.nullOutSpilledVariable(function3);
        c11172.L$3 = SpillingKt.nullOutSpilledVariable(flow);
        c11172.L$4 = linkedHashMap;
        c11172.I$0 = 0;
        c11172.I$1 = 0;
        c11172.label = 1;
        return flow.collect(c11214, c11172) == coroutine_suspended ? coroutine_suspended : linkedHashMap;
    }

    private static final <T, K, V> Object groupBy$$forInline(Flow<? extends T> flow, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super Map<K, ? extends List<? extends V>>> continuation) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        flow.collect(new C11214(function2, linkedHashMap, function3), continuation);
        return linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, M extends Map<? super K, List<T>>> Object groupByTo(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super M> continuation) {
        C11181 c11181;
        if (continuation instanceof C11181) {
            c11181 = (C11181) continuation;
            if ((c11181.label & Integer.MIN_VALUE) != 0) {
                c11181.label -= Integer.MIN_VALUE;
            } else {
                c11181 = new C11181(continuation);
            }
        } else {
            c11181 = new C11181(continuation);
        }
        Object obj = c11181.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11181.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11181.I$0;
            Map map = (Map) c11181.L$1;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector<? super Object> c11192 = new C11192<>(function2, m);
        c11181.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11181.L$1 = m;
        c11181.L$2 = SpillingKt.nullOutSpilledVariable(function2);
        c11181.I$0 = 0;
        c11181.label = 1;
        return flow.collect(c11192, c11181) == coroutine_suspended ? coroutine_suspended : m;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$groupByTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    public static final class C11192<T> implements FlowCollector {

        /* JADX INFO: Incorrect field signature: TM; */
        final /* synthetic */ Map $destination;
        final /* synthetic */ Function2<T, Continuation<? super K>, Object> $keySelector;

        /* JADX WARN: Incorrect types in method signature: (Lkotlin/jvm/functions/Function2<-TT;-Lkotlin/coroutines/Continuation<-TK;>;+Ljava/lang/Object;>;TM;)V */
        public C11192(Function2 function2, Map map) {
            this.$keySelector = function2;
            this.$destination = map;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T t, Continuation<? super Unit> continuation) {
            FlowKt__CollectionKt$groupByTo$2$emit$1 flowKt__CollectionKt$groupByTo$2$emit$1;
            if (continuation instanceof FlowKt__CollectionKt$groupByTo$2$emit$1) {
                flowKt__CollectionKt$groupByTo$2$emit$1 = (FlowKt__CollectionKt$groupByTo$2$emit$1) continuation;
                if ((flowKt__CollectionKt$groupByTo$2$emit$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__CollectionKt$groupByTo$2$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    flowKt__CollectionKt$groupByTo$2$emit$1 = new FlowKt__CollectionKt$groupByTo$2$emit$1(this, continuation);
                }
            } else {
                flowKt__CollectionKt$groupByTo$2$emit$1 = new FlowKt__CollectionKt$groupByTo$2$emit$1(this, continuation);
            }
            Object objInvoke = flowKt__CollectionKt$groupByTo$2$emit$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = flowKt__CollectionKt$groupByTo$2$emit$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objInvoke);
                Function2<T, Continuation<? super K>, Object> function2 = this.$keySelector;
                flowKt__CollectionKt$groupByTo$2$emit$1.L$0 = t;
                flowKt__CollectionKt$groupByTo$2$emit$1.label = 1;
                objInvoke = function2.invoke(t, flowKt__CollectionKt$groupByTo$2$emit$1);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                t = (T) flowKt__CollectionKt$groupByTo$2$emit$1.L$0;
                ResultKt.throwOnFailure(objInvoke);
            }
            Map map = this.$destination;
            Object obj = map.get(objInvoke);
            if (obj == null) {
                obj = (List) new ArrayList();
                map.put(objInvoke, obj);
            }
            ((List) obj).add(t);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object emit$$forInline(T t, Continuation<? super Unit> continuation) {
            new FlowKt__CollectionKt$groupByTo$2$emit$1(this, continuation);
            Object objInvoke = this.$keySelector.invoke(t, (Continuation<? super K>) continuation);
            Map map = this.$destination;
            Object obj = map.get(objInvoke);
            if (obj == null) {
                obj = (List) new ArrayList();
                map.put(objInvoke, obj);
            }
            ((List) obj).add(t);
            return Unit.INSTANCE;
        }
    }

    private static final <T, K, M extends Map<? super K, List<T>>> Object groupByTo$$forInline(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super M> continuation) {
        flow.collect(new C11192(function2, m), continuation);
        return m;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final <T, K, V, M extends Map<? super K, List<V>>> Object groupByTo(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super M> continuation) {
        C11203 c11203;
        if (continuation instanceof C11203) {
            c11203 = (C11203) continuation;
            if ((c11203.label & Integer.MIN_VALUE) != 0) {
                c11203.label -= Integer.MIN_VALUE;
            } else {
                c11203 = new C11203(continuation);
            }
        } else {
            c11203 = new C11203(continuation);
        }
        Object obj = c11203.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c11203.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c11203.I$0;
            Map map = (Map) c11203.L$1;
            ResultKt.throwOnFailure(obj);
            return map;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector<? super Object> c11214 = new C11214<>(function2, m, function3);
        c11203.L$0 = SpillingKt.nullOutSpilledVariable(flow);
        c11203.L$1 = m;
        c11203.L$2 = SpillingKt.nullOutSpilledVariable(function2);
        c11203.L$3 = SpillingKt.nullOutSpilledVariable(function3);
        c11203.I$0 = 0;
        c11203.label = 1;
        return flow.collect(c11214, c11203) == coroutine_suspended ? coroutine_suspended : m;
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__CollectionKt$groupByTo$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Collection.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    public static final class C11214<T> implements FlowCollector {

        /* JADX INFO: Incorrect field signature: TM; */
        final /* synthetic */ Map $destination;
        final /* synthetic */ Function2<T, Continuation<? super K>, Object> $keySelector;
        final /* synthetic */ Function2<T, Continuation<? super V>, Object> $valueTransform;

        /* JADX WARN: Incorrect types in method signature: (Lkotlin/jvm/functions/Function2<-TT;-Lkotlin/coroutines/Continuation<-TK;>;+Ljava/lang/Object;>;TM;Lkotlin/jvm/functions/Function2<-TT;-Lkotlin/coroutines/Continuation<-TV;>;+Ljava/lang/Object;>;)V */
        public C11214(Function2 function2, Map map, Function2 function3) {
            this.$keySelector = function2;
            this.$destination = map;
            this.$valueTransform = function3;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(T t, Continuation<? super Unit> continuation) {
            FlowKt__CollectionKt$groupByTo$4$emit$1 flowKt__CollectionKt$groupByTo$4$emit$1;
            List list;
            if (continuation instanceof FlowKt__CollectionKt$groupByTo$4$emit$1) {
                flowKt__CollectionKt$groupByTo$4$emit$1 = (FlowKt__CollectionKt$groupByTo$4$emit$1) continuation;
                if ((flowKt__CollectionKt$groupByTo$4$emit$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__CollectionKt$groupByTo$4$emit$1.label -= Integer.MIN_VALUE;
                } else {
                    flowKt__CollectionKt$groupByTo$4$emit$1 = new FlowKt__CollectionKt$groupByTo$4$emit$1(this, continuation);
                }
            } else {
                flowKt__CollectionKt$groupByTo$4$emit$1 = new FlowKt__CollectionKt$groupByTo$4$emit$1(this, continuation);
            }
            Object objInvoke = flowKt__CollectionKt$groupByTo$4$emit$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = flowKt__CollectionKt$groupByTo$4$emit$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objInvoke);
                Function2<T, Continuation<? super K>, Object> function2 = this.$keySelector;
                flowKt__CollectionKt$groupByTo$4$emit$1.L$0 = t;
                flowKt__CollectionKt$groupByTo$4$emit$1.label = 1;
                objInvoke = function2.invoke(t, flowKt__CollectionKt$groupByTo$4$emit$1);
                if (objInvoke != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                t = (T) flowKt__CollectionKt$groupByTo$4$emit$1.L$0;
                ResultKt.throwOnFailure(objInvoke);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                list = (List) flowKt__CollectionKt$groupByTo$4$emit$1.L$3;
                Object obj = flowKt__CollectionKt$groupByTo$4$emit$1.L$1;
                Object obj2 = flowKt__CollectionKt$groupByTo$4$emit$1.L$0;
                ResultKt.throwOnFailure(objInvoke);
            }
            list.add(objInvoke);
            return Unit.INSTANCE;
            Map map = this.$destination;
            Object obj3 = map.get(objInvoke);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                map.put(objInvoke, obj3);
            }
            List list2 = (List) obj3;
            Function2<T, Continuation<? super V>, Object> function3 = this.$valueTransform;
            flowKt__CollectionKt$groupByTo$4$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(t);
            flowKt__CollectionKt$groupByTo$4$emit$1.L$1 = SpillingKt.nullOutSpilledVariable(objInvoke);
            flowKt__CollectionKt$groupByTo$4$emit$1.L$2 = SpillingKt.nullOutSpilledVariable(list2);
            flowKt__CollectionKt$groupByTo$4$emit$1.L$3 = list2;
            flowKt__CollectionKt$groupByTo$4$emit$1.label = 2;
            objInvoke = function3.invoke(t, flowKt__CollectionKt$groupByTo$4$emit$1);
            if (objInvoke != coroutine_suspended) {
                list = list2;
                list.add(objInvoke);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final Object emit$$forInline(T t, Continuation<? super Unit> continuation) {
            new FlowKt__CollectionKt$groupByTo$4$emit$1(this, continuation);
            Object objInvoke = this.$keySelector.invoke(t, (Continuation<? super K>) continuation);
            Map map = this.$destination;
            Object obj = map.get(objInvoke);
            if (obj == null) {
                obj = (List) new ArrayList();
                map.put(objInvoke, obj);
            }
            ((List) obj).add(this.$valueTransform.invoke(t, (Continuation<? super V>) continuation));
            return Unit.INSTANCE;
        }
    }

    private static final <T, K, V, M extends Map<? super K, List<V>>> Object groupByTo$$forInline(Flow<? extends T> flow, M m, Function2<? super T, ? super Continuation<? super K>, ? extends Object> function2, Function2<? super T, ? super Continuation<? super V>, ? extends Object> function3, Continuation<? super M> continuation) {
        flow.collect(new C11214(function2, m, function3), continuation);
        return m;
    }
}
